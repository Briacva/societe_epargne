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
import services.ListeClientService;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

public class ListeClientForm extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel panelHeader = new JPanel();
	private ListeClientService listeClientService;
	
	public ListeClientForm() {
		listeClientService = new ListeClientService();
		List<Client> listClients = listeClientService.getClients();	
		String columns[] = { "id", "raisonSociale", "libelleClient", "numeroTel", "mail", "adresse", "civilite", "dateNaisssance" };
		
		Object data[][] = new Object[listClients.size()][columns.length];
		for(int i = 0; i < listClients.size(); i++){
			data[i][0] = listClients.get(i).getId();
			data[i][1] = listClients.get(i).getRaisonSociale();
			data[i][2] = listClients.get(i).getLibelleClient();
			data[i][3] = listClients.get(i).getNumeroTel();
			data[i][4] = listClients.get(i).getMail();
			data[i][5] = listClients.get(i).getAdresse();
			data[i][6] = listClients.get(i).getCivilite();
			data[i][7] = listClients.get(i).getDateNaisssance();
		}
					    
		setTitle("Liste des clients");
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

	public ListeClientService getListeClientService() {
		return listeClientService;
	}
}
