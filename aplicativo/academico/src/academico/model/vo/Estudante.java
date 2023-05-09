package academico.model.vo;

public class Estudante {

	private int id;
	private String nome;
	private String curso;
	private String departamento;

	public Estudante() {
	}

	public Estudante(String nome, String curso, String departamento) {
		super();
		this.nome = nome;
		this.curso = curso;
		this.departamento = departamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	public boolean validarCPF() {
		return false;
	}
}
