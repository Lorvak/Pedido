/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.SaborDAO;
import br.com.pi.dao.SaborDAOImp;
import br.com.pi.entidade.Sabor;
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
public class SaborControle {

    private Sabor sabor;
    private SaborDAO dao;
    private DataModel model;
        
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Sabor getSabor() {
        if (sabor == null) {
            sabor = new Sabor();
        }
        return sabor;
    }

    public void setSabor(Sabor usuario) {
        this.sabor = usuario;
    }
    
    private void limpar() {
        sabor = null;
        model = null;
    }
    
    public String novo() {
        sabor = new Sabor();
        return "cadSabor.faces";
    }
    
    public String pesq() {
        limpar();
        return "pesqSabor.faces";
    }
    
    public String salvar() {
        dao = new SaborDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (sabor.getId() == null) {
            dao.salva(sabor);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sabor Salvo Com Sucesso!", ""));
        } else {
            dao.altera(sabor);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sabor Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqSabor.faces";
    }
    
    public void pesquisa() {
        dao = new SaborDAOImp();
        List<Sabor> sabores = dao.getTodos();
        model = new ListDataModel(sabores);
        FacesContext context = FacesContext.getCurrentInstance();
        if (sabores.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sabor inesistente!", "Sabor inesistente!"));
            limpar();
        }
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new SaborDAOImp();
            sabor = (Sabor) model.getRowData();
            dao.remove(sabor);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sabor Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }
    
    public String alterar() {
        sabor = (Sabor) model.getRowData();
        return "cadSabor.faces";
    }
}
