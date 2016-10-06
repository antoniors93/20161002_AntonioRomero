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
 * @author antonio
 */
@WebServlet(name = "Formulario2", urlPatterns = {"/Formulario2"})
public class Formulario2 extends HttpServlet {

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
            out.println("<title>Servlet FormularioCompleto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Resultados del formulario </h1>");
            Enumeration<String> parametros = request.getParameterNames();
            int cont=0;
            int cont2=0;
            while (parametros.hasMoreElements()) {
                String elemento = parametros.nextElement();
                String valor = request.getParameter(elemento);
                
                if (elemento.equalsIgnoreCase("Enviar")) {
                } else{
                    if (elemento.equalsIgnoreCase("Aficiones")) {
                    String[] aficiones = new String[6];
                    aficiones=request.getParameterValues("Aficiones");
                    out.print("Aficiones:");
                    out.print("<ul>");
                    for (int i = 0; i < aficiones.length; i++) {
                        out.print("<li>"+aficiones[i]+"</li>");
                    }
                     out.print("</ul>");
                    }else{
                        if(elemento.equalsIgnoreCase("EstadoCivil")){
                            String[] estado=new String[4];
                            estado=request.getParameterValues("EstadoCivil");
                            out.print("Estado civil: ");
                            for (int i = 0; i < estado.length; i++) {
                            out.print(estado[i]+ "</br>");
                            }
                        }else{
                            if(elemento.equalsIgnoreCase("Internet")){
                            String[] internet=new String[2];
                            internet=request.getParameterValues("Internet");
                            out.print("Internet: ");
                            for (int i = 0; i < internet.length; i++) {
                            out.print(internet[i]+ "</br>");
                            }
                        }else{
                    out.print(elemento + " : " + valor + "</br>");
                    }
                    }
                    }
            }
                
        }
                if(request.getParameter("Aficiones")==null&&cont==0){
                        out.print("Aficiones: Ninguna seleccionada </br>");
                        cont++;
                }
                if(request.getParameter("SistOperativo")==null&&cont2==0){
                        out.print("Sistema operativo: Ninguno seleccionado");
                        cont2++;
                }
                out.println("</br> <a href='index.html' > Enlace a index </a>");
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
        //processRequest(request, response);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Formulario1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultados del formulario</h1>");
            out.println("No se ha pasado por el formulario");
            out.println("</br> <a href='index.html' > Enlace a index </a>");
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
