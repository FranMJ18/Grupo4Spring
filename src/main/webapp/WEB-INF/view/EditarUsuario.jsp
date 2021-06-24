<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo4.entity.Usuario" %>
<%@ page import="es.taw.grupo4.entity.UsuarioEvento" %>
<%@ page import="es.taw.grupo4.dto.UsuarioDto" %><%--
    Document   : EditarUsuario
    Created on : 11-may-2021, 10:26:18
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
        function mostrar_extra()
        {
            const visible = true;
            document.getElementById("extra").style.display = visible ? "block" : "none";
            document.getElementById("nombre").required = visible;
            document.getElementById("apellidos").required = visible;
            document.getElementById("sexo").required = visible;
            document.getElementById("edad").required = visible;
            document.getElementById("domicilio").required = visible;
            document.getElementById("ciudad").required = visible;
        }
    </script>

    <body>
        <%
            UsuarioDto usuario = (UsuarioDto) request.getAttribute("usuario");
        %>

        <div class="contact_form">
            <div class="formulario">      
                <h1>Edición</h1>
                <%--@elvariable id="usuario" type="es.taw.grupo4.dto.UsuarioDto"--%>
                <form:form method="post" action="/administrador/guardar" modelAttribute="usuario">
                    
                    <form:hidden name="rol" path="rol"/>
                    <form:hidden path="id"/>
                    <p>
                        <label for="usuario" >Usuario</label>
                        <form:input name="usuario" id="usuario" path="usuario"/>
                    </p>

                    <p>
                        <label for="contraseña" >Contraseña</label>
                        <form:password path="password" name="password" id="contraseña" value="<%=usuario.getPassword()%>"/>
                    </p>

                    <div id="extra" style="display:none;" oncha>
                        <p>
                            <label for="nombre" >Nombre</label>
                            <form:input name="nombre" id="nombre" path="nombre" placeholder="Escribe tu nombre"/>
                        </p>

                        <p>
                            <label for="apellidos" >Apellidos</label>
                            <form:input name="apellidos" id="apellidos" path="apellidos" placeholder="Escribe tus apellidos"/>
                        </p>

                        <p>
                            <label for="edad" >Edad</label>
                            <form:input type="number" name="edad" id="edad" path="edad" placeholder="Escribe tu edad"/>
                        </p>

                        <p>
                            <label for="sexo" >Sexo</label>
                            <form:select id="sexo" name="sexo" path="sexo" items="${sexo}"/>
                        </p>

                        <p>
                            <label for="domicilio" >Domicilio</label>
                            <form:input name="domicilio" id="domicilio" path="domicilio" placeholder="Escribe tu domicilio"/>
                        </p>

                        <p>
                            <label for="ciudad" >Ciudad</label>
                            <form:input name="ciudad" id="ciudad" path="ciudad" placeholder="Escribe tu ciudad"/>
                        </p>
                    </div>

                    <button type="submit" name="enviar_formulario" id="enviar"><p>Enviar</p></button>
                </form:form>
            </div>  
        </div>

        <%
            if (usuario.getRol() == 4) {
        %>
        <script> mostrar_extra();</script>    
        <%
            }
        %>
    </body>

</html>
