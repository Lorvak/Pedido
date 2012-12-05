/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Mesa;
import java.util.List;


/**
 *
 * @author Eduardo M. Silveira
 */
public class MesaDAOImp extends BaseDAOImp<Mesa, Long> implements MesaDAO{

    @Override
    public Mesa pesquisa(Long id) {
        abreConexao();
        Mesa entidade = (Mesa) session.get(Mesa.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<Mesa> getTodos() {
        abreConexao();
        List<Mesa> list = session.createCriteria(Mesa.class).list();
        fechaConexao();
        return list;
    }
    
}
