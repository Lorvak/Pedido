/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Bairro;
import br.com.pi.entidade.Cidade;
import br.com.pi.entidade.Logradouro;
import br.com.pi.entidade.Moradia;
import java.util.List;

/**
 *
 * @author Cursos Livres
 */
public interface MoradiaDAO extends BaseDAO<Moradia, Long> {
    List<Moradia> pesquisaLikeTelefone(String telefone);
    List<Moradia> pesquisaLogradouro(Logradouro logradouro);
    List<Moradia> pesquisaLikeNumero(String numero);
}
