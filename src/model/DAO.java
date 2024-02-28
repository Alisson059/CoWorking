package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	//Por que os atributos foram definidos como privado?
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.23.46.21:3306/dbCoworking";
	private String user = "ti";
	private String password = "senac";
	
	public Connection conectar() {
		//Objeto usadp para a conexão
		Connection conexaoBanco = null;
		
		try {
			//Uso do driver JDBC
			Class.forName(driver);
			conexaoBanco = DriverManager.getConnection(url, user, password);
			return conexaoBanco;
		}
		
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
