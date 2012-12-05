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
    private EnderecoDAO enderecoDAO;
    private DataModel model;
    private Bairro bairro;
    private Cidade cidade;
    private Estado estado;
    private Pais pais;
    private Logradouro logradouro;

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public EnderecoDAO getEnderecoDAO() {
        return enderecoDAO;
    }

    public void setEnderecoDAO(EnderecoDAO enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        enderecoDAO = new EnderecoDAOImp();
        if (endereco.getId() == null) {
            enderecoDAO.salva(endereco);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Endereco salvo com sucesso!", ""));
        } else {
            enderecoDAO.altera(endereco);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Endereco alterado com sucesso!", ""));
        }
        limpar();
        return "cadEndereco";
    }

    private void limpar() {
        endereco = null;
        model = null;
    }
     public String limpaPesquisa() {
        limpar();
        return "pesqEndereco";
    }

    public void pesquisaLike() {
        enderecoDAO = new EnderecoDAOImp();
        if (endereco != null) {
            List<Endereco> enderecos = enderecoDAO.pesquisaLikeTelefone(endereco.getTelefone());
            model = new ListDataModel(enderecos);
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

    public void excluir(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            enderecoDAO = new EnderecoDAOImp();
            endereco = (Endereco) model.getRowData();
            enderecoDAO.remove(endereco);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Endereco excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
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
}
