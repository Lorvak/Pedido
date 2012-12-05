/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import java.util.List;

/**
 *
 * @author Eduardo M. Silveira
 */
public interface BaseDAO<T, ID> {
    T salva(T entidade);
    
    T pesquisa(ID id);
    
    void remove(T entidade);
    
    void altera(T entidade);
    
    List<T> getTodos();
}
