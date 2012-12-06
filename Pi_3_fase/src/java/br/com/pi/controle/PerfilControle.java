/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.PerfilDAO;
import br.com.pi.dao.PerfilDAOImp;
import br.com.pi.entidade.Perfil;
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
 * @author Liana
 */
@ManagedBean
@SessionScoped
public class PerfilControle {
    private Perfil perfil;
    private PerfilDAO perfilDAO;
    private DataModel model;

    public Perfil getPerfil() {
         if (perfil == null) {
            perfil = new Perfil();
        }
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public PerfilDAO getPerfilDAO() {
        return perfilDAO;
    }

    public void setPerfilDAO(PerfilDAO perfilDAO) {
        this.perfilDAO = perfilDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }
    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        perfilDAO = new PerfilDAOImp();
        if (perfil.getId() == null) {
            perfilDAO.salva(perfil);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Perfil salvo com sucesso!", ""));
        } else {
            perfilDAO.altera(perfil);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Perfil alterado com sucesso!", ""));
        }
        limpar();
        return "cadPerfil";
    }

    private void limpar() {
        perfil = null;
        model = null;
    }
     public String limpaPesquisa() {
        limpar();
        return "pesqPerfil";
    }

    public void pesquisaLike() {
        perfilDAO = new PerfilDAOImp();
        if (perfil != null) {
            List<Perfil> perfils = perfilDAO.pesquisaLikeNome(perfil.getNome());
            model = new ListDataModel(perfils);
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoPerfil() {
        perfil = new Perfil();

        return "cadPerfil";
    }
    public String novo() {
        perfil = new Perfil();
        return "cadPerfil.faces";
    }


    public void excluir(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            perfilDAO = new PerfilDAOImp();
            perfil = (Perfil) model.getRowData();
            perfilDAO.remove(perfil);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Perfil excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        perfil = (Perfil) model.getRowData();
        setPerfil(perfil);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Perfil alterado com sucesso!", ""));
        return "cadPerfil";

    }

    public String btPesquisar() {
        perfil = null;
        return "pesqPerfil";
    }
}
