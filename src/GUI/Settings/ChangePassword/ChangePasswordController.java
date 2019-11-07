package GUI.Settings.ChangePassword;

import java.net.URL;
import java.util.ResourceBundle;

import GUI.AuthorizedController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ChangePasswordController extends AuthorizedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changePasswordButton;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private TextField oldPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    void initialize() {
        super.init();
        changePasswordButton.setOnAction(event -> {
            changePasswordButton.getScene().getWindow().hide();
            showNewFXMLByName("Settings");
        });
    }
}


