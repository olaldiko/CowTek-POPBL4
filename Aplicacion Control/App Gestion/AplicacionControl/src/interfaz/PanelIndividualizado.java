package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import dataBase.DatosVaca;
import dataBase.JDBC;
import dataBase.Vaca;

/**
 * calse con la informacion de las vacas mostrada en una tabla
 * @author gorka
 *
 */
public class PanelIndividualizado extends JPanel implements ActionListener {
	JPanel panelCentral, panelNorte;
	JScrollPane scroll;
	DefaultTableModel model;
	JTable tablaCentral;
	JComboBox<String> box;
	List<Vaca> listaVacas;
	String[] columnNames = {"VacaID", "Nombre", "Raza", "FechaNacimiento"};
	String informacion[][] = null;
	
	/**
	 * diseño del panel individualizado
	 */
	public PanelIndividualizado(){
	super(new BorderLayout());
	JPanel panelNorte = new JPanel();
	panelNorte.add(panelNorte());
	this.add(panelNorte, BorderLayout.NORTH);
	
	scroll = new JScrollPane();
	panelCentral = new JPanel(new BorderLayout());
	
	scroll.setViewportView(panelTablaCentral());
	panelCentral.add(scroll);
	panelCentral.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
	this.add(panelCentral, BorderLayout.CENTER);
	}
	/**
	 * diseño de la tabla central
	 * @return
	 */
	private JTable panelTablaCentral() {
		
		listaVacas = new JDBC().getVacas();
		String[] columnNames = {"VacaID", "Nombre", "Raza", "FechaNacimiento"};
		String informacion[][] = convertirAMatriz(listaVacas);
		
		model = new DefaultTableModel(informacion, columnNames);
		tablaCentral = new JTable(model);
		tablaCentral.setFillsViewportHeight(true);
		//tablaCentral.setEnabled(false);
			
		return tablaCentral;
	}
	
	/**
	 * convierte la informacion del array a una matriz
	 * @param listaVacas
	 * @return
	 */
	private  String[][] convertirAMatriz(List<Vaca> listaVacas){
	int i = 0;
	String informacion[][] = new String[listaVacas.size()][4]; 
	 
	 for (int x = 0; x < informacion.length; x++) {
			 informacion[x][0] = listaVacas.get(x).getVacaID() + "";
			 informacion[x][1] = listaVacas.get(x).getNombre() + "";
			 informacion[x][2] = listaVacas.get(x).getRaza() + "";
			 informacion[x][3] = listaVacas.get(x).getFechaNacimiento() + "";
	     }
	 return informacion;
	}
	
	/**
	 * 
	 * @return
	 */
	private Component panelNorte() {
		panelNorte = new JPanel(new BorderLayout());
		JButton mostrar = new JButton("mostrar");
		ArrayList<String> nombres = getNombreVacas();
		mostrar.addActionListener(this);
		panelNorte.add(mostrar, BorderLayout.EAST);
		box = new JComboBox(nombres.toArray());
		panelNorte.add(box, BorderLayout.WEST);
		
		return panelNorte;
	}

	/**
	 * obtener los nombres de las vacas, para despues poder mostrarlas en un ComboBox
	 * @return
	 */
	private ArrayList<String> getNombreVacas() {
		List<Vaca> vacas = new JDBC().getVacas();
		Iterator<Vaca> itrVacas = vacas.iterator();
		ArrayList<String> nombresVacas = new ArrayList<>();
		nombresVacas.add("Todas");
		for (int i=0;i<vacas.size();i++){
			 nombresVacas.add(vacas.get(i).getNombre()) ;
			}
		return nombresVacas;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JDBC dbConnection = new JDBC();
		switch((String)box.getSelectedItem()) {
		case "Todas":
			listaVacas = dbConnection.getVacas();
			break;
		default :
			listaVacas = dbConnection.getVacasporNomre((String)box.getSelectedItem());
			break;
		}
		informacion = convertirAMatriz(listaVacas);
		model.setDataVector(informacion, columnNames);
		System.out.println(listaVacas);
		model.fireTableDataChanged();
	}
}
