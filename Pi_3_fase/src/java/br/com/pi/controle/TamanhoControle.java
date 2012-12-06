/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.TamanhoDAO;
import br.com.pi.dao.TamanhoDAOImp;
import br.com.pi.entidade.Tamanho;
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
public class TamanhoControle {

    private Tamanho tamanho;
    private TamanhoDAO dao;
    private DataModel model;
        
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Tamanho getTamanho() {
        if (tamanho == null) {
            tamanho = new Tamanho();
        }
        return tamanho;
    }

    public void setTamanho(Tamanho usuario) {
        this.tamanho = usuario;
    }
    
    private void limpar() {
        tamanho = null;
        model = null;
    }
    
    public String novo() {
        tamanho = new Tamanho();
        return "cadTamanho.faces";
    }
    
    public String pesq() {
        limpar();
        return "pesqTamanho.faces";
    }
    
    public String salvar() {
        dao = new TamanhoDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (tamanho.getId() == null) {
            dao.salva(tamanho);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tamanho Salvo Com Sucesso!", ""));
        } else {
            dao.altera(tamanho);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tamanho Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqTamanho.faces";
    }
    
    public void pesquisa() {
        dao = new TamanhoDAOImp();
        List<Tamanho> tamanhoes = dao.getTodos();
        model = new ListDataModel(tamanhoes);
        FacesContext context = FacesContext.getCurrentInstance();
        if (tamanhoes.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Tamanho inesistente!"));
            limpar();
        }
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new TamanhoDAOImp();
            tamanho = (Tamanho) model.getRowData();
            dao.remove(tamanho);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tamanho Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }
    
    public String alterar() {
        tamanho = (Tamanho) model.getRowData();
        return "cadTamanho.faces";
    }
}
