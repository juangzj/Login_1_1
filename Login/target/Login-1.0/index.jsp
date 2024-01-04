<%-- 
    Document   : login
    Created on : 2/12/2023, 10:41:51 a. m.
    Author     : Juan Goyes
--%>

<%@include file="Templates/Header.jsp" %>




<section class="vh-100" style="background-color: #0B0A32;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-6 col-lg-5 d-none d-md-block">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img1.webp"
                                 alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
                        </div>
                        <div class="col-md-6 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">

                                <form action="SvLogin" method="POST"> 

                                    <div class="d-flex align-items-center mb-3 pb-1">
                                        <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                        <span class="h1 fw-bold mb-0">Logo</span>
                                    </div>

                                    <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Ingrese a su cuenta</h5>
                                    <!-- entrada para el numero de cedula -->
                                    <div class="form-outline mb-4">
                                        <input type="text" name="cedula" class="form-control form-control-lg"  required/>
                                        <label  class="form-label"   >Cedula</label>
                                    </div>
                                    <!-- entrada para la contrasenia-->
                                    <div class="form-outline mb-4">
                                        <input type="password" name="contrasenia" class="form-control form-control-lg" required/>
                                        <label  class="form-label" >Contraseña</label>
                                    </div>
                                    <!-- Boton para enviar los datos al servlet -->
                                    <div class="pt-1 mb-4">
                                        <button class="btn btn-dark btn-lg btn-block" type="submit">Entrar</button>
                                    </div>
                                    <!-- Boton para ejecutar el modal para el registro de un usuario-->
                                    <a class="small text-muted" href="#!"></a>
                                    <p class="mb-5 pb-lg-2" style="color: #393f81;"> ¿No tiene cuenta? <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#registroUsuario">
                                            Registrese aqui
                                        </button> </p>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Modal para el registro de usuario -->
<div class="modal fade" id="registroUsuario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Registrar Nuevo Usuario</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="SvLogin" method="POST" id="registroForm">
                <div class="modal-body">
                    <!-- Entrada para el numero de cedula -->
                    <div class="form-outline mb-4">
                        <input type="text" name="cedulaRegistrar" class="form-control form-control-lg" />
                        <label  class="form-label"   >Cedula</label>
                    </div>
                    <!-- Entrada para la contrasenia-->
                    <div class="form-outline mb-4">
                        <input type="password" name="contraseniaRegistrar" class="form-control form-control-lg" />
                        <label  class="form-label" >Contraseña</label>
                    </div>
                    <!-- Entrada para el nombre de usuario -->
                    <div class="form-outline mb-4">
                        <input type="text" name="nombreUsuarioRegistrar" class="form-control form-control-lg" />
                        <label  class="form-label" >Nombre de Usuario</label>
                    </div>
                    <!-- Botonoes para registrar usuario o cancelar la accion -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-primary" >Registrar Usuario</button>
                    </div>
            </form>

        </div>
    </div>
</div>

<%
    // Se pide el resultado del registro de usuario para segun este, lanzar la alerta
    String alertaRegistroUsuario = (String) request.getSession().getAttribute("alertaRegistroUsuario");
    //prueba
    //System.out.println("REGISTRO USUARIOS " + alertaRegistroUsuario);

    if (alertaRegistroUsuario != null && !alertaRegistroUsuario.isEmpty()) {//si la variable alertaRegistroUsuario llega diferente de null y diferente de vacia 

        if (alertaRegistroUsuario.equals("true")) { // si la variable alertaRegistroUsuario es igual a "true" lanza la alerta de error, ya que significa que la cedula ya esta en uso 
%>

<script>
    Swal.fire({
        title: "Error",
        text: "El numero de cedula ya esta en uso ",
        icon: "error"

    });

</script>


<%
    // Limpiar la alerta después de mostrarla
    request.getSession().removeAttribute("alertaRegistroUsuario");

} else if (alertaRegistroUsuario.equals("false")) {// si la variable alertaRegistroUsuario es igual a "false", se lanza la alerta de registro exitoso ya que significa que la cedula no esta en uso y el usuario se pudo registrar de forma exitosa
    //prueba
    //System.out.println("REGISTRO DE USUARIO " + alertaRegistroUsuario);
%> 
<script>
    Swal.fire({
        title: "Registro exitoso",
        text: "Usuario registrado ",
        icon: "success"

    });

</script>


<%
        // Limpiar la alerta después de mostrarla
        request.getSession().removeAttribute("alertaRegistroUsuario");
    }
} else {// de lo contrario entra a este if para lanzar la alerta de inicio de sesion
    // se pide el resultado de la variable alertaInicioSesion
    String alertaInicioSesion = (String) request.getSession().getAttribute("alertaInicioSesion");
    //prueba
    //System.out.println("LA ALERTA DE INICIO DE SESION ES: " + alertaInicioSesion);
    if (alertaInicioSesion != null && alertaInicioSesion.equals("false")) {// si la variable alertaInicioSesion es diferente de null y es igual a "false", se lanza la alerta de error para decirle al usuario que no pudo ingresar al sistema y que intente de nuevo
%>

<script>
    Swal.fire({
        title: "Error",
        text: "No pudo ingresar a la plataforma, por favor intente otra vez",
        icon: "error"

    });

</script>

<%
            // Limpiar la alerta después de mostrarla
            request.getSession().removeAttribute("alertaRegistroUsuario");
        }

    }
%>


<%@include file="Templates/Footer.jsp" %>
