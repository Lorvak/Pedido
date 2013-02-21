/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.controle;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hugo
 */
@WebFilter(servletNames = {"Faces Servlet"})
public class LoginFiltro implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        if (session.getAttribute("autenticado") != null || req.getRequestURI().endsWith("index.faces")) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("index.faces");
        }
    }

    @Override
    public void destroy() {
        
    }
}
