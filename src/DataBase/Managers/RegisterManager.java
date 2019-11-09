package DataBase.Managers;

import Core.Builders.TaskBuilder;
import Core.Task;
import DataBase.Executor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterManager extends Manager {
    private static final String REGISTER_SQL = "INSERT INTO `TASKS` (`NUMBER`, `NAME`, `DIFFICULTY`, `IS_OCUPIED`, `URL`, `OCUPIED_BY`) VALUES ('?', '?', '?', '?', '?', '?')";
    private static final String SELECT_SQL = " SELECT * FROM `TABLE 3`";

    public static void registerAllTask() throws SQLException {
        ResultSet resultSet = Executor.executeSelect(SELECT_SQL);
        int counter = -1;
        while (resultSet.next()){
            counter++;
            if (counter != 0){
                Task task = buildTask(resultSet);
                registerTask(task);
                System.out.println("Task " + counter + " registered");
            }
        }
    }

    private static void registerTask(Task task) throws SQLException {
            String sql = REGISTER_SQL;

            sql = addParamToSql(sql, "" + task.getNumber());
            sql = addParamToSql(sql, "" + task.getName());

            sql = addParamToSql(sql, "" + task.getDifficulty());

            sql = addParamToSql(sql, task.isOcupied() ? "1" : "0");

            sql = addParamToSql(sql, task.getUrl());

            sql = addParamToSql(sql, "" + task.getOcupiedBy());

            Executor.execute(sql);

    }

    private static Task buildTask(ResultSet set) throws SQLException {
        int number = Integer.parseInt(set.getString("ID"));
        String url = set.getString("URL");
        String name = set.getString("NAME");
        int difficulty = Integer.parseInt(set.getString("DIFFICULTY").replaceFirst("%", ""));

        Task task = TaskBuilder
                .number(number)
                .url(url)
                .name(name)
                .difficulty(difficulty)
                .build();
        task.setOcupied(false);
        task.setOcupiedBy(-1);
        return task;
    }
}
