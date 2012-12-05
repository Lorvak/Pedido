/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import br.com.pi.entidade.Perfil;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Liana
 */
public class PerfilDAOImp extends BaseDAOImp<Perfil, Long> implements PerfilDAO{

    @Override
    public Perfil pesquisa(Long id) {
        abreConexao();
        Perfil perfil = (Perfil) session.get(Perfil.class, id);
        fechaConexao();
        return perfil;
    }

    @Override
    public List<Perfil> getTodos() {
        abreConexao();
        Query query = session.createQuery("FROM Perfil b");
        List<Perfil> perfils = query.list();
        fechaConexao();
        return perfils;
    }

    @Override
    public List<Perfil> pesquisaLikeNome(String nome) {
        abreConexao();
        Query query = session.createQuery("FROM Perfil u WHERE u.nome like  :valor");
        query.setString("valor", "%" + nome + "%");
        List<Perfil> perfils = query.list();
        fechaConexao();
        return perfils;
    }
} 
