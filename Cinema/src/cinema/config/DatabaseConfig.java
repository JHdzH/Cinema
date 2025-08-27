/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:mariadb://localhost:3306/cinema";
    private static final String USER = "root"; 
    private static final String PASSWORD = "qwerty"; 
    
    public static Connection getConnection() throws SQLException {
        System.out.println("Intentando conectar a: " + URL);
        
        try {
            // Intenta cargar el driver
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver MariaDB cargado exitosamente");
            
            // Intenta establecer conexión
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a MariaDB establecida");
            return conn;
            
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: Driver MariaDB no encontrado");
            System.err.println("Solución: Descarga mariadb-java-client-3.3.3.jar");
            System.err.println("URL: https://mariadb.com/downloads/connectors/connectors-data-access/java-connector/");
            throw new SQLException("MariaDB Driver not found: " + e.getMessage(), e);
        } catch (SQLException e) {
            System.err.println("ERROR de SQL: " + e.getMessage());
            System.err.println("Verifica:");
            System.err.println("1. Que MariaDB esté ejecutándose");
            System.err.println("2. Que la base de datos 'cinema' exista");
            System.err.println("3. Usuario y contraseña correctos");
            throw e;
        }
    }
    
    // Método para probar conexión
    public static void testConnection() {
        try {
            Connection conn = getConnection();
            conn.close();
            System.out.println("Prueba de conexión: EXITOSA");
        } catch (SQLException e) {
            System.out.println("Prueba de conexión: FALLIDA");
        }
    }
}