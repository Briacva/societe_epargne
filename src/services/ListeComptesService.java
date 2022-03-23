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
import models.Compte;
import models.CompteStatut;

public class ListeComptesService {
	private DatabaseConnexion app;
	
	public ListeComptesService() {
		this.app = new DatabaseConnexion();
		new ClientService();
	}
	
	public DatabaseConnexion getApp() {
		return app;
	}
	
	public List<Compte> getComptes() {
		List<Compte> listComptes = new ArrayList<Compte>();
		
		String query = "SELECT co.* FROM compte co INNER JOIN client cl ON co.id_Client = cl.id WHERE co.cloture = " + CompteStatut.ACTIF.getStatut();
		
		try {
			  Connection conn = this.app.connect();
			  PreparedStatement preparedStmt = conn.prepareStatement(query);
		  	  ResultSet rs = preparedStmt.executeQuery();
			  
		      while (rs.next()) {
		    	int id = rs.getInt("id");
		    	int numeroCompte = rs.getInt("numeroCompte");
		        float solde = rs.getFloat("solde");
		        float soldeInitial = rs.getFloat("soldeInitial");
		        boolean cloture = rs.getBoolean("cloture");
		        boolean typeCompte = rs.getBoolean("typeCompte");
		        int id_Client = rs.getInt("id_Client");
		        Compte compte = new Compte(numeroCompte, solde, soldeInitial, cloture, typeCompte, id_Client);
		        compte.setId(id);
		        listComptes.add(compte);
		      }
			      
		} catch (SQLException e) {
		      System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		}
		
		return listComptes;
	}
}
