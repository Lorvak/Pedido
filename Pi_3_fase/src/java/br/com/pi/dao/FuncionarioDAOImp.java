/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Funcionario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Liana
 */
public class FuncionarioDAOImp extends BaseDAOImp<Funcionario, Long> implements FuncionarioDAO {

    @Override
    public Funcionario pesquisa(Long id) {
        abreConexao();
        Funcionario funcionario = (Funcionario) session.get(Funcionario.class, id);
        fechaConexao();
        return funcionario;
    }

    @Override
    public List<Funcionario> getTodos() {
        abreConexao();
        Query query = session.createQuery("FROM Funcionario f");
        List<Funcionario> funcionarios = query.list();
        fechaConexao();
        return funcionarios;
    }

    @Override
    public List<Funcionario> pesquisaLikeNome(String nome) {
        abreConexao();
        Query query = session.createQuery("FROM Funcionario f WHERE f.nome like  :valor");
        query.setString("valor", "%" + nome + "%");
        List<Funcionario> funcionarios = query.list();
        fechaConexao();
        return funcionarios;
    }
    
    @Override
    public Funcionario pesquisaLikecracha(String cracha) {
        abreConexao();
        Query query = session.createQuery("FROM Funcionario f WHERE f.cracha like  :valor");
        query.setString("valor", "%" + cracha + "%");
        Funcionario funcionario = (Funcionario) query.uniqueResult();
        fechaConexao();
        return funcionario;
    }

}
