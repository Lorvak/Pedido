/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Endereco;
import java.util.List;

/**
 *
 * @author Liana
 */
public interface EnderecoDAO extends BaseDAO<Endereco, Long> {
    List<Endereco> pesquisaLikeTelefone(String telefone);

    List<Endereco> pesquisaLikeId(Long id);
}
