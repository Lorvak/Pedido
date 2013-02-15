/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Aluno;
import java.util.List;

/**
 *
 * @author Junior
 */
public interface AlunoDAO extends BaseDAO<Aluno, Long> {

    public String ultimaMatricula();
    
    public List<Aluno> pesquisaAluno(String nome, String cpf);
    
}
