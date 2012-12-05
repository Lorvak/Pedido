/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.ClienteDAO;
import br.com.pi.dao.ClienteDAOImp;
import br.com.pi.entidade.Cliente;
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
public class ClienteControle {

    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private DataModel model;

    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        clienteDAO = new ClienteDAOImp();
        if (cliente.getId() == null) {
            clienteDAO.salva(cliente);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Cliente salvo com sucesso!", ""));
        } else {
            clienteDAO.altera(cliente);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Cliente alterado com sucesso!", ""));
        }
        limpar();
        return "cadCliente";
    }

    private void limpar() {
        cliente = null;
        model = null;
    }
     public String limpaPesquisa() {
        limpar();
        return "pesqCliente";
    }

    public void pesquisaLike() {
        clienteDAO = new ClienteDAOImp();
        if (cliente != null) {
            List<Cliente> clientes = clienteDAO.pesquisaLikeNome(cliente.getNome());
            model = new ListDataModel(clientes);
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoCliente() {
        cliente = new Cliente();

        return "cadCliente";
    }

    public void excluir(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            clienteDAO = new ClienteDAOImp();
            cliente = (Cliente) model.getRowData();
            clienteDAO.remove(cliente);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Cliente excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        cliente = (Cliente) model.getRowData();
        setCliente(cliente);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Cliente alterado com sucesso!", ""));
        return "cadCliente";

    }

    public String btPesquisar() {
        cliente = null;
        return "pesqCliente";
    }
}
