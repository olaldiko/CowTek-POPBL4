package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dataBase.DatosEstacion;
import dataBase.DatosVaca;
import dataBase.Estacion;
import dataBase.JDBC;
import dataBase.Vaca;

/**
 * selecciona los datos de las estaciones y los muestra en una tabla 
 * @author gorka
 *
 */
public class PanelEstaciones extends JPanel implements ActionListener {
	JTable panelCentral;
	JComboBox box;
	List<DatosEstacion> datosEstaciones;
	String informacion[][];
	String[] columnNames = {"SensorID", "EstacionID", "UnidadID", "FechaHora", "Valor"};
	DefaultTableModel model;
	
	public PanelEstaciones(){
	super(new BorderLayout());
	JPanel panelNorte = new JPanel();
	panelNorte.add(panelNorte());
	this.add(panelNorte, BorderLayout.NORTH);
	
	
	
	JScrollPane scroll = new JScrollPane();
	JPanel panelCentral = new JPanel(new BorderLayout());
	scroll.setViewportView(panelTablaCentral());
	panelCentral.add(scroll);
	panelCentral.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
	this.add(panelCentral, BorderLayout.CENTER);
	}
	/**
	 * panel central
	 * @return
	 */
	private Component panelTablaCentral() {
		
		datosEstaciones = new JDBC().getDatosEstacion();
		informacion = convertirAMatriz(datosEstaciones);
		
		model = new DefaultTableModel(informacion, columnNames);
		panelCentral = new JTable(model);

		
		return panelCentral;
	}
	/**
	 * convierte los datos de arraylist, obtenidos desde la bd a una matriz para la clase DefaultTableModel
	 * @param datosEstaciones array con la informacion de las estaciones
	 * @return
	 */
	
	private  String[][] convertirAMatriz(List<DatosEstacion> datosEstaciones){
	
	informacion = new String[datosEstaciones.size()][5]; 
	   for (int x = 0; x < informacion.length; x++) {
	 informacion[x][0] = datosEstaciones.get(x).getEstacionID() + "";
	 informacion[x][1] = datosEstaciones.get(x).getSensorID() + "";
	 informacion[x][2] = datosEstaciones.get(x).getUnidadID() + "";
	 informacion[x][3] = datosEstaciones.get(x).getValor() + "";
	 informacion[x][4] = datosEstaciones.get(x).getFechaHora() + "";
	     }
	 return informacion;
	}
	
	/**
	 * ComboBox con los nombres de las estaciones
	 * @return
	 */
	private Component panelNorte() {
		JPanel panel = new JPanel(new BorderLayout());
		JButton mostrar = new JButton("mostrar");
		ArrayList<String> opciones = getNombreEstaciones();
		mostrar.addActionListener(this);
		panel.add(mostrar, BorderLayout.EAST);
		box = new JComboBox(opciones.toArray());
		panel.add(box, BorderLayout.WEST);
		return panel;
	}
	/**
	 * obtener los nombres de las estaciones de una bd
	 * @return
	 */

	private ArrayList<String> getNombreEstaciones() {
		List<Estacion> estaciones = new JDBC().getEstaciones();
		ArrayList<String> nombresEstaciones = new ArrayList<>();
	for (int i=0;i<estaciones.size();i++){
			 nombresEstaciones.add(estaciones.get(i).getNombre());
			}
		return nombresEstaciones;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JDBC dbConnection = new JDBC();
		switch((String)box.getSelectedItem()) {
		case "Todas":
			datosEstaciones = dbConnection.getDatosEstacion();
			break;
		default :
			datosEstaciones = dbConnection.getDatosEstacionesporNombre((String)box.getSelectedItem());
			break;
		}
		informacion = convertirAMatriz(datosEstaciones);
		model.setDataVector(informacion, columnNames);
		System.out.println(datosEstaciones);
		model.fireTableDataChanged();
	}
}
