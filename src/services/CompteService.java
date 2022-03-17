package services;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import interfaces.OuvrirCompteForm;

public class CompteService {
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
        
        // initialisation d'une liste contenant les libellés des champs mal saisi (format numérique)
        List<String> badNumericFields = new ArrayList<String>();
        
        // initialisation d'un tableau associatif contenant les libellés et la valeur des champs du formulaire
        Hashtable<String, JTextField> listFields = new Hashtable<String, JTextField>();
        listFields.put("solde initial", form.getTextFieldSoldeInitial());
        listFields.put("solde minimum", form.getTextFieldSoldeMinimum());
        listFields.put("frais de transfert", form.getTextFieldFraisDeTransfert());
        listFields.put("taux d'intéret", form.getTextFieldTauxInteret());
        listFields.put("plafond", form.getTextFieldPlafond());
        
        // récupérations des clés du tableau associatif
        Set<String> listFieldsKeys = listFields.keySet();
        
        // pour chaque champ du formulaire
        for (String key : listFieldsKeys) {
        	// le champ est-il vide ?
            if(listFields.get(key).getText().isEmpty()) {
            	
            	// si création d'un compte courant, ajout du champ concerné dans la liste appropriée
            	if(form.getRdbtnCompteCourant().isSelected() && (key.equals("solde minimum") || key.equals("frais de transfert"))) {
                	emptyFields.add(key);
            	}else if(form.getRdbtnCompteEpargne().isSelected() && (key.equals("taux d'intéret") || key.equals("plafond"))) {
            		// si création d'un compte épargne, ajout du champs concerné dans la liste appropriée
            		emptyFields.add(key);
            	}else if(key.equals("solde initial")){
            		// ajout du champ dans la liste appropriée
            		emptyFields.add(key);
            	}
            // Sinon, est-il bien au format numérique ?	
            }else {
            	if(!this.patternMatches(listFields.get(key).getText(), "[+-]?([0-9]*[.])?[0-9]+")) {
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
}
