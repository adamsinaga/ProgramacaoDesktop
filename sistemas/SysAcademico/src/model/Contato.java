package model;

//utilizaremos como classe value objects
public class Contato {
	private int id;
	private String nome;
	private String email;
	private int idade;
		
	public Contato(String nome, String email, int idade) {		
		super();		
		this.nome = nome;
		this.email = email;
		this.idade = idade;
	}
	public Contato(int id, String nome, String email, int idade) {		
		super();		
		this.nome = nome;
		this.email = email;
		this.idade = idade;
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
}
