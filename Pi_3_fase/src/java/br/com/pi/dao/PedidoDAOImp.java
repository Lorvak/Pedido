/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Pedido;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eduardo M. Silveira
 */
public class PedidoDAOImp extends BaseDAOImp<Pedido, Long> implements PedidoDAO {

    @Override
    public Pedido pesquisa(Long id) {
        abreConexao();
        Pedido entidade = (Pedido) session.get(Pedido.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<Pedido> getTodos() {
        abreConexao();
        List<Pedido> list = session.createCriteria(Pedido.class).list();
        fechaConexao();
        return list;
    }

    @Override
    public List<Pedido> pesquisaLikeMesa(String numero){
        abreConexao();
        Criteria criteria = session.createCriteria(Pedido.class);
        criteria.setFetchMode("Mesa", FetchMode.JOIN).add(Restrictions.eq("numero", numero));
        List<Pedido> pedidos = criteria.list();
        fechaConexao();
        return pedidos;
    }
    
    public static void main(String[] args) {
        PedidoDAOImp aOImp = new PedidoDAOImp();
        System.out.println(aOImp.pesquisaLikeMesa("01"));
    }
}
