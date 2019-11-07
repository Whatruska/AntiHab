package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    public static ResultSet executeSelect(String sql) throws SQLException {
        Connection connection = Connector.getInstantConnection();

        Statement statement = connection.createStatement();

        return statement.executeQuery(sql);
    }

    public static int execute(String sql) throws SQLException {
        Connection connection = Connector.getInstantConnection();

        Statement statement = connection.createStatement();

        return statement.executeUpdate(sql);
    }
}
