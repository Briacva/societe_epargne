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
	
	public Hashtable<String, List<String>> checkFields(OuvrirCompteForm form) {
		
        // create a new ArrayList
		Hashtable<String, List<String>> fieldsInError = new Hashtable<String, List<String>>();
		
        List<String> emptyFields = new ArrayList<String>();
        List<String> badNumericFields = new ArrayList<String>();
        
        Hashtable<String, JTextField> listFields = new Hashtable<String, JTextField>();
        listFields.put("solde initial", form.getTextFieldSoldeInitial());
        listFields.put("solde minimum", form.getTextFieldSoldeMinimum());
        listFields.put("frais de transfert", form.getTextFieldFraisDeTransfert());
        listFields.put("taux d'intéret", form.getTextFieldTauxInteret());
        listFields.put("plafond", form.getTextFieldPlafond());
        
        Set<String> listFieldsKeys = listFields.keySet();
        
        for (String key : listFieldsKeys) {
            if(listFields.get(key).getText().isEmpty()) {
            	
            	if(form.getRdbtnCompteCourant().isSelected() && (key.equals("solde minimum") || key.equals("frais de transfert"))) {
                	emptyFields.add(key);
            	}else if(form.getRdbtnCompteEpargne().isSelected() && (key.equals("taux d'intéret") || key.equals("plafond"))) {
            		emptyFields.add(key);
            	}else if(key.equals("solde initial")){
            		emptyFields.add(key);
            	}
            	
            }else {
            	if(!this.patternMatches(listFields.get(key).getText(), "[+-]?([0-9]*[.])?[0-9]+")) {
            		badNumericFields.add(key);
            	}
            }
        }
        
        fieldsInError.put("emptyFields", emptyFields);
        fieldsInError.put("badNumericFields", badNumericFields);
        
        return fieldsInError;
        
	}
	
	public boolean patternMatches(String field, String regexPattern) {
        return Pattern.compile(regexPattern)
          .matcher((CharSequence) field)
          .matches();
    }
}
