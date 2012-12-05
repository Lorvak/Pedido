/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Cliente;
import java.util.List;

/**
 *
 * @author Liana
 */
public interface ClienteDAO extends BaseDAO<Cliente, Long> {
     List<Cliente> pesquisaLikeNome(String nome);
}
