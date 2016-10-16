/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Antonio
 */
@WebServlet(name = "ContadorVisitasSesiones", urlPatterns = {"/ContadorVisitasSesiones"})
public class ContadorVisitasSesiones extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Integer contador = 0;
        HttpSession sesion = request.getSession(true);
        
        if (request.getParameter("Invalidar") != null) {
            sesion.invalidate();
            sesion = request.getSession(true);
        }

        if (sesion.getAttribute("CONTADOR") != null) {
            contador = (Integer) sesion.getAttribute("CONTADOR");
            contador++;
            sesion.setAttribute("CONTADOR", contador);
        } else {
            contador++;
            sesion.setAttribute("CONTADOR", contador);
        }



        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ContadorVisitasSesiones</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form method=\"post\" action=\"ContadorVisitasSesiones\">");
            out.println("<h1>El numero de visitas es: " + sesion.getAttribute("CONTADOR") + "</h1>");
            out.println("<input type=\"checkbox\" name=\"Invalidar\">Invalidar</input></br>");
            out.println("</br><div id=\"botones\">");
            out.println("<input type=\"submit\" name=\"Recargar\" value=\"Recargar\" formaction=\"ContadorVisitasSesiones\"/> ");
            out.println("</div>");
            out.println("</br><a href='index.html' > Enlace a index </a>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
