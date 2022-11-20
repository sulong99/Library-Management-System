package Data;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Klasa odpowiadająca za połączenie z bazą danych.
 */
public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://freedb.tech:3306/freedbtech_Biblioteka?useUnicode=yes&characterEncoding=UTF-8";
    public static final String USER = "freedbtech_Administrator";
    public static final String PASS = "admin";

    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}

