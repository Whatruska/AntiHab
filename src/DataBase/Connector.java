package DataBase;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Connector {
    private static Connection connection = null;

    public static Connection getInstantConnection() throws SQLException {
        if (connection == null){
            connection = getConnection();
        }
        return connection;
    }
    private static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("DataBase.database");

        String url = resource.getString("db.url");
        String table = resource.getString("db.name");
        String login = resource.getString("db.login");
        String pass = resource.getString("db.password");

        Connection connection = null;

        Driver driver = null;
        try {
            driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(
                url + table + "?serverTimezone=UTC",
                login,
                pass
        );

        return connection;
    }
}
