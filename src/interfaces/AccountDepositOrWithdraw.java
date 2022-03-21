package interfaces;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AccountDepositOrWithdraw extends JFrame {
	private JTextField MontantTextField;
	private JLabel AccountNumber;
	private JLabel NameOrSocialInput;
	private JLabel SoldeIntput;
	
	
	public JTextField getMontantTextField() {
		return MontantTextField;
	}

	public void setMontantTextField(
			JTextField montantTextField) {
		MontantTextField = montantTextField;
	}

	public void
			setAccountNumber(JLabel accountNumber) {
		AccountNumber = accountNumber;
	}

	public void setNameOrSocialInput(
			JLabel nameOrSocialInput) {
		NameOrSocialInput = nameOrSocialInput;
	}

	public void setSoldeIntput(JLabel soldeIntput) {
		SoldeIntput = soldeIntput;
	}

		
	public AccountDepositOrWithdraw() {
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
		
		JLabel lblNewLabel = new JLabel("D\u00E9bit/Retrait");
		lblNewLabel.setBounds(204, 35, 202, 37);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel.add(lblNewLabel);
		
		JLabel NumeroDeCompte = new JLabel("Num\u00E9ro de compte");
		NumeroDeCompte.setHorizontalAlignment(SwingConstants.CENTER);
		NumeroDeCompte.setBounds(-2, 174, 602, 17);
		NumeroDeCompte.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(NumeroDeCompte);
		
		JLabel AccountNumber = new JLabel("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		AccountNumber.setHorizontalAlignment(SwingConstants.CENTER);
		AccountNumber.setBounds(0, 221, 601, 14);
		panel.add(AccountNumber);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("D\u00E9biter");
		rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnNewRadioButton_1.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton_1.setBounds(143, 111, 153, 29);
		panel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnComptepargne_1 = new JRadioButton("Cr\u00E9diter");
		rdbtnComptepargne_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnComptepargne_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnComptepargne_1.setBackground(Color.LIGHT_GRAY);
		rdbtnComptepargne_1.setBounds(325, 111, 157, 29);
		panel.add(rdbtnComptepargne_1);
		
		JLabel NameOrSocialReason = new JLabel("Raison sociale / Libell\u00E9 client");
		NameOrSocialReason.setHorizontalAlignment(SwingConstants.CENTER);
		NameOrSocialReason.setFont(new Font("Tahoma", Font.PLAIN, 18));
		NameOrSocialReason.setBounds(0, 259, 600, 17);
		panel.add(NameOrSocialReason);
		
		JLabel NameOrSocialInput = new JLabel("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
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
		
		JButton Validate = new JButton("Valider");
		Validate.setBounds(159, 522, 105, 35);
		panel.add(Validate);
		
		JButton Cancel = new JButton("Annuler");
		Cancel.setBounds(355, 522, 105, 35);
		panel.add(Cancel);
		
		JLabel SoldeInput = new JLabel("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
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
	}
}
