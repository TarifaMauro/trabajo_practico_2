package ar.edu.unju.fi.ejercicio4.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio4.constantes.Posicion;
import ar.edu.unju.fi.ejercicio4.model.Jugador;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        int op = 0;

        do {
            menu();
            try {
                op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1:
                        altaJugador(jugadores, scanner);
                        break;
                    case 2:
                        mostrarJugadores(jugadores);
                        break;
                    case 3:
                        modificarPosicion(jugadores, scanner);
                        break;
                    case 4:
                        eliminarJugador(jugadores, scanner);
                        break;
                    case 5:
                        System.out.println("Saliendo.");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida. Por favor ingrese una opcion correcta.");
                scanner.nextLine();
                op = 0;
            }
        } while (op != 5);

        scanner.close();
    }

    public static void menu() {
        System.out.println("*********** Menu **************");
        System.out.println("1 - Alta de jugador ");
        System.out.println("2 - Mostrar todos los jugadores ");
        System.out.println("3 - Modificar la posicion de un jugador ");
        System.out.println("4 - Eliminar un jugador ");
        System.out.println("5 - Salir ");
        System.out.print("Ingrese una opción: ");
    }

    private static void altaJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
        System.out.println("Ingrese el nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del jugador: ");
        String apellido = scanner.nextLine();
        LocalDate fechaNacimiento = ingresarFecha(scanner, "Ingrese la fecha de nacimiento del jugador (YYYY-MM-DD): ");
        System.out.println("Ingrese la nacionalidad del jugador: ");
        String nacionalidad = scanner.nextLine();
        System.out.println("Ingrese la estatura del jugador: ");
        double estatura = scanner.nextDouble();
        System.out.println("Ingrese el peso del jugador: ");
        double peso = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Ingrese la posicion del jugador: ");
        String posicionStr = scanner.nextLine();
        Posicion posicion = Posicion.valueOf(posicionStr.toUpperCase());

        Jugador jugador = new Jugador(nombre, apellido, fechaNacimiento, nacionalidad, estatura, peso, posicion);
        jugadores.add(jugador);
        System.out.println("Jugador agregado correctamente.");
    }

    private static LocalDate ingresarFecha(Scanner scanner, String mensaje) {
        LocalDate fecha = null;
        while (fecha == null) {
            try {
                System.out.println(mensaje);
                String fechaStr = scanner.nextLine();
                fecha = LocalDate.parse(fechaStr);
            } catch (Exception e) {
                System.out.println("Fecha invalida. Formato esperado: YYYY-MM-DD");
            }
        }
        return fecha;
    }

    private static void mostrarJugadores(ArrayList<Jugador> jugadores) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores registrados.");
        } else {
        	System.out.println("********* Jugadores ***********");
            for (Jugador jugador : jugadores) {
                System.out.println( "Nombre: "+jugador.getNombre());
                System.out.println( "Apellido: "+jugador.getApellido());
                System.out.println( "Nacionalidad: "+jugador.getNacionalidad());
                System.out.println( "Estatura: "+jugador.getEstatura()+ " m.");
                System.out.println( "Peso: "+jugador.getPeso()+ " kg.");
                System.out.println( "Fecha de Nacimiento: "+jugador.getFechaNac());
                System.out.println( "Posicion: "+jugador.getPosicion());
                System.out.println("*******************************");
            }
        }
    }
    private static void modificarPosicion(ArrayList<Jugador> jugadores, Scanner scanner) {
        System.out.println("Ingrese el nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del jugador: ");
        String apellido = scanner.nextLine();

        Iterator<Jugador> iterator = jugadores.iterator();
        boolean encontrado = false;
        while (iterator.hasNext()) {
            Jugador jugador = iterator.next();
            if (jugador.getNombre().equals(nombre) && jugador.getApellido().equals(apellido)) {
                System.out.println("Ingrese la nueva posición del jugador: ");
                String nuevaPosicionStr = scanner.nextLine();
                Posicion nuevaPosicion = Posicion.valueOf(nuevaPosicionStr.toUpperCase());
                jugador.setPosicion(nuevaPosicion);
                System.out.println("Posicion modificada correctamente.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Jugador no encontrado.");
        }
    }

    private static void eliminarJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
        System.out.println("Ingrese el nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del jugador: ");
        String apellido = scanner.nextLine();

        Iterator<Jugador> iterator = jugadores.iterator();
        boolean encontrado = false;
        while (iterator.hasNext()) {
            Jugador jugador = iterator.next();
            if (jugador.getNombre().equals(nombre) && jugador.getApellido().equals(apellido)) {
                iterator.remove();
                System.out.println("Jugador eliminado correctamente.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Jugador no encontrado.");
        }
    }
}