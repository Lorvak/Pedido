/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.integracao.mysql;

import br.sc.senac.pontoeletronico.integracao.iDAOUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Administrador
 */
public class DAOUsuarioMySQL implements iDAOUsuario {

    @Override
    public int autentica(String login, String senha) {
        int id=-1;
        DataSourceMySQL ds= new DataSourceMySQL();
        try{
            Connection con=ds.getCon();
            PreparedStatement ps=con.prepareStatement("select chave from usuario where login=? and hash_senha=md5(?)");
            ps.setString(1, login);
            ps.setString(2, senha);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                id=rs.getInt(1);
            }
         }catch(Exception e){
            System.err.println("Erro:"+e.getMessage()); 
        }
        return id;
    }
    
}
