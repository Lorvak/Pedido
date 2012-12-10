/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.dao.FabricaSessao;
import br.com.pi.entidade.Logradouro;
import br.com.pi.entidade.Moradia;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class MoradiaDAOImp extends BaseDAOImp<Moradia, Long> implements MoradiaDAO{

    @Override
    public Moradia pesquisa(Long id) {
        abreConexao();
        Moradia entidade = (Moradia) session.get(Moradia.class, id);
        fechaSessao();
        return entidade;
    }

    @Override
    public List<Moradia> getTodos() {
        abreConexao();
        List<Moradia> list = session.createCriteria(Moradia.class).list();
        fechaConexao();
        return list;
    }
    
    @Override
    public List<Moradia> pesquisaLikeTelefone(String telefone) {
        abreConexao();
        Query query = session.createQuery("FROM Moradia p WHERE p.telefone like :valor");
        query.setString("valor", "%" + telefone + "%");
        List<Moradia> list = query.list();
        fechaConexao();
        return list;
    }

    @Override
    public List<Moradia> pesquisaLikeNumero(String numero) {
        abreConexao();
        Query query = session.createQuery("FROM Moradia p WHERE p.numero like :valor");
        query.setString("valor", "%" + numero + "%");
        List<Moradia> list = query.list();
        fechaConexao();
        return list;
    }

    @Override
    public List<Moradia> pesquisaLogradouro(Logradouro logradouro) {
        abreConexao();
        Query query = session.createQuery("FROM Moradia p WHERE p.logradouro = :valor");
        query.setLong("valor", logradouro.getId());
        List<Moradia> list = query.list();
        fechaConexao();
        return list;
    }
}
