/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Aluno;
import entidade.Professor;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Junior
 */
public class ProfessorDAOImp extends BaseDAOImp<Professor, Long> implements ProfessorDAO {

    @Override
    public Professor pesquisaPorId(Long id) {
        abreConexao();
        Professor professor = (Professor) session.get(Professor.class, id);
        return professor;
    }

    @Override
    public List<Professor> getTodos() {
        abreConexao();
        Query query = session.createQuery("from Professor");
        List<Professor> professores = query.list();
        return professores;
    }

    @Override
    public List<Professor> pesquisaProfessor(String nome, String cpf) {
        session = AbreTransacao.abreSessao().openSession();
        Query query;
        if (!nome.equals("")) {
            query = session.createQuery("Select distinct(al) from Professor al where nome like :valor");
            query.setString("valor", "%" + nome + "%");
        } else {
            query = session.createQuery("Select distinct(al) from Professor al  where cpf = :valor");
            query.setString("valor", cpf);
        }
        List<Professor> alunos = query.list();

        session.close();
        return alunos;
    }

    @Override
    public String ultimoCracha() {
        session = AbreTransacao.abreSessao().openSession();
        Query query = session.createQuery("Select max(professor.cracha) from Professor professor ");
        String cracha = (String) query.uniqueResult();
        session.close();
        return cracha;

    }
}
