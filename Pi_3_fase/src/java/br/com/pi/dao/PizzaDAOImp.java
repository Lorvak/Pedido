/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Pizza;
import java.util.List;


/**
 *
 * @author Eduardo M. Silveira
 */
public class PizzaDAOImp extends BaseDAOImp<Pizza, Long> implements PizzaDAO{

    @Override
    public Pizza pesquisa(Long id) {
        abreConexao();
        Pizza entidade = (Pizza) session.get(Pizza.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<Pizza> getTodos() {
        abreConexao();
        List<Pizza> list = session.createCriteria(Pizza.class).list();
        fechaConexao();
        return list;
    }
    
}
