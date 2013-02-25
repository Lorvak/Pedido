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
public interface PedidoDAO extends BaseDAO<Pedido, Long> {
    public List<Pedido> pesquisaLikeMesa(String numero);
}
