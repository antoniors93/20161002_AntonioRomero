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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio
 */
@WebServlet(name = "ContadorVisitasCookies", urlPatterns = {"/ContadorVisitasCookies"})
public class ContadorVisitasCookies extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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

        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (int i = 0; i <= cookies.length; i++) {
                if (cookies[i].getName().equals("CONTADOR")) {
                    cookie = cookies[i];
                    break;
                }
            }
        }

        if (cookie == null) {
            cookie = new Cookie("CONTADOR", "0");
        } else if (request.getParameter("Borrar") != null) {
            cookie.setValue("0");
        }
        int contador = Integer.parseInt(cookie.getValue());
        contador = contador + 1;
        cookie.setValue(String.valueOf(contador));
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ContadorVisitasCookies</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form method=\"post\" action=\"ContadorVisitasCookies\">");
            out.println("<h2>El numero de visitas es: " + contador + "</h2>");
            if (request.getParameter("Borrar") != null) {
                out.println("Nombre: " + cookie.getName() + "</br>");
                out.println("Valor: " + cookie.getValue() + "</br>");
                out.println("Tiempo de vida: " + cookie.getMaxAge() + "</br>");
                out.println("Version: " + cookie.getVersion() + "</br>");
            }
            out.println("</br><div id=\"botones\">");
            out.println("<input type=\"submit\" name=\"Borrar\" value=\"Borrar\" formaction=\"ContadorVisitasCookies\"/> ");
            out.println("<input type=\"submit\" name=\"Recargar\" value=\"Recargar\" formaction=\"ContadorVisitasCookies\"/> ");
            out.println("</div>");
            out.println("</br><a href='index.html' > Enlace a index </a>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
