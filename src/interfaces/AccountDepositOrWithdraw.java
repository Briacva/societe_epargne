package interfaces;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import models.Client;
import models.Compte;
import services.ClientService;
import services.CompteService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AccountDepositOrWithdraw extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField MontantTextField;
	private Compte compte;
	private JRadioButton rdbtndebit;
	private JRadioButton rdbtnCredit;
	private ClientService clientService;
	private CompteService compteService;
	private JButton Validate;
	private int idCompte;
	
	public Compte getCompte() {
		return compte;
	}

	public JTextField getMontantTextField() {
		return MontantTextField;
	}

	public void
			setAccountNumber(JLabel accountNumber) {
	}

	public void setNameOrSocialInput(
			JLabel nameOrSocialInput) {
	}

	public void setSoldeIntput(JLabel soldeIntput) {
	}
	
	public JRadioButton getRdbtndebit() {
		return rdbtndebit;
	}

	public JRadioButton getRdbtnCredit() {
		return rdbtnCredit;
	}
	public int getIdCompte() {
		return idCompte;
	}
		
	public AccountDepositOrWithdraw(Compte compte) {
		this.compte = compte;
		this.idCompte = compte.getId();
		this.clientService = new ClientService();
		this.compteService = new CompteService();
		getContentPane().setForeground(new Color(0, 255, 0));
		setTitle("Societe d'epargne");
		setSize(1200,800);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0.60f,0.60f,0.56f,0.85f));
		panel.setBounds(301, 89, 600, 600);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Débit/Crédit");
		lblNewLabel.setBounds(-2, 34, 602, 37);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel.add(lblNewLabel);
		
		JLabel NumeroDeCompte = new JLabel("Num\u00E9ro de compte");
		NumeroDeCompte.setHorizontalAlignment(SwingConstants.CENTER);
		NumeroDeCompte.setBounds(-2, 174, 602, 17);
		NumeroDeCompte.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(NumeroDeCompte);
		
		JLabel AccountNumber = new JLabel(String.valueOf(this.compte.getNumCompte()));
		AccountNumber.setHorizontalAlignment(SwingConstants.CENTER);
		AccountNumber.setBounds(0, 221, 601, 14);
		panel.add(AccountNumber);
		
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		rdbtndebit = new JRadioButton("D\u00E9biter", true);
		rdbtndebit.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtndebit.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtndebit.setBackground(Color.LIGHT_GRAY);
		rdbtndebit.setBounds(143, 111, 153, 29);
		panel.add(rdbtndebit);
		
		rdbtnCredit = new JRadioButton("Cr\u00E9diter");
		rdbtnCredit.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnCredit.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnCredit.setBackground(Color.LIGHT_GRAY);
		rdbtnCredit.setBounds(325, 111, 157, 29);
		panel.add(rdbtnCredit);
		radioButtonGroup.add(rdbtnCredit);
		radioButtonGroup.add(rdbtndebit);
		
		JLabel NameOrSocialReason = new JLabel("Raison sociale / Libell\u00E9 client");
		NameOrSocialReason.setHorizontalAlignment(SwingConstants.CENTER);
		NameOrSocialReason.setFont(new Font("Tahoma", Font.PLAIN, 18));
		NameOrSocialReason.setBounds(0, 259, 600, 17);
		panel.add(NameOrSocialReason);
		
		Client client = clientService.getClientById(this.compte.getIdClient());
		JLabel NameOrSocialInput = new JLabel(client.getRaisonSocial() == null || client.getRaisonSocial().isEmpty() || client.getRaisonSocial().isBlank() ? client.getLibelleClient() : client.getRaisonSocial());
		NameOrSocialInput.setHorizontalAlignment(SwingConstants.CENTER);
		NameOrSocialInput.setBounds(-2, 304, 602, 14);
		panel.add(NameOrSocialInput);
		
		JLabel MontantTransac = new JLabel("Montant");
		MontantTransac.setHorizontalAlignment(SwingConstants.CENTER);
		MontantTransac.setFont(new Font("Tahoma", Font.PLAIN, 18));
		MontantTransac.setBounds(-2, 411, 602, 17);
		panel.add(MontantTransac);
		
		MontantTextField = new JTextField();
		MontantTextField.setBounds(189, 445, 228, 29);
		panel.add(MontantTextField);
		MontantTextField.setColumns(10);
		MontantTextField.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	        	Validate.setEnabled(true);
	            String value = MontantTextField.getText();
	            int l = value.length();
	            if (l > 0 && !compteService.patternMatches(value, "[+-]?([0-9]*[.])?[0-9]+")) {
	            	Validate.setEnabled(false);
	            }
	         }
	      });
		
		Validate = new JButton("Valider");
		Validate.setBounds(159, 522, 105, 35);
		panel.add(Validate);
		Validate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				AccountDepositOrWithdraw frame = (AccountDepositOrWithdraw) SwingUtilities.getWindowAncestor(component);
				if(MontantTextField.getText().isEmpty()|| MontantTextField.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(), 
					"Saisi un montant pauvre con !!",
       	         	" Erreur ",
       	         	JOptionPane.ERROR_MESSAGE);
				}else {
					if (compteService.updateDepositOrWithdraw(frame)){
						JOptionPane.showMessageDialog(getContentPane(), 
								"Tout s'est bien pass� Guignole",
			       	         	" Valider ",
			       	         	JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(getContentPane(), 
								"Ta encore fais de la merde bordel",
			       	         	" Try again ",
			       	         	JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		JButton Cancel = new JButton("Annuler");
		Cancel.setBounds(355, 522, 105, 35);
		panel.add(Cancel);
		
		JLabel SoldeInput = new JLabel(String.valueOf(this.compte.getSolde()));
		SoldeInput.setHorizontalAlignment(SwingConstants.CENTER);
		SoldeInput.setBounds(-2, 369, 602, 14);
		panel.add(SoldeInput);
		
		JLabel Solde = new JLabel("Solde");
		Solde.setHorizontalAlignment(SwingConstants.CENTER);
		Solde.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Solde.setBounds(-10, 341, 600, 17);
		panel.add(Solde);
		
		JLabel Bgimage = new JLabel("New label");
		Bgimage.setIcon(new ImageIcon(CreationClientForm.class.getResource("/images/BGimage.jpg")));
		Bgimage.setBounds(0, 0, 1184, 761);
		getContentPane().add(Bgimage);
		setVisible(true);
	}
}

	