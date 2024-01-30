package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class Sobre extends JDialog{
	public Sobre() {
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Sobre o software");
		titulo.setBounds(10, 63, 114, 28);
		getContentPane().add(titulo);
	}

	public static void main(String[] args) {
		
		
	}

}
