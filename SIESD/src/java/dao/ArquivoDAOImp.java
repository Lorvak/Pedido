/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Arquivo;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Junior
 */
public class ArquivoDAOImp extends BaseDAOImp<Arquivo, Long> implements ArquivoDAO{

    @Override
    public Arquivo pesquisaPorId(Long id) {
        session = AbreTransacao.abreSessao().openSession();
        Arquivo arquivo = (Arquivo) session.get(Arquivo.class, id);
        session.close();
        return arquivo;
    }

    @Override
    public List<Arquivo> getTodos() {
        session = AbreTransacao.abreSessao().openSession();
        Query query = session.createQuery("from Arquivo");
        List<Arquivo> arquivos = query.list();
        session.close();
        return arquivos;
    }
    
}
