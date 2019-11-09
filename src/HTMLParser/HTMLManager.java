package HTMLParser;

public class HTMLManager {
    private static final String ERROR_PATH = "/HTMLParser/error.html";
    public static String getPathToErr(){
        return Class.class.getResource("/HTMLParser/error.html").toExternalForm();
    }

    public static String getPathToHMTLTaskForParser(){
        return Class.class.getResource("/HTMLParser/task.html").getPath();
    }

    public static String getPathToHTMLTask(){
        return Class.class.getResource("/HTMLParser/task.html").toExternalForm();
    }

    public static String getPathtoStartPage(){
        return Class.class.getResource("/HTMLParser/start_page.html").toExternalForm();
    }
}
