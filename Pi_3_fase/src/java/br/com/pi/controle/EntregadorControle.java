/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.EntregadorDAO;
import br.com.pi.dao.EntregadorDAOImp;
import br.com.pi.dao.MoradiaDAOImp;
import br.com.pi.entidade.Entregador;
import br.com.pi.entidade.Moradia;
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
public class EntregadorControle {

    private Entregador entregador;
    private EntregadorDAO entregadorDAO;
    private DataModel model;

    public Entregador getEntregador() {
        if (entregador == null) {
            entregador = new Entregador();
        }
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public EntregadorDAO getEntregadorDAO() {
        return entregadorDAO;
    }

    public void setEntregadorDAO(EntregadorDAO entregadorDAO) {
        this.entregadorDAO = entregadorDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        entregadorDAO = new EntregadorDAOImp();
        if (entregador.getId() == null) {
            try {
                entregadorDAO.salva(entregador);
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Entregador ja Cadastrado!", ""));
                return "cadEntregador.faces";
            }
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Entregador salvo com sucesso!", ""));
        } else {
            entregadorDAO.altera(entregador);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Entregador alterado com sucesso!", ""));
        }
        limpar();
        return "cadEntregador";
    }

    private void limpar() {
        entregador = null;
        model = null;
    }
     public String limpaPesquisa() {
        limpar();
        return "pesqEntregador";
    }

    public void pesquisaLike() {
        entregadorDAO = new EntregadorDAOImp();
        if (entregador != null) {
            List<Entregador> entregadors = entregadorDAO.pesquisaLikeNome(entregador.getNome());
            model = new ListDataModel(entregadors);
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoEntregador() {
        entregador = new Entregador();

        return "cadEntregador";
    }

    public void excluir(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            entregadorDAO = new EntregadorDAOImp();
            entregador = (Entregador) model.getRowData();
            entregadorDAO.remove(entregador);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Entregador excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        entregador = (Entregador) model.getRowData();
        setEntregador(entregador);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Entregador alterado com sucesso!", ""));
        return "cadEntregador";

    }

    public String btPesquisar() {
        entregador = null;
        return "pesqEntregador";
    }
}
