package br.com.senac.Vo;

import java.util.List;

public class Pedido {
	private Integer id;
	private String tamanho;
	private String numSabores;
	private String preco;
	private Mesa mesa;
	private List<Cardapio> cardapios;
	private List<Bebida> bebidas;
	
	
	public Pedido() {
		super();
	}

	
	
	public Pedido(Integer id, String tamanho, String numSabores, String preco,
			Mesa mesa, List<Cardapio> cardapios, List<Bebida> bebidas) {
		super();
		this.id = id;
		this.tamanho = tamanho;
		this.numSabores = numSabores;
		this.preco = preco;
		this.mesa = mesa;
		this.cardapios = cardapios;
		this.bebidas = bebidas;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getNumSabores() {
		return numSabores;
	}
	public void setNumSabores(String numSabores) {
		this.numSabores = numSabores;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}



	public List<Cardapio> getCardapios() {
		return cardapios;
	}



	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
	}



	public List<Bebida> getBebidas() {
		return bebidas;
	}



	public void setBebidas(List<Bebida> bebidas) {
		this.bebidas = bebidas;
	}
	
}
