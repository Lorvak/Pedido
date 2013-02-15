/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Pessoa;
import entidade.Usuario;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Junior
 */
public class UsuarioDAOImp extends BaseDAOImp<Usuario, Long> implements UsuarioDAO {

    @Override
    public Usuario pesquisaPorId(Long id) {
        abreConexao();
        Usuario usuario = (Usuario) session.get(Usuario.class, id);
        fechaConexao();
        return usuario;
    }

    @Override
    public List<Usuario> getTodos() {
        abreConexao();
        Query query = session.createQuery("from Usuario");
        List<Usuario> usuarios = query.list();
        fechaConexao();
        return usuarios;
    }

    @Override
    public List pesquisaUsuario(String login) {
        abreConexao();
        Query query = null;
        if (!login.equals("")) {
            query = session.createQuery("from Usuario as usuario "
                    + " where usuario.login = :login");
            query.setString("login", login);
        }
        List<Usuario> usuarios = query.list();
        session.close();
        return usuarios;
    }

    @Override
    public Usuario loga(Usuario usu) {
        abreConexao();
        Query query;
        Usuario usuario = null;
        if (usu.getLogin() != null) {
            if (!usu.getLogin().equals("")) {
                query = session.createQuery("from Usuario as usuario "
                        + " where  usuario.login = :login and usuario.senha = :senha ");
                query.setString("login", usu.getLogin());
                query.setString("senha", usu.getSenha());
                usuario = (Usuario) query.uniqueResult();
            }
        }
        if (usuario != null) {
            usuario.setLogado(true);
            return usuario;
        }
        fechaConexao();
        return usu;
    }

    @Override
    public Pessoa usuario(String login) {
        abreConexao();
        Query query;
        Pessoa pessoa;
        query = session.createQuery("from Pessoa as pessoa "
                + " where  pessoa.usuario.login = :login ");
        query.setString("login", login);
        pessoa = (Pessoa) query.uniqueResult();
        session.close();
        return pessoa;
    }
    public static void main(String[] args) {
        UsuarioDAOImp usu = new UsuarioDAOImp();
        Usuario usuario = new Usuario();
        usuario.setLogin("tjunior103@hotmail.com");
        usuario.setSenha("12345");
        usu.loga(usuario);
    }
   
}
