package view;

import java.awt.EventQueue;

import javax.swing.JDialog;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Color;

public class Home extends JDialog {

	//Construtor
	public Home(){
		getContentPane().setBackground(new Color(192, 192, 192));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/logo.png")));
		setTitle("Home");
		setBounds(550,250,477,340);
		setResizable(false);
		getContentPane().setLayout(null);

		JButton btnUser = new JButton("");
		btnUser.setBorderPainted(false);
		btnUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUser.setIcon(new ImageIcon(Home.class.getResource("/img/user.png")));
		btnUser.setBounds(10, 11, 128, 99);
		getContentPane().add(btnUser);
		
		btnUser.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Funcionarios funcionarios = new Funcionarios (); 
				funcionarios.setVisible(true);
			}
		});
		
		
		JButton btnRoom = new JButton("");
		btnRoom.setBorderPainted(false);
		btnRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRoom.setIcon(new ImageIcon(Home.class.getResource("/img/room.png")));
		btnRoom.setBounds(169, 11, 128, 99);
		getContentPane().add(btnRoom);
		
		btnRoom.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Salas salas = new Salas (); 
				salas.setVisible(true);
			}
		});

		JButton btnReserve = new JButton("");
		btnReserve.setBorderPainted(false);
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReserve.setIcon(new ImageIcon(Home.class.getResource("/img/reserve.png")));
		btnReserve.setBounds(328, 11, 123, 99);
		getContentPane().add(btnReserve);
		
		btnReserve.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Reservas reservas = new Reservas (); 
				reservas.setVisible(true);
			}
		});

	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home dialog = new Home();
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