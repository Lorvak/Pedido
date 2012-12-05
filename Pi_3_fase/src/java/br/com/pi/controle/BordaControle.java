/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.BordaDAO;
import br.com.pi.dao.BordaDAOImp;
import br.com.pi.entidade.Borda;
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
public class BordaControle {

    private Borda borda;
    private BordaDAO bordaDAO;
    private DataModel model;

    public Borda getBorda() {
        if (borda == null) {
            borda = new Borda();
        }
        return borda;
    }

    public void setBorda(Borda borda) {
        this.borda = borda;
    }

    public BordaDAO getBordaDAO() {
        return bordaDAO;
    }

    public void setBordaDAO(BordaDAO bordaDAO) {
        this.bordaDAO = bordaDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        bordaDAO = new BordaDAOImp();
        if (borda.getId() == null) {
            bordaDAO.salva(borda);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Borda salvo com sucesso!", ""));
        } else {
            bordaDAO.altera(borda);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Borda alterado com sucesso!", ""));
        }
        limpar();
        return "cadBorda";
    }

    private void limpar() {
        borda = null;
        model = null;
    }
     public String limpaPesquisa() {
        limpar();
        return "pesqBorda";
    }

    public void pesquisaLike() {
        bordaDAO = new BordaDAOImp();
        if (borda != null) {
            List<Borda> bordas = bordaDAO.getTodos();
            model = new ListDataModel(bordas);
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoBorda() {
        borda = new Borda();

        return "cadBorda";
    }

    public void excluir(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            bordaDAO = new BordaDAOImp();
            borda = (Borda) model.getRowData();
            bordaDAO.remove(borda);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Borda excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        borda = (Borda) model.getRowData();
        setBorda(borda);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Borda alterado com sucesso!", ""));
        return "cadBorda";

    }

    public String btPesquisar() {
        borda = null;
        return "pesqBorda";
    }
}
