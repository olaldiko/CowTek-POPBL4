package dataBase;

import java.sql.Date;


public class Modelo{
	int modeloID;
	String nombre;
	String descripcion;
	float precio;
	Date fechaCreacion;
	int fabricanteID;
	
	public Modelo(String nombre, String descripcion, float precio, Date fechaCreacion, int fabricanteID){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fechaCreacion = fechaCreacion;
		this.fabricanteID = fabricanteID;
	}
	
	public Modelo(int modeloID, String nombre, String descripcion,
			float precio, Date fechaCreacion, int fabricanteID) {
			this.modeloID = this.modeloID;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.precio = precio;
			this.fechaCreacion = fechaCreacion;
			this.fabricanteID = fabricanteID;
	}

	
	public int introducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ();

		String sql = "INSERT INTO MODELO(NOMBRE, DESCRIPCION, PRECIO, FECHACREACION, FABRICANTEID) " +
				 "VALUES ('"+nombre+"', '"+descripcion+"', '"+precio+"',"
				 		+ "'"+fechaCreacion+"', '"+fabricanteID+"');";
	
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
		
	}

	
	public int getModeloID() {
		return modeloID;
	}

	public void setModeloID(int modeloID) {
		this.modeloID = modeloID;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getFabricanteID() {
		return fabricanteID;
	}

	public void setFabricanteID(int fabricanteID) {
		this.fabricanteID = fabricanteID;
	}	
	@Override
	public String toString() {
		return "Modelo [modeloID=" + modeloID + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", precio=" + precio
				+ ", fechaCreacion=" + fechaCreacion + ", fabricanteID="
				+ fabricanteID + "]";
	}

	
}