<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : CrearChatManual
    Created on : 15-may-2021, 1:52:20
    Author     : franc
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="/estilos/estiloFormulario.css" rel="stylesheet">
    </head>
    <body>
       <%
            String error = (String) request.getAttribute("error");
        %>
        <div class="contact_form">
            <div class="formulario">      
                <h1>Crea una conversación</h1>
                <form:form method="post" action="/chat/guardar" modelAttribute="chat">
                    <label for="teleoperador" >Teleoperador</label>
                    <form:select id="teleoperador" path="usuario1">
                       <form:options items="${teleoperadores}" itemLabel="usuario"/>
                    </form:select>
                    <label for="usuario" >Usuario a atender</label>
                    <form:select id="usuario" path="usuario2">
                        <form:options items="${usuarios}" itemLabel="usuario"/>
                    </form:select>
                    <% 
                        if(error != null) {
                    %>
                        <p style="color: red; text-align: center;"><%=error%></p>
                    <%
                        }
                    %>
                    <button type="submit" name="enviar_formulario" id="enviar"><p>Crear</p></button>
                </form:form>
            </div>  
        </div>
    </body>
</html>
