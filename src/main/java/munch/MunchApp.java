package munch;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MunchApp extends Application
{
    @Override
    public void start(Stage primaryStage)
    {

        //Munch title
        primaryStage.setTitle("Munch");

        Label titleLabel = new Label("Munch");
        titleLabel.setStyle("-fx-font-size: 24;");

        VBox titleVbox = new VBox(titleLabel);
        titleVbox.setAlignment(Pos.TOP_LEFT);

        StackPane root = new StackPane();
        root.getChildren().add(titleVbox);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
