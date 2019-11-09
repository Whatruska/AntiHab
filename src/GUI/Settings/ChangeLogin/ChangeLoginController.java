package GUI.Settings.ChangeLogin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import CSSManager.CSSManager;
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
            setWindow(homeButton.getScene().getWindow());
            try {
                if (validate()) {
                    hide();
                    getClient().setLogin(changeLoginField.getText());
                    UserManager.updateUser(getClient());
                    showNewFXMLByName("Settings");
                }
            } catch (SQLException e) {
                hide();
                showError(e);
            }
        });
    }

    private boolean validate() throws SQLException {
        CSSManager.setNormal(changeLoginField);
        if (changeLoginField.getText().length() > 0 && !UserManager.getAllLogins().contains(changeLoginField.getText())){
            CSSManager.setError(changeLoginField);
            return true;
        }
        return false;
    }
}



