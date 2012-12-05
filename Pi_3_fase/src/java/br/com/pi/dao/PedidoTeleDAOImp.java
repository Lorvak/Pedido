/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.PedidoTele;
import java.util.List;



/**
 *
 * @author Eduardo M. Silveira
 */
public class PedidoTeleDAOImp extends BaseDAOImp<PedidoTele, Long> implements PedidoTeleDAO{

    @Override
    public PedidoTele pesquisa(Long id) {
        abreConexao();
        PedidoTele entidade = (PedidoTele) session.get(PedidoTele.class, id);
        fechaConexao();
        return entidade;
    }

    @Override
    public List<PedidoTele> getTodos() {
        abreConexao();
        List<PedidoTele> list = session.createCriteria(PedidoTele.class).list();
        fechaConexao();
        return list;
    }
    
}
