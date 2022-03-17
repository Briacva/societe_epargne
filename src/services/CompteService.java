package services;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
	
	public void checkFillFields(OuvrirCompteForm form) {
		
		if(form.getTextFieldSoldeInitial().getText().isEmpty()) {
			form.getTextFieldSoldeInitial().setBorder(BorderFactory.createLineBorder(new Color(139,0, 0), 2));
			form.getTextFieldSoldeInitial().setBackground(new Color(250,158,114));
		}
		
		if(form.getRdbtnCompteCourant().isSelected() && form.getTextFieldSoldeMinimum().getText().isEmpty()) {
//			form.getLblSoldeMinimimError().setText("Veuillez saisir le champ !");
		}
		
		if(form.getRdbtnCompteCourant().isSelected() && form.getTextFieldFraisDeTransfert().getText().isEmpty()) {
//			form.getLblFraisDeTransfertError().setText("Veuillez saisir le champ !");
		}
		
		if(form.getRdbtnCompteEpargne().isSelected() && form.getTextFieldTauxInteret().getText().isEmpty()) {
//			form.getLblTauxInteretError().setText("Veuillez saisir le champ !");
		}
		
		if(form.getRdbtnCompteEpargne().isSelected() && form.getTextFieldPlafond().getText().isEmpty()) {
//			form.getLblPlafondError().setText("Veuillez saisir le champ !");
		}
	}
}
