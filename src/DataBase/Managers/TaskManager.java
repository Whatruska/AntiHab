package DataBase.Managers;

import Core.Task;

import java.util.ArrayList;

public class TaskManager {
    private static final String SELECT_SQL = "SELECT * FROM `TASKS` WHERE ";

    public static ArrayList<Task> buildListOfTasksByStrings(String[] tasks){
        ArrayList<Task> result = new ArrayList<>();
        return result;
    }
}
