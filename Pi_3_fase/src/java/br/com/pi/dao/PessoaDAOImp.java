/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Pessoa;
import java.util.List;

/**
 *
 * @author Liana
 */
public class PessoaDAOImp extends BaseDAOImp<Pessoa, Long> implements PessoaDAO {

    @Override
    public Pessoa pesquisa(Long id) {
        abreConexao();
        Pessoa pessoa = (Pessoa) session.get(Pessoa.class, id);
        fechaConexao();
        return pessoa;
    }

    @Override
    public List<Pessoa> getTodos() {
        abreConexao();
        List<Pessoa> list = session.createCriteria(Pessoa.class).list();
        fechaConexao();
        return list;
    }
}
