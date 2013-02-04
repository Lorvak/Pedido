package com.example.androidapp.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Registro {
	private int idProjeto;
	private int idUsuario;
	private Date data;
	private String descricao;
	private double horas;
	
	public int getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getHoras() {
		return horas;
	}
	public void setHoras(double horas) {
		this.horas = horas;
	}
	
	public String toString(){
		String saida="Projeto:"+ this.getIdProjeto();
		saida+="\nUsuário: "+ this.getIdUsuario();
		SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
		saida+="\nData: "+ formatter.format(this.getData());
		saida+="\nHoras: "+ this.getHoras();
		saida+="\nDescrição: "+ this.getDescricao() ;
		return saida;
	}
	
	
}
