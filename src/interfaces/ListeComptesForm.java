package interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Client;
import models.Compte;
import models.TypeCompte;
import services.ListeComptesService;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ListeComptesForm extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel subPanel = new JPanel();
	private ListeComptesService listeClientService;
	private JButton btnCreditOrDebit;
	
	public ListeComptesForm() {
		//instanciation des services
		listeClientService = new ListeComptesService();
		
		//r�cup�ration de la liste des compte dans service
		List<Compte> listComptes = listeClientService.getComptes();	
		
		//Nom des colonnes du tableau liste de compte
		String columns[] = { "Identifiant", "Raison sociale", "Numéro de compte", "Type de compte", "Solde", "Numero de téléphone", "Mail", "Adresse", "civilité", "Date de naisssance" };
		
		//donn�e du tableau liste de compte // tableau 2d // affichage dynamique 
		Object data[][] = new Object[listComptes.size()][columns.length];
		for(int i = 0; i < listComptes.size(); i++){
			//r�cup�ration de la liste des clients dans liste des comptes
			Client client = listComptes.get(i).getClient(listComptes.get(i));
			data[i][0] = listComptes.get(i).getId();
			data[i][1] = client.getLibelleClient() == null || client.getLibelleClient().isEmpty() || client.getLibelleClient().isBlank() ? client.getRaisonSociale() : client.getLibelleClient();
			data[i][2] = listComptes.get(i).getNumCompte();
			data[i][3] = listComptes.get(i).getTypeCompte() ? TypeCompte.EPARGNE.getLibelleType() : TypeCompte.COURANT.getLibelleType();
			data[i][4] = listComptes.get(i).getSolde();
			data[i][5] = client.getNumeroTel();
			data[i][6] = client.getMail();
			data[i][7] = client.getAdresse();
			data[i][8] = client.getCivilite();
			data[i][9] = client.getDateNaisssance();
		}
		
		setTitle("Liste des comptes clients");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setPreferredSize(new Dimension(1200, 700));
		getContentPane().setLayout(null);
		  
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 700);
		getContentPane().add(panel);
		panel.setLayout(null);
		subPanel.setForeground(new Color(175, 238, 238));
		

		subPanel.setBackground(new Color(0.60f,0.60f,0.56f,0.85f));
		subPanel.setLayout(null);
		subPanel.setBounds(70, 47, 1028, 595);
		panel.add(subPanel);
        
        JTable table = new JTable(data, columns) {
            /**
        	 * 
        	 */
        	private static final long serialVersionUID = 1L;

        	@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        //Selection d'un �l�ment dans un tableau
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                btnCreditOrDebit.setEnabled(true);
            }
        });
        
        //permet d'avoir la scroll barre
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 91, 1004, 426);
        subPanel.add(scrollPane);
        
		JButton btnAddClient = new JButton("Ajouter un client");
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        ListeComptesForm frame = getFrame();
		        
				CreationClientForm createClient = new CreationClientForm();
				createClient.setVisible(true);
				frame.dispose();
			}
		});
		
		btnAddClient.setBackground(new Color(30, 125, 125));
		btnAddClient.setForeground(Color.WHITE);
		btnAddClient.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddClient.setBounds(52, 549, 191, 34);
		subPanel.add(btnAddClient);
		
        JButton btnAjouterCompte = new JButton("Ajouter un compte");
        btnAjouterCompte.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ListeComptesForm frame = getFrame();
		        
        		OuvrirCompteForm addCompte = new OuvrirCompteForm();
        		addCompte.setVisible(true);
        		
        		frame.dispose();
        	}
        });
        
        btnAjouterCompte.setBackground(new Color(30, 125, 125));
        btnAjouterCompte.setForeground(Color.WHITE);
        btnAjouterCompte.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAjouterCompte.setBounds(287, 549, 191, 34);
        subPanel.add(btnAjouterCompte);
        
        JButton btnTransfertFond = new JButton("Transférer des fonds");
        btnTransfertFond.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
		        ListeComptesForm frame = getFrame();
		        
        		TransfererForm transfertFonds = new TransfererForm();
        		transfertFonds.setVisible(true);
        		
        		frame.dispose();
        	}
        });
        
        btnTransfertFond.setBackground(new Color(30, 125, 125));
        btnTransfertFond.setForeground(Color.WHITE);
        btnTransfertFond.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnTransfertFond.setBounds(543, 549, 213, 34);
        subPanel.add(btnTransfertFond);
        
        btnCreditOrDebit = new JButton("Créditer/Débiter");
        btnCreditOrDebit.setEnabled(false);
        btnCreditOrDebit.setBackground(new Color(30, 125, 125));
        btnCreditOrDebit.setForeground(Color.WHITE);
        btnCreditOrDebit.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCreditOrDebit.setBounds(816, 549, 165, 34);
        subPanel.add(btnCreditOrDebit);
        
        JLabel lblNewLabel = new JLabel("LISTE DES COMPTES");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(51, 51, 51));
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 22));
        lblNewLabel.setBounds(0, 0, 1028, 47);
        subPanel.add(lblNewLabel);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 102, 102));
        separator.setBackground(new Color(0, 102, 102));
        separator.setBounds(417, 42, 191, 2);
        subPanel.add(separator);
        
        JLabel lbllblBackground = new JLabel("");
        lbllblBackground.setIcon(new ImageIcon(ListeComptesForm.class.getResource("/images/backgroundAccountManagement.jpg")));
        lbllblBackground.setBounds(0, 0, 1190, 663);
        panel.add(lbllblBackground);
		pack();
	}

	public ListeComptesForm getFrame() {
		return this;
	}
	
	public ListeComptesService getListeClientService() {
		return listeClientService;
	}
}
