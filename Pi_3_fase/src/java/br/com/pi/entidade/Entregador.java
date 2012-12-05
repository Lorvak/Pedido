/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.entidade;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Liana
 */
@Entity
@PrimaryKeyJoinColumn(name = "idfuncionario")
public class Entregador extends Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String placa;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
