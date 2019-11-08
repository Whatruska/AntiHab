package GUI.PassTask;

import java.net.URL;
import java.util.ResourceBundle;

import GUI.AuthorizedController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class PassTaskController extends AuthorizedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button homeButton;

    @FXML
    private TextArea solutionTextArea;

    @FXML
    private Button submitButton;

    @FXML
    void initialize() {
        super.init();
        submitButton.setOnAction(event -> {
            startParser();
            submitButton.getScene().getWindow().hide();
            showNewFXMLByName("ShowResult");
        });
    }

    private void startParser(){}
}




