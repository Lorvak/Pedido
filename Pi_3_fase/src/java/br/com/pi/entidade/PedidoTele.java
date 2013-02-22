/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Eduardo M. Silveira
 */
@Entity
public class PedidoTele implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable=true,precision=2)
    private Double preco;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date horaPedido;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Pizza> pizzas;
    @OneToMany
    private List<Bebida> bebidas;
    @OneToOne
    private Cliente cliente; 
    @OneToOne
    private Funcionario funcionario;
    @OneToOne
    private Moradia moradia;
    @OneToOne
    private Entregador entregador;
    @Transient
    private Double valorFinal;

    public PedidoTele() {
    }

    public PedidoTele(Long id, Double preco, Date horaPedido, List<Pizza> pizzas, List<Bebida> bebidas, Cliente cliente, Funcionario funcionario, Moradia moradia, Entregador entregador, Double valorFinal) {
        this.id = id;
        this.preco = preco;
        this.horaPedido = horaPedido;
        this.pizzas = pizzas;
        this.bebidas = bebidas;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.moradia = moradia;
        this.entregador = entregador;
        this.valorFinal = valorFinal;
    }

    public Date getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(Date horaPedido) {
        this.horaPedido = horaPedido;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Moradia getMoradia() {
        return moradia;
    }

    public void setEndereco(Moradia moradia) {
        this.moradia = moradia;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoTele)) {
            return false;
        }
        PedidoTele other = (PedidoTele) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pizzaria.bean.PedidoTele[ id=" + id + " ]";
    }
    
}
