package view;
 
import javax.swing.JDialog;
 
import java.awt.EventQueue;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

import javax.swing.JTable;
 
public class Funcionarios extends JDialog {
	private JTextField inputNome;
	private JTextField inputEmail;
	private JTextField inputLogin;
	private JPasswordField inputSenha;
 
	public Funcionarios() {
		setTitle("Funcionários");
		setResizable(false);
		setBounds(100, 100, 614, 395);
		//setBounds(550,250,564,395);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		JLabel nomeFunc = new JLabel("Nome:");
		nomeFunc.setBounds(24, 58, 46, 14);
		getContentPane().add(nomeFunc);
		
		JLabel loginFunc = new JLabel("Login:");
		loginFunc.setBounds(24, 200, 46, 14);
		getContentPane().add(loginFunc);
		
		JLabel senhaFunc = new JLabel("Senha:");
		senhaFunc.setBounds(299, 200, 46, 14);
		getContentPane().add(senhaFunc);
		
		JLabel emailFunc = new JLabel("E-mail:");
		emailFunc.setBounds(299, 231, 46, 14);
		getContentPane().add(emailFunc);
		
		JLabel perfilFunc = new JLabel("Perfil:");
		perfilFunc.setBounds(24, 231, 46, 14);
		getContentPane().add(perfilFunc);
		
		inputNome = new JTextField();
		inputNome.setBounds(74, 55, 479, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);
		
		inputNome.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				buscarFuncionarioNatabela();
			}
		});
		
		inputEmail = new JTextField();
		inputEmail.setColumns(10);
		inputEmail.setBounds(353, 228, 200, 20);
		getContentPane().add(inputEmail);
		
		inputLogin = new JTextField();
		inputLogin.setColumns(10);
		inputLogin.setBounds(74, 197, 200, 20);
		getContentPane().add(inputLogin);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(353, 197, 200, 20);
		getContentPane().add(inputSenha);
		
		inputPerfil = new JComboBox();
		inputPerfil.setModel(new DefaultComboBoxModel(new String[] {" ","Administrador", "Gerência", "Atendimento", "Suporte"}));
		inputPerfil.setBounds(74, 227, 106, 22);
		getContentPane().add(inputPerfil);
		
		btnCreate = new JButton("");
		btnCreate.setBorder(null);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
		btnCreate.setBounds(254, 286, 92, 59);
		getContentPane().add(btnCreate);
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFuncionario ();
			}
		});
		
		btnUpdate = new JButton("");
		btnUpdate.setBorder(null);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		btnUpdate.setBounds(353, 286, 83, 59);
		getContentPane().add(btnUpdate);
		
		btnDelete = new JButton("");
		btnDelete.setBorder(null);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		btnDelete.setBounds(446, 286, 83, 59);
		getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 74, 479, 48);
		getContentPane().add(scrollPane);
		
		tblFuncionarios = new JTable();
		scrollPane.setRowHeaderView(tblFuncionarios);
 
	}
	DAO dao = new DAO();
	private JComboBox inputPerfil;
	public JButton btnCreate;
	public JButton btnUpdate;
	public JButton btnDelete;
	private JTable tblFuncionarios;
	
	private void adicionarFuncionario () {
		String create = "insert into funcionario (nomeFunc, login, senha, Perfil, email) values (?, ?, md5(?), ?, ?)";
	try {
		// Estabelecer a conexao 
		Connection conexaoBanco = dao.conectar();
		
		//preparar a execução dos comandos SQL
		PreparedStatement executarSQL = conexaoBanco.prepareStatement(create);
		
		//Substituir os pontos de interrogação pelo conteudo das caixas de texto (inputs)
		executarSQL.setString(1, inputNome.getText());
		executarSQL.setString(2, inputLogin.getText());
		executarSQL.setString(3, inputSenha.getText());
		executarSQL.setString(4, inputPerfil.getSelectedItem().toString());
		executarSQL.setString(5, inputEmail.getText());
		
		//Executar os comandos SQL e inserir o funcionario no banco de dados
		executarSQL.executeUpdate();
		JOptionPane.showMessageDialog(null, "Usuario cadstrado com sucesso");
		limparCampos();
		conexaoBanco.close();
	}
	catch (SQLIntegrityConstraintViolationException error) {
		JOptionPane.showMessageDialog(null, "Login em uso. \nEscolha outro nome de usuário");
		limparCampos();
	}
	catch (Exception e) {
		System.out.println(e);
	}
}
	
	private void buscarFuncionarioNatabela() {
		
		String readTabela = "Select idFuncionario as ID, nomeFunc as Nome, email as Email from funcionario"
				+ " where nomeFunc like ?; ";
		
		try {
			
			//estabelecer a conexão
			Connection conexaoBanco = dao.conectar();
			
			//preparar a execução dos comandos SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readTabela);
			
			//Substiruit o ? pelo conteúdo da caixa de texto
			executarSQL.setString(1, inputNome.getText() + "%");
			
			//Executar o comando Sql
			ResultSet resultadoExecucao = executarSQL.executeQuery();
			
			//Exibir o resultado na tabela, utilização da biblioteca rs2xml para "popular"
			//a tabela
			tblFuncionarios.setModel(DbUtils.resultSetToTableModel(resultadoExecucao));
			
			conexaoBanco.close();
			
		} 
		
		
		catch (Exception e) {

		}
	}
	
	private void setarCaixasTexto() {
		
		//Criar uma variável para receber a linha tabela
		int setarLinha = tblFuncionarios.getSelectedRow();
	}
	
	
	
	private void limparCampos() {
		inputNome.setText(null);
		inputLogin.setText(null);
		inputSenha.setText(null);
		//inputPerfil.setSelectedItem(null);
		inputPerfil.setSelectedIndex(-1);
		inputEmail.setText(null);
		inputNome.requestFocus();
}	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funcionarios dialog = new Funcionarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
