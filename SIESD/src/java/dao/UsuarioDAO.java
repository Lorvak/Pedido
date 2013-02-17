/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Pessoa;
import entidade.Usuario;
import java.util.List;

/**
 *
 * @author Junior
 */
public interface UsuarioDAO extends BaseDAO<Usuario, Long> {

    public List pesquisaUsuario(String login);
    
    public Pessoa usuario(String login);

    public Usuario loga(Usuario usu);
}
