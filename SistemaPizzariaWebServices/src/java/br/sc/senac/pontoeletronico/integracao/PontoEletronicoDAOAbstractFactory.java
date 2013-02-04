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
public abstract class PontoEletronicoDAOAbstractFactory {
    private iDAOProjeto daoProjeto;
    private iDAORegistro daoRegistro;
    private iDAOUsuario daoUsuario;

    public iDAOProjeto getDaoProjeto() {
        return daoProjeto;
    }

    public void setDaoProjeto(iDAOProjeto daoProjeto) {
        this.daoProjeto = daoProjeto;
    }

    public iDAORegistro getDaoRegistro() {
        return daoRegistro;
    }

    public void setDaoRegistro(iDAORegistro daoRegistro) {
        this.daoRegistro = daoRegistro;
    }

    public iDAOUsuario getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(iDAOUsuario daoUsuario) {
        this.daoUsuario = daoUsuario;
    }
      
}
