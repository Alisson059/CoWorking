package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.DAO;

import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JComboBox;


public class Funcionarios extends JDialog {
	private JTextField inputNome;
	private JTextField inputEmail;
	private JTextField inputSenha;
	private JTextField inputLogin;
	
	public Funcionarios() {
		setBounds(550, 250, 477, 340);
		setTitle("Funcionarios");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Funcionarios.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		
		JLabel nomeFunc = new JLabel("Nome:");
		nomeFunc.setBounds(31, 38, 46, 14);
		getContentPane().add(nomeFunc);
		
		JLabel emailFunc = new JLabel("Email:");
		emailFunc.setBounds(31, 121, 46, 14);
		getContentPane().add(emailFunc);
		
		JLabel loginFunc = new JLabel("Login:");
		loginFunc.setBounds(257, 121, 46, 14);
		getContentPane().add(loginFunc);
		
		JLabel senhaFunc = new JLabel("Senha:");
		senhaFunc.setBounds(31, 196, 46, 14);
		getContentPane().add(senhaFunc);
		
		JLabel perfilFunc = new JLabel("Perfil:");
		perfilFunc.setBounds(257, 196, 46, 14);
		getContentPane().add(perfilFunc);
		
		inputNome = new JTextField();
		inputNome.setBounds(71, 35, 358, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(71, 118, 137, 20);
		getContentPane().add(inputEmail);
		inputEmail.setColumns(10);
		
		inputSenha = new JTextField();
		inputSenha.setBounds(71, 193, 137, 20);
		getContentPane().add(inputSenha);
		inputSenha.setColumns(10);
		
		inputLogin = new JTextField();
		inputLogin.setBounds(292, 118, 137, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);
		
		
		JLabel imgUpdate = new JLabel("");
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		imgUpdate.setBounds(228, 234, 64, 56);
		getContentPane().add(imgUpdate);
		
		JLabel imgDelete = new JLabel("");
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		imgDelete.setBounds(365, 234, 64, 56);
		getContentPane().add(imgDelete);
		
		inputPerfil = new JComboBox();
		inputPerfil.setBounds(292, 192, 137, 22);
		getContentPane().add(inputPerfil);
		
		JLabel imgCreate = new JLabel("");
		imgCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
		imgCreate.setBounds(72, 234, 64, 56);
		getContentPane().add(imgCreate);
	}
	
DAO dao = new DAO();
private JComboBox inputPerfil;


	
	private void adicionarFuncionario() {
		String create = "insert into funcionario (nomeFunc, login, senha, perfil, email) values( ? , ? , md5(?), ?, ? );";
		
		try {
			//Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();
			
			//Preparar a execusão do script SQL
			PreparedStatement executarSQl = conexaoBanco.prepareStatement(create);
			
			//Subtituir os ponto de interrogação pelo conteúdo das caixas de texto (inputs)
			executarSQl.setString(1, nomeFunc.getText());
			executarSQl.setString(2,LoginFunc.getText());
			executarSQl.setString(3,senhaFunc.getText());
			//Trocar o componente do perfil
			executarSQl.setString(5,emailFunc.getText());
			
		}
		
		catch (Exception e) {
			
		}
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
