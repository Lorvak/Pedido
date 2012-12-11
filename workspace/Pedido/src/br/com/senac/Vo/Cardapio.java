package br.com.senac.Vo;

import org.w3c.dom.Text;

public class Cardapio {
	private Integer id;
	private String sabor;
	private Float valor;
	private Text descricaoSabor;
	
	
	
	
	public Cardapio() {
		super();
	}
	
	public Cardapio(Integer id, String sabor, Float valor, Text descricaoSabor) {
		super();
		this.id = id;
		this.sabor = sabor;
		this.valor = valor;
		this.descricaoSabor = descricaoSabor;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSabor() {
		return sabor;
	}
	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Text getDescricaoSabor() {
		return descricaoSabor;
	}
	public void setDescricaoSabor(Text descricaoSabor) {
		this.descricaoSabor = descricaoSabor;
	}
	
	
	
	
	
}
