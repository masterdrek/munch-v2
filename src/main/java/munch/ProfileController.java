package munch;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Text username;

    @FXML
    ListView<Pair<Rating, Review>> ProfileListView;

    public void setListView() throws SQLException {


        // Call the method to get ratings and reviews
        List<Pair<Rating, Review>> ratingsAndReviews = Ratings.getRatingsAndReviewsForUser(MunchApp.connect,MunchApp.currentUserID);


        // Convert the list to an ObservableList for the ListView
        ObservableList<Pair<Rating, Review>> items = FXCollections.observableArrayList(ratingsAndReviews);

        // Set the cell factory to customize the appearance of each cell
        ProfileListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Pair<Rating, Review>> call(ListView<Pair<Rating, Review>> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Pair<Rating, Review> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            Restaurant r = null;
                            try {
                                r = Restaurants.getRestaurantInfo(item.getKey().restID);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            setText(r.name + ", Rating: " + item.getKey().getRating() + (item.getValue() != null?", Review: " + item.getValue().getReview():""));
                        }
                    }
                };
            }
        });

        // Set the items in the ListView
        ProfileListView.setItems(items);
    }

    public void backButtonAction(ActionEvent actionEvent) throws SQLException, IOException {
        SceneController.switchToHome(actionEvent);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            User user = Users.getUserInfo();
            username.setText(user.username + "'s ratings");
            setListView();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
