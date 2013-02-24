/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Eduardo M. Silveira
 */
@Entity
public class Tamanho implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable=false,unique=true)
    private String nome;
    @Column(nullable=true,precision=2)
    private Double cemtimetros;
    @Column(nullable=true)
    private Long fatias;
    @Column(nullable=true,precision=2)
    private Double preco;
    @Column(nullable=true)
    private Long nsabores;

    public Tamanho() {
    }

    public Tamanho(String nome, Double cemtimetros, Long fatias, Double preco, Long nsabores) {
        this.nome = nome;
        this.cemtimetros = cemtimetros;
        this.fatias = fatias;
        this.preco = preco;
        this.nsabores = nsabores;
    }

    public Tamanho(Long id, String nome, Double cemtimetros, Long fatias, Double preco, Long nsabores) {
        this.id = id;
        this.nome = nome;
        this.cemtimetros = cemtimetros;
        this.fatias = fatias;
        this.preco = preco;
        this.nsabores = nsabores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getCemtimetros() {
        return cemtimetros;
    }

    public void setCemtimetros(Double cemtimetros) {
        this.cemtimetros = cemtimetros;
    }

    public Long getFatias() {
        return fatias;
    }

    public void setFatias(Long fatias) {
        this.fatias = fatias;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Long getNsabores() {
        return nsabores;
    }

    public void setNsabores(Long nsabores) {
        this.nsabores = nsabores;
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
        if (!(object instanceof Tamanho)) {
            return false;
        }
        Tamanho other = (Tamanho) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pizzaria.bean.tamanho[ id=" + id + " ]";
    }
    
}
