package services;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import interfaces.OuvrirCompteForm;
import main.DatabaseConnexion;
import models.Client;
import models.Compte;
import models.CompteCourant;
import models.CompteEpargne;
import models.CompteStatut;
import models.TypeCompte;

public class CompteService {
	
	private DatabaseConnexion app;
	private ClientService clientService;
	
	public CompteService() {
		this.app = new DatabaseConnexion();
		this.clientService = new ClientService();
	}
	
	public DatabaseConnexion getApp() {
		return app;
	}

	public void generateNumCompte(JTextField input) {
		BigDecimal numCompte;

		numCompte = new BigDecimal(100000 + (Math.random() * (999999 - 100000)));
		input.setText(numCompte.setScale(0, RoundingMode.FLOOR).toString());
	}
	
	/**
	 * 
	 * @param OuvrirCompteForm
	 */
	public void fieldReinitialization(OuvrirCompteForm form) {
		form.getTextFieldSoldeInitial().setText(null);
		form.getTextFieldSoldeMinimum().setText(null);
		form.getTextFieldFraisDeTransfert().setText(null);
		form.getTextFieldTauxInteret().setText(null);
		form.getTextFieldPlafond().setText(null);
	}
	
	/**
	 * Renvoie un tableau associatif d'erreurs
	 * @param form
	 * @return
	 */
	public Hashtable<String, List<String>> checkFields(OuvrirCompteForm form) {
		
        // initialisation du tableau associatif d'erreur
		Hashtable<String, List<String>> fieldsInError = new Hashtable<String, List<String>>();
		
		// initialisation d'une liste contenant les libellés des champs non saisis
        List<String> emptyFields = new ArrayList<String>();
        
        // initialisation d'une liste contenant les libellés des champs mal saisis (format numérique)
        List<String> badNumericFields = new ArrayList<String>();
        
        // initialisation d'un tableau associatif contenant les libellés et la valeur des champs du formulaire
        Hashtable<String, String> listFields = new Hashtable<String, String>();
        listFields.put("client", form.getComboBoxClients().getSelectedItem().toString());
        listFields.put("solde initial", form.getTextFieldSoldeInitial().getText());
        listFields.put("solde minimum", form.getTextFieldSoldeMinimum().getText());
        listFields.put("frais de transfert", form.getTextFieldFraisDeTransfert().getText());
        listFields.put("taux d'intéret", form.getTextFieldTauxInteret().getText());
        listFields.put("plafond", form.getTextFieldPlafond().getText());
        
        // récupérations des clés du tableau associatif
        Set<String> listFieldsKeys = listFields.keySet();
        
        // pour chaque champ du formulaire
        for (String key : listFieldsKeys) {
        	// le champ est-il vide ?
            if(listFields.get(key).isEmpty()) {
            	
            	// si création d'un compte courant, ajout du champ concerné dans la liste appropriée
            	if(form.getRdbtnCompteCourant().isSelected() && (key.equals("solde minimum") || key.equals("frais de transfert"))) {
                	emptyFields.add(key);
            	}else if(form.getRdbtnCompteEpargne().isSelected() && (key.equals("taux d'intéret") || key.equals("plafond"))) {
            		// si création d'un compte épargne, ajout du champs concerné dans la liste appropriée
            		emptyFields.add(key);
            	}else if(key.equals("solde initial") || key.equals("client")){
            		// ajout du champ dans la liste appropriée
            		emptyFields.add(key);
            	}
            // Sinon, est-il bien au format numérique ?	
            }else {
            	if(!this.patternMatches(listFields.get(key), "[+-]?([0-9]*[.])?[0-9]+")) {
            		//Sinon ajout dans la liste appropriée
            		badNumericFields.add(key);
            	}
            }
        }
        
        //Ajout des deux listes dans le tableau associatif global des erreurs
        fieldsInError.put("emptyFields", emptyFields);
        fieldsInError.put("badNumericFields", badNumericFields);
        
        return fieldsInError;
        
	}
	
	/**
	 * Fonction permettant de tester la regex du champ passée en paramètre
	 * @param field
	 * @param regexPattern
	 * @return
	 */
	public boolean patternMatches(String field, String regexPattern) {
        return Pattern.compile(regexPattern)
          .matcher((CharSequence) field)
          .matches();
    }

	public boolean createBankAccount(OuvrirCompteForm form) {
		// TODO Auto-generated method stub

		boolean isCreatedCompte = false, isCreatedCompteCourant = false, isCreatedCompteEpargne = false;
		
		int numCompte = Integer.parseInt(form.getTextFieldNumCompte().getText());
		float solde = Float.parseFloat(form.getTextFieldSoldeInitial().getText());
		float soldeInitial = solde;
		boolean cloture = CompteStatut.ACTIF.getStatut();
		boolean typeCompte = form.getRdbtnCompteCourant().isSelected() ? TypeCompte.COURANT.getType() : TypeCompte.EPARGNE.getType();
		int idClient = form.getListClientKey(form.getComboBoxClients().getSelectedItem().toString());
		
		Compte compte = new Compte(numCompte, solde, soldeInitial, cloture, typeCompte, idClient);
		isCreatedCompte = addCompte(compte);
		
		if(form.getRdbtnCompteCourant().isSelected() && isCreatedCompte) {
			
			float soldeMinimum = Float.parseFloat(form.getTextFieldSoldeMinimum().getText());
			float fraisTransfert = Float.parseFloat(form.getTextFieldFraisDeTransfert().getText());
			
			CompteCourant compteCourant = new CompteCourant(numCompte, solde, soldeInitial, cloture, typeCompte, idClient, soldeMinimum, fraisTransfert);
			isCreatedCompteCourant = addCompteCourant(compteCourant);
		}else {
			if(isCreatedCompte) {
				
				float plafond = Float.parseFloat(form.getTextFieldPlafond().getText());
				float tauxInteret = Float.parseFloat(form.getTextFieldTauxInteret().getText());
				
				CompteEpargne compteEpargne = new CompteEpargne(numCompte, solde, soldeInitial, cloture, typeCompte, idClient, plafond, tauxInteret);
				isCreatedCompteEpargne = addCompteEpargne(compteEpargne);
			}
		}
		
		return (isCreatedCompte && isCreatedCompteCourant) || (isCreatedCompte && isCreatedCompteEpargne);
	}
	
	public boolean addCompte(Compte compte) {
		boolean isCreated = false;
		// the mysql insert statement
		String query = "INSERT INTO Compte(numeroCompte, solde, soldeInitial, cloture, typeCompte, id_Client)"
		+ " VALUES (?, ?, ?, ?, ?, ?)";
	      
		try {
	      // create the mysql insert preparedstatement
		  Connection conn = this.app.connect();
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
		  
		  preparedStmt.setInt 		(1, compte.getNumCompte());
		  preparedStmt.setFloat 	(2, compte.getSolde());
		  preparedStmt.setFloat   	(3, compte.getSoldeInitial());
		  preparedStmt.setBoolean	(4, compte.getCloture());
		  preparedStmt.setBoolean	(5, compte.getTypeCompte());
		  preparedStmt.setInt		(6, compte.getIdClient());
		
		  // execute the preparedstatement
		  isCreated = preparedStmt.executeUpdate() == 1;
		  conn.close();
		      
		} catch (SQLException e) {
		      System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		}

        System.out.println(isCreated + " test");
		return isCreated;
	}
	
	public boolean addCompteCourant(CompteCourant compte) {
		boolean isCreated = false;
		
		int idCompte = getLastCompte(compte.getNumCompte());
		compte.setId(idCompte);
		
		String query = "INSERT INTO CompteCourant(id, soldeMinimum, fraisTransfert)"
		        + " VALUES (?, ?, ?)";
		try {	      
    	  // create the mysql insert preparedstatement
	      Connection conn = this.app.connect();
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      
	      preparedStmt.setInt 		(1, compte.getId());
	      preparedStmt.setFloat 	(2, compte.getSoldeMinimum());
	      preparedStmt.setFloat   	(3, compte.getFraisTransfert());

	      // execute the preparedstatement
	      isCreated = preparedStmt.executeUpdate() == 1;
	      
	      conn.close();
        }catch(SQLException e) {
        	System.err.println("Got an exception!");
        	System.err.println(e.getMessage());
        }
		      
      return isCreated;
	}
	
	public boolean addCompteEpargne(CompteEpargne compte) {
		boolean isCreated = false;
		
		int idCompte = getLastCompte(compte.getNumCompte());
		compte.setId(idCompte);
		
		String query = "INSERT INTO CompteEpargne(id, plafond, tauxInteret)"
		        + " VALUES (?, ?, ?)";
		      
        try {	      
		    // create the mysql insert preparedstatement
	        Connection conn = this.app.connect();
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	      
	        preparedStmt.setInt 		(1, compte.getId());
	        preparedStmt.setFloat 	(2, compte.getPlafond());
	        preparedStmt.setFloat   	(3, compte.getTauxInteret());
	
	        // execute the preparedstatement CHECK SI LA LIGNE A BIEN ETE CREE
	        isCreated = preparedStmt.executeUpdate() == 1;
	        conn.close();
		      
		} catch (SQLException e) {
		    System.err.println("Got an exception!");
		    System.err.println(e.getMessage());
		}
        
        return isCreated;
	}
	
	public int getLastCompte(int numCompte) {
		Integer id = null;
		
		try {
			String query = "SELECT MAX(id) FROM Compte WHERE numeroCompte = " + numCompte;
			Connection conn = this.app.connect();
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();
			if (rs.next())
	            id = rs.getInt(1);
		
	        rs.close();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
	public boolean fillListClients(OuvrirCompteForm form) {
		boolean error = false;
		List<Object> clients = clientService.getAll();
		
		try {
			for(Object client: clients) {
				int id = Integer.parseInt(client.getClass().getDeclaredField("id").get(client).toString());
				String libelleClient = client.getClass().getDeclaredField("libelleClient").get(client) == null ? "" : client.getClass().getDeclaredField("libelleClient").get(client).toString();
				String raisonSociale = client.getClass().getDeclaredField("raisonSociale").get(client) == null ? "" : client.getClass().getDeclaredField("raisonSociale").get(client).toString();
				String telephone = client.getClass().getDeclaredField("numeroTel").get(client).toString();
				String adresse = client.getClass().getDeclaredField("adresse").get(client).toString();
				
				form.getListClients().put(id, libelleClient.isEmpty() || libelleClient.isBlank() ? raisonSociale + " - " + telephone + " - " + adresse : libelleClient + " - " + telephone + " - " + adresse);
				form.getComboBoxClients().addItem(libelleClient.isEmpty() || libelleClient.isBlank() ? raisonSociale + " - " + telephone + " - " + adresse : libelleClient + " - " + telephone + " - " + adresse);
			}
		}catch(SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			error = true;
		}
		
		return error;
	}
}
