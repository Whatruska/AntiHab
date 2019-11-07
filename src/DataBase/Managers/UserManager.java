package DataBase.Managers;

import Core.Builders.UserBuilder;
import Core.Task;
import Core.User;
import DataBase.Executor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {

    private static final String SELECT_SQL = "SELECT * FROM `USERS` WHERE ";
    private static final String REGISTER_SQL = "INSERT INTO `USERS` (`LOGIN`, `PASSWORD`, `SURNAME`, `NAME`, `IS_ADMIN`, `BOTTOM_LIMIT`, `TOP_LIMIT`) VALUES ('?', '?', '?', '?', '?', '?', '?')";
    private static final String DELETE_SQL = "DELETE FROM `USERS` WHERE";

    public static User getUserByLogin(String login){
        try {
            ResultSet result = Executor.executeSelect(SELECT_SQL + "LOGIN= \"" + login + "\"");
            if (result != null){
                while (result.next()) {
                    return formUserFromSet(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new User();
    }

    public static ArrayList<String> getAllLogins(){
        return new ArrayList<>();
    }

    public static void registerNewUser(User user, String password){

        String sql = REGISTER_SQL;

        sql = addParamToSql(sql, user.getLogin());
        sql = addParamToSql(sql, password);

        sql = addParamToSql(sql, user.getSurname());
        sql = addParamToSql(sql, user.getName());

        sql = addParamToSql(sql, user.isAdmin() ? "1" : "0");
        sql = addParamToSql(sql, Integer.toString(user.getBottomLimit()));
        sql = addParamToSql(sql, Integer.toString(user.getTopLimit()));

        try {
            Executor.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String addParamToSql(String sql, String param){
        return sql.replaceFirst("[?]", param);
    }

    private static User formUserFromSet(ResultSet set) throws SQLException {
        String login = set.getString("LOGIN");
        String name = set.getString("NAME");
        String surname = set.getString("NAME");
        Boolean isAdmin = set.getBoolean("IS_ADMIN");
        Integer bottomLimit = set.getInt("BOTTOM_LIMIT");
        Integer topLimit = set.getInt("TOP_LIMIT");
        String stringTasks = set.getString("TASKS");
        ArrayList<Task> tasks;
        if (stringTasks == null || stringTasks.length() == 0){
            tasks = new ArrayList<>();
        } else {
            tasks = TaskManager.buildListOfTasksByStrings(stringTasks.split(" "));
        }

        return UserBuilder
                .login(login)
                .name(name)
                .surname(surname)
                .admin(isAdmin)
                .bottomLimit(bottomLimit)
                .topLimit(topLimit)
                .tasks(tasks)
                .build();
    }
}
