/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.BairroDAO;
import br.com.pi.dao.BairroDAOImp;
import br.com.pi.dao.CidadeDAO;
import br.com.pi.dao.CidadeDAOImp;
import br.com.pi.dao.EstadoDAO;
import br.com.pi.dao.EstadoDAOImp;
import br.com.pi.dao.PaisDAO;
import br.com.pi.dao.PaisDAOImp;
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
public class BairroControle {

    private Bairro bairro;
    private Cidade cidade;
    private Estado estado;
    private Pais pais;
    private BairroDAO dao;
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

    private void limpar() {
        bairro = null;
        cidade = null;
        estado = null;
        pais = null;
        model = null;
    }

    public String novo() {
        bairro = new Bairro();
        cidade = new Cidade();
        estado = new Estado();
        pais = new Pais();
        return "cadBairro.faces";
    }

    public String pesq() {
        limpar();
        return "pesqBairro.faces";
    }

    public String salvar() {
        dao = new BairroDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        bairro.setCidade(cidade);
        if (bairro.getId() == null) {
            dao.salva(bairro);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bairro Salvo Com Sucesso!", ""));
        } else {
            dao.altera(bairro);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bairro Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqBairro.faces";
    }

    public void pesquisaLike() {
        dao = new BairroDAOImp();
        List<Bairro> bairroes = dao.pesquisaLikeNome(nome);
        model = new ListDataModel(bairroes);
        FacesContext context = FacesContext.getCurrentInstance();
        if (bairroes.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Bairro inesistente!"));
            limpar();
        }
    }

    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new BairroDAOImp();
            dao.remove(bairro);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bairro Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }

    public String paginapesq() {
        limpar();
        return "pesBairro.faces";
    }

    public String alterar() {
        bairro = (Bairro) model.getRowData();
        cidade = bairro.getCidade();
        return "cadBairro.faces";
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
}
