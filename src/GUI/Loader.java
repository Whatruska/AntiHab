package GUI;

import Core.Task;
import Core.User;
import DataBase.Managers.TaskManager;
import DataBase.Managers.UserManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Loader extends Application {
    private static Stage mainScene = new Stage();

    public static Stage getMainScene() {
        return mainScene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainScene = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login/LoginForm.fxml"));
        root.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        mainScene.setScene(new Scene(root, 599, 549));
        mainScene.setResizable(false);
        mainScene.show();
    }


    public static void main(String[] args) {
        //launch(args);
        User user = UserManager.getUserByLogin("Whatruska");
        System.out.println(user);
        Task task = TaskManager.getTaskByNumber(1);
        System.out.println(task);
    }

    public static Application getInstance(){
        return new Application() {
            @Override
            public void start(Stage primaryStage) throws Exception {

            }
        };
    }
}
