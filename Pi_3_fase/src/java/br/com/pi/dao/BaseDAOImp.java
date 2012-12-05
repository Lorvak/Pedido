/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Eduardo M. Silveira
 */
public abstract class BaseDAOImp<T, ID> implements BaseDAO<T, ID> {
    protected Session session;
    protected Transaction transaction;
    
    protected void abreConexao(){
        session = FabricaSessao.abreConexao().openSession();
        transaction = session.beginTransaction();
    }
    
    protected void abreSessao(){
        session = FabricaSessao.abreConexao().openSession();
    }
    
    protected void fechaConexao(){
        transaction.commit();
        session.close();
    }
    
    protected void fechaSessao(){
        session.close();
    }

    @Override
    public T salva(T entidade) {
        abreConexao();
        session.save(entidade);
        fechaConexao();
        return entidade;
    }

    @Override
    public void altera(T entidade) {
        abreConexao();
        session.update(entidade);
        fechaConexao();
    }

    @Override
    public void remove(T entidade) {
        abreConexao();
        session.delete(entidade);
        fechaConexao();
    }
    
    
    
    
}
