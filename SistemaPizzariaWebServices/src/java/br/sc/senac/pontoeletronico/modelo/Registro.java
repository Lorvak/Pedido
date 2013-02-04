/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.modelo;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Administrador
 */

@Entity
@Table(name ="registro")
public class Registro {
    @Id
    @Column (name="chave")
    private Integer id=null; 
    @Column (name="data") 
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column (name="tempo") 
    private double tempo;
    @Column (name="descricao")
    private String descricao;
    @OneToOne
    @JoinColumn(name="chave_projeto") 
    private Projeto projeto;
    @OneToOne
    @JoinColumn(name="chave_usuario")
    private Usuario usuario;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Registro other = (Registro) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }   
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
}
