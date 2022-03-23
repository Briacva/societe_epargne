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
		
		//R�cup�ration de la liste des compte dans le model
		List<Compte> listComptes = new ArrayList<Compte>();
		
		//Requ�te selectionnant tout dans compte et ajoute la table client
		String query = "SELECT co.* FROM compte co INNER JOIN client cl ON co.id_Client = cl.id WHERE co.cloture = " + CompteStatut.ACTIF.getStatut();
		
		try {
			//connection a la base de donn�e via main/databaseConnection
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
	
	public boolean clotureCompte(int idCompte) {
		boolean isUpdated = false;
		
		String query = "UPDATE compte set cloture = ? WHERE id = ?";
		
        try {	      
		    // create the mysql insert preparedstatement
	        Connection conn = this.app.connect();
	        PreparedStatement preparedStmt = conn.prepareStatement(query);

	        preparedStmt.setBoolean (1, CompteStatut.CLOTURE.getStatut());
	        preparedStmt.setInt 	(2, idCompte);
	        
	
	        // check si la requête s'est correctement executée
	        isUpdated = preparedStmt.executeUpdate() == 1;
	        conn.close();
		      
		} catch (SQLException e) {
		    System.err.println("Got an exception!");
		    System.err.println(e.getMessage());
		}
		
		return isUpdated;
	}
}
