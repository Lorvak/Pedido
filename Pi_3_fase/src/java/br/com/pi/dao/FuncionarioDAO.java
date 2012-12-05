/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Funcionario;
import java.util.List;

/**
 *
 * @author Liana
 */
public interface FuncionarioDAO extends BaseDAO<Funcionario, Long> {

    List<Funcionario> pesquisaLikeNome(String nome);

   
}
