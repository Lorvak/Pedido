/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.BebidaDAO;
import br.com.pi.dao.BebidaDAOImp;
import br.com.pi.entidade.Bebida;
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
public class BebidaControle {

    private Bebida bebida;
    private BebidaDAO dao;
    private DataModel model;
        
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Bebida getBebida() {
        if (bebida == null) {
            bebida = new Bebida();
        }
        return bebida;
    }

    public void setBebida(Bebida usuario) {
        this.bebida = usuario;
    }
    
    private void limpar() {
        bebida = null;
        model = null;
    }
    
    public String novo() {
        bebida = new Bebida();
        return "cadBebida.faces";
    }
    
    public String pesq() {
        limpar();
        return "pesqBebida.faces";
    }
    
    public String salvar() {
        dao = new BebidaDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (bebida.getId() == null) {
            dao.salva(bebida);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bebida Salvo Com Sucesso!", ""));
        } else {
            dao.altera(bebida);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bebida Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqBebida.faces";
    }
    
    public void pesquisa() {
        dao = new BebidaDAOImp();
        List<Bebida> bebidaes = dao.getTodos();
        model = new ListDataModel(bebidaes);
        FacesContext context = FacesContext.getCurrentInstance();
        if (bebidaes.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bebida inesistente!", "Bebida inesistente!"));
            limpar();
        }
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new BebidaDAOImp();
            dao.remove(bebida);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bebida Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }
    
    public String alterar() {
        bebida = (Bebida) model.getRowData();
        return "cadBebida.faces";
    }
}
