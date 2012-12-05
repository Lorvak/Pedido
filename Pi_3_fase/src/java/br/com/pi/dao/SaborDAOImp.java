/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Sabor;
import java.util.List;


/**
 *
 * @author Eduardo M. Silveira
 */
public class SaborDAOImp extends BaseDAOImp<Sabor, Long> implements SaborDAO{

    @Override
    public Sabor pesquisa(Long id) {
        abreConexao();
        Sabor entidade = (Sabor) session.get(Sabor.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<Sabor> getTodos() {
        abreConexao();
        List<Sabor> list = session.createCriteria(Sabor.class).list();
        fechaConexao();
        return list;
    }
    
}
