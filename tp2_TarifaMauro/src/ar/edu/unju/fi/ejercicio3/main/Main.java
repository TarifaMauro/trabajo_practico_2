package ar.edu.unju.fi.ejercicio3.main;

import ar.edu.unju.fi.ejercicio3.constantes.Provincia;

public class Main {

	public static void main(String[] args) {
		Provincia[] provincias = Provincia.values();
		
		for (Provincia provincia : provincias) {
			String desnsidadPoblacional = String.format("%.1f", provincia.densidadPobl());
			System.out.println("Provincia: "+ provincia); 
			System.out.println("Cantidad de poblacion: "+ provincia.getCantPoblacion() + " Habitantes");
			System.out.println("Superficie: "+ provincia.getSuperficie() + " km2");
			System.out.println("Densidad poblacional: " + desnsidadPoblacional + " hab/km2");
			System.out.println();
		}
	}
}
