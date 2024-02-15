package view;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.EventQueue;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import java.awt.Cursor;
import javax.swing.ImageIcon;

public class Login extends JDialog {
	private JTextField inputLogin;
	private JPasswordField inputSenha;
	private JLabel imgDatabase;
	
	public Login() {
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				statusConexãoBanco();
			}
		});
		
		setTitle("Login");
		setBounds(new Rectangle(500, 250, 460, 311));
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		
		JLabel txtLogin = new JLabel("Login\r\n");
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtLogin.setBounds(188, 59, 45, 15);
		getContentPane().add(txtLogin);
		
		JLabel txtSenha = new JLabel("Senha");
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSenha.setBounds(188, 140, 45, 15);
		getContentPane().add(txtSenha);
		
		inputLogin = new JTextField();
		inputLogin.setBounds(137, 90, 150, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(137, 166, 150, 20);
		getContentPane().add(inputSenha);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(165, 214, 89, 23);
		getContentPane().add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		
		JLabel tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		tituloLogin.setBounds(149, 11, 122, 14);
		getContentPane().add(tituloLogin);
		
		imgDatabase = new JLabel("");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(10, 202, 53, 59);
		getContentPane().add(imgDatabase);
		
		
		//Acessar o botão "Entrar" com tecla "Enter"
		
		getRootPane().setDefaultButton(btnLogin);
		
		
		//Validação dos campos utilizando a bibliteca Atxy2k
		
		//Validação do campo inputLogin
		RestrictedTextField	validarLogin = new RestrictedTextField(inputLogin, 
				"abcdefghijklmnopqrstuvwxyz0123456789_-.");
		
		
		
		//Determinar o uso de alguns caracteres especiais (_ - .)
		validarLogin.setOnlyCustomCharacters(true);
		
		
		
		//Limitar a somente 20 caracteres no campo login
		validarLogin.setLimit(20);
		
		
		//Validação do campo inputSenha 
		RestrictedTextField validarSenha = new RestrictedTextField(inputSenha);
		
		//Limitar a somete 15 caracteres no campo senha
		validarSenha.setLimit(15);
		
		//Desativar a tecla espaço no campo
		
		
	}
	
	DAO dao = new DAO();
	
	
	private void statusConexãoBanco() {
		try {
			Connection conexaoBanco = dao.conectar();
			
			if(conexaoBanco == null) {
				//Esccolher a imagem para quando não há conexão
				imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
			}
			
			else {
				//Troca a imagem se houver conexão
				imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOn.png")));
			}
			conexaoBanco.close();
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void logar() {
		String read = "select * from funcionario where login=? and senha=md5(?)";
		
		//Validação Login do usuário
		if (inputLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login do usuário obrigatório!");
			inputLogin.requestFocus();
		}
		
		
		//Validação Senha do usuário
		else if (inputSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Senha do usuário obrigatório!");
			inputSenha.requestFocus();
			
		}
		
		
		else {
		
		
		
		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execusão do script SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(read);

			// Atribuir valores de login e senha
			// Substituir as interrogações ? ? pelo conteúdo da caixa de texto (input)
			executarSQL.setString(1, inputLogin.getText());
			executarSQL.setString(2, inputSenha.getText());

			// Executar os comandos SQL e de acordo com o resultado liberar os recursos na
			// tela
			ResultSet resultadoExecucao = executarSQL.executeQuery();

			// Validação do funcionário (autenticação)
			// resultadoExecucao.next() significa que o login e a senha existem, ou seja,
			// correspondem

			if (resultadoExecucao.next()) {
				
				Home home = new Home ();
				home.setVisible(true);
				
				//Fechar a janela de Login assim que a janela home abrir (Automatocamente)
				dispose();
						
			}
			else {
				//Criar uma alerta (Pou_up) que informa ao usuário  que login e/ou senha estão invaálidos
				JOptionPane.showMessageDialog(null, "Login e/ou senha inválido(s)!");
				inputLogin.setText(null);
				inputSenha.setText(null);
				inputLogin.requestFocus();
			
			}
			
			
			
			
		conexaoBanco.close();	
			
		}
		
		
		catch(Exception e) {
			System.out.println(e);
		}
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
