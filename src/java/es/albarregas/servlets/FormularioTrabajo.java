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
    
    private void controlarErrores(HttpServletRequest request){

        int aa=Integer.parseInt(request.getParameter("Ano"));
        int mm=Integer.parseInt(request.getParameter("Mes"));
        int dd=Integer.parseInt(request.getParameter("Dia"));
        
        if ("".equals(request.getParameter("Nombre"))||request.getParameter("Nombre")==null) {
            controlarNombre=false;
        } else {
            controlarNombre=true;
        }
        if ("".equals(request.getParameter("Usuario"))||request.getParameter("Usuario")==null) {
            controlarUsuario=false;
        } else {
            controlarUsuario=true;
        }
        if ("".equals(request.getParameter("Password"))||request.getParameter("Password")==null) {
            controlarPass=false;
        } else {
            controlarPass=true;
        }
        
        if (mm == 4 || mm == 6 || mm == 9 || mm == 11) {
            if (dd == 31) {
                controlarFecha = false;
            }
        }else{
            if (mm == 2) {
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
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormularioTrabajo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Controlador de errores</h1>");
            controlarErrores(request);
            if(controlarNombre&&controlarUsuario&&controlarPass&&controlarFecha){
                out.println("<p>Datos introducidos correctamente</p>");
                out.println("</br> <a href='index.html' > Enlace a index </a>");
            }else{
                if(!controlarNombre)
                out.println("<p>Nombre incorrecto</p>");
                if(!controlarUsuario)
                out.println("<p>Usuario incorrecto</p>");
                if(!controlarPass)
                out.println("<p>Password incorrecta</p>");
                if(!controlarFecha)
                out.println("<p>Fecha incorrecta</p>");
                
                
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
                                    out.println("<option>1</option>");
                                    out.println("<option>2</option>");
                                    out.println("<option>3</option>");
                                    out.println("<option>4</option>");
                                    out.println("<option>5</option>");
                                    out.println("<option>6</option>");
                                    out.println("<option>7</option>");
                                    out.println("<option>8</option>");
                                    out.println("<option>9</option>");
                                    out.println("<option>10</option>");
                                    out.println("<option>11</option>");
                                    out.println("<option>12</option>");
                                    out.println("<option>13</option>");
                                    out.println("<option>14</option>");
                                    out.println("<option>15</option>");
                                    out.println("<option>16</option>");
                                    out.println("<option>17</option>");
                                    out.println("<option>18</option>");
                                    out.println("<option>19</option>");
                                    out.println("<option>20</option>");
                                    out.println("<option>21</option>");
                                    out.println("<option>22</option>");
                                    out.println("<option>23</option>");
                                    out.println("<option>24</option>");
                                    out.println("<option>25</option>");
                                    out.println("<option>26</option>");
                                    out.println("<option>27</option>");
                                    out.println("<option>28</option>");
                                    out.println("<option>29</option>");
                                    out.println("<option>30</option>");
                                    out.println("<option>31</option>");
                                out.println("</select>");
                
                                out.println("<select name=\"Mes\">");
                                    out.println("<option selected>" +request.getParameter("Mes")+ "</option>");
                                    out.println("<option>1</option>");
                                    out.println("<option>2</option>");
                                    out.println("<option>3</option>");
                                    out.println("<option>4</option>");
                                    out.println("<option>5</option>");
                                    out.println("<option>6</option>");
                                    out.println("<option>7</option>");
                                    out.println("<option>8</option>");
                                    out.println("<option>9</option>");
                                    out.println("<option>10</option>");
                                    out.println("<option>11</option>");
                                    out.println("<option>12</option>");
                                out.println("</select>");
                                
                                out.println("<select name=\"Ano\">");
                                    out.println("<option selected>" +request.getParameter("Ano")+ "</option>");
                                    out.println("<option>1950</option>");
                                    out.println("<option>1951</option>");
                                    out.println("<option>1952</option>");
                                    out.println("<option>1953</option>");
                                    out.println("<option>1954</option>");
                                    out.println("<option>1955</option>");
                                    out.println("<option>1956</option>");
                                    out.println("<option>1956</option>");
                                    out.println("<option>1957</option>");
                                    out.println("<option>1958</option>");
                                    out.println("<option>1959</option>");
                                    out.println("<option>1960</option>");
                                    out.println("<option>1961</option>");
                                    out.println("<option>1962</option>");
                                    out.println("<option>1963</option>");
                                    out.println("<option>1964</option>");
                                    out.println("<option>1965</option>");
                                    out.println("<option>1966</option>");
                                    out.println("<option>1967</option>");
                                    out.println("<option>1968</option>");
                                    out.println("<option>1969</option>");
                                    out.println("<option>1970</option>");
                                    out.println("<option>1971</option>");
                                    out.println("<option>1972</option>");
                                    out.println("<option>1973</option>");
                                    out.println("<option>1974</option>");
                                    out.println("<option>1975</option>");
                                    out.println("<option>1976</option>");
                                    out.println("<option>1977</option>");
                                    out.println("<option>1978</option>");
                                    out.println("<option>1979</option>");
                                    out.println("<option>1980</option>");
                                    out.println("<option>1981</option>");
                                    out.println("<option>1982</option>");
                                    out.println("<option>1983</option>");
                                    out.println("<option>1984</option>");
                                    out.println("<option>1985</option>");
                                    out.println("<option>1986</option>");
                                    out.println("<option>1987</option>");
                                    out.println("<option>1988</option>");
                                    out.println("<option>1989</option>");
                                    out.println("<option>1990</option>");
                                    out.println("<option>1991</option>");
                                    out.println("<option>1992</option>");
                                    out.println("<option>1993</option>");
                                    out.println("<option>1994</option>");
                                    out.println("<option>1995</option>");
                                    out.println("<option>1996</option>");
                                    out.println("<option>1997</option>");
                                    out.println("<option>1998</option>");
                                    out.println("<option>1999</option>");
                                    out.println("<option>2000</option>");
                                    out.println("<option>2001</option>");
                                    out.println("<option>2002</option>");
                                    out.println("<option>2003</option>");
                                    out.println("<option>2004</option>");
                                    out.println("<option>2005</option>");
                                    out.println("<option>2006</option>");
                                    out.println("<option>2007</option>");
                                    out.println("<option>2008</option>");
                                    out.println("<option>2009</option>");
                                    out.println("<option>2010</option>");
                                    out.println("<option>2011</option>");
                                    out.println("<option>2012</option>");
                                    out.println("<option>2013</option>");
                                    out.println("<option>2014</option>");
                                    out.println("<option>2015</option>");
                                    out.println("<option>2016</option>");
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
                            
                            if(request.getParameter("Deporte")!=null)
                                out.println("<input type=\"checkbox\" name=\"Deporte\" value=\"Deporte\" checked> Deporte <br>");
                            else
                                out.println("<input type=\"checkbox\" name=\"Deporte\" value=\"Deporte\"> Deporte<br>");
                            
                            if(request.getParameter("Lectura")!=null)
                                out.println("<input type=\"checkbox\" name=\"Lectura\" value=\"Lectura\" checked> Lectura <br>");
                            else
                                out.println("<input type=\"checkbox\" name=\"Lectura\" value=\"Lectura\"> Lectura <br>");
                            
                            if(request.getParameter("Cine")!=null)
                                out.println("<input type=\"checkbox\" name=\"Cine\" value=\"Cine\" checked> Cine <br>");
                            else   
                                out.println("<input type=\"checkbox\" name=\"Cine\" value=\"Cine\"> Cine <br>");
                            
                        out.println("</div>");
                        out.println("</br>");

                    out.println("</fieldset>");
                        out.println("<div id=\"botones\">");
                            out.println("<input type=\"submit\" value=\"Enviar\"/>");
                            out.println("<input type=\"submit\" value=\"Borrar\" formaction=\"HTML/formularioTrabajo.html\"/> ");              
                            out.println("</div>");
                        out.println("</form>");                                
            }

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
