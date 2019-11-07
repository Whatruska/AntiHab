package GUI.Main;

import java.net.URL;
import java.util.ResourceBundle;

import GUI.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MainController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logoutButton;

    @FXML
    private ComboBox<?> comboBox;

    @FXML
    private WebView webView;

    @FXML
    private Button getNewTaskButton;

    @FXML
    private Button rejectTaskButton;

    @FXML
    private Button settingButton;

    @FXML
    void initialize() {
        WebEngine engine = webView.getEngine();
        engine.load("http://acmp.ru/index.asp?main=task&id_task=1");

        webView.setOnMouseClicked(event -> {
            engine.load("http://acmp.ru/index.asp?main=task&id_task=1");
        });

        logoutButton.setOnAction(event -> {
            logoutButton.getScene().getWindow().hide();
            showNewFXMLByName("Login");
        });

        comboBox.setOnAction(event -> {

        });

        settingButton.setOnAction(event -> {
            logoutButton.getScene().getWindow().hide();
            showNewFXMLByName("Settings");
        });
    }
}


