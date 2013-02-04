/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sc.senac.pontoeletronico.integracao;

/**
 *
 * @author Administrador
 */
public interface iDAOUsuario { 
    public int autentica(String login, String senha);
}