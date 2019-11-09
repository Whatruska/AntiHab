package GUI.Settings.ChangeDifficulty;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.Managers.UserManager;
import GUI.AuthorizedController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;

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

        int bottom = getClient().getBottomLimit();
        int top = getClient().getTopLimit();

        initFactories(bottom, top);

        changeDifficultyButton.setOnAction(event -> {
            setWindow(homeButton.getScene().getWindow());
            if (validate()) {
                hide();
                try {
                    getClient().setBottomLimit(bottomLimitSpinner.getValue());
                    getClient().setTopLimit(topLimitSpinner.getValue());
                    UserManager.updateUser(getClient());
                    changeDifficultyButton.getScene().getWindow().hide();
                    showNewFXMLByName("Settings");
                } catch (SQLException e) {
                    showError(e);
                }
            }
        });
    }

    private void initFactories(int bottom, int top){
        if (bottom > top){
            bottom = top;
        }

        SpinnerValueFactory<Integer> bottomFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,top, bottom);
        SpinnerValueFactory<Integer> topFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(bottom,100,top);

        bottomLimitSpinner.setValueFactory(bottomFactory);
        topLimitSpinner.setValueFactory(topFactory);
    }

    private boolean validate(){
        return topLimitSpinner.getValue() >= bottomLimitSpinner.getValue();
    }
}


