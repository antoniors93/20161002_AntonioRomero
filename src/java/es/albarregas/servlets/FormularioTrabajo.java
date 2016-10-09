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
@WebServlet(name = "FormularioTrabajo", urlPatterns = {"/FormularioTrabajo"})
public class FormularioTrabajo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    boolean controlarNombre;
    boolean controlarUsuario;
    boolean controlarPass;
    boolean controlarFecha;
    Enumeration<String>parametros;
    
    //metodo en el que mostramos los datos
    private void mostrarDatosCorrectos(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        int fecha=1;
        int pref=1;
        parametros=request.getParameterNames();
        
        //recorremos todos los parametros para ir mostrando
        while(parametros.hasMoreElements()){
            String nombre=parametros.nextElement();
            String valor=request.getParameter(nombre);
            
            //se hace una excepcion para mostrar la fecha y se comprueba que el valor de enviar no aparezca
            if(!nombre.equalsIgnoreCase("Enviar")&&!valor.equalsIgnoreCase("")){
                if(nombre.equalsIgnoreCase("Dia")||nombre.equalsIgnoreCase("Mes")||nombre.equalsIgnoreCase("Ano")){
                    if(fecha==1)
                        out.println("Fecha de nacimiento: "+request.getParameter("Dia")+"/"+request.getParameter("Mes")+"/"+request.getParameter("Ano")+"</br>");
                fecha++;
                }else{
                    if(nombre.startsWith("Prefe")){
                        if(pref==1){
                        out.println("Preferencias:</br>");
                        pref++;
                        }
                        out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+valor+"</br>");
                    }else{
                        out.println(nombre+": "+valor+"</br>");
                    }    
                }
            }
        }
    }
    
    //metodo que llamamos para que controle los errores del formulario
    private void controlarErrores(HttpServletRequest request){

        int aa=Integer.parseInt(request.getParameter("Ano"));
        int mm=Integer.parseInt(request.getParameter("Mes"));
        int dd=Integer.parseInt(request.getParameter("Dia"));
        
        //comprobamos que estos campos no esten vacios
        if (!"".equals(request.getParameter("Nombre"))){
            controlarNombre=true;
        }else{
            controlarNombre=false;
        }
        
        if (!"".equals(request.getParameter("Usuario"))){
            controlarUsuario=true;
        }else{
            controlarUsuario=false;
        }
        
        if (!"".equals(request.getParameter("Password"))){
            controlarPass=true;
        }else{
            controlarPass=false;
        }
        
        //comprobamos que la fecha marcada existe
        if (mm == 4 || mm == 6 || mm == 9 || mm == 11) {
            if (dd == 31) {
                controlarFecha = false;
            }
        }else{
            if (mm == 2) {
                //formula de años bisiestos
                if ((aa % 4 == 0) && (aa % 100 != 0) || (aa % 400 == 0)) {
                    if (dd <= 29) 
                    controlarFecha = true;
                    
                }else{ 
                    if (dd <= 28) {
                        controlarFecha = true;
                    }else {
                        controlarFecha = false;
                    }
                }    
            }else{
              controlarFecha = true;  
            }
        }
    }
    
    //metodo que nos muestra el mensaje de que hay datos erroneos y crea el formulario oculto
    private void mostrarMensajeError(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormularioTrabajo</title>");
            out.println("<Link rel=\"stylesheet\" href=\"CSS/cssFormulario.css\"/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Formulario</h2>");
                
                out.println("<h3>Existen datos erroneos</h3>");
                out.println("<form method=\"post\" action=\"FormularioTrabajo\">");
                parametros=request.getParameterNames();
                //recorremos todos los parametros para crear el formulario oculto
                while(parametros.hasMoreElements()){
                String nombre=parametros.nextElement();
                String valor=request.getParameter(nombre);
                out.println("<input type=\"hidden\" name="+nombre+" value="+valor+">");
                }
                out.println("<input type=\"submit\" name=\"Volver\" value=\"Volver\" formaction=\"FormularioTrabajo\"/>");                            
                out.println("</form>");         

            out.println("</body>");
            out.println("</html>");
        }
    }
            
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormularioTrabajo</title>");
            out.println("<Link rel=\"stylesheet\" href=\"CSS/cssFormulario.css\"/>");
            out.println("</head>");
            out.println("<body>");
            controlarErrores(request);
            if(controlarNombre&&controlarUsuario&&controlarPass&&controlarFecha){//si no hay ningun error
                out.println("<h2>Datos introducidos correctamente:</h2></br></br>");
                mostrarDatosCorrectos(request,response);
                out.println("</br> <a href='index.html' > Enlace a index </a>");
            }else{
                if(request.getParameter("Volver")==null){//si hay error y se ha pulsado volver a atras
                    mostrarMensajeError(request,response);
                }else{
                //se muestran los errores
                if(!controlarNombre)
                out.println("<p>Nombre incorrecto</p>");
                if(!controlarUsuario)
                out.println("<p>Usuario incorrecto</p>");
                if(!controlarPass)
                out.println("<p>Password incorrecta</p>");
                if(!controlarFecha)
                out.println("<p>Fecha incorrecta</p>");
                
                //mostramos el formulario con los datos cargados
                out.println("</br><h2>Formulario</h2>");
                 out.println("<form method=\"post\" action=\"FormularioTrabajo\">");
                    out.println("<fieldset id=\"datosPers\">");
                        out.println("<legend>Datos personales</legend>");
                        out.println("</br>");

                            out.println("<div id=\"nombre\">");
                                out.println("<label>*Nombre:</label>");
                                out.println("<input type=\"text\" name=\"Nombre\" value="+request.getParameter("Nombre")+">");
                            out.println("</div></br>");
                        
                            out.println("<div id=\"apellidos\">");
                                out.println("<label>Apellidos:</label>");
                                out.println("<input type=\"text\" name=\"Apellidos\" value="+request.getParameter("Apellidos")+">");
                            out.println("</div></br>");
                            
                            out.println("<div id=\"sexo\">");
                                out.println("<label>Sexo:</label>");
                                if(request.getParameter("Sexo").equalsIgnoreCase("Mujer")){
                                    out.println("<input type=\"radio\" name=\"Sexo\" value=\"Hombre\"/>Hombre"); 
                                    out.println("<input type=\"radio\" name=\"Sexo\" value=\"Mujer\" checked/>Mujer");
                                }else{
                                    out.println("<input type=\"radio\" name=\"Sexo\" value=\"Hombre\" checked/>Hombre"); 
                                    out.println("<input type=\"radio\" name=\"Sexo\" value=\"Mujer\" />Mujer");
                                }
                            out.println("</div> <br/>");
                            
                            out.println("<div id=\"fechaNac\">");
                                out.println("<label >Fecha de nacimiento:</label>");
                                out.println("<select name=\"Dia\">");
                                    out.println("<option selected>" +request.getParameter("Dia")+ "</option>");
                                    for(int i=1;i<=31;i++)
                                    out.println("<option>"+i+"</option>");    
                                out.println("</select>");
                
                                out.println("<select name=\"Mes\">");
                                    out.println("<option selected>" +request.getParameter("Mes")+ "</option>");
                                    for(int i=1;i<=12;i++)
                                    out.println("<option>"+i+"</option>");
                                out.println("</select>");
                                
                                out.println("<select name=\"Ano\">");
                                    out.println("<option selected>" +request.getParameter("Ano")+ "</option>");
                                    for(int i=1950;i<=2016;i++)
                                    out.println("<option>"+i+"</option>");
                                out.println("</select>");
                            
                            out.println("</div></br>");
                    out.println("</fieldset>");

                    out.println("<fieldset id=\"datosAcces\">");         
                        out.println("<legend>Datos de acceso</legend>");
                        out.println("</br>");

                            out.println("<div id=\"usuario\">");
                                out.println("<label>*Usuario:</label>");
                                out.println("<input type=\"text\" name=\"Usuario\" value="+request.getParameter("Usuario")+">");
                            out.println("</div></br>");

                            out.println("<div id=\"password\">");
                                out.println("<label>*Password:</label>");
                                out.println("<input type=\"password\" name=\"Password\" value="+request.getParameter("Password")+">");
                            out.println("</div></br>");

                    out.println("</fieldset>");

                    out.println("<fieldset id=\"infoGeneral\">");                        
                        out.println("<legend>Informaion general</legend>");
                        out.println("</br>");

                        out.println("<div id=\"preferencias\">");
                            out.println("<label>Preferencias:</label></br>");
                            
                            if(request.getParameter("Prefe1")!=null)
                                out.println("<input type=\"checkbox\" name=\"Prefe1\" value=\"Deporte\" checked> Deporte <br>");
                            else
                                out.println("<input type=\"checkbox\" name=\"Prefe1\" value=\"Deporte\"> Deporte<br>");
                            
                            if(request.getParameter("Prefe2")!=null)
                                out.println("<input type=\"checkbox\" name=\"Prefe2\" value=\"Lectura\" checked> Lectura <br>");
                            else
                                out.println("<input type=\"checkbox\" name=\"Prefe2\" value=\"Lectura\"> Lectura <br>");
                            
                            if(request.getParameter("Prefe3")!=null)
                                out.println("<input type=\"checkbox\" name=\"Prefe3\" value=\"Cine\" checked> Cine <br>");
                            else   
                                out.println("<input type=\"checkbox\" name=\"Prefe3\" value=\"Cine\"> Cine <br>");
                            
                            if(request.getParameter("Afi3")!=null)
                                out.println("<input type=\"checkbox\" name=\"Prefe4\" value=\"Juegos\" checked> Juegos <br>");
                            else   
                                out.println("<input type=\"checkbox\" name=\"Prefe4\" value=\"Juegos\"> Juegos <br>");
                            
                        out.println("</div>");
                        out.println("</br>");

                    out.println("</fieldset>");
                        out.println("<div id=\"botones\">");
                            out.println("<input type=\"submit\" name=\"Enviar\" value=\"Enviar\"/>");
                            out.println("<input type=\"submit\" value=\"Borrar\" value=\"Borrar\" formaction=\"HTML/formularioTrabajo.html\"/> ");              
                            out.println("</div>");
                        out.println("</form>");   
            }

            out.println("</body>");
            out.println("</html>");
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
