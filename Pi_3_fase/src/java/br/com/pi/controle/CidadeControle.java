/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.CidadeDAO;
import br.com.pi.dao.CidadeDAOImp;
import br.com.pi.dao.EstadoDAO;
import br.com.pi.dao.EstadoDAOImp;
import br.com.pi.dao.PaisDAO;
import br.com.pi.dao.PaisDAOImp;
import br.com.pi.entidade.Cidade;
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
public class CidadeControle {

    private Cidade cidade;
    private Estado estado;
    private Pais pais;
    private CidadeDAO dao;
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

    public Estado getEstado() {
        if (estado == null) {
            estado = new Estado();
        }
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    public Cidade getCidade() {
        if (cidade == null) {
            cidade = new Cidade();
        }
        return cidade;
    }

    public void setCidade(Cidade usuario) {
        this.cidade = usuario;
    }

    private void limpar() {
        cidade = null;
        estado = null;
        pais = null;
        model = null;
    }

    public String novo() {
        cidade = new Cidade();
        estado = new Estado();
        pais = new Pais();
        return "cadCidade.faces";
    }

    public String pesq() {
        limpar();
        return "pesqCidade.faces";
    }

    public String salvar() {
        dao = new CidadeDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        cidade.setEstado(estado);
        if (cidade.getId() == null) {
            dao.salva(cidade);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cidade Salvo Com Sucesso!", ""));
        } else {
            dao.altera(cidade);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cidade Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqCidade.faces";
    }

    public void pesquisaLike() {
        dao = new CidadeDAOImp();
        List<Cidade> cidadees = dao.pesquisaLikeNome(nome);
        model = new ListDataModel(cidadees);
        FacesContext context = FacesContext.getCurrentInstance();
        if (cidadees.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Cidade inesistente!"));
            limpar();
        }
    }

    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new CidadeDAOImp();
            cidade = (Cidade) model.getRowData();
            dao.remove(cidade);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cidade Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }

    public String paginapesq() {
        limpar();
        return "pesCidade.faces";
    }

    public String alterar() {
        cidade = (Cidade) model.getRowData();
        estado = cidade.getEstado();
        return "cadCidade.faces";
    }

    public List<SelectItem> getComboPais() {
        PaisDAO pdao = new PaisDAOImp();
        List<Pais> paises = pdao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Pais forn : paises) {
            listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
        }
        return listaCombo;
    }

    public List<SelectItem> getComboEstado() {
        EstadoDAO pdao = new EstadoDAOImp();
        if (pais.getId() != null) {
            List<Estado> estadoes = pdao.pesquisaPais(pais);
            List<SelectItem> listaCombo = new ArrayList<SelectItem>();
            for (Estado forn : estadoes) {
                listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
            }
            return listaCombo;
        } else {
            return new ArrayList<SelectItem>();
        }
    }
}
