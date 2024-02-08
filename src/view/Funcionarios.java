package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.Rectangle;

public class Funcionarios extends JDialog {
	public Funcionarios() {
		setBounds(550, 250, 477, 340);
		setTitle("Funcionarios");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Funcionarios.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
	}
			
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Login dialog = new Login();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					}
					catch (Exception e) {
						e.printStackTrace();

					}
				}

			});
	}

}
