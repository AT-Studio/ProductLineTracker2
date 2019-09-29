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

  Statement stmt;

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

  @FXML
  protected void addProduct(ActionEvent event) {
    System.out.println("This button will add products");

    String name = productLineName.getText();
    String manufacturer = productLineManufacturer.getText();
    String type = productLineType.getValue();

    if (!name.isEmpty() && !manufacturer.isEmpty() && !type.isEmpty()) {
      try {
        String sql = "INSERT INTO " + PRODUCT_TABLE_NAME + "(NAME, TYPE, MANUFACTURER) " +
            "VALUES ('" + name + "', '" + type + "', '"  + manufacturer + "');";

        stmt.execute(sql);

        setProductionLineTableView();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private void setProductionLineTableView() {
    productionLineTableView.setItems(getProducts());
  }

  @FXML
  protected void produceProducts(ActionEvent event) {
    System.out.println("This button will produce products");
  }

  private void initializeDB() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/HR";

    final String USER = "";
    final String PASS = "";

    try {
      //Add code that does the following
      //Create a connection to your Oracle database using the orcluser account

      Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);;

      //Use the connection to create a statement

      stmt = conn.createStatement();

//      Connection test = new Connection()
//
//      //Use the Database MetaData to generate a resultSet based on tables that
//      //contain the word job
//
//      DatabaseMetaData dbmd = conn.getMetaData();
//      ResultSet rsTables = dbmd.getTables(null, null, "JOB%", new String[] {"TABLE"});
//
//      //Add the returned table names to the comboBox, selecting the first item
//
//      while (rsTables.next()) {
//        System.out.println(rsTables.getString("TABLE_NAME"));
//        cboTableName.getItems().add(rsTables.getString("TABLE_NAME"));
//      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } //end try catch
  }// end method initializeDB

//  private void showData() {
//    ta.clear();
//    String tableName = cboTableName.getValue();
//    try {
//      //Create query that will select from the chosen table name
//
//      String sql = "SELECT * FROM " + tableName;
//
//      //Create a ResultSet object to hold the data from the executed query.
//
//      ResultSet rs = stmt.executeQuery(sql);
//
//      //Use the MetaData from the ResultSet to append the column names to the text
//      //area
//
//      ResultSetMetaData dbmd = rs.getMetaData();
//
//      for (int i = 1; i <= dbmd.getColumnCount(); i++) {
//        ta.appendText(dbmd.getColumnName(i) + "\t\t");
//      }
//      ta.appendText("\n");
//
//      //Use a while loop to display the values of the returned data to the text
//      //area
//
//      while (rs.next()) {
//        for (int i = 1; i <= dbmd.getColumnCount(); i++) {
//          ta.appendText(rs.getString(i) + "\t\t");
//        }
//        ta.appendText("\n");
//      }
//
//    } catch (Exception e) {
//      e.printStackTrace();
//    } //end try catch
//  } //end method showData

  private ObservableList<Product> getProducts() {
    ObservableList<Product> products = FXCollections.observableArrayList();

    try {
      String query = "SELECT * FROM " + PRODUCT_TABLE_NAME;

      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        System.out.println(rs.getString(PRODUCT_NAME));
        products.add(new Product(rs.getString(PRODUCT_NAME), rs.getString(PRODUCT_TYPE), rs.getString(PRODUCT_MANUFACTURER)));
      }

      rs.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return products;
  }

}
