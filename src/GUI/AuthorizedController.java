package GUI;

import Core.Task;
import Core.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AuthorizedController extends Controller {

    private static User client;
    private static Task targetTask;

    public static User getClient() {
        return client;
    }

    public static void setClient(User client) {
        AuthorizedController.client = client;
    }

    public static Task getTargetTask() {
        return targetTask;
    }

    public static void setTargetTask(Task targetTask) {
        AuthorizedController.targetTask = targetTask;
    }

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
