/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Endereco;
import java.util.List;

/**
 *
 * @author Junior
 */
public class EnderecoDAOImp extends BaseDAOImp<Endereco, Long> implements EnderecoDAO{

    @Override
    public Endereco pesquisaPorId(Long id) {
        throw new UnsupportedOperationException("Nenhum método foi implementado para "
                + "o pesquisaPorID do endereço.");
    }

    @Override
    public List<Endereco> getTodos() {
        throw new UnsupportedOperationException("Nenhum método foi implementado para "
                + "o getTodos do endereço.");
    }
    
}
