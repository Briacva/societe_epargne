package interfaces;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mysql.cj.xdevapi.Statement;

import services.ListeClientService;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListeClientForm extends JFrame {
	
	private JTable ClientTable;
	private final JPanel panelHeader = new JPanel();
	
	public ListeClientForm() {
		try 
		  {
		      String url = "jdbc:mysql://localhost:3306/societe_epargne";
		      String user = "root";
		      String password = "";
		    
		      Connection con = DriverManager.getConnection(url, user, password);
		    
		      String query = "SELECT * FROM client";
		      
		      PreparedStatement stm = con.prepareStatement(query);
		      ResultSet res = stm.executeQuery();
		    
		      String columns[] = { "id", "raisonSociale", "libelleClient", "numeroTel", "mail", "adresse", "civilite", "dateNaisssance" };
		      String data[][] = new String[8][3];
		    
		      int i = 0;
		      while (res.next()) {
		        int id = res.getInt("ID");
		        String raisonSociale = res.getString("raisonSociale");
		        String libelleClient = res.getString("libelleClient");
		        data[i][0] = id + "";
		        data[i][1] = raisonSociale;
		        data[i][2] = libelleClient;
		        i++;
		      }
		    
		      DefaultTableModel model = new DefaultTableModel(data, columns);
		      JTable table = new JTable(model);
		      table.setShowGrid(true);
		      table.setShowVerticalLines(true);
		      JScrollPane pane = new JScrollPane(table);
		      JFrame f = new JFrame("Remplir JTable a partir d'une BDD");
		      JPanel panel = new JPanel();
		      panel.add(pane);
		      f.add(panel);
		      f.setSize(500, 250);
		      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      f.setVisible(true);
		    
		    } catch(SQLException e) {
		      e.printStackTrace();
		    }
		
//		ListeClientService listeClientService = new ListeClientService();
//		
//		setTitle("Liste des clients");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setResizable(false);
//		setPreferredSize(new Dimension(1200, 700));
//		getContentPane().setLayout(null);
//		
//		JPanel panel = new JPanel();
//		panel.setBounds(0, 0, 1200, 700);
//		getContentPane().add(panel);
//		panel.setLayout(null);
//		
//		panelHeader.setBackground(new Color(57, 91, 100));
//		panelHeader.setBounds(0, 0, 1200, 96);
//		panel.add(panelHeader);
//		panelHeader.setLayout(null);
//		
//		JButton btnAddClient = new JButton("Ajouter un client");
//		btnAddClient.setForeground(new Color(57, 91, 100));
//		btnAddClient.setFont(new Font("Tahoma", Font.BOLD, 14));
//		btnAddClient.setBounds(92, 35, 154, 34);
//		panelHeader.add(btnAddClient);
		
		//ClientTable = new JTable();
		//ClientTable.setModel(new DefaultTableModel(
//			new Object[][] {
//				{"Raison sociale", "Libell\u00E9 Client", "Num\u00E9ro de t\u00E9l\u00E9phone", "Mail", "Adresse", "Civilit\u00E9", "Date de Naissance"},
//			},
//			new String[] {
//				"Raison sociale", "Libell\u00E9 Client", "Num\u00E9ro de t\u00E9l\u00E9phone", "Mail", "Adresse", "Civilit\u00E9", "Date de Naissance"
//			}
//		));
		//JTableHeader Theader = table.getTableHeader();
		//Theader.setBackground(Color.red);
		
//		ClientTable.setBounds(111, 131, 940, 504);
//		panel.add(ClientTable);
		
		
		pack();
	}
}
