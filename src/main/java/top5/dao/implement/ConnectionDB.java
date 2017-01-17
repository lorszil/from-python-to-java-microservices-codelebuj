package top5.dao.implement;

import top5.model.ReadJson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionDB {

    public ConnectionDB() {
    }
    private static final String DATABASE = String.format("jdbc:postgresql://%s/%s",
            ReadJson.readJsonFromFile().get("url"),
            ReadJson.readJsonFromFile().get("database"));
    private static final String DB_USER = String.format("%s", ReadJson.readJsonFromFile().get("user"));
    private static final String DB_PASSWORD = String.format("%s", ReadJson.readJsonFromFile().get("password"));

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);

    }

    /**
     *
     * @param query
     */

    public void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
