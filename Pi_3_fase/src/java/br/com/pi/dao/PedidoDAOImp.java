/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Pedido;
import java.util.List;



/**
 *
 * @author Eduardo M. Silveira
 */
public class PedidoDAOImp extends BaseDAOImp<Pedido, Long> implements PedidoDAO{

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
    
}
