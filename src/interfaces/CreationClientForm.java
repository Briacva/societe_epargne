package interfaces;

import services.ClientService;
import javax.swing.*;
import javax.swing.text.*;
import main.DatabaseConnexion;
import models.Client;
import models.TypeCivilite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class CreationClientForm extends JFrame {

	private JTextField socialReason;
	private JTextField NameText;
	private JTextField firstnameText;
	private JTextField EmailText;
	private JComboBox<String> civiliteText;
	private JTextField adressText;
	private JTextField phoneTextField;

	public String toStringLibelleClient(){
		return this.NameText.getText()+" "+ this.firstnameText.getText();
	}
	
	public CreationClientForm() {
		getContentPane().setForeground(new Color(0, 255, 0));
		setTitle("Societe d'epargne");
		setSize(1200,800);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTION BANCAIRE");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 22));
		lblNewLabel.setBounds(260, 80, 680, 35);
		getContentPane().add(lblNewLabel);
		
		
		socialReason = new JTextField(5);
		socialReason.setForeground(Color.WHITE);
		socialReason.setBorder(null);
		socialReason.setBackground(SystemColor.desktop);
		socialReason.setBounds(575, 149, 300, 30);
		getContentPane().add(socialReason);
		socialReason.setDocument(new LimitJTextField(50));
		
		JLabel lblNewLabel_1_1 = new JLabel("Raison sociale :");
		lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(326, 149, 201, 30);
		getContentPane().add(lblNewLabel_1_1);
		
		NameText = new JTextField();
		NameText.setForeground(Color.WHITE);
		NameText.setBorder(null);
		NameText.setBackground(SystemColor.desktop);
		NameText.setBounds(575, 280, 300, 30);
		getContentPane().add(NameText);
		NameText.setDocument(new LimitJTextField(50));
		
		JLabel lblNewLabel_1_2 = new JLabel("Nom :");
		lblNewLabel_1_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(326, 280, 201, 30);
		getContentPane().add(lblNewLabel_1_2);
		
		firstnameText = new JTextField();
		firstnameText.setForeground(Color.WHITE);
		firstnameText.setBorder(null);
		firstnameText.setBackground(SystemColor.desktop);
		firstnameText.setBounds(575, 339, 300, 30);
		getContentPane().add(firstnameText);
		firstnameText.setDocument(new LimitJTextField(50));
		
		JLabel lblNewLabel_1_3 = new JLabel("Prenom :");
		lblNewLabel_1_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_3.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_3.setBounds(326, 339, 201, 30);
		getContentPane().add(lblNewLabel_1_3);
		
		EmailText = new JTextField();
		EmailText.setForeground(Color.WHITE);
		EmailText.setBorder(null);
		EmailText.setBackground(SystemColor.desktop);
		EmailText.setBounds(575, 460, 300, 30);
		getContentPane().add(EmailText);
		EmailText.setDocument(new LimitJTextField(50));
		
		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setForeground(Color.DARK_GRAY);
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEmail.setBounds(326, 460, 201, 30);
		getContentPane().add(lblEmail);
		
		civiliteText = new JComboBox<String>();
		civiliteText.setForeground(Color.WHITE);
		civiliteText.setBorder(null);
		civiliteText.setBackground(SystemColor.desktop);
		civiliteText.setBounds(575, 214, 300, 30);
		civiliteText.addItem("");
		civiliteText.addItem(TypeCivilite.HOMME.getType());
		civiliteText.addItem(TypeCivilite.FEMME.getType());
		getContentPane().add(civiliteText);
		
		JLabel lblNewLabel_1_5 = new JLabel("Civilite :");
		lblNewLabel_1_5.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_5.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_5.setBounds(326, 214, 201, 30);
		getContentPane().add(lblNewLabel_1_5);
		
		adressText = new JTextField();
		adressText.setForeground(Color.WHITE);
		adressText.setBorder(null);
		adressText.setBackground(SystemColor.desktop);
		adressText.setBounds(575, 516, 300, 60);
		getContentPane().add(adressText);
		adressText.setDocument(new LimitJTextField(50));
		
		JLabel lblNewLabel_1_6 = new JLabel("Adresse :");
		lblNewLabel_1_6.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_6.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_6.setBounds(326, 516, 201, 30);
		getContentPane().add(lblNewLabel_1_6);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setForeground(Color.WHITE);
		btnValider.setBackground(new Color(30, 125, 125));
		btnValider.setFont(new Font("Dialog", Font.BOLD, 12));
		btnValider.setBounds(300, 649, 150, 40);
		getContentPane().add(btnValider);
		btnValider.addActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                CreationClientForm frame = (CreationClientForm) SwingUtilities.getWindowAncestor(component);
                List<String> errors = ClientService.requireText(frame);
                
                if (!ClientService.patternMatches(frame.getEmailText().getText(), "^(.+)@(\\S+)$")) {
                	JOptionPane.showMessageDialog(getContentPane(), 
                	         "E-mail non valide",
                	         " Erreur ",
                	         JOptionPane.ERROR_MESSAGE);
                } else if  (errors.size() > 0) {
                	String error = errors.stream().collect(Collectors.joining(", "));
                  	JOptionPane.showMessageDialog(getContentPane(), 
               	         "Les champs "  + error +" " + " sont vides ! ",
               	         " Erreur ",
               	         JOptionPane.ERROR_MESSAGE);
                } else {
                	Client client = new Client(socialReason.getText(), toStringLibelleClient(), phoneTextField.getText() , EmailText.getText(), adressText.getText(), civiliteText.getSelectedItem().toString(),null, 1 );
                	DatabaseConnexion.Update(client);
                	ClientService.fieldReinitialization(frame);
                	ListeComptesForm list = new ListeComptesForm();
                	list.setVisible(true);
                	frame.dispose();
                	System.out.println("it's ok");
            	}
            }
        });
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        Component component = (Component) e.getSource();
		        CreationClientForm frame = (CreationClientForm) SwingUtilities.getWindowAncestor(component);
		        
		        ListeComptesForm liste = new ListeComptesForm();
		        liste.setVisible(true);
		        frame.dispose();
			}
		});
		btnAnnuler.setBackground(new Color(30, 125, 125));
		btnAnnuler.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAnnuler.setBounds(750, 649, 150, 40);
		getContentPane().add(btnAnnuler);
		
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
		panel.setBackground(new Color(0.60f,0.60f,0.56f,0.85f));
		panel.setBounds(260, 45, 680, 670);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		phoneTextField = new JTextField();
		phoneTextField.setForeground(Color.WHITE);
		phoneTextField.setBackground(SystemColor.desktop);
		phoneTextField.setBounds(314, 353, 300, 30);
		panel.add(phoneTextField);
		
		JButton btnReinitialiser = new JButton("R\u00E9initialiser");
		btnReinitialiser.setForeground(Color.WHITE);
		btnReinitialiser.setBackground(new Color(30, 125, 125));
		btnReinitialiser.setFont(new Font("Dialog", Font.BOLD, 12));
		btnReinitialiser.setBounds(270, 604, 150, 40);
		panel.add(btnReinitialiser);
		
		JLabel lblTelephone = new JLabel("Telephone :");
		lblTelephone.setBounds(63, 351, 201, 30);
		panel.add(lblTelephone);
		lblTelephone.setForeground(Color.DARK_GRAY);
		lblTelephone.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JSeparator separatorRaisonSociale = new JSeparator();
		separatorRaisonSociale.setForeground(new Color(0, 102, 102));
		separatorRaisonSociale.setBackground(new Color(0, 102, 102));
		separatorRaisonSociale.setBounds(67, 131, 70, 2);
		panel.add(separatorRaisonSociale);
		
		JSeparator separatorRaisonSociale_1 = new JSeparator();
		separatorRaisonSociale_1.setForeground(new Color(0, 102, 102));
		separatorRaisonSociale_1.setBackground(new Color(0, 102, 102));
		separatorRaisonSociale_1.setBounds(67, 195, 70, 2);
		panel.add(separatorRaisonSociale_1);
		
		JSeparator separatorRaisonSociale_2 = new JSeparator();
		separatorRaisonSociale_2.setForeground(new Color(0, 102, 102));
		separatorRaisonSociale_2.setBackground(new Color(0, 102, 102));
		separatorRaisonSociale_2.setBounds(67, 261, 70, 2);
		panel.add(separatorRaisonSociale_2);
		
		JSeparator separatorRaisonSociale_3 = new JSeparator();
		separatorRaisonSociale_3.setForeground(new Color(0, 102, 102));
		separatorRaisonSociale_3.setBackground(new Color(0, 102, 102));
		separatorRaisonSociale_3.setBounds(67, 320, 70, 2);
		panel.add(separatorRaisonSociale_3);
		
		JSeparator separatorRaisonSociale_4 = new JSeparator();
		separatorRaisonSociale_4.setForeground(new Color(0, 102, 102));
		separatorRaisonSociale_4.setBackground(new Color(0, 102, 102));
		separatorRaisonSociale_4.setBounds(67, 377, 70, 2);
		panel.add(separatorRaisonSociale_4);
		
		JSeparator separatorRaisonSociale_5 = new JSeparator();
		separatorRaisonSociale_5.setForeground(new Color(0, 102, 102));
		separatorRaisonSociale_5.setBackground(new Color(0, 102, 102));
		separatorRaisonSociale_5.setBounds(67, 441, 70, 2);
		panel.add(separatorRaisonSociale_5);
		
		JSeparator separatorRaisonSociale_6 = new JSeparator();
		separatorRaisonSociale_6.setForeground(new Color(0, 102, 102));
		separatorRaisonSociale_6.setBackground(new Color(0, 102, 102));
		separatorRaisonSociale_6.setBounds(67, 497, 70, 2);
		panel.add(separatorRaisonSociale_6);
		
		JSeparator separatorRaisonSociale_7 = new JSeparator();
		separatorRaisonSociale_7.setForeground(new Color(0, 102, 102));
		separatorRaisonSociale_7.setBackground(new Color(0, 102, 102));
		separatorRaisonSociale_7.setBounds(234, 70, 194, 2);
		panel.add(separatorRaisonSociale_7);
		
		
		btnReinitialiser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                CreationClientForm frame = (CreationClientForm) SwingUtilities.getWindowAncestor(component);
                
                ClientService.fieldReinitialization(frame);
            }
        });
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(CreationClientForm.class.getResource("/images/BGimage.jpg")));
		lblNewLabel_2.setBounds(0, 0, 1184, 761);
		getContentPane().add(lblNewLabel_2);
	}
	
	
	//limite la longueur des zones de textes en charact�res
	
	public class LimitJTextField extends PlainDocument 
	{
	   private int max;
	   LimitJTextField(int max) {
	      super();
	      this.max = max;
	   }
	   public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
	      if (text == null)
	         return;
	      if ((getLength() + text.length()) <= max) {
	         super.insertString(offset, text, attr);
	      }
	   }
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField getSocialReason() {
		return socialReason;
	}

	public JTextField getNameText() {
		return NameText;
	}

	public JTextField getFirstnameText() {
		return firstnameText;
	}

	public JTextField getEmailText() {
		return EmailText;
	}

	public JTextField getAdressText() {
		return adressText;
	}

	public JTextField getPhoneText() {
		return phoneTextField;
	}
	
	public JComboBox<String> getCiviliteText() {
		return civiliteText;
	}
}

