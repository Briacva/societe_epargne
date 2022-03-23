package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.DatabaseConnexion;
import models.Client;

public class ClientService {
	private DatabaseConnexion app;
	
	public ClientService() {
		this.app = new DatabaseConnexion();
	}

	public DatabaseConnexion getApp() {
		return app;
	}

	public List<Client> getAll() {
		List<Client> list = new ArrayList<Client>();
		
		try{
			Connection conn = this.app.connect();
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM client";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.isBeforeFirst()) {  // Le curseur est-il avant la première ligne ? Sinon pas de données
				while (rs.next()) {
					
					Client client = new Client
					(
		                rs.getString("raisonSociale"),
		                rs.getString("libelleClient"),
		                rs.getString("numeroTel"),
		                rs.getString("mail"),
		                rs.getString("adresse"),
		                rs.getString("civilite"),
		                rs.getDate("dateNaissance"),
		                rs.getInt("id_Conseiller")
					);
					
					client.setId(rs.getInt("id"));
					list.add(client);
					
				}
			}else {
				System.out.println("\nAucune donnée n'a été trouvé.");
			}
			
			rs.close();
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		
		return list;
	}

	public Client getClientById(int idClient) {
        // TODO Auto-generated method stub
        Client client = null;
        try {
            String query = "SELECT * FROM client WHERE id =" + idClient;
            Connection conn = this.app.connect();
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String raisonSocial = rs.getString("raisonSociale");
                String libelleClient = rs.getString("libelleClient");
                String numeroTel = rs.getString("numeroTel");
                String mail = rs.getString("mail");
                String adresse = rs.getString("adresse");
                String civilite = rs.getString("civilite");
                Date dateDeNaissance = rs.getDate("dateNaissance");
                int idConseille = rs.getInt("id_Conseiller");
                client = new Client(raisonSocial, libelleClient, numeroTel, mail, adresse, civilite, dateDeNaissance, idConseille);
                client.setId(id);
                rs.close();
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return client;
    }
}
