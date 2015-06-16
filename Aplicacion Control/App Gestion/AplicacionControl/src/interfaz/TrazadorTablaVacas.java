package interfaz;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * trazador de la tabla, con el estilo que tine cada apartado
 * @author gorka
 *
 */
public class TrazadorTablaVacas extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object valor,
			boolean isSelected, boolean hasFocus, int fila, int columna) {
		
		super.getTableCellRendererComponent(table, valor, isSelected, hasFocus, fila, columna);
		switch (columna ){
		case 0: 
		case 1:
		case 2: super.setHorizontalAlignment(LEFT);break;
		case 3: super.setHorizontalAlignment(CENTER);break;
		case 4: super.setHorizontalAlignment(RIGHT);break;
		case 5: 
				
		}
		
		
		return this;
	}

}
