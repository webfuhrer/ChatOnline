<%-- 
    Document   : indice
    Created on : 01-dic-2017, 13:46:29
    Author     : luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .error
            {
                color: red;
            }
            
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            String error="";
            if(request.getAttribute("error")!=null)
            {
                error=(String)request.getAttribute("error");
            }
            %>
    </head>
     <body>
        LOGIN:
        <p class="error"><%=error%></p>
        <form action="ServletChat" method="POST">
            Usuario: <input type="text" name="usuario" placeholder="Introduzca su nombre de usuario"><br>
            Password:<input type="password" name="password" placeholder="Introduzca su password"><br>
            <input type="hidden" name="accion" value="login">
            <input type="submit" value="Login">
        </form>
         REGISTRO:
        <form action="ServletChat" method="POST">
            Usuario: <input type="text" name="usuario" placeholder="Introduzca su nombre de usuario"><br>
            Password:<input type="password" name="password" placeholder="Introduzca su password"><br>
            Avatar:<input type="text" name="avatar" placeholder="Introduzca su avatar"><br>
            <input type="hidden" name="accion" value="registro">
            <input type="submit" value="Registrarse">
        </form>
    </body>
</html>
