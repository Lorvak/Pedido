/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.PedidoDAO;
import br.com.pi.dao.PedidoDAOImp;
import br.com.pi.entidade.Pedido;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Hugo
 */
@ManagedBean
@SessionScoped
public class PedidoTeleControle {

    private Pedido pedido;
    private PedidoDAO pedidoDAO;
    private DataModel model;

    public Pedido getPedido() {
        if (pedido == null) {
            pedido = new Pedido();
        }
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public PedidoDAO getPedidoDAO() {
        return pedidoDAO;
    }

    public void setPedidoDAO(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        pedidoDAO = new PedidoDAOImp();
        if (pedido.getId() == null) {
            pedidoDAO.salva(pedido);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Pedido salvo com sucesso!", ""));
        } else {
            pedidoDAO.altera(pedido);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Pedido alterado com sucesso!", ""));
        }
        limpar();
        return "cadPedido";
    }

    private void limpar() {
        pedido = null;
        model = null;
    }
     public String limpaPesquisa() {
        limpar();
        return "pesqPedido";
    }

    public void pesquisaLike() {
        pedidoDAO = new PedidoDAOImp();
        if (pedido != null) {
            List<Pedido> pedidos = pedidoDAO.getTodos();
            model = new ListDataModel(pedidos);
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoPedido() {
        pedido = new Pedido();

        return "cadPedido";
    }

    public void excluir(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            pedidoDAO = new PedidoDAOImp();
            pedido = (Pedido) model.getRowData();
            pedidoDAO.remove(pedido);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Pedido excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        pedido = (Pedido) model.getRowData();
        setPedido(pedido);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Pedido alterado com sucesso!", ""));
        return "cadPedido";

    }

    public String btPesquisar() {
        pedido = null;
        return "pesqPedido";
    }
}
