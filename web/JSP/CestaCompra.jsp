<%-- 
    Document   : CestaCompra
    Created on : 17-oct-2016, 18:31:53
    Author     : Antonio
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="java.util.ArrayList"%>
<%@page import="es.albarregas.beans.Compra"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <Link rel="stylesheet" href="../CSS/cssFormulario.css"/>
        <title>Biblioteca Online</title>
    </head>
    <body>
        <h2>Formulario</h2>
        <form action="CestaCompra.jsp" method="post">
            <%! //metodo para comprobar que un valor sea numerido y creacion de variables
                ArrayList<Compra> compras = new ArrayList();
                String cantidad;
                String nombre;

                private boolean esNumerico(String str) {
                    try {
                        Integer.parseInt(str);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
            %>   

            <%  //hacemos la comprobacion de todos los errores cuando se pulsa añadir
                boolean cont = false;
                if (request.getParameter("Anadir") != null || request.getParameter("Finalizar") == null) {
                    if (request.getParameter("Anadir") != null) {
                        if (request.getParameter("Libros") == null) {
                            cont = true;
            %>
            <h3>Debe seleccionar un libro.</h3>
            <%
                }
                if (request.getParameter("Cantidad") == "") {
                    cont = true;
            %>
            <h3>Debe seleccionar una cantidad.</h3>
            <%
            } else if (esNumerico(request.getParameter("Cantidad"))) {
                if (Integer.parseInt(request.getParameter("Cantidad")) <= 0) {
                    cont = true;
            %>
            <h3>La cantidad no puede ser 0 o negativa.</h3>
            <%
                }
            } else {
                cont = true;
            %>
            <h3>La cantidad debe ser un numero entero.</h3>
            <%
                }

                if (cont == false) {//si no hay errores
                    boolean repetido = false;
                    HttpSession sesion = request.getSession(true);
                    Compra compra = new Compra();

                    //comprobamos si el atributo compras existe para almacenarlo en local
                    if (sesion.getAttribute("compras") != null) {
                        compras = (ArrayList) sesion.getAttribute("compras");
                    } else {
                        compras.clear();
                    }

                    //comprobamos si el libro marcado en el formulario existe ya en compras para sumar la cantidad
                    for (int i = 0; i < compras.size(); i++) {
                        if (request.getParameter("Libros").equals(compras.get(i).getNombre())) {
                            compras.get(i).setCantidad(Integer.parseInt(request.getParameter("Cantidad")) + compras.get(i).getCantidad());
                            //sesion
                            repetido = true;
                        }
                    }
                    //si no existe añadimos una compra nueva a compras
                    if (repetido == false) {
                        compra.setNombre(request.getParameter("Libros"));
                        compra.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
                        compras.add(compra);

                    }
                    //añadimos compras a la sesion y mostramos los datos
                    cantidad = request.getParameter("Cantidad");
                    nombre = request.getParameter("Libros");
                    sesion.setAttribute("compras", compras);
            %><h3>Se han anadido <%=cantidad%> unidades del libro <%=nombre%></h3><%
                    }
                }%>

            <fieldset id="otrosDatos">
                <legend>Compra</legend>
                <br/>

                <div id="libros">
                    <label>Libros:</label></br></br>
                    <select name="Libros" size="4">
                        <option>El Cascanuces</option>
                        <option>El Quijote</option>
                        <option>El Codigo Da Vinci</option>
                        <option>El Principito</option>
                        <option>La Divina Comedia</option>
                        <option>Cien Anios de Soledad</option>
                    </select>
                </div><br/>

                <div id="cantidad">
                    <label>Cantidad:</label>
                    <input type="text" name="Cantidad"/>
                </div><br/>

                <div id="botones">
                    <input type="reset" name="Limpiar" value="Limpiar"/>
                    <input type="submit" name="Anadir" value="Anadir" formaction="CestaCompra.jsp"/>
                    <input type="submit" name="Finalizar" value="Finalizar" formaction="CestaCompra.jsp"/>
                </div><br/>

            </fieldset>
            <%} else if (request.getParameter("Finalizar") != null) { //si pulsamos finalizar
                HttpSession sesion = request.getSession(true);

            %><h3>Productos comprados:</h3><%                //comprobamos si compras ya esta en la sesion para guardarla en local
                if (sesion.getAttribute("compras") != null) {
                    compras = (ArrayList) sesion.getAttribute("compras");

                    for (int i = 0; i < compras.size(); i++) { //recorremos compras para mostrarlas
                        cantidad = String.valueOf(compras.get(i).getCantidad());
                        nombre = compras.get(i).getNombre();
            %><p><%=nombre%>: <%=cantidad%> unidades<p><%
                    }
                }
                sesion.invalidate();
                %></br> <a href='../index.html' > Enlace a index </a><%
                    }
                %>    


        </form>
    </body>
</html>                 
