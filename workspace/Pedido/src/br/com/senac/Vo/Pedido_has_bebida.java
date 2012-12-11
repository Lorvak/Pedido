package br.com.senac.Vo;

public class Pedido_has_bebida {
	private Pedido pedido;
	private Bebida bebida;
	
	
	
	public Pedido_has_bebida() {
		super();
	}

	public Pedido_has_bebida(Pedido pedido, Bebida bebida) {
		super();
		this.pedido = pedido;
		this.bebida = bebida;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Bebida getBebida() {
		return bebida;
	}
	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}
	
	
}
