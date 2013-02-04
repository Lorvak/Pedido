package br.sc.senac.pontoeletronico.integracao.mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataSourceMySQL {
    
    private static Connection con = null;

    public Connection getCon() throws Exception, ClassNotFoundException, SQLException {
        this.ConectaBD();
        return con;
    }

    private void ConectaBD() throws Exception, ClassNotFoundException, SQLException {
        if (con == null) {
	    Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/ponto_eletronico";
            String user = "root";
            String passwd = "";
            con = DriverManager.getConnection(url, user, passwd);
        }
    }   
    
    public EntityManager getEntityManager(){
       EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PontoEletronicoPU");
       return entityManagerFactory.createEntityManager();
    }
}