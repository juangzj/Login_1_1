package Servlets;

import Controladores.ControladorUsuario;
import Controladores.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan Goyes
 */
@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    //arrayList donde se guardan los usuarios
    ArrayList<Usuario> registroUsuarios = new ArrayList();

    ControladorUsuario controladorU = new ControladorUsuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        //obtenemos el contexto del servlet
        ServletContext context = getServletContext();

        //Metodo para leer los usuarios que ya estan registrados en la hoja txt
        controladorU.leerRegistroUsuarios(registroUsuarios, context);

        //Variables globales
        String alertaInicioSesion = "";
        String alertaRegistroUsuario = "";

        //extraemos los datos ingresados al formulario que esta en la pagina index.jsp
        String cedulaRegistrar = request.getParameter("cedulaRegistrar");
        String contraseniaRegistrar = request.getParameter("contraseniaRegistrar");
        String nombreUsuarioRegistrar = request.getParameter("nombreUsuarioRegistrar");

        //prubea de conexion de datos
        // System.out.println("Cedula: " + cedulaRegistrar);
        //System.out.println("Contraseña: " + contraseniaRegistrar);
        //System.out.println("Nombre de usuario: " + nombreUsuarioRegistrar);
        /**
         * if donde se verificara los datos ingresados y se determina si el
         * usuario se registrara o iniciara sesion
         */
        if (cedulaRegistrar != null && contraseniaRegistrar != null && nombreUsuarioRegistrar != null) {//si las varibales llegan diferentes de null se hace llamado al metodo correspondiente para registrar el usuario
            //System.out.println("Conexion de datos correcto");
            boolean verificacionRegistro = controladorU.registrarUsuario(cedulaRegistrar, contraseniaRegistrar, nombreUsuarioRegistrar, registroUsuarios, context);
            if(verificacionRegistro){
                alertaRegistroUsuario = "true";  // la cedula esta en uso
           
            }else{
               alertaRegistroUsuario = "false"; // el usuario se registro de forma exitosa
               
            }
             HttpSession miSesion = request.getSession();
             miSesion.setAttribute("alertaRegistroUsuario", alertaRegistroUsuario);
            response.sendRedirect("index.jsp");
        } else {// de lo contrario, se pedira las variables para el inicio de sesion
            String cedula = request.getParameter("cedula");
            String contrasenia = request.getParameter("contrasenia");
            System.out.println("cedula inicio de sesion: " + cedula);
            System.out.println("contrasenia incio de sesion: " + contrasenia);

            boolean comprobacionSesion = controladorU.inicioSesion(cedula, contrasenia, registroUsuarios);
            if (comprobacionSesion == true) {

                response.sendRedirect("Plataforma.jsp");
            } else {

                alertaInicioSesion = "false";
                HttpSession miSesion = request.getSession();
                miSesion.setAttribute("alertaInicioSesion", alertaInicioSesion);

                response.sendRedirect("index.jsp");
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
