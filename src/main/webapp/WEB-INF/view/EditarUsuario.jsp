<%-- 
    Document   : EditarUsuario
    Created on : 11-may-2021, 10:26:18
    Author     : nieto
--%>

<%@page import="grupo4app.entity.UsuarioEvento"%>
<%@page import="grupo4app.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Registro</title>

        <link href="estilos/estiloFormulario.css" rel="stylesheet">
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
            Usuario usuario = (Usuario) request.getAttribute("usuario");
            UsuarioEvento usuarioEvento = (UsuarioEvento) request.getAttribute("usuarioEvento");
        %>

        <div class="contact_form">
            <div class="formulario">      
                <h1>Edición</h1>
                <form method="post" action="ServletEditarUsuario?id=<%=usuario.getIdusuario()%>">  
                    
                    <input type="text" name="rol" hidden value="<%=usuario.getRol()%>">
                    <p>
                        <label for="usuario" >Usuario</label>
                        <input required type="text" name="usuario" id="usuario" value=<%=usuario.getNickname()%>>
                    </p>

                    <p>
                        <label for="contraseña" >Contraseña</label>
                        <input required type="password" name="password" id="contraseña" value=<%=usuario.getPassword()%>>
                    </p>

                   <%--
                    <select id="seleccionador" name="rol" onchange="mostrar_extra()">
                        <option value="0" <%= usuario.getRol() == 0 ? "selected" : ""%>>Creador de evento</option>
                        <option value="1" <%= usuario.getRol() == 1 ? "selected" : ""%>>Administrador del sistema</option>
                        <option value="2" <%= usuario.getRol() == 2 ? "selected" : ""%>>Teleoperador</option>
                        <option value="3" <%= usuario.getRol() == 3 ? "selected" : ""%>>Analista de eventos</option>
                        <option value="4" <%= usuario.getRol() == 4 ? "selected" : ""%>>Usuario de evento</option>
                    </select>
                   --%>
                    <div id="extra" style="display:none;" oncha>
                        <p>
                            <label for="nombre" >Nombre</label>
                            <input type="text" name="nombre" id="nombre" value="<%=usuarioEvento == null ? "" : usuarioEvento.getNombre()%>" placeholder="Escribe tu nombre">
                        </p>

                        <p>
                            <label for="apellidos" >Apellidos</label>
                            <input type="text" name="apellidos" id="apellidos" value="<%=usuarioEvento == null ? "" : usuarioEvento.getApellido()%>" placeholder="Escribe tus apellidos">
                        </p>

                        <p>
                            <label for="edad" >Edad</label>
                            <input type="number" name="edad" id="edad" value="<%=usuarioEvento == null ? "" : usuarioEvento.getEdad()%>" placeholder="Escribe tu edad">
                        </p>

                        <p>
                            <label for="sexo" >Sexo</label>
                            <select id="sexo" name="sexo">
                                <option value="Hombre" <%= usuarioEvento == null ? "" : (usuarioEvento.getSexo().equals("Hombre") ? "selected" : "")%>>Hombre</option>
                                <option value="Mujer" <%= usuarioEvento == null ? "" : (usuarioEvento.getSexo().equals("Mujer") ? "selected" : "")%>>Mujer</option>
                                <option value="Otro" <%= usuarioEvento == null ? "" : (usuarioEvento.getSexo().equals("Otro") ? "selected" : "")%>>Otro</option>
                            </select>
                        </p>

                        <p>
                            <label for="domicilio" >Domicilio</label>
                            <input type="text" name="domicilio" id="domicilio" value="<%=usuarioEvento == null ? "" : usuarioEvento.getDomicilio()%>" placeholder="Escribe tu domicilio">
                        </p>

                        <p>
                            <label for="ciudad" >Ciudad</label>
                            <input type="text" name="ciudad" id="ciudad" value="<%=usuarioEvento == null ? "" : usuarioEvento.getCiudad()%>" placeholder="Escribe tu ciudad">
                        </p>
                    </div>

                    <button type="submit" name="enviar_formulario" id="enviar"><p>Enviar</p></button>
                </form>
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
