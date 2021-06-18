<%-- 
    Document   : FiltrosUsuariosEventos
    Created on : 08-may-2021, 0:22:09
    Author     : carlo
--%>

<%@page import="grupo4app.entity.Usuario"%>
<%@page import="grupo4app.entity.EventoUsuario"%>
<%@page import="grupo4app.entity.UsuarioEvento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muestro los usuarios filtrados</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <%
        List<EventoUsuario> listaUsuarios = (List) request.getAttribute("filtrados");
        HttpSession ses = request.getSession();
        Usuario u = (Usuario) ses.getAttribute("usuario");
    %>
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
                a:hover{
                    font-weight: bold;
                    color: black;
                    text-decoration: none;
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
                .filaprim {
                    background-color: #de7ebf;
                }
                .columna{
                    cursor:pointer; 
                    background-color: #f5f5f5;
                }

            </style>
            <a class="col-2  text-decoration-none" href="ServletInicioSesion?usuario=<%= u.getNickname()%>&contrasena=<%= u.getPassword()%>">
                <img src="img/Logo.png" style="width:2em; height:2em;">
            </a>
            <div class="col-4"></div>
            <%
                if (u.getRol() == 3) {
            %>
            <div class="col-4 d-flex justify-content-center ">
                <a class="text-decoration-none" href="CrearAnalisis.jsp">Crear analisis</a>
            </div>
            <%
            } else {
            %>
            <div class="col-4"></div>
            <%
                }
            %>                          
            <div  class="col-2 dropdown">
                <img src="img/avatar.png" style="width:2em; height:2em;">
                <div class="dropdown-content">
                    <a class="row dropdown-element text-decoration-none" href="ServletCargarListaEventosUsuario">Mi perfil</a>
                    <a class="row dropdown-element text-decoration-none" href="ServletCerrarSesion">Cerrar sesion</a>
                </div>
            </div>
        </div>


        <h1>Usuarios filtrados: </h1>

        <%
            if (!listaUsuarios.isEmpty()) {
        %>

        <div class="mx-sm-3 p-sm-3 text-center">
            <div class="row justify-content-start">
                <div class="filaprim col-4 px-sm-3 py-sm-4 my-1"><b>Nombre usuario</b></div>
                <div class="filaprim col-4 px-sm-3 py-sm-4 my-1"><b>Nombre evento</b></div>
            </div>
            <%
                for (EventoUsuario filtrado : listaUsuarios) {
            %>    
            <div class="row justify-content-start">
                <div class="columna col-4 p-sm-3 my-1" ><img src="img/avatar.png" width="50px" alt="..."><%= filtrado.getUsuarioEvento().getNombre()%>

                </div>
                <div class="columna col-4 p-sm-3 my-1" ><%= filtrado.getEvento().getTitulo()%>
                </div>  
            </div>
            <% }
            %>
        </div>   
        <%
        } else {
        %>   
        <p>Nada de nada</p>       
        <%
            }
        %> 

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
