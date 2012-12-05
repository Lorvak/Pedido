/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.BebidaDAO;
import br.com.pi.dao.BebidaDAOImp;
import br.com.pi.entidade.Bebida;
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
public class BebidaControle {

    private Bebida bebida;
    private BebidaDAO bebidaDAO;
    private DataModel model;

    public Bebida getBebida() {
        if (bebida == null) {
            bebida = new Bebida();
        }
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public BebidaDAO getBebidaDAO() {
        return bebidaDAO;
    }

    public void setBebidaDAO(BebidaDAO bebidaDAO) {
        this.bebidaDAO = bebidaDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        bebidaDAO = new BebidaDAOImp();
        if (bebida.getId() == null) {
            bebidaDAO.salva(bebida);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Bebida salvo com sucesso!", ""));
        } else {
            bebidaDAO.altera(bebida);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Bebida alterado com sucesso!", ""));
        }
        limpar();
        return "cadBebida";
    }

    private void limpar() {
        bebida = null;
        model = null;
    }
     public String limpaPesquisa() {
        limpar();
        return "pesqBebida";
    }

    public void pesquisaLike() {
        bebidaDAO = new BebidaDAOImp();
        if (bebida != null) {
            List<Bebida> bebidas = bebidaDAO.getTodos();
            model = new ListDataModel(bebidas);
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoBebida() {
        bebida = new Bebida();

        return "cadBebida";
    }

    public void excluir(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            bebidaDAO = new BebidaDAOImp();
            bebida = (Bebida) model.getRowData();
            bebidaDAO.remove(bebida);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Bebida excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        bebida = (Bebida) model.getRowData();
        setBebida(bebida);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Bebida alterado com sucesso!", ""));
        return "cadBebida";

    }

    public String btPesquisar() {
        bebida = null;
        return "pesqBebida";
    }
}
