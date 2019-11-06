package GUI.Login;
import java.net.URL;
import java.util.ResourceBundle;

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
            System.out.println("Login");
        });

        registerButton.setOnAction(event -> {
            registerButton.getScene().getWindow().hide();
            showNewFXML("/GUI/Register/RegisterForm.fxml");
        });
    }
}
