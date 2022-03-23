package interfaces;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Client;
import models.Compte;
import models.TypeCompte;
import services.ListeComptesService;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

public class ListeComptesForm extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel panelHeader = new JPanel();
	private ListeComptesService listeClientService;
	
	public ListeComptesForm() {
		listeClientService = new ListeComptesService();
		List<Compte> listComptes = listeClientService.getComptes();	
		String columns[] = { "Identifiant", "Raison sociale", "Numéro de Compte", "Type de Compte", "Solde", "Numero de téléphone", "mail", "adresse", "civilite", "dateNaisssance" };
		//List<Compte> -> compte.getClient.getAdress, etc...
		Object data[][] = new Object[listComptes.size()][columns.length];
		for(int i = 0; i < listComptes.size(); i++){
			Client client = listComptes.get(i).getClient(listComptes.get(i));
			data[i][0] = listComptes.get(i).getId();
			data[i][1] = client.getLibelleClient().isEmpty() || client.getLibelleClient().isBlank() || client.getLibelleClient() == null ? client.getRaisonSociale() : client.getLibelleClient();
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
		
		panelHeader.setBackground(new Color(57, 91, 100));
		panelHeader.setBounds(0, 0, 1200, 96);
		panel.add(panelHeader);
		panelHeader.setLayout(null);
		
		JButton btnAddClient = new JButton("Ajouter un client");
		btnAddClient.setForeground(new Color(57, 91, 100));
		btnAddClient.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddClient.setBounds(92, 35, 191, 34);
		panelHeader.add(btnAddClient);
        
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
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
            	int column = 0;
            	int row = table.getSelectedRow();
            	String value = table.getModel().getValueAt(row, column).toString();
                System.out.println(value);
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(126, 156, 929, 426);
        panel.add(scrollPane);
		pack();
	}

	public ListeComptesService getListeClientService() {
		return listeClientService;
	}
}
