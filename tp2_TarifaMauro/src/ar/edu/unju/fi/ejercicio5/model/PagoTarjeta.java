package ar.edu.unju.fi.ejercicio5.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ar.edu.unju.fi.ejercicio5.interfaces.Pago;

public class PagoTarjeta implements Pago{
	private String numeroTarjeta;
	private LocalDate fechaPago;
	private double montoPagado;
	
	
	public PagoTarjeta(String numeroTarjeta, LocalDate fechaPago, double montoPagado) {
		this.numeroTarjeta = numeroTarjeta;
		this.fechaPago = fechaPago;
		this.montoPagado = montoPagado;
	}


	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}


	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}


	public LocalDate getFehaPago() {
		return fechaPago;
	}


	public void setFehaPago(LocalDate fehaPago) {
		this.fechaPago = fehaPago;
	}


	public double getMontoPagado() {
		return montoPagado;
	}


	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}
	
	
	@Override
	public void realizarPago(double monto) {
		this.montoPagado = monto * 1.15; // 15% DE RECARGA
		
	}
	
	@Override
	public void imprimirRecibo() { 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = fechaPago.format(formatter);
		System.out.println("Numero de tarjeta: " + numeroTarjeta);
		System.out.println("Fecha de pago: " + fechaFormateada);
		System.out.println("Monto pagado: " + "$"+montoPagado);
		}

}
