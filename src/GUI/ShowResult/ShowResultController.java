package GUI.ShowResult;

import java.net.URL;
import java.util.ResourceBundle;

import GUI.AuthorizedController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ShowResultController extends AuthorizedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button homeButton;

    @FXML
    private WebView webView;

    @FXML
    void initialize() {
        super.init();
        WebEngine engine = webView.getEngine();
        engine.load("https://acmp.ru/index.asp?main=status");
        webView.setOnMouseClicked(event -> {
            engine.load("https://acmp.ru/index.asp?main=status");
        });
    }
}





