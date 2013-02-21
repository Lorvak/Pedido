/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.webServices;

import br.com.pi.dao.UsuarioDAO;
import br.com.pi.dao.UsuarioDAOImp;
import br.com.pi.entidade.Usuario;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author root
 */
@WebService(serviceName = "LogarService")
public class LogarService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "logar")
    public String logar(@WebParam(name = "login") String login, @WebParam(name="senha") String senha) {
        UsuarioDAO usuDao = new UsuarioDAOImp();
        Usuario usuario = usuDao.pesquisaLoginSenha(login, senha);
        if(usuario != null){
            return usuario.getId().toString();
        }else{
            return "0";
        }
    }
}
