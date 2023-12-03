package munch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddRestaurantController {
    @FXML
    private TextField restaurantName;
    @FXML
    private TextField restaurantAddress;

    public void createRestaurantAction(ActionEvent actionEvent) throws SQLException, IOException {
        String name = restaurantName.getText();
        String address = restaurantAddress.getText();
        System.out.println(name);
        System.out.println(address);

        Restaurants.addRestaurant(MunchApp.connect, name, address);
        SceneController.switchToHome(actionEvent);

    }
    public void backAction(ActionEvent actionEvent) throws IOException {
        SceneController.switchToHome(actionEvent);
    }
}
