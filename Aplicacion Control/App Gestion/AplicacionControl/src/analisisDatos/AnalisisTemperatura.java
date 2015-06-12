package analisisDatos;

import java.util.ArrayList;
import java.util.Arrays;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import Jama.Matrix;

public class AnalisisTemperatura {

	ArrayList<Dato> datos;
	String ecuacion;
	String dEcuacion;

	public AnalisisTemperatura(ArrayList<Dato> datos) {
		super();
		this.datos = datos;
		//realizarAnalisis();
	}

	private void realizarAnalisis() {
		
		float sumX = 0;
		float sumY = 0;
		
		float sumXX = 0;
		float sumXY = 0;
		
		float sumXXX = 0;
		float sumXXY = 0;
		
		float sumXXXX = 0;
		
		for(int i = 0; i < datos.size(); i++){
			sumXY = sumXY + (datos.get(i).getValorEntrada()*datos.get(i).getValorSalida());
		    sumXX = sumXX + (datos.get(i).getValorEntrada()*datos.get(i).getValorEntrada());
		    sumXXX = sumXXX + (datos.get(i).getValorEntrada()*datos.get(i).getValorEntrada()*datos.get(i).getValorEntrada());
		    sumXXY = sumXXY + (datos.get(i).getValorEntrada()*datos.get(i).getValorEntrada()*datos.get(i).getValorSalida());
		    sumXXXX = sumXXXX + (datos.get(i).getValorEntrada()*datos.get(i).getValorEntrada()*datos.get(i).getValorEntrada()*datos.get(i).getValorEntrada());
		}
		
		double[][] matrix = {{1, sumX, sumXX, },{sumX, sumXX, sumXXX},{sumXX, sumXXX, sumXXXX}};
		double[][] array = {{sumY}, {sumXY}, {sumXXY}};
		
		Matrix A = new Matrix(matrix);
		Matrix b = new Matrix(array);
		
		Matrix x = A.solve(b);
		
		ecuacion = "y = " + x.get(0, 0) + " + " + x.get(1, 0) + "*x + " + x.get(2, 0) + "*x^2";
		
		dEcuacion = derivada(ecuacion);
		
	}
	
	public String derivada(String funcion){
		String derivada = "";
		
		DJep j = new DJep();
		//DJep es la clase encargada de la derivacion en su escencia
		j.addStandardConstants();
		//agrega constantes estandares, pi, e, etc
		j.addStandardFunctions();
		//agrega funciones estandares cos(x), sin(x)
		j.addComplex();
		//por si existe algun numero complejo
		j.setAllowUndeclared(true);
		//permite variables no declarables
		j.setAllowAssignment(true);
		//permite asignaciones
		j.setImplicitMul(true);
		//regla de multiplicacion o para sustraccion y sumas
		j.addStandardDiffRules();
		
		try{
			//coloca el nodo con una funcion preestablecida
			Node node = j.parse(funcion);
			//deriva la funcion con respecto a x
			Node diff = j.differentiate(node,"x");
			//Simplificamos la funcion con respecto a x
			Node simp = j.simplify(diff);
			//Convertimos el valor simplificado en un String
			derivada =j.toString(simp);
			//imprime la funci�n
			j.println(simp);
		} catch(ParseException e){ 
			e.printStackTrace();
		}
		
		return derivada;
	}

	public ArrayList<Dato> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<Dato> datos) {
		this.datos = datos;
	}

	public String getEcuacion() {
		return ecuacion;
	}

	public void setEcuacion(String ecuacion) {
		this.ecuacion = ecuacion;
	}

	public String getdEcuacion() {
		return dEcuacion;
	}

	public void setdEcuacion(String dEcuacion) {
		this.dEcuacion = dEcuacion;
	}
	
	

}
