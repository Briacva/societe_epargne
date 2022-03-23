/**
 * 
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author marvin
 *
 */
public class DatabaseConnexion {
    private final String url = "jdbc:mysql://localhost:3307/societe_epargne";
    private final String user = "root";
    private final String password = "";

    /**
     * Connect to the Mysql database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the mysql server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
