<%-- 
    Document   : AnalistaEventos
    Created on : 20-abr-2021, 17:49:40
    Author     : carlo
--%>

<%@page import="grupo4app.entity.Usuario"%>
<%@page import="grupo4app.entity.Filtro"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AnalistaEventos</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <%
        List<Filtro> listaFiltros = (List) request.getAttribute("listaFiltros");
        Usuario u = (Usuario) session.getAttribute("usuario");
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
        <h1 class="text-center">Bienvenido analista de eventos</h1>
        <h4 class="text-center py-3" style="background: #de7ebf">Lista de filtros: </h4>

        <div style="display: grid; grid-template-columns: 1fr 1fr 1fr 1fr; margin: 3em">



            <%
                for (Filtro filtro : listaFiltros) {
            %>

            <div>
                <a class="row" href="ServletMostrarEventoUsuarios?id=<%=filtro.getFiltroPK().getIdfiltro()%>"><%= filtro.getNombre()%></a>

                <div class="row">
                    <a  href="ServletCrearEditarFiltro?id=<%=filtro.getFiltroPK().getIdfiltro()%>">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                        </svg>
                    </a> 

                    <a href="ServletBorrarFiltro?id=<%=filtro.getFiltroPK().getIdfiltro()%>" style="margin-left:2em;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                        </svg>
                    </a>  
                </div>

            </div>


            <%
                }
            %>   
        </div>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
