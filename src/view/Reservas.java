package view;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JDialog;
import java.awt.Toolkit;

public class Reservas extends JDialog {
	public Reservas() {
		setBounds(550, 250, 477, 340);
		setTitle("Reservas");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/img/logo.png")));
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
