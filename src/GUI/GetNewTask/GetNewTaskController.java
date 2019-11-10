package GUI.GetNewTask;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Core.Task;
import DataBase.Managers.TaskManager;
import DataBase.Managers.UserManager;
import GUI.AuthorizedController;
import HTMLParser.HTMLManager;
import HTMLParser.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Window;

public class GetNewTaskController extends AuthorizedController {

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

        Task task = null;
        try {
            task = TaskManager.getRandomTask(getClient());

            reloadPage(task);

            Task finalTask1 = task.copy();

            getThisTaskButton.setOnAction(event -> {
                setWindow(webView.getScene().getWindow());
                hide();
                try {
                    setClient(UserManager.getUserByLogin(getClient().getLogin()));
                    TaskManager.assignTaskOnUser(getClient(), finalTask1);
                    showNewFXMLByName("Main");
                } catch (SQLException e) {
                    showError(e);
                }
            });

            randomNewTaskButton.setOnAction(event -> {
                setWindow(webView.getScene().getWindow());
                Task t = null;
                try {
                    t = TaskManager.getRandomTask(getClient());
                    reloadPage(t);
                } catch (SQLException e) {
                    hide();
                    showError(e);
                }
            });
        } catch (SQLException e) {
            setWindow(webView.getScene().getWindow());
            hide();
            showError(e);
        }
    }

    private void reloadPage(Task task){
        WebEngine engine = webView.getEngine();
        if (task.getName() != null) {
            try {
                Parser.parseTaskFormNumber(task.getNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
            engine.load(HTMLManager.getPathToHTMLTask());

            taskNumField.setText(task.toString());
            difficultyField.setText(Integer.toString(task.getDifficulty()));
        } else {
            engine.load(HTMLManager.getPathToErr());
            getThisTaskButton.setDisable(true);
            randomNewTaskButton.setDisable(true);
        }
    }
}




