package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.DatabaseConnexion;

public class ListeClientService {
	private DatabaseConnexion app;
	private ClientService clientService;
	
	public ListeClientService() {
		this.app = new DatabaseConnexion();
		this.clientService = new ClientService();
	}
	
	public DatabaseConnexion getApp() {
		return app;
	}
	
	
	
	public void getClient() {
		String query = "SELECT * from client";
		ResultSet result = null;	      
		try {
			  Connection conn = this.app.connect();
			  PreparedStatement preparedStmt = conn.prepareStatement(query);
			  result = preparedStmt.executeQuery();
			  //table.setModel(DbUtils.resutSetToTableModel(result));
			      
			} catch (SQLException e) {
			      System.err.println("Got an exception!");
			      System.err.println(e.getMessage());
			}		
	}
}
