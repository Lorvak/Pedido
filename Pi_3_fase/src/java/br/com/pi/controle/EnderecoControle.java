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
import br.com.pi.dao.LogradouroDAO;
import br.com.pi.dao.LogradouroDAOImp;
import br.com.pi.dao.PaisDAO;
import br.com.pi.dao.PaisDAOImp;
import br.com.pi.dao.EnderecoDAO;
import br.com.pi.dao.EnderecoDAOImp;
import br.com.pi.entidade.Bairro;
import br.com.pi.entidade.Cidade;
import br.com.pi.entidade.Estado;
import br.com.pi.entidade.Logradouro;
import br.com.pi.entidade.Pais;
import br.com.pi.entidade.Endereco;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author Hugo
 */
@ManagedBean
@SessionScoped
public class EnderecoControle {

    private Endereco endereco;
    private EnderecoDAO dao;
    private DataModel model;
    private Bairro bairro;
    private Cidade cidade;
    private Estado estado;
    private Pais pais;
    private Logradouro logradouro;
    private String telefone;

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        if (telefone == null) {
            telefone = "";
        }
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public DataModel getModel() {
        if (model == null) {
            model = new ListDataModel();
        }
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

    public String salvar() {
        dao = new EnderecoDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        endereco.setLogradouro(logradouro);
        if (endereco.getId() == null) {
            dao.salva(endereco);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logradouro Salvo Com Sucesso!", ""));
        } else {
            dao.altera(endereco);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logradouro Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqEndereco.faces";
    }

    private void limpar() {
        endereco = null;
        logradouro = null;
        bairro = null;
        cidade = null;
        estado = null;
        pais = null;
        model = null;
    }
    
    public String novo() {
        limpar();
        return "cadEndereco.faces";
    }
    
    public String pesq() {
        limpar();
        return "pesqEndereco.faces";
    }

    public void pesquisaLike() {
        dao = new EnderecoDAOImp();
        List<Endereco> enderecos = dao.pesquisaLikeTelefone(telefone);
        model = new ListDataModel(enderecos);
        FacesContext context = FacesContext.getCurrentInstance();
        if (enderecos.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Endereco inesistente!", "Endereco inesistente!"));
            limpar();
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoEndereco() {
        endereco = new Endereco();

        return "cadEndereco";
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new EnderecoDAOImp();
            endereco = (Endereco) model.getRowData();
            dao.remove(endereco);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Endereco Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        endereco = (Endereco) model.getRowData();
        setEndereco(endereco);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Endereco alterado com sucesso!", ""));
        return "cadEndereco";

    }

    public String btPesquisar() {
        endereco = null;
        return "pesqEndereco";
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
    
    public List<SelectItem> getComboLogradouro(){
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
