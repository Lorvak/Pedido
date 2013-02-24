/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.MesaDAO;
import br.com.pi.dao.MesaDAOImp;
import br.com.pi.entidade.Mesa;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author Eduardo Moraes Silveira
 */
@ManagedBean
@SessionScoped
public class MesaControle {

    private Mesa mesa;
    private MesaDAO dao;
    private DataModel model;
        
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Mesa getMesa() {
        if (mesa == null) {
            mesa = new Mesa();
        }
        return mesa;
    }

    public void setMesa(Mesa usuario) {
        this.mesa = usuario;
    }
    
    private void limpar() {
        mesa = null;
        model = null;
    }
    
    public String novo() {
        mesa = new Mesa();
        return "cadMesa.faces";
    }
    
    public String pesq() {
        limpar();
        return "pesqMesa.faces";
    }
    
    public String salvar() {
        dao = new MesaDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (mesa.getId() == null) {
            try {
                dao.salva(mesa);
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mesa ja Cadastrada!", ""));
                return "cadMesa.faces";
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mesa Salva Com Sucesso!", ""));
        } else {
            dao.altera(mesa);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mesa Alterada Com Sucesso!", ""));
        }
        limpar();
        return "pesqMesa.faces";
    }
    
    public void pesquisa() {
        dao = new MesaDAOImp();
        List<Mesa> mesaes = dao.getTodos();
        model = new ListDataModel(mesaes);
        FacesContext context = FacesContext.getCurrentInstance();
        if (mesaes.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mesa inesistente!", "Mesa inesistente!"));
            limpar();
        }
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new MesaDAOImp();
            mesa = (Mesa) model.getRowData();
            dao.remove(mesa);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mesa Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }
    
    public String alterar() {
        mesa = (Mesa) model.getRowData();
        return "cadMesa.faces";
    }
}
