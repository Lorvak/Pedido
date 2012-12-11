package br.com.senac.Vo;

import java.util.List;

public class Mesa {
	

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	private Integer id;
	private String numMesa;
	private List<Pedido>pedidos;
	
	
	public Mesa() {
		super();
	}

	public Mesa(Integer id, String numMesa) {
		super();
		this.id = id;
		this.numMesa = numMesa;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNumMesa() {
		return numMesa;
	}
	
	public void setNumMesa(String numMesa) {
		this.numMesa = numMesa;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	
	
	
	
	
}
