/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Cliente;
import br.com.pi.entidade.Perfil;
import java.util.List;

/**
 *
 * @author Liana
 */
public interface PerfilDAO extends BaseDAO<Perfil, Long> {
    List<Perfil> pesquisaLikeNome(String nome);
}
