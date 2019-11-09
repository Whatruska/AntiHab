package GUI;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Controller {

    private Window window;

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    protected void hide(){
        window.hide();
    }

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
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    protected final void showNewFXMLByName(String name){
        showNewFXMLByPath("/GUI/" + name + "/" + name + "Form.fxml");
    }

    protected final void showNewSettingsFXMLByName(String name){
        showNewFXMLByPath("/GUI/Settings/" + name + "/" + name + "Form.fxml");
    }

    protected final void showError(Exception e){
        e.printStackTrace();
        showNewFXMLByName("Error");
    }
}
