/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.webServices;

import br.com.pi.dao.BebidaDAO;
import br.com.pi.dao.BebidaDAOImp;
import br.com.pi.dao.MesaDAO;
import br.com.pi.dao.MesaDAOImp;
import br.com.pi.dao.PedidoDAO;
import br.com.pi.dao.PedidoDAOImp;
import br.com.pi.entidade.Bebida;
import br.com.pi.entidade.Mesa;
import br.com.pi.entidade.Pedido;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Aluno
 */
@WebService(serviceName = "PedidoWebService")
public class PedidoWebService {

    
    @WebMethod(operationName = "Bem vindo!")
    public void hello(@WebParam(name = "name") Pedido txt) {
//        PedidoDAO pedidoDAO = new PedidoDAOImp();
//        pedidoDAO.salva(txt);
    }
    
    @WebMethod(operationName = "listarMesas")
    public String[] listarMesas(){
        MesaDAO mesaDAO = new MesaDAOImp();
        List<Mesa> mesas = mesaDAO.getTodos();
        String[] saida = new String[mesas.size()];
        for (int i = 0; i < mesas.size(); i++) {
            saida[i] = mesas.get(i).getId().toString() + "-" + mesas.get(i).getNumero();
        }
        return saida;
    }
    
    @WebMethod(operationName = "listarBebidas")
    public String[] listarBebidas(){
        BebidaDAO bebidaDAO = new BebidaDAOImp();
        List<Bebida> bebidas = bebidaDAO.getTodos();
        String[] saida = new String[bebidas.size()];
        for (int i = 0; i < bebidas.size(); i++) {
            saida[i] = bebidas.get(i).getId().toString() + "-" + bebidas.get(i).getNome();
        }
        return saida;
    }
}
