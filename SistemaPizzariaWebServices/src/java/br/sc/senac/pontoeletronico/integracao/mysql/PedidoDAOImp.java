/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.integracao.mysql;

import br.android.pedido.Pedido;
import br.sc.senac.pontoeletronico.integracao.PedidoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrador
 */
public class PedidoDAOImp implements PedidoDAO {

    @Override
    public Integer insere(Pedido p) {
        EntityManager em= new DataSourceMySQL().getEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        return p.getId();
    }

  
    
}
