package Core;

public class Task {

    private int number;
    private int difficulty;
    private String url;
    private boolean isOcupied;
    private int ocupiedBy;
    private String name;

    public Task() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOcupiedBy() {
        return ocupiedBy;
    }

    public void setOcupiedBy(int ocupiedBy) {
        this.ocupiedBy = ocupiedBy;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
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

    @Override
    public String toString() {
        return getNumber() + " - " + getName();
    }
}
