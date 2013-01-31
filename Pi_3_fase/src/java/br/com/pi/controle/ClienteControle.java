/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.ClienteDAO;
import br.com.pi.dao.ClienteDAOImp;
import br.com.pi.dao.MoradiaDAO;
import br.com.pi.dao.MoradiaDAOImp;
import br.com.pi.entidade.Cliente;
import br.com.pi.entidade.Logradouro;
import br.com.pi.entidade.Moradia;
import br.com.pi.entidade.Perfil;
import br.com.pi.entidade.Usuario;
import com.sun.security.ntlm.Client;
import java.util.ArrayList;
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
    private DataModel model2;
    private Moradia moradia;
    private MoradiaDAO moradiaDAO;
    private Logradouro logradouro;
    private Usuario usuario;
    private Perfil perfil;

    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setUsuario(new Usuario());
            cliente.getUsuario().setPerfil(new Perfil());
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

    public DataModel getModel2() {
        if(model2 == null){
            model2 = new ListDataModel();
        }
        return model2;
    }

    public void setModel2(DataModel model2) {
        this.model2 = model2;
    }

    public Moradia getMoradia() {
        if (moradia == null) {
            moradia = new Moradia();
        }
        return moradia;
    }

    public void setMoradia(Moradia moradia) {
        this.moradia = moradia;
    }

    public MoradiaDAO getMoradiaDAO() {
        return moradiaDAO;
    }

    public void setMoradiaDAO(MoradiaDAO moradiaDAO) {
        this.moradiaDAO = moradiaDAO;
    }

    public Logradouro getLogradouro() {
        if (logradouro == null) {
            logradouro = new Logradouro();
        }
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        if (perfil == null) {
            perfil = new Perfil();
        }
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        clienteDAO = new ClienteDAOImp();
        usuario.setPerfil(perfil);
        cliente.setUsuario(usuario);
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
        usuario = null;
        perfil = null;
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

    public String voltarAoCadastro() {

        return "cadCliente.faces";
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
        usuario = cliente.getUsuario();
        perfil = usuario.getPerfil();
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

    public String btNovoEndereco() {
        moradia = new Moradia();
        return "cadNMoradia";
    }

    public String salvarNovoEndereco() {
        if(cliente.getMoradias()==null){
            cliente.setMoradias(new ArrayList<Moradia>());
        }
        moradia.setLogradouro(logradouro);
        cliente.getMoradias().add(moradia);
        moradiaDAO = new MoradiaDAOImp();
//        moradiaDAO.salva(moradia);
        return "cadCliente";
    }
    
     public void pesquisaMoradias() {
        clienteDAO = new ClienteDAOImp();
        cliente = (Cliente) model.getRowData();
        cliente = clienteDAO.pesquisa(cliente.getId());
        model2 = new ListDataModel(cliente.getMoradias());
    }
}
