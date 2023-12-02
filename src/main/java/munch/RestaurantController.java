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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class RestaurantController implements Initializable {
    @FXML
    Text restaurantName;

    public void setListView()
    {
        restaurantName.setText(Integer.toString(MunchApp.currentRestID));

    }
    public void backButtonAction(ActionEvent actionEvent) throws SQLException, IOException {
        SceneController.switchToHome(actionEvent);
        MunchApp.currentRestID = -1;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListView();
    }
}
