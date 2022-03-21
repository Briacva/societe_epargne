/**
 * 
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.Query;

import models.Client;

/**
 * @author Guillaume
 *
 */
public class DatabaseConnexion {
    private final static String url = "jdbc:mysql://localhost:3307/societe_epargne";
    private final static String user = "root";
    private final static String password = "";
    
    static Connection conn;
    static Statement myStat;
    static ResultSet myRs;
    static Query query;

    /**
     * Connect to the Mysql database
     */
    public static void connect() {
        
        try {
        	conn = DriverManager.getConnection(url, user, password);
        	myStat = conn.createStatement();
            myRs = myStat.executeQuery("SELECT * FROM client");
            System.out.println("Connected to the mysql server successfully.");
            
            while(myRs.next()) {
            	System.out.println(myRs.getString("adresse"));
//            	System.out.println(myRs.getString("Theme"));
            	
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public static void Update(Client client) {
    	try {
    		String query = "INSERT INTO client (`raisonSociale`,`libelleClient`,`numeroTel`,`mail`,`adresse`,`civilite`,`id_Conseiller`) VALUES ('"+client.getRaisonSocial()+"', '"+client.getLibelleClient()+"', '"+client.getTelephone()+"', '"+client.getMail()+"', '"+client.getAdresse()+"' , '"+client.getCivilite()+"', "+client.getId_Conseille()+")";
    		

    		myStat.executeUpdate(query);
    		
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    }
}
}
