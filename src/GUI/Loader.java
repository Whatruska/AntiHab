package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;

public class Loader extends Application {
    private static Stage mainScene = new Stage();

    public static Stage getMainScene() {
        return mainScene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainScene = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("Login/LoginForm.fxml");
        loader.setLocation(url);
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("css/mainStyle.css").toExternalForm());
        mainScene.setScene(new Scene(root, 599, 549));
        mainScene.setResizable(false);
        mainScene.show();
    }


    public static void main(String[] args) throws SQLException {
        launch(args);
    }

    public static Application getInstance(){
        return new Application() {
            @Override
            public void start(Stage primaryStage) throws Exception {

            }
        };
    }
}
