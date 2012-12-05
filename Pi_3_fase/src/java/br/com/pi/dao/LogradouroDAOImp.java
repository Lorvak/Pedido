/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.dao.FabricaSessao;
import br.com.pi.entidade.Bairro;
import br.com.pi.entidade.Logradouro;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class LogradouroDAOImp extends BaseDAOImp<Logradouro, Long> implements LogradouroDAO{

    @Override
    public Logradouro pesquisa(Long id) {
        abreConexao();
        Logradouro entidade = (Logradouro) session.get(Logradouro.class, id);
        fechaSessao();
        return entidade;
    }

    @Override
    public List<Logradouro> getTodos() {
        abreConexao();
        List<Logradouro> list = session.createCriteria(Logradouro.class).list();
        fechaConexao();
        return list;
    }
    
    @Override
    public List<Logradouro> pesquisaLikeNome(String nome) {
        abreConexao();
        Query query = session.createQuery("FROM Logradouro p WHERE p.nome like :valor");
        query.setString("valor", "%" + nome + "%");
        List<Logradouro> list = query.list();
        fechaConexao();
        return list;
    }

    @Override
    public List<Logradouro> pesquisaLikeCep(String cep) {
        abreConexao();
        Query query = session.createQuery("FROM Logradouro p WHERE p.cep like :valor");
        query.setString("valor", "%" + cep + "%");
        List<Logradouro> list = query.list();
        fechaConexao();
        return list;
    }

    @Override
    public List<Logradouro> pesquisaBairro(Bairro bairro) {
        abreConexao();
        Query query = session.createQuery("FROM Logradouro p WHERE p.bairro = :valor");
        query.setLong("valor", bairro.getId());
        List<Logradouro> list = query.list();
        fechaConexao();
        return list;
    }
}
