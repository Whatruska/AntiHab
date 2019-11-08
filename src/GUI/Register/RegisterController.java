package GUI.Register;

import java.net.URL;
import java.util.ResourceBundle;

import Core.Builders.UserBuilder;
import Core.User;
import DataBase.Encryptor;
import DataBase.Managers.UserManager;
import GUI.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;

import javax.swing.*;

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
             if (validate() == EVERYTHING_IS_OK){
                 UserManager.registerNewUser(formUser(), Encryptor.encrypt(passwordField.getText()));
                 User user = UserManager.getUserByLogin(loginField.getText());
                 System.out.println(user);
                 registerButton.getScene().getWindow().hide();
                 showNewFXMLByName("Login");
             }
         });
    }

    private int validate(){
        if (surnameField.getText().equalsIgnoreCase("")){
            return SURNAME_IS_EMPTY;
        } else if (nameField.getText().equalsIgnoreCase("")){
            return NAME_IS_EMPTY;
        } else if (loginField.getText().equalsIgnoreCase("")){
            return LOGIN_IS_EMPTY;
        } else if (UserManager.getAllLogins().contains(loginField.getText())){
            return LOGIN_IS_OCUPIED;
        } else if (passwordField.getText().length() <= 7 || passwordField.getText().equalsIgnoreCase("")){
            return PASSWORD_IS_SHORT;
        } else if (!passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText())){
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

