package GUI.Error;

import java.net.URL;
import java.util.ResourceBundle;

import GUI.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ErrorController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button returnButton;

    @FXML
    void initialize() {
        returnButton.setOnAction(event -> {
            returnButton.getScene().getWindow().hide();
            showNewFXMLByName("Login");
        });
    }
}

