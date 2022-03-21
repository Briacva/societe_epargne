package interfaces;

import java.awt.Dimension;

import javax.swing.JFrame;

public class CreationClientForm extends JFrame {
	public CreationClientForm() {
		setTitle("Ouvrir un compte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setPreferredSize(new Dimension(1200, 700));
		getContentPane().setLayout(null);
	}

}
