package dataBase;

import java.sql.Date;


public class Estacion{
	int estacionID;
	String nombre;
	String descripcion;
	double latitud;
	double longitud;
	
	public Estacion(String nombre, String descripcion, double latitud, double longitud){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public Estacion(int estacionID, String nombre, String descripcion) {
		this.estacionID = estacionID;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int introducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ();
		
		String sql = "INSERT INTO estacion (Nombre, Descripcion, Latitud, Longitud) " +
				 "VALUES ('"+nombre+"','"+descripcion+"','"+latitud+"','"+longitud+"');";
		
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

}