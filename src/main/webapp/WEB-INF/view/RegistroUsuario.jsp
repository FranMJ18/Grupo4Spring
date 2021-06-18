<%-- 
    Document   : RegistroUsuario
    Created on : 17-abr-2021, 11:52:08
    Author     : chinchar@hotmail.es
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Registro</title>

        <link href="estilos/estiloFormulario.css" rel="stylesheet">
    </head>



    <body>
        <div class="contact_form">
            <div class="formulario">      
                <h1>Registro</h1>
                <form method="post" action="ServletRegistroUsuario">  
                    <p>
                        <label for="usuario" >Usuario</label>
                        <input type="text" name="usuario" id="usuario" required placeholder="Escribe tu usuario">
                    </p>

                    <p>
                        <label for="contraseña" >Contraseña</label>
                        <input type="password" name="password" id="contraseña" required placeholder="Escribe tu contraseña">
                    </p>

                    <p>
                        <label for="nombre" >Nombre</label>
                        <input required type="text" name="Nombre" id="nombre" placeholder="Escribe tu nombre">
                    </p>

                    <p>
                        <label for="apellidos" >Apellidos</label>
                        <input required type="text" name="Apellidos" id="apellidos" placeholder="Escribe tus apellidos">
                    </p>

                    <p>
                        <label for="edad" >Edad</label>
                        <input required type="number" name="Edad" id="edad" placeholder="Escribe tu edad">
                    </p>
                    <input required type="number" name="rol"  value="4" hidden="true">
                    
                       
                    
                    <div style="display: grid; grid-template-columns:1fr 1fr 1fr; width:460px">
                        <div>
                            <label style="width:auto; height:auto" for="hombre" >Hombre</label>
                            <input style="width:auto; height:auto" required type="radio" name="Sexo" id="hombre" value="Hombre">
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="mujer" >Mujer</label>
                            <input style="width:auto; height:auto" required type="radio" name="Sexo" id="mujer" value="Mujer">
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="otro" >Otro</label>
                            <input style="width:auto; height:auto" required type="radio" name="Sexo" id="otro" value="Otro">
                        </div>

                    </div>

                    <p>
                        <label for="domicilio" >Domicilio</label>
                        <input required type="text" name="Domicilio" id="domicilio" placeholder="Escribe tu domicilio">
                    </p>

                    <p>
                        <label for="ciudad" >Ciudad</label>
                        <input required type="text" name="Ciudad" id="ciudad" placeholder="Escribe tu ciudad">
                    </p>



                    <button type="submit" name="enviar_formulario" id="enviar"><p>Enviar</p></button>
                </form>
            </div>  
        </div>
    </body>

</html>
