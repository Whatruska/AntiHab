package GUI.Settings.ChangePassword;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import CSSManager.CSSManager;
import DataBase.Encryptor;
import DataBase.Managers.UserManager;
import GUI.AuthorizedController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class ChangePasswordController extends AuthorizedController {

    private final int EVERYTHING_IS_OK = 0;
    private final int INCORRECT_OLD_PASSWORD = 1;
    private final int NEW_PASSWORD_IS_EMPTY = 2;
    private final int PASSWORDS_DOES_NOT_MATCHES = 3;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changePasswordButton;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    void initialize() {
        super.init();

        changePasswordButton.setOnAction(event -> {
            setWindow(homeButton.getScene().getWindow());
            try {
                if (validate() == EVERYTHING_IS_OK) {
                    hide();
                    String crypt = Encryptor.encrypt(confirmPasswordField.getText());
                    UserManager.updatePassword(getClient(), crypt);
                    changePasswordButton.getScene().getWindow().hide();
                    showNewFXMLByName("Settings");
                }
            } catch (SQLException e) {
                hide();
                showError(e);
            }
        });
    }

    private int validate() throws SQLException {
        String oldPass = oldPasswordField.getText();
        String encrypted = Encryptor.encrypt(oldPass);
        String passFromDB = UserManager.getPasswordFromDBByLogin(getClient().getLogin());

        CSSManager.setNormal(oldPasswordField);
        CSSManager.setNormal(newPasswordField);
        CSSManager.setNormal(confirmPasswordField);

        if (oldPasswordField.getText().length() == 0 || !encrypted.equalsIgnoreCase(passFromDB)){
            CSSManager.setError(oldPasswordField);
            return INCORRECT_OLD_PASSWORD;
        } else if (newPasswordField.getText().length() == 0){
            CSSManager.setError(newPasswordField);
            return NEW_PASSWORD_IS_EMPTY;
        } else if (!newPasswordField.getText().equalsIgnoreCase(confirmPasswordField.getText())){
            CSSManager.setError(newPasswordField);
            CSSManager.setError(confirmPasswordField);
            return PASSWORDS_DOES_NOT_MATCHES;
        } else {
            return EVERYTHING_IS_OK;
        }

    }
}


