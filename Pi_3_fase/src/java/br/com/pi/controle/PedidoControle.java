/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.BebidaDAO;
import br.com.pi.dao.BebidaDAOImp;
import br.com.pi.dao.BordaDAO;
import br.com.pi.dao.BordaDAOImp;
import br.com.pi.dao.MesaDAO;
import br.com.pi.dao.MesaDAOImp;
import br.com.pi.dao.PedidoDAO;
import br.com.pi.dao.PedidoDAOImp;
import br.com.pi.dao.PizzaDAO;
import br.com.pi.dao.PizzaDAOImp;
import br.com.pi.dao.SaborDAO;
import br.com.pi.dao.SaborDAOImp;
import br.com.pi.dao.TamanhoDAO;
import br.com.pi.dao.TamanhoDAOImp;
import br.com.pi.entidade.Bebida;
import br.com.pi.entidade.Borda;
import br.com.pi.entidade.Mesa;
import br.com.pi.entidade.Pedido;
import br.com.pi.entidade.Pizza;
import br.com.pi.entidade.Sabor;
import br.com.pi.entidade.SaborSelecionado;
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
public class PedidoControle {

    private Pizza pizza;
    private PizzaDAO dao;
    private PedidoDAO pdao;
    private TamanhoDAO tDao;
    private BebidaDAO bDao;
    private MesaDAO mDao;
    private DataModel model;
    private DataModel model2;
    private DataModel model3;
    private DataModel model4;
    private DataModel model5;
    private List<Sabor> sabores;
    private List<Bebida> bebidas;
    private List<Pizza> pizzas;
    private List<SaborSelecionado> saboresSelecionados;
    private Sabor sabor;
    private SaborSelecionado saborSelecionado;
    private Borda borda;
    private Bebida bebida;
    private Pedido pedido;
    private Mesa mesa;
    private Tamanho tamanho;
    private boolean btSabor;

    public boolean isBtSabor() {
        return btSabor;
    }

    public void setBtSabor(boolean btSabor) {
        this.btSabor = btSabor;
    }

    public Pedido getPedido() {
        if (pedido == null) {
            pedido = new Pedido();
            pedido.setMesa(new Mesa());
        }
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
    
    public DataModel getModel2() {
        return model2;
    }

    public void setModel2(DataModel model2) {
        this.model2 = model2;
    }

    public DataModel getModel3() {
        return model3;
    }

    public void setModel3(DataModel model3) {
        this.model3 = model3;
    }

    public DataModel getModel4() {
        return model4;
    }

    public void setModel4(DataModel model4) {
        this.model4 = model4;
    }

    public DataModel getModel5() {
        return model5;
    }

    public void setModel5(DataModel model5) {
        this.model5 = model5;
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

    public Bebida getBebida() {
        if (bebida == null) {
            bebida = new Bebida();
        }
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public Mesa getMesa() {
        if (mesa == null) {
            mesa = new Mesa();
        }
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<SaborSelecionado> getSaboresSelecionados() {
        if (saboresSelecionados == null) {
            saboresSelecionados = new ArrayList<SaborSelecionado>();
        }
        return saboresSelecionados;
    }

    public void setSaboresSelecionados(List<SaborSelecionado> saboresSelecionados) {
        this.saboresSelecionados = saboresSelecionados;
    }

    public SaborSelecionado getSaborSelecionado() {
        if (saborSelecionado == null) {
            saborSelecionado = new SaborSelecionado();
            saborSelecionado.setSabor(new Sabor());
        }
        return saborSelecionado;
    }

    public void setSaborSelecionado(SaborSelecionado saborSelecionado) {
        this.saborSelecionado = saborSelecionado;
    }

    private void limpar() {
        pizza = null;
        model = null;
        model2 = null;
        model3 = null;
        model4 = null;
        model5 = null;
    }

    public String novo() {
        pedido = new Pedido();
        pizza = new Pizza();
        borda = new Borda();
        tamanho = new Tamanho();
        sabores = new ArrayList<Sabor>();
        return "cadPedido.faces";
    }
    
    public String btNovaPizza() {
        pizza = new Pizza();
        borda = new Borda();
        tamanho = new Tamanho();
        sabores = new ArrayList<Sabor>();
        return "addPizza.faces";
    }
    
    public String btNovaBebida() {
        pizza = new Pizza();
        borda = new Borda();
        tamanho = new Tamanho();
        sabores = new ArrayList<Sabor>();
        return "addBebida.faces";
    }

    public String novoSabor() {
        sabor = null;
        if (sabores == null) {
            sabores = new ArrayList<Sabor>();
        }
        saborSelecionado = null;
        if (saboresSelecionados == null) {
            saboresSelecionados = new ArrayList<SaborSelecionado>();
        }
        return "addSaborSelecionado.faces";
    }

    public String salvaSabor() {
        saboresSelecionados.add(saborSelecionado);
        tDao = new TamanhoDAOImp();
        pizza.setTamanho(tDao.pesquisa(tamanho.getId()));
        if (saboresSelecionados.size() == pizza.getTamanho().getNsabores()) {
            btSabor = true;
        }
        return "addPizza.faces";
    }

    public String voltar() {
        return "addPizza.faces";
    }

    public String pesq() {
        limpar();
        return "pesqPedido.faces";
    }

    public void calculaSabores() {
        for (int i = 0; i < pizza.getTamanho().getNsabores(); i++) {
            pizza.getSabores().add(new SaborSelecionado());
        }
    }

    public String salvar() {
        pdao = new PedidoDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (pedido.getId() == null) {
            pdao.salva(pedido);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pizza Salvo Com Sucesso!", ""));
        } else {
            pdao.altera(pedido);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pizza Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqPedido.faces";
    }

    public void pesquisa() {
        pdao = new PedidoDAOImp();
        List<Pedido> pedidos = pdao.getTodos();
        List<Pedido> pedidos2 = new ArrayList<Pedido>();
        boolean flag;
        for (Pedido item : pedidos) {
            flag = true;
            for (Pedido item2 : pedidos2) {
                if(item.getId().longValue() == item2.getId().longValue()){
                    flag = false;
                }
            }
            if (flag) {
                pedidos2.add(item);
            }
        }
        model = new ListDataModel(pedidos2);
        FacesContext context = FacesContext.getCurrentInstance();
        if (pedidos2.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pizza inesistente!", "Pedido inesistente!"));
            limpar();
        }
    }
    
    public void pesquisapizza() {
        pdao = new PedidoDAOImp();
        pedido = (Pedido) model.getRowData();
        pedido = pdao.pesquisa(pedido.getId());
        model5 = new ListDataModel(pedido.getPizzas());
    }
    
    public void pesquisasabor() {
        dao = new PizzaDAOImp();
        pizza = (Pizza) model5.getRowData();
        pizza = dao.pesquisa(pizza.getId());
        model2 = new ListDataModel(pizza.getSabores());
    }

    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            pdao = new PedidoDAOImp();
            pedido = (Pedido) model.getRowData();
            pdao.remove(pedido);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }

    public String alterar() {
        pedido = (Pedido) model.getRowData();
        return "cadPedido.faces";
    }

    public List<SelectItem> getComboSabor() {
        boolean flag;
        SaborDAO sdao = new SaborDAOImp();
        List<Sabor> list = sdao.getTodos();
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
        tDao = new TamanhoDAOImp();
        List<Tamanho> list = tDao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Tamanho forn : list) {
            listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
        }
        return listaCombo;
    }

    public List<SelectItem> getComboBorda() {
        BordaDAO boDao = new BordaDAOImp();
        List<Borda> list = boDao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Borda forn : list) {
            listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
        }
        return listaCombo;
    }
    
    public List<SelectItem> getComboBebida() {
        bDao = new BebidaDAOImp();
        List<Bebida> list = bDao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Bebida forn : list) {
            listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
        }
        return listaCombo;
    }
    
    public List<SelectItem> getComboMesa() {
        mDao = new MesaDAOImp();
        List<Mesa> list = mDao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Mesa forn : list) {
            listaCombo.add(new SelectItem(forn.getId(), forn.getNumero()));
        }
        return listaCombo;
    }
    
    public void deletarBebida() {
        bebida = (Bebida) model4.getRowData();
        pedido.getBebidas().remove(bebida);
        if(bebida.getId() != null){
            if(bebida == null){
                bebidas = new ArrayList<Bebida>();
            }
            bebidas.add(bebida);
        }
    }
    
    public void deletarPizza() {
        pizza = (Pizza) model5.getRowData();
        pedido.getPizzas().remove(pizza);
        if(pizza.getId() != null){
            if(pizza == null){
                pizzas = new ArrayList<Pizza>();
            }
            pizzas.add(pizza);
        }
    }
}
