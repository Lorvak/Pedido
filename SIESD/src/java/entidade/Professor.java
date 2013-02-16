/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Junior
 */
@Entity
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Professor extends Pessoa {

    private static final long serialVersionUID = 1L;
    
    private int totalHoras;
    private String cracha;
    private double vlHoras;

    public String getCracha() {
        return cracha;
    }

    public void setCracha(String cracha) {
        this.cracha = cracha;
    }

    public int getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(int totalHoras) {
        this.totalHoras = totalHoras;
    }

    public double getVlHoras() {
        return vlHoras;
    }

    public void setVlHoras(double vlHoras) {
        this.vlHoras = vlHoras;
    }
    
}
