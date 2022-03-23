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
import javax.swing.JSeparator;
import java.awt.SystemColor;

public class AccountDepositOrWithdraw extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField MontantTextField;
	private JLabel AccountNumber;
	private JLabel NameOrSocialInput;
	private JLabel SoldeInput;
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

	public void setAccountNumber(JLabel accountNumber) {
		AccountNumber = accountNumber;
	}

	public void setNameOrSocialInput(JLabel nameOrSocialInput) {
		NameOrSocialInput = nameOrSocialInput;
	}

	public void setSoldeIntput(JLabel soldeInput) {
		SoldeInput = soldeInput;
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
		setSize(1200, 800);
		setResizable(false);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0.60f, 0.60f, 0.56f, 0.85f));
		panel.setBounds(301, 89, 600, 600);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Débit/Crédit");
		lblNewLabel.setBounds(-2, 34, 602, 37);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel.add(lblNewLabel);

		JLabel NumeroDeCompte = new JLabel("Numéro de compte");
		NumeroDeCompte.setHorizontalAlignment(SwingConstants.LEFT);
		NumeroDeCompte.setBounds(42, 175, 193, 17);
		NumeroDeCompte.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(NumeroDeCompte);

		AccountNumber = new JLabel(String.valueOf(this.compte.getNumCompte()));
		AccountNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		AccountNumber.setBounds(325, 178, 228, 14);
		panel.add(AccountNumber);

		ButtonGroup radioButtonGroup = new ButtonGroup();
		rdbtndebit = new JRadioButton("Débiter", true);
		rdbtndebit.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtndebit.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtndebit.setBackground(Color.LIGHT_GRAY);
		rdbtndebit.setBounds(143, 111, 153, 29);
		panel.add(rdbtndebit);

		rdbtnCredit = new JRadioButton("Créditer");
		rdbtnCredit.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnCredit.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnCredit.setBackground(Color.LIGHT_GRAY);
		rdbtnCredit.setBounds(325, 111, 157, 29);
		panel.add(rdbtnCredit);
		radioButtonGroup.add(rdbtnCredit);
		radioButtonGroup.add(rdbtndebit);

		JLabel NameOrSocialReason = new JLabel("Raison sociale / Libellé client");
		NameOrSocialReason.setHorizontalAlignment(SwingConstants.LEFT);
		NameOrSocialReason.setFont(new Font("Tahoma", Font.PLAIN, 18));
		NameOrSocialReason.setBounds(42, 259, 263, 17);
		panel.add(NameOrSocialReason);

		Client client = clientService.getClientById(this.compte.getIdClient());
		NameOrSocialInput = new JLabel(client.getRaisonSociale() == null || client.getRaisonSociale().isEmpty()
				|| client.getRaisonSociale().isBlank() ? client.getLibelleClient() : client.getRaisonSociale());
		NameOrSocialInput.setHorizontalAlignment(SwingConstants.RIGHT);
		NameOrSocialInput.setBounds(325, 259, 228, 14);
		panel.add(NameOrSocialInput);

		JLabel MontantTransac = new JLabel("Montant");
		MontantTransac.setHorizontalAlignment(SwingConstants.LEFT);
		MontantTransac.setFont(new Font("Tahoma", Font.PLAIN, 18));
		MontantTransac.setBounds(42, 440, 147, 17);
		panel.add(MontantTransac);

		MontantTextField = new JTextField();
		MontantTextField.setForeground(Color.WHITE);
		MontantTextField.setBackground(SystemColor.desktop);
		MontantTextField.setBounds(325, 435, 228, 29);
		MontantTextField.setBorder(null);
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
		Validate.setForeground(Color.WHITE);
		Validate.setBackground(new Color(30, 125, 125));
		Validate.setBounds(159, 522, 105, 35);
		panel.add(Validate);
		Validate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountDepositOrWithdraw frame = getFrame();
				
				if (MontantTextField.getText().isEmpty() || MontantTextField.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(), "Saisi un montant pauvre con !!", " Erreur ",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (compteService.updateDepositOrWithdraw(frame)) {
						JOptionPane.showMessageDialog(getContentPane(), "Tout s'est bien passé Guignole", " Valider ",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "T'as encore fait de la merde bordel",
								" Try again ", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		JButton Cancel = new JButton("Retour");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountDepositOrWithdraw frame = getFrame();
				ListeComptesForm list = new ListeComptesForm();
				list.setVisible(true);
				frame.dispose();
			}
		});
		Cancel.setBackground(new Color(30, 125, 125));
		Cancel.setForeground(Color.WHITE);
		Cancel.setBounds(355, 522, 105, 35);
		panel.add(Cancel);

		SoldeInput = new JLabel(String.valueOf(this.compte.getSolde()));
		SoldeInput.setHorizontalAlignment(SwingConstants.RIGHT);
		SoldeInput.setBounds(325, 369, 228, 15);
		panel.add(SoldeInput);

		JLabel Solde = new JLabel("Solde actuel :");
		Solde.setHorizontalAlignment(SwingConstants.LEFT);
		Solde.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Solde.setBounds(42, 369, 147, 17);
		panel.add(Solde);

		JSeparator separatorTitle = new JSeparator();
		separatorTitle.setForeground(new Color(0, 102, 102));
		separatorTitle.setBackground(new Color(0, 102, 102));
		separatorTitle.setBounds(226, 71, 153, 2);
		panel.add(separatorTitle);

		JSeparator separatorNumCompte = new JSeparator();
		separatorNumCompte.setForeground(new Color(0, 102, 102));
		separatorNumCompte.setBackground(new Color(0, 102, 102));
		separatorNumCompte.setBounds(45, 195, 72, 2);
		panel.add(separatorNumCompte);

		JSeparator separatorRaisonSociale = new JSeparator();
		separatorRaisonSociale.setForeground(new Color(0, 102, 102));
		separatorRaisonSociale.setBackground(new Color(0, 102, 102));
		separatorRaisonSociale.setBounds(42, 281, 72, 2);
		panel.add(separatorRaisonSociale);

		JSeparator separatorMontant = new JSeparator();
		separatorMontant.setForeground(new Color(0, 102, 102));
		separatorMontant.setBackground(new Color(0, 102, 102));
		separatorMontant.setBounds(42, 458, 72, 2);
		panel.add(separatorMontant);

		JSeparator separatorSolde = new JSeparator();
		separatorSolde.setForeground(new Color(0, 102, 102));
		separatorSolde.setBackground(new Color(0, 102, 102));
		separatorSolde.setBounds(42, 388, 72, 2);
		panel.add(separatorSolde);

		JLabel Bgimage = new JLabel("New label");
		Bgimage.setIcon(new ImageIcon(CreationClientForm.class.getResource("/images/BGimage.jpg")));
		Bgimage.setBounds(0, 0, 1184, 761);
		getContentPane().add(Bgimage);
		setVisible(true);
	}
	
	public AccountDepositOrWithdraw getFrame() {
		return this;
	}
}
