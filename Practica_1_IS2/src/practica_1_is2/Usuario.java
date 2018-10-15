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
    private String nombre, email;
    private ArrayList<Objeto> objetos_prestados = new ArrayList<Objeto>();

    /**
     * CONSTRUCTOR
     */
    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
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
        
        u = "\nPROPIETARIO " + idCliente + "\n";
        u += "Nombre del propietario: " + nombre + "\n";
        u += "Correo Electrónico: " + email + "\n";
        
        return u;
    }
}
