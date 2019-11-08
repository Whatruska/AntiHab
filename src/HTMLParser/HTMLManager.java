package HTMLParser;

public class HTMLManager {
    private static final String ERROR_PATH = "/HTMLParser/error.html";
    public static String getPathToErr(){
        return Class.class.getResource("/HTMLParser/error.html").toExternalForm();
    }
}
