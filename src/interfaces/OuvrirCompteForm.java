package interfaces;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import services.CompteService;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ButtonGroup;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OuvrirCompteForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNumCompte;
	private JTextField textFieldSoldeInitial;
	private JTextField textFieldSoldeMinimum;
	private JTextField textFieldRaisonSociale;
	private JTextField textFieldFraisDeTransfert;
	private JTextField textFieldPlafond;
	private JTextField textFieldTauxInteret;
	private JRadioButton rdbtnCompteCourant;
	private JRadioButton rdbtnCompteEpargne;
	
	public OuvrirCompteForm() {
		// Instanciation du CompteService
		CompteService compteService = new CompteService();
		
		setTitle("Ouvrir un compte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setPreferredSize(new Dimension(1200, 700));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 700);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel subPanelForm = new JPanel();
		subPanelForm.setBounds(251, 49, 698, 597);
		panel.add(subPanelForm);
		subPanelForm.setBackground(new Color(0.60f,0.60f,0.56f,0.85f));
		subPanelForm.setLayout(null);
		
		JLabel lblBackgroundAccountManagement = new JLabel("");
		lblBackgroundAccountManagement.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBackgroundAccountManagement.setIcon(new ImageIcon(OuvrirCompteForm.class.getResource("/images/backgroundAccountManagement.jpg")));
		lblBackgroundAccountManagement.setBounds(0, 0, 1200, 700);
		panel.add(lblBackgroundAccountManagement);
		
		JLabel lblNumCompte = new JLabel("Numéro de compte :");
		lblNumCompte.setBounds(48, 57, 160, 24);
		subPanelForm.add(lblNumCompte);
		
		JLabel lblTauxInteret = new JLabel("Taux d'intérêt :");
		lblTauxInteret.setBounds(48, 391, 160, 24);
		subPanelForm.add(lblTauxInteret);
		
		JLabel lblPlafond = new JLabel("Plafond :");
		lblPlafond.setBounds(48, 455, 160, 24);
		subPanelForm.add(lblPlafond);
		
		JLabel lblTypeDeCompe = new JLabel("Type de compe :");
		lblTypeDeCompe.setBounds(48, 212, 160, 24);
		subPanelForm.add(lblTypeDeCompe);
		
		JLabel lblRaisonSociale = new JLabel("Raison sociale :");
		lblRaisonSociale.setBackground(SystemColor.desktop);
		lblRaisonSociale.setBounds(48, 111, 160, 24);
		subPanelForm.add(lblRaisonSociale);
		
		JLabel lblFraisDeTransfert = new JLabel("Frais de transfert :");
		lblFraisDeTransfert.setBounds(48, 329, 160, 24);
		subPanelForm.add(lblFraisDeTransfert);
		
		JSeparator separatorNumCompte = new JSeparator();
		separatorNumCompte.setBackground(SystemColor.desktop);
		separatorNumCompte.setForeground(new Color(0, 102, 102));
		separatorNumCompte.setBounds(48, 80, 60, 15);
		subPanelForm.add(separatorNumCompte);
		
		JLabel lblsoldeInitial = new JLabel("Solde initial :");
		lblsoldeInitial.setBackground(SystemColor.desktop);
		lblsoldeInitial.setBounds(48, 165, 160, 24);
		subPanelForm.add(lblsoldeInitial);
		
		JLabel lblSoldeMinimum = new JLabel("Solde minimum :");
		lblSoldeMinimum.setBounds(48, 267, 160, 24);
		subPanelForm.add(lblSoldeMinimum);
		
		JSeparator separatorPlafond = new JSeparator();
		separatorPlafond.setForeground(new Color(0, 102, 102));
		separatorPlafond.setBackground(SystemColor.desktop);
		separatorPlafond.setBounds(48, 480, 60, 15);
		subPanelForm.add(separatorPlafond);
		
		JSeparator separatorTauxInteret = new JSeparator();
		separatorTauxInteret.setForeground(new Color(0, 102, 102));
		separatorTauxInteret.setBackground(SystemColor.desktop);
		separatorTauxInteret.setBounds(48, 415, 60, 15);
		subPanelForm.add(separatorTauxInteret);
		
		JSeparator separatorSoldeInitial = new JSeparator();
		separatorSoldeInitial.setBackground(SystemColor.desktop);
		separatorSoldeInitial.setForeground(new Color(0, 102, 102));
		separatorSoldeInitial.setBounds(48, 188, 60, 15);
		subPanelForm.add(separatorSoldeInitial);
		
		JSeparator separatorRaisonSociale = new JSeparator();
		separatorRaisonSociale.setForeground(new Color(0, 102, 102));
		separatorRaisonSociale.setBackground(SystemColor.desktop);
		separatorRaisonSociale.setBounds(48, 134, 60, 15);
		subPanelForm.add(separatorRaisonSociale);
		
		JSeparator separatorFraisDeTransfert = new JSeparator();
		separatorFraisDeTransfert.setForeground(new Color(0, 102, 102));
		separatorFraisDeTransfert.setBackground(SystemColor.desktop);
		separatorFraisDeTransfert.setBounds(48, 353, 60, 15);
		subPanelForm.add(separatorFraisDeTransfert);
		
		JSeparator separatorSoldeMinimum = new JSeparator();
		separatorSoldeMinimum.setBackground(SystemColor.desktop);
		separatorSoldeMinimum.setForeground(new Color(0, 102, 102));
		separatorSoldeMinimum.setBounds(48, 291, 60, 15);
		subPanelForm.add(separatorSoldeMinimum);
		
		JSeparator separatorTypeCompte = new JSeparator();
		separatorTypeCompte.setForeground(new Color(0, 102, 102));
		separatorTypeCompte.setBackground(SystemColor.desktop);
		separatorTypeCompte.setBounds(48, 237, 60, 15);
		subPanelForm.add(separatorTypeCompte);
		
		textFieldNumCompte = new JTextField();
		textFieldNumCompte.setForeground(Color.WHITE);
		textFieldNumCompte.setEditable(false);
		textFieldNumCompte.setBackground(new Color(51, 102, 102));
		textFieldNumCompte.setBounds(304, 53, 326, 33);
		textFieldNumCompte.setBorder(null);
		subPanelForm.add(textFieldNumCompte);
		textFieldNumCompte.setColumns(10);
		
		// Appel de la fonction de génération de numéro du CompteService
		compteService.generateNumCompte(textFieldNumCompte);
		
		textFieldRaisonSociale = new JTextField();
		textFieldRaisonSociale.setForeground(Color.WHITE);
		textFieldRaisonSociale.setEditable(false);
		textFieldRaisonSociale.setColumns(10);
		textFieldRaisonSociale.setBorder(null);
		textFieldRaisonSociale.setBackground(new Color(51, 102, 102));
		textFieldRaisonSociale.setBounds(304, 107, 326, 33);
		subPanelForm.add(textFieldRaisonSociale);
		
		textFieldSoldeInitial = new JTextField();
		textFieldSoldeInitial.setForeground(Color.WHITE);
		textFieldSoldeInitial.setColumns(10);
		textFieldSoldeInitial.setBackground(new Color(51, 102, 102));
		textFieldSoldeInitial.setBounds(304, 161, 326, 33);
		textFieldSoldeInitial.setBorder(null);
		subPanelForm.add(textFieldSoldeInitial);
		
		textFieldSoldeMinimum = new JTextField();
		textFieldSoldeMinimum.setForeground(Color.WHITE);
		textFieldSoldeMinimum.setColumns(10);
		textFieldSoldeMinimum.setBackground(new Color(51, 102, 102));
		textFieldSoldeMinimum.setBounds(304, 263, 326, 33);
		textFieldSoldeMinimum.setBorder(null);
		subPanelForm.add(textFieldSoldeMinimum);
		
		textFieldFraisDeTransfert = new JTextField();
		textFieldFraisDeTransfert.setForeground(Color.WHITE);
		textFieldFraisDeTransfert.setColumns(10);
		textFieldFraisDeTransfert.setBorder(null);
		textFieldFraisDeTransfert.setBackground(new Color(51, 102, 102));
		textFieldFraisDeTransfert.setBounds(304, 325, 326, 33);
		subPanelForm.add(textFieldFraisDeTransfert);
		
		textFieldPlafond = new JTextField();
		textFieldPlafond.setForeground(Color.WHITE);
		textFieldPlafond.setColumns(10);
		textFieldPlafond.setBorder(null);
		textFieldPlafond.setEditable(false);
		textFieldPlafond.setBackground(new Color(26, 53, 53));
		textFieldPlafond.setBounds(304, 451, 326, 33);
		subPanelForm.add(textFieldPlafond);
		
		textFieldTauxInteret = new JTextField();
		textFieldTauxInteret.setForeground(Color.WHITE);
		textFieldTauxInteret.setColumns(10);
		textFieldTauxInteret.setBorder(null);
		textFieldTauxInteret.setEditable(false);
		textFieldTauxInteret.setBackground(new Color(26, 53, 53));
		textFieldTauxInteret.setBounds(304, 387, 326, 33);
		subPanelForm.add(textFieldTauxInteret);
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		
		rdbtnCompteCourant = new JRadioButton("Courant");
		rdbtnCompteCourant.setBackground(new Color(0, 0, 0, 0));
		rdbtnCompteCourant.setSelected(true);
		rdbtnCompteCourant.setBounds(306, 206, 160, 39);
		rdbtnCompteCourant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// Réinitialisation des champs spécifiques au compte épargne
				textFieldPlafond.setText(null);
				textFieldPlafond.setEditable(false);
				textFieldPlafond.setBackground(new Color(26, 53, 53));
				textFieldTauxInteret.setText(null);
				textFieldTauxInteret.setEditable(false);
				textFieldTauxInteret.setBackground(new Color(26, 53, 53));
				
				// Activation des champs spécifiques au compte courant
				textFieldSoldeMinimum.setEditable(true);
				textFieldSoldeMinimum.setBackground(new Color(51, 102, 102));
				textFieldFraisDeTransfert.setEditable(true);
				textFieldFraisDeTransfert.setBackground(new Color(51, 102, 102));		
			}
		});
		
		subPanelForm.add(rdbtnCompteCourant);
		
		rdbtnCompteEpargne = new JRadioButton("Epargne");
		rdbtnCompteEpargne.setBackground(new Color(0, 0, 0, 0));
		rdbtnCompteEpargne.setBounds(470, 206, 160, 39);
		rdbtnCompteEpargne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// Réinitialisation des champs spécifiques au compte épargne
				textFieldSoldeMinimum.setText(null);
				textFieldSoldeMinimum.setEditable(false);
				textFieldSoldeMinimum.setBackground(new Color(26, 53, 53));
				textFieldFraisDeTransfert.setText(null);
				textFieldFraisDeTransfert.setEditable(false);
				textFieldFraisDeTransfert.setBackground(new Color(26, 53, 53));
				
				// Activation des champs spécifiques au compte courant
				textFieldPlafond.setEditable(true);
				textFieldPlafond.setBackground(new Color(51, 102, 102));		
				textFieldTauxInteret.setEditable(true);
				textFieldTauxInteret.setBackground(new Color(51, 102, 102));		
			}
		});
		subPanelForm.add(rdbtnCompteEpargne);
		
		// Ajout des boutons radio dans un groupe => sélection unique
		
		radioButtonGroup.add(rdbtnCompteCourant);
		radioButtonGroup.add(rdbtnCompteEpargne);
		
		JButton btnCreer = new JButton("Créer");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        Component component = (Component) e.getSource();
		        OuvrirCompteForm frame = (OuvrirCompteForm) SwingUtilities.getWindowAncestor(component);
		        compteService.checkFillFields(frame);
			}
		});
		
		btnCreer.setBounds(497, 541, 133, 33);
		subPanelForm.add(btnCreer);
		
		JButton btnReinitialiser = new JButton("Réinitialiser");
		btnReinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        Component component = (Component) e.getSource();
		        OuvrirCompteForm frame = (OuvrirCompteForm) SwingUtilities.getWindowAncestor(component);
		        
		        compteService.fieldReinitialization(frame);
			}
		});
		
		btnReinitialiser.setBounds(263, 541, 149, 33);
		subPanelForm.add(btnReinitialiser);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(48, 541, 133, 33);
		subPanelForm.add(btnRetour);
		
		pack();
	}
	
	public JTextField getTextFieldNumCompte() {
		return textFieldNumCompte;
	}

	public JTextField getTextFieldSoldeInitial() {
		return textFieldSoldeInitial;
	}

	public JTextField getTextFieldSoldeMinimum() {
		return textFieldSoldeMinimum;
	}
	
	public JTextField getTextFieldRaisonSociale() {
		return textFieldRaisonSociale;
	}

	public JTextField getTextFieldFraisDeTransfert() {
		return textFieldFraisDeTransfert;
	}

	public JTextField getTextFieldPlafond() {
		return textFieldPlafond;
	}

	public JTextField getTextFieldTauxInteret() {
		return textFieldTauxInteret;
	}
	
	public JRadioButton getRdbtnCompteCourant() {
		return rdbtnCompteCourant;
	}

	public JRadioButton getRdbtnCompteEpargne() {
		return rdbtnCompteEpargne;
	}
}
