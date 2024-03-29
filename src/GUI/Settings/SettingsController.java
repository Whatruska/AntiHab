package GUI.Settings;

import java.net.URL;
import java.util.ResourceBundle;

import GUI.AuthorizedController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SettingsController extends AuthorizedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Button changeLoginButton;

    @FXML
    private Button changeFIOButton;

    @FXML
    private Button changeDifficultyButton;

    @FXML
    void initialize() {
        super.init();

        changePasswordButton.setOnAction(event -> {
            setWindow(changeDifficultyButton.getScene().getWindow());
            hide();
            showNewSettingsFXMLByName("ChangePassword");
        });

        changeLoginButton.setOnAction(event -> {
            setWindow(changeDifficultyButton.getScene().getWindow());
            hide();
            showNewSettingsFXMLByName("ChangeLogin");
        });

        changeDifficultyButton.setOnAction(event -> {
            setWindow(changeDifficultyButton.getScene().getWindow());
            hide();
            showNewSettingsFXMLByName("ChangeDifficulty");
        });

        changeFIOButton.setOnAction(event -> {
            setWindow(changeDifficultyButton.getScene().getWindow());
            hide();
            showNewSettingsFXMLByName("ChangeFIO");
        });
    }
}


