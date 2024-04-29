package ar.edu.unju.fi.ejercicio1.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio1.model.Producto;
import ar.edu.unju.fi.ejercicio1.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio1.model.Producto.OrigenFabricacion;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Producto> productos = new ArrayList<>();
		
		int op = 0;
		  do {
	            try {
	            	menu();
	                op = scanner.nextInt();
	                scanner.nextLine();

	                switch (op) {
	                    case 1:
	                        crearProducto(scanner, productos);
	                        break;
	                    case 2:
	                        mostrarProductos(productos);
	                        break;
	                    case 3:
	                        modificarProducto(scanner, productos);
	                        break;
	                    case 4:
	                        System.out.println("Saliendo del programa...");
	                        break;
	                    default:
	                        System.out.println("Opcion no valida. Por favor, ingrese una opción valida.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Error: Ingrese un valor valido.");
	                scanner.nextLine();
	                op = 0;
	            }
	        } while (op != 4);
	        scanner.close();


	}
	
	public static void menu(){
		System.out.println("************ Menu de opciones **************");
        System.out.println("1 - Crear Producto");
        System.out.println("2 - Mostrar productos");
        System.out.println("3 - Modificar producto");
        System.out.println("4 - Salir");
        System.out.print("Elija una opcion: ");
	}
	
	private static void crearProducto(Scanner scanner, ArrayList<Producto> productos) {
        try {
            System.out.println("Creando un nuevo producto:");

            System.out.print("Ingrese el codigo del producto: ");
            String codigo = scanner.nextLine();

            System.out.print("Ingrese la descripcion del producto: ");
            String descripcion = scanner.nextLine();

            System.out.print("Ingrese el precio unitario del producto: ");
            double precioUnitario = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Origen de fabricación:");
            mostrarOpcionesEnum(OrigenFabricacion.values());
            System.out.print("Elija una opción: ");
            int opcionOrigen = scanner.nextInt();
            scanner.nextLine();
            boolean disponible = true;
            OrigenFabricacion origenFabricacion = OrigenFabricacion.values()[opcionOrigen - 1];

            System.out.println("Categoría:");
            mostrarOpcionesEnum(Categoria.values());
            System.out.print("Elija una opción: ");
            int opcionCategoria = scanner.nextInt();
            scanner.nextLine();
            Categoria categoria = Categoria.values()[opcionCategoria - 1];

            Producto producto = new Producto(codigo, descripcion, precioUnitario, origenFabricacion, categoria, disponible);
            productos.add(producto);
            System.out.println("Producto creado exitosamente.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un valor valido.");
            scanner.nextLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Opcion no valida.");
        }
    }

    private static void mostrarProductos(ArrayList<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos para mostrar.");
        } else {
            System.out.println("Lista de productos:");
            for (Producto producto : productos) {
            	System.out.println("********************************************");
                System.out.println("Codigo: "+producto.getCodigo());
                System.out.println("Descripcion: "+producto.getDescripcion());
                System.out.println("Precio Unitario: "+producto.getPrecioUnitario());
                System.out.println("Categoria: "+producto.getCategoria());
                System.out.println("Origen de Fabricacion: "+producto.getOrigenFabricacion());
                System.out.println("********************************************");
            }
        }
    }

    private static void modificarProducto(Scanner scanner, ArrayList<Producto> productos) {
        try {
            if (productos.isEmpty()) {
                System.out.println("No hay productos para modificar.");
                return;
            }

            System.out.println("Seleccione el producto a modificar:");
            mostrarProductos(productos);
            System.out.print("Ingrese el número de producto a modificar: ");
            int numeroProducto = scanner.nextInt();
            scanner.nextLine();

            if (numeroProducto < 1 || numeroProducto > productos.size()) {
                System.out.println("Numero de producto no valido.");
                return;
            }

            Producto producto = productos.get(numeroProducto - 1);
            System.out.println("Producto seleccionado: " + producto);

            System.out.println("Seleccione el atributo a modificar:");
            System.out.println("1 - Descripcion");
            System.out.println("2 - Precio unitario");
            System.out.println("3 - Origen de fabricacion");
            System.out.println("4 - Categoria");
            System.out.print("Ingrese su opcion: ");
            int op = scanner.nextInt();
            scanner.nextLine(); 

            switch (op) {
                case 1:
                    System.out.print("Ingrese la nueva descripción: ");
                    producto.setDescripcion(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo precio unitario: ");
                    producto.setPrecioUnitario(scanner.nextDouble());
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Origen de fabricacion:");
                    mostrarOpcionesEnum(OrigenFabricacion.values());
                    System.out.print("Elija una opcion: ");
                    int opcionOrigen = scanner.nextInt();
                    scanner.nextLine();
                    producto.setOrigenFabricacion(OrigenFabricacion.values()[opcionOrigen - 1]);
                    break;
                case 4:
                    System.out.println("Categoría:");
                    mostrarOpcionesEnum(Categoria.values());
                    System.out.print("Elija una opcion: ");
                    int opcionCategoria = scanner.nextInt();
                    scanner.nextLine();
                    producto.setCategoria(Categoria.values()[opcionCategoria - 1]);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            System.out.println("Producto modificado exitosamente.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un valor válido.");
            scanner.nextLine(); // Limpiar el buffer de entrada
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Opción no válida.");
        }
    }

    private static void mostrarOpcionesEnum(Enum<?>[] values) {
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + " - " + values[i]);
        }
    }

}
