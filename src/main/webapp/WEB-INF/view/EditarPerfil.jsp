<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo4.dto.UsuarioDto" %><%--
    Document   : EditarPerfil
    Created on : 13-may-2021, 18:56:39
    Author     : nieto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Registro</title>

        <link href="/estilos/estiloFormulario.css" rel="stylesheet">
    </head>

    <script>
        function mostrar_extra_usuario_evento(){
            document.getElementById("extra").style.display = "block";
            document.getElementById("nombre").required = true;
            document.getElementById("apellidos").required = true;
            document.getElementById("sexo").required = true;
            document.getElementById("edad").required = true;
            document.getElementById("domicilio").required = true;
            document.getElementById("ciudad").required = true;
        }
    </script>

    <%
        UsuarioDto usuario = (UsuarioDto) request.getAttribute("usuario");
    %>
    <body>

        <div class="contact_form">
            <div class="formulario">      
                <h1>Edición</h1>
                <form:form method="POST" action="/guardarPerfilUsuario" modelAttribute="usuario">
                    <form:hidden path="id"/>
                    <form:hidden path="rol"/>
                    
                    <p>
                        <label for="usuario" >Usuario</label>
                        <form:input id="usuario" path="usuario"/>
                    </p>

                    <p>
                        <label for="contraseña" >Contraseña</label>
                        <form:password id="contraseña" path="password" value="<%=usuario.getPassword()%>"/>
                    </p>

                    <div id="extra" style="display:none;">
                        <p>
                            <label for="nombre" >Nombre</label>
                            <form:input id="nombre" path="nombre" placeholder="Escribe tu nombre"/>
                        </p>

                        <p>
                            <label for="apellidos" >Apellidos</label>
                            <form:input id="apellidos" path="apellidos" placeholder="Escribe tus apellidos"/>
                        </p>

                        <p>
                            <label for="edad" >Edad</label>
                            <form:input id="edad" path="edad" placeholder="Escribe tu edad"/>
                        </p>

                        <p>
                            <label for="sexo" >Sexo</label>
                            <form:select id="sexo" path="sexo" items="${sexo}"/>
                        </p>

                        <p>
                            <label for="domicilio" >Domicilio</label>
                            <form:input id="domicilio" path="domicilio" placeholder="Escribe tu domicilio"/>
                        </p>

                        <p>
                            <label for="ciudad" >Ciudad</label>
                            <form:input id="ciudad" path="ciudad" placeholder="Escribe tu ciudad"/>
                        </p>
                    </div>

                    <button type="submit" name="enviar_formulario" id="enviar"><p>Enviar</p></button>
                </form:form>
            </div>  
        </div>

        <%
            if (usuario.getRol() == 4) {
        %>
        <script> mostrar_extra_usuario_evento();</script>    
        <%
            }
        %>
    </body>

</html>
