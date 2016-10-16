<%-- 
    Document   : SaludoJSP
    Created on : 10-oct-2016, 17:31:01
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalTime" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            String nombre = request.getParameter("Nombre") != null ? request.getParameter("Nombre") : "";
            String sexo = request.getParameter("Sexo").equalsIgnoreCase("Hombre") ? "señor" : "señora";
            int hora = LocalTime.now().getHour();
            String saludo = "";
            if (hora >= 21 || hora < 6) {
                saludo = "Buenas noches";
            }

            if (hora >= 6 && hora < 12) {
                saludo = "Buenos dias";
            }

            if (hora >= 12 && hora < 21) {
                saludo = "Buenas tardes";
            }
        %>

        <h2><%=saludo%>, <%=sexo%> <%=nombre%>.</h2>
        </br> <a href='../index.html' > Enlace a index </a>

    </body>
</html>
