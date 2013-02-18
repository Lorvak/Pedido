/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.dao.FabricaSessao;
import br.com.pi.entidade.SaborSelecionado;
import br.com.pi.entidade.Cidade;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class SaborSelecionadoDAOImp extends BaseDAOImp<SaborSelecionado, Long> implements SaborSelecionadoDAO{

    @Override
    public SaborSelecionado pesquisa(Long id) {
        abreConexao();
        SaborSelecionado entidade = (SaborSelecionado) session.get(SaborSelecionado.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<SaborSelecionado> getTodos() {
        abreConexao();
        List<SaborSelecionado> list = session.createCriteria(SaborSelecionado.class).list();
        fechaConexao();
        return list;
    }
    
}
