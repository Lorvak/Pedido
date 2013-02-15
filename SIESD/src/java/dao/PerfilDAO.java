/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Perfil;
import java.util.List;

/**
 *
 * @author Junior
 */
public interface PerfilDAO extends BaseDAO<Perfil, Long> {
    
    public List pesquisaPerfil(String nome);
}
