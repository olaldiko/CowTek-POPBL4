package interfaz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.table.AbstractTableModel;

import dataBase.JDBC;
import dataBase.Vaca;

public class ModeloTablaVacas extends AbstractTableModel {
	
	final static String NOMBRE_FICHERO = "listaclase.txt";
	
	ModeloColumnasTablaVacas columnas;
	
	ArrayList<Vaca> listaVacas;
	
	public ModeloTablaVacas(ModeloColumnasTablaVacas columnas){
		super();
		leerTablaFichero();
		
		this.columnas = columnas;
		
	}
	public void leerTablaFichero() {
		listaVacas = new ArrayList<>();
		
		JDBC dbConection =new JDBC();
		listaVacas=(ArrayList<Vaca>) dbConection.getVacas();
		
	}

	@Override
	public int getColumnCount() {
		
		return columnas.getColumnCount();
	}

	@Override
	public int getRowCount() {
		
		return listaVacas.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Vaca a = listaVacas.get(fila);
		switch(columna){
		case 0:
			return a.getVacaID();
		case 1:
			return a.getNombre();
		case 2:
			return a.getRaza();
		case 3:
			return a.getFechaNacimiento();
		default:
			return null;
		}
	}
		
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 3) return true;
		return false;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return getValueAt(0,columnIndex).getClass();
	}
	public ArrayList<Vaca> getListaVacas() {
		return listaVacas;
	}
	public void setListaVacas(ArrayList<Vaca> listaVacas) {
		this.listaVacas = listaVacas;
	}
	

}

