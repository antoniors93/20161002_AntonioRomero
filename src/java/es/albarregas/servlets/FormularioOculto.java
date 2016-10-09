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
@WebServlet(name = "FormularioOculto", urlPatterns = {"/FormularioOculto"})
public class FormularioOculto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Enumeration<String>parametros;
    
    //metodo que nos muestra los datos introducidos en el formulario al clikear en enviar
    private void mostrarDatos(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        int pref=1;
        parametros=request.getParameterNames();
        
        out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormularioOculto</title>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Formulario oculto</h1>");
            out.println("<form method=\"post\" action=\"FormularioOculto\">");
        while(parametros.hasMoreElements()){
            String nombre=parametros.nextElement();
            String valor=request.getParameter(nombre);
            
            if(!nombre.equalsIgnoreCase("Enviar")&&!valor.equalsIgnoreCase("")){ 
                if(nombre.startsWith("Afi")){//mostramos las aficiones
                    //con este if nos aseguramos de que la palabra aficiones aparezca una sola vez  
                    if(pref==1){
                    out.println("Aficiones:</br>");
                    pref++;
                    }
                    out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+valor+"</br>");
                    out.println("<input type=\"hidden\" name="+nombre+" value="+valor+">");
                }else{//mostramos el resto de datos
                    out.println(nombre+": "+valor+"</br>");
                    out.println("<input type=\"hidden\" name="+nombre+" value="+valor+">");
                }    
            }
        }   
            out.println("<input type=\"submit\" name=\"Volver\" value=\"Volver\" formaction=\"FormularioOculto\"/> ");
            out.println("<input type=\"submit\" name=\"Aceptar\" value=\"Aceptar\" formaction=\"FormularioOculto\"/> ");
             
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormularioOculto</title>"); 
            out.println("<Link rel=\"stylesheet\" href=\"CSS/cssFormulario.css\"/>");
            out.println("</head>");
            out.println("<body>");
            
            //si aceptar se ha pulsado en la ventana que nos muestra los datos introducidos se hace lo siguiente
            if(request.getParameter("Aceptar")!=null){
                out.println("<h2>Datos introducidos correctamente</h2>");
                out.println("</br> <a href='index.html' > Enlace a index </a>");
            }else{
            if(request.getParameter("Enviar")!=null){ //si enviar se ha pulsado en el formulario llamamos al metodo que muestra los datos
                mostrarDatos(request,response);
            }else{// en caso contrario mostramos el formulario 
            out.println("<h2>Formulario</h2>");
            out.println("<form method=\"post\" action=\"FormularioOculto\">");
            out.println("<fieldset id=\"datosPers\">");
            //datos personales
                out.println("<legend>Datos personales</legend>");
                out.println("</br>");
                    out.println("<div id=\"nombre\">");
                        out.println("<label>Nombre:</label>");
                        //si nombre no es nulo y el boton limpiar no se ha pulsado le pasamos parametro
                        if(request.getParameter("Nombre")!=null&&request.getParameter("Limpiar")==null){
                        out.println("<input type=\"text\" name=\"Nombre\" value="+request.getParameter("Nombre")+">");
                        }else{
                        out.println("<input type=\"text\" name=\"Nombre\" value=\"\">");    
                        }
                    out.println("</div></br>");

                    out.println("<div id=\"apellidos\">");
                        out.println("<label>Apellidos:</label>");
                        //si apellidos no es nulo y el boton limpiar no se ha pulsado le pasamos parametro
                        if(request.getParameter("Apellidos")!=null&&request.getParameter("Limpiar")==null){
                        out.println("<input type=\"text\" name=\"Apellidos\" value="+request.getParameter("Apellidos")+">");
                        }else{
                        out.println("<input type=\"text\" name=\"Apellidos\" value=\"\">");    
                        }
                        out.println("</div></br>");

                    out.println("<div id=\"sexo\">");
                        out.println("<label>Sexo:</label>");
                        //si sexo no es nulo y limpiar no se ha pulsado se comprueba que radio esta marcado
                        if(request.getParameter("Sexo")!=null&&request.getParameter("Limpiar")==null){
                            if(request.getParameter("Sexo").equalsIgnoreCase("Mujer")){
                                out.println("<input type=\"radio\" name=\"Sexo\" value=\"Hombre\"/>Hombre"); 
                                out.println("<input type=\"radio\" name=\"Sexo\" value=\"Mujer\" checked/>Mujer");
                            }else{
                                out.println("<input type=\"radio\" name=\"Sexo\" value=\"Hombre\" checked/>Hombre"); 
                                out.println("<input type=\"radio\" name=\"Sexo\" value=\"Mujer\"/>Mujer");
                            }
                        }else{
                            out.println("<input type=\"radio\" name=\"Sexo\" value=\"Hombre\" checked/>Hombre"); 
                            out.println("<input type=\"radio\" name=\"Sexo\" value=\"Mujer\"/>Mujer");
                        }
                    out.println("</div> <br/>");
                    
                    out.println("<div id=\"aficiones\">");
                        out.println("<label>Aficiones(uno o varios,CTRL)</label> </br>");
                        //en cada check se comprueba si estaba marcado y limpiar ha sido pulsado
                           if(request.getParameter("Afi1")!=null&&request.getParameter("Limpiar")==null)
                                out.println("<input type=\"checkbox\" name=\"Afi1\" value=\"Deporte\" checked> Deporte <br>");
                            else
                                out.println("<input type=\"checkbox\" name=\"Afi1\" value=\"Deporte\"> Deporte<br>");
                            
                            if(request.getParameter("Afi2")!=null&&request.getParameter("Limpiar")==null)
                                out.println("<input type=\"checkbox\" name=\"Afi2\" value=\"Lectura\" checked> Lectura <br>");
                            else
                                out.println("<input type=\"checkbox\" name=\"Afi2\" value=\"Lectura\"> Lectura <br>");
                            
                            if(request.getParameter("Afi3")!=null&&request.getParameter("Limpiar")==null)
                                out.println("<input type=\"checkbox\" name=\"Afi3\" value=\"Cine\" checked> Cine <br>");
                            else   
                                out.println("<input type=\"checkbox\" name=\"Afi3\" value=\"Cine\"> Cine <br>");
                            
                            if(request.getParameter("Afi4")!=null&&request.getParameter("Limpiar")==null)
                                out.println("<input type=\"checkbox\" name=\"Afi4\" value=\"Juegos\" checked> Juegos <br>");
                            else   
                                out.println("<input type=\"checkbox\" name=\"Afi4\" value=\"Juegos\"> Juegos <br>");

                    out.println("</div> <br/>");
            out.println("</fieldset>");
            
                out.println("<div id=\"botones\">");
                    out.println("<input type=\"submit\" name=\"Limpiar\" value=\"Limpiar\" formaction=\"FormularioOculto\"/> ");
                    out.println("<input type=\"submit\" name=\"Enviar\" value=\"Enviar\" formaction=\"FormularioOculto\"/> ");    
                out.println("</div>");
                
            out.println("</form>");            
            out.println("</body>");
            out.println("</html>");
            

            }
            }
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
