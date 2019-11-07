package Core;

import java.util.ArrayList;

public class User {

    private String name;
    private String surname;
    private String login;
    private int bottomLimit;
    private int topLimit;
    private ArrayList<Task> tasks = new ArrayList<>();
    private boolean isAdmin = false;

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Login : " + this.login + "\n");
        builder.append("Name : " + this.name + "\n");
        builder.append("Surname : " + this.surname + "\n");
        builder.append("Limits : [" + this.bottomLimit + " ; " + this.topLimit + "]\n");
        builder.append("Tasks : ");
        if (tasks.size() > 0){
            for (Task task : this.tasks){
                builder.append(task.getNumber() + " ");
            }
        } else {
            builder.append("-");
        }
        if (this.isAdmin){
            builder.append("\n----ADMIN----\n");
        }
        return builder.toString();
    }
}
