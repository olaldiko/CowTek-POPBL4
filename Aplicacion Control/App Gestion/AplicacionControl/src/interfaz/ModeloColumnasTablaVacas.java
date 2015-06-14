package interfaz;


import javax.swing.JComboBox;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 * clase encargada del diseño de las columnas de una tabla
 * @author gorka
 *
 */
public class ModeloColumnasTablaVacas extends DefaultTableColumnModel{
	
	TrazadorTablaVacas trazador;
	/**
	 * definir el estilo de las columnas de la tabla, y el nombre de estas
	 * @param trazador trazador
	 */
	public ModeloColumnasTablaVacas(TrazadorTablaVacas trazador){
		super();
		this.trazador = trazador;
		this.addColumn(crearColumna("VacaID",0,100));
		this.addColumn(crearColumna("Nombre",1,100));
		this.addColumn(crearColumna("Raza",2,100));
		this.addColumn(crearColumna("FechaNacimiento",3,200));
	}
	/**
	 * crear la columna
	 * @param texto
	 * @param indice
	 * @param ancho
	 * @return tablaColumnas
	 */
	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice,ancho);
		
		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);
		
		return columna;
	}

}

