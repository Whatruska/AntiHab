package GUI.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Core.Task;
import DataBase.Managers.TaskManager;
import DataBase.Managers.UserManager;
import GUI.AuthorizedController;
import GUI.Loader;
import HTMLParser.HTMLManager;
import HTMLParser.Parser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
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
        WebEngine engine = webView.getEngine();

        ObservableList<Task> tasks = convertToObservableList(getClient().getTasks());

        comboBox.setItems(tasks);

        rejectTaskButton.setDisable(true);

        helloText.setText("Привет, " + getClient().getName());

        logoutButton.setOnAction(event -> {
            setWindow(webView.getScene().getWindow());
            hide();
            showNewFXMLByName("Login");
        });

        comboBox.setOnAction(event -> {
            reload();
        });

        settingButton.setOnAction(event -> {
            setWindow(webView.getScene().getWindow());
            hide();
            showNewFXMLByName("Settings");
        });

        getNewTaskButton.setOnAction(event -> {
            setWindow(webView.getScene().getWindow());
            hide();
            showNewFXMLByName("GetNewTask");
        });

        rejectTaskButton.setOnAction(event -> {
            try {
                setWindow(webView.getScene().getWindow());
                ObservableList<Task> taskList = comboBox.getItems();
                Task currentTask = comboBox.getValue();
                TaskManager.reassignTaskFromUser(getClient(), currentTask);
                taskList.remove(currentTask);
                comboBox.setItems(taskList);
                if (taskList.size() == 0){
                    comboBox.setValue(null);
                }
                reload();
            } catch (SQLException e) {
                hide();
                showError(e);
            }
        });

        reload();
    }

    private ObservableList<Task> convertToObservableList(ArrayList<Task> tasks){
        return FXCollections.observableList(tasks);
    }

    private void reload(){
        Task task = comboBox.getValue();
        if (task == null){
            task = new Task();
            task.setUrl("");
            task.setNumber(0);
        }
        urlRef.setText(task.getUrl());
        WebEngine engine = webView.getEngine();
        try {
            if (task.getNumber() != 0) {
                Parser.parseTaskFormNumber(task.getNumber());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = HTMLManager.getPathToHTMLTask();
        if (task.getNumber() == 0){
            url = HTMLManager.getPathtoStartPage();
        }
        engine.load(url);
        urlRef.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Loader.getInstance().getHostServices().showDocument(urlRef.getText());
            }
        });

        rejectTaskButton.setDisable(task.getUrl().equalsIgnoreCase(""));
    }
}


