package GUI.Settings.ChangeDifficulty;

import java.net.URL;
import java.util.ResourceBundle;

import Core.User;
import DataBase.Managers.TaskManager;
import DataBase.Managers.UserManager;
import GUI.AuthorizedController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class ChangeDifficultyController extends AuthorizedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changeDifficultyButton;

    @FXML
    private Button homeButton;

    @FXML
    private Spinner<Integer> bottomLimitSpinner;

    @FXML
    private Spinner<Integer> topLimitSpinner;

    @FXML
    void initialize() {
        super.init();
        SpinnerValueFactory<Integer> bottomFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,10);
        SpinnerValueFactory<Integer> topFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,10);
        bottomLimitSpinner.setValueFactory(bottomFactory);
        topLimitSpinner.setValueFactory(topFactory);

        changeDifficultyButton.setOnAction(event -> {
            client.setBottomLimit(bottomLimitSpinner.getValue());
            client.setTopLimit(topLimitSpinner.getValue());
            UserManager.updateUser(client);
            changeDifficultyButton.getScene().getWindow().hide();
            showNewFXMLByName("Settings");
        });
    }
}


