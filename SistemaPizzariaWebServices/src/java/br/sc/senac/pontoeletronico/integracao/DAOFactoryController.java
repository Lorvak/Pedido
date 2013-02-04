/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.integracao;

import br.sc.senac.pontoeletronico.integracao.mysql.PontoEletronicoMySQLDAOFactory;

/**
 *
 * @author Administrador
 */
public class DAOFactoryController {
    private static PontoEletronicoDAOAbstractFactory daoFactory=new PontoEletronicoMySQLDAOFactory();;

    public static PontoEletronicoDAOAbstractFactory getDaoFactory() {
        return daoFactory;
    }

    public static void setDaoFactory(PontoEletronicoDAOAbstractFactory daoFactory) {
        DAOFactoryController.daoFactory = daoFactory;
    }
   
}
