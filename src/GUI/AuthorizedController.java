package GUI;

import Core.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AuthorizedController extends Controller {

    protected static User client;

    public static User getClient() {
        return client;
    }

    public static void setClient(User client) {
        AuthorizedController.client = client;
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
