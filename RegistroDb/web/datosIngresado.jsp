<%-- 
    Document   : datosIngresado
    Created on : 08-mar-2015, 18:54:39
    Author     : Altair0141
--%>

<%@page import="modelo.Consulta"%>
<%@page import="controlador.ConsultaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");

            ConsultaDAO cDao = new ConsultaDAO();
            Consulta con = new Consulta(nombre, apellido);
            if(cDao.ingresarEmpresa(con)){
        %>
        <h1>Ingresado</h1>
        
        <%
            }else{
             %>
             <h1>No ingresado</h1>
             <%   
            }
        %>
    </body>
</html>
