package dataBase;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.sql.Statement;

/**
 * 	Clase para crear la conexion con la db mediante usuario y contraseña y leer las tablas 
 *  y vistas de la bd
 *  @author gorka
 *
 */

public class JDBC{
	final String db = "COWTEK";
	final String dbmsurl = "jdbc:mysql://cowtek.mooo.com:3306/"+db;
	
	String userName;
	String password;
	Statement stmt = null;
	ResultSet result = null;
	Connection conn = null;
	
	public JDBC(){
		this.userName = "gorkaram";
		this.password = "narvaja";
	}
	/**
	 * Funcion para crear un establecimiento con la BD
	 * @return Resultado
	 */
	public ResultSet getStatements(){
		
		try{
			stmt = conn.createStatement();
			
		} catch (SQLException e) {
			System.out.println("Error en la statement:");
			System.out.println (e.getMessage());
		}
		return result;
		
	}
	/**
	 * Conexion a la bd mediante usuario y contraseña
	 * @param user usuario
	 * @param password contraseña
	 * @return 	conexion
	 * @throws SQLException excepcion en la conexion
	 */
	public Connection getConnection(String user, String password) throws SQLException{
		this.userName = user ;
		this.password = password;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		
		conn = DriverManager.getConnection(dbmsurl, connectionProps);
		return conn;
	}
	/**
	 * Conexion a BD con usuario y contraseña ya preestablecidos
	 * @return connection conexion
	 * @throws SQLException error en la conexion
	 */
	public Connection getConnection() throws SQLException{
		
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		
		conn = DriverManager.getConnection(dbmsurl, connectionProps);
		return conn;
	}
	/**
	 * Ejecutar la SQL para actualizar la BD
	 * @param sql String del Query a ejecutar
	 * @return IDvaca actualizada
	 */
	public int ejecutarUpdate(String sql) {
		int insertedID = 0;
		try {
			conn=getConnection();
			getStatements();
			stmt.executeUpdate(sql);
			insertedID=getLastInsertID();
		} catch (SQLException e1) {
			e1.printStackTrace();										
		}finally{
			if (stmt!=null){
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					System.out.println (e.getMessage());
				}
			}
		}
		return insertedID;
		
	}

	/**
	 * Obtiene los datos de las estaciones
	 * @return datosEstacion
	 */
	public List<DatosEstacion> getDatosEstacion (){
		String sql = "SELECT * FROM datosEstacion";
		List<DatosEstacion> datosEstaciones = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){
				int unidadID = result.getInt("UnidadID");
				int sensorID = result.getInt("SensorID");	
				int estacionID = result.getInt("EstacionID");
				Date fechaHora = result.getDate("FechaHora");
				float valor = result.getFloat("Valor");
				
				datosEstaciones.add(new DatosEstacion(sensorID, estacionID, fechaHora, valor, unidadID));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datosEstaciones;
	}

	/**
	 * Obtiene los datos de las estaciones en funcion del nombre de las estaciones
	 * @param nombre Nombre de la estacion
	 * @return datos de estaciones
	 */
	
	public List<DatosEstacion> getDatosEstacionesporNombre(String nombre) {
		String sql = "SELECT  datEst.SensorID, datEst.EstacionID, datEst.FechaHora, datEst.Valor, datEst.UnidadID"
				+ " FROM		estacion est, datosEstacion datEst"
				+ " WHERE	est.EstacionID = datEst.EstacionID"
				+ " AND 	est.Nombre='"+ nombre+"'" ;
		
		System.out.println(sql);
		List<DatosEstacion> datosEstaciones = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){
				int unidadID = result.getInt("UnidadID");
				int sensorID = result.getInt("SensorID");	
				int estacionID = result.getInt("EstacionID");
				Date fechaHora = result.getDate("FechaHora");
				float valor = result.getFloat("Valor");
				
				datosEstaciones.add(new DatosEstacion(sensorID, estacionID, fechaHora, valor, unidadID));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(datosEstaciones);
		return datosEstaciones;
	}
	/**
	 * Obtener la informacion de los comederos
	 * @return comedero
	 */
	public List<Comedero> getComederos() {
		String sql = "SELECT * FROM comedero";
		List<Comedero> comederos = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){				
				int comederoID = result.getInt("ComederoID");
				String descripcion = result.getString("Descripcion");	
				int modeloID = result.getInt("ModeloID");
				int estacionID = result.getInt("EstacionID");
				
				comederos.add(new Comedero(comederoID, descripcion, modeloID, estacionID));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return comederos;
	}
	/**
	 * Obtener los datos de consumo de las vacas
	 * @return consumosvacas
	 */
	public List<ConsumoVaca> getConsumosVacas() {
		String sql = "SELECT * FROM consumoVaca";
		List<ConsumoVaca> consumosVacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){				
				  
				int vacaID = result.getInt("VacaID");
				int comederoID= result.getInt("ComederoID");	
				Date fechaHora = result.getDate("FechaHora");
				int cantidad = result.getInt("Cantidad");
				
				consumosVacas.add(new ConsumoVaca(vacaID, comederoID, fechaHora, cantidad));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return consumosVacas;
	}
	/**
	 * Obtener los datos de las vacas
	 * @return datos vacas
	 */
	public List<DatosVaca> getDatosVacas() {
	
	String sql = "SELECT * FROM datosVaca";
	List<DatosVaca> datosVacas = new ArrayList<>();
	
	try {
		conn=getConnection();
		getStatements();
		result = stmt.executeQuery(sql);

		while (result.next()){
			int sensorID = result.getInt("SensorID");
			int vacaID = result.getInt("VacaID");	
			int unidadID = result.getInt("UnidadID");
			Date fechaHora = result.getDate("FechaHora");
			float valor = result.getFloat("Valor");
			
			datosVacas.add(new DatosVaca(sensorID, vacaID, fechaHora, valor, unidadID));

		}
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	Iterator<DatosVaca> itrVacas = datosVacas.iterator();
	while (itrVacas.hasNext()){
		System.out.println(itrVacas.next().toString());
		}
	
	return datosVacas;
	}
	/**
	 * Obtener los datos de las estaciones
	 * @return estaciones
	 */
	public List<Estacion> getEstaciones() {
		
		String sql = "SELECT * FROM estacion";
		List<Estacion> estaciones = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){
				int estacionID = result.getInt("EstacionID");
				String nombre = result.getString("Nombre");
				String descripcion = result.getString ("Descripcion");
				
				estaciones.add(new Estacion(estacionID, nombre, descripcion) );

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return estaciones;
	}
	/**
	 * Obtener la informacion de los fabricantes
	 * @return fabricantes
	 */
	public List<Fabricante> getFabricantes() {
		
		String sql = "SELECT * FROM fabricante";
		List<Fabricante> fabricantes = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){
				int fabricanteID = result.getInt("FabricanteID");
				String nombre = result.getString("Nombre");
				String descripcion = result.getString ("Descripcion");
				Date fechaCreacion = result.getDate ("FechaCreacion");
				
				fabricantes.add(new Fabricante(fabricanteID, nombre, descripcion, fechaCreacion));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fabricantes;
	}
	/**
	 * Obtener la informacion de los modelos utilizados
	 * @return	modelos
	 */
	public List<Modelo> getModelos() {
		
		String sql = "SELECT * FROM modelo";
		List<Modelo> modelos= new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){				
				int modeloID = result.getInt("ModeloID");
				String nombre = result.getString("Nombre");
				String descripcion = result.getString ("Descripcion");
				float precio = result.getFloat("ModeloID");
				Date fechaCreacion = result.getDate ("FechaCreacion");
				int fabricanteID = result.getInt("FabricanteID");
				
				modelos.add(new Modelo(modeloID, nombre, descripcion, precio, fechaCreacion, fabricanteID));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelos;
	}
	/**
	 * Obtener la informacion de los ordenaderos
	 * @return ordeñaderos
	 */
	public List<Ordenadero> getOrdenaderos() {
		
		String sql = "SELECT * FROM ordenadero";
		List<Ordenadero> ordenaderos = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			
				
				int ordenaderoID = result.getInt("OrdenaderoID");
				String descripcion = result.getString ("Descripcion");
				int modeloID = result.getInt("ModeloID");
				int estacionID = result.getInt("EstacionID");
				
				ordenaderos.add(new Ordenadero(ordenaderoID, descripcion, modeloID, estacionID));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ordenaderos;
	}
	/**
	 * Obtener la informacion de las placas
	 * @return placas
	 */
	public List<Placa> getPlacas() {
		
		String sql = "SELECT * FROM placa";
		List<Placa> placas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			
				int placaID = result.getInt("PlacaID");
				Date fechaCompra = result.getDate ("FechaCompra");
				int tipoPlacaID = result.getInt("TipoPlacaID");
				
				placas.add(new Placa(placaID, fechaCompra, tipoPlacaID));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return placas;
	}
	/**
	 * Obtener la informacion de las placas de estaciones
	 * @return placas estaciones
	 */
	public List<PlacaEstacion> getPlacasEstaciones() {
		
		String sql = "SELECT * FROM placaEstacion";
		List<PlacaEstacion> placasEstaciones = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			
				int estacionID = result.getInt("EstacionID");
				int placaID = result.getInt("PlacaID");
				Date fechaInicio = result.getDate ("FechaInicio");
				Date fechaFinal = result.getDate ("FechaFinal");
				
				placasEstaciones.add(new PlacaEstacion(estacionID, placaID, fechaInicio, fechaFinal));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return placasEstaciones;
	}
	/**
	 * Obtener la informacino de las placas de las vacas
	 * @return placas vacas
	 */
	public List<PlacaVaca> getPlacasVacas() {
		
		String sql = "SELECT * FROM placaVaca";
		List<PlacaVaca> placasVacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){		
				
				int vacaID = result.getInt("VacaID");
				int placaID = result.getInt("PlacaID");
				Date fechaInicio = result.getDate ("FechaInicio");
				Date fechaFinal = result.getDate ("FechaFinal");
				
				placasVacas.add(new PlacaVaca(vacaID, placaID, fechaInicio, fechaFinal));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return placasVacas;
	}
	/**
	 * Obtener la informacion de las producciondes de cada vaca
	 * @return producciones de vacas
	 */
	public List<ProduccionVaca> getProduccionesVacas() {
		
		String sql = "SELECT * FROM produccionVaca";
		List<ProduccionVaca> produccionesVacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){						  
				int vacaID = result.getInt("VacaID");
				int ordenaderoID = result.getInt("OrdenaderoID");
				Date fechaHora = result.getDate ("FechaHora");
				float cantidad = result.getFloat ("Cantidad");
				
				produccionesVacas.add(new ProduccionVaca(vacaID, ordenaderoID, fechaHora, cantidad));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return produccionesVacas;
	}
	/**
	 * obtener la informacion de los tipos de placas
	 * @return tipos de placas
	 */
	public List<TipoPlaca> getTiposPlacas() {
		
		String sql = "SELECT * FROM tipoPlaca";
		List<TipoPlaca> tiposPlacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){						
				int tipoPlacaID = result.getInt("TipoPlacaID");
				String descripcion = result.getString ("Descripcion");
				int modeloID = result.getInt ("ModeloID");
				
				tiposPlacas.add(new TipoPlaca(tipoPlacaID, descripcion, modeloID));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tiposPlacas;
	}
	/**
	 * Obtener la informacion de los tipos de sensores
	 * @return tipos de sensores
	 */
	public List<TipoSensor> getTiposSesores() {
		
		String sql = "SELECT * FROM tipoSensor";
		List<TipoSensor> tiposSensores = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			
			  	
				int tipoSensorID = result.getInt("TipoSensorID");
				String descripcion = result.getString ("Descripcion");
				int modeloID = result.getInt ("ModeloID");
				
				tiposSensores.add(new TipoSensor(tipoSensorID, descripcion, modeloID));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tiposSensores;
	}
	/**
	 * Obtener las informacion de las unidades
	 * @return unidades
	 */
	public List<Unidad> getUnidades() {
		
		String sql = "SELECT * FROM unidad";
		List<Unidad> unidades = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			

				int unidadID = result.getInt("UnidadID");
				String nombre = result.getString ("Nombre");
				
				unidades.add(new Unidad(unidadID, nombre));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return unidades;
	}
	/**
	 * Obtener la informacion de las vacas
	 * @return vacas
	 */
	public List<Vaca> getVacas() {
		
		String sql = "SELECT * FROM vaca";
		List<Vaca> vacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			

				int vacaID	 = result.getInt("VacaID");
				String nombre = result.getString ("Nombre");
				String Raza = result.getString ("Raza");
				Date FechaNacimiento = result.getDate("FechaNacimiento");
				
				vacas.add(new Vaca(vacaID, nombre, Raza , FechaNacimiento));
			}
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vacas;
	}
	/**
	 * Obtener la informacion de las vacas en funcion de su nombre
	 * @param nombreVaca nombre de la vaca
	 * @return vacas por nombre
	 */
	public List<Vaca> getVacasporNomre(String nombreVaca) {
		String sql = "SELECT * FROM vaca"
				+ "	  WHERE Nombre='"+ nombreVaca+"'" ;
		List<Vaca> vacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			

				int vacaID	 = result.getInt("VacaID");
				String nombre = result.getString ("Nombre");
				String Raza = result.getString ("Raza");
				Date FechaNacimiento = result.getDate("FechaNacimiento");
				
				vacas.add(new Vaca(vacaID, nombre, Raza , FechaNacimiento));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vacas;
	}
		
	/**
	 * obtener la vista de ubicacion de las vacas
	 * @return vista de vacas
	 */
	
	public List<DatosVistaVaca> getVistaUbicacionVaca() {
		
		String sql = "SELECT * FROM ubicacionVaca_v";
		List<DatosVistaVaca> vacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			

				int vacaID	 = result.getInt("VacaID");
				Date fechaHora = result.getDate("FechaHora");
				float valor = result.getFloat("Valor");
				String unidad = result.getString ("Unidad");
				
				vacas.add(new DatosVistaVaca(vacaID, fechaHora, valor , unidad));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vacas;
	}
	/**
	 * obtener la vista de las temperaturas corporales de las vacas
	 * @return temperaturas corporales
	 */
	public List<DatosVistaVaca> getVistaTempCorpVaca() {
		
		String sql = "SELECT * FROM tempCorpVaca_v";
		List<DatosVistaVaca> vacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			
			while (result.next()){			

				int vacaID	 = result.getInt("VacaID");
				Date fechaHora = result.getDate("fechahora");
				float valor = result.getFloat("valor");
				
				vacas.add(new DatosVistaVaca(vacaID, fechaHora, valor , "ºC"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vacas;
	}
	/**
	 * obtener la vista de las temperatura ambietal
	 * @return vista de temperaturas
	 */
	public List<DatosVistaVaca> getVistaTempAmb() {
		
		String sql = "SELECT * FROM tempAmb_v";
		List<DatosVistaVaca> vacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			

				int vacaID	 = result.getInt("EstacionID");
				Date fechaHora = result.getDate("FechaHora");
				float valor = result.getFloat("Valor");
				String unidad = result.getString ("nombre");
				
				vacas.add(new DatosVistaVaca(vacaID, fechaHora, valor , unidad));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vacas;
	}
	/**
	 * obtener la vista de la humedad ambiental
	 * @return vistas humedad
	 */
	public List<DatosVistaVaca> getVistaHumAmb() {
		
		String sql = "SELECT * FROM humAmb_v";
		List<DatosVistaVaca> vacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);
	
			while (result.next()){			
	
				int vacaID	 = result.getInt("EstacionID");
				Date fechaHora = result.getDate("FechaHora");
				float valor = result.getFloat("Valor");
				String unidad = result.getString ("nombre");
				
				vacas.add(new DatosVistaVaca(vacaID, fechaHora, valor , unidad));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return vacas;
	}
	/**
	 * Obtener la vista del consumo de pienso de las vacas
	 * @return vistas consumos de vacas
	 */
	public List<DatosVistaVaca> getVistaConsumoVaca() {
		
		String sql = "SELECT * FROM consumoVaca";
		List<DatosVistaVaca> consumo = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);
			
			while (result.next()){
	
				int vacaID	 = result.getInt("VacaID");
				Date fechaHora = result.getDate("fechahora");
				float valor = result.getFloat("Cantidad");
				
				consumo.add(new DatosVistaVaca(vacaID, fechaHora, valor , "Kg"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return consumo;
	}
	/**
	 * obtener la vista de la produccion de las vacas
	 * @return vista producciones de vacas
	 */
	public List<DatosVistaVaca> getVistaProduccionVaca() {
		
		String sql = "SELECT * FROM produccionVaca";
		List<DatosVistaVaca> produccion = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);
			
			while (result.next()){			
	
				int vacaID	 = result.getInt("VacaID");
				int ordenaderoID = result.getInt("OrdenaderoID");
				Date fechaHora = result.getDate("FechaHora");
				float valor = result.getFloat("Cantidad");
				
				produccion.add(new DatosVistaVaca(vacaID, fechaHora, valor , "L"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return produccion;
	}
	/**
	 * obtener el ID del ultimo INSERT echo en la BD
	 * @return ultimo ID insertado
	 */
	public int getLastInsertID() {
		int vacaID = -1 ;
		String sql = "SELECT LAST_INSERT_ID() ;";
		try {
			result = stmt.executeQuery(sql);
			
			while (result.next()){	
				vacaID	 = result.getInt(1);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return vacaID;
	}
	/**
	 * Borra la vaca de la BD
	 * @param vacaID id de la vaca a eliminar
	 * @return vacaID borrada
	 */
	public int borrarVaca(int vacaID) {
		String sql = "DELETE FROM vaca"
				+ "WHERE vacaID = "+vacaID+";";
		try {
			result = stmt.executeQuery(sql);
			
			while (result.next()){	
				vacaID	 = result.getInt(1);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return vacaID;
	}

	/*public void addRandomTemperature() throws SQLException {
	int i = 10, value=50;
	String sql;

	conn=getConnection();
	
	sql = "DELETE	FROM	sensor " +
			"WHERE	(SensorID <= 100)";
	ejecutarUpdate(sql);
	
	for (i=0; i<100; i++){
		value = (int)Math.floor(25+Math.random()*(38-25+1));
		System.out.println(value);
		
		sql = "INSERT INTO sensor (SensorID, Valor) " +
			 "VALUES ("+i+", "+value+")";
		
		
		ejecutarUpdate(sql);			
		
	}
	conn.close();
}
*/

	/*public List<Double> readResultTemperatures() throws SQLException{
		
		conn=getConnection();
		
		List<Double> Temperatures = new ArrayList<>();
		String sql ="SELECT * FROM sensor";
		try {
			getStatements();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()){
				Temperatures.add((double)result.getInt("Valor"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.close();
		return Temperatures;
		
	}*/
}
