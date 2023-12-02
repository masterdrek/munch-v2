package munch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Text username;

    public void backButtonAction(ActionEvent actionEvent) throws SQLException, IOException {
        SceneController.switchToHome(actionEvent);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            User user = Users.getUserInfo();
            username.setText(user.username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
