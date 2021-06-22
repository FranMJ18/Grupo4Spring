<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : eventosCRUD
    Created on : 21-abr-2021, 9:49:28
    Author     : josea
--%>

<%@page import="java.util.List" %>
<%@ page import="es.taw.grupo4.entity.Evento" %>
<%@ page import="es.taw.grupo4.entity.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de eventos</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<%
    List<Evento> eventos = (List<Evento>) request.getAttribute("eventos");
    Usuario u = (Usuario) session.getAttribute("usuario");
    boolean puede_editar = (u).getRol() != 4;
%>
<body>

<!-- Navbar -->
<div class="row py-2 text-center" style="background: #de7ebf">
    <style>
        body {
            background-image: url("img/pattern.jpg");
        }

        a {
            font-size: 1.3em;
            color: black
        }

        a:hover {
            font-weight: bold;
            color: black;
            text-decoration: none;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            z-index: 1
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown-element {
            background: #eccbe8;
            text-align: center;
            padding: .3em;
        }
    </style>

    <%-- TODO el enlace de abajo está mal --%>
    <a class="col-2  text-decoration-none"
       href="ServletInicioSesion?usuario=<%= u.getNickname()%>&contrasena=<%= u.getPassword()%>">
        <img src="img/Logo.png" style="width:2em; height:2em;">
    </a>
    <div class="col-4"></div>

    <%
        if (puede_editar) {
    %>
    <div class="col-2"></div>
    <a class="col-2  text-decoration-none" href="/createEvent">Crear evento</a>
    <% } else { %>
    <div class="col-4"></div>
    <% } %>
    <div class="col-2 dropdown">
        <img src="img/avatar.png" style="width:2em; height:2em;">
        <div class="dropdown-content">
            <a class="row dropdown-element" href="ServletCargarListaEventosUsuario">Mi perfil</a>
            <%
                if (u.getRol() != 1) {
            %><a class="row dropdown-element" href="ServletListarConversaciones">Mensajes</a><%
            }
        %>
            <a class="row dropdown-element" href="/logout">Cerrar sesion</a>
        </div>
    </div>
</div>

<!-- Contenido -->
<div class="py-3 row">
    <!-- Filtros -->
    <div class="col-2" style="background: #77e5ff;">
        <%--@elvariable id="filtro" type="es.taw.grupo4.dto.FiltroEvento"--%>
        <form:form method="post" action="/evento/events" modelAttribute="filtro">

            <div class="row m-1">
                <form:input style="width: 100%; border-radius: 25px" type="search" path="nombre"/>
            </div>
            <div class="row m-1">
                <p class="col-6">Min:</p>
                <form:input class="col-6" type="number" path="min"/>
            </div>
            <div class="row m-1">
                <p class="col-6">Max:</p>
                <form:input class="col-6" type="number" path="max"/>
            </div>
            <div class="row m-1">
                <p class="col-6">Disponible:</p>
                <form:checkbox class="col-6"  path="disponible"/>
            </div>


            <div class="row m-1">
                <label class="col-6" for="musica">Música </label>
                <form:checkbox class="col-6"  path="musica" id="musica"/>
            </div>
            <div class="row m-1">
                <label class="col-6" for="aire_libre">Aire libre</label>
                <form:checkbox class="col-6"  path="aireLibre" id="aire_libre"/>
            </div>
            <div class="row m-1">
                <label class="col-6" for="deporte">Deporte </label>
                <form:checkbox class="col-6"  path="deporte" id="deporte"/>
            </div>
            <div class="row m-1">
                <label class="col-6" for="teatro">Teatro </label>
                <form:checkbox class="col-6"  path="teatro" id="teatro"/>
            </div>
            <div class="row m-1">
                <label class="col-6" for="gaming">Gaming </label>
                <form:checkbox class="col-6"  path="gaming" id="gaming"/>
            </div>
            <div class="row m-1">
                <label class="col-6" for="lectura">Lectura</label>
                <form:checkbox class="col-6"  path="lectura" id="lectura"/>
            </div>
            <div class="row m-1">
                <label class="col-6" for="formacion">Formacion </label>
                <form:checkbox class="col-6"  path="formacion" id="formacion"/>
            </div>
            <div class="row m-1">
                <label class="col-6" for="conferencia">Conferencia </label>
                <form:checkbox class="col-6"  path="conferencia" id="conferencia"/>
            </div>
            <div class="row m-1">
                <label class="col-6" for="benefico">Benéfico</label>
                <form:checkbox class="col-6"  path="benefico" id="benefico"/>
            </div>
            <div class="row m-1">
                <label class="col-6" for="arte">Arte </label>
                <form:checkbox class="col-6"  path="arte" id="arte"/>
            </div>
            <div class="row m-1">
                <label class="col-6" for="turismo">Turismo </label>
                <form:checkbox class="col-6"  path="turismo" id="turismo"/>
            </div>
            <div class="row m-1">
                <div class="col-3">
                </div>
                <input class="col-6 " type="submit" value="Filtrar">
            </div>
        </form:form>
    </div>

    <!-- Grid -->
    <div class="col-10">
        <div class="row">
            <%
                for (Evento e : eventos) {
            %>
            <div class="col-4">
                <div class="row">
                    <a href="/showEvent/<%= e.getIdevento()%>" class="col-6"><img width="100%" src="img/evento.jpg"></a>
                    <div class="col-6">
                        <h2><%=e.getTitulo()%>
                        </h2>
                        <p><%=e.getDescripcion()%>
                        </p>
                        <p>Precio: <%=e.getCosteEntrada()%>
                        </p>

                        <%
                            if (puede_editar) {
                        %>
                        <a href="ServletCrearEditarEvento?evento=<%= e.getIdevento()%>">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                 class="bi bi-pencil" viewBox="0 0 16 16">
                                <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                            </svg>
                        </a>
                        <a href="/evento/eraseEvent/<%= e.getIdevento()%>" style="margin-left:2em;">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                 class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                <path fill-rule="evenodd"
                                      d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                            </svg>
                        </a>
                        <% }%>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
