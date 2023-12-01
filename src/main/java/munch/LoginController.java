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

    private static Connection connect;

    public static void setConnection(Connection connection)
    {
        connect = connection;
    }

    public void loginButtonAction(ActionEvent actionEvent) throws SQLException {
        String uname = username.getText();
        String pword = password.getText();

        System.out.println(uname);
        System.out.println(pword);
        String check = Users.checkUser(connect, uname, pword);

        if (!check.equals("password incorrect") && !check.equals("username not found")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials, please try again");
        }
    }
}

