/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Responsavel;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Junior
 */
public class ResponsavelDAOImp extends BaseDAOImp<Responsavel, Long> implements ResponsavelDAO{

    @Override
    public Responsavel pesquisaPorId(Long id) {
        abreConexao();
        Responsavel responsavel = (Responsavel) session.get(Responsavel.class, id);
        fechaConexao();
        return responsavel;
    }

    @Override
    public List<Responsavel> getTodos() {
        abreConexao();
        Query query = session.createQuery("from Responsavel");
        List<Responsavel> responsavels = query.list();
        return responsavels;
    }

    @Override
    public Responsavel pesquisaPorCpf(String cpf) {
       abreConexao();
       Query query = session.createQuery("from Responsavel resp where resp.cpf = :cpf");
       query.setString("cpf", cpf);
       Responsavel responsavel = (Responsavel) query.uniqueResult();
       fechaConexao();
       return responsavel;
    }
    
    public static void main(String[] args) {
        ResponsavelDAOImp dAOImp = new ResponsavelDAOImp();
        dAOImp.pesquisaPorCpf("3242342");
    }
    
}
