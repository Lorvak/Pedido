/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Bebida;
import java.util.List;


/**
 *
 * @author Eduardo M. Silveira
 */
public class BebidaDAOImp extends BaseDAOImp<Bebida, Long> implements BebidaDAO{

    @Override
    public Bebida pesquisa(Long id) {
        abreConexao();
        Bebida entidade = (Bebida) session.get(Bebida.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<Bebida> getTodos() {
        abreConexao();
        List<Bebida> list = session.createCriteria(Bebida.class).list();
        fechaConexao();
        return list;
    }
    
}
