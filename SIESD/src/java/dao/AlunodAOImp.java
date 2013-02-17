/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Aluno;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Junior
 */
public class AlunodAOImp extends BaseDAOImp<Aluno, Long> implements AlunoDAO {

    @Override
    public Aluno pesquisaPorId(Long id) {
        session = AbreTransacao.abreSessao().openSession();
        Aluno aluno = (Aluno) session.get(Aluno.class, id);
        session.close();
        return aluno;
    }

    @Override
    public List<Aluno> getTodos() {
        session = AbreTransacao.abreSessao().openSession();
        Query query = session.createQuery("from Aluno");
        List<Aluno> alunos = query.list();
        session.close();
        return alunos;
    }

    @Override
    public String ultimaMatricula() {
        session = AbreTransacao.abreSessao().openSession();
        Query query = session.createQuery("Select max(aluno.numMatricula) from Aluno aluno ");
        String matricula = (String) query.uniqueResult();
        session.close();
        return matricula;
    }

    public static void main(String[] args) {
        AlunoDAO adao = new AlunodAOImp();
        adao.ultimaMatricula();
    }

    @Override
    public List<Aluno> pesquisaAluno(String nome, String cpf) {
        session = AbreTransacao.abreSessao().openSession();
        Query query;
        if (!nome.equals("")) {
            query = session.createQuery("Select distinct(al) from Aluno al where nome like :valor");
            query.setString("valor", "%" + nome + "%");
        } else {
            query = session.createQuery("Select distinct(al) from Aluno al  where cpf = :valor");
            query.setString("valor", cpf);
        }
        List<Aluno> alunos = query.list();
       
        session.close();
        return alunos;
    }
}
