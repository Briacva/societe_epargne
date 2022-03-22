package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.DatabaseConnexion;

public class ClientService {
	private DatabaseConnexion app;
	
	public ClientService() {
		this.app = new DatabaseConnexion();
	}

	public DatabaseConnexion getApp() {
		return app;
	}

	public List<Object> getAll() {
		List<Object> list = new ArrayList<Object>();
		
		try{
			Connection conn = this.app.connect();
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM Client";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.isBeforeFirst()) {  // Le curseur est-il avant la première ligne ? Sinon pas de données
				while (rs.next()) {
					
					Object client = new Object()
					{ 
						int id = rs.getInt("id");
						String raisonSociale = rs.getString("raisonSociale");
						String libelleClient = rs.getString("libelleClient");
						String numeroTel = rs.getString("numeroTel");
						String adresse = rs.getString("adresse");
						String civilite = rs.getString("civilite");
						Date dateNaissance = rs.getDate("dateNaissance");
						int idConseiller = rs.getInt("id_Conseiller");
					};
					
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
}
