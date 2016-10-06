/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author antonio
 */
@WebServlet(name = "CicloVida", urlPatterns = {"/CicloVida"})
public class CicloVida extends HttpServlet {    
    


   @Override
    public void init(ServletConfig config){
        System.out.println("init()");
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaludoAnotacion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Par&aacute;metros iniciales</h1>");
            out.println("</br> <a href='index.html' > Enlace a index </a>");
            out.println("</body>");
            out.println("</html>");
            System.out.println("service()");
    }
    
    @Override
    public void destroy(){
        System.out.println("destroy()");
    }
    

}
