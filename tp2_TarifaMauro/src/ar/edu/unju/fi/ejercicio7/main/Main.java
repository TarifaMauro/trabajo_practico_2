package ar.edu.unju.fi.ejercicio7.main;

import ar.edu.unju.fi.ejercicio5.model.Producto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> productos = preCargarProductos();
        Scanner scanner = new Scanner(System.in);

        int op = 0;
        do {
            menu();
            try {
                op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        mostrarProductos(productos, p -> p.isDisponible());
                        break;
                    case 2:
                        mostrarProductosFaltantes(productos);
                        break;
                    case 3:
                        incrementarPrecios(productos);
                        break;
                    case 4:
                        mostrarProductos(productos, p -> p.isDisponible() && p.getCategoria() == Producto.Categoria.ELECTROHOGAR);
                        break;
                    case 5:
                        ordenarProductosPorPrecioDescendente(productos);
                        break;
                    case 6:
                        mostrarNombresEnMayusculas(productos);
                        break;
                    case 7:
                        System.out.println("Saliendo del programa.");
                        break;
                    default:
                        System.out.println("Opcion incorrecta. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error. Ingrese una opcion valida.");
                scanner.nextLine();
                op = 0;
            }
        } while (op != 7);

        scanner.close();
    }

    public static void menu() {
        System.out.println("***************Menu de opciones*******************");
        System.out.println("1 - Mostrar productos disponibles");
        System.out.println("2 - Mostrar productos no disponibles");
        System.out.println("3 - Incrementar precios en un 20%");
        System.out.println("4 - Mostrar productos de categoria ELECTROHOGAR disponibles");
        System.out.println("5 - Ordenar productos por precio descendente");
        System.out.println("6 - Mostrar nombres de productos en mayusculas");
        System.out.println("7 - Salir");
        System.out.print("Ingrese su opción: ");
    }

    private static ArrayList<Producto> preCargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("1", "Telefono ", 150000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.TELEFONIA, true));
        productos.add(new Producto("2", "Laptop", 200000, Producto.OrigenFabricacion.BRASIL, Producto.Categoria.INFORMATICA, true));
        productos.add(new Producto("3", "Aspiradora", 35000, Producto.OrigenFabricacion.URUGUAY, Producto.Categoria.ELECTROHOGAR, false));
        productos.add(new Producto("4", "Taladro", 50000, Producto.OrigenFabricacion.ARGENTINA, Producto.Categoria.HERRAMIENTAS, false));
        productos.add(new Producto("5", "Impresora", 150000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.INFORMATICA, true));
        productos.add(new Producto("6", "Televisor", 230000, Producto.OrigenFabricacion.URUGUAY, Producto.Categoria.ELECTROHOGAR, true));
        productos.add(new Producto("7", "Tablet", 200000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.INFORMATICA, false));
        productos.add(new Producto("8", "Lavadora", 140000, Producto.OrigenFabricacion.BRASIL, Producto.Categoria.ELECTROHOGAR, false));
        productos.add(new Producto("9", "Martillo", 20000, Producto.OrigenFabricacion.ARGENTINA, Producto.Categoria.HERRAMIENTAS, true));
        productos.add(new Producto("10", "Destornillador", 1000, Producto.OrigenFabricacion.ARGENTINA, Producto.Categoria.HERRAMIENTAS, true));
        productos.add(new Producto("11", "Camara de seguridad", 40000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.ELECTROHOGAR, true));
        productos.add(new Producto("12", "Mouse", 15000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.INFORMATICA, false));
        productos.add(new Producto("13", "Teclado", 20000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.INFORMATICA, true));
        productos.add(new Producto("14", "Cable HDMI", 5000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.INFORMATICA, false));
        productos.add(new Producto("15", "Bombillo LED", 2000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.ELECTROHOGAR, true));
        return productos;
    }

    public static void mostrarProductos(ArrayList<Producto> productos, Predicate<Producto> predicate) {
        productos.stream()
                .filter(predicate)
                .forEach(System.out::println);
    }

    public static void mostrarProductosFaltantes(ArrayList<Producto> productos) {
        productos.stream()
                .filter(p -> !p.isDisponible())
                .forEach(System.out::println);
    }

    public static void incrementarPrecios(ArrayList<Producto> productos) {
        Function<Producto, Producto> incrementarPrecio = p -> new Producto(p.getCodigo(), p.getDescripcion(), p.getPrecioUnitario() * 1.20, p.getOrigenFabricacion(), p.getCategoria(), p.isDisponible());
        ArrayList<Producto> productosIncrementados = productos.stream()
                .map(incrementarPrecio)
                .collect(Collectors.toCollection(ArrayList::new));
        productos.clear();
        productos.addAll(productosIncrementados);
    }

    public static void ordenarProductosPorPrecioDescendente(ArrayList<Producto> productos) {
        productos.sort(Comparator.comparing(Producto::getPrecioUnitario).reversed());
        productos.forEach(System.out::println);
    }

    public static void mostrarNombresEnMayusculas(ArrayList<Producto> productos) {
        Function<Producto, Producto> toUpperCase = p -> new Producto(p.getCodigo(), p.getDescripcion().toUpperCase(), p.getPrecioUnitario(), p.getOrigenFabricacion(), p.getCategoria(), p.isDisponible());
        mostrarProductos(productos.stream().map(toUpperCase).collect(Collectors.toCollection(ArrayList::new)), p -> true);
    }
}