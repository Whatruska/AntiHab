package HTMLParser;

import Core.Task;
import DataBase.Managers.TaskManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Parser {
    private static final String ACMP_URL = "https://acmp.ru/index.asp?main=task&id_task=";

    public static void main(String[] args) throws IOException {
        parseTaskFormNumber(754);
    }

    public static void parseTaskFormNumber(int number) throws IOException {
        parseTaskFromUrl(ACMP_URL + number);
    }

    private static void parseTaskFromUrl(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("td[colspan=3]");
        Element conditions = elements.last();

        Elements elems = conditions.getAllElements();

        Document resultDoc = Jsoup.parse(elems.first().html());

        resultDoc.selectFirst("head").append("<link rel=\"stylesheet\" href=\"taskStyle.css\">").append("<meta charset=\"UTF-8\">");

        writeDocumentToFile(resultDoc);
    }

    private static void writeDocumentToFile(Document document) throws IOException {
        File file = new File(HTMLManager.getPathToHMTLTaskForParser());
        PrintWriter out = new PrintWriter(file);
        out.append(document.toString());
        out.flush();
        out.close();
    }
}
