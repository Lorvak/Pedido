/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Bairro;
import br.com.pi.entidade.Cidade;
import java.util.List;

/**
 *
 * @author Cursos Livres
 */
public interface BairroDAO extends BaseDAO<Bairro, Long> {
    List<Bairro> pesquisaCidade(Cidade cidade);
    List<Bairro> pesquisaLikeNome(String nome);
}
