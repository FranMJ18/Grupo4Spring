<%-- 
    Document   : CrearChatManual
    Created on : 15-may-2021, 1:52:20
    Author     : franc
--%>

<%@page import="java.util.List"%>
<%@page import="grupo4app.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estilos/estiloFormulario.css" rel="stylesheet">
    </head>
    <body>
       <%
            List<Usuario> teleOps = (List<Usuario>) request.getAttribute("teleoperadores");
            List<Usuario> usrs = (List<Usuario>) request.getAttribute("usuarios");
            String error = (String) request.getAttribute("error");
        %>
        <div class="contact_form">
            <div class="formulario">      
                <h1>Crea una conversación</h1>
                <form method="post" action="ServletCrearChat">  
                    <label for="teleoperador" >Teleoperador</label>
                    <select id="teleoperador" name="teleoperador">
                        <% 
                            for(Usuario us : teleOps){
                        %>
                            <option value="<%=us.getIdusuario()%>" ><%=us.getNickname()%></option>
                        <%
                            }
                        %>
                       
                    </select>
                    <label for="usuario" >Usuario a atender</label>
                    <select id="usuario" name="usuario">
                        <% 
                            for(Usuario us : usrs){
                        %>
                            <option value="<%=us.getIdusuario()%>" ><%=us.getNickname()%></option>
                        <%
                            }
                        %>
                    </select>
                    <% 
                        if(error != null) {
                    %>
                        <p style="color: red; text-align: center;"><%=error%></p>
                    <%
                        }
                    %>
                    <button type="submit" name="enviar_formulario" id="enviar"><p>Crear</p></button>
                </form>
            </div>  
        </div>
    </body>
</html>
