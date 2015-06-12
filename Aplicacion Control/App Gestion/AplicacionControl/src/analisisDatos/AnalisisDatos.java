package analisisDatos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dataBase.ConsumoVaca;
import dataBase.DatosVistaVaca;
import dataBase.JDBC;

public class AnalisisDatos {

	
	public void analizarTodo() {
		JDBC dbConnection = new JDBC ();
		List<DatosVistaVaca> produccionVacas = dbConnection.getVistaProduccionVaca();
		List<DatosVistaVaca> consumosVacas = dbConnection.getVistaConsumoVaca();
		List<DatosVistaVaca> temperaturaVacas = dbConnection.getVistaTempVaca();
		List<DatosVistaVaca> humedadVacas = dbConnection.getVistaHumVaca();
		
	}
	
	public void analizarIngesta(ArrayList<DatosVistaVaca> ingesta, ArrayList<DatosVistaVaca> produccion) {
		int i = 0;
		int vaca = 0;
		
		ArrayList<Dato> datosVaca;
		
		while(vaca < 1000){
			datosVaca  = new ArrayList<Dato>();
			while(ingesta.get(i) != null){
				if(ingesta.get(i).getVacaID() == vaca){
					while(produccion.get(i) != null) {
						if(produccion.get(i).getVacaID() == vaca){
							float diff = Math.abs(produccion.get(i).getFechaHora().getTime() - ingesta.get(i).getFechaHora().getTime());
							if(diff < 1000*60*60) datosVaca.add(new Dato(ingesta.get(i).getValor(), produccion.get(i).getValor()));
						}
					}
				}
			}
			AnalisisIngesta analisis = new AnalisisIngesta(datosVaca);
		}
	}
	
	public void analizarTemperatura(ArrayList<DatosVistaVaca> temperatura, ArrayList<DatosVistaVaca> produccion) {
		int i = 0;
		int vaca = 0;
		
		ArrayList<Dato> datosVaca;
		
		while(vaca < 1000){
			datosVaca  = new ArrayList<Dato>();
			while(temperatura.get(i) != null){
				if(temperatura.get(i).getVacaID() == vaca){
					while(produccion.get(i) != null) {
						if(produccion.get(i).getVacaID() == vaca){
							float diff = Math.abs(produccion.get(i).getFechaHora().getTime() - temperatura.get(i).getFechaHora().getTime());
							if(diff < 1000*60*60) datosVaca.add(new Dato(temperatura.get(i).getValor(), produccion.get(i).getValor()));
						}
					}
				}
			}
			AnalisisTemperatura analisis = new AnalisisTemperatura(datosVaca);
		}
	}
	
	public void analizarHumedad(ArrayList<DatosVistaVaca> humedad, ArrayList<DatosVistaVaca> produccion) {
		int i = 0;
		int vaca = 0;
		
		ArrayList<Dato> datosVaca;
		
		while(vaca < 1000){
			datosVaca  = new ArrayList<Dato>();
			while(humedad.get(i) != null){
				if(humedad.get(i).getVacaID() == vaca){
					while(produccion.get(i) != null) {
						if(produccion.get(i).getVacaID() == vaca){
							float diff = Math.abs(produccion.get(i).getFechaHora().getTime() - humedad.get(i).getFechaHora().getTime());
							if(diff < 1000*60*60) datosVaca.add(new Dato(humedad.get(i).getValor(), produccion.get(i).getValor()));
						}
					}
				}
			}
			AnalisisHumedad analisis = new AnalisisHumedad(datosVaca);
		}
	}
}
