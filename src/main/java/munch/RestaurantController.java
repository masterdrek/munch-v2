package munch;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class RestaurantController implements Initializable {
    @FXML
    Text restaurantName;
    @FXML
    Text averageRating;
    @FXML
    ListView<Pair<Rating, Review>> RestaurantListView;

    public void setListView() throws SQLException {
        restaurantName.setText(Restaurants.getRestaurantInfo(MunchApp.currentRestID).name);

        // Call the method to get ratings and reviews
        List<Pair<Rating, Review>> ratingsAndReviews = Ratings.getRatingsAndReviewsForRestaurant(MunchApp.connect,MunchApp.currentRestID);

        //compute average rating
        int sum = 0;
        int count = 0;
        for(Pair<Rating, Review> rating : ratingsAndReviews){
            sum += rating.getKey().rating;
            count++;
        }
        if(count != 0){
            averageRating.setText(String.format("Average Rating: %.2f", ((double)sum)/count));
        }else{
            averageRating.setText("no ratings yet");
        }

        // Convert the list to an ObservableList for the ListView
        ObservableList<Pair<Rating, Review>> items = FXCollections.observableArrayList(ratingsAndReviews);

        // Set the cell factory to customize the appearance of each cell
        RestaurantListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Pair<Rating, Review>> call(ListView<Pair<Rating, Review>> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Pair<Rating, Review> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText("Rating: " + item.getKey().getRating() + (item.getValue() != null?", Review: " + item.getValue().getReview():""));
                        }
                    }
                };
            }
        });

        // Set the items in the ListView
        RestaurantListView.setItems(items);
    }
    public void backButtonAction(ActionEvent actionEvent) throws SQLException, IOException {
        SceneController.switchToHome(actionEvent);
        MunchApp.currentRestID = -1;
    }
    public void AddReviewAction(ActionEvent actionEvent) throws SQLException, IOException {
        SceneController.switchToAddReview(actionEvent);
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
