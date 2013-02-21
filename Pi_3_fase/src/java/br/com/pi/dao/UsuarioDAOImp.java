/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Liana
 */
public class UsuarioDAOImp extends BaseDAOImp<Usuario, Long> implements UsuarioDAO {

    @Override
    public Usuario pesquisa(Long id) {
        abreConexao();
        Usuario bairro = (Usuario) session.get(Usuario.class, id);
        fechaConexao();
        return bairro;
    }

    @Override
    public List<Usuario> getTodos() {
        abreConexao();
        Query query = session.createQuery("FROM Usuario b");
        List<Usuario> bairro = query.list();
        fechaConexao();
        return bairro;
    }

    @Override
    public List<Usuario> pesquisaLikeLogin(String login) {
        abreConexao();
        Query query = session.createQuery("FROM Usuario u WHERE u.login like  :valor");
        query.setString("valor", "%" + login + "%");
        List<Usuario> bairros = query.list();
        fechaConexao();
        return bairros;
    }

    @Override
    public Usuario pesquisaLoginSenha(String login, String senha) {
        abreConexao();
        Query query = session.createQuery("FROM Usuario u WHERE u.login = :login AND u.senha = :senha");
        query.setString("login", login);
        query.setString("senha", senha);
        Usuario usuario = (Usuario) query.uniqueResult();
        return usuario;
    }
}
