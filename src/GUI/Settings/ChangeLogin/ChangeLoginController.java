package GUI.Settings.ChangeLogin;

import java.net.URL;
import java.util.ResourceBundle;

import DataBase.Managers.UserManager;
import GUI.AuthorizedController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ChangeLoginController extends AuthorizedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changeLoginButton;

    @FXML
    private TextField changeLoginField;

    @FXML
    private Button homeButton;

    @FXML
    void initialize() {
        super.init();
        changeLoginButton.setOnAction(event -> {
            if (validate()) {
                getClient().setLogin(changeLoginField.getText());
                UserManager.updateUser(getClient());
                changeLoginButton.getScene().getWindow().hide();
                showNewFXMLByName("Settings");
            }
        });
    }

    private boolean validate(){
        return changeLoginField.getText().length() > 0 && !UserManager.getAllLogins().contains(changeLoginField.getText());
    }
}



