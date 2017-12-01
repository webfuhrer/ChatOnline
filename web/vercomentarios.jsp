<%-- 
    Document   : vercomentarios
    Created on : 01-dic-2017, 11:41:05
    Author     : luis
--%>

<%@page import="paquetechat.CrearHTML"%>
<%@page import="paquetechat.Comentario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Comentario> lista=(List<Comentario>)request.getAttribute("lista_comentarios");
   
    String html_comentarios=CrearHTML.pintarComentarios(lista);
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenido al foro</h1>
        <%=html_comentarios%>
        <form action="ServletChat" method="POST">
           
            <textarea name="comentario" cols="80" rows="5"></textarea>
            <input type="hidden" value="insertar_comentario" name="accion">
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
