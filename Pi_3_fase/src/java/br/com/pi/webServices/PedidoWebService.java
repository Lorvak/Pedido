/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.webServices;

import br.com.pi.dao.PedidoDAO;
import br.com.pi.dao.PedidoDAOImp;
import br.com.pi.entidade.Pedido;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Aluno
 */
@WebService(serviceName = "PedidoWebService")
public class PedidoWebService {

    
    @WebMethod(operationName = "hello")
    public void hello(@WebParam(name = "name") Pedido txt) {
        PedidoDAO pedidoDAO = new PedidoDAOImp();
        pedidoDAO.salva(txt);
    }
}
