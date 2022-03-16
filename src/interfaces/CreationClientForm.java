package interfaces;
import javax.swing.JFrame;
import javax.swing.*;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;


public class CreationClientForm extends JFrame {
	
	
	public CreationClientForm() {
		getContentPane().setForeground(new Color(0, 255, 0));
		setTitle("Societe d'epargne");
		setSize(1200,800);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion Bancaire");
		lblNewLabel.setForeground(Color.decode("#395B64"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Ebrima", Font.BOLD, 32));
		lblNewLabel.setBounds(0, 80, 1184, 35);
		getContentPane().add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setBounds(575, 160, 300, 30);
		getContentPane().add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Numero de compte :");
		lblNewLabel_1.setForeground(Color.decode("#395B64"));
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD, 16));
		lblNewLabel_1.setBounds(326, 160, 201, 30);
		getContentPane().add(lblNewLabel_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(575, 225, 300, 30);
		getContentPane().add(textArea_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Raison sociale :");
		lblNewLabel_1_1.setForeground(Color.decode("#395B64"));
		lblNewLabel_1_1.setFont(new Font("Georgia", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(326, 225, 201, 30);
		getContentPane().add(lblNewLabel_1_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(575, 290, 300, 30);
		getContentPane().add(textArea_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nom :");
		lblNewLabel_1_2.setForeground(Color.decode("#395B64"));
		lblNewLabel_1_2.setFont(new Font("Georgia", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(326, 290, 201, 30);
		getContentPane().add(lblNewLabel_1_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(575, 355, 300, 30);
		getContentPane().add(textArea_3);
		
		JLabel lblNewLabel_1_3 = new JLabel("Prenom :");
		lblNewLabel_1_3.setForeground(Color.decode("#395B64"));
		lblNewLabel_1_3.setFont(new Font("Georgia", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(326, 355, 201, 30);
		getContentPane().add(lblNewLabel_1_3);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBounds(575, 420, 300, 30);
		getContentPane().add(textArea_4);
		
		JLabel lblNewLabel_1_4 = new JLabel("Telephone :");
		lblNewLabel_1_4.setForeground(Color.decode("#395B64"));
		lblNewLabel_1_4.setFont(new Font("Georgia", Font.BOLD, 16));
		lblNewLabel_1_4.setBounds(326, 420, 201, 30);
		getContentPane().add(lblNewLabel_1_4);
		
		JTextArea textArea_5 = new JTextArea();
		textArea_5.setBounds(575, 485, 300, 30);
		getContentPane().add(textArea_5);
		
		JLabel lblNewLabel_1_5 = new JLabel("Civilite :");
		lblNewLabel_1_5.setForeground(Color.decode("#395B64"));
		lblNewLabel_1_5.setFont(new Font("Georgia", Font.BOLD, 16));
		lblNewLabel_1_5.setBounds(326, 485, 201, 30);
		getContentPane().add(lblNewLabel_1_5);
		
		JTextArea textArea_6 = new JTextArea();
		textArea_6.setBounds(575, 550, 300, 60);
		getContentPane().add(textArea_6);
		
		JLabel lblNewLabel_1_6 = new JLabel("Adresse :");
		lblNewLabel_1_6.setForeground(Color.decode("#395B64"));
		lblNewLabel_1_6.setFont(new Font("Georgia", Font.BOLD, 16));
		lblNewLabel_1_6.setBounds(326, 550, 201, 30);
		getContentPane().add(lblNewLabel_1_6);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBackground(new Color(255, 255, 255));
		btnValider.setFont(new Font("Ebrima", Font.PLAIN, 14));
		btnValider.setBounds(300, 649, 150, 40);
		getContentPane().add(btnValider);
//		btnValider.addActionListener(new ActionListener() {
//
////			@Override
////			public void actionPerformed(ActionEvent e) {
////				
////				 public static boolean isValid(String email){  
////				        String emailFormate ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\."  
////				                + "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";  
////				          
////				        Pattern p = Pattern.compile(emailFormate);  
////				        if(email == null){  
////				        return false;  
////				        }   
////				        return p.matcher(email).matches();  
////				    }      
////				
////			}
//
//			
//		});
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBackground(new Color(255, 255, 255));
		btnAnnuler.setFont(new Font("Ebrima", Font.PLAIN, 14));
		btnAnnuler.setBounds(750, 649, 150, 40);
		getContentPane().add(btnAnnuler);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0.60f,0.60f,0.56f,0.85f));
		panel.setBounds(260, 45, 680, 670);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnReinitialiser = new JButton("R\u00E9initialiser");
		btnReinitialiser.setBackground(new Color(255, 255, 255));
		btnReinitialiser.setFont(new Font("Ebrima", Font.PLAIN, 14));
		btnReinitialiser.setBounds(270, 604, 150, 40);
		panel.add(btnReinitialiser);
		btnReinitialiser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                textArea_1.setText("");
                textArea_2.setText("");
                textArea_3.setText("");
                textArea_4.setText("");
                textArea_5.setText("");
                textArea_6.setText("");
			}
		});
		this.setVisible(true);
	}
}

//
//private static void clearFileds() {
//	JTextArea.setText("");
//	btnReinitialiser.clearSelection();
//	
//}

