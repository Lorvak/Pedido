package br.com.senac.Vo;

public class Bebida {
	private Integer id;
	private String nome;
	private String unidadeMedida;
	
	
	
	
	public Bebida() {
		super();
	}
	
	public Bebida(Integer id, String nome, String unidadeMedida) {
		super();
		this.id = id;
		this.nome = nome;
		this.unidadeMedida = unidadeMedida;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUnidadeMedida() {
		return unidadeMedida;
	}
	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	
	
}
