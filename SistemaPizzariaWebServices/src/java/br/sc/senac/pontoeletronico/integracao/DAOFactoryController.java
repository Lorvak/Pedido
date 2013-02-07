/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.integracao;

import br.sc.senac.pontoeletronico.integracao.mysql.DAOFactory;

/**
 *
 * @author Administrador
 */
public class DAOFactoryController {
    private static DAOAbstractFactory daoFactory=new DAOFactory();;

    public static DAOAbstractFactory getDaoFactory() {
        return daoFactory;
    }

    public static void setDaoFactory(DAOAbstractFactory daoFactory) {
        DAOFactoryController.daoFactory = daoFactory;
    }
   
}
