/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Liana
 */
@Entity
@PrimaryKeyJoinColumn(name = "idpessoa")
public class Funcionario extends Pessoa implements Serializable{
    private static final long serialVersionUID = 1L;
    @Column(nullable=false,unique=true)
    private String cracha;
    @Column(nullable=false,unique=true)
    private String cpf;
    @Column(nullable=false,unique=true)
    private String CarteiraTrabalho;
    @Column(nullable=false,unique=true)
    private String pis;

    public String getCracha() {
        return cracha;
    }

    public void setCracha(String cracha) {
        this.cracha = cracha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCarteiraTrabalho() {
        return CarteiraTrabalho;
    }

    public void setCarteiraTrabalho(String CarteiraTrabalho) {
        this.CarteiraTrabalho = CarteiraTrabalho;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }
    
}
