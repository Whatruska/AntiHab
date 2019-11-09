package GUI.Login;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import CSSManager.CSSManager;
import Core.User;
import DataBase.Encryptor;
import DataBase.Managers.UserManager;
import GUI.AuthorizedController;
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
            setWindow(loginButton.getScene().getWindow());
            if (login()) {
                hide();
                showNewFXMLByName("Main");
            }
        });

        registerButton.setOnAction(event -> {
            setWindow(loginButton.getScene().getWindow());
            hide();
            showNewFXMLByName("Register");
        });

    }

    private boolean login(){
        String login = loginField.getText();
        String password = null;
        try {
            password = UserManager.getPasswordFromDBByLogin(login);
            String encryptedPassword = Encryptor.encrypt(passwordField.getText());
            if (password != null && !password.equalsIgnoreCase("") && password.equalsIgnoreCase(encryptedPassword)){
                User user = UserManager.getUserByLogin(login);
                AuthorizedController.setClient(user);
                return true;
            }
            CSSManager.setError(loginField);
            CSSManager.setError(passwordField);
            return false;
        } catch (SQLException e) {
            hide();
            showError(e);
            return false;
        }
    }
}
