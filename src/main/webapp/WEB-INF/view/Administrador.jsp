<%-- 
    Document   : Administrador
    Created on : 21-abr-2021, 13:54:05
    Author     : nieto
--%>
<%@page import="javafx.scene.control.Alert"%>
<%@page import="grupo4app.entity.Usuario"%>
<%@page import="grupo4app.entity.UsuarioEvento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">    
        <%
            HttpSession sesionUsuario = request.getSession();
            List<Usuario> listaUsuarios = (List) sesionUsuario.getAttribute("listaUsuarios");
            if (listaUsuarios == null) {
                response.sendRedirect("ServletUsuarioListar");
            }
            Usuario usuario = (Usuario) sesionUsuario.getAttribute("usuario");
        %>

    </head>

    <body>     
        <!-- Navbar -->      
        <div class="row py-2 text-center" style="background: #de7ebf">
            <style>
                body{
                    background-image: url("img/pattern.jpg");
                }
                a{
                    font-size: 1.3em;
                    color: black
                }

                span{
                    border-radius: 5px;  
                    border-style: solid; 
                    border-width: 1px; 
                    border-color: #ab4493;
                    background: white;
                    padding: .2em;
                }

                a:hover{
                    font-weight: bold;
                    color: black;
                    text-decoration: none;
                }
                button{
                    border-radius: 15px;
                    background: #f48542;
                    font-weight: bold;
                    cursor: pointer;
                }
                .dropdown{
                    position: relative;display: inline-block;
                }
                .dropdown-content{
                    display: none; position: absolute; z-index: 1
                }
                .dropdown:hover .dropdown-content{
                    display: block;
                }
                .dropdown-element
                {
                    background: #eccbe8;
                    text-align: center;
                    padding:  .3em;
                }
                .fondito {
                    background: #f2f2f2;
                }
            </style>

            <a class="col-2  text-decoration-none" href="ServletInicioSesion?usuario=<%= usuario.getNickname()%>&contrasena=<%= usuario.getPassword()%>">
                <img src="img/Logo.png" style="width:2em; height:2em;">
            </a>
                <div class="col-4"></div>
           <!-- <form class="col-4">
                <input style="width: 100%; border-radius: 25px" type="text">
            </form>-->
            <div class="col-4"></div>
            <div  class="col-2 dropdown">
                <img src="img/avatar.png" style="width:2em; height:2em;">
                <div class="dropdown-content">
                    <a class="row dropdown-element" href="ServletCargarListaEventosUsuario">Mi perfil</a>
                    <a class="row dropdown-element" href="ServletCerrarSesion">Cerrar sesion</a>
                </div>
            </div>       
        </div>

        <div class='d-flex justify-content-between'>
            <h1 class='col-5 text-center'>USUARIOS</h1>

            <div class="col-1"></div>
        </div>

        <hr style='width: 100%;'>

        <!-- Contenido -->

        <div class="py-3 row">  
            <!-- Grid -->
            <div class="col-10">
                <div class="row">
                    <%
                        for (Usuario e : listaUsuarios) {
                    %>
                    <div class="col-4">
                        <div class="row">
                            <a href="ServletCargarListaEventosUsuario?usuario=<%=e.getIdusuario()%>" class="col-6"><img width="100%" height="100%" src="img/avatar.png"></a>
                            <div class="col-6">
                                <h2><%=e.getNickname()%></h2>
                                <p><%=e.getRol() == 0 ? "Creador de evento" : (e.getRol() == 1 ? "Administrador del sistema" : (e.getRol() == 2 ? "Teleoperador" : (e.getRol() == 3 ? "Analista de eventos" : "Usuario de evento")))%> </p>
                                <a href="ServletMostrarUsuario?id=<%= e.getIdusuario()%>">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                    <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </a>                
                                <a href="ServletEliminarUsuario?usuario=<%= e.getIdusuario()%>" style="margin-left:2em;">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                    </svg>
                                </a>

                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>                
            </div>  
            <div class="col-2">
                <form method="post" action="ServletAñadirUsuario">
                    <p>
                        <label for="usuario" >Usuario</label>
                        <input required type="text" name="usuario" id="usuario" value=>
                    </p>

                    <p>
                        <label for="contraseña" >Contraseña</label>
                        <input required type="password" name="password" id="contraseña" >
                    </p>

                    <select id="seleccionador" name="rol" onchange="mostrar_extra()">
                        <option value="0" >Creador de evento</option>
                        <option value="1" >Administrador del sistema</option>
                        <option value="2" >Teleoperador</option>
                        <option value="3" >Analista de eventos</option>
                        <option value="4" >Usuario de evento</option>
                    </select>

                    <div id="extra" class="mt-2" style="display:none;">
                        <p>
                            <label for="nombre" >Nombre</label>
                            <input type="text" name="nombre" id="nombre">
                        </p>

                        <p>
                            <label for="apellidos" >Apellidos</label>
                            <input type="text" name="apellidos" id="apellidos">
                        </p>

                        <p>
                            <label for="edad" >Edad</label>
                            <input type="number" name="edad" id="edad">
                        </p>

                        <p>
                            <label for="sexo" >Sexo</label>
                            <select id="sexo" name="sexo" >
                                <option value="Hombre" >Hombre</option>
                                <option value="Mujer" >Mujer</option>
                                <option value="Otro" >Otro</option>
                            </select>
                        </p>

                        <p>
                            <label for="domicilio" >Domicilio</label>
                            <input type="text" name="domicilio" id="domicilio">
                        </p>

                        <p>
                            <label for="ciudad" >Ciudad</label>
                            <input type="text" name="ciudad" id="ciudad">
                        </p>
                    </div>

                    <input type="submit" name="enviar_formulario" id="enviar">
                </form>
                <form action="ServletListarEventos">
                    <br>
                    <br>
                    <br>
                    <div>
                        <h3>Todos los eventos</h3>
                        <div class="row justify-content-start px-1 my-3">
                            <input type = "submit" value="Mostrar todos los eventos"/>
                        </div>
                    </div>

                </form>
            </div>
        </div>
        <%
            if (request.getAttribute("error") != null) {
        %>
        <p style="color:red"><%=request.getAttribute("error")%>
            <%
                }
            %>
            <script>
                function mostrar_extra()
                {
                    let value = document.getElementById("seleccionador").value;
                    const visible = value === "4";
                    document.getElementById("extra").style.display = visible ? "block" : "none";
                    document.getElementById("nombre").required = visible;
                    document.getElementById("apellidos").required = visible;
                    document.getElementById("sexo").required = visible;
                    document.getElementById("edad").required = visible;
                    document.getElementById("domicilio").required = visible;
                    document.getElementById("ciudad").required = visible;
                }
            </script>
    </body>
</html>
