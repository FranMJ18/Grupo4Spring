<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : registsroEvento
    Created on : 21-abr-2021, 10:27:38
    Author     : josea
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Registro Evento </title>

        <link href="estilos/estiloFormulario.css" rel="stylesheet">

        <script>
            function mostrar_extra()
            {
                const visible = document.getElementById("seleccionador").checked;
                document.getElementById("extra").style.display = visible ? "block" : "none";
                document.getElementById("numFilas").required = visible;
                document.getElementById("numColumnas").required = visible;
                document.getElementById("aforo").required = !visible;
                document.getElementById("pAforo").style.display = !visible ? "block" : "none";
            }
        </script>
    </head>

    <body>
        <div class="contact_form">

            <div class="formulario">      
                <h1>Registro de Evento</h1>

                <%--@elvariable id="evento" type="es.taw.grupo4.dto.EventoDto"--%>
                <form:form method="post" action="/evento/saveEvent" modelAttribute="evento">
                    <form:hidden path="id"/>
                    
                    <p>
                        <label for="titulo">Titulo Evento</label>
                        <form:input id="titulo"  placeholder="Escribe el titulo del Evento" path="titulo"/>
                    </p>

                    <p>
                        <label for="costeEntrada">Precio evento</label>
                        <form:input type="number" path="costeEntrada" id="costeEntrada" placeholder="Escribe el precio del evento"/>
                    </p>


                    <p>
                        <label for="fechaInicio">Comienzo de la compra de entradas</label>
                        <form:input type="date" path="fechaInicio" id="fechaInicio" placeholder="Escribe el comienzo de la compra de las entradas"/>
                    </p>


                    <p>
                        <label for="fechaFin">Fin de la compra de entradas</label>
                        <input type="date" name="fechaFin" id="fechaFin" required placeholder="Escribe el fin de la compra de las entradas" />
                    </p> 

                 
                    <p>
                        <label for="descripcion" class="colocar_mensaje">Descripcion</label>                     
                        <form:textarea path="descripcion" class="texto_mensaje" id="descripcion" required="obligatorio" placeholder="Deja aquí la descripción del evento..."/>
                    </p>     

                    <p>
                        <label for="maxEntradas">Maximas entradas por persona</label>
                        <form:input type="number" path="maxEntradas" id="maxEntradas" placeholder="Escribe el numero de entradas por persona que puede compra un usuario"/>
                    </p>

                    <div style="display: grid; grid-template-columns:1fr 1fr 1fr; width:460px">
                        <div>
                            <label style="width:auto; height:auto" for="musica">Música </label>
                            <form:checkbox path="musica" style="width:auto; height:auto" id="musica" />
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="aire_libre">Aire libre</label>
                            <form:checkbox path="aireLibre" style="width:auto; height:auto" id="aire_libre" />
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="deporte">Deporte </label>
                            <form:checkbox path="deporte" style="width:auto; height:auto" id="deporte" />
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="teatro">Teatro </label>
                            <form:checkbox path="teatro" style="width:auto; height:auto" id="teatro" />
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="gaming">Gaming </label>
                            <form:checkbox path="gaming" style="width:auto; height:auto" id="gaming" />
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="lectura">Lectura</label>
                            <form:checkbox path="lectura" style="width:auto; height:auto" id="lectura" />
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="formacion">Formacion </label>
                            <form:checkbox path="formacion" style="width:auto; height:auto" id="formacion" />
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="conferencia">Conferencia </label>
                            <form:checkbox path="conferencia" style="width:auto; height:auto" id="conferencia" />
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="benefico">Benéfico</label>
                            <form:checkbox path="benefico" style="width:auto; height:auto" id="benefico" />
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="arte">Arte </label>
                            <form:checkbox path="arte" style="width:auto; height:auto" id="arte" />
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="turismo">Turismo </label>
                            <form:checkbox path="turismo" style="width:auto; height:auto" id="turismo" />
                        </div>  
                    </div>

                    <p>
                        <label for="seleccionador" class="colocar_asunto">Asientos fijos </label>
                        <form:checkbox path="asientosFijos" onchange="mostrar_extra()" style="width:auto; height:auto" id="seleccionador" />
                    </p>

                    <p id="pAforo">
                        <label for="aforo">Aforo máximo</label>
                        <form:input type="number" path="aforo" id="aforo" placeholder="Escribe el aforo del evento" />
                    </p>
                    <div id="extra" style="display:none;">
                        <p>
                            <label for="numFilas">Numero de filas de asientos</label>
                            <form:input type="number" path="filas" id="numFilas"  placeholder="Escribe el numero de filas" />
                        </p>

                        <p>
                            <label for="numColumnas">Numero de columnas de asientos</label>
                            <form:input type="number" path="columnas" id="numColumnas"  placeholder="Escribe el numero de columnas" />
                        </p>
                    </div>
                    <button type="submit" name="enviar_formulario" id="enviar"><p>Enviar</p></button>



                </form:form>
                <script>
                    mostrar_extra();
                </script>
            </div>  
        </div>
    </body>
</html>
