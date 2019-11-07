package GUI.Login;
import java.net.URL;
import java.util.ResourceBundle;

import Core.User;
import DataBase.Managers.UserManager;
import GUI.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    void initialize() {
        loginButton.setOnAction(event -> {
            loginButton.getScene().getWindow().hide();
            showNewFXMLByName("Main");
        });

        registerButton.setOnAction(event -> {
            registerButton.getScene().getWindow().hide();
            showNewFXMLByName("Register");
        });
    }
}
