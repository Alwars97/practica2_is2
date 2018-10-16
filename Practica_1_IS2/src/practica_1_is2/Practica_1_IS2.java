package practica_1_is2;

import java.io.FileWriter;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Álvaro Temporal Palomares 
 * Ingeniería del Software II 
 * 3º Ingeniería Informática - ETSE-UV
 */
public class Practica_1_IS2 {

    /**
     * Funcion que verifica que un correo electronico este bien formado mediante
     * una expresion regular
     *
     * @param e Correo Electronico
     * @return True si esta bien formado, False si esta mal formado
     */
    private static boolean comprobarEmail(String e) {

        String exp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(exp);
        Matcher matcher = pattern.matcher(e);

        return matcher.matches();
    }

    /**
     * Funcion que verifica que una fecha este bien formada
     *
     * @param fecha Fecha a verificar
     * @return True si esta bien formada, False si esta mal formada
     */
    private static boolean comprobarFecha(String fecha) {

        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;

    }

    /**
     * Funcion que compara 2 fechas
     *
     * @param f1 Fecha a comparar
     * @param f2 Fecha a comparar
     * @return True si f1 es < a f2, False si f1 es > a f2
     */
    private static boolean compararFechas(String f1, String f2) {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        Date fecha1 = formatoFecha.parse(f1, new ParsePosition(0));
        Date fecha2 = formatoFecha.parse(f2, new ParsePosition(0));

        if (fecha1.before(fecha2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Funcion para comparar rango entre fechas
     *
     * @param f1 Fecha original
     * @param f2 Fecha original
     * @param f3 Fecha introducida
     * @param f4 Fecha introducida
     * @return True si las fechas introducidas estan en el rango de las
     * originales, False si se pasan
     */
    private static boolean comprobarRango(String f1, String f2, String f3, String f4) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        Date fecha1 = formatoFecha.parse(f1, new ParsePosition(0));
        Date fecha2 = formatoFecha.parse(f2, new ParsePosition(0));
        Date fecha3 = formatoFecha.parse(f3, new ParsePosition(0));
        Date fecha4 = formatoFecha.parse(f4, new ParsePosition(0));

        if (fecha1.before(fecha3) && fecha2.after(fecha4) || fecha1.equals(fecha3) && fecha2.equals(fecha4)
                || fecha1.before(fecha3) && fecha2.equals(fecha4) || fecha1.equals(fecha3) && fecha2.after(fecha4)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Funcion para calcular la diferencia entre 2 fechas en días
     *
     * @param f1 Fecha inicio
     * @param f2 Fecha final
     * @return La diferencia en dias
     */
    private static int diferenciaDias(String f1, String f2) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        Date fecha1 = formatoFecha.parse(f1, new ParsePosition(0));
        Date fecha2 = formatoFecha.parse(f2, new ParsePosition(0));

        return (int) ((fecha1.getTime() - fecha2.getTime()) / 86400000) * -1;
    }

    /**
     *
     * PROGRAMA PRINCIPAL DESDE DONDE SE EJECUTA EL MENÚ
     */
    public static void main(String[] args) {

        // VARIABLES USADAS PARA RECOGER DATOS DE TECLADO
        int opcion = -1;
        String nombre, email, desc, fecIni, fecFin;
        double precio, importe, cont = 0, nalquiler;
        int cod, codProd;
        boolean flag = false;

        // ARRAYS DINAMICOS PARA ALMACENAR INFORMACION DE USUARIOS, OBJETOS, ECT
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        ArrayList<Objeto> objetos = new ArrayList<Objeto>();
        ArrayList<Integer> idClientes = new ArrayList<Integer>();

        Scanner teclado = new Scanner(System.in);

        // COMIENZO DEL PROGRAMA
        System.out.println("Bienvenido a la mayor empresa de alquiler de España\n'En tiempos de crisis, saque el máximo rendimiento a sus pertenencias'\n\nPor favor selecciona una de las acciones para comenzar: ");

        // MENÚ
        while (opcion != 9) {

            System.out.println("\nMENÚ PRINCIPAL 2.0");
            System.out.println("\nCrear Usuario ............. 1");
            System.out.println("Alta Objeto ............... 2");
            System.out.println("Alquiler de Objeto ........ 3");
            System.out.println("Listar Objetos ............ 4");
            System.out.println("Baja de Objeto ............ 5");
            System.out.println("Mostrar Saldo ............. 6");
            System.out.println("Modificar alquiler......... 7");
            System.out.println("Generar recibo (.txt)...... 8");
            System.out.println("Salir ..................... 9");

            System.out.print("\nOpcion: ");
            opcion = Integer.parseInt(teclado.nextLine());

            switch (opcion) {
                case 1: {              // ALTA USUARIO
                    System.out.print("Por favor introduce tu nombre completo: ");
                    nombre = teclado.nextLine();
                    System.out.print("Por favor introduce tu correo electronico: ");
                    email = teclado.nextLine();

                    while (comprobarEmail(email) == false) {
                        System.out.print("Correo electronico incorrecto, por favor introduce un correo válido: ");
                        email = teclado.nextLine();
                    }

                    Usuario u = new Usuario(nombre, email);
                    usuarios.add(u);

                    System.out.println("\nUSUARIO CREADO CORRECTAMENTE\n");
                }
                break;

                case 2: {                // ALTA OBJETO
                    if (usuarios.isEmpty()) {
                        System.out.println("\nNo existen usuarios registrados en el sistema, por favor introduce la opcion 1. \n");
                    } else {

                        System.out.print("\nPor favor introduce tu codigo de usuario: \n");

                        for (int i = 0; i < usuarios.size(); i++) {
                            System.out.println(usuarios.get(i).toString());
                        }

                        System.out.print("Codigo: ");
                        cod = Integer.parseInt(teclado.nextLine());

                        System.out.print("Por favor indique una descripcion para su objeto: ");
                        desc = teclado.nextLine();

                        System.out.println("Un rango de fechas");
                        System.out.print("\tFecha inicio (dd/mm/yyyy): ");
                        fecIni = teclado.nextLine();

                        while (comprobarFecha(fecIni) == false) {
                            System.out.print("\tFecha INCORRECTA, por favor introduce una fecha correcta: ");
                            fecIni = teclado.nextLine();
                        }

                        System.out.print("\tFecha fin (dd/mm/yyyy): ");
                        fecFin = teclado.nextLine();

                        while (comprobarFecha(fecFin) == false || compararFechas(fecIni, fecFin) == false) {
                            System.out.print("\tFecha INCORRECTA, por favor introduce una fecha correcta: ");
                            fecFin = teclado.nextLine();
                        }

                        System.out.print("Precio por día: ");
                        precio = Double.parseDouble(teclado.nextLine());

                        Objeto o = new Objeto(cod, desc, fecIni, fecFin, precio);
                        objetos.add(o);
                        usuarios.get(cod - 1).setObjetos_prestados(o);

                        System.out.println("\nOBJETO DADO DE ALTA CORRECTAMENTE\n");
                    }
                }
                break;

                case 3: {             // ALTA ALQUILER
                    if (usuarios.isEmpty()) {
                        System.out.println("\nNo existen usuarios registrados en el sistema, por favor introduce la opcion 1. \n");
                    } else {
                        System.out.print("\nPor favor introduce tu codigo de usuario: \n");

                        for (int i = 0; i < usuarios.size(); i++) {
                            System.out.println(usuarios.get(i).toString());
                        }

                        System.out.print("Codigo: ");
                        cod = Integer.parseInt(teclado.nextLine());

                        if (objetos.isEmpty()) {
                            System.out.println("\nNo hay objetos para alquilar. ");
                        } else {
                            for (int i = 0; i < objetos.size(); i++) {
                                if (objetos.get(i).getIdCliente() != cod) {
                                    System.out.println(objetos.get(i).toString());
                                    flag = true;
                                }
                            }
                        }

                        if (flag) {
                            System.out.print("Codigo del objeto a pedir: ");
                            codProd = Integer.parseInt(teclado.nextLine());

                            System.out.println("Introduce las fechas de alquiler");
                            System.out.print("\tFecha inicio: ");
                            fecIni = teclado.nextLine();
                            System.out.print("\tFecha fin: ");
                            fecFin = teclado.nextLine();

                            while (comprobarRango(objetos.get(codProd - 1).getFechaIni(), objetos.get(codProd - 1).getFechaFin(), fecIni, fecFin) == false) {
                                System.out.println("Fechas para el producto no validas");
                                System.out.println("Introduce las fechas de alquiler");
                                System.out.print("\tFecha inicio: ");
                                fecIni = teclado.nextLine();
                                System.out.print("\tFecha fin: ");
                                fecFin = teclado.nextLine();
                            }

                            importe = objetos.get(codProd - 1).getPrecio() * diferenciaDias(fecIni, fecFin);

                            Prestamo p = new Prestamo(cod, codProd, objetos.get(codProd - 1).getIdCliente(), usuarios.get(cod - 1).getNombre(), fecIni, fecFin, importe, importe * 0.1);

                            for (int i = 0; i < usuarios.get(objetos.get(codProd - 1).getIdCliente() - 1).getObjetos_prestados().size(); i++) {
                                if (usuarios.get(objetos.get(codProd - 1).getIdCliente() - 1).getObjetos_prestados().get(i).getIdObjeto() == codProd) {
                                    usuarios.get(objetos.get(codProd - 1).getIdCliente() - 1).getObjetos_prestados().get(i).setPrestamos(p);
                                }
                            }

                            flag = false;

                            System.out.println("\nALQUILER REALIZADO CORRECTAMENTE\n");
                        } else {
                            System.out.println("\nNo hay objetos para alquilar. ");
                        }

                    }

                }
                break;

                case 4: {               // LISTADO DE USUARIOS, OBJETOS Y PRESTAMOS
                    if (usuarios.isEmpty()) {
                        System.out.println("\nNo existen usuarios registrados en el sistema, por favor introduce la opcion 1. \n");
                    } else {

                        for (int i = 0; i < usuarios.size(); i++) {
                            System.out.println(usuarios.get(i));

                            System.out.println("\tOBJETOS DEL PROPIETARIO " + usuarios.get(i).getIdCliente() + "\n");

                            if (usuarios.get(i).getObjetos_prestados().isEmpty()) {
                                System.out.println("\tEl propietario " + usuarios.get(i).getIdCliente() + " no tiene objetos asociados. \n");
                            } else {
                                for (int j = 0; j < usuarios.get(i).getObjetos_prestados().size(); j++) {
                                    System.out.println(usuarios.get(i).getObjetos_prestados().get(j).toString());

                                    System.out.println("\t\tPRÉSTAMOS DEL OBJETO " + usuarios.get(i).getObjetos_prestados().get(j).getIdObjeto() + "\n");

                                    if (usuarios.get(i).getObjetos_prestados().get(j).getPrestamos().isEmpty()) {
                                        System.out.println("\t\tEl objeto " + usuarios.get(i).getObjetos_prestados().get(j).getIdObjeto() + " no tiene prestamos asociados. \n");
                                    } else {
                                        for (int k = 0; k < usuarios.get(i).getObjetos_prestados().get(j).getPrestamos().size(); k++) {
                                            System.out.println(usuarios.get(i).getObjetos_prestados().get(j).getPrestamos().get(k));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;

                case 5: {                    // BORRAR OBJETOS
                    if (usuarios.isEmpty()) {
                        System.out.println("\nNo existen usuarios registrados en el sistema, por favor introduce la opcion 1. \n");
                    } else {

                        if (objetos.isEmpty()) {
                            System.out.println("No existen objetos\n");
                        } else {
                            System.out.print("\nPor favor introduce el codigo de usuario: \n");

                            for (int i = 0; i < usuarios.size(); i++) {
                                System.out.println(usuarios.get(i).toString());
                            }

                            System.out.print("Codigo: ");
                            cod = Integer.parseInt(teclado.nextLine());

                            System.out.print("\nPor favor introduce el codigo de producto: \n");

                            for (int i = 0; i < objetos.size(); i++) {
                                if (objetos.get(i).getIdCliente() == cod) {
                                    System.out.println(objetos.get(i).toString());
                                    flag = true;
                                }
                            }

                            if (flag) {
                                System.out.print("Codigo del objeto a quitar: ");
                                codProd = Integer.parseInt(teclado.nextLine());

                                objetos.remove(codProd - 1);
                                flag = false;

                                System.out.println("\nOBJETO ELIMINADO CORRECTAMENTE\n");
                            } else {
                                System.out.println("\nNo tienes objetos. ");
                            }
                        }
                    }
                }
                break;

                case 6: {               // MOSTRAR PRESTAMOS CON EL IMPORTE TOTAL DE LA STARTUP
                    if (usuarios.isEmpty()) {
                        System.out.println("\nNo existen usuarios registrados en el sistema, por favor introduce la opcion 1. \n");
                    } else {

                        for (int i = 0; i < usuarios.size(); i++) {
                            for (int j = 0; j < usuarios.get(i).getObjetos_prestados().size(); j++) {
                                for (int k = 0; k < usuarios.get(i).getObjetos_prestados().get(j).getPrestamos().size(); k++) {
                                    idClientes.add(usuarios.get(i).getObjetos_prestados().get(j).getPrestamos().get(k).getIdClienteD());
                                }
                            }
                        }

                        idClientes = new ArrayList<Integer>(new HashSet<Integer>(idClientes)); //Elimino los duplicados
                        Collections.sort(idClientes); //Ordeno el arraylist

                        for (int i = 0; i < idClientes.size(); i++) {
                            System.out.println(usuarios.get(idClientes.get(i) - 1).toString());

                            for (int j = 0; j < usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().size(); j++) {
                                System.out.println(usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).toString());

                                System.out.println("\t\tPRÉSTAMOS DEL OBJETO " + usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).getIdObjeto() + "\n");

                                if (usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).getPrestamos().isEmpty()) {
                                    System.out.println("\t\tEl objeto " + usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).getIdObjeto() + " no tiene prestamos asociados. \n");
                                } else {
                                    for (int k = 0; k < usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).getPrestamos().size(); k++) {
                                        System.out.println(usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).getPrestamos().get(k).toString());

                                        cont += usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).getPrestamos().get(k).getStartUp();
                                    }
                                }
                            }

                            System.out.println("El importe total para la startUp es de " + cont + " euros\n");
                            cont = 0;
                        }

                        idClientes.clear();
                    }
                }
                break;

                case 7: {                 // MODIFICAR EL PRECIO DEL ALQUILER - MODIFICACIÓN 1 PRÁCTICA 2
                    if (usuarios.isEmpty()) {

                        System.out.println("\nNo existen usuarios registrados en el sistema, por favor introduce la opcion 1. \n");

                    } else {

                        System.out.print("\nPor favor introduce tu codigo de usuario: \n");

                        for (int i = 0; i < usuarios.size(); i++) {
                            System.out.println(usuarios.get(i).toString());
                        }

                        System.out.print("Codigo: ");
                        cod = Integer.parseInt(teclado.nextLine());

                        if (objetos.isEmpty()) {
                            System.out.println("\nNo hay objetos para alquilar. ");
                        } else {
                            for (int i = 0; i < objetos.size(); i++) {
                                if (objetos.get(i).getIdCliente() == cod) {
                                    System.out.println(objetos.get(i).toString());
                                    flag = true;
                                }
                            }
                        }

                        if (flag) {

                            System.out.print("Codigo del objeto a modificar su precio de alquiler: ");
                            codProd = Integer.parseInt(teclado.nextLine());

                            System.out.print("Introduce un nuevo precio de alquiler por día: ");
                            nalquiler = Double.parseDouble(teclado.nextLine());

                            objetos.get(codProd - 1).setPrecio(nalquiler);

                            System.out.println("\nPRECIO DE ALQUILER DEL OBJETO '" + objetos.get(codProd - 1).getDescripcion() + "' MODIFICADO CORRECTAMENTE. \n");
                        }
                    }
                }
                break;

                case 8: { // GENERAR UN ARCHIVO TXT CON LA INFORMACION MOSTRADA EN LA OPCION 6 - MODIFICACION 2 PRACTICA 2
                    
                    String recibo = "";
                    
                    if (usuarios.isEmpty()) {
                        System.out.println("\nNo existen usuarios registrados en el sistema, por favor introduce la opcion 1. \n");
                    } else {

                        for (int i = 0; i < usuarios.size(); i++) {
                            for (int j = 0; j < usuarios.get(i).getObjetos_prestados().size(); j++) {
                                for (int k = 0; k < usuarios.get(i).getObjetos_prestados().get(j).getPrestamos().size(); k++) {
                                    idClientes.add(usuarios.get(i).getObjetos_prestados().get(j).getPrestamos().get(k).getIdClienteD());
                                }
                            }
                        }

                        idClientes = new ArrayList<Integer>(new HashSet<Integer>(idClientes)); //Elimino los duplicados
                        Collections.sort(idClientes); //Ordeno el arraylist

                        for (int i = 0; i < idClientes.size(); i++) {
                            recibo += usuarios.get(idClientes.get(i) - 1).toString();

                            for (int j = 0; j < usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().size(); j++) {
                                recibo += usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).toString();

                                recibo += "\t\tPRÉSTAMOS DEL OBJETO " + usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).getIdObjeto() + "\n\r";

                                if (usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).getPrestamos().isEmpty()) {
                                    recibo += "\t\tEl objeto " + usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).getIdObjeto() + " no tiene prestamos asociados. \n\r";
                                } else {
                                    for (int k = 0; k < usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).getPrestamos().size(); k++) {
                                        recibo += usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).getPrestamos().get(k).toString();

                                        cont += usuarios.get(idClientes.get(i) - 1).getObjetos_prestados().get(j).getPrestamos().get(k).getStartUp();
                                    }
                                }
                            }

                            recibo += "\rEl importe total para la startUp es de " + cont + " euros\n\r";
                            cont = 0;
                        }

                        idClientes.clear();
                        
                        try{
                            FileWriter fichero = new FileWriter("saldo.txt");
                            fichero.write(recibo);
                            fichero.close();
                            
                            System.out.println("\nRECIBO GENERADO CORRECTAMENTE\n");
                        }catch(Exception e){
                            System.out.println("\nError desconocido. \n");
                        }
                    } 
                }
                break;

                case 9: {
                    System.out.println("MUCHAS GRACIAS POR USAR NUESTROS SERVICIOS, HASTA PRONTO!");
                }
                break;

                default: {
                    System.out.println("OPCIÓN NO RECONOCIDA. \n");
                }
                break;
            }
        }
    }
}
