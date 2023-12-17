package Controladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;

/**
 *
 * @author Juan Goyes
 */
public class ControladorUsuario {

    //construcor de controlador de usuario
    public ControladorUsuario() {
    }

    /**
     * Metodo que nos permitira registrar un usuario Si la cedula ya esta een
     * uso, no permitira registrarse
     *
     * @param cedula
     * @param contrasenia
     * @param nombre
     * @param registroUsuarios
     * @return
     */
    public boolean registrarUsuario(String cedula, String contrasenia, String nombre, ArrayList<Usuario> registroUsuarios) {
        boolean comprobacionRegistro = false;

        // Si el ArrayList está vacío, agrega el primer usuario
        if (registroUsuarios.isEmpty()) {
            // se llama al metodo constructor de usuario y se le pasa como parametros las variables ingresadas
            Usuario primerUsuario = new Usuario(cedula, contrasenia, nombre);
            // se agrega el usuario a el ArrayList
            registroUsuarios.add(primerUsuario);
            System.out.println("Se agregó el primer usuario");
        } else {
            // Si el ArrayList no está vacío, verifica si la cédula ya está en uso
            for (Usuario a : registroUsuarios) {
                if (a.getCedula().equals(cedula)) {
                    comprobacionRegistro = true;
                    System.out.println("La cédula ya está en uso");
                    break;
                }
            }

            // Si la cédula no está en uso, agrega un nuevo usuario
            if (!comprobacionRegistro) {
                Usuario nuevoUsuario = new Usuario(cedula, contrasenia, nombre);
                registroUsuarios.add(nuevoUsuario);
                System.out.println("Se registró el usuario");
                mostrarRegistros(registroUsuarios);
            }
        }

        return comprobacionRegistro;
    }

    /**
     * Metodo para el inicio de sesion, este metodo nos permitira ingresar a la
     * plataforma si el usuario ya esta registrado y sus credenciales son
     * correctas
     *
     * @param cedula
     * @param contrasenia
     * @param registroUsuarios
     * @return
     */
    public boolean inicioSesion(String cedula, String contrasenia, ArrayList<Usuario> registroUsuarios) {
        boolean comprobacionSesion = false;
        if (registroUsuarios.isEmpty()) {// si el array esta vacio, no hay registros aun
            System.out.println("El array esta vacio para el inicio de sesion, no hay usuarios registrados");
            comprobacionSesion = false;

        } else {// si el ArrayList es diferente de vacio, se comparará las cedulas y contraseñas de los usuarios registrados, de acuedo a esto se permitirá el accceso o se negará
            for (Usuario inicioSesionComprobacion : registroUsuarios) {

                if (cedula.equals(inicioSesionComprobacion.getCedula()) && contrasenia.equals(inicioSesionComprobacion.getContrasenia())) {
                    comprobacionSesion = true;
                    System.out.println("El usuario entro a la plataforma");
                    break;

                } else {
                    System.out.println("El usuario no pudo entrar a la plataforma");
                }
            }
        }
        return comprobacionSesion;
    }

    /**
     * metodo para hacer pruebas
     *
     * @param registroUsuarios
     */
    public void mostrarRegistros(ArrayList<Usuario> registroUsuarios) {
        for (Usuario a : registroUsuarios) {
            System.out.println("===============USUARIOS REGISTRADOS===========");
            System.out.println("Cedula: " + a.getCedula());
            System.out.println("contrasenia: " + a.getContrasenia());
            System.out.println("NombreUsuario: " + a.getnombreUsuario());
            System.out.println("==============================================");

        }
    }

}
