/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Cidade;
import br.com.pi.entidade.Estado;
import java.util.List;

/**
 *
 * @author Cursos Livres
 */
public interface CidadeDAO extends BaseDAO<Cidade, Long> {
    List<Cidade> pesquisaEstado(Estado estado);
    List<Cidade> pesquisaLikeNome(String nome);
}
