/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Entregador;
import java.util.List;

/**
 *
 * @author Liana
 */
public interface EntregadorDAO extends BaseDAO<Entregador, Long> {
    List<Entregador> pesquisaLikeNome(String nome);

}
