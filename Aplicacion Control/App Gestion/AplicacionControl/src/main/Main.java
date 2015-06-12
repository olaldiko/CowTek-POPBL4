package main;

import javax.swing.JFrame;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.watermark.SubstanceImageWatermark;

import dataBase.DataBase;
import analisisDatos.AnalisisHumedad;
import analisisDatos.AnalisisTemperatura;;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setCurrentWatermark( new SubstanceImageWatermark("C:/Users/gorka/Downloads/cowtekLogo.png"));
        
        DataBase dataBase = new DataBase();
		dataBase.iniciarPrograma();
		dataBase.mostrarTodo();
		AnalisisTemperatura a = new AnalisisTemperatura(null);
		AnalisisHumedad humedad = new AnalisisHumedad(null);
	}

}