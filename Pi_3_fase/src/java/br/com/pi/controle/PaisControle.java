/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.PaisDAO;
import br.com.pi.dao.PaisDAOImp;
import br.com.pi.entidade.Pais;
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
 * @author tecnicom
 */
@ManagedBean
@SessionScoped
public class PaisControle {

    private Pais pais;
    private PaisDAO dao;
    private DataModel model;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Pais getPais() {
        if (pais == null) {
            pais = new Pais();
        }
        return pais;
    }

    public void setPais(Pais usuario) {
        this.pais = usuario;
    }

    private void limpar() {
        pais = null;
        model = null;
    }

    public String novo() {
        pais = new Pais();
        return "cadPais.faces";
    }

    public String pesq() {
        limpar();
        return "pesqPais.faces";
    }

    public String salvar() {
        dao = new PaisDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (pais.getId() == null) {
            try {
                dao.salva(pais);
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pais ja Cadastrado!", ""));
                return "cadPais.faces";
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais Salvo Com Sucesso!", ""));
        } else {
            dao.altera(pais);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqPais.faces";
    }

    public void pesquisaLike() {
        dao = new PaisDAOImp();
        List<Pais> paises = dao.pesquisaLikeNome(nome);
        model = new ListDataModel(paises);
        FacesContext context = FacesContext.getCurrentInstance();
        if (paises.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Pais inesistente!"));
            limpar();
        }
    }

    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new PaisDAOImp();
            dao.remove(pais);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }

    public String alterar() {
        pais = (Pais) model.getRowData();
        return "cadPais.faces";
    }
}
