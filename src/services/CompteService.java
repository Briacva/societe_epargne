package services;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
	
	public void checkFillFields(OuvrirCompteForm form) {
		form.getLblRaisonSocialeErreur().setText(null);
		form.getLblNumCompteErreur().setText(null);
		form.getLblSoldeInitialError().setText(null);
		form.getLblSoldeMinimimError().setText(null);
		form.getLblFraisDeTransfertError().setText(null);
		form.getLblTauxInteretError().setText(null);
		form.getLblPlafondError().setText(null);
		
		
		if(form.getTextFieldSoldeInitial().getText().isEmpty()) {
			form.getLblSoldeInitialError().setText("Veuillez saisir le champ !");
		}
		
		if(form.getRdbtnCompteCourant().isSelected() && form.getTextFieldSoldeMinimum().getText().isEmpty()) {
			form.getLblSoldeMinimimError().setText("Veuillez saisir le champ !");
		}
		
		if(form.getRdbtnCompteCourant().isSelected() && form.getTextFieldFraisDeTransfert().getText().isEmpty()) {
			form.getLblFraisDeTransfertError().setText("Veuillez saisir le champ !");
		}
		
		if(form.getRdbtnCompteEpargne().isSelected() && form.getTextFieldTauxInteret().getText().isEmpty()) {
			form.getLblTauxInteretError().setText("Veuillez saisir le champ !");
		}
		
		if(form.getRdbtnCompteEpargne().isSelected() && form.getTextFieldPlafond().getText().isEmpty()) {
			form.getLblPlafondError().setText("Veuillez saisir le champ !");
		}
	}
}
