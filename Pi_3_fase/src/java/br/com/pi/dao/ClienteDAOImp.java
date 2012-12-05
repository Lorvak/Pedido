/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Cliente;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Liana
 */
public class ClienteDAOImp extends BaseDAOImp<Cliente, Long> implements ClienteDAO{

    @Override
    public Cliente pesquisa(Long id) {
        abreConexao();
        Cliente cliente = (Cliente) session.get(Cliente.class, id);
        fechaConexao();
        return cliente;
    }

    @Override
    public List<Cliente> getTodos() {
        abreConexao();
        Query query = session.createQuery("FROM Cliente c");
        List<Cliente> clientes = query.list();
        fechaConexao();
        return clientes;
    }

    @Override
    public List<Cliente> pesquisaLikeNome(String nome) {
        abreConexao();
        Query query = session.createQuery("FROM Cliente c WHERE c.nome like  :valor");
        query.setString("valor", "%" + nome + "%");
        List<Cliente> clientes = query.list();
        fechaConexao();
        return clientes;
    }
    
}
