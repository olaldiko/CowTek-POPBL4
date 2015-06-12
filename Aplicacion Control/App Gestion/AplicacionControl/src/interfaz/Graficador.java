package interfaz;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Graficador extends JPanel{
	double coeficienteGrado2;
	double coeficienteGrado1;
	double coeficienteGrado0;
	
    public Graficador(double coeficienteGrado2, double coeficienteGrado1, double coeficienteGrado0){
       this.coeficienteGrado0 = coeficienteGrado0;
       this.coeficienteGrado1 = coeficienteGrado1;
       this.coeficienteGrado2 = coeficienteGrado2;
    	
    	XYDataset paresDeDatos = generarDatos();
        JFreeChart diagrama = crearDiagrama(paresDeDatos);
        ChartPanel chartPanel = new ChartPanel(diagrama);
        chartPanel.setPreferredSize(new Dimension(400,300));
        this.add(chartPanel);
    }
 
    private XYDataset generarDatos(){
        //le pasamos una funcion generadora f(x)
        XYSeries datos = new XYSeries("Linea Funcion");
        for(double x=0.0; x<=50.0; x+=0.2) datos.add(x,f(x));
 
        XYSeriesCollection conjuntoDatos = new XYSeriesCollection();
        conjuntoDatos.addSeries(datos);
 
        return conjuntoDatos;
    }
 
    private JFreeChart crearDiagrama(XYDataset conjuntoDatos){
        JFreeChart diag = ChartFactory.createXYLineChart(
                                "Ingesta", //Titulo Grafica
                                "X", // Leyenda eje X
                                "Y", // Leyenda eje Y
                                conjuntoDatos, // Los datos
                                PlotOrientation.VERTICAL, //orientacion
                                false, // ver titulo de linea
                                false, //tooltips
                                false  //URL
                            );
        return diag;
    }
 
    //aqui definimos la funcion que desees, en esta caso la f(x) = 4sen(x)
    private double f(double x){
        return (coeficienteGrado2*Math.pow(x,2)+coeficienteGrado1*Math.pow(x, 1));
    }
}