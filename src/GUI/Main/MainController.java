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
    void initialize() {

        ObservableList<Task> tasks = convertToObservableList(client.getTasks());

        comboBox.setItems(tasks);

        helloText.setText("Привет, " + client.getName());

        WebEngine engine = webView.getEngine();

        webView.setOnMouseClicked(event -> {
            engine.load("http://acmp.ru/index.asp?main=task&id_task=1");
        });

        logoutButton.setOnAction(event -> {
            logoutButton.getScene().getWindow().hide();
            showNewFXMLByName("Login");
        });

        comboBox.setOnAction(event -> {
            engine.load(comboBox.getValue().getUrl());
            urlRef.setText(comboBox.getValue().getUrl());
            urlRef.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Loader.getInstance().getHostServices().showDocument(urlRef.getText());
                }
            });
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
            Task currentTask = comboBox.getValue();
            TaskManager.reassignTaskFromUser(client, currentTask);
        });
    }

    private ObservableList<Task> convertToObservableList(ArrayList<Task> tasks){
         return FXCollections.observableList(tasks);
    }
}


