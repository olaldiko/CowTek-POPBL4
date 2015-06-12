package dataBase;

import java.sql.Date;

public class DatosVistaVaca {
	int vacaID;
	Date fechaHora;
	String unidad;
	float valor;
	  
	
	public DatosVistaVaca(int vacaID, Date fechaHora, float valor, String unidad) {
		super();
		this.vacaID = vacaID;
		this.fechaHora = fechaHora;
		this.unidad = unidad;
		this.valor = valor;
	}
	

	public int getVacaID() {
		return vacaID;
	}

	public void setVacaID(int vacaID) {
		this.vacaID = vacaID;
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
		return "UbicacionVaca [vacaID=" + vacaID + ", fechaHora=" + fechaHora
				+ ", unidad=" + unidad + ", valor=" + valor + "]";
	}
	
}