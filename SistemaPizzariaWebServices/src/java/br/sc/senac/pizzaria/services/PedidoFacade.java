package br.sc.senac.pizzaria.services;

import br.android.pedido.Pedido;
import br.sc.senac.pontoeletronico.integracao.DAOFactoryController;
import br.sc.senac.pontoeletronico.integracao.DAOAbstractFactory;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService (serviceName="PedidoFacade")
public class PedidoFacade {
    
    @WebMethod (operationName="insere")
    public int insere(@WebParam(name="sabor") String sabor,@WebParam(name="tamanho") String tamanho,@WebParam(name="borda") String borda){
        System.out.println("Processando cadastro....");
        DAOAbstractFactory factory=DAOFactoryController.getDaoFactory();
        Pedido p= new Pedido();
        p.setBorda(borda);
        p.setTamanho(tamanho);
        p.setSabor(sabor);
        return factory.getDaoPedido().insere(p);
    }
}