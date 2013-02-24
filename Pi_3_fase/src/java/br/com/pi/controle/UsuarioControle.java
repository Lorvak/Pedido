/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.PerfilDAO;
import br.com.pi.dao.PerfilDAOImp;
import br.com.pi.dao.UsuarioDAO;
import br.com.pi.dao.UsuarioDAOImp;
import br.com.pi.entidade.Perfil;
import br.com.pi.entidade.Pessoa;
import br.com.pi.entidade.Usuario;
import br.com.pi.util.Cripto;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hugo
 */
@ManagedBean
@SessionScoped
public class UsuarioControle {

    private Usuario usuario;
    private List<Usuario> usuarios;
    private UsuarioDAO usuarioDAO;
    private Perfil perfil;
    private Pessoa pessoa;
    private DataModel model;
    private Cripto cripto;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Perfil getPerfil() {
        if (perfil == null) {
            perfil = new Perfil();
        }
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        usuarioDAO = new UsuarioDAOImp();
        cripto = new Cripto();
        if (usuario.getId() == null) {
            try {
                usuario.setSenha(cripto.criptoGraf(usuario.getSenha()));
                usuarioDAO.salva(usuario);
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario ja Cadastrado!", ""));
                return "cadUsuario.faces";
            }
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Usuario salvo com sucesso!", ""));
        } else {
            usuarioDAO.altera(usuario);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Usuario alterado com sucesso!", ""));
        }
        limpar();
        return "cadUsuario";
    }

    private void limpar() {
        usuario = null;
        model = null;
    }

    public String limpaPesquisa() {
        limpar();
        return "pesqUsuario";
    }

    public void pesquisaLike() {
        usuarioDAO = new UsuarioDAOImp();
        if (usuario != null) {
            List<Usuario> usuarios = usuarioDAO.pesquisaLikeLogin(usuario.getLogin());
            model = new ListDataModel(usuarios);
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoUsuario() {
        usuario = new Usuario();

        return "cadUsuario";
    }

    public void excluir(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            usuarioDAO = new UsuarioDAOImp();
            usuario = (Usuario) model.getRowData();
            usuarioDAO.remove(usuario);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Usuario excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        usuario = (Usuario) model.getRowData();
        setUsuario(usuario);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Usuario alterado com sucesso!", ""));
        return "cadUsuario";

    }

    public String btPesquisar() {
        usuario = null;
        return "pesqUsuario";
    }

    public List<SelectItem> getComboPerfis() {
        PerfilDAO pdao = new PerfilDAOImp();
        List<Perfil> perfis = pdao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Perfil forn : perfis) {
            listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
        }
        return listaCombo;
    }

    public String logar() throws Exception {
        usuarioDAO = new UsuarioDAOImp();
        cripto = new Cripto();
        String pagina = null;
        usuario.setSenha(cripto.criptoGraf(usuario.getSenha()));
        if (usuario.getLogin() != null) {
            usuario = usuarioDAO.loga(usuario);
            if (usuario.isLogado()) {
                HttpSession session = (HttpSession) FacesContext.
                        getCurrentInstance().getExternalContext().getSession(false);
                session.setAttribute("autenticado", true);
                pessoa = usuarioDAO.usuario(usuario.getLogin());
                pagina = "index";
            } else {
                pagina = "login";
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login ou Senha incorreto!", ""));
            }
        }
        return pagina;
    }

    public String sair() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, pessoa.getNome() + " Desconectou", ""));
        session.removeAttribute("autenticado");
        pessoa = null;
        return "login";
    }
}
