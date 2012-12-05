/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.dao.FabricaSessao;
import br.com.pi.entidade.Cidade;
import br.com.pi.entidade.Estado;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class CidadeDAOImp extends BaseDAOImp<Cidade, Long> implements CidadeDAO{

    @Override
    public Cidade pesquisa(Long id) {
        abreConexao();
        Cidade entidade = (Cidade) session.get(Cidade.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<Cidade> getTodos() {
        abreConexao();
        List<Cidade> list = session.createCriteria(Cidade.class).list();
        fechaConexao();
        return list;
    }
    
    @Override
    public List<Cidade> pesquisaLikeNome(String nome) {
        abreConexao();
        Query query = session.createQuery("FROM Cidade p WHERE p.nome like :valor");
        query.setString("valor", "%" + nome + "%");
        List<Cidade> list = query.list();
        fechaConexao();
        return list;
    }

    @Override
    public List<Cidade> pesquisaEstado(Estado estado) {
        abreConexao();
        Query query = session.createQuery("FROM Cidade p WHERE p.estado = :valor");
        query.setLong("valor", estado.getId());
        List<Cidade> list = query.list();
        fechaConexao();
        return list;
    }
    
}
