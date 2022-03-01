package interfaces;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class OuvrirCompteForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNumCompte;
	private JTextField textFieldSoldeInitial;
	private JTextField textFieldSoldeMinimum;

	public OuvrirCompteForm() {
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
		
		textFieldNumCompte = new JTextField();
		textFieldNumCompte.setBackground(new Color(51, 102, 102));
		textFieldNumCompte.setBounds(304, 53, 326, 33);
		textFieldNumCompte.setBorder(null);
		subPanelForm.add(textFieldNumCompte);
		textFieldNumCompte.setColumns(10);
		
		JLabel lblNumCompte = new JLabel("Numéro de compte :");
		lblNumCompte.setBounds(48, 57, 160, 24);
		subPanelForm.add(lblNumCompte);
		
		JSeparator separatorNumCOmpte = new JSeparator();
		separatorNumCOmpte.setBackground(SystemColor.desktop);
		separatorNumCOmpte.setForeground(new Color(0, 102, 102));
		separatorNumCOmpte.setBounds(48, 80, 60, 15);
		subPanelForm.add(separatorNumCOmpte);
		
		textFieldSoldeInitial = new JTextField();
		textFieldSoldeInitial.setColumns(10);
		textFieldSoldeInitial.setBackground(new Color(51, 102, 102));
		textFieldSoldeInitial.setBounds(304, 126, 326, 33);
		textFieldSoldeInitial.setBorder(null);
		subPanelForm.add(textFieldSoldeInitial);
		
		textFieldSoldeMinimum = new JTextField();
		textFieldSoldeMinimum.setColumns(10);
		textFieldSoldeMinimum.setBackground(new Color(51, 102, 102));
		textFieldSoldeMinimum.setBounds(304, 203, 326, 33);
		textFieldSoldeMinimum.setBorder(null);
		subPanelForm.add(textFieldSoldeMinimum);
		
		JLabel lblsoldeInitial = new JLabel("Solde initial :");
		lblsoldeInitial.setBackground(SystemColor.desktop);
		lblsoldeInitial.setBounds(48, 130, 160, 24);
		subPanelForm.add(lblsoldeInitial);
		
		JSeparator separatorSoldeInitial = new JSeparator();
		separatorSoldeInitial.setBackground(SystemColor.desktop);
		separatorSoldeInitial.setForeground(new Color(0, 102, 102));
		separatorSoldeInitial.setBounds(48, 153, 60, 15);
		subPanelForm.add(separatorSoldeInitial);
		
		JLabel lblSoldeMinimum = new JLabel("Solde minimum :");
		lblSoldeMinimum.setBounds(48, 207, 160, 24);
		subPanelForm.add(lblSoldeMinimum);
		
		JSeparator separatorSoldeMinimum = new JSeparator();
		separatorSoldeMinimum.setBackground(SystemColor.desktop);
		separatorSoldeMinimum.setForeground(new Color(0, 102, 102));
		separatorSoldeMinimum.setBounds(48, 231, 60, 15);
		subPanelForm.add(separatorSoldeMinimum);
		
		JRadioButton rdbtnCompteCourant = new JRadioButton("Courant");
		rdbtnCompteCourant.setBackground(new Color(0, 0, 0, 0));
		rdbtnCompteCourant.setSelected(true);
		rdbtnCompteCourant.setBounds(304, 269, 149, 23);
		subPanelForm.add(rdbtnCompteCourant);
		
		JRadioButton rdbtnCompteEpargne = new JRadioButton("Epargne");
		rdbtnCompteEpargne.setBackground(new Color(0, 0, 0, 0));
		rdbtnCompteEpargne.setBounds(481, 269, 149, 23);
		subPanelForm.add(rdbtnCompteEpargne);
		
		JLabel lblTypeDeCompe = new JLabel("Type de compe :");
		lblTypeDeCompe.setBounds(48, 273, 160, 24);
		subPanelForm.add(lblTypeDeCompe);
		
		JSeparator separatorTypeCompte = new JSeparator();
		separatorTypeCompte.setForeground(new Color(0, 102, 102));
		separatorTypeCompte.setBackground(SystemColor.desktop);
		separatorTypeCompte.setBounds(48, 298, 60, 15);
		subPanelForm.add(separatorTypeCompte);
		
		JButton btnNewButton = new JButton("Créer");
		btnNewButton.setBackground(Color.decode("#8F8E8E"));
		btnNewButton.setBounds(33, 524, 133, 33);
		subPanelForm.add(btnNewButton);
		
		JButton btnRinitialiser = new JButton("Réinitialiser");
		btnRinitialiser.setBounds(255, 524, 149, 33);
		subPanelForm.add(btnRinitialiser);
		
		JButton btnNewButton_1_1 = new JButton("Retour");
		btnNewButton_1_1.setBounds(497, 524, 133, 33);
		subPanelForm.add(btnNewButton_1_1);
		
		JLabel lblBackgroundAccountManagement = new JLabel("");
		lblBackgroundAccountManagement.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBackgroundAccountManagement.setIcon(new ImageIcon(OuvrirCompteForm.class.getResource("/images/backgroundAccountManagement.jpg")));
		lblBackgroundAccountManagement.setBounds(0, 0, 1200, 700);
		panel.add(lblBackgroundAccountManagement);
		pack();
	}
}
