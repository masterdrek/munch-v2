package munch;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController
{
   private static Stage stage;
   private static Scene scene;
   private Parent root;

   public static void setStage(Stage s)
   {
       stage = s;
   }
   public static void switchToHome(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(MunchApp.class.getResource("homepage.fxml"));
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
   }
    public static void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MunchApp.class.getResource("login.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void switchToProfile(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(MunchApp.class.getResource("profile.fxml"));
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }
 }
