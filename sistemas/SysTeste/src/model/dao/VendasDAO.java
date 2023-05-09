package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import model.vo.Venda;

public class VendasDAO {
	Connection conn;
	//método para obter a conexão com o banco de dados
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/systeste", "teste", "Teste@1234_");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//método para inserir dados no DB
	// Executa as querys para inserir, atualizar e deletar
		public boolean inserir(Venda venda) {
			Statement st;
			String query = "INSERT INTO vendas(nmrvenda, idcliente, nomecliente, emissao,historico) VALUES ('" + venda.getNmrVenda() + "','"
					+ venda.getCliente().getIdCliente() + "','" + venda.getCliente().getNome() + "','" + venda.getEmissao() + "','" + venda.getHistorico()+ "')";		
			try {
				st = getConnection().createStatement();
				st.executeUpdate(query);
				// atualiza dados da jtable
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
	
	
}
