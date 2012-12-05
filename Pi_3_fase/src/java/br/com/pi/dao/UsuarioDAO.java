/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Usuario;
import java.util.List;

/**
 *
 * @author Liana
 */
public interface UsuarioDAO extends BaseDAO<Usuario, Long> {
    List<Usuario> pesquisaLikeLogin(String login);
}
