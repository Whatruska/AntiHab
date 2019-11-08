package GUI.Settings.ChangePassword;

import java.net.URL;
import java.util.ResourceBundle;

import DataBase.Encryptor;
import DataBase.Managers.UserManager;
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
            if (validate()) {
                String crypt = Encryptor.encrypt(confirmPasswordField.getText());
                UserManager.updatePassword(getClient(), crypt);
                changePasswordButton.getScene().getWindow().hide();
                showNewFXMLByName("Settings");
            }
        });
    }

    private boolean validate(){
        String oldPass = oldPasswordField.getText();
        String encrypted = Encryptor.encrypt(oldPass);
        String passFromDB = UserManager.getPasswordFromDBByLogin(getClient().getLogin());
        return encrypted.equalsIgnoreCase(passFromDB) && newPasswordField.getText().length() > 7 && newPasswordField.getText().equalsIgnoreCase(confirmPasswordField.getText());
    }
}


