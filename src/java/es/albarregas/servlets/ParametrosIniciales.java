/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio
 */
@WebServlet(name = "ParametrosIniciales", urlPatterns = {"/ParametrosIniciales"})
public class ParametrosIniciales extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

            Enumeration<String>parametros=request.getParameterNames();
            while(parametros.hasMoreElements()){
                String elemento=parametros.nextElement();
                String valor=request.getParameter(elemento);
                out.println(elemento+" - "+valor);
            }
              
            out.println("</br> <a href='index.html' > Enlace a index </a>");
            out.println("</body>");
            out.println("</html>");
    }

}
