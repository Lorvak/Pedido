/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.services;

import br.sc.senac.pontoeletronico.integracao.DAOFactoryController;
import br.sc.senac.pontoeletronico.integracao.iDAORegistro;
import br.sc.senac.pontoeletronico.modelo.Projeto;
import br.sc.senac.pontoeletronico.modelo.Registro;
import br.sc.senac.pontoeletronico.modelo.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Administrador
 */
@WebService(serviceName = "PontoEletronicoFacade")
public class PontoEletronicoFacade {
   
    @WebMethod(operationName = "getProjetos")
    public String[] getProjetos() {
        Projeto[] lista= DAOFactoryController.getDaoFactory().getDaoProjeto().getProjetos();
        String[] saida= new String[lista.length];
        for (int i=0;i<lista.length;i++){
            Projeto p= lista[i];
            saida[i]=p.getId()+" - "+p.getNome();
        }
        return saida;
    }
    
    @WebMethod(operationName = "registra")
    public int registra(@WebParam(name = "descricao") String descricao,@WebParam(name = "ano") String ano,@WebParam(name = "mes") String mes,@WebParam(name = "dia") String dia,@WebParam(name = "horas") String horas, @WebParam(name = "idProjeto") int idProjeto, @WebParam(name = "idUsuario") int idUsuario) {
        System.out.println("Inserindo registro...");
        iDAORegistro dao = DAOFactoryController.getDaoFactory().getDaoRegistro();
        Registro r = new Registro();
        Usuario u= new Usuario();
        u.setId(idUsuario);
        r.setUsuario(u);
        Projeto p = new Projeto();
        p.setId(idProjeto);
        r.setProjeto(p);
        r.setData(new Date(Integer.parseInt(ano),Integer.parseInt(mes),Integer.parseInt(dia)));
        r.setTempo(new Float(horas).floatValue());
        r.setDescricao(descricao);
        int chave=dao.insereRegistro(r);
        return chave;
    }
    
    @WebMethod(operationName = "getRegistros")
    public String[] getRegistros(@WebParam(name = "ano") String ano,@WebParam(name = "mes") String mes,@WebParam(name = "dia") String dia) {
        Date dataBusca= new Date(Integer.parseInt(ano),Integer.parseInt(mes),Integer.parseInt(dia)); 
        System.out.println("Data entrada: "+dataBusca.toString());
        Registro[] lista= DAOFactoryController.getDaoFactory().getDaoRegistro().getRegistros(dataBusca);
        String[] saida= new String[lista.length];
        for (int i=0;i<lista.length;i++){
            Registro r= lista[i];
            saida[i]=r.getId()+"- "+r.getDescricao() + " - "+r.getTempo() ;
        }
        System.out.println("Encontrados:"+ saida.length);
        return saida;
    }
        
}
