/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Endereco;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Liana
 */
public class EnderecoDAOImp extends BaseDAOImp<Endereco, Long> implements EnderecoDAO {

    @Override
    public Endereco pesquisa(Long id) {
        abreConexao();
        Endereco endereco = (Endereco) session.get(Endereco.class, id);
        fechaConexao();
        return endereco;
    }

    @Override
    public List<Endereco> getTodos() {
         session = (Session) FabricaSessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Endereco e");
        List<Endereco> enderecos = query.list();
        fechaConexao();
        return enderecos;
    }

    @Override
    public List<Endereco> pesquisaLikeTelefone(String telefone) {
        abreConexao();
        Query query = session.createQuery("FROM Endereco e WHERE e.telefone like  :valor");
        query.setString("valor", "%" + telefone + "%");
        List<Endereco> enderecos = query.list();
        fechaConexao();
        return enderecos;
    }

    @Override
    public List<Endereco> pesquisaLikeId(Long id) {
        abreConexao();
        Query query = session.createQuery("FROM Endereco e WHERE e.logradouro =:id");
        query.setLong("id", id);
        List<Endereco> enderecos = query.list();
        fechaConexao();
        return enderecos;
    }
}
