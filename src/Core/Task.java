package Core;

import java.util.ArrayList;

public class Task {

    private int number;
    private String topic;
    private int score;
    private String url;
    private String description;
    private ArrayList<TaskCase> taskCases = new ArrayList<>();
    private boolean isOcupied;

    public Task() {}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<TaskCase> getTaskCases() {
        return taskCases;
    }

    public void setTaskCases(ArrayList<TaskCase> taskCases) {
        this.taskCases = taskCases;
    }

    public boolean isOcupied() {
        return isOcupied;
    }

    public void setOcupied(boolean ocupied) {
        isOcupied = ocupied;
    }

    public void addTaskCase(TaskCase taskCase){
        taskCases.add(taskCase);
    }

}
