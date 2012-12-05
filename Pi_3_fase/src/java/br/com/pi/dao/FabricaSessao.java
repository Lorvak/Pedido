/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author tecnicom
 */
public class FabricaSessao {
    private static SessionFactory sf;
    
    public static SessionFactory abreConexao(){
        if (sf == null) {
            Configuration cfg = new AnnotationConfiguration();
            cfg.configure("/br/com/pi/dao/hibernate.cfg.xml");
            sf = cfg.buildSessionFactory();
        }
        return sf;
    }
}
