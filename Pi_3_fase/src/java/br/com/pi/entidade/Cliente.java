/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Liana
 */
@Entity
@PrimaryKeyJoinColumn(name = "idpessoa")
public class Cliente extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date cadastro;
   

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

  
    
    
}
