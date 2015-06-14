package dataBase;

import java.sql.Date;

/**
 * clase para crear estaciones e introducirlos en la db
 * @author gorka
 *
 */
public class Estacion{
	int estacionID;
	String nombre;
	String descripcion;
	Double latitud ;
	Double longitud ;
	
	public Estacion(String nombre, String descripcion){
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Estacion(int estacionID, String nombre, String descripcion, Double latitud, Double longitud) {
		this.estacionID = estacionID;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public int introducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ();
		
		String sql = "INSERT INTO estacion (Nombre, Descripcion) " +
				 "VALUES ('"+nombre+"','"+descripcion+"');";
		
		System.out.println(sql);
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
	}

	@Override
	public String toString() {
		return "Estacion [estacionID=" + estacionID + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + "]";
	}

	public int getEstacionID() {
		return estacionID;
	}

	public void setEstacionID(int estacionID) {
		this.estacionID = estacionID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}


}