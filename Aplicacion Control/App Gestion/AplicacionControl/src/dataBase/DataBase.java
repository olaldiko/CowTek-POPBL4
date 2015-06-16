package dataBase;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


public class DataBase {

	
	public void iniciarPrograma() {
		Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
		fecha.toString();

		
		/*
		Fabricante fabricante = new Fabricante("Olimex", "Placas SUBdesarrolladas", fecha);
		fabricante.introducirEnBD();
		Unidad unidad = new Unidad("Grados Celsius");
		unidad.introducirEnBD();
		PlacaEstacion placaEstacion = new PlacaEstacion(1, 1, fecha, fecha);
		placaEstacion.introducirEnBD();
		PlacaVaca placavaca = new PlacaVaca(1, 1, fecha, fecha);
		placavaca.introducirEnBD();
		ConsumoVaca consumoVaca = new ConsumoVaca(1, 1, fecha, (float)13.65);
		consumoVaca.introducirEnBD();
		DatosVaca datosVaca = new DatosVaca(1, 1, fecha, 23, 1);
		datosVaca.introducirEnBD();
		DatosEstacion datosEstacion = new DatosEstacion(1, 1, fecha, 46, 1);
		datosEstacion.introducirEnBD();		
		Sensor sensor = new Sensor("prueba", 1, 1);
		sensor.introducirEnBD();
		Comedero comedero = new Comedero("prueba", 1, 1);
		comedero.introducirEnBD();
		Vaca vaca = new Vaca("Maribel", "Blanca y negra", fecha);
		vaca.introducirEnBD();
		*/
	}
	
	public void mostrarTodo() {
		mostrarVacas();
		mostrarTipoSensor();
		mostrarUnidad();
		mostrarModelos();
		mostrarPlacasVacas();
		mostrarOrdenaderos();
		mostrarPlacas();
		mostrarPlacaEstacion();
		mostrarProduccionVaca();
		mostrarTiposPlacas();
		mostrarfabricantes();
		mostrarEstaciones();
		mostrarConsumosVacas();
		mostrarDatosEstacion();
		mostrarComederos();
		mostrarDatosVaca();
	}

	private void mostrarVacas() {
		JDBC dbConnection = new JDBC ();
		List<Vaca> vacas= dbConnection.getVacas();
		Iterator<Vaca> itrVacas = vacas.iterator();
		while (itrVacas.hasNext()){
			System.out.println(itrVacas.next().toString());
			}
		
	}

	private void mostrarUnidad() {
		JDBC dbConnection = new JDBC ();
		List<Unidad> unidades= dbConnection.getUnidades();
		Iterator<Unidad> itrUnidades = unidades.iterator();
		while (itrUnidades.hasNext()){
			System.out.println(itrUnidades.next().toString());
			}
		}
		

	private void mostrarTipoSensor() {
		JDBC dbConnection = new JDBC ();
		List<TipoSensor> tiposSensores = dbConnection.getTiposSesores();
		Iterator<TipoSensor> itrTiposSensores = tiposSensores.iterator();
		while (itrTiposSensores.hasNext()){
			System.out.println(itrTiposSensores.next().toString());
			}
		}
	private void mostrarTiposPlacas() {
		JDBC dbConnection = new JDBC ();
		List<TipoPlaca> tiposPlacas = dbConnection.getTiposPlacas();
		Iterator<TipoPlaca> itrTiposPlacas = tiposPlacas.iterator();
		while (itrTiposPlacas.hasNext()){
			System.out.println(itrTiposPlacas.next().toString());
			}
		}


	private void mostrarPlacasVacas() {
		JDBC dbConnection = new JDBC ();
		List<PlacaVaca> placasVacas = dbConnection.getPlacasVacas();
		Iterator<PlacaVaca> itrPlacasVacas = placasVacas.iterator();
		while (itrPlacasVacas.hasNext()){
			System.out.println(itrPlacasVacas.next().toString());
			}
		}

	private void mostrarProduccionVaca() {
		JDBC dbConnection = new JDBC ();
		List<ProduccionVaca> produccionesVacas = dbConnection.getProduccionesVacas();
		Iterator<ProduccionVaca> itrProduccionesVacas = produccionesVacas.iterator();
		while (itrProduccionesVacas.hasNext()){
			System.out.println(itrProduccionesVacas.next().toString());
			}
		}

	private void mostrarPlacaEstacion() {
		JDBC dbConnection = new JDBC ();
		List<PlacaEstacion> placasEstaciones = dbConnection.getPlacasEstaciones();
		Iterator<PlacaEstacion> itrPlacasEstaciones = placasEstaciones.iterator();
		while (itrPlacasEstaciones.hasNext()){
			System.out.println(itrPlacasEstaciones.next().toString());
			}
		}

	private void mostrarPlacas() {
		JDBC dbConnection = new JDBC ();
		List<Placa> placas = dbConnection.getPlacas();
		Iterator<Placa> itrPlacas= placas.iterator();
		while (itrPlacas.hasNext()){
			System.out.println(itrPlacas.next().toString());
			}
		}

	private void mostrarOrdenaderos() {
		JDBC dbConnection = new JDBC ();
		List<Ordenadero> ordenaderos = dbConnection.getOrdenaderos();
		Iterator<Ordenadero> itrOrdenaderos = ordenaderos.iterator();
		while (itrOrdenaderos.hasNext()){
			System.out.println(itrOrdenaderos.next().toString());
			}
		}

	private void mostrarModelos() {
		JDBC dbConnection = new JDBC ();
		List<Modelo> modelos = dbConnection.getModelos();
		Iterator<Modelo> itrModelos = modelos.iterator();
		while (itrModelos.hasNext()){
			System.out.println(itrModelos.next().toString());
			}
		}

	private void mostrarfabricantes() {
		JDBC dbConnection = new JDBC ();
		List<Fabricante> faricantes = dbConnection.getFabricantes();
		Iterator<Fabricante> itrFabricantes = faricantes.iterator();
		while (itrFabricantes.hasNext()){
			System.out.println(itrFabricantes.next().toString());
			}
		}
	

	private void mostrarEstaciones() {
	
	JDBC dbConnection = new JDBC ();
	List<Estacion> estaciones = dbConnection.getEstaciones();
	Iterator<Estacion> itrEstaciones = estaciones.iterator();
	while (itrEstaciones.hasNext()){
		System.out.println(itrEstaciones.next().toString());
		}
	}

	private void mostrarDatosVaca() {
		  
		JDBC dbConnection = new JDBC ();
		List<DatosVaca> datosVacas = dbConnection.getDatosVacas();
		Iterator<DatosVaca> itrDatosVacas = datosVacas.iterator();
		while (itrDatosVacas.hasNext()){
			System.out.println(itrDatosVacas.next().toString());
		}
	}
	
	private void mostrarConsumosVacas() {
		  
		JDBC dbConnection = new JDBC ();
		List<ConsumoVaca> consumosVacas = dbConnection.getConsumosVacas();
		Iterator<ConsumoVaca> itrConsumoVacas = consumosVacas.iterator();
		while (itrConsumoVacas.hasNext()){
			System.out.println(itrConsumoVacas.next().toString());
		}
	}

	private void mostrarComederos() {
		JDBC dbConnection = new JDBC ();
		List<Comedero> comederos = dbConnection.getComederos();
		Iterator<Comedero> itrComedero = comederos.iterator();
		while (itrComedero.hasNext()){
			System.out.println(itrComedero.next().toString());
		}
		
	}

	private void mostrarDatosEstacion() {
		JDBC dbConnection = new JDBC ();
		List<DatosEstacion> datosEstaciones = dbConnection.getDatosEstacion();
		Iterator<DatosEstacion> itrEstaciones = datosEstaciones.iterator();
		while (itrEstaciones.hasNext()){
			System.out.println(itrEstaciones.next().toString());
		}
		
	}

}
