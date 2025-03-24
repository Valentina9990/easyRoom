package com.easyRoom.dataAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection conn;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver de PostgreSQL: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:postgresql://localhost:5432/habitaciones";
                String user = "postgres";
                String password = "1234";
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Conexión establecida correctamente.");
            } catch (SQLException exception) {
                System.out.println("Error de conexión: " + exception.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
