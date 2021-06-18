<%-- 
    Document   : Chat
    Created on : 24-abr-2021, 3:05:59
    Author     : franc
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="grupo4app.entity.Mensaje"%>
<%@page import="java.util.List"%>
<%@page import="grupo4app.entity.Chat"%>
<%@page import="grupo4app.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% Chat ch = (Chat)request.getAttribute("chat");%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <title>Chat</title>
        <style>
            
            body{
                background-image: url("img/pattern.jpg");
            }
            
            .btn .tooltiptext {
                visibility: hidden;
                width: 120px;
                background-color: black;
                color: #fff;
                text-align: center;
                margin-left: 5px;
                padding: 5px 0;
                border-radius: 6px;

                /* Position the tooltip text - see examples below! */
                position: absolute;
                z-index: 1;
            }

            /* Show the tooltip text when you mouse over the tooltip container */
            .btn:hover .tooltiptext {
                visibility: visible;
            }
            
            a{
                font-size: 1.3em;
                color: black;
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
            textarea{
                resize: none;
            }
            .fechahora{
                font-size: 70%;
            }
            .msg{
                overflow-wrap: break-word;
            }
        </style>
    </head>
    <%
        List<Mensaje> mensajes = (List<Mensaje>)request.getAttribute("mensajes");
        Usuario teleOp = ch.getUsuario1();
        Usuario usr = ch.getUsuario2();
        
        HttpSession ses = request.getSession();
        Usuario usuarioIniciado = (Usuario)ses.getAttribute("usuario");
    %>
    <body>
        <!-- BARRA -->
        
        <div class="row py-2 text-center" style="background: #de7ebf">
            
            <a class="col-2  text-decoration-none" href="ServletInicioSesion?usuario=<%= usuarioIniciado.getNickname()%>&contrasena=<%= usuarioIniciado.getPassword()%>">
                <img src="img/Logo.png" style="width:2em; height:2em;">            
            </a>
                
            <div class="col-4">
            </div>
            <div class="col-4 d-flex justify-content-around ">
            </div>
            <div  class="col-2 dropdown">
                <img src="img/avatar.png" style="width:2em; height:2em;">
                <div class="dropdown-content">
                    <a class="row dropdown-element text-decoration-none" href="ServletCargarListaEventosUsuario">Mi perfil</a>
                    <a class="row dropdown-element text-decoration-none" href="ServletListarConversaciones">Mensajes</a>
                    <a class="row dropdown-element text-decoration-none" href="ServletCerrarSesion">Cerrar sesion</a>
                </div>
            </div>       
        </div>
        
        <!-- CONTENIDO -->
        <div class="px-sm-3 pt-3 pb-sm-1 text-sm-left">
            <h1>Chat</h1>
        </div>
        <hr style="width: 100%;"/>
        <div class="container-fluid mx-sm-3 p-sm-3">
            <div class="row">
                <div class="col-md-8 col-sm-12 overflow-auto border border-1 border-primary rounded" style="background-color: white;">
                    <div class="container-fluid px-sm-3 py-sm-4">
                        <%if(mensajes.size() == 0){
                        %>
                        <div class="row text-center">
                            <p style="font-style: italic; color:#bebebe;"> No hay mensajes todavía</p>
                        </div>
                        <% } %>
                        <%
                            for(Mensaje msg : mensajes){
                         %>
                                <div class="row">
                             <%if (msg.getEmisor().getIdusuario() == usuarioIniciado.getIdusuario()){
                             %>    
                                <div class="col-10 px-sm-4 py-2 text-wrap text-end my-1 msg" style="background-color: #f3edf7;">
                                     <%=msg.getTexto().replaceAll("(\r\n|\n)", "<br />") %> <br/>
                                    <span class="fechahora">(<%= new SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm:ss").format(msg.getFechaHora()) %>) </span>
                                </div>
                                <div class="col-2 px-sm-2 py-2 text-end my-1" style="background-color: #f3edf7;">
                                    <b><%=msg.getEmisor().getNickname()%></b><img class="px-1" src="img/avatar.png" width="50px" alt="...">
                                </div>
                            <% } else {
                            %>
                                <div class="col-2 px-sm-2 py-2 my-1" style="background-color: #fafafa;">
                                    <img class="px-1" src="img/avatar.png" width="50px" alt="..."><b><%=msg.getEmisor().getNickname()%></b>
                                </div>
                                <div class="col-10 px-sm-4 py-2 text-wrap text-start my-1 msg" style="background-color: #fafafa;">
                                    <%=msg.getTexto().replaceAll("(\r\n|\n)", "<br />") %> <br/>
                                    <span class="fechahora">(<%= new SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm:ss").format(msg.getFechaHora()) %>) </span>
                                </div>
                            <% }
                            %>
                                </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                <div class="col-md-4 col-sm-12 px-sm-3 py-sm-4 text-center">
                    <div class="d-block p-2">
                        <b>Teleoperador</b><br/>
                        <div class="d-flex flex-row justify-content-center align-items-center">
                            <div class="py-2 px-1"><img src="img/avatar.png" width="100px" alt="..."></div>
                            <div class="py-2 px-1" style="width: 8em;"><%=teleOp.getNickname()%></div>
                        </div>
                    </div>
                    <div class="d-block p-2">
                        <b>Usuario</b></br>
                        <div class="d-flex flex-row justify-content-center align-items-center">
                            <div class="py-2 px-1"><img src="img/avatar.png" width="100px" alt="..."></div>
                            <div class="py-2 px-1" style="width: 8em;"><%=usr.getNickname()%></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      
        <% 
            if (usuarioIniciado.getIdusuario() == usr.getIdusuario() || usuarioIniciado.getIdusuario() == teleOp.getIdusuario()) {
        %>
        <div class="mx-sm-3 my-sm-3">
            <form name="enviar" method="POST" action="ServletEnviarMensaje">
                <input type="hidden" name="idChat" value="<%=ch.getIdchat()%>">
                <div class="d-flex align-items-md-center">
                    <textarea name="mensaje" id="mensaje" rows="3" cols="80" maxlength="200" placeholder="Envía un mensaje"></textarea>
                    <input class="mx-2" type="submit" name="enviar"/>
                </div>
            </form>
        </div>
        <%
            }
        %>
        <div class="mx-sm-3 my-sm-3">
            <button class="btn" style="background-color: rgb(239, 239, 239); border: 1px solid black;" onclick="location.href='ServletMostrarChat?idChat=<%=ch.getIdchat()%>';">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"></path>
                <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"></path>
                </svg>
                <span class="tooltiptext">Recargar chat</span>
            </button>
        </div>
    </body>
    <script>
        document.getElementById("mensaje").addEventListener('keydown', function (e) {
            if (e.key === 'Enter' && !e.shiftKey) {
                e.preventDefault();
                document.enviar.submit();
            }
        });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js" integrity="sha384-lpyLfhYuitXl2zRZ5Bn2fqnhNAKOAaM/0Kr9laMspuaMiZfGmfwRNFh8HlMy49eQ" crossorigin="anonymous"></script>
</html>
