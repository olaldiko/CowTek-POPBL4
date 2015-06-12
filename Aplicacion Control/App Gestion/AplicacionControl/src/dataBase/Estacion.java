package dataBase;

import java.sql.Date;


public class Estacion{
	int estacionID;
	String nombre;
	String descripcion;
	
	public Estacion(String nombre, String descripcion){
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Estacion(int estacionID, String nombre, String descripcion) {
		this.estacionID = estacionID;
		this.nombre = nombre;
		this.descripcion = descripcion;
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

}