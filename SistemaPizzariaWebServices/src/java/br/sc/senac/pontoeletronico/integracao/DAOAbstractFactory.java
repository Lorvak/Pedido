/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.integracao;

import java.util.Date;

/**
 *
 * @author Administrador
 */
public abstract class DAOAbstractFactory {

    private PedidoDAO daoPedido;

    public PedidoDAO getDaoPedido() {
        return daoPedido;
    }

    public void setDaoPedido(PedidoDAO daoPedido) {
        this.daoPedido = daoPedido;
    }



   
      
}
