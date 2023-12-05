package munch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class HomePageController
{
    @FXML
    TextField restaurantName;
    @FXML
    Label wrongSearch;

    @FXML
    Button findBest;
    public void profileAction(ActionEvent actionEvent) throws IOException {
        //Switch to profileScene
        SceneController.switchToProfile(actionEvent);
    }

    public void logOutAction(ActionEvent actionEvent) throws IOException {
        //Switch back to loginScene
        System.out.println("logging out of userid: " + Integer.toString(MunchApp.currentUserID));
        MunchApp.currentUserID = -1;
        SceneController.switchToLogin(actionEvent);

    }

    public void addRestaurantAction(ActionEvent actionEvent) throws  IOException {
        SceneController.switchToAddRestaurant(actionEvent);
    }

    public void searchRestaurantAction(ActionEvent actionEvent) throws IOException, SQLException {
        System.out.println("searched restaurant:" + restaurantName.getText());
        Restaurant restaurant = Restaurants.getRestaurantInfo(restaurantName.getText());
        if(restaurant != null) {
            MunchApp.currentRestID = restaurant.restID;
            SceneController.switchToRestaurant(actionEvent);
            wrongSearch.setText("");
        }else {
            System.out.println("couldnt find restaurant");
            wrongSearch.setText("Couldn't find restaurant. Please try again or use the add restaurant button!");
        }
    }

    public void findBestRestaurants(ActionEvent actionEvent) throws IOException, SQLException
    {
        System.out.println("Finding top 10 restaurants");
        SceneController.switchToBestRestaurants(actionEvent);
    }
}
