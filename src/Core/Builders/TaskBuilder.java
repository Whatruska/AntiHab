package Core.Builders;

import Core.Task;
import Core.TaskCase;

import java.util.ArrayList;

public class TaskBuilder {

    private static Task task = new Task();

    public static TaskBuilder number(int number){
        task.setNumber(number);
        return null;
    }

    public static TaskBuilder topic(String topic){
        task.setTopic(topic);
        return null;
    }

    public static TaskBuilder score(int score){
        task.setScore(score);
        return null;
    }

    public static TaskBuilder url(String url){
        task.setUrl(url);
        return null;
    }

    public static TaskBuilder description(String description){
        task.setDescription(description);
        return null;
    }

    public static TaskBuilder taskCases(ArrayList<TaskCase> cases){
        task.setTaskCases(cases);
        return null;
    }

    public static TaskBuilder occupied(boolean isOcupied){
        task.setOcupied(isOcupied);
        return null;
    }

    public static Task build(){
        return task;
    }
}
