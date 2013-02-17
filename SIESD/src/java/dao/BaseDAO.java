package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 *
 * @author Junior
 */
public interface BaseDAO<T, ID> {
    T salva(T entidade) throws SQLIntegrityConstraintViolationException, Exception;

    T pesquisaPorId(ID id);

    void remove(T entidade);

    void altera(T entidade);

    List<T> getTodos();

}
