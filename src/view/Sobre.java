package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Rectangle;


public class Sobre extends JDialog {
	public Sobre() {
		setTitle("Sobre");
		setBounds(new Rectangle(0, 0, 604, 370));
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		getContentPane().setBackground(new Color(128, 128, 128));
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setLayout(null);

		JLabel titulo = new JLabel("Sobre o software");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(219, 11, 138, 14);
		getContentPane().add(titulo);

		JLabel descricao1 = new JLabel("O software CoWorking trata-se de um protótipo cujo objetivo.");
		descricao1.setHorizontalAlignment(SwingConstants.CENTER);
		descricao1.setBounds(103, 81, 396, 14);
		getContentPane().add(descricao1);

		JLabel descricao2 = new JLabel("É possibilitar o gerenciamento de reserva de salas em um espaço colaborativo.");
		descricao2.setHorizontalAlignment(SwingConstants.CENTER);
		descricao2.setBounds(69, 107, 490, 29);
		getContentPane().add(descricao2);

		JLabel versao = new JLabel("Versão 1.0.0");
		versao.setHorizontalAlignment(SwingConstants.CENTER);
		versao.setBounds(245, 199, 102, 14);
		getContentPane().add(versao);

		JLabel atualizacao = new JLabel("Última atualização: 31/01/2024");
		atualizacao.setHorizontalAlignment(SwingConstants.CENTER);
		atualizacao.setBounds(190, 224, 202, 14);
		getContentPane().add(atualizacao);

		JLabel imgMIT = new JLabel("");
		imgMIT.setHorizontalAlignment(SwingConstants.CENTER);
		imgMIT.setIcon(new ImageIcon(Sobre.class.getResource("/img/mitLicense.png")));
		imgMIT.setBounds(495, 253, 64, 48);
		getContentPane().add(imgMIT);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
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