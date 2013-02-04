package br.sc.senac.pontoeletronico.services;

import br.sc.senac.pontoeletronico.integracao.DAOFactoryController;
import br.sc.senac.pontoeletronico.integracao.PontoEletronicoDAOAbstractFactory;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService (serviceName="UsuarioFacade")
public class UsuarioFacade {
    
    @WebMethod (operationName="autentica")
    public int autentica(@WebParam(name="login") String login,@WebParam(name="senha") String senha){
        System.out.println("Processando Serviço de autenticação...");
        PontoEletronicoDAOAbstractFactory factory=DAOFactoryController.getDaoFactory();
        System.out.println("Usuário: "+login + ", senha: "+senha);
        return factory.getDaoUsuario().autentica(login, senha);
    }
}