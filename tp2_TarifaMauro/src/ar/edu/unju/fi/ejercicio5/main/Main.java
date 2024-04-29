package ar.edu.unju.fi.ejercicio5.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio1.model.Producto;
import ar.edu.unju.fi.ejercicio1.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio1.model.Producto.OrigenFabricacion;
import ar.edu.unju.fi.ejercicio5.model.PagoEfectivo;
import ar.edu.unju.fi.ejercicio5.model.PagoTarjeta;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> productos = preCargarProductos();

        int op = 0;
        do {
            try {
                menu();
                op = scanner.nextInt();

                switch (op) {
                    case 1:
                        mostrarProductos(productos);
                        break;
                    case 2:
                        ArrayList<Producto> productosComprados = seleccionarProductos(productos);
                        int opcionPago;
                        do {
                            System.out.println("Seleccione una opcion de pago:");
                            System.out.println("1 - Pago efectivo");
                            System.out.println("2 - Pago con tarjeta");
                            opcionPago = scanner.nextInt();
                        } while (opcionPago != 1 && opcionPago != 2);

                        double total = calcularTotal(productosComprados);
                        try {
                            if (opcionPago == 1) {
                                PagoEfectivo pagoEfectivo = new PagoEfectivo(LocalDate.now(), total);
                                pagoEfectivo.realizarPago(total);
                                pagoEfectivo.imprimirRecibo();
                            } else {
                                System.out.print("Ingrese el numero de tarjeta: ");
                                String numeroTarjeta = scanner.next();
                                PagoTarjeta pagoTarjeta = new PagoTarjeta(numeroTarjeta, LocalDate.now(), total);
                                pagoTarjeta.realizarPago(total);
                                pagoTarjeta.imprimirRecibo();
                            }
                        } catch (Exception e) {
                            System.out.println("Error al procesar el pago: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Salir");
                        break;
                    default:
                        System.out.println("Opcion invalida. Seleccione nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error. Ingrese una opcion valida.");
                scanner.next(); 
            }
        } while (op != 3);

        scanner.close();
    }
	
	public static void menu() {
		System.out.println("*************Menú de opciones *****************");
        System.out.println("1 - Mostrar productos");
        System.out.println("2 - Realizar compra");
        System.out.println("3 - Salir");
        System.out.print("Seleccione una opción: ");
	}

    private static ArrayList<Producto> preCargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("1", "Telefono ", 150000, OrigenFabricacion.CHINA, Categoria.TELEFONIA, true));
        productos.add(new Producto("2", "Laptop", 200000, OrigenFabricacion.BRASIL, Categoria.INFORMATICA, true));
        productos.add(new Producto("3", "Aspiradora", 35000, OrigenFabricacion.URUGUAY, Categoria.ELECTROHOGAR, false));
        productos.add(new Producto("4", "Taladro", 50000, OrigenFabricacion.ARGENTINA, Categoria.HERRAMIENTAS, false));
        productos.add(new Producto("5", "Impresora", 150000, OrigenFabricacion.CHINA, Categoria.INFORMATICA, true));
        productos.add(new Producto("6", "Televisor", 230000, OrigenFabricacion.URUGUAY, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto("7", "Tablet", 200000, OrigenFabricacion.CHINA, Categoria.INFORMATICA, false));
        productos.add(new Producto("8", "Lavadora", 140000, OrigenFabricacion.BRASIL, Categoria.ELECTROHOGAR, false));
        productos.add(new Producto("9", "Martillo", 20000, OrigenFabricacion.ARGENTINA, Categoria.HERRAMIENTAS, true));
        productos.add(new Producto("10", "Destornillador", 1000, OrigenFabricacion.ARGENTINA, Categoria.HERRAMIENTAS, true));
        productos.add(new Producto("11", "Camara de seguridad", 40000, OrigenFabricacion.CHINA, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto("12", "Mouse", 15000, OrigenFabricacion.CHINA, Categoria.INFORMATICA, true));
        productos.add(new Producto("13", "Teclado", 20000, OrigenFabricacion.CHINA, Categoria.INFORMATICA, true));
        productos.add(new Producto("14", "Cable HDMI", 5000, OrigenFabricacion.CHINA, Categoria.INFORMATICA, true));
        productos.add(new Producto("15", "Bombillo LED", 2000, OrigenFabricacion.CHINA, Categoria.ELECTROHOGAR, true));
        return productos;
    }

    private static void mostrarProductos(ArrayList<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        } else {
            for (Producto producto : productos) {
                System.out.println("Codigo: " + producto.getCodigo());
                System.out.println("Descripcion: " + producto.getDescripcion());
                System.out.println("Precio Unitario: $" + producto.getPrecioUnitario());
                System.out.println("Origen de Fabricacion: " + producto.getOrigenFabricacion());
                System.out.println("Categoría: " + producto.getCategoria());
                System.out.println("Disponible: " + (producto.isDisponible() ? "Si" : "No"));
                System.out.println(); 
            }
        }
    }

    private static ArrayList<Producto> seleccionarProductos(ArrayList<Producto> productos) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> productosSeleccionados = new ArrayList<>();

        System.out.println("Seleccione los productos que desea comprar (ingrese el numero correspondiente):");
        for (int i = 0; i < productos.size(); i++) {
            System.out.println((i + 1) + " - " + productos.get(i).getDescripcion());
        }
        System.out.println("0 - Finalizar seleccion");

        int opcion;
        do {
            System.out.print("Ingrese el numero del producto (0 para finalizar): ");
            opcion = scanner.nextInt();

            if (opcion > 0 && opcion <= productos.size()) {
                Producto productoSeleccionado = productos.get(opcion - 1);
                productosSeleccionados.add(productoSeleccionado);
                System.out.println("Producto '" + productoSeleccionado.getDescripcion() + "' agregado al carrito.");
            } else if (opcion != 0) {
                System.out.println("Opcion invalida. Por favor, ingrese un numero valido.");
            }
        } while (opcion != 0);

        return productosSeleccionados;
    }

    private static double calcularTotal(ArrayList<Producto> productos) {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecioUnitario();
        }
        return total;
    }
}