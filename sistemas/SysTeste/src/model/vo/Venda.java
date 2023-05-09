package model.vo;

import java.util.Date;

public class Venda {
	Cliente cliente = new Cliente();
	int nmrVenda;
	String emissao;
	String historico;
	//métodos getters e setters
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getNmrVenda() {
		return nmrVenda;
	}
	public void setNmrVenda(int nmrVenda) {
		this.nmrVenda = nmrVenda;
	}
	public String getEmissao() {
		return emissao;
	}
	public void setEmissao(String emissao) {
		this.emissao = emissao;
	}
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	
}
