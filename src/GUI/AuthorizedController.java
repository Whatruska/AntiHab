package GUI;

import GUI.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AuthorizedController extends Controller {

    @FXML
    protected Button homeButton;

    @FXML
    void initialize() {
        homeButton.setOnAction(event -> {
            homeButton.getScene().getWindow().hide();
            showNewFXMLByName("Main");
        });
    }

    protected void init(){
        initialize();
    }
}
