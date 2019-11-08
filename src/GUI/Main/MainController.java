package GUI.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Core.Task;
import DataBase.Managers.TaskManager;
import GUI.AuthorizedController;
import GUI.Loader;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MainController extends AuthorizedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logoutButton;

    @FXML
    private ComboBox<Task> comboBox;

    @FXML
    private WebView webView;

    @FXML
    private Button getNewTaskButton;

    @FXML
    private Button rejectTaskButton;

    @FXML
    private Button settingButton;

    @FXML
    private Text helloText;

    @FXML
    private Hyperlink urlRef;

    @FXML
    private Button passTaskButton;

    @FXML
    void initialize() {
        WebEngine engine = webView.getEngine();

        ObservableList<Task> tasks = convertToObservableList(getClient().getTasks());

        comboBox.setItems(tasks);

        passTaskButton.setDisable(true);
        rejectTaskButton.setDisable(true);

        helloText.setText("Привет, " + getClient().getName());

        webView.setOnMouseClicked(event -> {
            engine.load(comboBox.getValue().getUrl());
        });

        logoutButton.setOnAction(event -> {
            logoutButton.getScene().getWindow().hide();
            showNewFXMLByName("Login");
        });

        comboBox.setOnAction(event -> {
            reload();
        });

        settingButton.setOnAction(event -> {
            logoutButton.getScene().getWindow().hide();
            showNewFXMLByName("Settings");
        });

        getNewTaskButton.setOnAction(event -> {
            getNewTaskButton.getScene().getWindow().hide();
            showNewFXMLByName("GetNewTask");
        });

        rejectTaskButton.setOnAction(event -> {
            ObservableList<Task> taskList = comboBox.getItems();
            Task currentTask = comboBox.getValue();
            taskList.remove(currentTask);
            comboBox.setItems(taskList);
            TaskManager.reassignTaskFromUser(getClient(), currentTask);
            reload();
        });

        passTaskButton.setOnAction(event -> {
            getNewTaskButton.getScene().getWindow().hide();
            setTargetTask(comboBox.getValue());
            showNewFXMLByName("PassTask");
        });
    }

    private ObservableList<Task> convertToObservableList(ArrayList<Task> tasks){
        return FXCollections.observableList(tasks);
    }

    private void reload(){
        Task task = comboBox.getValue();
        urlRef.setText(task.getUrl());
        WebEngine engine = webView.getEngine();
        engine.load(task.getUrl());
        urlRef.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Loader.getInstance().getHostServices().showDocument(urlRef.getText());
            }
        });
        if (comboBox.getValue().getUrl() != null){
            rejectTaskButton.setDisable(false);
            passTaskButton.setDisable(false);
        }
    }
}


