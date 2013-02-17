/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.PerfilDAO;
import dao.PerfilDAOImp;
import dao.ProfessorDAO;
import dao.ProfessorDAOImp;
import entidade.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.hibernate.NonUniqueResultException;
import util.EnviaEmail;

/**
 *
 * @author Junior
 */
@ManagedBean
@SessionScoped
public class ProfessorControle {

    private Professor professor;
    private Endereco endereco;
    private Usuario usuario;
    private DataModel model;
    private boolean pesquisa = false;
    private ProfessorDAO professorDAO;

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }

    public Professor getProfessor() {
        if (professor == null) {
            professor = new Professor();
        }
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String salvar() throws Exception {
        professorDAO = new ProfessorDAOImp();
        professor.setEndereco(endereco);
        FacesContext context = FacesContext.getCurrentInstance();

        if (professor.getId() == null) {
            professor.setCracha(geraCracha());
            professor.setUsuario(criaUsuario());
            try {
                professorDAO.salva(professor);
                context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Professor salvo com Sucesso!"));
                try {
                    EnviaEmail.mandaEmail(professor);
                } catch (AddressException ex) {
                    Logger.getLogger(AlunoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(AlunoControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                 context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Já existe um professor com esse e-mail!"));
            }
            
        } else {
            professorDAO.altera(professor);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Professor atualizado com Sucesso!"));
        }
        limpa();
        model = null;
        return "pesqProfessor";
    }

    private String geraCracha() {
        professorDAO = new ProfessorDAOImp();

        String cracha = professorDAO.ultimoCracha();

        if (cracha != null) {
            int num = Integer.parseInt(cracha);
            num = num++;
            cracha = Integer.toString(num);
            switch (cracha.length()) {
                case 1:
                    cracha = "000" + num;
                    break;
                case 2:
                    cracha = "00" + num;
                    break;
                case 3:
                    cracha = "0" + num;
                    break;
            }

        }

        return cracha;
    }

    private Usuario criaUsuario() {
        usuario = new Usuario();
        PerfilDAO perfilDAO = new PerfilDAOImp();
        Perfil perfil = perfilDAO.pesquisaPorId(2L);// 2 professor
        usuario.setLogin(professor.getEmail());
        usuario.setPerfil(perfil);
        int senha = (int) (Math.random() * 10000);
        usuario.setSenha(Integer.toString(senha));
        return usuario;
    }

    public String pesquisar() {
        if (professor != null) {
            limpa();
            model = null;
        }
//        pesquisa = false;
        return "pesqProfessor";
    }

    public String pesquisaProfessor() {
        professorDAO = new ProfessorDAOImp();
        if (professor.getNome() != null || professor.getCpf() != null) {
            if (professor.getNome().equals("") && professor.getCpf().equals("")) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                        " nome ou cpf é obrigatório!"));
            } else {
                List<Professor> professores = professorDAO.pesquisaProfessor(professor.getNome(),
                        professor.getCpf());
                if (professores.isEmpty()) {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Não foi encontrado nenhum professor!", ""));
                }
                model = new ListDataModel(professores);
                pesquisa = true;
            }
        }
        limpa();       
        return "";
    }

    public String atualiza() {
        professor = (Professor) model.getRowData();
        endereco = professor.getEndereco();
        setProfessor(professor);
        return "cadProfessor";
    }

    private void limpa() {
        professor = new Professor();
    }

    public String novoProfessor() {
        professor = new Professor();
        endereco = new Endereco();
        usuario = new Usuario();
        pesquisa = false;
        return "cadProfessor";
    }
}
