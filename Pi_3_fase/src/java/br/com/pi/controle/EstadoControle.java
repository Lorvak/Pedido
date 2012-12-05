/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.EstadoDAO;
import br.com.pi.dao.EstadoDAOImp;
import br.com.pi.dao.PaisDAO;
import br.com.pi.dao.PaisDAOImp;
import br.com.pi.entidade.Estado;
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
public class EstadoControle {

    private Estado estado;
    private Pais pais;
    private EstadoDAO dao;
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

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Estado getEstado() {
        if (estado == null) {
            estado = new Estado();
        }
        return estado;
    }

    public void setEstado(Estado usuario) {
        this.estado = usuario;
    }
    
    private void limpar() {
        estado = null;
        pais = null;
        model = null;
    }
    
    public String novo() {
        estado = new Estado();
        pais = new Pais();
        return "cadEstado.faces";
    }
    
    public String pesq() {
        limpar();
        return "pesqEstado.faces";
    }
    
    public String salvar() {
        dao = new EstadoDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        estado.setPais(pais);
        if (estado.getId() == null) {
            dao.salva(estado);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado Salvo Com Sucesso!", ""));
        } else {
            dao.altera(estado);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqEstado.faces";
    }
    
    public void pesquisaLike() {
        dao = new EstadoDAOImp();
        List<Estado> estadoes = dao.pesquisaLikeNome(nome);
        model = new ListDataModel(estadoes);
        FacesContext context = FacesContext.getCurrentInstance();
        if (estadoes.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Estado inesistente!"));
            limpar();
        }
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new EstadoDAOImp();
            estado = (Estado) model.getRowData();
            dao.remove(estado);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }
    
    public String paginapesq() {
        limpar();
        return "pesEstado.faces";
    }
    
    public String alterar() {
        estado = (Estado) model.getRowData();
        pais = estado.getPais();
        return "cadEstado.faces";
    }
    
    public List<SelectItem> getComboPais(){
        PaisDAO pdao = new PaisDAOImp();
        List<Pais> paises = pdao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Pais forn : paises) {
            listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
        }
        return listaCombo;
    }
    
}
