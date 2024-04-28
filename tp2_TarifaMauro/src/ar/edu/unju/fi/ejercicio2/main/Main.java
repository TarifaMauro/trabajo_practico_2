package ar.edu.unju.fi.ejercicio2.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio2.constantes.Mes;
import ar.edu.unju.fi.ejercicio2.model.Efemeride;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Efemeride> efemerides = new ArrayList<>();
		
		int op = 0;
		do {
			try {
				menu();
				op = scanner.nextInt();
				switch (op) {
                case 1:
                     crearEfemeride(scanner, efemerides);
                     break;
                case 2:
                     mostrarEfemerides(efemerides);
                     break;
                case 3:
                     eliminarEfemeride(scanner, efemerides);
                     break;
                case 4:
                     modificarEfemeride(scanner, efemerides);
                     break;
                case 5:
                     System.out.println("Saliendo...");
                     break;
                default:
                     System.out.println("Opcion invalida. Por favor, ingrese una opción valida.");
				}
			}catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese una opción valida.");
                scanner.nextLine();
                op = 0;
			}
		}while(op != 5);
		
	}
	
	public static void menu(){
		System.out.println("**********Menu de opciones**********");
		System.out.println("1 - Crear efemeride");
        System.out.println("2 - Mostrar efemerides");
        System.out.println("3 - Eliminar efemeride");
        System.out.println("4 - Modificar efemeride");
        System.out.println("5 - Salir");
        System.out.print("Ingrese su opción: ");
	}
	public static void crearEfemeride(Scanner scanner, ArrayList<Efemeride> efemerides) {
        try {
            System.out.print("Ingrese el código: ");
            int codigo = scanner.nextInt();
            System.out.print("Ingrese el mes (1-12): ");
            int numeroMes = scanner.nextInt();
            while (numeroMes < 1 || numeroMes > 12) {
                System.out.print("Mes invalido. Ingrese el mes nuevamente (1-12): ");
                numeroMes = scanner.nextInt();
            }
            Mes mes = Mes.values()[numeroMes - 1];
            System.out.print("Ingrese el dia: ");
            int dia = scanner.nextInt();
            while (dia < 1 || dia > 31) {
            	System.out.println("Dia no valido. Ingrese un da nuevamente (1-31)");
            	dia = scanner.nextInt();
            }
            System.out.print("Ingrese el detalle: ");
            scanner.nextLine();
            String detalle = scanner.nextLine();
            // CREAR LA EFEMERIDE Y AGREGARLA AL ARRAYLIST 
            efemerides.add(new Efemeride(codigo, mes, dia, detalle));
            System.out.println("Efemeride creada exitosamente.");
        } catch (InputMismatchException e) {
            System.out.println("Error. Ingrese un valor numerico para el codigo, el mes y el dia.");
            scanner.nextLine();
        }
    }
	public static void mostrarEfemerides(ArrayList<Efemeride> efemerides) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemerides para mostrar.");
        } else {
            System.out.println("Efemérides:");
            for (Efemeride efemeride : efemerides) {
            	System.out.println("****************************************");
                System.out.println("Codigo: "+ efemeride.getCodigo());
                System.out.println("Mes: "+ efemeride.getMes()); 
                System.out.println("Dia: " + efemeride.getDia());
                System.out.println("Detalle: "+ efemeride.getDetalle());
                System.out.println("****************************************");
            }
        }
    }
	
	public static void eliminarEfemeride(Scanner scanner, ArrayList<Efemeride> efemerides) {
	       if (efemerides.isEmpty()) {
	           System.out.println("No hay efemerides disponibles para eliminar.");
	       } else {
	           try {
	               System.out.print("Ingrese el codigo de la efemeride que desea eliminar eliminar: ");
	               int codigo = scanner.nextInt();
	               boolean eliminado = false;
	               // BUSCAR Y ELIMINAR EFEMERIDE POR EL CODIGO DADO
	               for (Efemeride efemeride : efemerides) {
	                   if (efemeride.getCodigo() == codigo) {
	                       efemerides.remove(efemeride);
	                       eliminado = true;
	                       System.out.println("Efeméride eliminada exitosamente.");
	                       break; //SALIR DEL BULCE UNA VEZ ELIMINADA LA EFEMERIDE
	                   }
	               }
	               if (!eliminado) {
	                   System.out.println("No se encontro ninguna efemeride con el codigo ingresado.");
	               }
	           } catch (InputMismatchException e) {
	               System.out.println("Error. Ingrese un valor numerico para el codigo.");
	               scanner.nextLine(); 
	           }
	        }
	    }
	
	public static void modificarEfemeride(Scanner scanner, ArrayList<Efemeride> efemerides) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemerides disponibles para modificar.");
        } else {
            try {
                System.out.print("Ingrese el codigo de la efeméride que desea modificar: ");
                int codigo = scanner.nextInt();
                boolean encontrado = false;
                // BUSCAR LA EFEMERIDE MEDIANTE EL CODIGO PROPORCIONADO
                for (Efemeride efemeride : efemerides) {
                    if (efemeride.getCodigo() == codigo) {
                        encontrado = true;
                        System.out.print("Ingrese el nuevo mes (1-12): ");
                        int numeroMes = scanner.nextInt();
                        while (numeroMes < 1 || numeroMes > 12) {
                            System.out.print("Mes invalido. Ingrese el mes nuevamente (1-12): ");
                            numeroMes = scanner.nextInt();
                        }
                        Mes mes = Mes.values()[numeroMes - 1];
                        efemeride.setMes(mes);
                        System.out.println("Ingrese el nuevo dia (1-31)");
                        int dia = scanner.nextInt();
                        while (dia < 1 || dia > 31) {
                        	System.out.println("Dia no valido. Ingrese un da nuevamente (1-31): ");
                        	dia = scanner.nextInt();
                        }
                        efemeride.setDia(dia);
                        System.out.println("Ingrese el detalle. ");
                        scanner.nextLine();
                        String detalle = scanner.nextLine();
                        efemeride.setDetalle(detalle);
                        System.out.println("EfemEride modificada exitosamente.");
                        break; // SALIR DEL BUCLE UNA VEZ MODIFICADA LA EFEMERIDE
                    }
                }
                if (!encontrado) {
                    System.out.println("No se encontro ninguna efemeride con el codigo proporcionado.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error. ingrese un valor numerico para el codigo y el nuevo mes.");
                scanner.nextLine();
            }
        }
    }
}
