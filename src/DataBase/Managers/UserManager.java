package DataBase.Managers;

import Core.Builders.UserBuilder;
import Core.Task;
import Core.User;
import DataBase.Executor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager extends Manager {

    private static final String SELECT_SQL = "SELECT * FROM `USERS`";
    private static final String REGISTER_SQL = "INSERT INTO `USERS` (`LOGIN`, `PASSWORD`, `SURNAME`, `NAME`, `IS_ADMIN`, `BOTTOM_LIMIT`, `TOP_LIMIT`) VALUES ('?', '?', '?', '?', '?', '?', '?')";
    private static final String UPDATE_SQL = "UPDATE `USERS` SET LOGIN = '?', SURNAME = '?', NAME = '?', IS_ADMIN = '?', BOTTOM_LIMIT = '?', TOP_LIMIT = '?', TASKS = '?'";
    private static final String DELETE_SQL = "DELETE FROM `USERS` WHERE";

    public static User getUserByLogin(String login) throws SQLException {
        ResultSet result = Executor.executeSelect(SELECT_SQL + "WHERE LOGIN= \"" + login + "\"");
        if (result != null){
            while (result.next()) {
                return formUserFromSet(result);
            }
        }
        return new User();
    }

    public static ArrayList<String> getAllLogins() throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        ResultSet set = Executor.executeSelect(SELECT_SQL);
        while (set.next()){
            result.add(set.getString("LOGIN"));
        }
        return result;
    }

    public static String getPasswordFromDBByLogin(String login) throws SQLException {
        String pass = "";
        ResultSet set = Executor.executeSelect("SELECT `PASSWORD` FROM `USERS` WHERE LOGIN = \"" + login + "\"");
        while (set.next()){
            pass = set.getString("PASSWORD");
        }
        return pass;
    }

    public static void updatePassword(User user, String password) throws SQLException {
        Executor.execute("UPDATE `USERS` SET PASSWORD=\"" + password + "\"");
    }

    public static void registerNewUser(User user, String password) throws SQLException {

        String sql = REGISTER_SQL;

        sql = addParamToSql(sql, user.getLogin());
        sql = addParamToSql(sql, password);

        sql = addParamToSql(sql, user.getSurname());
        sql = addParamToSql(sql, user.getName());

        sql = addParamToSql(sql, user.isAdmin() ? "1" : "0");
        sql = addParamToSql(sql, Integer.toString(user.getBottomLimit()));
        sql = addParamToSql(sql, Integer.toString(user.getTopLimit()));

        Executor.execute(sql);
    }

    public static void updateUser(User user) throws SQLException {
        String sql = UPDATE_SQL;

        sql = addParamToSql(sql, user.getLogin());

        sql = addParamToSql(sql, user.getSurname());
        sql = addParamToSql(sql, user.getName());

        sql = addParamToSql(sql, user.isAdmin() ? "1" : "0");
        sql = addParamToSql(sql, Integer.toString(user.getBottomLimit()));
        sql = addParamToSql(sql, Integer.toString(user.getTopLimit()));

        sql = addParamToSql(sql, taskListToString(user.getTasks()));

        Executor.execute(sql);
    }

    public static int getIDByLogin(String login) throws SQLException {
        int id = 0;
        String sql = "SELECT `ID` FROM `USERS` WHERE LOGIN = '" + login + "'";
        ResultSet set = Executor.executeSelect(sql);
        while (set.next()){
            id = set.getInt("ID");
        }
        return id;
    }

    private static User formUserFromSet(ResultSet set) throws SQLException {
        String login = set.getString("LOGIN");
        String name = set.getString("NAME");
        String surname = set.getString("SURNAME");
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
