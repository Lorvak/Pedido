/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.SaborDAO;
import br.com.pi.dao.SaborDAOImp;
import br.com.pi.entidade.Sabor;
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
public class SaborControle {

    private Sabor sabor;
    private SaborDAO saborDAO;
    private DataModel model;

    public Sabor getSabor() {
        if (sabor == null) {
            sabor = new Sabor();
        }
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public SaborDAO getSaborDAO() {
        return saborDAO;
    }

    public void setSaborDAO(SaborDAO saborDAO) {
        this.saborDAO = saborDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        saborDAO = new SaborDAOImp();
        if (sabor.getId() == null) {
            saborDAO.salva(sabor);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sabor salvo com sucesso!", ""));
        } else {
            saborDAO.altera(sabor);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sabor alterado com sucesso!", ""));
        }
        limpar();
        return "cadSabor";
    }

    private void limpar() {
        sabor = null;
        model = null;
    }
     public String limpaPesquisa() {
        limpar();
        return "pesqSabor";
    }

    public void pesquisaLike() {
        saborDAO = new SaborDAOImp();
        if (sabor != null) {
            List<Sabor> sabors = saborDAO.getTodos();
            model = new ListDataModel(sabors);
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoSabor() {
        sabor = new Sabor();

        return "cadSabor";
    }

    public void excluir(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            saborDAO = new SaborDAOImp();
            sabor = (Sabor) model.getRowData();
            saborDAO.remove(sabor);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sabor excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        sabor = (Sabor) model.getRowData();
        setSabor(sabor);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sabor alterado com sucesso!", ""));
        return "cadSabor";

    }

    public String btPesquisar() {
        sabor = null;
        return "pesqSabor";
    }
}
