package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author davu
 */
public class DatabaseConnection {
    private static Connection conn;

    public DatabaseConnection() {
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:postgresql://localhost:5432/habitaciones";
                String user = "postgres";
                String password = "1234";
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException exception) {
                System.out.println("Error de conexión: " + exception.getMessage());
            }
        }

        return conn;
    }
}
