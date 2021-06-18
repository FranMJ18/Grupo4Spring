<%-- 
    Document   : MostrarEvento
    Created on : 24-abr-2021, 19:15:48
    Author     : chinchar@hotmail.es
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="grupo4app.entity.EventoUsuario"%>
<%@page import="grupo4app.entity.UsuarioEvento"%>
<%@page import="grupo4app.entity.Usuario"%>
<%@page import="grupo4app.entity.Asientos"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="grupo4app.entity.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>

    <%
        Evento e = (Evento) request.getAttribute("evento");
        List<EventoUsuario> lista_evento_usuario = (List<EventoUsuario>) request.getAttribute("lista_evento_usuario");
        Usuario u = (Usuario) session.getAttribute("usuario");
        UsuarioEvento ue = u.getUsuarioEvento();
        String patron = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(patron);

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

            <a class="col-2  text-decoration-none" href="ServletInicioSesion?usuario=<%= u.getNickname()%>&contrasena=<%= u.getPassword()%>">
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
                        if(u.getRol() != 1){
                            %><a class="row dropdown-element" href="ServletListarConversaciones">Mensajes</a><%
                        }
                    %>
                    <a class="row dropdown-element" href="ServletCerrarSesion">Cerrar sesion</a>
                </div>
            </div>       
        </div>

        <div class = "row">
            <div class="col-1"></div>
            <img src = "img/evento.jpg"  style="aspect-ratio: 16 / 9;"  class = "col-7 fondito">
            <div class = "col-3 fondito py-2">
                <p>
                    <label for ="titulo">Titulo:</label> <span id="titulo"><%= e.getTitulo()%></span>
                </p>
                <p> 
                    <label for="inicio" >Inicio: </label> <span id="inicio"><%= format.format(e.getFecha())%></span>
                    <label for="fin" class="pl-4">Fin: </label> <span id="fin"><%= format.format(e.getFechaReserva())%></span>
                </p>
                <p>
                    <label for ="precio">Precio:</label> <span id="precio"><%= e.getCosteEntrada()%></span>
                </p>

                <form action="ServletRegistrarPersonaEnEvento?evento=<%=e.getIdevento()%>" method="post">

                    <% if (ue != null) {
                            
                            int plazas = e.getAforo() - lista_evento_usuario.size();
                            int numEntradas = 0;
                            for (EventoUsuario aux : lista_evento_usuario) {
                                if (aux.getEvento().getIdevento() == e.getIdevento()) {
                                    numEntradas++;
                                }
                            }

                            if (numEntradas >= e.getMaxNumEntradas()) {
                    %>
                    <p>Has superado el cupo</p>
                    <%
                    } else if (plazas == 0) {
                    %>
                    <p>No quedan plazas</p>
                    <%
                    } else if ((new Date()).compareTo(e.getFechaReserva())>0) {
                    %>
                    <p>El evento ya se ha realizado</p>
                    <%
                    } else if ((new Date()).compareTo(e.getFecha())<0) {
                    %>
                    <p>El evento aun no se puede comprar</p>
                    <%
                    } else {
                        if (e.getAsientosFijos()) {
                    %>
                    <select name="asiento">
                        <% for (Asientos a : e.getAsientosList()) {
                                if (a.getOcupado() != 0) {
                                    continue;
                                }
                        %>
                        <option value="<%= a.getAsientosPK().getFila()%> <%= a.getAsientosPK().getColumna()%>">Fila: <%= a.getAsientosPK().getFila()%> Columna: <%= a.getAsientosPK().getColumna()%></option>
                  
                    <% }%>
                     </select>  
                        <input type="submit" value="Comprar">
                    <%} else {%>
                    <span>Plazas disponibles: <%= plazas%></span>
                    <input type="submit" value="Comprar">
                    <% }
                        }%>
                    
                    <%}%>
                </form>

            </div>
            <div class="col-1"></div>
        </div>
        <div class = "row " >
            <div class="col-1"></div>
            <p class ="col-7 fondito py-3"> <%= e.getDescripcion()%></p>
            <div class="col-3 fondito"></div>
        </div>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    </body>
</html>
