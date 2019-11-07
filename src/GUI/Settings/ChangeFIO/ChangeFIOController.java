package GUI.Settings.ChangeFIO;

import java.net.URL;
import java.util.ResourceBundle;

import DataBase.Managers.UserManager;
import GUI.AuthorizedController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ChangeFIOController extends AuthorizedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changePasswordButton;

    @FXML
    private PasswordField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private Button homeButton;

    @FXML
    void initialize() {
        super.init();
        changePasswordButton.setOnAction(event -> {
            client.setSurname(surnameField.getText());
            client.setName(nameField.getText());
            UserManager.updateUser(client);
            changePasswordButton.getScene().getWindow().hide();
            showNewFXMLByName("Settings");
        });
    }
}



