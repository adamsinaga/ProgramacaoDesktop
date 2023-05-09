package academico.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {
	//é necessário fornecer uma api de conexão com o banco de dados específico (MYSQL, POSTGRE)
	private static String dbUrl = "jdbc:mysql://localhost:3306/Academico";
	private static String dbUser = "root";
	private static String dbPass = "";

	//método para obter conexão física com o banco dados (MYSQL)
	public static Connection getConnect() throws Exception {
		//Este comando obtém a referência do driver mysql
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		return con;
	}
	
	
}