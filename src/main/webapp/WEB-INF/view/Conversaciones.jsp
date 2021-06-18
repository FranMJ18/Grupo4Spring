<%-- 
    Document   : Conversaciones
    Created on : 22-abr-2021, 20:51:37
    Author     : franc
--%>

<%@page import="grupo4app.entity.Usuario"%>
<%@page import="grupo4app.entity.Chat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conversaciones</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <style> 
            body{
                background-image: url("img/pattern.jpg");
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
            
            .columna{
                cursor:pointer; 
                background-color: #f5f5f5;
            }
            
            /*.columna:hover {
               background: #f2f3ff; 
            }*/
            
            .columna .tooltiptext {
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
            .columna:hover .tooltiptext {
                visibility: visible;
            }
            
            .filaprim {
                background-color: #de7ebf;
            }
            
        </style>
    </head>
    <%
        List<Chat> conversaciones = (List<Chat>)request.getAttribute("chats");
        String error = (String)request.getAttribute("error");
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
            <%
                if(usuarioIniciado.getRol() == 2){
            %>
                <div class="col-4 d-flex justify-content-center ">
                    <a class="text-decoration-none" href="ServletCrearChatManual">Crear una nueva conversación</a>
                </div>
            <%
                } else {
            %>
                <div class="col-4">
                </div>
            <%
                }
            %>
            <div  class="col-2 dropdown">
                <img src="img/avatar.png" style="width:2em; height:2em;">
                <div class="dropdown-content">
                    <a class="row dropdown-element text-decoration-none" href="ServletCargarListaEventosUsuario">Mi perfil</a>
                    <a class="row dropdown-element text-decoration-none" href="ServletListarConversaciones">Mensajes</a>
                    <a class="row dropdown-element text-decoration-none" href="ServletCerrarSesion">Cerrar sesion</a>
                </div>
            </div>       
        </div>
        
        <!-- TÍTULO Y BUSCADOR -->
        
        <div class="p-sm-3 text-sm-left">
            <h1 class="py-2">Conversaciones</h1>
            <form method="POST" action="ServletListarConversaciones">
                <input type="search" name="usuarioBuscar" placeholder="Buscar por usuario"/>
                <input type="submit" value="Buscar"/>
            </form>
            <% if(request.getAttribute("buscado") != null) {
            %>
                <br/>
                <button onclick="location.href='ServletListarConversaciones';">Mostrar todas</button>
            <% }
            %>
        </div>
        <hr style="width: 100%">
        
        <!-- CONTENIDO -->
        
        <% if(usuarioIniciado.getRol() == 0 || usuarioIniciado.getRol() == 4) {
        %>   
            <form class="px-sm-3" method="POST" action="ServletCrearChat">
                <input type="submit" value="Inicia una conversación con un nuevo teleoperador">
            </form>
        <% }
        
        if (error != null) {
        %>
            <p class="px-sm-3" style="color: red;"><%=error%></p>
        <%
        }
        %>
    <!-- TELEOPERADOR -->
    <%
    if(usuarioIniciado.getRol() == 2){
        // MIS CONVERSACIONES
    %>
        <h2 class="px-sm-3 ">Mis conversaciones</h2>
    <%
        if(conversaciones != null && conversaciones.size() > 0) {  
        %>
             <div class="mx-sm-3 p-sm-3">
                <div class="row justify-content-start">
                    <div class="filaprim col-4 px-sm-3 py-sm-4 my-1"><b>Teleoperador</b></div>
                    <div class="filaprim col-4 px-sm-3 py-sm-4 my-1"><b>Usuario atendido</b></div>
                </div>
                <%  
                    for(Chat ch : conversaciones) {
                %>    
                    <div class="row justify-content-start">
                        <div class="columna col-4 p-sm-3 my-1" onclick="location.href='ServletMostrarChat?idChat=<%=ch.getIdchat()%>';" ><img src="img/avatar.png" width="50px" alt="..."><%=ch.getUsuario1().getNickname()%>
                            <span class="tooltiptext">Ver la conversación</span>
                        </div>
                        <div class="columna col-4 p-sm-3 my-1" onclick="location.href='ServletMostrarChat?idChat=<%=ch.getIdchat()%>';" ><img src="img/avatar.png" width="50px" alt="..."><%=ch.getUsuario2().getNickname()%>
                            <span class="tooltiptext">Ver la conversación</span>
                        </div>

                        <div class="col-1 p-sm-3 my-1"><a href="ServletBorrarChat?idChat=<%=ch.getIdchat()%>"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                      </svg></a>
                        </div>
                        
                    </div>
                <% }
                %>
            </div>
        <% } else {
        %>
            <p class="mx-sm-3">No hay conversaciones que mostrar</p>
        <%
           }
        
        List<Chat> otrosC = (List<Chat>)request.getAttribute("otrosChats");
        /// OTRAS CONVERSACIONES
        %>
            <h2 class="px-sm-3">Otras conversaciones</h2>
        <%
        if(otrosC != null && otrosC.size() > 0) {
    %>
            <div class="mx-sm-3 p-sm-3">
                <div class="row justify-content-start">
                    <div class="filaprim col-4 px-sm-3 py-sm-4 my-1"><b>Teleoperador</b></div>
                    <div class="filaprim col-4 px-sm-3 py-sm-4 my-1"><b>Usuario atendido</b></div>
                </div>
                <%  
                    for(Chat ch : otrosC) {
                %>    
                    <div class="row justify-content-start">
                        <div class="columna col-4 p-sm-3 my-1" onclick="location.href='ServletMostrarChat?idChat=<%=ch.getIdchat()%>';" ><img src="img/avatar.png" width="50px" alt="..."><%=ch.getUsuario1().getNickname()%>
                            <span class="tooltiptext">Ver la conversación</span>
                        </div>
                        <div class="columna col-4 p-sm-3 my-1" onclick="location.href='ServletMostrarChat?idChat=<%=ch.getIdchat()%>';" ><img src="img/avatar.png" width="50px" alt="..."><%=ch.getUsuario2().getNickname()%>
                            <span class="tooltiptext">Ver la conversación</span>
                        </div>   
                        <div class="col-1 p-sm-3 my-1"><a href="ServletBorrarChat?idChat=<%=ch.getIdchat()%>"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                      </svg></a>
                        </div>
                    </div>
                <% }
                
                %>
            </div>
        <%
            } else {
        %>
                <p class="mx-sm-3">No hay conversaciones que mostrar</p>
        <%
            }
        %>
    <% } else {
        //// USUARIOS NO TELEOPERADORES
        if(conversaciones != null && conversaciones.size() > 0) {  
        %>
             <div class="mx-sm-3 p-sm-3 text-center">
                <div class="row justify-content-start">
                    <div class="filaprim col-4 px-sm-3 py-sm-4 my-1"><b>Teleoperador</b></div>
                    <div class="filaprim col-4 px-sm-3 py-sm-4 my-1"><b>Usuario atendido</b></div>
                </div>
                <%  
                    for(Chat ch : conversaciones) {
                %>    
                    <div class="row justify-content-start">
                        <div class="columna col-4 p-sm-3 my-1" onclick="location.href='ServletMostrarChat?idChat=<%=ch.getIdchat()%>';" ><img src="img/avatar.png" width="50px" alt="..."><%=ch.getUsuario1().getNickname()%>
                            <span class="tooltiptext">Ver la conversación</span>
                        </div>
                        <div class="columna col-4 p-sm-3 my-1" onclick="location.href='ServletMostrarChat?idChat=<%=ch.getIdchat()%>';" ><img src="img/avatar.png" width="50px" alt="..."><%=ch.getUsuario2().getNickname()%>
                            <span class="tooltiptext">Ver la conversación</span>
                        </div>  
                    </div>
                <% }
                %>
            </div>
        <% } else {
        %>
            <p class="mx-sm-3">No hay conversaciones que mostrar</p>
        <% }
        }   
        %>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js" integrity="sha384-lpyLfhYuitXl2zRZ5Bn2fqnhNAKOAaM/0Kr9laMspuaMiZfGmfwRNFh8HlMy49eQ" crossorigin="anonymous"></script>
    </body>
</html>
