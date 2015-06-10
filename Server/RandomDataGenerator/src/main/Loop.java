package main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

import dataBase.ConsumoVaca;
import dataBase.DatosEstacion;
import dataBase.DatosVaca;
import dataBase.Placa;
import dataBase.PlacaEstacion;
import dataBase.PlacaVaca;
import dataBase.ProduccionVaca;
import dataBase.Sensor;
import dataBase.Vaca;

public class Loop {

	public void init() {
		Random generator = new Random();
		
		double humedad = 0;
		double temperatura = 0;
		double ingesta = 0;
		double produccion = 0;
		
		float cold = 0;
		
		ArrayList<Integer> cowList = new ArrayList<Integer>();
		System.out.println("Creating cows...");
		for(int cow = 1; cow < 100; cow++) {
			Placa placa = new Placa(new Date(System.currentTimeMillis()), 2);
			int placaID = placa.introducirEnBD();
			
			Vaca vaca = new Vaca("Cow" + cow, "Raza", new Date(System.currentTimeMillis()));
			int vacaID = vaca.introducirEnBD();
			
			cowList.add(vacaID);
			
			PlacaVaca placaVaca = new PlacaVaca(vacaID, placaID, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
			placaVaca.introducirEnBD();
			Sensor sensor_GPS_latitud = new Sensor("GPD latitud", placaID, 4);
			sensor_GPS_latitud.introducirEnBD();
			Sensor sensor_GPS_longitud = new Sensor("GPD longitud", placaID, 5);
			sensor_GPS_longitud.introducirEnBD();
			Sensor sensor_T = new Sensor("Temperatura corp.", placaID, 3);
			sensor_T.introducirEnBD();
		}
		
		Placa placa = new Placa(new Date(System.currentTimeMillis()), 1);
		int placaID = placa.introducirEnBD();
		
		PlacaEstacion placaEstacion = new PlacaEstacion(1, placaID, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		placaEstacion.introducirEnBD();
		
		
		System.out.println("Cows created");
		
		for(int year = 2000; year < 2001; year++) {
			System.out.println("Year: " + year);
			
			for(int month = 1; month <= 12; month++) {
				System.out.println("Month: " + month);
				
				cold = (12 - month)/10;
				
				for(int day = 1; day <= 31; day++) {
					
					if (month == 2){
						if (year % 4 != 0) {
							if (day == 29) break;
						}
						if (day == 30) break;
					}
					if ((month == 4)||(month == 6)||(month == 9)||(month == 11)){
						if (day == 31) break;
					}
					
					Date date = new Date(year - 1970, month, day);

					System.out.println(date.toString());
					
					humedad = ((60 + generator.nextInt(20))*(0.5 + cold))*(0.9*(generator.nextInt(20)/10));
					temperatura = (generator.nextInt(30)*(1.5 - cold))*(0.9*(generator.nextInt(20)/10));
					
					DatosEstacion datosEstacionT = new DatosEstacion(1, 1, date, (int)temperatura, 6);
					datosEstacionT.introducirEnBD();
					DatosEstacion datosEstacionH = new DatosEstacion(2, 1, date, (int)humedad, 7);
					datosEstacionH.introducirEnBD();
					
					for(int cow = 0; cow < cowList.size(); cow++){
						
						ingesta = 10 + generator.nextInt(10);
						produccion = (10 - (Math.abs(60 - humedad)/100) - (Math.abs(18 - temperatura)/100) + ((ingesta*(70 + generator.nextInt(30))/100))*(80 + generator.nextInt(20)/100))/20;
						ConsumoVaca consumoVaca = new ConsumoVaca(cowList.get(cow), 1, date, (int)ingesta);
						consumoVaca.introducirEnBD();
						ProduccionVaca produccionVaca = new ProduccionVaca(cowList.get(cow), 1, date, (int)produccion);
						produccionVaca.introducirEnBD();
						
					}
					
				}
				
			}
			
		}
		
	}
	
	

}
