package main;

import dataBase.DataBase;
import analisisDatos.AnalisisDatos;
import analisisDatos.AnalisisHumedad;
import analisisDatos.AnalisisTemperatura;;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataBase dataBase = new DataBase();
		dataBase.iniciarPrograma();
		dataBase.mostrarTodo();
		AnalisisDatos analisisDatos = new AnalisisDatos();
		analisisDatos.analizarTodo();
	}

}
