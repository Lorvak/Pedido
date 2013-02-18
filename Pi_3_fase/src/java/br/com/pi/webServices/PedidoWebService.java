/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.webServices;

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
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
