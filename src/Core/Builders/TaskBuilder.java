package Core.Builders;

import Core.Task;

public class TaskBuilder {

    private static Task task = new Task();

    public static TaskBuilder number(int number){
        task.setNumber(number);
        return null;
    }

    public static TaskBuilder difficulty(int score){
        task.setDifficulty(score);
        return null;
    }

    public static TaskBuilder url(String url){
        task.setUrl(url);
        return null;
    }

    public static TaskBuilder ocupiedBy(int by){
        task.setOcupiedBy(by);
        return null;
    }

    public static TaskBuilder ocupied(boolean isOcupied){
        task.setOcupied(isOcupied);
        return null;
    }

    public static Task build(){
        return task;
    }
}
