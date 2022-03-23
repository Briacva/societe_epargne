package interfaces;

import java.awt.Dimension;
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
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

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
		lblNumCompte.setBounds(51, 81, 160, 24);
		subPanelForm.add(lblNumCompte);
		
		JLabel lblTauxInteret = new JLabel("Taux d'intérêt* :");
		lblTauxInteret.setBounds(51, 415, 160, 24);
		subPanelForm.add(lblTauxInteret);
		
		JLabel lblPlafond = new JLabel("Plafond* :");
		lblPlafond.setBounds(51, 479, 160, 24);
		subPanelForm.add(lblPlafond);
		
		JLabel lblTypeDeCompe = new JLabel("Type de compe* :");
		lblTypeDeCompe.setBounds(51, 236, 160, 24);
		subPanelForm.add(lblTypeDeCompe);
		
		JLabel lblRaisonSociale = new JLabel("Raison sociale :");
		lblRaisonSociale.setBackground(SystemColor.desktop);
		lblRaisonSociale.setBounds(51, 135, 160, 24);
		subPanelForm.add(lblRaisonSociale);
		
		JLabel lblFraisDeTransfert = new JLabel("Frais de transfert* :");
		lblFraisDeTransfert.setBounds(51, 353, 160, 24);
		subPanelForm.add(lblFraisDeTransfert);
		
		JSeparator separatorNumCompte = new JSeparator();
		separatorNumCompte.setBackground(SystemColor.desktop);
		separatorNumCompte.setForeground(new Color(0, 102, 102));
		separatorNumCompte.setBounds(51, 104, 60, 15);
		subPanelForm.add(separatorNumCompte);
		
		JLabel lblsoldeInitial = new JLabel("Solde initial* :");
		lblsoldeInitial.setBackground(SystemColor.desktop);
		lblsoldeInitial.setBounds(51, 189, 160, 24);
		subPanelForm.add(lblsoldeInitial);
		
		JLabel lblSoldeMinimum = new JLabel("Solde minimum* :");
		lblSoldeMinimum.setBounds(51, 291, 160, 24);
		subPanelForm.add(lblSoldeMinimum);
		
		JSeparator separatorPlafond = new JSeparator();
		separatorPlafond.setForeground(new Color(0, 102, 102));
		separatorPlafond.setBackground(SystemColor.desktop);
		separatorPlafond.setBounds(51, 504, 60, 15);
		subPanelForm.add(separatorPlafond);
		
		JSeparator separatorTauxInteret = new JSeparator();
		separatorTauxInteret.setForeground(new Color(0, 102, 102));
		separatorTauxInteret.setBackground(SystemColor.desktop);
		separatorTauxInteret.setBounds(51, 439, 60, 15);
		subPanelForm.add(separatorTauxInteret);
		
		JSeparator separatorSoldeInitial = new JSeparator();
		separatorSoldeInitial.setBackground(SystemColor.desktop);
		separatorSoldeInitial.setForeground(new Color(0, 102, 102));
		separatorSoldeInitial.setBounds(51, 212, 60, 15);
		subPanelForm.add(separatorSoldeInitial);
		
		JSeparator separatorRaisonSociale = new JSeparator();
		separatorRaisonSociale.setForeground(new Color(0, 102, 102));
		separatorRaisonSociale.setBackground(SystemColor.desktop);
		separatorRaisonSociale.setBounds(51, 158, 60, 15);
		subPanelForm.add(separatorRaisonSociale);
		
		JSeparator separatorFraisDeTransfert = new JSeparator();
		separatorFraisDeTransfert.setForeground(new Color(0, 102, 102));
		separatorFraisDeTransfert.setBackground(SystemColor.desktop);
		separatorFraisDeTransfert.setBounds(51, 377, 60, 15);
		subPanelForm.add(separatorFraisDeTransfert);
		
		JSeparator separatorSoldeMinimum = new JSeparator();
		separatorSoldeMinimum.setBackground(SystemColor.desktop);
		separatorSoldeMinimum.setForeground(new Color(0, 102, 102));
		separatorSoldeMinimum.setBounds(51, 315, 60, 15);
		subPanelForm.add(separatorSoldeMinimum);
		
		JSeparator separatorTypeCompte = new JSeparator();
		separatorTypeCompte.setForeground(new Color(0, 102, 102));
		separatorTypeCompte.setBackground(SystemColor.desktop);
		separatorTypeCompte.setBounds(51, 261, 60, 15);
		subPanelForm.add(separatorTypeCompte);
		
		textFieldNumCompte = new JTextField();
		textFieldNumCompte.setForeground(Color.WHITE);
		textFieldNumCompte.setEditable(false);
		textFieldNumCompte.setBackground(new Color(51, 102, 102));
		textFieldNumCompte.setBounds(307, 77, 326, 33);
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
		textFieldRaisonSociale.setBounds(307, 131, 326, 33);
		subPanelForm.add(textFieldRaisonSociale);
		
		textFieldSoldeInitial = new JTextField();
		textFieldSoldeInitial.setForeground(Color.WHITE);
		textFieldSoldeInitial.setColumns(10);
		textFieldSoldeInitial.setBackground(new Color(51, 102, 102));
		textFieldSoldeInitial.setBounds(307, 185, 326, 33);
		textFieldSoldeInitial.setBorder(null);
		subPanelForm.add(textFieldSoldeInitial);
		
		textFieldSoldeMinimum = new JTextField();
		textFieldSoldeMinimum.setForeground(Color.WHITE);
		textFieldSoldeMinimum.setColumns(10);
		textFieldSoldeMinimum.setBackground(new Color(51, 102, 102));
		textFieldSoldeMinimum.setBounds(307, 287, 326, 33);
		textFieldSoldeMinimum.setBorder(null);
		subPanelForm.add(textFieldSoldeMinimum);
		
		textFieldFraisDeTransfert = new JTextField();
		textFieldFraisDeTransfert.setForeground(Color.WHITE);
		textFieldFraisDeTransfert.setColumns(10);
		textFieldFraisDeTransfert.setBorder(null);
		textFieldFraisDeTransfert.setBackground(new Color(51, 102, 102));
		textFieldFraisDeTransfert.setBounds(307, 349, 326, 33);
		subPanelForm.add(textFieldFraisDeTransfert);
		
		textFieldPlafond = new JTextField();
		textFieldPlafond.setForeground(Color.WHITE);
		textFieldPlafond.setColumns(10);
		textFieldPlafond.setBorder(null);
		textFieldPlafond.setEditable(false);
		textFieldPlafond.setBackground(new Color(26, 53, 53));
		textFieldPlafond.setBounds(307, 475, 326, 33);
		subPanelForm.add(textFieldPlafond);
		
		textFieldTauxInteret = new JTextField();
		textFieldTauxInteret.setForeground(Color.WHITE);
		textFieldTauxInteret.setColumns(10);
		textFieldTauxInteret.setBorder(null);
		textFieldTauxInteret.setEditable(false);
		textFieldTauxInteret.setBackground(new Color(26, 53, 53));
		textFieldTauxInteret.setBounds(307, 411, 326, 33);
		subPanelForm.add(textFieldTauxInteret);
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		
		rdbtnCompteCourant = new JRadioButton("Courant");
		rdbtnCompteCourant.setBackground(new Color(0, 0, 0, 0));
		rdbtnCompteCourant.setSelected(true);
		rdbtnCompteCourant.setBounds(309, 230, 160, 39);
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
		rdbtnCompteEpargne.setBounds(473, 230, 160, 39);
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
		btnCreer.setForeground(new Color(255, 255, 255));
		btnCreer.setBackground(new Color(30, 125, 125));
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Récupération de l'interface
		        Component component = (Component) e.getSource();
		        OuvrirCompteForm frame = (OuvrirCompteForm) SwingUtilities.getWindowAncestor(component);
		        
		        // alimentation du tableau des erreurs
		        Hashtable<String, List<String>> errors = compteService.checkFields(frame);
		        
		        // si il y a plusieurs champs non saisis
		        if((errors.get("emptyFields").size() > 0)) {
		        	
	                String error = errors.get("emptyFields").stream().collect(Collectors.joining(", "));
	                
	                JOptionPane.showMessageDialog(getContentPane(), 
	                      "Veuillez saisir le ou les champs suivants : " + error,
	                      "Erreur",
	                      JOptionPane.ERROR_MESSAGE);
	            // sinon si les champs ne sont pas numériques    
                }else if(errors.get("emptyFields").size() == 0 && errors.get("badNumericFields").size() > 0) {
                	
	                String error = errors.get("badNumericFields").stream().collect(Collectors.joining(", "));
	                
	                JOptionPane.showMessageDialog(getContentPane(), 
	                      "Le ou les champs suivants ne sont pas au format numérique : " + error,
	                      "Erreur",
	                      JOptionPane.ERROR_MESSAGE);
	            // sinon le champ solde initial est-il inférieur au champ soolde minimum
                }else if(rdbtnCompteCourant.isSelected() && Double.parseDouble(textFieldSoldeInitial.getText()) < Double.parseDouble(textFieldSoldeMinimum.getText())) {
                	
	                JOptionPane.showMessageDialog(getContentPane(), 
		                      "Le solde initial ne peut pas être inférieur au solde minimum autorisé !",
		                      "Avertissement",
		                      JOptionPane.WARNING_MESSAGE);
	            //sinon si le champ solde initial est-il supérieur au champ plafond    
                }else if(rdbtnCompteEpargne.isSelected() && Double.parseDouble(textFieldSoldeInitial.getText()) > Double.parseDouble(textFieldPlafond.getText())) {
                	
	                JOptionPane.showMessageDialog(getContentPane(), 
		                      "Le solde initial ne peut pas être supérieur au plafond autorisé !",
		                      "Avertissement",
		                      JOptionPane.WARNING_MESSAGE);
                }else if(rdbtnCompteEpargne.isSelected() && (Double.parseDouble(textFieldSoldeInitial.getText()) > 100000 || Double.parseDouble(textFieldPlafond.getText()) > 100000)) {
                	JOptionPane.showMessageDialog(getContentPane(), 
		                      "La valeur maximal des champs solde initial et plafond ne peut pas excéder 100000 !",
		                      "Avertissement",
		                      JOptionPane.WARNING_MESSAGE);
                }else if(rdbtnCompteEpargne.isSelected() && (Double.parseDouble(textFieldTauxInteret.getText()) > 100)) {
                	JOptionPane.showMessageDialog(getContentPane(), 
		                      "Le taux d'intérêt ne peut pas excéder 100% !",
		                      "Avertissement",
		                      JOptionPane.WARNING_MESSAGE);
                }
                else {
                	//vérifier que le champ taux d'intérêt est pas supérieur à 100
                	if(compteService.createBankAccount(frame)){
                    	JOptionPane.showMessageDialog(getContentPane(), 
  		                      "Le compte a bien été créé.",
  		                      "Information",
  		                      JOptionPane.INFORMATION_MESSAGE);
                    	
                    	compteService.fieldReinitialization(frame);
                	}else {
                    	JOptionPane.showMessageDialog(getContentPane(), 
    		                      "Une erreur s'est produite, le compte n'a pas été enregistré !",
    		                      "Erreur",
    		                      JOptionPane.ERROR_MESSAGE);
                	}
                }		        
			}
		});
		
		btnCreer.setBounds(497, 541, 133, 33);
		subPanelForm.add(btnCreer);
		
		JButton btnReinitialiser = new JButton("Réinitialiser");
		btnReinitialiser.setForeground(new Color(255, 255, 255));
		btnReinitialiser.setBackground(new Color(30, 125, 125));
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
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
		        OuvrirCompteForm frame = (OuvrirCompteForm) SwingUtilities.getWindowAncestor(component);
				ListeComptesForm listeComptes = new ListeComptesForm();
				listeComptes.setVisible(true);
				frame.dispose();
			}
		});
		btnRetour.setForeground(new Color(255, 255, 255));
		btnRetour.setBackground(new Color(30, 125, 125));
		btnRetour.setBounds(48, 541, 133, 33);
		subPanelForm.add(btnRetour);
		
		JComboBox<String> comboBoxClients = new JComboBox<String>();
		comboBoxClients.setForeground(Color.WHITE);
		comboBoxClients.setBackground(SystemColor.desktop);
		comboBoxClients.setBounds(307, 24, 326, 33);
		compteService.fillListClients(comboBoxClients);
		subPanelForm.add(comboBoxClients);
		
		JLabel lblClients = new JLabel("Sélectionnez un client :");
		lblClients.setBounds(51, 24, 193, 24);
		subPanelForm.add(lblClients);
		
		JSeparator separatorClients = new JSeparator();
		separatorClients.setForeground(new Color(0, 102, 102));
		separatorClients.setBackground(SystemColor.desktop);
		separatorClients.setBounds(51, 47, 60, 15);
		subPanelForm.add(separatorClients);
		
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
