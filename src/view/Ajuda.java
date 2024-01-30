package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Ajuda extends JDialog {
	public Ajuda() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 46, 261);
		getContentPane().add(lblNewLabel);
	}

	public static void main(String[] args) {

		
	}

}
