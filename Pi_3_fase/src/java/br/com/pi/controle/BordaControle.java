/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.BordaDAO;
import br.com.pi.dao.BordaDAOImp;
import br.com.pi.entidade.Borda;
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
public class BordaControle {

    private Borda borda;
    private BordaDAO dao;
    private DataModel model;
        
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Borda getBorda() {
        if (borda == null) {
            borda = new Borda();
        }
        return borda;
    }

    public void setBorda(Borda usuario) {
        this.borda = usuario;
    }
    
    private void limpar() {
        borda = null;
        model = null;
    }
    
    public String novo() {
        borda = new Borda();
        return "cadBorda.faces";
    }
    
    public String pesq() {
        limpar();
        return "pesqBorda.faces";
    }
    
    public String salvar() {
        dao = new BordaDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (borda.getId() == null) {
            try {
                dao.salva(borda);
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Borda ja Cadastrado!", ""));
                return "cadBorda.faces";
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Borda Salvo Com Sucesso!", ""));
        } else {
            dao.altera(borda);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Borda Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqBorda.faces";
    }
    
    public void pesquisa() {
        dao = new BordaDAOImp();
        List<Borda> bordaes = dao.getTodos();
        model = new ListDataModel(bordaes);
        FacesContext context = FacesContext.getCurrentInstance();
        if (bordaes.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Borda inesistente!", "Borda inesistente!"));
            limpar();
        }
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new BordaDAOImp();
            borda = (Borda) model.getRowData();
            dao.remove(borda);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Borda Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }
    
    public String alterar() {
        borda = (Borda) model.getRowData();
        return "cadBordab.faces";
    }
}
