package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    protected final void showNewFXMLByPath(String path){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = Loader.getMainScene();
        stage.setScene(new Scene(root));
        stage.show();
    }

    protected final void showNewFXMLByName(String name){
        showNewFXMLByPath("/GUI/" + name + "/" + name + "Form.fxml");
    }

    protected final void showNewSettingsFXMLByName(String name){
        showNewFXMLByPath("/GUI/Settings/" + name + "/" + name + "Form.fxml");
    }
}
