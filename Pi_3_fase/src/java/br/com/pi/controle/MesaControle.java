/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.MesaDAO;
import br.com.pi.dao.MesaDAOImp;
import br.com.pi.entidade.Mesa;
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
public class MesaControle {

    private Mesa mesa;
    private MesaDAO mesaDAO;
    private DataModel model;

    public Mesa getMesa() {
        if (mesa == null) {
            mesa = new Mesa();
        }
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public MesaDAO getMesaDAO() {
        return mesaDAO;
    }

    public void setMesaDAO(MesaDAO mesaDAO) {
        this.mesaDAO = mesaDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        mesaDAO = new MesaDAOImp();
        if (mesa.getId() == null) {
            mesaDAO.salva(mesa);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Mesa salvo com sucesso!", ""));
        } else {
            mesaDAO.altera(mesa);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Mesa alterado com sucesso!", ""));
        }
        limpar();
        return "cadMesa";
    }

    private void limpar() {
        mesa = null;
        model = null;
    }
     public String limpaPesquisa() {
        limpar();
        return "pesqMesa";
    }

    public void pesquisaLike() {
        mesaDAO = new MesaDAOImp();
        if (mesa != null) {
            List<Mesa> mesas = mesaDAO.getTodos();
            model = new ListDataModel(mesas);
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoMesa() {
        mesa = new Mesa();

        return "cadMesa";
    }

    public void excluir(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            mesaDAO = new MesaDAOImp();
            mesa = (Mesa) model.getRowData();
            mesaDAO.remove(mesa);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Mesa excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        mesa = (Mesa) model.getRowData();
        setMesa(mesa);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Mesa alterado com sucesso!", ""));
        return "cadMesa";

    }

    public String btPesquisar() {
        mesa = null;
        return "pesqMesa";
    }
}
