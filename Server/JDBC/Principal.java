package JDBC;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import com.mysql.fabric.FabricStateResponse;


public class Principal {

	public static void main(String[] args) {
		Principal demo = new Principal();
		demo.iniciarPrograma();

	}

	private void iniciarPrograma() {
		Date fecha = new Date(15, 5, 21);
		
		mostrarVacas();
		
		/*
		mostrarTipoSensor();
		mostrarUnidad();
		mostrarModelos();
		mostrarPlacasVacas();
		mostrarOrdeñaderos();
		mostrarPlacas();
		mostrarPlacaEstacion();
		mostrarProduccionVaca();
		mostrarTiposPlacas();
		mostrarfabricantes();
		mostrarEstaciones();
		mostrarDatosEstacion();
		mostrarConsumosVacas();
		mostrarDatosEstacion();
		mostrarComederos();
		Fabricante fabricante = new Fabricante("Olimex", "Placas SUBdesarrolladas", fecha);
		fabricante.intoducirEnBD();
		Unidad unidad = new Unidad("Grados Celsius");
		unidad.intoducirEnBD();
		PlacaEstacion placaEstacion = new PlacaEstacion(1, 1, fecha, fecha);
		placaEstacion.intoducirEnBD();
		PlacaVaca placavaca = new PlacaVaca(1, 1, fecha, fecha);
		placavaca.intoducirEnBD();
		ConsumoVaca consumoVaca = new ConsumoVaca(1, 1, fecha, (float)13.65);
		consumoVaca.intoducirEnBD();
		DatosVaca datosVaca = new DatosVaca(1, 1, fecha, 23, 1);
		datosVaca.intoducirEnBD();
		DatosEstacion datosEstacion = new DatosEstacion(1, 1, fecha, 46, 1);
		datosEstacion.intoducirEnBD();		
		Sensor sensor = new Sensor("prueba", 1, 1);
		sensor.intoducirEnBD();
		Comedero comedero = new Comedero("prueba", 1, 1);
		comedero.intoducirEnBD();
		Vaca vaca = new Vaca("Maribel", "Blanca y negra", fecha);
		vaca.intoducirEnBD(); 
		*/
	}

	private void mostrarVacas() {
		JDBC dbConnection = new JDBC ("gorkaram","narvaja");
		List<Vaca> vacas= dbConnection.getVacas();
		Iterator<Vaca> itrVacas = vacas.iterator();
		while (itrVacas.hasNext()){
			System.out.println(itrVacas.next().toString());
			}
		
	}

	private void mostrarUnidad() {
		JDBC dbConnection = new JDBC ("root","");
		List<Unidad> unidades= dbConnection.getUnidades();
		Iterator<Unidad> itrUnidades = unidades.iterator();
		while (itrUnidades.hasNext()){
			System.out.println(itrUnidades.next().toString());
			}
		}
		

	private void mostrarTipoSensor() {
		JDBC dbConnection = new JDBC ("root", "");
		List<TipoSensor> tiposSensores = dbConnection.getTiposSesores();
		Iterator<TipoSensor> itrTiposSensores = tiposSensores.iterator();
		while (itrTiposSensores.hasNext()){
			System.out.println(itrTiposSensores.next().toString());
			}
		}
	private void mostrarTiposPlacas() {
		JDBC dbConnection = new JDBC ("root","");
		List<TipoPlaca> tiposPlacas = dbConnection.getTiposPlacas();
		Iterator<TipoPlaca> itrTiposPlacas = tiposPlacas.iterator();
		while (itrTiposPlacas.hasNext()){
			System.out.println(itrTiposPlacas.next().toString());
			}
		}


	private void mostrarPlacasVacas() {
		JDBC dbConnection = new JDBC ("root","");
		List<PlacaVaca> placasVacas = dbConnection.getPlacasVacas();
		Iterator<PlacaVaca> itrPlacasVacas = placasVacas.iterator();
		while (itrPlacasVacas.hasNext()){
			System.out.println(itrPlacasVacas.next().toString());
			}
		}

	private void mostrarProduccionVaca() {
		JDBC dbConnection = new JDBC ("root","");
		List<ProduccionVaca> produccionesVacas = dbConnection.getProduccionesVacas();
		Iterator<ProduccionVaca> itrProduccionesVacas = produccionesVacas.iterator();
		while (itrProduccionesVacas.hasNext()){
			System.out.println(itrProduccionesVacas.next().toString());
			}
		}

	private void mostrarPlacaEstacion() {
		JDBC dbConnection = new JDBC ("root","");
		List<PlacaEstacion> placasEstaciones = dbConnection.getPlacasEstaciones();
		Iterator<PlacaEstacion> itrPlacasEstaciones = placasEstaciones.iterator();
		while (itrPlacasEstaciones.hasNext()){
			System.out.println(itrPlacasEstaciones.next().toString());
			}
		}

	private void mostrarPlacas() {
		JDBC dbConnection = new JDBC ("root","");
		List<Placa> placas = dbConnection.getPlacas();
		Iterator<Placa> itrPlacas= placas.iterator();
		while (itrPlacas.hasNext()){
			System.out.println(itrPlacas.next().toString());
			}
		}

	private void mostrarOrdeñaderos() {
		JDBC dbConnection = new JDBC ("root","");
		List<Ordeñadero> ordeñaderos = dbConnection.getOrdeñaderos();
		Iterator<Ordeñadero> itrOrdeñaderos = ordeñaderos.iterator();
		while (itrOrdeñaderos.hasNext()){
			System.out.println(itrOrdeñaderos.next().toString());
			}
		}

	private void mostrarModelos() {
		JDBC dbConnection = new JDBC ("root","");
		List<Modelo> modelos = dbConnection.getModelos();
		Iterator<Modelo> itrModelos = modelos.iterator();
		while (itrModelos.hasNext()){
			System.out.println(itrModelos.next().toString());
			}
		}

	private void mostrarfabricantes() {
		JDBC dbConnection = new JDBC ("root","");
		List<Fabricante> faricantes = dbConnection.getFabricantes();
		Iterator<Fabricante> itrFabricantes = faricantes.iterator();
		while (itrFabricantes.hasNext()){
			System.out.println(itrFabricantes.next().toString());
			}
		}
	

	private void mostrarEstaciones() {
	
	JDBC dbConnection = new JDBC ("root","");
	List<Estacion> estaciones = dbConnection.getEstaciones();
	Iterator<Estacion> itrEstaciones = estaciones.iterator();
	while (itrEstaciones.hasNext()){
		System.out.println(itrEstaciones.next().toString());
		}
	}

	private void mostrarDatosVaca() {
		  
		JDBC dbConnection = new JDBC ("root","");
		List<DatosVaca> datosVacas = dbConnection.getDatosVacas();
		Iterator<DatosVaca> itrDatosVacas = datosVacas.iterator();
		while (itrDatosVacas.hasNext()){
			System.out.println(itrDatosVacas.next().toString());
		}
	}
	
	private void mostrarConsumosVacas() {
		  
		JDBC dbConnection = new JDBC ("root","");
		List<ConsumoVaca> consumosVacas = dbConnection.getConsumosVacas();
		Iterator<ConsumoVaca> itrConsumoVacas = consumosVacas.iterator();
		while (itrConsumoVacas.hasNext()){
			System.out.println(itrConsumoVacas.next().toString());
		}
	}

	private void mostrarComederos() {
		JDBC dbConnection = new JDBC ("root","");
		List<Comedero> comederos = dbConnection.getComederos();
		Iterator<Comedero> itrComedero = comederos.iterator();
		while (itrComedero.hasNext()){
			System.out.println(itrComedero.next().toString());
		}
		
	}

	private void mostrarDatosEstacion() {
		JDBC dbConnection = new JDBC ("root","");
		List<DatosEstacion> datosEstaciones = dbConnection.getDatosEstacion();
		Iterator<DatosEstacion> itrEstaciones = datosEstaciones.iterator();
		while (itrEstaciones.hasNext()){
			System.out.println(itrEstaciones.next().toString());
		}
		
	}

}
