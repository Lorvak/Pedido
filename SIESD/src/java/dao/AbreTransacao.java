package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Junior
 */
public class AbreTransacao {

    private static SessionFactory sf;

    private static SessionFactory abreFabrica() {
        //Cria objeto que receberá as configurações
        Configuration cfg = new AnnotationConfiguration();
        //Informe o arquivo XML que contém a configurações
        cfg.configure("/dao/hibernate.cfg.xml");
        //Cria uma fábrica de sessões.
        //Deve existir apenas uma instância na aplicação
        sf = cfg.buildSessionFactory();
        return sf;
    }
    public static SessionFactory abreSessao(){
        if(sf == null){
            abreFabrica();
        }
        return sf;
    }
}
