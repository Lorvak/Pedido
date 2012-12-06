/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import br.com.pi.dao.PedidoTeleDAO;
import br.com.pi.dao.PedidoTeleDAOImp;
import br.com.pi.dao.PaisDAO;
import br.com.pi.dao.PaisDAOImp;
import br.com.pi.entidade.PedidoTele;
import br.com.pi.entidade.Pais;
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
 * @author tecnicom
 */
@ManagedBean
@SessionScoped
public class PedidoTeleControle {

    private PedidoTele pedidoTele;
    private Pais pais;
    private PedidoTeleDAO dao;
    private DataModel model;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Pais getPais() {
        if (pais == null) {
            pais = new Pais();
        }
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public PedidoTele getPedidoTele() {
        return pedidoTele;
    }

    public void setPedidoTele(PedidoTele usuario) {
        this.pedidoTele = usuario;
    }
    
    private void limpar() {
        pedidoTele = null;
        pais = null;
        model = null;
    }
    
    public String novo() {
        pedidoTele = new PedidoTele();
        pais = new Pais();
        return "cadPedidoTele.faces";
    }
    
    public String pesq() {
        limpar();
        return "pesqPedidoTele.faces";
    }
    
    public String salvar() {
        dao = new PedidoTeleDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (pedidoTele.getId() == null) {
            dao.salva(pedidoTele);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PedidoTele Salvo Com Sucesso!", ""));
        } else {
            dao.altera(pedidoTele);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PedidoTele Alterado Com Sucesso!", ""));
        }
        limpar();
        return "pesqPedidoTele.faces";
    }
    
    public void pesquisaLike() {
        dao = new PedidoTeleDAOImp();
        List<PedidoTele> pedidoTelees = new ArrayList();
        model = new ListDataModel(pedidoTelees);
        FacesContext context = FacesContext.getCurrentInstance();
        if (pedidoTelees.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "PedidoTele inesistente!"));
            limpar();
        }
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            dao = new PedidoTeleDAOImp();
            pedidoTele = (PedidoTele) model.getRowData();
            dao.remove(pedidoTele);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PedidoTele Excluido com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi posivel executar a exclusão!", ""));
        }
        limpar();
        return "";
    }
    
    public String paginapesq() {
        limpar();
        return "pesPedidoTele.faces";
    }
    
    public String alterar() {
        pedidoTele = (PedidoTele) model.getRowData();
//        pais = pedidoTele.getPais();
        return "cadPedidoTele.faces";
    }
    
    public List<SelectItem> getComboPais(){
        PaisDAO pdao = new PaisDAOImp();
        List<Pais> paises = pdao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Pais forn : paises) {
            listaCombo.add(new SelectItem(forn.getId(), forn.getNome()));
        }
        return listaCombo;
    }
    
}
