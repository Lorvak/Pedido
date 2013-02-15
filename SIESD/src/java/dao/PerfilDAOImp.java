/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Perfil;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Junior
 */
public class PerfilDAOImp extends BaseDAOImp<Perfil, Long> implements PerfilDAO {

    @Override
    public Perfil pesquisaPorId(Long id) {
        session = AbreTransacao.abreSessao().openSession();
        Perfil perfil = (Perfil) session.get(Perfil.class, id);
        session.close();
        return perfil;
    }

    @Override
    public List<Perfil> getTodos() {
        session = AbreTransacao.abreSessao().openSession();
        Query query = session.createQuery("from Perfil");
        List<Perfil> perfis = query.list();
        session.close();
        return perfis;
    }

    @Override
    public List pesquisaPerfil(String nome) {
        session = AbreTransacao.abreSessao().openSession();
        Query query;

        if (!nome.equals("")) {
            query = session.createQuery("from Perfil as perfil "
                    + " where perfil.nome like :nome");
            query.setString("nome", "%" + nome + "%");

        } else {
            query = session.createQuery("from Perfil as perfil "
                    + " where perfil.nome like :nome");
            query.setString("nome", (nome + "%"));
        }
        List<Perfil> perfis = query.list();
        session.close();
        return perfis;
    }
}
