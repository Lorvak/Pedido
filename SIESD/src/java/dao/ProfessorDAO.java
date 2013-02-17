/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Aluno;
import entidade.Professor;
import java.util.List;

/**
 *
 * @author Junior
 */
public interface ProfessorDAO extends BaseDAO<Professor, Long> {

    public List<Professor> pesquisaProfessor(String nome, String cpf);

    public String ultimoCracha();
    
}
