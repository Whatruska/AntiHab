package Core;

import java.util.ArrayList;

public class Task {

    private int number;
    private int score;
    private String url;
    private boolean isOcupied;

    public Task() {}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isOcupied() {
        return isOcupied;
    }

    public void setOcupied(boolean ocupied) {
        isOcupied = ocupied;
    }

}
