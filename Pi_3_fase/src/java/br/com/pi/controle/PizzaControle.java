/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.PizzaDAO;
import br.com.pi.dao.PizzaDAOImp;
import br.com.pi.entidade.Pizza;
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
public class PizzaControle {

    private Pizza pizza;
    private PizzaDAO pizzaDAO;
    private DataModel model;

    public Pizza getPizza() {
        if (pizza == null) {
            pizza = new Pizza();
        }
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public PizzaDAO getPizzaDAO() {
        return pizzaDAO;
    }

    public void setPizzaDAO(PizzaDAO pizzaDAO) {
        this.pizzaDAO = pizzaDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        pizzaDAO = new PizzaDAOImp();
        if (pizza.getId() == null) {
            pizzaDAO.salva(pizza);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Pizza salvo com sucesso!", ""));
        } else {
            pizzaDAO.altera(pizza);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Pizza alterado com sucesso!", ""));
        }
        limpar();
        return "cadPizza";
    }

    private void limpar() {
        pizza = null;
        model = null;
    }
     public String limpaPesquisa() {
        limpar();
        return "pesqPizza";
    }

    public void pesquisaLike() {
        pizzaDAO = new PizzaDAOImp();
        if (pizza != null) {
            List<Pizza> pizzas = pizzaDAO.getTodos();
            model = new ListDataModel(pizzas);
        }
    }

    public String voltar() {
        limpar();
        return "index.feces";
    }

    public String novoPizza() {
        pizza = new Pizza();

        return "cadPizza";
    }

    public void excluir(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            pizzaDAO = new PizzaDAOImp();
            pizza = (Pizza) model.getRowData();
            pizzaDAO.remove(pizza);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Pizza excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
    }

    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        pizza = (Pizza) model.getRowData();
        setPizza(pizza);
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Pizza alterado com sucesso!", ""));
        return "cadPizza";

    }

    public String btPesquisar() {
        pizza = null;
        return "pesqPizza";
    }
}
