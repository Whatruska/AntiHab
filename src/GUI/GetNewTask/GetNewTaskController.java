package GUI.GetNewTask;

import java.net.URL;
import java.util.ResourceBundle;

import Core.Task;
import DataBase.Managers.TaskManager;
import GUI.AuthorizedController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class GetNewTaskController extends AuthorizedController {

    private Task task = TaskManager.getRandomTask(client);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button homeButton;

    @FXML
    private WebView webView;

    @FXML
    private TextField taskNumField;

    @FXML
    private TextField difficultyField;

    @FXML
    private Button randomNewTaskButton;

    @FXML
    private Button getThisTaskButton;

    @FXML
    void initialize() {
        super.init();
        WebEngine engine = webView.getEngine();
        engine.load("http://acmp.ru/index.asp?main=task&id_task=1");

        webView.setOnMouseClicked(event -> {
            engine.load("http://acmp.ru/index.asp?main=task&id_task=1");
        });

        getThisTaskButton.setOnAction(event -> {
            TaskManager.assignTaskOnUser(client, task);
            getThisTaskButton.getScene().getWindow().hide();
            showNewFXMLByName("Main");
        });

        randomNewTaskButton.setOnAction(event -> {
            task = TaskManager.getRandomTask(client);
            engine.load(task.getUrl());
        });

    }
}




