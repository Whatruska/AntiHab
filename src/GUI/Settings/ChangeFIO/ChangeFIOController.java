package GUI.Settings.ChangeFIO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import CSSManager.CSSManager;
import DataBase.Managers.UserManager;
import GUI.AuthorizedController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ChangeFIOController extends AuthorizedController {

    private final int SURNAME_IS_EMPTY = 0;
    private final int NAME_IS_EMPTY = 1;
    private final int EVERYTHING_IS_OK = 2;

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
            setWindow(homeButton.getScene().getWindow());
            if (validate() == EVERYTHING_IS_OK) {
                hide();
                try {
                    getClient().setSurname(surnameField.getText());
                    getClient().setName(nameField.getText());
                    UserManager.updateUser(getClient());
                    showNewFXMLByName("Settings");
                } catch (SQLException e) {
                    showError(e);
                }
            }
        });
    }

    private int validate(){
        CSSManager.setNormal(surnameField);
        CSSManager.setNormal(nameField);
        if (surnameField.getText().length() == 0){
            CSSManager.setError(surnameField);
            return SURNAME_IS_EMPTY;
        } else if (nameField.getText().length() == 0){
            CSSManager.setError(nameField);
            return NAME_IS_EMPTY;
        } else {
            return EVERYTHING_IS_OK;
        }
    }
}



