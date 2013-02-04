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
import br.com.pi.dao.MoradiaDAO;
import br.com.pi.dao.MoradiaDAOImp;
import br.com.pi.dao.PaisDAO;
import br.com.pi.dao.PaisDAOImp;
import br.com.pi.entidade.Logradouro;
import br.com.pi.entidade.Bairro;
import br.com.pi.entidade.Cidade;
import br.com.pi.entidade.Estado;
import br.com.pi.entidade.Moradia;
import br.com.pi.entidade.Pais;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author tecnicom
 */
@ManagedBean
@SessionScoped
public class MoradiaControle {

    private Moradia moradia;
    private Logradouro logradouro;
    private Bairro bairro;
    private Cidade cidade;
    private Estado estado;
    private Pais pais;
    private MoradiaDAO dao;
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

    public Moradia getMoradia() {
        if (moradia == null) {
            moradia = new Moradia();
        }
        return moradia;
    }

    public void setMoradia(Moradia moradia) {
        this.moradia = moradia;
    }

    private void limpar() {
        moradia = null;
        logradouro = null;
        bairro = null;
        cidade = null;
        estado = null;
        pais = null;
        model = null;
    }

    public String novo() {
        moradia = new Moradia();
        logradouro = new Logradouro();
        bairro = new Bairro();
        cidade = new Cidade();
        estado = new Estado();
        pais = new Pais();
        return "cadMoradia.faces";
    }
    
    public void novo2() {
        moradia = new Moradia();
        logradouro = new Logradouro();
        bairro = new Bairro();
        cidade = new Cidade();
        estado = new Estado();
        pais = new Pais();
    }

    public String pesq() {
        limpar();
        return "pesqMoradia.faces";
    }

    public String pesqFone() {
        limpar();
        return "pesqMoradiaFone.faces";
    }

    public String salvar() {
        dao = new MoradiaDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        moradia.setLogradouro(logradouro);
        if (moradia.getId() == null) {
            dao.salva(moradia);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Moradia Salvo Com Sucesso!", ""));
        } else {
            dao.altera(moradia);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Moradia Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqMoradia.faces";
    }

    public void pesquisaLike() {
        dao = new MoradiaDAOImp();
        List<Moradia> moradias = dao.pesquisaLikeNumero(nome);
        model = new ListDataModel(moradias);
        FacesContext context = FacesContext.getCurrentInstance();
        if (moradias.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Moradia inesistente!", "Moradia inesistente!"));
            limpar();
        }
    }

    public void pesquisaFone() {
        dao = new MoradiaDAOImp();
        List<Moradia> moradias = dao.pesquisaLikeTelefone(nome);
        model = new ListDataModel(moradias);
        FacesContext context = FacesContext.getCurrentInstance();
        if (moradias.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Moradia inesistente!", "Moradia inesistente!"));
            limpar();
        }
    }

    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new MoradiaDAOImp();
            moradia = (Moradia) model.getRowData();
            dao.remove(moradia);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Moradia Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }

    public String paginapesq() {
        limpar();
        return "pesMoradia.faces";
    }

    public String alterar() {
        moradia = (Moradia) model.getRowData();
        logradouro = moradia.getLogradouro();
        bairro = logradouro.getBairro();
        cidade = bairro.getCidade();
        estado = cidade.getEstado();
        pais = estado.getPais();
        return "cadMoradia.faces";
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

    public List<SelectItem> getComboCidade() {
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

    public List<SelectItem> getComboBairro() {
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
    
    public List<SelectItem> getComboLogradouro() {
        LogradouroDAO pdao = new LogradouroDAOImp();
        if (cidade.getId() != null) {
            List<Logradouro> logradouros = pdao.pesquisaBairro(bairro);
            List<SelectItem> listaCombo = new ArrayList<SelectItem>();
            for (Logradouro forn : logradouros) {
                listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
            }
            return listaCombo;
        } else {
            return new ArrayList<SelectItem>();
        }
    }
}
