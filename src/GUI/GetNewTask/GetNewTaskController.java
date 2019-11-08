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

    private Task task = TaskManager.getRandomTask(getClient());

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

        if (task.getUrl() == null){
            showError();
        } else {
            reloadPage();

            webView.setOnMouseClicked(event -> {
                engine.load(task.getUrl());
            });

            getThisTaskButton.setOnAction(event -> {
                TaskManager.assignTaskOnUser(getClient(), task);
                getThisTaskButton.getScene().getWindow().hide();
                showNewFXMLByName("Main");
            });

            randomNewTaskButton.setOnAction(event -> {
                task = TaskManager.getRandomTask(getClient());
                reloadPage();
            });
        }
    }

    private void showError(){
        System.out.println("ERROR");
        getThisTaskButton.setDisable(true);
        randomNewTaskButton.setDisable(true);
        WebEngine engine = webView.getEngine();
        engine.load("file:///error.html");
    }

    private void reloadPage(){
        WebEngine engine = webView.getEngine();
        engine.load(task.getUrl());

        taskNumField.setText(task.toString());
        difficultyField.setText(Integer.toString(task.getDifficulty()));
    }
}




