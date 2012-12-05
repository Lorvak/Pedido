/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Borda;
import java.util.List;


/**
 *
 * @author Eduardo M. Silveira
 */
public class BordaDAOImp extends BaseDAOImp<Borda, Long> implements BordaDAO{

    @Override
    public Borda pesquisa(Long id) {
        abreConexao();
        Borda entidade = (Borda) session.get(Borda.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<Borda> getTodos() {
        abreConexao();
        List<Borda> list = session.createCriteria(Borda.class).list();
        fechaConexao();
        return list;
    }
    
}
