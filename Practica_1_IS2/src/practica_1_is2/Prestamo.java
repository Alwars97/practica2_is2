package practica_1_is2;

/**
 *
 * @author Álvaro Temporal Palomares 
 * Ingeniería del Software II 
 * 3º Ingeniería Informática - ETSE-UV
 */
public class Prestamo {

    // ATRIBUTOS PRIVADOS DE LA CLASE
    private int idCliente, idObjeto, idClienteD;
    private String nomUsu, fechaIni, fechaFin;
    private double importe, startUp;
    
    /**
     * CONSTRUCTOR
     */

    public Prestamo(int idCliente, int idObjeto, int idClienteD, String nomUsu, String fechaIni, String fechaFin, double importe, double startUp) {

        this.idCliente = idCliente;
        this.idObjeto = idObjeto;
        this.idClienteD = idClienteD;
        this.nomUsu = nomUsu;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.importe = importe;
        this.startUp = startUp;
    }
    
    /**
     * METODOS SETTERS Y GETTERS
     */

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public int getIdClienteD() {
        return idClienteD;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
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

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getStartUp() {
        return startUp;
    }

    public void setStartUp(double startUp) {
        this.startUp = startUp;
    }

    /**
     * SOBRECARGA DEL METODO toString
     */
    @Override
    public String toString() {
        String p = "";

        p = "\t\tNombre del cliente: " + nomUsu + "\n\r";
        p += "\t\tFechas del prestamo: " + fechaIni + " - " + fechaFin + "\n\r";
        p += "\t\tImporte para el propietario: " + importe + " euros\n\r";
        p += "\t\tImporte para la startup: " + startUp + " euros\n\r";

        return p;
    }
}
