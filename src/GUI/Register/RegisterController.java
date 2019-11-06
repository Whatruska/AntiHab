package GUI.Register;

import java.net.URL;
import java.util.ResourceBundle;

import GUI.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registerButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField surnameField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField loginField;

    @FXML
    void initialize() {

    }
}

