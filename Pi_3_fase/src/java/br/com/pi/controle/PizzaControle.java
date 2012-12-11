/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.BordaDAO;
import br.com.pi.dao.BordaDAOImp;
import br.com.pi.dao.PizzaDAO;
import br.com.pi.dao.PizzaDAOImp;
import br.com.pi.dao.SaborDAO;
import br.com.pi.dao.SaborDAOImp;
import br.com.pi.dao.TamanhoDAO;
import br.com.pi.dao.TamanhoDAOImp;
import br.com.pi.entidade.Borda;
import br.com.pi.entidade.Pizza;
import br.com.pi.entidade.Sabor;
import br.com.pi.entidade.Tamanho;
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
 * @author Eduardo Moraes Silveira
 */
@ManagedBean
@SessionScoped
public class PizzaControle {

    private Pizza pizza;
    private PizzaDAO dao;
    private TamanhoDAO tDao;
    private DataModel model;
    private List<Sabor> sabores;
    private Sabor sabor;
    private Borda borda;
    private Tamanho tamanho;
    private boolean btSabor;

    public boolean isBtSabor() {
        return btSabor;
    }

    public void setBtSabor(boolean btSabor) {
        this.btSabor = btSabor;
    }

    public Tamanho getTamanho() {
        if (tamanho == null) {
            tamanho = new Tamanho();
        }
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Borda getBorda() {
        if (borda == null) {
            borda = new Borda();
        }
        return borda;
    }

    public void setBorda(Borda borda) {
        this.borda = borda;
    }

    public Sabor getSabor() {
        if (sabor == null) {
            sabor = new Sabor();
        }
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public List<Sabor> getSabores() {
        if (sabores == null) {
            sabores = new ArrayList<Sabor>();
        }
        return sabores;
    }

    public void setSabores(List<Sabor> sabores) {
        this.sabores = sabores;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Pizza getPizza() {
        if (pizza == null) {
            pizza = new Pizza();
            pizza.setSabores(new ArrayList());
        }
        return pizza;
    }

    public void setPizza(Pizza usuario) {
        this.pizza = usuario;
    }

    private void limpar() {
        pizza = null;
        model = null;
    }

    public String novo() {
        pizza = new Pizza();
        borda = new Borda();
        tamanho = new Tamanho();
        sabores = new ArrayList<Sabor>();
        return "cadPizza.faces";
    }

    public String novoSabor() {
        sabor = null;
        if (sabores == null) {
            sabores = new ArrayList<Sabor>();
        }
        return "addSabor.faces";
    }

    public String salvaSabor() {
        sabores.add(sabor);
        tDao = new TamanhoDAOImp();
        pizza.setTamanho(tDao.pesquisa(tamanho.getId()));
        if (sabores.size() == pizza.getTamanho().getNsabores()) {
            btSabor = true;
        }
        return "cadPizza.faces";
    }

    public String voltar() {
        return "cadPizza.faces";
    }

    public String pesq() {
        limpar();
        return "pesqPizza.faces";
    }

    public void calculaSabores() {
        for (int i = 0; i < pizza.getTamanho().getNsabores(); i++) {
            pizza.getSabores().add(new Sabor());
        }
    }

    public String salvar() {
        dao = new PizzaDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        pizza.setTamanho(tamanho);
        pizza.setBorda(borda);
        pizza.setSabores(sabores);
        if (pizza.getId() == null) {
            dao.salva(pizza);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pizza Salvo Com Sucesso!", ""));
        } else {
            dao.altera(pizza);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pizza Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqPizza.faces";
    }

    public void pesquisa() {
        dao = new PizzaDAOImp();
        List<Pizza> pizzaes = dao.getTodos();
        model = new ListDataModel(pizzaes);
        FacesContext context = FacesContext.getCurrentInstance();
        if (pizzaes.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pizza inesistente!", "Pizza inesistente!"));
            limpar();
        }
    }

    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new PizzaDAOImp();
            pizza = (Pizza) model.getRowData();
            dao.remove(pizza);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pizza Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }

    public String alterar() {
        pizza = (Pizza) model.getRowData();
        tamanho = pizza.getTamanho();
        sabores = pizza.getSabores();
        borda = pizza.getBorda();
        return "cadPizza.faces";
    }

    public List<SelectItem> getComboSabor() {
        boolean flag;
        SaborDAO pdao = new SaborDAOImp();
        List<Sabor> list = pdao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Sabor forn : list) {
            flag = true;
            for (Sabor selectItem : sabores) {
                if (forn.getId().longValue() == selectItem.getId().longValue()) {
                    flag = false;
                }
            }
            if (flag) {
                listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
            }
        }
        return listaCombo;
    }

    public List<SelectItem> getComboTamanho() {
        TamanhoDAO pdao = new TamanhoDAOImp();
        List<Tamanho> list = pdao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Tamanho forn : list) {
            listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
        }
        return listaCombo;
    }

    public List<SelectItem> getComboBorda() {
        BordaDAO pdao = new BordaDAOImp();
        List<Borda> list = pdao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Borda forn : list) {
            listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
        }
        return listaCombo;
    }
}
