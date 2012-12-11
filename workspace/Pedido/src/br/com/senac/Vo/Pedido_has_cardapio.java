package br.com.senac.Vo;

public class Pedido_has_cardapio {
	private String descricao;
	private String adicionado;
	private Pedido pedido;
	private Cardapio cardapio;
	private Borda borda;
	
	
	
	public Pedido_has_cardapio() {
		super();
	}
	
	public Pedido_has_cardapio(String descricao, String adicionado,
			Pedido pedido, Cardapio cardapio, Borda borda) {
		super();
		this.descricao = descricao;
		this.adicionado = adicionado;
		this.pedido = pedido;
		this.cardapio = cardapio;
		this.borda = borda;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAdicionado() {
		return adicionado;
	}
	public void setAdicionado(String adicionado) {
		this.adicionado = adicionado;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Cardapio getCardapio() {
		return cardapio;
	}
	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}
	public Borda getBorda() {
		return borda;
	}
	public void setBorda(Borda borda) {
		this.borda = borda;
	}
	
	
}
