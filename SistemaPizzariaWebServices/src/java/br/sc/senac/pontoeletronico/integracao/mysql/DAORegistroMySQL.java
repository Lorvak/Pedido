/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.integracao.mysql;

import br.sc.senac.pontoeletronico.integracao.iDAORegistro;
import br.sc.senac.pontoeletronico.modelo.Registro;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
public class DAORegistroMySQL implements iDAORegistro {

    @Override
    public Registro[] getRegistros(Date data) {
       DataSourceMySQL ds= new DataSourceMySQL();
       EntityManager em=ds.getEntityManager();
       Query q= em.createQuery("select r from Registro r where r.data=:data");
       q.setParameter("data", data);
        System.out.println("Data SQL:"+data.toString());
       List l=q.getResultList();
       Registro[] registros= new Registro[l.size()];
       for (int i=0;i<registros.length;i++){
           Registro r=(Registro)l.get(i);
           registros[i]=r;
       }
       return registros;
    }

    @Override
    public int insereRegistro(Registro registro) {
        DataSourceMySQL ds= new DataSourceMySQL();
        int chave=-1;
        try{
            Connection con=ds.getCon();
            PreparedStatement ps= con.prepareStatement("insert into registro (chave_projeto,chave_usuario,data,descricao,tempo) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, registro.getProjeto().getId().intValue());
            ps.setInt(2, registro.getUsuario().getId().intValue());
            ps.setDate(3, new java.sql.Date(registro.getData().getTime()));
            ps.setString(4, registro.getDescricao());
            ps.setDouble(5, registro.getTempo());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                chave=rs.getInt(1);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return chave;
     }
    
    public void deletaTestes(){
         DataSourceMySQL ds= new DataSourceMySQL();
        
        try{
            Connection con=ds.getCon();
            PreparedStatement ps= con.prepareStatement("delete from registro where descricao='Teste'"); 
            ps.execute();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
