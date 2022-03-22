package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.DatabaseConnexion;
import models.Client;

public class ListeClientService {
	private DatabaseConnexion app;
	
	public ListeClientService() {
		this.app = new DatabaseConnexion();
		new ClientService();
	}
	
	public DatabaseConnexion getApp() {
		return app;
	}
	
	public List<Client> getClients() {
		List<Client> listClients = new ArrayList<Client>();
		String query = "SELECT * from client";  
		try {
			  Connection conn = this.app.connect();
			  PreparedStatement preparedStmt = conn.prepareStatement(query);
		  	  ResultSet rs = preparedStmt.executeQuery();
			  
		      int i = 0;
		      while (rs.next()) {
		    	int id = rs.getInt("id");
		        String raisonSociale = rs.getString("raisonSociale");
		        String libelleClient = rs.getString("libelleClient");
		        String numeroTel = rs.getString("numeroTel");
		        String mail = rs.getString("mail");
		        String adresse = rs.getString("adresse");
		        String civilite = rs.getString("civilite");
		        String dateNaisssance = rs.getString("dateNaisssance");
		        int id_Conseiller = rs.getInt("id_Conseiller");
		        Client client = new Client(id, raisonSociale, libelleClient, numeroTel, mail, adresse, civilite, dateNaisssance, id_Conseiller);
		        listClients.add(client);
		      }
			      
		} catch (SQLException e) {
		      System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		}
		
		return listClients;
	}
}
