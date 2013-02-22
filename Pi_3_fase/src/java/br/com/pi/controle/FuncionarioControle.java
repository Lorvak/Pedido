/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.FuncionarioDAO;
import br.com.pi.dao.FuncionarioDAOImp;
import br.com.pi.dao.LogradouroDAO;
import br.com.pi.dao.LogradouroDAOImp;
import br.com.pi.dao.MoradiaDAO;
import br.com.pi.dao.MoradiaDAOImp;
import br.com.pi.entidade.Funcionario;
import br.com.pi.entidade.Logradouro;
import br.com.pi.entidade.Moradia;
import br.com.pi.entidade.Perfil;
import br.com.pi.entidade.Usuario;
import java.util.ArrayList;
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
 * @author Hugo
 */
@ManagedBean
@SessionScoped
public class FuncionarioControle {

    private Funcionario funcionario;
    private FuncionarioDAO funcionarioDAO;
    private DataModel model;
    private DataModel model2;
    private LogradouroDAOImp logradouroDAO;
    private Moradia moradia;
    private List<Moradia> moradias;
    private MoradiaDAO moradiaDAO;
    private Logradouro logradouro;
    
    public Moradia getMoradia() {
        if (moradia == null) {
            moradia = new Moradia();
        }
        return moradia;
    }

    public void setMoradia(Moradia moradia) {
        this.moradia = moradia;
    }

    public Logradouro getLogradouro() {
        if (logradouro == null) {
            logradouro = new Logradouro();
        }
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Funcionario getFuncionario() {
        if (funcionario == null) {
            funcionario = new Funcionario();
            funcionario.setUsuario(new Usuario());
            funcionario.getUsuario().setPerfil(new Perfil());
        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public FuncionarioDAO getFuncionarioDAO() {
        return funcionarioDAO;
    }

    public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }
    
    public DataModel getModel2() {
        if(model2 == null){
            model2 = new ListDataModel();
        }
        return model2;
    }

    public void setModel2(DataModel model2) {
        this.model2 = model2;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        funcionarioDAO = new FuncionarioDAOImp();
        if (funcionario.getId() == null) {
            funcionarioDAO.salva(funcionario);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Funcionario salvo com sucesso!", ""));
        } else {
            funcionarioDAO.altera(funcionario);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Funcionario alterado com sucesso!", ""));
        }
        limpar();
        return "cadFuncionario";
    }

    private void limpar() {
        funcionario = null;
        model = null;
    }
     public String limpaPesquisa() {
        limpar();
        return "pesqFuncionario";
    }

    public void pesquisaLike() {
        funcionarioDAO = new FuncionarioDAOImp();
        if (funcionario != null) {
            List<Funcionario> funcionarios = funcionarioDAO.pesquisaLikeNome(funcionario.getNome());
            model = new ListDataModel(funcionarios);
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoFuncionario() {
        funcionario = new Funcionario();

        return "cadFuncionario";
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            funcionarioDAO = new FuncionarioDAOImp();
            funcionario = (Funcionario) model.getRowData();
            funcionarioDAO.remove(funcionario);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Funcionario excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        funcionario = (Funcionario) model.getRowData();
        model2 = new ListDataModel(funcionario.getMoradias());
        setFuncionario(funcionario);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Funcionario alterado com sucesso!", ""));
        return "cadFuncionario";

    }

    public String btPesquisar() {
        funcionario = null;
        return "pesqFuncionario";
    }
    
    public String btNovoEndereco() {
        moradia = new Moradia();
        return "cadNMoradia";
    }

    public String salvarNovoEndereco() {
        if(funcionario.getMoradias()==null){
            funcionario.setMoradias(new ArrayList<Moradia>());
        }
        logradouroDAO = new LogradouroDAOImp();
        logradouro = logradouroDAO.pesquisa(logradouro.getId());
        moradia.setLogradouro(logradouro);
        funcionario.getMoradias().add(moradia);
        moradiaDAO = new MoradiaDAOImp();
        model2 = new ListDataModel(funcionario.getMoradias());
        return "cadCliente";
    }
    
     public void pesquisaMoradias() {
        funcionarioDAO = new FuncionarioDAOImp();
        funcionario = (Funcionario) model.getRowData();
        funcionario = funcionarioDAO.pesquisa(funcionario.getId());
        model2 = new ListDataModel(funcionario.getMoradias());
    }
     
     public void deletarMoradia() {
        moradiaDAO = new MoradiaDAOImp();
        moradia = (Moradia) model2.getRowData();
        funcionario.getMoradias().remove(moradia);
        if(moradia.getId() != null){
            if(moradias == null){
                moradias = new ArrayList<Moradia>();
            }
            moradias.add(moradia);
        }
    }
}