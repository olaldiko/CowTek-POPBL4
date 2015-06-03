package JDBC;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.Statement;



public class JDBC{
	final String db = "COWTEK";
	final String dbmsurl = "jdbc:mysql://cowtek.mooo.com:3306/"+db;
	//final String dbmsurl = "jdbc:mysql://modra.olaldiko.mooo.com:3306";
	
	String userName;
	String password;
	Statement stmt = null;
	ResultSet result = null;
	Connection conn = null;
	
	public JDBC(String username, String password){
		this.userName = username;
		this.password = password;
	}
	
	public void updatedatabase (int opcion){
		opcion = 1;
		System.out.println("opcion: "+ opcion);
		switch (opcion){
		case 1:
			//addVaca(1065, "homer", new SimpleDateFormat("2001-10-09"));
			
				
			break;
		case 2:
			
			break;
		}
	}

	public void addRandomTemperature() throws SQLException {
		int i = 10, value=50;
		String sql;

		conn=getConnection();
		
		sql = "DELETE	FROM	sensor " +
				"WHERE	(sensorid <= 100)";
		ejecutarUpdate(sql);
		
		for (i=0; i<100; i++){
			value = (int)Math.floor(25+Math.random()*(38-25+1));
			System.out.println(value);
			
			sql = "INSERT INTO SENSOR (SENSORID, VALOR) " +
				 "VALUES ("+i+", "+value+")";
			
			
			ejecutarUpdate(sql);			
			
		}
		conn.close();
	}

	public ResultSet getStatements(){
		
		try{
			stmt = conn.createStatement();
			
		} catch (SQLException e) {
			System.out.println("Error en la statement:");
			System.out.println (e.getMessage());
		}
		return result;
		
	}
	
	public List<Double> readResultTemperatures() throws SQLException{
		
		conn=getConnection();
		
		List<Double> Temperatures = new ArrayList<>();
		String sql ="SELECT * FROM SENSOR";
		try {
			getStatements();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()){
				Temperatures.add((double)result.getInt("valor"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.close();
		return Temperatures;
		
	}
	
	public Connection getConnection() throws SQLException{
		
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		
		conn = DriverManager.getConnection(dbmsurl, connectionProps);
		return conn;
	}
	
	
	public int ejecutarUpdate(String sql) {
		int rowsAffected = 0;
		try {
			conn=getConnection();
			getStatements();
			rowsAffected = stmt.executeUpdate(sql);
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
		return rowsAffected;
		
	}

	public List<DatosEstacion> getDatosEstacion (){
		String sql = "SELECT * FROM DATOSESTACION";
		List<DatosEstacion> datosEstaciones = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){
				int unidadID = result.getInt("unidadID");
				int sensorID = result.getInt("sensorID");	
				int estacionID = result.getInt("estacionID");
				Date fechaHora = result.getDate("fechaHora");
				float valor = result.getFloat("valor");
				
				datosEstaciones.add(new DatosEstacion(sensorID, estacionID, fechaHora, valor, unidadID));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datosEstaciones;
	}
	
	public List<Comedero> getComederos() {
		String sql = "SELECT * FROM COMEDERO";
		List<Comedero> comederos = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){				
				int comederoID = result.getInt("comederoID");
				String descripcion = result.getString("descripcion");	
				int modeloID = result.getInt("modeloID");
				int estacionID = result.getInt("estacionID");
				
				comederos.add(new Comedero(comederoID, descripcion, modeloID, estacionID));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return comederos;
	}

	public List<ConsumoVaca> getConsumosVacas() {
		String sql = "SELECT * FROM CONSUMOVACA";
		List<ConsumoVaca> consumosVacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){				
				  
				int vacaID = result.getInt("vacaID");
				int comederoID= result.getInt("comederoID");	
				Date fechaHora = result.getDate("fechaHora");
				int cantidad = result.getInt("cantidad");
				
				consumosVacas.add(new ConsumoVaca(vacaID, comederoID, fechaHora, cantidad));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return consumosVacas;
	}

	public List<DatosVaca> getDatosVacas() {
	
	String sql = "SELECT * FROM DATOSESVACA";
	List<DatosVaca> datosVacas = new ArrayList<>();
	
	try {
		conn=getConnection();
		getStatements();
		result = stmt.executeQuery(sql);

		while (result.next()){
			int sensorID = result.getInt("sensorID");
			int vacaID = result.getInt("vacaID");	
			int unidadID = result.getInt("unidadID");
			Date fechaHora = result.getDate("fechaHora");
			float valor = result.getFloat("valor");
			
			datosVacas.add(new DatosVaca(sensorID, vacaID, fechaHora, valor, unidadID));

		}
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return datosVacas;
	}

	public List<Estacion> getEstaciones() {
		
		String sql = "SELECT * FROM ESTACION";
		List<Estacion> estaciones = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){
				int estacionID = result.getInt("estacionID");
				String nombre = result.getString("nombre");
				String descripcion = result.getString ("descripcion");
				
				estaciones.add(new Estacion(estacionID, nombre, descripcion) );

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return estaciones;
	}

	public List<Fabricante> getFabricantes() {
		
		String sql = "SELECT * FROM FABRICANTE";
		List<Fabricante> fabricantes = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){
				int fabricanteID = result.getInt("fabricanteID");
				String nombre = result.getString("nombre");
				String descripcion = result.getString ("descripcion");
				Date fechaCreacion = result.getDate ("fechaCreacion");
				
				fabricantes.add(new Fabricante(fabricanteID, nombre, descripcion, fechaCreacion));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fabricantes;
	}

	public List<Modelo> getModelos() {
		
		String sql = "SELECT * FROM MODELO";
		List<Modelo> modelos= new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){				
				int modeloID = result.getInt("modeloID");
				String nombre = result.getString("nombre");
				String descripcion = result.getString ("descripcion");
				float precio = result.getFloat("modeloID");
				Date fechaCreacion = result.getDate ("fechaCreacion");
				int fabricanteID = result.getInt("fabricanteID");
				
				modelos.add(new Modelo(modeloID, nombre, descripcion, precio, fechaCreacion, fabricanteID));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelos;
	}

	public List<Ordeñadero> getOrdeñaderos() {
		
		String sql = "SELECT * FROM ORDENADERO";
		List<Ordeñadero> ordeñaderos = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			
				
				int ordenaderoID = result.getInt("ordenaderoID");
				String descripcion = result.getString ("descripcion");
				int modeloID = result.getInt("modeloID");
				int estacionID = result.getInt("estacionID");
				
				ordeñaderos.add(new Ordeñadero(ordenaderoID, descripcion, modeloID, estacionID));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ordeñaderos;
	}

	public List<Placa> getPlacas() {
		
		String sql = "SELECT * FROM PLACA";
		List<Placa> placas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			
				int placaID = result.getInt("placaID");
				Date fechaCompra = result.getDate ("fechaCompra");
				int tipoPlacaID = result.getInt("tipoPlacaID");
				
				placas.add(new Placa(placaID, fechaCompra, tipoPlacaID));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return placas;
	}
	

	public List<PlacaEstacion> getPlacasEstaciones() {
		
		String sql = "SELECT * FROM PLACAESTACION";
		List<PlacaEstacion> placasEstaciones = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			
				int estacionID = result.getInt("estacionID");
				int placaID = result.getInt("placaID");
				Date fechaInicio = result.getDate ("fechaInicio");
				Date fechaFinal = result.getDate ("fechaFinal");
				
				placasEstaciones.add(new PlacaEstacion(estacionID, placaID, fechaInicio, fechaFinal));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return placasEstaciones;
	}

	public List<PlacaVaca> getPlacasVacas() {
		
		String sql = "SELECT * FROM PLACAVACA";
		List<PlacaVaca> placasVacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){		
				
				int vacaID = result.getInt("vacaID");
				int placaID = result.getInt("placaID");
				Date fechaInicio = result.getDate ("fechaInicio");
				Date fechaFinal = result.getDate ("fechaFinal");
				
				placasVacas.add(new PlacaVaca(vacaID, placaID, fechaInicio, fechaFinal));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return placasVacas;
	}

	public List<ProduccionVaca> getProduccionesVacas() {
		
		String sql = "SELECT * FROM PRODUCCIONVACA";
		List<ProduccionVaca> produccionesVacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){						  
				int vacaID = result.getInt("vacaID");
				int ordenaderoID = result.getInt("ordenaderoID");
				Date fechaHora = result.getDate ("fechaHora");
				float cantidad = result.getFloat ("cantidad");
				
				produccionesVacas.add(new ProduccionVaca(vacaID, ordenaderoID, fechaHora, cantidad));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return produccionesVacas;
	}

	public List<TipoPlaca> getTiposPlacas() {
		
		String sql = "SELECT * FROM TIPOPLACA";
		List<TipoPlaca> tiposPlacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){						
				int tipoPlacaID = result.getInt("tipoPlacaID");
				String descripcion = result.getString ("descripcion");
				int modeloID = result.getInt ("modeloID");
				
				tiposPlacas.add(new TipoPlaca(tipoPlacaID, descripcion, modeloID));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tiposPlacas;
	}

	public List<TipoSensor> getTiposSesores() {
		
		String sql = "SELECT * FROM TIPOSENSOR";
		List<TipoSensor> tiposSensores = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			
			  	
				int tipoSensorID = result.getInt("TipoSensorID");
				String descripcion = result.getString ("descripcion");
				int modeloID = result.getInt ("modeloID");
				
				tiposSensores.add(new TipoSensor(tipoSensorID, descripcion, modeloID));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tiposSensores;
	}

	public List<Unidad> getUnidades() {
		
		String sql = "SELECT * FROM UNIDAD";
		List<Unidad> unidades = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			

				int unidadID = result.getInt("unidadID");
				String nombre = result.getString ("nombre");
				
				unidades.add(new Unidad(unidadID, nombre));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return unidades;
	}

	public List<Vaca> getVacas() {
		
		String sql = "SELECT * FROM vaca";
		List<Vaca> vacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			

				int vacaID	 = result.getInt("vacaID");
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

}





