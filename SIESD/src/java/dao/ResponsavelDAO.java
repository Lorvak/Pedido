/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Responsavel;

/**
 *
 * @author Junior
 */
public interface ResponsavelDAO extends BaseDAO<Responsavel, Long> {

    public Responsavel pesquisaPorCpf(String cpf);
    
}
