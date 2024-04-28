package ar.edu.unju.fi.ejercicio3.constantes;

public enum Provincia {
	JUJUY(770881, 52244),
	SALTA(1424397, 155340),
	TUCUMAN(1694656, 22592),
	CATAMARCA(415438, 101486),
	LA_RIOJA(393531, 91493),
	SANTIAGO_DEL_ESTERO(978313, 136934);
	
	//ATRIBUTOS
	
	private int cantPoblacion;
	private int superficie;
	
	//CONSTRUCTOR 
	
	
	private Provincia(int cantPoblacion, int superficie) {
		this.cantPoblacion = cantPoblacion;
		this.superficie = superficie;
	}
	
	//GETTERS AND SETTERS

	public int getCantPoblacion() {
		return cantPoblacion;
	}

	public void setCantPoblacion(int cantPoblacion) {
		this.cantPoblacion = cantPoblacion;
	}

	public int getSuperficie() {
		return superficie;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	
	//CALCULO DE LA DENSIDAD POBLACIONAL.
	
	public double densidadPobl() {
		return (double) cantPoblacion / superficie;
	}
	
	
	

}
