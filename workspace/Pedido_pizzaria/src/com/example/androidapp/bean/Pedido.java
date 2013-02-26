package com.example.androidapp.bean;

import java.util.List;

public class Pedido {
private Long id;
private List<Pizza>pizzas;
private String resumo;

public String getResumo() {
	return resumo;
}
public void setResumo(String resumo) {
	this.resumo = resumo;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public List<Pizza> getPizzas() {
	return pizzas;
}
public void setPizzas(List<Pizza> pizzas) {
	this.pizzas = pizzas;
}
public List<Bebida> getBebidas() {
	return bebidas;
}
public void setBebidas(List<Bebida> bebidas) {
	this.bebidas = bebidas;
}
public Mesa getMesa() {
	return mesa;
}
public void setMesa(Mesa mesa) {
	this.mesa = mesa;
}
private List<Bebida>bebidas;
private Mesa mesa;



}
