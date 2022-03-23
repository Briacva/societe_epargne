/**
 * 
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Client;

/**
 * @author Guillaume
 *
 */
public class DatabaseConnexion {

	private final static String url = "jdbc:mysql://localhost:3306/societe_epargne";
	private final static String user = "root";
	private final static String password = "password";

	/**
	 * Connect to the Mysql database
	 *
	 * @return a Connection object
	 */
	public Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	public static void Update(Client client) {
		try {
			String query = "INSERT INTO client (`raisonSociale`,`libelleClient`,`numeroTel`,`mail`,`adresse`,`civilite`,`id_Conseiller`) VALUES ('"
					+ client.getRaisonSociale() + "', '" + client.getLibelleClient() + "', '" + client.getNumeroTel()
					+ "', '" + client.getMail() + "', '" + client.getAdresse() + "' , '" + client.getCivilite() + "', "
					+ client.getId_Conseiller() + ")";
			
			DatabaseConnexion app = new DatabaseConnexion();
			Connection conn = app.connect();

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
