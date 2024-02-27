package view;
 
import javax.swing.JDialog;
 
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SingleSelectionModel;
 
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.attribute.AclEntry;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
 
public class Salas extends JDialog {
	private JTextField inputOcup;
	public JButton btnCreate;
	public JButton btnUpdate;
	public JButton btnDelete;
	private JTable tblSalas;
	private JButton btnPesquisar;
	public JTextField inputID;
	private JComboBox inputCategoria;
	private JComboBox inputCod;
	private JTextField inputNum;
	private JComboBox inputAndar;
 
	public Salas() {
		setTitle("Salas");
		setResizable(false);
		setBounds(100, 100, 624, 437);
		// setBounds(550,250,564,395);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
 
		JLabel tipoSala = new JLabel("Categoria:");
		tipoSala.setBounds(10, 58, 60, 14);
		getContentPane().add(tipoSala);
 
		JLabel codSala = new JLabel("Codigo:");
		codSala.setBounds(24, 244, 46, 14);
		getContentPane().add(codSala);
 
		JLabel andarSala = new JLabel("Andar:");
		andarSala.setBounds(312, 244, 46, 14);
		getContentPane().add(andarSala);
 
		JLabel ocupSala = new JLabel("Ocupação Maxima:");
		ocupSala.setBounds(260, 276, 98, 14);
		getContentPane().add(ocupSala);
 
		JLabel numSala = new JLabel("Numero:");
		numSala.setBounds(20, 276, 60, 14);
		getContentPane().add(numSala);
 
		inputOcup = new JTextField();
		inputOcup.setColumns(10);
		inputOcup.setBounds(368, 273, 148, 20);
		getContentPane().add(inputOcup);
 
		btnCreate = new JButton("");
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorder(null);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setIcon(new ImageIcon(Salas.class.getResource("/img/create.png")));
		btnCreate.setBounds(312, 328, 92, 59);
		
		getContentPane().add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarSalas();
 
			}
		});
		
 
		btnUpdate = new JButton("");
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorder(null);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setIcon(new ImageIcon(Salas.class.getResource("/img/update.png")));
		btnUpdate.setBounds(414, 328, 83, 59);
		getContentPane().add(btnUpdate);
 
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSalas();
			}
		});
 
		btnDelete = new JButton("");
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorder(null);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setIcon(new ImageIcon(Salas.class.getResource("/img/delete.png")));
		btnDelete.setBounds(507, 328, 83, 59);
		getContentPane().add(btnDelete);
 
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarSalas();
			}
		});
 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 76, 494, 87);
		getContentPane().add(scrollPane);
 
		tblSalas = new JTable();
		scrollPane.setViewportView(tblSalas);
 
		tblSalas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
			}
		});
 
		btnPesquisar = new JButton("");
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(Salas.class.getResource("/img/search.png")));
		btnPesquisar.setBorder(null);
		btnPesquisar.setBounds(188, 270, 34, 20);
		getContentPane().add(btnPesquisar);
		
		btnPesquisar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				btnBuscarSalas();
			}
		});
 
		inputID = new JTextField();
		inputID.setEnabled(false);
		inputID.setBounds(46, 199, 60, 20);
		getContentPane().add(inputID);
		inputID.setColumns(10);
 
		JLabel idFunc = new JLabel("ID :");
		idFunc.setBounds(24, 202, 29, 14);
		getContentPane().add(idFunc);
 
		inputCategoria = new JComboBox();
		inputCategoria.setModel(new DefaultComboBoxModel(new String[] { "", "Sala de Reunião", "Sala de Conferência",
				"Espaço de Eventos", "Escritório Privado" }));
		inputCategoria.setBounds(74, 54, 139, 22);
		getContentPane().add(inputCategoria);
		
		inputCategoria.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				buscarSalaNaTabela();
				
			}
		});
 
		inputCod = new JComboBox();
		inputCod.setModel(new DefaultComboBoxModel(new String[] { "", "REU", "CONF", "EVENT", "PRIV" }));
		inputCod.setBounds(74, 240, 106, 22);
		getContentPane().add(inputCod);
 
		inputAndar = new JComboBox();
		inputAndar.setModel(
				new DefaultComboBoxModel(new String[] { "", "Subsolo", "Térreo", "1ª andar", "2ª andar", "3ª andar" }));
		inputAndar.setBounds(368, 240, 148, 22);
		getContentPane().add(inputAndar);
 
		inputNum = new JTextField();
		inputNum.setBounds(74, 273, 104, 20);
		getContentPane().add(inputNum);
		inputNum.setColumns(10);
 
	}
	
	DAO dao = new DAO();
 
	private void adicionarSalas() {
 
		String create = "insert into salas (andarSala, numeroSala, tipoSala, codigoSala, ocupacaoSala) values (?, ?, ?, ?, ?)";
 
		if (inputCategoria.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Categoria da sala Obrigatória!");
 
		}
 
		else if (inputCod.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Código da sala Obrigatória!");
 
		}
		
		else if (inputAndar.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Andar da sala Obrigatória!");
 
		}
 
		else if (inputNum.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Numero da sala Obrigatória!");
			inputNum.requestFocus();
			
		}
 
		else if (inputOcup.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ocupação máxima Obrigatória!");
			inputOcup.requestFocus();
			
		}
 
		else {
 
			try {
				// Estabelecer a conexao
				Connection conexaoBanco = dao.conectar();
 
				PreparedStatement executarSQL = conexaoBanco.prepareStatement(create);
 
				// Substituir os pontos de interrogação pelo conteudo das caixas de texto
				// (inputs)
				
				executarSQL.setString(1, inputAndar.getSelectedItem().toString());
				executarSQL.setString(2, inputNum.getText());
				executarSQL.setString(3, inputCategoria.getSelectedItem().toString());
				executarSQL.setString(4, inputCod.getSelectedItem().toString());
				executarSQL.setString(5, inputOcup.getText());
 
				// Executar os comandos SQL e inserir o Salas no banco de dados
				executarSQL.executeUpdate();
 
				JOptionPane.showMessageDialog(null, "Sala cadastrada com sucesso.");
 
				limparCampos();
				conexaoBanco.close();
			}
 
			catch (SQLIntegrityConstraintViolationException error) {
				JOptionPane.showMessageDialog(null, "Sala já cadastrada.");
				limparCampos();
 
			}
 
			catch (Exception e) {
				System.out.println(e);
 
			}
 
		}
 
	}
	
private void buscarSalaNaTabela() {
		
		String readTabela = "select tipoSala as Categoria, andarSala as Sala, numeroSala as Número from salas where tipoSala = ?; ";
		
		try {
			
			//estabelecer a conexão
			Connection conexaoBanco = dao.conectar();
			
			//preparar a execução dos comandos SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readTabela);
			
			//Substiruit o ? pelo conteúdo da caixa de texto
			executarSQL.setString(1, inputCategoria.getSelectedItem().toString());
			
			//Executar o comando Sql
			ResultSet resultadoExecucao = executarSQL.executeQuery();
			
			//Exibir o resultado na tabela, utilização da biblioteca rs2xml para "popular"
			//a tabela
			tblSalas.setModel(DbUtils.resultSetToTableModel(resultadoExecucao));
			
			conexaoBanco.close();
			
		} 
		
		
		catch (Exception e) {

		}
	}
 
	private void setarCaixasTexto() {
		
		// Criar uma variavel para receber a linha da tabela
		int setarLinha = tblSalas.getSelectedRow();
 
		inputNum.setText(tblSalas.getModel().getValueAt(setarLinha, 2).toString());
 
	}
 
	
	
	private void btnBuscarSalas() {
 
		String readBtn = "select * from salas where numeroSala = ?;";
 
		try {
 
			Connection conexaoBanco = dao.conectar();
 
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readBtn);
 
			executarSQL.setString(1, inputNum.getText());
 
			// Executar o comando SQL e exibir o resultado no fomulario Salas (todos
			// os seus dados)
			ResultSet resultadoExecucao = executarSQL.executeQuery();
 
			if (resultadoExecucao.next()) {
				
				// preencher os campos do formulario
				inputAndar.setSelectedItem(resultadoExecucao.getString(2));
				inputCod.setSelectedItem(resultadoExecucao.getString(5));
				inputOcup.setText(resultadoExecucao.getString(6));
 
			}
 
		}
 
		catch (Exception e) {
			System.out.println(e);
 
		}
 
	}
 
	private void limparCampos() {
 
		inputCategoria.setSelectedIndex(-1);
		inputCod.setSelectedIndex(-1);
		inputAndar.setSelectedIndex(-1);
		inputNum.setText(null);
		inputOcup.setText(null);
		
		inputCategoria.requestFocus();
 
	}
 
	private void updateSalas() {
		String updateBtn = "update salas set andarSala = ?, numeroSala = ?, tipoSala = ?, codigoSala = ?, ocupacaoSala = ? where tipoSala = ?;";
 
		if (inputCategoria.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Categoria da sala Obrigatória!");
 
		}
 
		else if (inputCod.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Código da sala Obrigatória!");
 
		}
		
		else if (inputAndar.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Andar da sala Obrigatória!");
 
		}
 
		else if (inputNum.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Numero da sala Obrigatória!");
			inputNum.requestFocus();
			
		}
 
		else if (inputOcup.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ocupação máxima Obrigatória!");
			inputOcup.requestFocus();
			
		}
 
		else {
 
 
			try {
 
				Connection conexaoBanco = dao.conectar();
 
				PreparedStatement executarSQL = conexaoBanco.prepareStatement(updateBtn);
 
				executarSQL.setString(1, inputAndar.getSelectedItem().toString());
				executarSQL.setString(2, inputNum.getText());
				executarSQL.setString(3, inputCategoria.getSelectedItem().toString());
				executarSQL.setString(4, inputCod.getSelectedItem().toString());
				executarSQL.setString(5, inputOcup.getText());
				executarSQL.setString(6, inputID.getText());
 
				executarSQL.executeUpdate();
 
				JOptionPane.showMessageDialog(null, "Sala aualizada com sucesso");
 
				limparCampos();
 
				conexaoBanco.close();
 
			}
 
			catch (SQLIntegrityConstraintViolationException error) {
				JOptionPane.showMessageDialog(null, "Sala já cadastrada.");
				limparCampos();
 
			}
 
			catch (Exception e) {
				System.out.println(e);
 
			}
 
		}
 
	}
 
	private void deletarSalas() {
		String delete = "delete from Salas where idSala = ?;";
 
		try {
			Connection conexaoBanco = dao.conectar();
 
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(delete);
 
			executarSQL.setString(1, inputID.getText());
 
			executarSQL.executeUpdate();
 
			JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso.");
 
			limparCampos();
 
			conexaoBanco.close();
		}
 
		catch (Exception e) {
			System.out.print(e);
 
		}
	}
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salas dialog = new Salas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
