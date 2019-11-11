/**
 * @author: Alexander Thieler
 * @description: This class is responsible for handling user interactions with the UI and
 * interactions with the database
 * @Date: Sep 28 2019
 */

package sample;

import static org.h2.expression.Aggregate.AggregateType.COUNT;
import static sample.DBUtils.ITEMTYPE_COUNTER_COUNT;
import static sample.DBUtils.ITEMTYPE_COUNTER_TABLE_NAME;
import static sample.DBUtils.ITEMTYPE_COUNTER_TYPE;
import static sample.DBUtils.PRODUCTIONRECORD_DATE_PRODUCED;
import static sample.DBUtils.PRODUCTIONRECORD_PRODUCTION_NUM;
import static sample.DBUtils.PRODUCTIONRECORD_PRODUCT_ID;
import static sample.DBUtils.PRODUCTIONRECORD_SERIAL_NUM;
import static sample.DBUtils.PRODUCTIONRECORD_TABLE_NAME;
import static sample.DBUtils.PRODUCT_ID;
import static sample.DBUtils.PRODUCT_MANUFACTURER;
import static sample.DBUtils.PRODUCT_NAME;
import static sample.DBUtils.PRODUCT_TABLE_NAME;
import static sample.DBUtils.PRODUCT_TYPE;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Class handles user interactions with the UI as well as DB interactions
 */
public class Controller implements Initializable {

  @FXML
  TableView<Product> productionLineTableView;

  @FXML
  TableView<Product> produceTableView;

  @FXML
  ComboBox<String> productLineComboBox;

  @FXML
  TextField productLineName;

  @FXML
  TextField productLineManufacturer;

  @FXML
  ChoiceBox<String> productLineType;

  @FXML
  TextArea productionLogTextArea;

  Connection conn;

  Statement stmt;

  /**
   * Called when the controller is created
   *
   * @param location
   * @param resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    initializeDB();
    setProductionLineTableView();
    setProduceTableView();
    setProductionLog();

    ObservableList<String> numbers = FXCollections.observableArrayList();
    for (int i = 1; i <= 10; i++) {
      numbers.add(i + "");
    }
    productLineComboBox.setItems(numbers);
    productLineComboBox.setEditable(true);
    productLineComboBox.getSelectionModel().selectFirst();

    ObservableList<String> types = FXCollections.observableArrayList();
    for (ItemType itemType : ItemType.values()) {
      types.add(itemType.getLabel());
    }
    productLineType.setItems(types);
    productLineType.getSelectionModel().selectFirst();

    productionLogTextArea.setEditable(false);
//    productionLogTextArea.appendText("Production Number\tProduct ID\tSerial Number\tDate Produced\n");
  }

  /**
   * Called when the button is clicked that adds a new item to the catalog. Reads the required text
   * fields and then inserts the data into the database and refreshes the catalog.
   *
   * @param event
   */
  @FXML
  protected void addProduct(ActionEvent event) {
    System.out.println("This button will add products");

    String name = productLineName.getText();
    String manufacturer = productLineManufacturer.getText();
    String type = productLineType.getValue();

    if (!name.isEmpty() && !manufacturer.isEmpty() && !type.isEmpty()) {
      try {
        String sql = "INSERT INTO " + PRODUCT_TABLE_NAME + "(NAME, TYPE, MANUFACTURER) " +
            "VALUES ('" + name + "', '" + type + "', '" + manufacturer + "');";

        //Find bugs does not like that the above SQL statement is dynamically generated. However, I need
        //to know name, type and manufacturer of the product before being able to generate the statement
        stmt.execute(sql);

        setProductionLineTableView();
        setProduceTableView();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Populates the tableview (catalog) with items retrieved from the database.
   */
  private void setProductionLineTableView() {
    productionLineTableView.setItems(getProducts());
  }

  /**
   * Produces new products. The products are added to the ProductionRecord table with the
   * appropriate fields. The counter for the particular product time is increased in the
   * ItemType_Counter table. Finally, the production log is updated.
   *
   * @param event
   */
  @FXML
  protected void produceProducts(ActionEvent event) {
    Product product = produceTableView.getSelectionModel().getSelectedItem();
    int numItems = 0;
    try {
      numItems = Integer.parseInt(productLineComboBox.getSelectionModel().getSelectedItem());
    } catch (Exception e) {
      // invalid entry
    }

    if (product != null && numItems > 0) {

      int currCount = 0;

      try {
        String query =
            "SELECT * FROM " + ITEMTYPE_COUNTER_TABLE_NAME + " WHERE " + ITEMTYPE_COUNTER_TYPE
                + " = '" + product.type + "'";
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
          currCount = rs.getInt(ITEMTYPE_COUNTER_COUNT);

          int newCount = currCount + numItems;

          String sql =
              "UPDATE ITEMTYPE_COUNTER SET COUNT = " + newCount + " WHERE TYPE = '" + product.type
                  + "'";

          stmt.execute(sql);
        } else {
          String sql = "INSERT INTO ITEMTYPE_COUNTER(TYPE, COUNT) VALUES (?, ?)";
          PreparedStatement preparedStatement = conn.prepareStatement(sql);
          preparedStatement.setString(1, product.type);
          preparedStatement.setInt(2, numItems);

          preparedStatement.execute();
          preparedStatement.close();
        }

        rs.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }

      for (int i = 1; i <= numItems; i++) {
        ProductionRecord record = new ProductionRecord(product, currCount + i);

        try {
          String sql = "INSERT INTO PRODUCTIONRECORD(PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED) VALUES (?, ?, ?)";
          PreparedStatement preparedStatement = conn.prepareStatement(sql);
          preparedStatement.setInt(1, record.getProductID());
          preparedStatement.setString(2, record.getSerialNum());
          preparedStatement.setTimestamp(3, new Timestamp(record.getProdDate()));

          //Find bugs does not like that the above SQL statement is dynamically generated. However, I need
          //to know name, type and manufacturer of the product before being able to generate the statement
          preparedStatement.execute();

          preparedStatement.close();
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        }
      }
    }

    setProductionLog();
  }

  /**
   * This methods retrieves the products from the database and then populates the Produce tab.
   */
  private void setProduceTableView() {
    produceTableView.setItems(getProducts());
  }

  /**
   * This method retrieves the production log from the database and then populates the TextArea in
   * the Production Log tab. For each product the product name is fetched from the Product table to
   * replace the product ID.
   */
  private void setProductionLog() {
    productionLogTextArea.clear();

    ObservableList<ProductionRecord> records = FXCollections.observableArrayList();

    try {
      String query = "SELECT * FROM " + PRODUCTIONRECORD_TABLE_NAME;
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        ProductionRecord record = new ProductionRecord(rs.getInt(PRODUCTIONRECORD_PRODUCTION_NUM),
            rs.getInt(PRODUCTIONRECORD_PRODUCT_ID), rs.getString(PRODUCTIONRECORD_SERIAL_NUM),
            rs.getTimestamp(PRODUCTIONRECORD_DATE_PRODUCED).getTime());
        records.add(record);

        Statement tempStmt = conn.createStatement();

        String nameQuery = "SELECT NAME FROM PRODUCT WHERE ID = " + record.getProductID();
        ResultSet nameRS = tempStmt.executeQuery(nameQuery);
        nameRS.next();

        productionLogTextArea
            .appendText(record.getProductionNum() + "\t\t" + nameRS.getString(PRODUCT_NAME) +
                "\t\t" + record.getSerialNum() + "\t\t" + new Date(record.getProdDate()) + "\n");

        nameRS.close();
        tempStmt.close();
      }

      rs.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Initializes the database by creating a connection and initializing the statement object.
   */
  private void initializeDB() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/HR";

    final String USER = "";
    final String PASS = "";

    try {
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      stmt = conn.createStatement();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Retrieves the list of products already in the database from the database and returns them.
   *
   * @return List of products from the database
   */
  private ObservableList<Product> getProducts() {
    ObservableList<Product> products = FXCollections.observableArrayList();

    try {
      String query = "SELECT * FROM " + PRODUCT_TABLE_NAME;
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        products.add(new Product(rs.getInt(PRODUCT_ID), rs.getString(PRODUCT_NAME),
            rs.getString(PRODUCT_MANUFACTURER),
            rs.getString(PRODUCT_TYPE)));
      }

      rs.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return products;
  }

}
