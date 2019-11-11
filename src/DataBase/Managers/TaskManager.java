package DataBase.Managers;

import Core.Builders.TaskBuilder;
import Core.Task;
import Core.User;
import DataBase.Executor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeSet;

public class TaskManager extends Manager {
    private static final String SELECT_SQL = "SELECT * FROM `TASKS` ";
    private static final String UPDATE_SQL = "UPDATE `TASKS` SET NUMBER = '?', NAME = '?', DIFFICULTY = '?', IS_OCUPIED = '?', OCUPIED_BY = '?', URL = '?'";

    public static ArrayList<Task> buildListOfTasksByStrings(String[] tasks) throws SQLException {
        ArrayList<Task> result = new ArrayList<>();
        for (String string : tasks){
            result.add(getTaskByNumber(Integer.parseInt(string)));
        }
        return result;
    }

    private static ArrayList<Task> getAvailableTasksByDifficulty(int difficulty) throws SQLException {
        ArrayList<Task> taskList = getTaskListByDifficulty(difficulty);
        for (Task task : taskList){
            if (task.isOcupied()){
                taskList.remove(task);
            }
        }
        return taskList;
    }

    private static ArrayList<Task> getTaskListByDifficulty(int difficulty) throws SQLException {
        ArrayList<Task> result = new ArrayList<>();
        String sql = "SELECT * FROM `TASKS` WHERE DIFFICULTY = '" + difficulty + "'";
        ResultSet set = Executor.executeSelect(sql);
        while (set.next()){
            Task task = buildTaskFromSet(set);
            result.add(task);
        }
        return result;
    }

    private static TreeSet<Integer> getAvaliableDifficultySet() throws SQLException {
        TreeSet<Integer> result = new TreeSet<>();
        String sql = "SELECT `DIFFICULTY`, `IS_OCUPIED` FROM `TASKS`";
        ResultSet set = Executor.executeSelect(sql);
        while (set.next()){
            boolean isOcupied = set.getBoolean("IS_OCUPIED");
            int difficulty = set.getInt("DIFFICULTY");
            if (!isOcupied){
                result.add(difficulty);
            }
        }
        return result;
    }

    public static Task getTaskByNumber(int number) throws SQLException {
        String sql = SELECT_SQL + "WHERE NUMBER = '" + number + "'";
        ResultSet set = Executor.executeSelect(sql);
        while (set.next()){
            return buildTaskFromSet(set);
        }
        return new Task();
    }

    public static Task getRandomTask(User user) throws SQLException {
        int randomDifficulty = getRandomAvailableDifficulty(user);
        if (randomDifficulty > -1) {
            ArrayList<Task> tasks = getAvailableTasksByDifficulty(randomDifficulty);
            if (tasks.size() > 0) {
                return tasks.get(0);
            } else {
                return new Task();
            }
        } else {
            return new Task();
        }
    }

    private static int getRandomAvailableDifficulty(User user) throws SQLException {
        int bottomLimit = user.getBottomLimit();
        int topLimit = user.getTopLimit();
        int d = topLimit - bottomLimit;
        TreeSet<Integer> availableDifficulty = getAvaliableDifficultySet();
        int randomDifficulty = (int)(bottomLimit + (Math.random() * d));
        if (availableDifficulty.size() == 0){
            return -1;
        }

        int counter = 0;
        while (!availableDifficulty.contains(randomDifficulty)){
            randomDifficulty = (int)(bottomLimit + (Math.random() * d));
            counter++;
            if (counter > 2000){
                return -1;
            }
        }

        return randomDifficulty;
    }

    public static void updateTask(Task task) throws SQLException {
        String sql = UPDATE_SQL;

        sql = addParamToSql(sql, "" + task.getNumber());
        sql = addParamToSql(sql, "" + task.getName());

        sql = addParamToSql(sql, "" + task.getDifficulty());

        sql = addParamToSql(sql, task.isOcupied() ? "1" : "0");

        sql = addParamToSql(sql, "" + task.getOcupiedBy());

        sql = addParamToSql(sql, task.getUrl());

        sql += " WHERE NUMBER = \"" + task.getNumber() + "\"";

        Executor.execute(sql);
    }

    public static int assignTaskOnUser(User user, Task task) throws SQLException {
        if (!task.isOcupied()){
            user.addTask(task);
            task.setOcupied(true);
            task.setOcupiedBy(UserManager.getIDByLogin(user.getLogin()));
            UserManager.updateUser(user);
            updateTask(task);
            return 0;
        } else {
            return -1;
        }
    }

    public static int reassignTaskFromUser(User user, Task task) throws SQLException {
            if (task.isOcupied()) {
                user.removeTask(task);
                task.setOcupied(false);
                UserManager.updateUser(user);
                updateTask(task);
                return 0;
            }
            return -1;
    }

    private static Task buildTaskFromSet(ResultSet set) throws SQLException {
        Integer number = set.getInt("NUMBER");
        String name = set.getString("NAME");
        Integer difficulty = set.getInt("DIFFICULTY");
        String url = set.getString("URL");
        Boolean isOcupied = set.getBoolean("IS_OCUPIED");
        Integer ocupiedBy = set.getInt("OCUPIED_BY");

        return TaskBuilder
                .number(number)
                .name(name)
                .difficulty(difficulty)
                .url(url)
                .ocupied(isOcupied)
                .ocupiedBy(ocupiedBy)
                .build();
    }
}
