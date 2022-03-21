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


public class ClientService {

	public static void fieldReinitialization(CreationClientForm form) {
		form.getSocialReason().setText(null);
		form.getNameText().setText(null);
		form.getFirstnameText().setText(null);
		form.getEmailText().setText(null);
		form.getCiviliteText().setText(null);
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
		listFields.put("Raison sociale", form.getSocialReason().getText());
		listFields.put("Prénom", form.getNameText().getText());
		listFields.put("Nom", form.getFirstnameText().getText());
		listFields.put("Email", form.getEmailText().getText());
		listFields.put("Civilité", form.getCiviliteText().getText());
		listFields.put("Adresse", form.getAdressText().getText());
		
		Set<String> listFieldsKeys = listFields.keySet();
		
        for (String key : listFieldsKeys) {
        	if(listFields.get(key).isEmpty()) {
        		fieldsInError.add(key);
        	}
        }
        
        if(listFields.get("Raison sociale").isEmpty() && (!listFields.get("Nom").isEmpty() && !listFields.get("Prénom").isEmpty())) {
        	fieldsInError.remove("Raison sociale");
        	fieldsInError.remove("Nom");
        	fieldsInError.remove("Prénom");
        }else if(!listFields.get("Raison sociale").isEmpty() && (listFields.get("Nom").isEmpty() && listFields.get("Prénom").isEmpty())) {
        	fieldsInError.remove("Raison sociale");
        	fieldsInError.remove("Nom");
        	fieldsInError.remove("Prénom");
        }
        
		return fieldsInError;
	}	
}


