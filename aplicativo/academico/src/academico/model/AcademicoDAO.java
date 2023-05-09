package academico.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import academico.model.vo.Estudante;

//NESTA CLASSE ESTAREMOS REALIZANDO O PROCEDIMENTO CRUD (Create-Research-Update-Delete)
public class AcademicoDAO {
	// Connection permite obter uma conexão física com o banco de dados
	static Connection con = null;
	// Esta api permite que fornecamos um ambiente de execução para sentenças SQL.
	static PreparedStatement pstm = null;
	// O ResultSet é utilizado para manter uma coleção de dados advindos do BANCO DE
	// DADOS
	static ResultSet rs = null;

	public void save(Estudante est) {
		String sql = "INSERT INTO estudante(nome, curso, departamento) VALUES(?,?,?)";
		// TRATAMENTO DE EXCEÇÃO
		try {
			con = FabricaDeConexao.getConnect();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, est.getNome());
			pstm.setString(2, est.getCurso());
			pstm.setString(3, est.getDepartamento());
			pstm.execute();
		} catch (SQLException sqlExc) {
			sqlExc.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Estudante> getEstudantes() {
		String sql = "SELECT * FROM estudante";

		List<Estudante> estudantes = new ArrayList<Estudante>();

		try {
			con = FabricaDeConexao.getConnect();
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Estudante est = new Estudante();
				est.setId(rs.getInt("id"));
				est.setNome(rs.getString("nome"));
				est.setCurso(rs.getString("curso"));
				est.setDepartamento(rs.getString("departamento"));
				estudantes.add(est);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return estudantes;
	}

	public void deleteById(int id) {
		String sql = "delete from estudante where id = ?";
		try {
			con = FabricaDeConexao.getConnect();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public boolean updateEstudante(Estudante est) {
		boolean ok = false;
		// montar a sentença SQL
		String sql = "UPDATE estudante SET nome = ?, curso = ?, departamento = ? where id = ?";
		try {
			con = FabricaDeConexao.getConnect();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, est.getNome());
			pstm.setString(2, est.getCurso());
			pstm.setString(3, est.getDepartamento());
			pstm.execute();
			ok = true;
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return ok;
	}

}
