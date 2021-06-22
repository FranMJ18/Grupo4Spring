<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : InicioSesion
    Created on : 17-abr-2021, 11:40:09
    Author     : chinchar@hotmail.es
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Inicio de sesion</title>
        
        <link href="estilos/estiloFormulario.css" rel="stylesheet">
         
    </head>
    <body>        
        <div class="contact_form">

            <div class="formulario">      
              <h1>Inicio de sesión</h1>               

                <%--@elvariable id="usuario" type="es.taw.grupo4.dto.UsuarioDto"--%>
                <form:form method="post" action="/iniciar" modelAttribute="usuario">
                    <form:hidden path="id"/>
                    <p>
                        <label for="usuario">Usuario</label>
                        <form:input path="usuario" id="usuario"/>
                    </p>

                    <p>
                        <label for="contraseña">Contraseña</label>
                        <form:password path="contraseña" id="contraseña"/>
                    </p><%
                    String error = (String)request.getAttribute("error");
                    if(error != null){
                    %>
                    <p style="color: red; text-align: center;"><%=error%></p>
                    <%
                        }
                    %>
                    <button type="submit" name="enviar_formulario" id="enviar"><p>Enviar</p></button>
                </form:form>
                <a href="/register">¿Todavía no tienes una cuenta?</a>
            </div>  
        </div>               
    </body>
</html>
