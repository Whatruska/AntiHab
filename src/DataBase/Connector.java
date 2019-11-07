package DataBase;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Connector {
    private static Connection connection = getConnection();

    public static Connection getInstantConnection(){
        return connection;
    }
    private static Connection getConnection(){
        ResourceBundle resource = ResourceBundle.getBundle("DataBase.database");

        String url = resource.getString("db.url");
        String table = resource.getString("db.name");
        String login = resource.getString("db.login");
        String pass = resource.getString("db.password");

        Connection connection = null;

        try {
            Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(
                    url + table + "?serverTimezone=UTC",
                    login,
                    pass
            );
        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
