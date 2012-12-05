/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Estado;
import br.com.pi.entidade.Pais;
import java.util.List;

/**
 *
 * @author Cursos Livres
 */
public interface EstadoDAO extends BaseDAO<Estado, Long> {
    List<Estado> pesquisaPais(Pais pais);
    List<Estado> pesquisaLikeNome(String nome);
}
