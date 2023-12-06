package munch;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class BestRestaurantController implements Initializable
{
    @FXML
    ListView<String> bestRestaurants;

    public void setListView() throws SQLException {
        ResultSet rs = Ratings.getBestRestaurants(MunchApp.connect);

        ObservableList<String> restaurantItems = FXCollections.observableArrayList();
        while(rs.next())
        {
            String restaurantName = rs.getString("name");
            String location = rs.getString("location");
            double avgRating = rs.getDouble("avg_rating");

            String restaurantInfo = String.format("%s - %s: %.2f", restaurantName, location, avgRating);

            restaurantItems.add(restaurantInfo);
        }
        bestRestaurants.setItems(restaurantItems);
    }

    public void backAction(ActionEvent actionEvent) throws IOException {
        SceneController.switchToHome(actionEvent);
    }

    public void listViewClick(MouseEvent mouseEvent) throws SQLException {
        if (mouseEvent.getClickCount() == 2) {
            String selectedRestaurant = bestRestaurants.getSelectionModel().getSelectedItem();
            if (selectedRestaurant != null)
            {

                String restaurantName = selectedRestaurant.split(" - ")[0];
                Restaurant restaurant = Restaurants.getRestaurantInfo(restaurantName);
                MunchApp.currentRestID = Objects.requireNonNull(restaurant).restID;
                try
                {
                    ActionEvent actionEvent = new ActionEvent();
                    SceneController.switchToRestaurant(actionEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            setListView();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
