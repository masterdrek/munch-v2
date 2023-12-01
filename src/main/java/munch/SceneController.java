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


   public static void switchToHome(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(MunchApp.class.getResource("homepage.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
   }
}
