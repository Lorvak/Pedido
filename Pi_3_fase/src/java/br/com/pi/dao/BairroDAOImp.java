/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.dao.FabricaSessao;
import br.com.pi.entidade.Bairro;
import br.com.pi.entidade.Cidade;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class BairroDAOImp extends BaseDAOImp<Bairro, Long> implements BairroDAO{

    @Override
    public Bairro pesquisa(Long id) {
        abreConexao();
        Bairro entidade = (Bairro) session.get(Bairro.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<Bairro> getTodos() {
        abreConexao();
        List<Bairro> list = session.createCriteria(Bairro.class).list();
        fechaConexao();
        return list;
    }
    
    @Override
    public List<Bairro> pesquisaLikeNome(String nome) {
        abreConexao();
        Query query = session.createQuery("FROM Bairro p WHERE p.nome like :valor");
        query.setString("valor", "%" + nome + "%");
        List<Bairro> list = query.list();
        fechaConexao();
        return list;
    }

    @Override
    public List<Bairro> pesquisaCidade(Cidade cidade) {
        abreConexao();
        Query query = session.createQuery("FROM Bairro p WHERE p.cidade = :valor");
        query.setLong("valor", cidade.getId());
        List<Bairro> list = query.list();
        fechaConexao();
        return list;
    }
    
}
