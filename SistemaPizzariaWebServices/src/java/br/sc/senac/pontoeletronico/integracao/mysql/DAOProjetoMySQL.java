/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.integracao.mysql;

import br.sc.senac.pontoeletronico.integracao.iDAOProjeto;
import br.sc.senac.pontoeletronico.modelo.Projeto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
public class DAOProjetoMySQL implements iDAOProjeto {

    @Override
    public Projeto[] getProjetos() {
        EntityManager em = new DataSourceMySQL().getEntityManager();
        Query query= em.createQuery("select p from Projeto p");
        List objectList=query.getResultList();
        Projeto[] lista=new Projeto[objectList.size()];
        int i=0;
        for(Object obj:objectList){
            lista[i]=(Projeto)obj;
            i++;
        }
        return lista;
    }
    
}
