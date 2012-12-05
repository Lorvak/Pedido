/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.dao.FabricaSessao;
import br.com.pi.entidade.Estado;
import br.com.pi.entidade.Pais;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class EstadoDAOImp extends BaseDAOImp<Estado, Long> implements EstadoDAO{

    @Override
    public Estado pesquisa(Long id) {
        abreConexao();
        Estado entidade = (Estado) session.get(Estado.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<Estado> getTodos() {
        abreConexao();
        List<Estado> list = session.createCriteria(Estado.class).list();
        fechaConexao();
        return list;
    }
    
    @Override
    public List<Estado> pesquisaLikeNome(String nome) {
        abreConexao();
        Query query = session.createQuery("FROM Estado p WHERE p.nome like :valor");
        query.setString("valor", "%" + nome + "%");
        List<Estado> list = query.list();
        fechaConexao();
        return list;
    }

    @Override
    public List<Estado> pesquisaPais(Pais pais) {
        abreConexao();
        Query query = session.createQuery("FROM Estado p WHERE p.pais = :valor");
        query.setLong("valor", pais.getId());
        List<Estado> list = query.list();
        fechaConexao();
        return list;
    }
}
