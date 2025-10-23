package model;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/agencia_autos";
    private static final String USER = "root";
    private static final String PASSWORD = "Carro2020!";
 
    public static Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}