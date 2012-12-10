/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.entidade;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Cursos Livres
 */

@Entity
public class Moradia implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String numero;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private String complemento;
    @JoinColumn(name="idLogradouro")
    @ManyToOne
    private Logradouro Logradouro;

    public Moradia() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Logradouro getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(Logradouro Logradouro) {
        this.Logradouro = Logradouro;
    }

}
