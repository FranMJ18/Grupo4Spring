<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : RegistroUsuario
    Created on : 17-abr-2021, 11:52:08
    Author     : chinchar@hotmail.es
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Registro</title>

        <link href="/estilos/estiloFormulario.css" rel="stylesheet">
    </head>


    <%
        String error = (String)request.getAttribute("error");
    %>
    <body>
        <div class="contact_form">
            <div class="formulario">      
                <h1>Registro</h1>
                <%--@elvariable id="usuario" type="es.taw.grupo4.dto.UsuarioDto"--%>
                <form:form method="post" action="/registrar" modelAttribute="usuario">
                    <form:hidden path="id"/>
                    <form:hidden path="rol"/>
                    <p>
                        <label for="usuario" >Usuario</label>
                        <form:input path="usuario" id="usuario" placeholder="Escribe tu usuario"/>
                    </p>

                    <p>
                        <label for="contraseña" >Contraseña</label>
                        <form:password path="password" id="contraseña" placeholder="Escribe tu contraseña"/>
                    </p>

                    <p>
                        <label for="nombre" >Nombre</label>
                        <form:input path="nombre" id="nombre" placeholder="Escribe tu nombre"/>
                    </p>

                    <p>
                        <label for="apellidos" >Apellidos</label>
                        <form:input path="apellidos" id="apellidos" placeholder="Escribe tus apellidos"/>
                    </p>

                    <p>
                        <label for="edad" >Edad</label>
                        <form:input path="edad" id="edad" placeholder="Escribe tu edad"/>
                    </p>

                    <form:select path="sexo" items="${sexo}" delimiter="<br>"/>

                    <p>
                        <label for="domicilio" >Domicilio</label>
                        <form:input path="domicilio" id="domicilio" placeholder="Escribe tu domicilio"/>
                    </p>

                    <p>
                        <label for="ciudad" >Ciudad</label>
                        <form:input path="ciudad" id="ciudad" placeholder="Escribe tu ciudad"/>
                    </p>

                    <%
                        if(error != null) {
                    %>
                    <p style="color: red; text-align: center;"><%=error%></p>
                    <%
                        }
                    %>

                    <button type="submit" name="enviar_formulario" id="enviar"><p>Enviar</p></button>
                </form:form>

            </div>  
        </div>
    </body>

</html>
