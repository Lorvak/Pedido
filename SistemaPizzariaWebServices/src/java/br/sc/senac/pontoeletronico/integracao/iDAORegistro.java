/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.integracao;

import br.sc.senac.pontoeletronico.modelo.Registro;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public interface iDAORegistro {
    public Registro[] getRegistros(Date data);
    public int insereRegistro(Registro registro);
}
