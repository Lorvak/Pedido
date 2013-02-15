package controle;


import dao.PerfilDAO;
import dao.PerfilDAOImp;
import dao.UsuarioDAO;
import dao.UsuarioDAOImp;
import entidade.Perfil;
import entidade.Pessoa;
import entidade.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "usuControl")
@SessionScoped
public class UsuarioControle {

    private String nomeUsuario;
    private String perfilUsuario;
    private Usuario usuario;
    private Perfil perfil;
    private UsuarioDAO usuarioDAO;
    private PerfilDAO perfilDAO;
    private DataModel model;
    private boolean pesquisa = false;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getPerfilUsuario() {
        return perfilUsuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
            perfil = new Perfil();
            usuario.setPerfil(perfil);
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void novoUsuario() {
        usuario = new Usuario();
        perfil = new Perfil();
        usuario.setPerfil(perfil);
        pesquisa = false;
    }

    public DataModel getTodos() throws Exception {
        usuarioDAO = new UsuarioDAOImp();
        pesquisa = true;
        if (usuario.getLogin() != null) {
            model = new ListDataModel(
          usuarioDAO.pesquisaUsuario(usuario.getLogin()));
        }
        return model;
    }

    public List<SelectItem> getTodosPerfils() throws Exception {
        perfilDAO = new PerfilDAOImp();
        List<Perfil> perfis = perfilDAO.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Perfil perfilUsu : perfis) {
            listaCombo.add(new SelectItem(perfilUsu.getId(), perfilUsu.getNome()));
        }
        return listaCombo;
    }

    public Usuario getUsuarioFromEditOrDelete() {
        usuario = (Usuario) model.getRowData();
        return usuario;
    }

    public String editar() {
        usuario = getUsuarioFromEditOrDelete();
        setUsuario(usuario);
        return "cadUsuario";
    }

    public String excluir() throws Exception {
        usuarioDAO = new UsuarioDAOImp();
        usuario = getUsuarioFromEditOrDelete();
        usuarioDAO.remove(usuario);
        limpa();
        return "pesqUsuario";
    }

    private void limpa() {
        usuario.setId(null);
        usuario.setLogin(null);
        usuario.setSenha(null);
        usuario.setLogado(false);
        usuario.getPerfil().setId(null);
        usuario.getPerfil().setNome(null);
    }

    public String pesquisar() {
        pesquisa = false;
        if (usuario == null) {
            getUsuario();
        } else {
            limpa();
        }
        return "pesqUsuario";
    }

    public String salvar() throws Exception {
        usuarioDAO = new UsuarioDAOImp();
        String mens = null;
        if (usuario.getId() == null) {
            usuarioDAO.salva(usuario);
            mens = "salvo com sucesso";
        } else {
            usuarioDAO.altera(usuario);
            mens = "alterado com sucesso";
        }
        limpa();
        pesquisa = false;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,  mens, ""));
        return "pesqUsuario";
    }

    public String loga() throws Exception {
        usuarioDAO = new UsuarioDAOImp();
        String pagina = null;
        if (usuario.getLogin() != null) {
            usuario = usuarioDAO.loga(usuario);
            if (usuario.isLogado()) {
                HttpSession session = (HttpSession) FacesContext.
                        getCurrentInstance().getExternalContext().getSession(false);
                session.setAttribute("autenticado", true);
               Pessoa pessoa = usuarioDAO.usuario(usuario.getLogin());
               nomeUsuario = pessoa.getNome();
               perfilUsuario = pessoa.getUsuario().getPerfil().getNome();
                pagina = "principal";
            } else {
                pagina = "index";
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Login ou Senha incorreto!", ""));
            }
        }
        return pagina;
    }

    public String sair() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        session.removeAttribute("autenticado");
        limpa();
        return "index";
    }
}
