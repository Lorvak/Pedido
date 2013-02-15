/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Junior
 */
public class Gerador {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SIESDPU");
        factory.close();
    }

}
