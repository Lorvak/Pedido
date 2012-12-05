/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Entregador;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Liana
 */
public class EntregadorDAOImp extends BaseDAOImp<Entregador, Long> implements EntregadorDAO {

    @Override
    public Entregador pesquisa(Long id) {
        abreConexao();
        Entregador entregador = (Entregador) session.get(Entregador.class, id);
        fechaConexao();
        return entregador;
    }

    @Override
    public List<Entregador> getTodos() {
        abreConexao();
        Query query = session.createQuery("FROM Entregador e");
        List<Entregador> entregadores = query.list();
        fechaConexao();
        return entregadores;
    }

    @Override
    public List<Entregador> pesquisaLikeNome(String nome) {
        abreConexao();
        Query query = session.createQuery("FROM Entregador e WHERE e.nome like  :valor");
        query.setString("valor", "%" + nome + "%");
        List<Entregador> entregadores = query.list();
        fechaConexao();
        return entregadores;
    }
}
