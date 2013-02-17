/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Transient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Eduardo M. Silveira
 */
@Entity
public class Pizza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Tamanho tamanho;
    @OneToOne
    private Borda borda;
    @ManyToMany(fetch= FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<SaborSelecionado> sabores;
    @Transient
    private Double precoFinal;
    @Transient
    private Boolean brinde;

    public Pizza() {
    }

    public Pizza(Tamanho tamanho, Borda borda, List<SaborSelecionado> sabores, Double precoFinal, String obs, Boolean brinde) {
        this.tamanho = tamanho;
        this.borda = borda;
        this.sabores = sabores;
        this.precoFinal = precoFinal;
        this.brinde = brinde;
    }

    public Pizza(Long id, Tamanho tamanho, Borda borda, List<SaborSelecionado> sabores, Double precoFinal, String obs, Boolean brinde) {
        this.id = id;
        this.tamanho = tamanho;
        this.borda = borda;
        this.sabores = sabores;
        this.precoFinal = precoFinal;
        this.brinde = brinde;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Borda getBorda() {
        return borda;
    }

    public void setBorda(Borda borda) {
        this.borda = borda;
    }

    public List<SaborSelecionado> getSabores() {
        return sabores;
    }

    public void setSabores(List<SaborSelecionado> sabores) {
        this.sabores = sabores;
    }

    public Double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(Double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public Boolean getBrinde() {
        return brinde;
    }

    public void setBrinde(Boolean brinde) {
        this.brinde = brinde;
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
        if (!(object instanceof Pizza)) {
            return false;
        }
        Pizza other = (Pizza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pizzaria.bean.Pizza[ id=" + id + " ]";
    }
    
}
