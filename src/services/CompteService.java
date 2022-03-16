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
//		System.out.println(form.getTextFieldSoldeMinimum().getText());
	}
}
