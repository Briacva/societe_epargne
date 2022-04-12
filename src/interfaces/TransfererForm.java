/**
 * 
 */
package interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import services.CompteService;
import java.awt.Font;

/**
 * @author marvin
 *
 */
public class TransfererForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldMontant;
	private JComboBox<String> comboBoxCompteSource;
	private JComboBox<String> comboBoxCompteDestinataire;

	private Hashtable<Integer, String> listCompteSource = new Hashtable<Integer, String>();
	private Hashtable<Integer, String> listCompteDestinataire = new Hashtable<Integer, String>();

	public TransfererForm() {
		// Instanciation du CompteService
		CompteService compteService = new CompteService();
		
		setTitle("Transférer des fonds");
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
		
		JLabel lblTitre = new JLabel("TRANSFERER DES FONDS");
		lblTitre.setFont(new Font("Dialog", Font.BOLD, 22));
		lblTitre.setBounds(168, 60, 328, 24);
		subPanelForm.add(lblTitre);
		
		JSeparator separatorTitre = new JSeparator();
		separatorTitre.setBackground(SystemColor.desktop);
		separatorTitre.setForeground(new Color(0, 102, 102));
		separatorTitre.setBounds(214, 90, 222, 15);
		subPanelForm.add(separatorTitre);
		
		JLabel lblMontant = new JLabel("Montant :");
		lblMontant.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMontant.setBackground(SystemColor.desktop);
		lblMontant.setBounds(284, 402, 96, 24);
		subPanelForm.add(lblMontant);
		
		JSeparator separatorMontant = new JSeparator();
		separatorMontant.setBackground(SystemColor.desktop);
		separatorMontant.setForeground(new Color(0, 102, 102));
		separatorMontant.setBounds(275, 428, 96, 15);
		subPanelForm.add(separatorMontant);
		
		textFieldMontant = new JTextField();
		textFieldMontant.setForeground(Color.WHITE);
		textFieldMontant.setColumns(10);
		textFieldMontant.setBackground(new Color(51, 102, 102));
		textFieldMontant.setBounds(150, 457, 370, 33);
		textFieldMontant.setBorder(null);
		subPanelForm.add(textFieldMontant);
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		
		JButton btnCreer = new JButton("Créer");
		btnCreer.setForeground(new Color(255, 255, 255));
		btnCreer.setBackground(new Color(30, 125, 125));
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Récupération de l'interface
		        TransfererForm frame = getFrame();
		        
		        // alimentation du tableau des erreurs
		        Hashtable<String, List<String>> errors = compteService.checkFields(null, frame);
		        
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
                }
                else {
                	//vérifier que le champ taux d'intérêt est pas supérieur à 100 
                	if(compteService.createTransfert(frame)){
                    	JOptionPane.showMessageDialog(getContentPane(), 
  		                      "Le transfert a bien été effectué !",
  		                      "Information",
  		                      JOptionPane.INFORMATION_MESSAGE);
                    	
                    	compteService.fieldReinitialization(null, frame);
                	}else {
                    	JOptionPane.showMessageDialog(getContentPane(), 
    		                      "Une erreur s'est produite, le transfert n'a pas été enregistré !",
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
		        TransfererForm frame = getFrame();
		        
		        compteService.fieldReinitialization(null, frame);
			}
		});
		
		btnReinitialiser.setBounds(263, 541, 149, 33);
		subPanelForm.add(btnReinitialiser);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransfererForm frame = getFrame();
				
				ListeComptesForm liste = new ListeComptesForm();
				liste.setVisible(true);
				
				frame.dispose();
			}
		});
		btnRetour.setForeground(new Color(255, 255, 255));
		btnRetour.setBackground(new Color(30, 125, 125));
		btnRetour.setBounds(48, 541, 133, 33);
		subPanelForm.add(btnRetour);
		
		comboBoxCompteSource = new JComboBox<String>();
		comboBoxCompteSource.setForeground(Color.WHITE);
		comboBoxCompteSource.setBackground(SystemColor.desktop);
		comboBoxCompteSource.setBounds(150, 197, 370, 33);
		comboBoxCompteSource.addItem("");
		listCompteSource.put(-1, "");
		
		subPanelForm.add(comboBoxCompteSource);
		
		JLabel lblCompteSource = new JLabel("Sélectionnez le compte source :");
		lblCompteSource.setBounds(150, 154, 230, 24);
		subPanelForm.add(lblCompteSource);
		
		JSeparator separatorCompteSource = new JSeparator();
		separatorCompteSource.setForeground(new Color(0, 102, 102));
		separatorCompteSource.setBackground(SystemColor.desktop);
		separatorCompteSource.setBounds(150, 177, 60, 15);
		subPanelForm.add(separatorCompteSource);
		
		JLabel lblCompteDestinataire = new JLabel("Sélectionnez le compte destinataire :");
		lblCompteDestinataire.setBounds(150, 283, 276, 24);
		subPanelForm.add(lblCompteDestinataire);
		
		JSeparator separatorCompteDestinataire = new JSeparator();
		separatorCompteDestinataire.setForeground(new Color(0, 102, 102));
		separatorCompteDestinataire.setBackground(SystemColor.desktop);
		separatorCompteDestinataire.setBounds(151, 307, 60, 15);
		subPanelForm.add(separatorCompteDestinataire);
		
		comboBoxCompteDestinataire = new JComboBox<String>();
		comboBoxCompteDestinataire.setForeground(Color.WHITE);
		comboBoxCompteDestinataire.setBackground(SystemColor.desktop);
		comboBoxCompteDestinataire.setBounds(150, 319, 370, 33);
		comboBoxCompteDestinataire.addItem("");
		listCompteDestinataire.put(-1, "");
		
		subPanelForm.add(comboBoxCompteDestinataire);
		
		if(compteService.fillListComptes(this)) {
           	JOptionPane.showMessageDialog(getContentPane(), 
                    "Une erreur s'est produite, vous ne pouvez pas transférer de fonds pour le moment !",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
           	this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
		pack();
	}
	
	public TransfererForm getFrame() {
		return this;
	}
	
	public JTextField getTextFieldMontant() {
		return textFieldMontant;
	}

	public JComboBox<String> getComboBoxCompteSource() {
		return comboBoxCompteSource;
	}

	public JComboBox<String> getComboBoxCompteDestinataire() {
		return comboBoxCompteDestinataire;
	}
	
	public Hashtable<Integer, String> getListCompteSource() {
		return listCompteSource;
	}

	public Hashtable<Integer, String> getListCompteDestinataire() {
		return listCompteDestinataire;
	}

}
