<%-- 
    Document   : Perfil
    Created on : 13-may-2021, 18:25:43
    Author     : nieto
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="grupo4app.entity.EventoUsuario"%>
<%@page import="grupo4app.entity.UsuarioEvento"%>
<%@page import="grupo4app.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <%
            HttpSession ses = request.getSession();
            Usuario usSesion = (Usuario)ses.getAttribute("usuario");
            Usuario usuario = (Usuario) request.getAttribute("usuario");
            UsuarioEvento usuarioEvento = usuario.getUsuarioEvento();
            List<EventoUsuario> listaEventos = (List) request.getAttribute("listaEventos");
        %>

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

            <a class="col-2  text-decoration-none" href="ServletInicioSesion?usuario=<%= usSesion.getNickname()%>&contrasena=<%= usSesion.getPassword()%>">
                <img src="img/Logo.png" style="width:2em; height:2em;">
            </a>
            <div class="col-4">

            </div>
            <div class="col-4"></div>
            <div  class="col-2 dropdown">
                <img src="img/avatar.png" style="width:2em; height:2em;">
                <div class="dropdown-content">
                    <a class="row dropdown-element" href="ServletCargarListaEventosUsuario">Mi perfil</a>
                    <%
                        if (usuario.getRol() ==  0 || usuario.getRol() == 2 || usuario.getRol() ==  4) {
                    %><a class="row dropdown-element" href="ServletListarConversaciones">Mensajes</a><%
                                }
                    %>
                    <a class="row dropdown-element" href="ServletCerrarSesion">Cerrar sesion</a>
                </div>
            </div>       
        </div>

        <div method="GET" action="ServletMostrarEditarPerfil?idUsuario=<%=usuario.getIdusuario()%>" class="row">
            <div class="col-2">

            </div>

            <div class="col-8 text-center">
                <h1>Mi perfil</h1>
                <p>Nombre: <%=usuario.getNickname()%></p>
                <p>Rol: <%=usuario.getRol() == 0 ? "Creador de evento" : (usuario.getRol() == 1 ? "Administrador del sistema" : (usuario.getRol() == 2 ? "Teleoperador" : (usuario.getRol() == 3 ? "Analista de eventos" : "Usuario de evento")))%></p>
                <%
                    if (usuarioEvento != null) {
                %>
                <p>Nombre: <%=usuarioEvento.getNombre()%></p>
                <p>Apellido: <%=usuarioEvento.getApellido()%></p>
                <p>Ciudad: <%=usuarioEvento.getCiudad()%></p>
                <p>Domicilio: <%=usuarioEvento.getDomicilio()%></p>
                <p>Edad: <%=usuarioEvento.getEdad()%></p>
                <p>Sexo: <%=usuarioEvento.getSexo()%></p>
                <%
                    }
                %>
                <button onclick="window.location.href='ServletMostrarEditarPerfil?idUsuario=<%=usuario.getIdusuario()%>'">Editar</button>
            </div>


            <div class="col-2">

            </div>
        </div> 
        <%
            if(usuario.getRol() == 4){
        %>       
            
        <div class="py-3 row">  
            <!-- Grid -->
            
                <div class="col-12 text-center mt-4" style="background-color: #de7ebf;">
                    
                        <h2>Eventos adquiridos</h2>
                   
                </div>
                <div class="row">
                    <%
                        for (EventoUsuario e : listaEventos) {
                    %>
                    <div class="col-4">
                        <div class="row">
                            <a href="ServletMostrarEvento?idEvento=<%=e.getEvento().getIdevento()%>" class="col-6"><img width="100%" height="100%" src="img/Logo.png"></a>
                            <div class="col-6">
                                <h2><%=e.getEvento().getTitulo()%></h2>
                                <p><%=e.getEvento().getDescripcion()%></p>
                                <% 
                                    if(e.getEvento().getAsientosFijos()){
                                %>   
                                    <p>Fila: <%=e.getAsientos().getAsientosPK().getFila()%> Columna: <%=e.getAsientos().getAsientosPK().getColumna()%></p>
                                <%
                                    }
                                %>
                                    <a href="ServletCancelarEntrada?eventoUsuario=<%= e.getEventoUsuarioPK().getIdeventousuario()%>&id_usuario=<%=usuario.getIdusuario()%>" style="margin-left:2em;">
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
                
        <%
            }
       %>
    </body>
</html>
