/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.integracao.mysql;

import br.sc.senac.pontoeletronico.integracao.DAOAbstractFactory;


public class DAOFactory extends DAOAbstractFactory{
    public  DAOFactory(){
        this.setDaoPedido(new PedidoDAOImp());
    }
}
