package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    protected final void showNewFXML(String name){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/" + name + "/" + name + "Form.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = Loader.mainScene;
        stage.setScene(new Scene(root));
        stage.show();
    }
}
