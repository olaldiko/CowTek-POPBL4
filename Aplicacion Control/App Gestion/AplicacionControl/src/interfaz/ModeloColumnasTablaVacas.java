package interfaz;


import javax.swing.JComboBox;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ModeloColumnasTablaVacas extends DefaultTableColumnModel{
	
	TrazadorTablaVacas trazador;
	
	public ModeloColumnasTablaVacas(TrazadorTablaVacas trazador){
		super();
		this.trazador = trazador;
		this.addColumn(crearColumna("VacaID",0,100));
		this.addColumn(crearColumna("Nombre",1,100));
		this.addColumn(crearColumna("Raza",2,100));
		this.addColumn(crearColumna("FechaNacimiento",3,200));
	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice,ancho);
		
		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);
		
		return columna;
	}

}

