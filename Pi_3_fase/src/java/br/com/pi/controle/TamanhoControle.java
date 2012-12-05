/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.TamanhoDAO;
import br.com.pi.dao.TamanhoDAOImp;
import br.com.pi.entidade.Tamanho;
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
public class TamanhoControle {

    private Tamanho tamanho;
    private TamanhoDAO tamanhoDAO;
    private DataModel model;

    public Tamanho getTamanho() {
        if (tamanho == null) {
            tamanho = new Tamanho();
        }
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public TamanhoDAO getTamanhoDAO() {
        return tamanhoDAO;
    }

    public void setTamanhoDAO(TamanhoDAO tamanhoDAO) {
        this.tamanhoDAO = tamanhoDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        tamanhoDAO = new TamanhoDAOImp();
        if (tamanho.getId() == null) {
            tamanhoDAO.salva(tamanho);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Tamanho salvo com sucesso!", ""));
        } else {
            tamanhoDAO.altera(tamanho);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Tamanho alterado com sucesso!", ""));
        }
        limpar();
        return "cadTamanho";
    }

    private void limpar() {
        tamanho = null;
        model = null;
    }
     public String limpaPesquisa() {
        limpar();
        return "pesqTamanho";
    }

    public void pesquisaLike() {
        tamanhoDAO = new TamanhoDAOImp();
        if (tamanho != null) {
            List<Tamanho> tamanhos = tamanhoDAO.getTodos();
            model = new ListDataModel(tamanhos);
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoTamanho() {
        tamanho = new Tamanho();

        return "cadTamanho";
    }

    public void excluir(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            tamanhoDAO = new TamanhoDAOImp();
            tamanho = (Tamanho) model.getRowData();
            tamanhoDAO.remove(tamanho);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Tamanho excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        tamanho = (Tamanho) model.getRowData();
        setTamanho(tamanho);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Tamanho alterado com sucesso!", ""));
        return "cadTamanho";

    }

    public String btPesquisar() {
        tamanho = null;
        return "pesqTamanho";
    }
}
