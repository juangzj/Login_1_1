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
    public boolean registrarUsuario(String cedula, String contrasenia, String nombre, ArrayList<Usuario> registroUsuarios, ServletContext context) throws FileNotFoundException {
        boolean comprobacionRegistro = false;
        

        // Si el ArrayList está vacío, agrega el primer usuario
        if (registroUsuarios.isEmpty()) {
            // se llama al metodo constructor de usuario y se le pasa como parametros las variables ingresadas
            Usuario primerUsuario = new Usuario(cedula, contrasenia, nombre);
            // se agrega el usuario a el ArrayList
            registroUsuarios.add(primerUsuario);
            guardarUsuarioTxt(registroUsuarios, context);
           // System.out.println("Se agregó el primer usuario");
        } else {
            // Si el ArrayList no está vacío, verifica si la cédula ya está en uso
            for (Usuario a : registroUsuarios) {
                if (a.getCedula().equals(cedula)) {
                    comprobacionRegistro = true;
                    //System.out.println("La cédula ya está en uso");
                    break;
                }
            }

            // Si la cédula no está en uso, agrega un nuevo usuario
            if (!comprobacionRegistro) {
                Usuario nuevoUsuario = new Usuario(cedula, contrasenia, nombre);
                registroUsuarios.add(nuevoUsuario);
                //System.out.println("Se registró el usuario");
                guardarUsuarioTxt(registroUsuarios, context);
                //mostrarRegistros(registroUsuarios);
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
            //System.out.println("El array esta vacio para el inicio de sesion, no hay usuarios registrados");
            comprobacionSesion = false;

        } else {// si el ArrayList es diferente de vacio, se comparará las cedulas y contraseñas de los usuarios registrados, de acuedo a esto se permitirá el accceso o se negará
            for (Usuario inicioSesionComprobacion : registroUsuarios) {

                if (cedula.equals(inicioSesionComprobacion.getCedula()) && contrasenia.equals(inicioSesionComprobacion.getContrasenia())) {
                    comprobacionSesion = true;
                    //System.out.println("El usuario entro a la plataforma");
                    break;

                } 
            }
            if(!comprobacionSesion){
                //System.out.println("El usuario no pudo entrar a la plataforma");
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

    /**
     * Metodo para guardar usuarios en la hoja txt
     * @param registroUsuarios
     * @param context
     * @throws FileNotFoundException 
     */
    public void guardarUsuarioTxt(ArrayList<Usuario> registroUsuarios, ServletContext context) throws FileNotFoundException {
        //creamos la ruta de los archivos data y la hoja txt
        String path = "data/UsuariosRegistrados.txt";
        //Obtenemos la ruta con el contexti del servlet y la concatenamos con la ruta anteriora
        String Rpath = context.getRealPath(path);
        //Creamos un archivo con la ruta anterior
        File archivo = new File(Rpath);
        try (PrintWriter pluma = new PrintWriter(archivo)) {//se crea la pluma con la que se va a escribir en la hoja de texto
            if (registroUsuarios.isEmpty()) {//si el arrayList registrousuarios esta vacio se lanza un mensaje por consola 
                //System.out.println("No hay usuarios registrados en el sistemas");
            } else {// de lo contrario se escribe a los usuarios en el txt 
                for (Usuario guardarUsuario : registroUsuarios) {
                    pluma.println(guardarUsuario.getCedula() + "," + guardarUsuario.getContrasenia() + "," + guardarUsuario.getnombreUsuario());

                }
            }
        }
        //Ruta en la que se guardaran los usuarios
        //System.out.println("Ruta UsuariosRegistrados.txt: " + Rpath);
        //System.out.println("se guardo los usuarios");
    }

    /**
     * Metodo para leer el registro que queda guardado en la hoja UsuariosRegistrados.txt
     * @param registroUsuarios
     * @param context
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void leerRegistroUsuarios(ArrayList<Usuario> registroUsuarios, ServletContext context) throws FileNotFoundException, IOException {

        // Limpiar el ArrayList antes de cargar los usuarios para que no se repitan 
        registroUsuarios.clear();

        //Creamos la ruta de los archivos data y la hoja txt
        String path = "data/UsuariosRegistrados.txt";
        //Obtenemos la ruta con el contexto del servlet y la unimos con la ruta anterior
        String Rpath = context.getRealPath(path);
        //creamos un archivo con la ruta anterior
        File archivo = new File(Rpath);
        //usuamos los metodos Filereader y BufferedReader para la lectura del archivo de texto
        FileReader fr = new FileReader(archivo);
        BufferedReader lector = new BufferedReader(fr);

        String linea = lector.readLine();
        while (linea != null) {

            String[] datos = linea.split(",");
            String cedula = datos[0];
            String contrasenia = datos[1];
            String nombreUsuario = datos[2];

            //llamado al metodo contructor de usuarios para crear un nuevo usuario con sus atributos
            Usuario usuarioRegistrado = new Usuario(cedula, contrasenia, nombreUsuario);

            //añadimos al arrayList registroUsuarios
            registroUsuarios.add(usuarioRegistrado);

            linea = lector.readLine();

        }
        fr.close();
        lector.close();
    }

}
