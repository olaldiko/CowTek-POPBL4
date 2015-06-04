package analisisDatos;

import java.util.ArrayList;

public class AnalisisIngesta extends AnalisisDatos {

	ArrayList<Dato> datos;
	
	float a = 0;
	float b = 0;

	public AnalisisIngesta(ArrayList<Dato> datos) {
		super();
		this.datos = datos;
		realizarAnalisis();
	}
	
	private void realizarAnalisis(){
		ArrayList<Float> U = new ArrayList<Float>();
		ArrayList<Float> V = new ArrayList<Float>();
		
		float sumU = 0;
		float sumV = 0;
		float sumUU = 0;
		float sumUV = 0;
		
		float medU = 0;
		float medV = 0;
		
		for(int i = 0; i < datos.size(); i++){
			U.add((float) Math.log(datos.get(i).getValorEntrada()));
			V.add((float) Math.log(datos.get(i).getValorSalida()));
			sumU = sumU + U.get(i);
			sumV = sumV + V.get(i);
			sumUU = sumUU + (U.get(i)*U.get(i));
		    sumUV = sumUV + (U.get(i)*V.get(i));
		}
		
		medU = sumU/datos.size();
		medV = sumV/datos.size();
		
		b = ((sumUV/datos.size())-(medU*medV))/((sumUU/datos.size())-(medU*medU));
		float A = medV - b*medU;
		a = (float) Math.exp(A);
	}
	
}
