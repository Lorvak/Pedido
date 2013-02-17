/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Junior
 */
public abstract class BaseDAOImp<T, ID> implements BaseDAO<T, ID> {

    protected Session session;
    protected Transaction tx;

    @Override
    public T salva(T entidade) throws SQLIntegrityConstraintViolationException, Exception{
        abreConexao();
        session.save(entidade);
        fechaConexao();
        return entidade;
    }

    @Override
    public void remove(T entidade) {
        abreConexao();
        session.delete(entidade);
        fechaConexao();
    }

    @Override
    public void altera(T entidade) {
        abreConexao();
        session.update(entidade);
        fechaConexao();
    }

    protected void abreConexao() {
        SessionFactory sf = AbreTransacao.abreSessao();  
        session = sf.openSession();
        tx = session.beginTransaction();
    }

    protected void fechaConexao() {
        tx.commit();
        session.close();
    }
}
