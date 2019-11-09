package CSSManager;


import javafx.scene.control.TextField;

public class CSSManager {
    private static String getErrStyle(){
        return "-fx-border-color: red";
    }

    private static String getNormalStyle(){
        return "-fx-border-color: #080A03";
    }

    public static void setNormal(TextField field){
        field.setStyle(getNormalStyle());
    }

    public static void setError(TextField field){
        field.setStyle(getErrStyle());
    }

}
