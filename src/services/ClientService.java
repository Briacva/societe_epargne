package services;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import interfaces.CreationClientForm;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

	public static void fieldReinitialization(CreationClientForm form) {
		form.getSocialReason().setText(null);
		form.getNameText().setText(null);
		form.getFirstnameText().setText(null);
		form.getEmailText().setText(null);
		form.getPhoneText().setText(null);
		form.getCiviliteText().setSelectedIndex(0);
		form.getAdressText().setText(null);
	}
	
	public static boolean patternMatches(String emailText, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher((CharSequence) emailText)
	      .matches();
	}
	
	public static List<String> requireText(CreationClientForm form) {

        // create a new ArrayList
		List<String> fieldsInError = new ArrayList<String>();
		
		Hashtable<String,String> listFields = new Hashtable<String,String>();
		listFields.put("raison sociale", form.getSocialReason().getText());
		listFields.put("prénom", form.getNameText().getText());
		listFields.put("nom", form.getFirstnameText().getText());
		listFields.put("téléphone", form.getPhoneText().getText());
		listFields.put("email", form.getEmailText().getText());
		listFields.put("adresse", form.getAdressText().getText());
		
		Set<String> listFieldsKeys = listFields.keySet();
		
        for (String key : listFieldsKeys) {
        	if(listFields.get(key).isEmpty()) {
        		fieldsInError.add(key);
        	}
        }
        
        if(listFields.get("raison sociale").isEmpty() && (!listFields.get("nom").isEmpty() && !listFields.get("prénom").isEmpty())) {
        	fieldsInError.remove("raison sociale");
        	fieldsInError.remove("nom");
        	fieldsInError.remove("prénom");
        }else if(!listFields.get("raison sociale").isEmpty() && (listFields.get("nom").isEmpty() && listFields.get("prénom").isEmpty())) {
        	fieldsInError.remove("raison sociale");
        	fieldsInError.remove("nom");
        	fieldsInError.remove("prénom");
        }
        
		return fieldsInError;
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


