package practica_1_is2;

import java.util.ArrayList;

/**
 *
 * @author Álvaro Temporal Palomares
 * Ingeniería del Software II
 * 3º Ingeniería Informática - ETSE-UV
 */
public class Objeto {
    
    // ATRIBUTOS PRIVADOS DE LA CLASE
    private static int id = 1;
    private int idObjeto, idCliente;
    private String descripcion, fechaIni, fechaFin;
    private double precio;
    private ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();

    
    /**
     * CONSTRUCTOR
     */
    public Objeto(int idCliente, String descripcion, String fechaIni, String fechaFin, double precio) {
        this.idCliente = idCliente;
        this.descripcion = descripcion;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.idObjeto = this.id;
        id++;
    }

    /**
     * METODOS SETTERS Y GETTERS
     */
    
    public int getIdObjeto() {
        return idObjeto;
    }

    public int getIdCliente() {
        return idCliente;
    }
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Prestamo p) {
        prestamos.add(p);
    }
    
    /**
     * SOBRECARGA DEL METODO toString
     */

    @Override
    public String toString(){
        String o = "";
        
        o = "\tCódigo del objeto: " + idObjeto + "\n\r";
        o += "\tDescripción: " + descripcion + "\n\r";
        o += "\tFecha de disponibilidad: " + fechaIni + " - " + fechaFin + "\n\r";
        o += "\tCoste del prestamo por día: " + precio + " euros\n\r";
        
        return o;
    }
}
