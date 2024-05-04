package ar.edu.nunju.fi.ejercicio6.main;

import ar.ede.unju.fi.ejercicio6.interfaces.funcionales.Converter;
import ar.edu.unju.fi.ejercicio6.model.FelinoDomestico;
import ar.edu.unju.fi.ejercicio6.model.FelinoSalvaje;

public class Main {

	public static void main(String[] args) {
		//DEFINICION DEL FELINO DOMESTICO GARFIIELD
		FelinoDomestico gato = new FelinoDomestico("Garfield" , (byte)45, 12.0f);
		
		//DEFINICION DE LA EXPRESION LAMNBDA QUE DEFINE EL CONVERT DE FELINODOMESTICO A FELINO SALVAJE
		Converter<FelinoDomestico, FelinoSalvaje> converter = x -> new FelinoSalvaje(x.getNombre(), x.getEdad(), x.getPeso());
		
		//CONVERSION
		FelinoSalvaje felino1 = converter.convert(gato);
		
		//MOSTRAR DATOS FELINO1
		converter.mostrarObjeto(felino1);
		
		//CONVERTIR UN FELINO SALVAJE A DOMESTICO
		
		FelinoSalvaje felinoSalvaje = new FelinoSalvaje("tanner", (byte) 20, 186f);
		
		//VERIFICACION DE QUE EL OBJETO NO ES NULO
		if(Converter.isNotNull(felinoSalvaje)) {
			//DEFINICION DE LA EXPRESION LAMBDA
			Converter<FelinoSalvaje, FelinoDomestico> converterReverse = x -> 
			new FelinoDomestico(x.getNombre(), x.getEdad(), x.getPeso());
			
			//CONVERSION
			
			FelinoDomestico felino2 = converterReverse.convert(felinoSalvaje);
			
			converterReverse.mostrarObjeto(felino2);
			
		}else {
			
			System.out.println("El objeto a convertir es nulo. ");
		}

	}

}
