package usjt.atividade.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.util.Objects.isNull;

public class MySQLConnection {
    private static Connection connection;

    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL = dotenv.get("MYSQL_URL");
    private static final String USER = dotenv.get("MYSQL_USER");
    private static final String PASSWORD = dotenv.get("MYSQL_PASSWORD");

    public static Connection getInstance() {
        try {
            if (isNull(connection) || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        }catch (SQLException e) {
            throw new RuntimeException("error connecting to database", e);
        }
        return connection;
    }
}
