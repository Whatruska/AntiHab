package DataBase.Managers;

import Core.Task;

import java.util.ArrayList;

public class Manager {
    protected static String addParamToSql(String sql, String param){
        return sql.replaceFirst("'\\?'", "'" + param + "'");
    }
    protected static String taskListToString(ArrayList<Task> tasks){
        StringBuilder builder = new StringBuilder();
        for (Task task : tasks){
            builder.append(task.getNumber() + " ");
        }
        return builder.toString();
    }
}
