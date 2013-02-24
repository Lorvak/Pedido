/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Cursos Livres
 */

@Entity
public class Estado implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,unique=true)
    private String nome;
    @Column(nullable = false)
    private String sigla;
    @OneToMany
    private List<Cidade> cidades;
    @ManyToOne
    @JoinColumn(name="idPais")
    private Pais pais;

    public Estado() {
    }

    public Estado(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
