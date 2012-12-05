/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.FuncionarioDAO;
import br.com.pi.dao.FuncionarioDAOImp;
import br.com.pi.entidade.Funcionario;
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

    public Funcionario getFuncionario() {
        if (funcionario == null) {
            funcionario = new Funcionario();
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

    public void excluir(ActionEvent evento) {
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
}
