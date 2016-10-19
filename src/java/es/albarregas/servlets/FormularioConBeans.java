/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import es.albarregas.beans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "FormularioConBeans", urlPatterns = {"/FormularioConBeans"})
public class FormularioConBeans extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormularioConBeans</title>");
            out.println("<Link rel=\"stylesheet\" href=\"CSS/cssFormulario.css\"/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Formulario</h2>");
            out.println("<form method=\"post\" action=\"FormularioConBeans\">");

            if (request.getParameter("Enviar") == null && request.getParameter("Cerrar") == null) {
                out.println("<fieldset id=\"datosPers\">");
                out.println("<legend>Datos primer usuario</legend>");
                out.println("</br>");

                out.println("<div id=\"id\">");
                out.println("<label>Id:</label>");
                out.println("<input type=\"text\" name=\"Id1\">");
                out.println("</div></br>");

                out.println("<div id=\"nombre\">");
                out.println("<label>Nombre:</label>");
                out.println("<input type=\"text\" name=\"Nombre1\">");
                out.println("</div></br>");

                out.println("<div id=\"sueldo\">");
                out.println("<label>Sueldo:</label>");
                out.println("<input type=\"text\" name=\"Sueldo1\">");
                out.println("</div></br>");
                out.println("</legend>");
                out.println("</fieldset></br>");

                out.println("<fieldset id=\"datosPers\">");
                out.println("<legend>Datos segundo usuario</legend>");
                out.println("</br>");

                out.println("<div id=\"id\">");
                out.println("<label>Id:</label>");
                out.println("<input type=\"text\" name=\"Id2\">");
                out.println("</div></br>");

                out.println("<div id=\"nombre\">");
                out.println("<label>Nombre:</label>");
                out.println("<input type=\"text\" name=\"Nombre2\">");
                out.println("</div></br>");

                out.println("<div id=\"sueldo\">");
                out.println("<label>Sueldo:</label>");
                out.println("<input type=\"text\" name=\"Sueldo2\">");
                out.println("</div></br>");
                out.println("</legend>");
                out.println("</fieldset></br>");

                out.println("<fieldset id=\"datosPers\">");
                out.println("<legend>Datos tercer usuario</legend>");
                out.println("</br>");

                out.println("<div id=\"id\">");
                out.println("<label>Id:</label>");
                out.println("<input type=\"text\" name=\"Id3\">");
                out.println("</div></br>");

                out.println("<div id=\"nombre\">");
                out.println("<label>Nombre:</label>");
                out.println("<input type=\"text\" name=\"Nombre3\">");
                out.println("</div></br>");

                out.println("<div id=\"sueldo\">");
                out.println("<label>Sueldo:</label>");
                out.println("<input type=\"text\" name=\"Sueldo3\">");
                out.println("</div></br>");
                out.println("</legend>");
                out.println("</fieldset></br>");

                out.println("<div id=\"botones\">");
                out.println("<input type=\"submit\" name=\"Enviar\" value=\"Enviar\" formaction=\"FormularioConBeans\"/> ");
                out.println("</div></br>");

            } else if (request.getParameter("Cerrar") == null) {

                out.println("<h3>Los datos se han guardado</h3>");
                out.println("<input type=\"submit\" name=\"Cerrar\" value=\"Cerrar\" formaction=\"FormularioConBeans\"/> ");

                HttpSession sesion = request.getSession();
                ArrayList<Usuario> usuarios = new ArrayList();
                
                //recogemos los datos de los usuarios y los almacenamos en el objeto para despues guardarlos en el arraylist
                Usuario usr1 = new Usuario();
                usr1.setId(Integer.parseInt(request.getParameter("Id1")));
                usr1.setNombre(request.getParameter("Nombre1"));
                usr1.setSueldo(Float.parseFloat(request.getParameter("Sueldo1")));
                usuarios.add(usr1);

                Usuario usr2 = new Usuario();
                usr2.setId(Integer.parseInt(request.getParameter("Id2")));
                usr2.setNombre(request.getParameter("Nombre2"));
                usr2.setSueldo(Float.parseFloat(request.getParameter("Sueldo2")));
                usuarios.add(usr2);

                Usuario usr3 = new Usuario();
                usr3.setId(Integer.parseInt(request.getParameter("Id3")));
                usr3.setNombre(request.getParameter("Nombre3"));
                usr3.setSueldo(Float.parseFloat(request.getParameter("Sueldo3")));
                usuarios.add(usr3);
                sesion.setAttribute("users", usuarios);

            } else {//cuando pulsamos cerrar mostramos los datos

                out.println("<h3>Datos introducidos:</h3>");
                HttpSession sesion = request.getSession(true);
                ArrayList<Usuario> usuarios = (ArrayList) sesion.getAttribute("users");

                if (usuarios != null) {
                    for (int i = 0; i < usuarios.size(); i++) {
                        out.println("</br>-Usuario" + (i + 1) + ": </br>");
                        out.println("Id: " + usuarios.get(i).getId() + "</br>");
                        out.println("Nombre: " + usuarios.get(i).getNombre() + "</br>");
                        out.println("Sueldo: " + usuarios.get(i).getSueldo() + "</br>");
                    }
                }
                sesion.invalidate();
                out.println("</br><a href='index.html' > Enlace a index </a>");

            }

            out.println("</form>");
            out.println("</body>");
            out.println("</html>");

        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
