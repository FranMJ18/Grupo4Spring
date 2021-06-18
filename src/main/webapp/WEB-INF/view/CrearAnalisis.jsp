<%-- 
    Document   : CrearAnalisis
    Created on : 21-abr-2021, 9:50:10
    Author     : carlo
--%>

<%@page import="java.util.List"%>
<%@page import="grupo4app.entity.Filtro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CrearAnalisis</title>
        <link href="estilos/estiloFormulario.css" rel="stylesheet">
    </head>
    <%
        Filtro filtro = (Filtro)request.getAttribute("filtroEditar");
        String ciudad = "";
        String sexo = "";
        String anyo = "";
        String costeEntrada = "";
        String categorias = "";
        String usuario = "";
        String edadInferior = "";
        String edadSuperior = "";
        String id = "";
        String nombre = "";
        
        //Comprobamos si es nueva o hay que editarla.
        if (filtro != null) {
            
            try{
                 id =  String.valueOf(filtro.getFiltroPK().getIdfiltro());
            }catch(Exception e){
                
            }
            
            ciudad = filtro.getCiudad();
            sexo = filtro.getSexo();
            Integer anyoPro = filtro.getAnyo();
            if(anyoPro != 0){
                anyo = anyoPro.toString();
            }
            Integer costeEntradaPro = filtro.getCosteEntrada();
            if(costeEntradaPro != 0){
                costeEntrada = costeEntradaPro.toString();
            }
            
            categorias = filtro.getCategoria();
                      
            usuario = "0";
           
            try{
                usuario = filtro.getUsuario1().getIdusuario().toString();   
                
            }catch(Exception e){
                        
            } 
          
            try{
                nombre = filtro.getNombre();
            }catch(Exception e){
                
            }
            
            Integer edadInferiorPro = filtro.getEdadLimInf();
            if(edadInferiorPro != 0){
                edadInferior = edadInferiorPro.toString();
            }
            Integer edadSuperiorPro = filtro.getEdadLimSup();
            if(edadSuperiorPro != 0){
                edadSuperior = edadSuperiorPro.toString();
            }
        }
    %> 
    <body>
        <div class="contact_form">
            <div class="formulario"> 
        <h1>Creacion de analisis</h1>
        <form method="post" action="ServletFiltroGuardar">
            <input type="hidden" name="id" value=<%=id%>>
            <label for="opCiudad">Ciudad: </label>
            <input type="text" id="opCiudad" name="ciudad" maxlength="50" value="<%=ciudad%>">
            <br/>
            <label for="idNombre">Nombre: </label>
            <input type="text" name="nombre" id="idNombre" maxlength="200" value="<%=nombre%>">           
            <br/>
            <label>Sexo: </label><br/>
            
            <%
                if(sexo.equals("hombre")){
                %>
                   
                <div style="display: grid; grid-template-columns:1fr 1fr 1fr; width:460px">
                        <div>
                            <label style="width:auto; height:auto" for="hombre" >Hombre</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="hombre" value="hombre" checked>
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="mujer" >Mujer</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="mujer" value="Mujer">
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="otro" >Otro</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="otro" value="Otro">
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="ninguno" >Ninguno</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="ninguno" value="">
                        </div>
                    </div>
                
                <!--<input type="radio" id="opMasculino" name="sexo" value="hombre" checked>
            <label for="opMasculino"> Hombre </label>
            <br/>
            <label for="opMujer"> Mujer </label>
            <input type="radio" id="opMujer" name="sexo" value="mujer">
            <br/>
            <label for="opOtro"> Otro </label>
            <input type="radio" id="opOtro" name="sexo" value="otro">
            <br/>
            <input type="radio" id="opNinguno" name="sexo" value="">
            <label for="opNinguno"> Ninguno </label> --> 
            
            <%
                }else if(sexo.equals("Mujer")){
                %>
                
                <div style="display: grid; grid-template-columns:1fr 1fr 1fr; width:460px">
                        <div>
                            <label style="width:auto; height:auto" for="hombre" >Hombre</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="hombre" value="hombre">
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="mujer" >Mujer</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="mujer" value="Mujer" checked>
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="otro" >Otro</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="otro" value="Otro">
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="ninguno" >Ninguno</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="ninguno" value="">
                        </div>
                    </div>
                
               <!-- <input type="radio" id="opMasculino" name="sexo" value="hombre" >
            <label for="opMasculino"> Hombre </label>
            <br/>
            <label for="opMujer"> Mujer </label>
            <input type="radio" id="opMujer" name="sexo" value="mujer" checked>
            <br/>
            <label for="opOtro"> Otro </label>
            <input type="radio" id="opOtro" name="sexo" value="otro">
            <br/>
            <input type="radio" id="opNinguno" name="sexo" value="">
            <label for="opNinguno"> Ninguno </label> -->
            
            <%
                }else if(sexo.equals("Otro")){
                  %>
                  
                  <div style="display: grid; grid-template-columns:1fr 1fr 1fr; width:460px">
                        <div>
                            <label style="width:auto; height:auto" for="hombre" >Hombre</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="hombre" value="hombre">
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="mujer" >Mujer</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="mujer" value="Mujer">
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="otro" >Otro</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="otro" value="Otro" checked>
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="ninguno" >Ninguno</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="ninguno" value="">
                        </div>
                    </div>
                  
                  
              <!--<input type="radio" id="opMasculino" name="sexo" value="hombre" >
            <label for="opMasculino"> Hombre </label>
            <br/>
            <label for="opMujer"> Mujer </label>
            <input type="radio" id="opMujer" name="sexo" value="mujer">
            <br/>
            <label for="opOtro"> Otro </label>
            <input type="radio" id="opOtro" name="sexo" value="otro" checked>
            <br/>
            <input type="radio" id="opNinguno" name="sexo" value="">
            <label for="opNinguno"> Ninguno </label> -->
            
            <%
                }else{
                %>
                
                <div style="display: grid; grid-template-columns:1fr 1fr 1fr; width:460px">
                        <div>
                            <label style="width:auto; height:auto" for="hombre" >Hombre</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="hombre" value="hombre">
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="mujer" >Mujer</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="mujer" value="Mujer">
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="otro" >Otro</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="otro" value="Otro">
                        </div>
                        <div>
                            <label style="width:auto; height:auto" for="ninguno" >Ninguno</label>
                            <input style="width:auto; height:auto" required type="radio" name="sexo" id="ninguno" value="" checked>
                        </div>
                    </div>
                
                                                                             
             <!--<input type="radio" id="opMasculino" name="sexo" value="hombre" >
            <label for="opMasculino"> Hombre </label>
            <br/>
            <label for="opMujer"> Mujer </label>
            <input type="radio" id="opMujer" name="sexo" value="mujer" >
            <br/>
            <label for="opOtro"> Otro </label>
            <input type="radio" id="opOtro" name="sexo" value="otro">
            <br/>
            <input type="radio" id="opNinguno" name="sexo" value="" checked>
            <label for="opNinguno"> Ninguno </label> -->
            
            <%
                }
                %>
                      
            <br/>
            <label>Año: <input type="text" id="opAnyo" name="anyo" value=<%=anyo%>></label>
            <br/>
            <label>Coste Entrada: <input type="text" id="opEntrada" name="costeEntrada" value=<%=costeEntrada%>></label>
            <p>Categorias: </p>
            
            <%
                if(categorias.equals("musica")){
                %>
                <select name="categorias">
                    <option value="musica">Musica</option>
                    <option value="">-----</option>
                    <option value="aireLibre">Aire Libre</option>
                    <option value="deporte">Deporte</option>
                    <option value="teatro">Teatro</option>
                    <option value="gaming">Gaming</option> 
                    <option value="lectura">Lectura</option> 
                <option value="formacion">Formacion</option>
                <option value="conferencia">Conferencia</option>
                <option value="benefico">Benefico</option>
                <option value="arte">Arte</option>
                <option value="turismo">Turismo</option>
            
                </select>
            <%
                }else if(categorias.equals("aireLibre")){
                %>  
                <select name="categorias">
                    <option value="aireLibre">Aire Libre</option>
                    <option value="">-----</option>
                    <option value="musica">Musica</option>               
                    <option value="deporte">Deporte</option>
                    <option value="teatro">Teatro</option>
                    <option value="gaming">Gaming</option>  
                    <option value="lectura">Lectura</option> 
                <option value="formacion">Formacion</option>
                <option value="conferencia">Conferencia</option>
                <option value="benefico">Benefico</option>
                <option value="arte">Arte</option>
                <option value="turismo">Turismo</option>
            
                </select>
            <%
                }else if(categorias.equals("deporte")){
                %> 
                <select name="categorias">
                    <option value="deporte">Deporte</option>
                    <option value="aireLibre">Aire Libre</option>
                    <option value="">-----</option>
                    <option value="musica">Musica</option>                              
                    <option value="teatro">Teatro</option>
                    <option value="gaming">Gaming</option>  
                    <option value="lectura">Lectura</option> 
                <option value="formacion">Formacion</option>
                <option value="conferencia">Conferencia</option>
                <option value="benefico">Benefico</option>
                <option value="arte">Arte</option>
                <option value="turismo">Turismo</option>
            
                </select>
            <%
                }else if(categorias.equals("teatro")){
                %>
                <select name="categorias">
                    <option value="teatro">Teatro</option>
                    <option value="deporte">Deporte</option>
                    <option value="aireLibre">Aire Libre</option>
                    <option value="">-----</option>
                    <option value="musica">Musica</option>                                             
                    <option value="gaming">Gaming</option> 
                    <option value="lectura">Lectura</option> 
                <option value="formacion">Formacion</option>
                <option value="conferencia">Conferencia</option>
                <option value="benefico">Benefico</option>
                <option value="arte">Arte</option>
                <option value="turismo">Turismo</option>
            
                </select>
            <%
                }else if(categorias.equals("gaming")){
                %>
                <select name="categorias">
                    <option value="gaming">Gaming</option> 
                    <option value="teatro">Teatro</option>
                    <option value="deporte">Deporte</option>
                    <option value="aireLibre">Aire Libre</option>
                    <option value="">-----</option>
                    <option value="musica">Musica</option> 
                    <option value="lectura">Lectura</option> 
                <option value="formacion">Formacion</option>
                <option value="conferencia">Conferencia</option>
                <option value="benefico">Benefico</option>
                <option value="arte">Arte</option>
                <option value="turismo">Turismo</option>
            
                </select>
                <%
                }else if(categorias.equals("lectura")){
                %>
                <select name="categorias">
                    <option value="lectura">Lectura</option>                     
                    <option value="teatro">Teatro</option>
                    <option value="deporte">Deporte</option>
                    <option value="aireLibre">Aire Libre</option>
                    <option value="">-----</option>
                    <option value="musica">Musica</option> 
                    <option value="gaming">Gaming</option>
                <option value="formacion">Formacion</option>
                <option value="conferencia">Conferencia</option>
                <option value="benefico">Benefico</option>
                <option value="arte">Arte</option>
                <option value="turismo">Turismo</option>
            
                </select>
                
                <%
                }else if(categorias.equals("formacion")){
                %>
                <select name="categorias">                    
                    <option value="formacion">Formacion</option>                
                    <option value="teatro">Teatro</option>
                    <option value="deporte">Deporte</option>
                    <option value="aireLibre">Aire Libre</option>
                    <option value="">-----</option>
                    <option value="musica">Musica</option> 
                    <option value="gaming">Gaming</option>
                <option value="lectura">Lectura</option> 
                <option value="conferencia">Conferencia</option>
                <option value="benefico">Benefico</option>
                <option value="arte">Arte</option>
                <option value="turismo">Turismo</option>
            
                </select>
                
                 <%
                }else if(categorias.equals("conferencia")){
                %>
                <select name="categorias">  
                    <option value="conferencia">Conferencia</option>
                    <option value="formacion">Formacion</option>                
                    <option value="teatro">Teatro</option>
                    <option value="deporte">Deporte</option>
                    <option value="aireLibre">Aire Libre</option>
                    <option value="">-----</option>
                    <option value="musica">Musica</option> 
                    <option value="gaming">Gaming</option>
                <option value="lectura">Lectura</option>                 
                <option value="benefico">Benefico</option>
                <option value="arte">Arte</option>
                <option value="turismo">Turismo</option>
            
                </select>
                
                 <%
                }else if(categorias.equals("benefico")){
                %>
                <select name="categorias">  
                    <option value="benefico">Benefico</option>
                    <option value="conferencia">Conferencia</option>
                    <option value="formacion">Formacion</option>                
                    <option value="teatro">Teatro</option>
                    <option value="deporte">Deporte</option>
                    <option value="aireLibre">Aire Libre</option>
                    <option value="">-----</option>
                    <option value="musica">Musica</option> 
                    <option value="gaming">Gaming</option>
                <option value="lectura">Lectura</option>                                
                <option value="arte">Arte</option>
                <option value="turismo">Turismo</option>
            
                </select>
                
                 <%
                }else if(categorias.equals("arte")){
                %>
                <select name="categorias">  
                    <option value="arte">Arte</option>
                    <option value="benefico">Benefico</option>
                    <option value="conferencia">Conferencia</option>
                    <option value="formacion">Formacion</option>                
                    <option value="teatro">Teatro</option>
                    <option value="deporte">Deporte</option>
                    <option value="aireLibre">Aire Libre</option>
                    <option value="">-----</option>
                    <option value="musica">Musica</option> 
                    <option value="gaming">Gaming</option>
                <option value="lectura">Lectura</option>                                              
                <option value="turismo">Turismo</option>
            
                </select>
                
                  <%
                }else if(categorias.equals("turismo")){
                %>
                <select name="categorias">  
                    <option value="turismo">Turismo</option>
                    <option value="arte">Arte</option>
                    <option value="benefico">Benefico</option>
                    <option value="conferencia">Conferencia</option>
                    <option value="formacion">Formacion</option>                
                    <option value="teatro">Teatro</option>
                    <option value="deporte">Deporte</option>
                    <option value="aireLibre">Aire Libre</option>
                    <option value="">-----</option>
                    <option value="musica">Musica</option> 
                    <option value="gaming">Gaming</option>
                <option value="lectura">Lectura</option>                                              
                           
                </select>
                                           
            <%
                }else{
                %>
                <select name="categorias">
                    <option value="">-----</option>
                    <option value="gaming">Gaming</option> 
                    <option value="teatro">Teatro</option>
                    <option value="deporte">Deporte</option>
                    <option value="aireLibre">Aire Libre</option>                
                    <option value="musica">Musica</option>  
                    <option value="lectura">Lectura</option> 
                <option value="formacion">Formacion</option>
                <option value="conferencia">Conferencia</option>
                <option value="benefico">Benefico</option>
                <option value="arte">Arte</option>
                <option value="turismo">Turismo</option>
            
                </select>
            <%
                }
                %>
                
            
            <!--<select name="categorias">
                <option value="">-----</option>
                <option value="musica">Musica</option>
                <option value="aireLibre">Aire Libre</option>
                <option value="deporte">Deporte</option>
                <option value="teatro">Teatro</option>
                <option value="gaming">Gaming</option>
                <option value="lectura">Lectura</option> 
                <option value="formacion">Formacion</option>
                <option value="conferencia">Conferencia</option>
                <option value="benefico">Benefico</option>
                <option value="arte">Arte</option>
                <option value="turismo">Turismo</option>
            
            </select> -->
            <br/>
            <input hidden type="text" id="opUsuario" name="usuario" value=<%=usuario%>>
            <br/>
            <label>Edad-inf: <input type="text" id="opEdadInf" name="edadInferior" value=<%=edadInferior%>></label>
            <br/>
            <label>Edad-Sup: <input type="text" id="opEdadSup" name="edadSuperior" value=<%=edadSuperior%>></label>  
            <br/>
            <input type="reset" value="Resetear"/>
            <input type="submit" value="Guardar">
        </form>
            </div>
        </div>
    </body>
</html>
