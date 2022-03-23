package services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import interfaces.AccountDepositOrWithdraw;
import interfaces.OuvrirCompteForm;
import interfaces.TransfererForm;
import main.DatabaseConnexion;
import models.Client;
import models.Compte;
import models.CompteCourant;
import models.CompteEpargne;
import models.CompteStatut;
import models.Transfert;
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
	public void fieldReinitialization(OuvrirCompteForm formNewAccount, TransfererForm formTransfert) {
		if(formNewAccount != null) {
			formNewAccount.getTextFieldSoldeInitial().setText(null);
			formNewAccount.getTextFieldSoldeMinimum().setText(null);
			formNewAccount.getTextFieldFraisDeTransfert().setText(null);
			formNewAccount.getTextFieldTauxInteret().setText(null);
			formNewAccount.getTextFieldPlafond().setText(null);
		}else {
			formTransfert.getComboBoxCompteDestinataire().setSelectedIndex(0);
			formTransfert.getComboBoxCompteSource().setSelectedIndex(0);
			formTransfert.getTextFieldMontant().setText(null);
		}
	}
	
	/**
	 * Renvoie un tableau associatif d'erreurs
	 * @param form
	 * @return
	 */
	public Hashtable<String, List<String>> checkFields(OuvrirCompteForm formNewAccount, TransfererForm formTransfert) {
		
        // initialisation du tableau associatif d'erreur
		Hashtable<String, List<String>> fieldsInError = new Hashtable<String, List<String>>();
		
		// initialisation d'une liste contenant les libellés des champs non saisis
        List<String> emptyFields = new ArrayList<String>();
        
        // initialisation d'une liste contenant les libellés des champs mal saisis (format numérique)
        List<String> badNumericFields = new ArrayList<String>();
        
        // initialisation d'un tableau associatif contenant les libellés et la valeur des champs du formulaire
        Hashtable<String, String> listFields = new Hashtable<String, String>();
        if(formNewAccount != null) {
	        listFields.put("client", formNewAccount.getComboBoxClients().getSelectedItem().toString());
	        listFields.put("solde initial", formNewAccount.getTextFieldSoldeInitial().getText());
	        listFields.put("solde minimum", formNewAccount.getTextFieldSoldeMinimum().getText());
	        listFields.put("frais de transfert", formNewAccount.getTextFieldFraisDeTransfert().getText());
	        listFields.put("taux d'intéret", formNewAccount.getTextFieldTauxInteret().getText());
	        listFields.put("plafond", formNewAccount.getTextFieldPlafond().getText());
        }else {
        	listFields.put("compte source", formTransfert.getComboBoxCompteSource().getSelectedItem().toString());
        	listFields.put("compte destinataire", formTransfert.getComboBoxCompteDestinataire().getSelectedItem().toString());
        	listFields.put("montant", formTransfert.getTextFieldMontant().getText());
        }
        
        // récupérations des clés du tableau associatif
        Set<String> listFieldsKeys = listFields.keySet();
        
        // pour chaque champ du formulaire
        for (String key : listFieldsKeys) {
        	// le champ est-il vide ?
            if(listFields.get(key).isEmpty()) {
            	
            	if(formNewAccount != null) {
	            	// si création d'un compte courant, ajout du champ concerné dans la liste appropriée
	            	if(formNewAccount.getRdbtnCompteCourant().isSelected() && (key.equals("solde minimum") || key.equals("frais de transfert"))) {
	                	emptyFields.add(key);
	            	}else if(formNewAccount.getRdbtnCompteEpargne().isSelected() && (key.equals("taux d'intéret") || key.equals("plafond"))) {
	            		// si création d'un compte épargne, ajout du champs concerné dans la liste appropriée
	            		emptyFields.add(key);
	            	}else if(key.equals("solde initial") || key.equals("client")){
	            		// ajout du champ dans la liste appropriée
	            		emptyFields.add(key);
	            	}
            	}else {
            		emptyFields.add(key);
            	}
            // Sinon, est-il bien au format numérique ?	
            }else {
            	if((!key.equals("client") && !key.equals("compte source") && !key.equals("compte destinataire")) && !this.patternMatches(listFields.get(key), "[+-]?([0-9]*[.])?[0-9]+")) {

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
		int idClient = this.getListClientKey(form.getListClients(), form.getComboBoxClients().getSelectedItem().toString());
		
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
		String query = "INSERT INTO compte(numeroCompte, solde, soldeInitial, cloture, typeCompte, id_Client)"
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
		
		String query = "INSERT INTO comptecourant(id, soldeMinimum, fraisTransfert)"
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
		
		String query = "INSERT INTO compteepargne(id, plafond, tauxInteret)"
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
	
	public boolean addTransfert(Transfert transfert) {
		boolean isCreated = false;
		
		String query = "INSERT INTO transferer(idCompteDebiteur, idCompteCredite, montant, dateTransfert)"
		        + " VALUES (?, ?, ?, ?)";
		      
        try {	      
		    // create the mysql insert preparedstatement
	        Connection conn = this.app.connect();
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	      
	        preparedStmt.setInt 		(1, transfert.getIdCompteDebiteur());
	        preparedStmt.setInt 		(2, transfert.getIdCompteCredite());
	        preparedStmt.setFloat 		(3, transfert.getMontant());
	        preparedStmt.setDate		(4,  new java.sql.Date(transfert.getDateTransfert().getTime()));
	
	        // check si la requête s'est correctement executée
	        isCreated = preparedStmt.executeUpdate() == 1;
	        conn.close();
		      
		} catch (SQLException e) {
		    System.err.println("Got an exception!");
		    System.err.println(e.getMessage());
		}
        
        return isCreated;
	}
	
	public boolean updateCompte(Transfert transfert) {
		boolean isUpdated = false;
		
		String queryDebite = "UPDATE compte set solde = solde - ? WHERE id = ?";
		String queryCredite = "UPDATE compte set solde = solde + ? WHERE id = ?";
		
        try {	      
		    // create the mysql insert preparedstatement
	        Connection conn = this.app.connect();
	        PreparedStatement preparedStmtCredite = conn.prepareStatement(queryCredite);

	        preparedStmtCredite.setFloat (1, transfert.getMontant());
	        preparedStmtCredite.setInt 	 (2, transfert.getIdCompteCredite());
	        
	        PreparedStatement preparedStmtDebite = conn.prepareStatement(queryDebite);

	        preparedStmtDebite.setFloat (1, transfert.getMontant());
	        preparedStmtDebite.setInt 	(2, transfert.getIdCompteDebiteur());
	
	        // check si la requête s'est correctement executée
	        isUpdated = preparedStmtCredite.executeUpdate() == 1 && preparedStmtDebite.executeUpdate() == 1;
	        conn.close();
		      
		} catch (SQLException e) {
		    System.err.println("Got an exception!");
		    System.err.println(e.getMessage());
		}
		
		return isUpdated;
	}
	
	public int getLastCompte(int numCompte) {
		Integer id = null;
		
		try {
			String query = "SELECT MAX(id) FROM compte WHERE numeroCompte = " + numCompte;
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
		List<Client> clients = clientService.getAll();
		
		try {
			for(Client client: clients) {
				int id = client.getId();
				String libelleClient = client.getLibelleClient();
				String raisonSociale = client.getRaisonSociale();
				String telephone = client.getNumeroTel();
				String adresse = client.getAdresse();
				
				form.getListClients().put(id, libelleClient == null || libelleClient.isEmpty() || libelleClient.isBlank() ? raisonSociale + " - " + telephone + " - " + adresse : libelleClient + " - " + telephone + " - " + adresse);
				form.getComboBoxClients().addItem(libelleClient == null || libelleClient.isEmpty() || libelleClient.isBlank() ? raisonSociale + " - " + telephone + " - " + adresse : libelleClient + " - " + telephone + " - " + adresse);
			}
		}catch(SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			error = true;
		}
		
		return error;
	}
	
	public boolean createTransfert(TransfererForm form) {
		
		boolean isCreatedTransfert = false, isUpdated = false;
		
		int idCompteSource = this.getListClientKey(form.getListCompteSource(), form.getComboBoxCompteSource().getSelectedItem().toString());
		int idCompteDestinataire = this.getListClientKey(form.getListCompteDestinataire(), form.getComboBoxCompteDestinataire().getSelectedItem().toString());
		float montant = Float.parseFloat(form.getTextFieldMontant().getText());
        Date sysDate = new Date();
		
		Transfert transfert = new Transfert(idCompteSource, idCompteDestinataire, montant, sysDate);
		
		isCreatedTransfert = addTransfert(transfert);
		isUpdated = updateCompte(transfert);
		
		return isCreatedTransfert && isUpdated;
	}
	
	public int getListClientKey(Hashtable<Integer, String> list, String value) {
		Integer id = -1;
	    for(Map.Entry<Integer, String> entry : list.entrySet()){
	        if(entry.getValue().equals(value)){
	            id = entry.getKey();
	        }
	    }
	    
	    return id;
	}
	
	public boolean fillListComptes(TransfererForm form) {
		boolean error = false;
		List<Compte> comptes = this.getAll();
		
		try {
			for(Compte compte: comptes) {
				int id = compte.getId();
				Client client = compte.getClient(compte);
				String libelleClient = client.getLibelleClient();
				String raisonSociale = client.getRaisonSociale();
				String typeCompte = compte.getTypeCompte() ? TypeCompte.EPARGNE.getLibelleType() : TypeCompte.COURANT.getLibelleType();
				int numCompte = compte.getNumCompte();
				
				form.getListCompteSource().put(id, libelleClient == null || libelleClient.isEmpty() || libelleClient.isBlank() ? raisonSociale + " - " + numCompte + " - " + typeCompte : libelleClient + " - " + numCompte + " - " + typeCompte);
				form.getComboBoxCompteSource().addItem(libelleClient == null || libelleClient.isEmpty() || libelleClient.isBlank() ? raisonSociale + " - " + numCompte + " - " + typeCompte : libelleClient + " - " + numCompte + " - " + typeCompte);
			
				form.getListCompteDestinataire().put(id, libelleClient == null || libelleClient.isEmpty() || libelleClient.isBlank() ? raisonSociale + " - " + numCompte + " - " + typeCompte : libelleClient + " - " + numCompte + " - " + typeCompte);
				form.getComboBoxCompteDestinataire().addItem(libelleClient == null || libelleClient.isEmpty() || libelleClient.isBlank() ? raisonSociale + " - " + numCompte + " - " + typeCompte : libelleClient + " - " + numCompte + " - " + typeCompte);
			}
		}catch(SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			error = true;
		}
		
		return error;
	}
	
  public boolean updateDepositOrWithdraw(AccountDepositOrWithdraw frame) {
        boolean isUpdated = false;
        
        String query = "UPDATE compte set solde = solde + ? WHERE id = ?";
        
        try {          
            // create the mysql insert preparedstatement
            Connection conn = this.app.connect();
            float sarko = frame.getRdbtndebit().isSelected() ? 0 - Float.parseFloat(frame.getMontantTextField().getText()) : Float.parseFloat(frame.getMontantTextField().getText());
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setFloat (1, sarko);
            preparedStmt.setInt      (2, frame.getIdCompte());
            System.out
					.println(sarko);
            // check si la requ�te s'est correctement execut�e
            isUpdated = preparedStmt.executeUpdate() == 1;
            conn.close();
              
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        
        return isUpdated;
    }
  
	public Compte getCompteById(int idCompte) {
        // TODO Auto-generated method stub
        Compte compte = null;
        try {
            String query = "SELECT * FROM compte WHERE id =" + idCompte;
            Connection conn = this.app.connect();
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
				compte = new Compte(
						rs.getInt("numeroCompte"),
						rs.getFloat("solde"),
						rs.getFloat("soldeInitial"),
						rs.getBoolean("cloture"),
						rs.getBoolean("typeCompte"),
						rs.getInt("id_Client")
				);
				
				compte.setId(rs.getInt("id"));
                rs.close();
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return compte;
    }

	public List<Compte> getAll() {
		List<Compte> list = new ArrayList<Compte>();
		
		try{
			Connection conn = this.app.connect();
			Statement stmt = conn.createStatement();
			
			String query = "SELECT co.* FROM compte co INNER JOIN client cl ON co.id_Client = cl.id WHERE co.cloture = " + CompteStatut.ACTIF.getStatut();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.isBeforeFirst()) {  // Le curseur est-il avant la première ligne ? Sinon pas de données
				while (rs.next()) {
					Compte compte = new Compte(
							rs.getInt("numeroCompte"),
							rs.getFloat("solde"),
							rs.getFloat("soldeInitial"),
							rs.getBoolean("cloture"),
							rs.getBoolean("typeCompte"),
							rs.getInt("id_Client")
					);
					
					compte.setId(rs.getInt("id"));
					
					list.add(compte);
					
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
