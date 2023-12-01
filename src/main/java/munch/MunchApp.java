package munch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class MunchApp extends Application
{
    static Connection connect;
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/cs365munch?user=cs365munch&password=cs365munchpass");
        LoginController.setConnection(connect);
        Parent root = FXMLLoader.load(MunchApp.class.getResource("login.fxml"));
        primaryStage.setTitle("Munch");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
