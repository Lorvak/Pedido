/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.PerfilDAO;
import dao.PerfilDAOImp;
import entidade.Perfil;
import java.sql.SQLIntegrityConstraintViolationException;
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
 * @author Junior
 */
@ManagedBean
@SessionScoped
public class PerfilControle {

    private Perfil perfil;
    private DataModel model;
    private boolean pesquisa = false;
    private PerfilDAO perfilDAO;

    public DataModel getModel() {
        return model;
    }

    public Perfil getPerfil() {
        if (perfil == null) {
            perfil = new Perfil();
        }
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String salvar() {
        perfilDAO = new PerfilDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();

        if (perfil.getId() == null) {
            try {
                perfilDAO.salva(perfil);
            } catch (SQLIntegrityConstraintViolationException ex) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "", "erro ao salvar perfil!"));
            } catch (Exception ex) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Jerro ao salvar perfil!"));
            }
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Salvo com Sucesso!"));
        } else {
            perfilDAO.altera(perfil);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Atualizado com Sucesso!"));
        }
        limpa();
        model = null;
        return "pesqPerfil";
    }

    public String pesquisar() {
        if (perfil != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqPerfil";
    }

    private void limpa() {
        perfil = null;
    }

    public String novoPerfil() {
        perfil = new Perfil();
        pesquisa = false;
        return "cadPerfil";
    }

    public String pesquisaPerfil() {
        pesquisa = true;
        perfilDAO = new PerfilDAOImp();
        if (perfil.getNome() != null) {
            List<Perfil> perfis = perfilDAO.pesquisaPerfil(perfil.getNome());
            model = new ListDataModel(perfis);

        }
        limpa();
        return "";
    }

    public String editar() {
        perfil = (Perfil) model.getRowData();
        setPerfil(perfil);
        return "cadPerfil";
    }

    public void excluir(ActionEvent actionEvent) {
        String nome = perfil.getNome();
        perfil = (Perfil) model.getRowData();
        
        perfilDAO = new PerfilDAOImp();
        perfilDAO.remove(perfil);
        model = new ListDataModel(perfilDAO.pesquisaPerfil(nome));
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Excluído com Sucesso!"));
        perfil = null;
        pesquisa = false;

    }
}
