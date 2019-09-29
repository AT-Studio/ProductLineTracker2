/**
 * @author: Alexander Thieler
 * @description: This class is responsible for handling user interactions with the UI and
 * interactions with the database
 * @Date: Sep 28 2019
 */

package sample;

import static sample.DBUtils.PRODUCT_MANUFACTURER;
import static sample.DBUtils.PRODUCT_NAME;
import static sample.DBUtils.PRODUCT_TABLE_NAME;
import static sample.DBUtils.PRODUCT_TYPE;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Class handles user interactions with the UI as well as DB interactions
 */
public class Controller implements Initializable {

  @FXML
  TableView<Product> productionLineTableView;

  @FXML
  ComboBox<String> productLineComboBox;

  @FXML
  TextField productLineName;

  @FXML
  TextField productLineManufacturer;

  @FXML
  ChoiceBox<String> productLineType;

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

    ObservableList<String> numbers = FXCollections.observableArrayList();
    for (int i = 1; i <= 10; i++) {
      numbers.add(i + "");
    }
    productLineComboBox.setItems(numbers);
    productLineComboBox.setEditable(true);
    productLineComboBox.getSelectionModel().selectFirst();

    ObservableList<String> types = FXCollections.observableArrayList();
    types.add("Video");
    types.add("Audio");
    types.add("Audio/Video");
    productLineType.setItems(types);
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
   * Implementation is still missing. This method will be called when the button is clicked to add
   * an item to production.
   *
   * @param event
   */
  @FXML
  protected void produceProducts(ActionEvent event) {
    System.out.println("This button will produce products");
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
        products.add(new Product(rs.getString(PRODUCT_NAME), rs.getString(PRODUCT_TYPE),
            rs.getString(PRODUCT_MANUFACTURER)));
      }

      rs.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return products;
  }

}
