/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Pais;
import java.util.List;

/**
 *
 * @author Cursos Livres
 */
public interface PaisDAO extends BaseDAO<Pais, Long> {
    List<Pais> pesquisaLikeNome(String nome);
}
