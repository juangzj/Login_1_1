package Controladores;

/**
 *
 * @author Juan Goyes
 */
public class Usuario {

    private String cedula;
    private String contrasenia;
    private String nombreUsuario;

    /**
     * Constructor vacio
     */
    public Usuario() {
    }

    /**
     * contructor con atributos de usuaurio
     *
     * @param cedula
     * @param contrasenia
     * @param nombreUsuario
     */
    public Usuario(String cedula, String contrasenia, String nombreUsuario) {
        this.cedula = cedula;
        this.contrasenia = contrasenia;
        this.nombreUsuario = nombreUsuario;
    }

    //================================================
    /**
     * metodos getters y setters
     */
    //================================================

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getnombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
