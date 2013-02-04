/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.integracao.mysql;

import br.sc.senac.pontoeletronico.integracao.PontoEletronicoDAOAbstractFactory;

public class PontoEletronicoMySQLDAOFactory extends PontoEletronicoDAOAbstractFactory{
    public  PontoEletronicoMySQLDAOFactory(){
        this.setDaoProjeto(new DAOProjetoMySQL());
        this.setDaoUsuario(new DAOUsuarioMySQL());
        this.setDaoRegistro(new DAORegistroMySQL());
    }
}
