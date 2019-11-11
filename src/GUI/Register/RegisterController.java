package GUI.Register;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import CSSManager.CSSManager;
import Core.Builders.UserBuilder;
import Core.User;
import DataBase.Encryptor;
import DataBase.Managers.UserManager;
import GUI.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController extends Controller {

    private static final int EVERYTHING_IS_OK = -1;
    private static final int SURNAME_IS_EMPTY = 0;
    private static final int NAME_IS_EMPTY = 1;
    private static final int LOGIN_IS_EMPTY = 2;
    private static final int LOGIN_IS_OCUPIED = 3;
    private static final int PASSWORD_IS_SHORT = 4;
    private static final int PASSWORDS_DOES_NOT_MATCHES = 6;

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

         registerButton.setOnAction(event -> {
             setWindow(registerButton.getScene().getWindow());
             try {
                 if (validate() == EVERYTHING_IS_OK){
                     hide();
                     UserManager.registerNewUser(formUser(), Encryptor.encrypt(passwordField.getText()));
                     User user = UserManager.getUserByLogin(loginField.getText());
                     showNewFXMLByName("Login");
                 }
             } catch (SQLException e) {
                 showError(e);
             }
         });
    }

    private int validate() throws SQLException {
        CSSManager.setNormal(surnameField);
        CSSManager.setNormal(nameField);
        CSSManager.setNormal(loginField);
        CSSManager.setNormal(passwordField);
        CSSManager.setNormal(confirmPasswordField);
        if (surnameField.getText().equalsIgnoreCase("")){
            CSSManager.setError(surnameField);
            return SURNAME_IS_EMPTY;
        } else if (nameField.getText().equalsIgnoreCase("")){
            CSSManager.setError(nameField);
            return NAME_IS_EMPTY;
        } else if (loginField.getText().equalsIgnoreCase("")){
            CSSManager.setError(loginField);
            return LOGIN_IS_EMPTY;
        } else if (UserManager.getAllLogins().contains(loginField.getText())){
            CSSManager.setError(loginField);
            return LOGIN_IS_OCUPIED;
        } else if (passwordField.getText().length() <= 7 || passwordField.getText().equalsIgnoreCase("")){
            CSSManager.setError(passwordField);
            return PASSWORD_IS_SHORT;
        } else if (!passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText())){
            CSSManager.setError(passwordField);
            CSSManager.setError(confirmPasswordField);
            return PASSWORDS_DOES_NOT_MATCHES;
        }
        return EVERYTHING_IS_OK;
    }

    private User formUser(){
        return UserBuilder
                .name(nameField.getText())
                .surname(surnameField.getText())
                .login(loginField.getText())
                .admin(false)
                .bottomLimit(10)
                .topLimit(25)
                .build();
    }
}

