package DataBase.Managers;

import Core.Builders.TaskBuilder;
import Core.Task;
import Core.User;
import DataBase.Executor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskManager extends Manager {
    private static final String SELECT_SQL = "SELECT * FROM `TASKS` ";
    private static final String REGISTER_SQL = "INSERT INTO `TASKS` (`NUMBER`, `DIFFICULTY`, `URL`, `IS_OCUPIED`) VALUES ('?', '?', '?', '?')";
    private static final String UPDATE_SQL = "UPDATE `TASKS` SET NUMBER = `?`, DIFFICULTY = `?`, URL = `?`, IS_OCUPIED = `?`, OCUPIED_BY = `?`";

    public static ArrayList<Task> buildListOfTasksByStrings(String[] tasks){
        ArrayList<Task> result = new ArrayList<>();
        for (String string : tasks){
            result.add(getTaskByNumber(Integer.parseInt(string)));
        }
        return result;
    }

    public static Task getTaskByNumber(int number){
        String sql = SELECT_SQL + "WHERE NUMBER = `" + number + "`";
        try {
            ResultSet set = Executor.executeSelect(sql);
            while (set.next()){
                return buildTaskFromSet(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Task();
    }

    public static void registerNewTask(Task task){
        String sql = REGISTER_SQL;

        sql = addParamToSql(sql, "" + task.getNumber());

        sql = addParamToSql(sql, "" + task.getDifficulty());
        sql = addParamToSql(sql, task.getUrl());

        sql = addParamToSql(sql, "0");

        try {
            Executor.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTask(Task task){
        String sql = UPDATE_SQL;

        sql = addParamToSql(sql, "" + task.getNumber());

        sql = addParamToSql(sql, "" + task.getDifficulty());
        sql = addParamToSql(sql, task.getUrl());

        sql = addParamToSql(sql, task.isOcupied() ? "1" : "0");
        sql = addParamToSql(sql, "" + task.getOcupiedBy());

        try {
            Executor.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int assignTaskOnUser(User user, Task task){
        if (!task.isOcupied()){
            user.addTask(task);
            task.setOcupied(true);
            UserManager.updateUser(user);
            updateTask(task);
            return 0;
        } else {
            return -1;
        }
    }

    private static Task buildTaskFromSet(ResultSet set) throws SQLException {
        Integer number = set.getInt("NUMBER");
        Integer difficulty = set.getInt("DIFFICULTY");
        String url = set.getString("URL");
        Boolean isOcupied = set.getBoolean("IS_OCUPIED");
        Integer ocupiedBy = set.getInt("OCUPIED_BY");

        return TaskBuilder
                .number(number)
                .difficulty(difficulty)
                .url(url)
                .ocupied(isOcupied)
                .ocupiedBy(ocupiedBy)
                .build();
    }
}
