/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.LogradouroDAO;
import br.com.pi.dao.LogradouroDAOImp;
import br.com.pi.dao.BairroDAO;
import br.com.pi.dao.BairroDAOImp;
import br.com.pi.dao.CidadeDAO;
import br.com.pi.dao.CidadeDAOImp;
import br.com.pi.dao.EstadoDAO;
import br.com.pi.dao.EstadoDAOImp;
import br.com.pi.dao.PaisDAO;
import br.com.pi.dao.PaisDAOImp;
import br.com.pi.entidade.Logradouro;
import br.com.pi.entidade.Bairro;
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
public class LogradouroControle {

    private Logradouro logradouro;
    private Bairro bairro;
    private Cidade cidade;
    private Estado estado;
    private Pais pais;
    private LogradouroDAO dao;
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

    public Cidade getCidade() {
        if (cidade == null) {
            cidade = new Cidade();
        }
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Bairro getBairro() {
        if (bairro == null) {
            bairro = new Bairro();
        }
        return bairro;
    }

    public void setBairro(Bairro usuario) {
        this.bairro = usuario;
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

    public Logradouro getLogradouro() {
        if (logradouro == null) {
            logradouro = new Logradouro();
        }
        return logradouro;
    }

    public void setLogradouro(Logradouro usuario) {
        this.logradouro = usuario;
    }
    
    private void limpar() {
        logradouro = null;
        bairro = null;
        cidade = null;
        estado = null;
        pais = null;
        model = null;
    }
    
    public String novo() {
        logradouro = new Logradouro();
        bairro = new Bairro();
        cidade = new Cidade();
        estado = new Estado();
        pais = new Pais();
        return "cadLogradouro.faces";
    }
    
    public String pesq() {
        limpar();
        return "pesqLogradouro.faces";
    }
    
    public String pesqCep() {
        limpar();
        return "pesqLogradouroCep.faces";
    }
    
    public String salvar() {
        dao = new LogradouroDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        logradouro.setBairro(bairro);
        if (logradouro.getId() == null) {
            dao.salva(logradouro);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logradouro Salvo Com Sucesso!", ""));
        } else {
            dao.altera(logradouro);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logradouro Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqLogradouro.faces";
    }
    
    public void pesquisaLike() {
        dao = new LogradouroDAOImp();
        List<Logradouro> logradouroes = dao.pesquisaLikeNome(nome);
        model = new ListDataModel(logradouroes);
        FacesContext context = FacesContext.getCurrentInstance();
        if (logradouroes.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logradouro inesistente!", "Logradouro inesistente!"));
            limpar();
        }
    }
    
    public void pesquisaCep() {
        dao = new LogradouroDAOImp();
        List<Logradouro> logradouroes = dao.pesquisaLikeCep(nome);
        model = new ListDataModel(logradouroes);
        FacesContext context = FacesContext.getCurrentInstance();
        if (logradouroes.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logradouro inesistente!", "Logradouro inesistente!"));
            limpar();
        }
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new LogradouroDAOImp();
            logradouro = (Logradouro) model.getRowData();
            dao.remove(logradouro);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logradouro Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }
    
    public String paginapesq() {
        limpar();
        return "pesLogradouro.faces";
    }
    
    public String alterar() {
        logradouro = (Logradouro) model.getRowData();
        bairro = logradouro.getBairro();
        return "cadLogradouro.faces";
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
    
    public List<SelectItem> getComboCidade(){
        CidadeDAO pdao = new CidadeDAOImp();
        if (estado.getId() != null) {
        List<Cidade> cidadees = pdao.pesquisaEstado(estado);
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Cidade forn : cidadees) {
            listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
        }
            return listaCombo;
        } else {
            return new ArrayList<SelectItem>();
        }
    }
    
    public List<SelectItem> getComboBairro(){
        BairroDAO pdao = new BairroDAOImp();
        if (cidade.getId() != null) {
        List<Bairro> bairroes = pdao.pesquisaCidade(cidade);
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Bairro forn : bairroes) {
            listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
        }
            return listaCombo;
        } else {
            return new ArrayList<SelectItem>();
        }
    }
    
}
