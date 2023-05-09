package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//Fornecerá objetos de acesso a dados
public class ContatoDAO {
	Connection conn;

	// Fornecerá o objeto de conexão com a base de dados
	public Connection getConnection() {

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/contatos", "teste", "Teste@1234_");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// obtendo uma lista de contatos
	public ArrayList<Contato> getContatosList() {	
		ArrayList<Contato> listaDeContatos = new ArrayList<Contato>();
		// montar uma sentença sql para buscar os dados
		String query = "SELECT * FROM usuarios";
		
		Statement st; // fornece um ambiente para execução da sentença SQL
		ResultSet rs; // fornce um objeto (coleção) para armazenar dados vindo do DB

		try {
			st = getConnection().createStatement();
			rs = st.executeQuery(query);
			Contato contato;
			while (rs.next()) {
				contato = new Contato(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getInt("idade"));		
				listaDeContatos.add(contato);
			}
			return listaDeContatos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	// Executa as querys para inserir, atualizar e deletar
	public boolean inserir(Contato contato) {
		Statement st;
		String query = "INSERT INTO usuarios(nome, email, idade) VALUES ('" + contato.getNome() + "','"
				+ contato.getEmail() + "','" + contato.getIdade() + "')";		
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

	// Executa as querys para inserir, atualizar e deletar
	public boolean atualizar(Contato contato) {
		Statement st;
		String query = "UPDATE usuarios SET nome=" + "'" + contato.getNome() + "'" + ",email='" + contato.getEmail()
				+ "',idade=" + contato.getIdade() + " WHERE id = " + contato.getId();
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

	// Executa as querys para inserir, atualizar e deletar
	public boolean deletar(int id) {
		Statement st;
		String query = "DELETE FROM usuarios WHERE id = " + id;
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

	public ResultSet consulta(String info, String select) {
		String query = "select id, nome, email, idade from usuarios where " + select + "=?";
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = getConnection().prepareStatement(query);
			pst.setString(1, info);			
			rs = pst.executeQuery();			
		} catch (Exception e) {			
			return null;
		}		
		return rs;		
	}
}
