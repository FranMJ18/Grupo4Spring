<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : CrearAnalisis
    Created on : 21-abr-2021, 9:50:10
    Author     : carlo
--%>

<%@page import="java.util.List"%>
<%@ page import="es.taw.grupo4.entity.Filtro" %>
<%@ page import="es.taw.grupo4.dto.FiltroDto" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CrearAnalisis</title>
        <link href="estilos/estiloFormulario.css" rel="stylesheet">
    </head>
    <%
        //FiltroDto filtro = (FiltroDto)request.getAttribute("fdto");

    %> 
    <body>
        <div class="contact_form">
            <div class="formulario"> 
        <h1>Creacion de analisis</h1>
        <%--@elvariable id="fdto" type="es.taw.grupo4.dto.FiltroDto"--%>
        <form:form method="post" action="" modelAttribute="fdto">
            <form:hidden path="idfiltro" />
            Ciudad: <form:input path="ciudad" /><br>
            Nombre: <form:input path="nombre" /><br>
            Sexo:
            <div style="display: grid; grid-template-columns:1fr 1fr 1fr; width:460px">
            <%--@elvariable id="sexo" type="java.util.ArrayList<String>"--%>
            <form:radiobuttons path="sexo" items="${sexo}" /><br>
            </div>
            Anyo: <form:input path="anyo" /><br>
            Coste Entrada: <form:input path="coste_entrada" />
            Categorias:
            <%--@elvariable id="categorias" type="java.util.ArrayList<String>"--%>
            <form:select path="categoria">
                <form:options items="${categorias}" />
            </form:select><br>
            EdadInferior :<form:input path="edad_lim_inf" /><br>
            EdadSuperior :<form:input path="edad_lim_sup" /><br>
            <input type="reset" value="Resetear"/>
            <input type="submit" value="Guardar">
        </form:form>
            </div>
        </div>
    </body>
</html>
