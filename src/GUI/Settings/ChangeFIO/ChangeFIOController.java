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
            if (validate()) {
                getClient().setSurname(surnameField.getText());
                getClient().setName(nameField.getText());
                UserManager.updateUser(getClient());
                changePasswordButton.getScene().getWindow().hide();
                showNewFXMLByName("Settings");
            }
        });
    }

    private boolean validate(){
        return surnameField.getText().length() > 0 && nameField.getText().length() > 0;
    }
}



