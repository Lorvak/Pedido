/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Eduardo M. Silveira
 */
public class Gerador {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Pi_3_fasePU");
        EntityManager manager = factory.createEntityManager();
    }
}
