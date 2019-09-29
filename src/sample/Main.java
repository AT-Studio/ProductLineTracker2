/**
 * @author: Alexander Thieler
 * @description: This class is the main class of the application and is responsible for showing the
 * main stage
 * @Date: Sep 28 2019
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class of the application
 */
public class Main extends Application {

  /**
   * Prepares the scene and attaches it to the primary stage and finally shows the primary stage
   *
   * @param primaryStage will be shown to the user
   * @throws Exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("Production Line Tracker");
    Scene scene = new Scene(root, 800, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * First method to be called on application start
   *
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
