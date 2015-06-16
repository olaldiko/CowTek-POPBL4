package dataBase;

import java.sql.Date;
/**
 * clase para crear DatosVistaVaca e introducirlos en la db
 * @author gorka
 *
 */
public class VistaDatos {
	int elementoID;
	Date fechaHora;
	String unidad;
	float valor;
	  
	
	public VistaDatos(int elementoID, Date fechaHora, float valor, String unidad) {
		super();
		this.elementoID = elementoID;
		this.fechaHora = fechaHora;
		this.unidad = unidad;
		this.valor = valor;
	}
	

	public int getElementoID() {
		return elementoID;
	}

	public void setElementoID(int vacaID) {
		this.elementoID = vacaID;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}


	@Override
	public String toString() {
		return "UbicacionVaca [vacaID=" + elementoID + ", fechaHora=" + fechaHora
				+ ", unidad=" + unidad + ", valor=" + valor + "]";
	}
	
}