package ar.edu.unju.fi.ejercicio5.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ar.edu.unju.fi.ejercicio5.interfaces.Pago;

public class PagoEfectivo implements Pago {
	private LocalDate fechaPago;
	private double montoPagado;
	
	
	public PagoEfectivo(LocalDate fechaPago, double montoPagado) {
		this.fechaPago = fechaPago;
		this.montoPagado = montoPagado;
	}


	public LocalDate getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}


	public double getMontoPagado() {
		return montoPagado;
	}


	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}
		
	@Override
	public void realizarPago(double monto) {
		this.montoPagado = monto * 0.90; //10% DE DESCUENTO
	}
	
	@Override
	public void imprimirRecibo() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = fechaPago.format(formatter);
		System.out.println("Fecha de pago: " + fechaFormateada);
		System.out.println("Monto pagado: " + "$"+montoPagado);
	}

}
