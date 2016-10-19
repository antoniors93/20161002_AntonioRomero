<%-- 
    Document   : Calculadora
    Created on : 10-oct-2016, 18:13:18
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <Link rel="stylesheet" href="../CSS/cssFormulario.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="Calculadora.jsp">
            <%!
                private boolean esNumerico(String str) {//metodo para comprobar que una cadena sea un numero
                    try {
                        Integer.parseInt(str);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
            %>
            <%
                if (request.getParameter("Calcular") != null) {// si calcular esta pulsado

                    Enumeration<String> parametros = request.getHeaderNames();//recogemos y mostramos las cabeceras
                    while (parametros.hasMoreElements()) {
                        String elemento = parametros.nextElement();
                        String valor = request.getHeader(elemento);
                        if (elemento.equalsIgnoreCase("user-agent")) {
            %><p><%=elemento%> - <%=valor%></p><%
                        }
                    }
                    //mostramos la hora con un formato determinado
                    Date ahora = new Date();
                    SimpleDateFormat formatear = new SimpleDateFormat("EEEE d MMMM yyyy HH:mm:ss");
                    %><p><%=formatear.format(ahora)%></p><%
                        //si los dos operandos son numericos
                    if (esNumerico(request.getParameter("ope1")) && esNumerico(request.getParameter("ope2"))) {
                        int ope1 = Integer.parseInt(request.getParameter("ope1"));
                        int ope2 = Integer.parseInt(request.getParameter("ope2"));
                        int resultado=0;
                        //realizamos la operacion seleccionada y devolvemos el resultado
                        switch (Integer.parseInt(request.getParameter("Operacion"))) {
                            case 1:
                                resultado=ope1+ope2;
                               %><h3>Resultado: <%=resultado%></h3><%
                                break;
                            case 2:
                                resultado=ope1-ope2;
                                %><h3>Resultado: <%=resultado%></h3><%
                                break;
                            case 3:
                                resultado=ope1*ope2;
                                %><h3>Resultado: <%=resultado%></h3><%
                                break;
                            case 4:
                                if (ope2 == 0) {
                                   %><h3>No se puede dividir por 0</h3><%
                                } else {
                                    resultado=ope1/ope2;
                                    %><h3>Resultado: <%=resultado%></h3><%
                                }
                                break;
                        }

                    } else {
                       %><h3>Los operandos no tienen un valor correcto</h3><%
                    }

                }
            %>

            <fieldset id="calculadora">
                <legend>Calculadora</legend>
                </br>

                <div id="operaciones">
                    <label>Operando1:</label>
                    <input type="text" Name="ope1">

                    <label>Operando2:</label>
                    <input type="text" Name="ope2">
                </div></br>

                <div id="operacion">
                    <label>Operacion:</label>
                    <input type="radio" name="Operacion" value="1" checked>Sumar
                    <input type="radio" name="Operacion" value="2">Restar
                    <input type="radio" name="Operacion" value="3">Multiplicar
                    <input type="radio" name="Operacion" value="4">Dividir
                </div> <br/>
            </fieldset>

            <div id="botones">
                <input type="submit" name="Index" value="Index" formaction="../index.html"/>
                <input type="reset" name="Limpiar" value="Limpiar"/>
                <input type="submit" name="Calcular" value="Calcular" formaction="Calculadora.jsp"/>
            </div>
        </form>
    </body>
</html>
