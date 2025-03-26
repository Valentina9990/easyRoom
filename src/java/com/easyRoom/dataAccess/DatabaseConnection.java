package com.easyRoom.dataAccess;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DatabaseConnection {
    private static DataSource dataSource;

    static {
        try {
            PoolProperties poolProps = new PoolProperties();
            poolProps.setUrl("jdbc:postgresql://localhost:5432/habitaciones");
            poolProps.setUsername("postgres");
            poolProps.setPassword("1234");
            
            poolProps.setDriverClassName("org.postgresql.Driver");
            poolProps.setInitialSize(5);         
            poolProps.setMaxActive(20);          
            poolProps.setMaxIdle(10);            
            poolProps.setMinIdle(5);             
            poolProps.setTestOnBorrow(true);      
            poolProps.setValidationQuery("SELECT 1"); 
            poolProps.setValidationInterval(30000);   
            poolProps.setRemoveAbandoned(true);  
            poolProps.setRemoveAbandonedTimeout(60); 
            poolProps.setLogAbandoned(true);     
            poolProps.setMaxWait(10000);       
            
            dataSource = new DataSource();
            dataSource.setPoolProperties(poolProps);
            
            System.out.println("Pool de conexiones PostgreSQL inicializado correctamente");
        } catch (Exception e) {
            System.err.println("Error al inicializar el pool de conexiones: " + e.getMessage());
            throw new RuntimeException("Error en la configuración de la base de datos", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = dataSource.getConnection();
        
        if (conn == null || conn.isClosed() || !conn.isValid(2)) {
            throw new SQLException("No se pudo obtener una conexión válida");
        }
        
        return conn;
    }

    public static void closePool() {
        if (dataSource != null) {
            dataSource.close();
            System.out.println("Pool de conexiones cerrado correctamente");
        }
    }
}
