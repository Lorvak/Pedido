/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.*;
import entidade.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import util.EnviaEmail;

/**
 *
 * @author Junior
 */
@ManagedBean
@SessionScoped
public class AlunoControle {

    private Aluno aluno;
    private Endereco endereco;
    private Usuario usuario;
    private Responsavel responsavel;
    private AlunoDAO alunoDAO;
    private DataModel model;
    private boolean pesquisa = false;

    public DataModel getModel() {
        pesquisa = true;
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Aluno getAluno() {
        if (aluno == null) {
            aluno = new Aluno();
        }
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
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

    public Responsavel getResponsavel() {
        if (responsavel == null) {
            responsavel = new Responsavel();
        }
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String salvar() {
        alunoDAO = new AlunodAOImp();
        aluno.setEndereco(endereco);
        aluno.setResponsavel(responsavel);
        FacesContext context = FacesContext.getCurrentInstance();

        if (aluno.getId() == null) {
            aluno.setNumMatricula(geraMatricula());
            aluno.setDataMatricula(new Date());
            aluno.setUsuario(criaUsuario(aluno));
            try {
                alunoDAO.salva(aluno);
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                try {
                    EnviaEmail.mandaEmail(aluno);
                } catch (AddressException ex) {
                    Logger.getLogger(AlunoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(AlunoControle.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLIntegrityConstraintViolationException ex) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um aluno com esse e-mail!", ""));
            } catch (Exception ex) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um aluno com esse e-mail!", ""));
            }
        } else {
            alunoDAO.altera(aluno);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com Sucesso!", ""));
        }
        limpa();
        model = null;
        return "pesqAluno";
    }

    public String pesquisar() {
        if (aluno != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqAluno";
    }

    private void limpa() {
        aluno = new Aluno();
        responsavel = new Responsavel();
        endereco = new Endereco();

    }

    public String novoAluno() {
        aluno = new Aluno();
        endereco = new Endereco();
        responsavel = new Responsavel();
        usuario = new Usuario();
        pesquisa = false;
        return "cadAluno";
    }

    private String geraMatricula() {
        alunoDAO = new AlunodAOImp();
        Calendar cal = new GregorianCalendar();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        String matricula = alunoDAO.ultimaMatricula();
        String parte = Integer.toString(ano);
        if (mes <= 5) {
            parte += 1;
        } else {
            parte += 2;
        }
        int num;
        String valorFinal = "0";
        if (matricula != null) {
            num = matricula.length();
            valorFinal = "";
            for (int i = 5; i < num; i++) {
                valorFinal += matricula.charAt(i);
            }

        }
        num = Integer.parseInt(valorFinal) + 1;
        parte += num;
        return parte;
    }

    private Usuario criaUsuario(Object obj) {
        usuario = new Usuario();
        PerfilDAO perfilDAO = new PerfilDAOImp();
        Perfil perfil = null;
        if (obj instanceof Aluno) {
            perfil = perfilDAO.pesquisaPorId(3L);// 3 aluno
            usuario.setLogin(aluno.getEmail());
        } else if (obj instanceof Responsavel) {
            perfil = perfilDAO.pesquisaPorId(4L);// id é responsável            
            usuario.setLogin(responsavel.getEmail());
        }
        usuario.setPerfil(perfil);
        int senha = (int) (Math.random() * 100000);
        usuario.setSenha(Integer.toString(senha));
        return usuario;
    }

    public String pesquisaCpfResponsavel() {
        ResponsavelDAO responsavelDAO = new ResponsavelDAOImp();
        if (responsavel.getCpf() != null) {
            responsavel = responsavelDAO.pesquisaPorCpf(responsavel.getCpf());
            if (responsavel == null) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Não há cadastro com esse CPF!", ""));
                return "";
            }
            endereco = responsavel.getEndereco();
        }
        return "";
    }

    public String salveResponsavel() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResponsavelDAO responsavelDAO = new ResponsavelDAOImp();
        responsavel.setEndereco(endereco);
        if (responsavel.getId() == null) {
            responsavel.setUsuario(criaUsuario(responsavel));
            try {
                responsavelDAO.salva(responsavel);
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                        responsavel.getNome() + " salvo com sucesso!", ""));
                try {
                    EnviaEmail.mandaEmail(responsavel);
                } catch (AddressException ex) {
                    Logger.getLogger(AlunoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(AlunoControle.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLIntegrityConstraintViolationException ex) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um responsável com esse e-mail!", ""));
            } catch (Exception ex) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um responsável com esse e-mail!", ""));
            }
        } else {
            responsavelDAO.altera(responsavel);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    responsavel.getNome() + " atualizado com sucesso!", ""));
        }
        aluno.setTelefone(responsavel.getTelefone());
        return "cadAluno";
    }

    public void pesquisaAluno() {
        alunoDAO = new AlunodAOImp();
        if (aluno.getNome() != null || aluno.getCpf() != null) {
            if (aluno.getNome().equals("") && aluno.getCpf().equals("")) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Nome ou cpf é obrigatório!", ""));
            } else {
                pesquisa = true;
                List<Aluno> alunos = alunoDAO.pesquisaAluno(aluno.getNome(),
                        aluno.getCpf());
                if (alunos.isEmpty()) {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Não foi encontrado nenhum aluno !", ""));
                }

                model = new ListDataModel(alunos);

            }
        }
        limpa();
    }

    public String editar() {
        aluno = (Aluno) model.getRowData();
        responsavel = aluno.getResponsavel();
        endereco = aluno.getEndereco();
        setAluno(aluno);
        return "cadAluno";
    }
}
