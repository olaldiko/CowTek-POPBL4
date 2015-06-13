package analisisDatos;

public class Dato {

	float valorEntrada;
	float valorSalida;
	
	
	public Dato(float valorEntrada, float valorSalida) {
		super();
		this.valorEntrada = valorEntrada;
		this.valorSalida = valorSalida;
	}


	public float getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(float valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public float getValorSalida() {
		return valorSalida;
	}

	public void setValorSalida(float valorSalida) {
		this.valorSalida = valorSalida;
	}


	@Override
	public String toString() {
		return "Dato [valorEntrada=" + valorEntrada + ", valorSalida="
				+ valorSalida + "]";
	}
		
}
