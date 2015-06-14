package analisisDatos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dataBase.ConsumoVaca;
import dataBase.DatosVistaVaca;
import dataBase.JDBC;
/**
 * analiza los datos de produccion e funcion del consumo, temperatura y humedad
 * @author gorka
 *
 */
public class AnalisisDatos {
	
	AnalisisIngesta aIng;
	AnalisisTemperatura aTemp;
	AnalisisHumedad aHum;
	
	/**
	 * analiza los tres factores: consumo temperatura y humedad
	 */
	public void analizarTodo() {
		JDBC dbConnection = new JDBC ();
		List<DatosVistaVaca> produccionVacas = dbConnection.getVistaProduccionVaca();
		List<DatosVistaVaca> consumoVacas = dbConnection.getVistaConsumoVaca();
		List<DatosVistaVaca> temperaturaVacas = dbConnection.getVistaTempAmb();
		List<DatosVistaVaca> humedadVacas = dbConnection.getVistaHumAmb();
		
		aIng = new AnalisisIngesta(sacarDatos((ArrayList<DatosVistaVaca>) consumoVacas, (ArrayList<DatosVistaVaca>) produccionVacas));
		System.out.println("Ingesta: a = " + aIng.getA() + "; b = " + aIng.getB() + ";");
		
		aTemp = new AnalisisTemperatura(sacarDatosEst((ArrayList<DatosVistaVaca>) temperaturaVacas, (ArrayList<DatosVistaVaca>) produccionVacas));
		System.out.println("Temperatura: " + aTemp.getEcuacion());
		
		aHum = new AnalisisHumedad(sacarDatosEst((ArrayList<DatosVistaVaca>) humedadVacas, (ArrayList<DatosVistaVaca>) produccionVacas));
		System.out.println("Humedad: " + aHum.getEcuacion());
	}
	
	/**
	 * Obtiene los datos
	 * @param entrada entrada
	 * @param salida salida
	 * @return datos
	 */
	public ArrayList<Dato> sacarDatos(ArrayList<DatosVistaVaca> entrada, ArrayList<DatosVistaVaca> salida) {

		ArrayList<Dato> datosVaca = new ArrayList<Dato>();
		
		for(int i = 0; i < salida.size(); i++) {
			
			long diffSal = System.currentTimeMillis() - salida.get(i).getFechaHora().getTime();
			if (diffSal < 1000*60*60*24*365) {
			
				for(int z = 0; z < entrada.size(); z++) {
					
					if (salida.get(i).getVacaID() == entrada.get(z).getVacaID()) {
						
						long diff = salida.get(i).getFechaHora().getTime() - entrada.get(z).getFechaHora().getTime();
						diff = Math.abs(diff);
						
						if (diff < 1000*60*60*24) {
							datosVaca.add(new Dato(entrada.get(z).getValor(), salida.get(i).getValor()));
						}
						
					}
					
				}
			
			}
			
		}
		return datosVaca;
	}
	
	public ArrayList<Dato> sacarDatosEst(ArrayList<DatosVistaVaca> entrada, ArrayList<DatosVistaVaca> salida) {

		ArrayList<Dato> datosVaca = new ArrayList<Dato>();
		
		for(int i = 0; i < salida.size(); i++) {
			
			long diffSal = System.currentTimeMillis() - salida.get(i).getFechaHora().getTime();
			
			if (diffSal < 1000*60*60*24*365) {
				for(int z = 0; z < entrada.size(); z++) {
					
					long diff = salida.get(i).getFechaHora().getTime() - entrada.get(z).getFechaHora().getTime();
					diff = Math.abs(diff);
					if (diff < 1000*60*60*24) {
						datosVaca.add(new Dato(entrada.get(z).getValor(), salida.get(i).getValor()));
					}
					
				}
			
			}
			
		}
		return datosVaca;
	}

	public AnalisisIngesta getaIng() {
		return aIng;
	}

	public void setaIng(AnalisisIngesta aIng) {
		this.aIng = aIng;
	}

	public AnalisisTemperatura getaTemp() {
		return aTemp;
	}

	public void setaTemp(AnalisisTemperatura aTemp) {
		this.aTemp = aTemp;
	}

	public AnalisisHumedad getaHum() {
		return aHum;
	}

	public void setaHum(AnalisisHumedad aHum) {
		this.aHum = aHum;
	}
	
}
