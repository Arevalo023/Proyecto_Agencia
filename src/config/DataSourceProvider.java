package config;
 
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;
 
/**

* Proveedor de conexiones simple (Singleton) usando DriverManager.

* Ajusta las constantes URL/USER/PASS a tu entorno.

*/

public final class DataSourceProvider {
 
    private static final DataSourceProvider INSTANCE = new DataSourceProvider();
 
    // === Ajusta estos valores a tu entorno ===

    private static final String URL  = "jdbc:mysql://localhost:3306/agencia_autos?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";

    private static final String PASS = "!ClubameItzel18";

    // =========================================
 
    private DataSourceProvider() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8

        } catch (ClassNotFoundException e) {

            throw new IllegalStateException("No se encontró el driver de MySQL (mysql-connector-j).", e);

        }

    }
 
    public static DataSourceProvider getInstance() {

        return INSTANCE;

    }
 
    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASS);

    }
 
    // Utilidad para cerrar en cascada sin romper

    public static void closeQuietly(AutoCloseable... closables) {

        for (AutoCloseable c : closables) {

            if (c != null) {

                try { c.close(); } catch (Exception ignore) {}

            }

        }

    }

}

 