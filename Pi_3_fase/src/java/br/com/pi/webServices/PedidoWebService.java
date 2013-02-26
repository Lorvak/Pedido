/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.webServices;

import br.com.pi.dao.BebidaDAO;
import br.com.pi.dao.BebidaDAOImp;
import br.com.pi.dao.FuncionarioDAO;
import br.com.pi.dao.FuncionarioDAOImp;
import br.com.pi.dao.MesaDAO;
import br.com.pi.dao.MesaDAOImp;
import br.com.pi.dao.PedidoDAO;
import br.com.pi.dao.PedidoDAOImp;
import br.com.pi.dao.PizzaDAO;
import br.com.pi.dao.PizzaDAOImp;
import br.com.pi.dao.UsuarioDAO;
import br.com.pi.dao.UsuarioDAOImp;
import br.com.pi.entidade.Bebida;
import br.com.pi.entidade.Funcionario;
import br.com.pi.entidade.Mesa;
import br.com.pi.entidade.Pedido;
import br.com.pi.entidade.Pizza;
import br.com.pi.entidade.SaborSelecionado;
import br.com.pi.entidade.Usuario;
import java.util.ArrayList;
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
    
    @WebMethod(operationName = "listarPizzas")
    public String[] listarPizzas(){
        PizzaDAO pizzaDAO = new PizzaDAOImp();
        List<Pizza> pizzas = pizzaDAO.getTodos();
        String[] saida = new String[pizzas.size()];
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza p= pizzas.get(i);
            String sabores="";
            for(SaborSelecionado ss: p.getSabores()){
                sabores+=ss.getSabor().getNome()+"/";
            }
            saida[i] =p.getId().toString() + "-" + p.getBorda().getNome() +" - "+p.getTamanho().getNome()+" - "+sabores;
        }
        return saida;
    }
    
 @WebMethod(operationName = "listarPedidos")   
 public String[] listarPedidos(){
        PedidoDAO pedidoDAO = new PedidoDAOImp();
        List<Pedido> pedidos = pedidoDAO.getTodos();
        String[] saida = new String[pedidos.size()];
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p= pedidos.get(i);
            saida[i] =p.getId().toString() + " | "+ p.getMesa().getNumero() +" |" +p.getFuncionario().getNome();
            
        }
        return saida;
    }
    
     @WebMethod(operationName = "realizaPedido")
    public String realizaPedido(@WebParam(name = "idFuncionario") String idFuncionario, @WebParam(name="idMesa") String idMesa,@WebParam(name="idPizza") String idPizza,@WebParam(name="idBebida") String idBebida) {
        PedidoDAO pedidoDAO = new PedidoDAOImp();
        MesaDAO mesaDAO= new MesaDAOImp();
        FuncionarioDAO funcDAO= new FuncionarioDAOImp();
        PizzaDAO pDAO= new PizzaDAOImp();
        BebidaDAO bDAO= new BebidaDAOImp();
        
        Pedido p= new Pedido();
        Funcionario f= funcDAO.pesquisa(Long.valueOf(idFuncionario));
        p.setFuncionario(f);
        Bebida b= bDAO.pesquisa(Long.valueOf(idBebida));
        p.setAberto(true);
        List<Bebida> listBebida= new ArrayList<Bebida>();
        listBebida.add(b);
        p.setBebidas(listBebida);
        List<Pizza> listPizza = new ArrayList<Pizza>();
        Pizza p1=pDAO.pesquisa(Long.valueOf((idPizza)));
        listPizza.add(p1);
        p.setPizzas(listPizza);
        Mesa m=mesaDAO.pesquisa(Long.valueOf(idMesa));
        p.setMesa(m);
        pedidoDAO.salva(p);
        return String.valueOf(p.getId());
    }
}
