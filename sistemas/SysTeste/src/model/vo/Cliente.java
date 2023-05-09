package model.vo;

public class Cliente {
	int idCliente;
	String nome;
	
	//método construtor 
	
	public Cliente(int idCliente, String nome) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
	}
		
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
