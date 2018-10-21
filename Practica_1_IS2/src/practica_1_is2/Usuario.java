package practica_1_is2;

import java.util.ArrayList;


/**
 *
 * @author Álvaro Temporal Palomares
 * Ingeniería del Software II
 * 3º Ingeniería Informática - ETSE-UV
 */
public class Usuario {
    
    // ATRIBUTOS PRIVADOS DE LA CLASE
    private static int id = 1;
    private int idCliente;
    private String nombre, email, direccion, poblacion, provincia;
    private ArrayList<Objeto> objetos_prestados = new ArrayList<Objeto>();

    /**
     * CONSTRUCTOR
     */
    public Usuario(String nombre, String email, String direccion, String poblacion, String provincia) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.idCliente = this.id;
        id++;
    }
    
    /**
     * METODOS SETTERS Y GETTERS
     */

    public int getIdCliente() {
        return idCliente;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public ArrayList<Objeto> getObjetos_prestados() {
        return objetos_prestados;
    }

    public void setObjetos_prestados(Objeto o) {
        objetos_prestados.add(o);
    }

    /**
     * SOBRECARGA DEL METODO toString
     */

    @Override
    public String toString(){
        String u = "";
        
        u = "\n\rPROPIETARIO " + idCliente + "\n\r";
        u += "Nombre del propietario: " + nombre + "\n\r";
        u += "Correo Electrónico: " + email + "\n\r";
        u += "Direccion: " + direccion + "\n\r";
        u += "Poblacion: " + poblacion + "\n\r";
        u += "Provincia: " + provincia + "\n\r";
        
        return u;
    }
}
