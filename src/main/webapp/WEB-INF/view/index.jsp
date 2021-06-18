<%-- 
    Document   : index
    Created on : 12-may-2021, 13:16:49
    Author     : chinchar@hotmail.es
--%>

<%@page import="grupo4app.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Grupo4</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <link rel="stylesheet" href="estilos/estilo.css">
    </head>

    <%
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

                .fondito {
                    background: #f2f2f2;
                }
            </style>

            <a class="col-2  text-decoration-none" href="index.jsp">
                <img src="img/Logo.png" style="width:2em; height:2em;">
            </a>


            <div class="col-6">

            </div>

            <div  class="col-2 " style="font-size: .9em">
                <a href="RegistroUsuario.jsp">Registrarse </a>
            </div>            
            <div  class="col-2 " style="font-weight: bold">
                <a href="InicioSesion.jsp">Iniciar sesión </a>
            </div>            
        </div>
        <!-- End NavBar -->


        <section>
            <div id="eslogan" class="container-fluid my-4" style="background: #cccccc ">

                <h3 style="font-size: 3em;"><span>Haz lo que</span><br> <span style="color:#f48542 ">te apasiona</span></h3>
                <img class="my-4" src="img/gente-feliz.jpg" alt="imagenEslogan">
            </div>            
        </section>
        <div class="container-fluid bg-warning">
            <div class="container py-5">
                <div class="row">
                    <div class="col-md-4"> <!--comienzo-->
                        <div class="card" style="width: 18rem; border-radius: 10px">
                            <img class="card-img-top" src="img/img1.jpg" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title text-center">Organiza eventos de ensueño</h5>
                                <p class="card-text">Crea eventos de forma sencilla y rápida, a la vez que se dan a conocer entre la gente.
                                    Crea eventos multitudinarios que la no pueda olvidar</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4"> <!--comienzo-->
                        <div class="card" style="width: 18rem; border-radius: 10px">
                            <img class="card-img-top" src="img/img2.jpg" alt="Card image cap">
                            <div class="card text-center">
                                <div class="card-body">
                                    <h5 class="card-title">Un evento para todos</h5><br>
                                    <p class="card-text">Podrás encontrar un evento que se adecue a tus exigencias y gustos. Desde eventos literarios a torneos gaming, siempre encontraras tu lugar                                                   </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4"> <!--comienzo-->
                        <div class="card" style="width: 18rem; border-radius: 10px">
                            <img class="card-img-top" src="img/img3.jpg" alt="Card image cap">
                            <div class="card text-center">
                                <div class="card-body">
                                    <h5 class="card-title">Un evento inolvidable</h5><br><br>
                                    <p class="card-text">Es tu momento de vivir experiencias nuevas y únicas las cuales formarán parte de ti toda tu vida</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <footer class="py-3">
            Trabajo TAW is a property of Grupo4 Co.,Ltd. ©2021 All Rights Reserved.
        </footer>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    </body>
</html>
