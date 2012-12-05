/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.dao.FabricaSessao;
import br.com.pi.entidade.Pais;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class PaisDAOImp extends BaseDAOImp<Pais, Long> implements PaisDAO{

    @Override
    public Pais pesquisa(Long id) {
        abreConexao();
        Pais entidade = (Pais) session.get(Pais.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<Pais> getTodos() {
        abreConexao();
        List<Pais> list = session.createCriteria(Pais.class).list();
        fechaConexao();
        return list;
    }

    @Override
    public List<Pais> pesquisaLikeNome(String nome) {
        abreConexao();
        Query query = session.createQuery("FROM Pais p WHERE p.nome like :valor");
        query.setString("valor", "%" + nome + "%");
        List<Pais> list = query.list();
        fechaConexao();
        return list;
    }
    
}
