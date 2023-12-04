package munch;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private Button button;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;



    public void loginButtonAction(ActionEvent actionEvent) throws SQLException, IOException {
        String uname = username.getText();
        String pword = password.getText();

        System.out.println(uname);
        System.out.println(pword);
        String check = Users.checkUser(MunchApp.connect, uname, pword);

        if (!check.equals("password incorrect") && !check.equals("username not found")) {
            //Switched to home page if login is successful
            MunchApp.currentUserID = Integer.parseInt(check);
            SceneController.switchToHome(actionEvent);
            wrongLogIn.setText("");

        } else if (uname.isEmpty() && pword.isEmpty()) {
            wrongLogIn.setText("Please enter your data");
        } else {
            wrongLogIn.setText("Username or Password Incorrect: Please try again");
        }
    }

    public void createAccountAction(ActionEvent actionEvent) throws SQLException {
        String uname = username.getText();
        String pword = password.getText();

        System.out.println(uname);
        System.out.println(pword);

        Users.addUser(MunchApp.connect, "", uname, pword);

    }
}

