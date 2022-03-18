package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	public List<Object> getAll() {
		List<Object> list = new ArrayList<Object>();
		
		try{
			Connection conn = this.app.connect();
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM Client";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.isBeforeFirst()) {  // Le curseur est-il avant la première ligne ? Sinon pas de données
				while (rs.next()) {
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();
					
					Object client = new Object()
					{ 
						int id = 0;
						String libelleClient = "Firstname";
					};
					
					for (int i = 1; i <= columnsNumber; i++) {
						if (i > 1) System.out.print(",  ");
						String columnValue = rs.getString(i);
						System.out.print(columnValue + " " + rsmd.getColumnName(i));
					}
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
