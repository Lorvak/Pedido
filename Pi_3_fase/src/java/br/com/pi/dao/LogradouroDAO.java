/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Bairro;
import br.com.pi.entidade.Cidade;
import br.com.pi.entidade.Logradouro;
import java.util.List;

/**
 *
 * @author Cursos Livres
 */
public interface LogradouroDAO extends BaseDAO<Logradouro, Long> {
    List<Logradouro> pesquisaLikeCep(String cep);
    List<Logradouro> pesquisaBairro(Bairro bairro);
    List<Logradouro> pesquisaLikeNome(String nome);
}
