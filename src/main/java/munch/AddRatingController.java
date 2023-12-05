package munch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class AddRatingController {
    @FXML
    private MenuItem rating0;
    @FXML
    private MenuItem rating1;
    @FXML
    private MenuItem rating2;
    @FXML
    private MenuItem rating3;
    @FXML
    private MenuItem rating4;
    @FXML
    private MenuItem rating5;

    @FXML
    private MenuButton selectedRating;
    @FXML
    private TextField review;

    @FXML
    private Label reviewError;

    public void selectRating0Action(ActionEvent actionEvent){
        System.out.println("rating: " + rating0.getText());
        selectedRating.setText("0");
    }
    public void selectRating1Action(ActionEvent actionEvent){
        System.out.println("rating: " + rating1.getText());
        selectedRating.setText("1");
    }
    public void selectRating2Action(ActionEvent actionEvent){
        System.out.println("rating: " + rating2.getText());
        selectedRating.setText("2");
    }
    public void selectRating3Action(ActionEvent actionEvent){
        System.out.println("rating: " + rating3.getText());
        selectedRating.setText("3");
    }
    public void selectRating4Action(ActionEvent actionEvent){
        System.out.println("rating: " + rating4.getText());
        selectedRating.setText("4");
    }
    public void selectRating5Action(ActionEvent actionEvent){
        System.out.println("rating: " + rating5.getText());
        selectedRating.setText("5");
    }
    public void createRatingAction(ActionEvent actionEvent) throws IOException {
        try {
            if (!selectedRating.getText().equals("")) {
                int ratingID = Ratings.addRating(MunchApp.connect, MunchApp.currentRestID, Integer.parseInt(selectedRating.getText()), MunchApp.currentUserID);
                if (!review.getText().equals("")) {
                    Reviews.attachReviewToRating(MunchApp.connect, ratingID, MunchApp.currentRestID, review.getText());
                }

                SceneController.switchToRestaurant(actionEvent);
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            reviewError.setText("You have already entered a review for this Restaurant");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void backAction(ActionEvent actionEvent) throws IOException {
        SceneController.switchToRestaurant(actionEvent);
    }
}
