/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Tamanho;
import java.util.List;

/**
 *
 * @author Eduardo M. Silveira
 */
public class TamanhoDAOImp extends BaseDAOImp<Tamanho, Long> implements TamanhoDAO{

    @Override
    public Tamanho pesquisa(Long id) {
        abreConexao();
        Tamanho entidade = (Tamanho) session.get(Tamanho.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<Tamanho> getTodos() {
        abreConexao();
        List<Tamanho> list = session.createCriteria(Tamanho.class).list();
        fechaConexao();
        return list;
    }
    
}
