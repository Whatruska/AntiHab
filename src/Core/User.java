package Core;

import java.util.ArrayList;

public class User {

    private String login;
    private int bottomLimit;
    private int topLimit;
    private ArrayList<Task> tasks = new ArrayList<>();
    private boolean isAdmin = false;

    public User() {}

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getBottomLimit() {
        return bottomLimit;
    }

    public void setBottomLimit(int bottomLimit) {
        this.bottomLimit = bottomLimit;
    }

    public int getTopLimit() {
        return topLimit;
    }

    public void setTopLimit(int topLimit) {
        this.topLimit = topLimit;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task){
        tasks.add(task);
    }
}
