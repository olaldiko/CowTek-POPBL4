package interfaz;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristian Jesus Torres
 */
import java.awt.*;

import org.jfree.data.xy.XYSeries;
public class Graficacion extends Canvas
{
  private double Xpixel=0,Ypixel=0;
  private double Xpixel1=0,Ypixel1=0;
  private int x[][], y[][],  numd;
  private int n, MaxX, MaxY, posx[], posy[];
  private int numser, inser;
  private double maxy, miny, etiqx[], etiqy[];
  private double maxx, minx;
  private Color c[];
  private int pos_ejex, pos_ejey;
  private double xmouse=0,ymouse=0;
  private double Ancho,Alto;
  private double miny1, maxy1;
  private double minx1, maxx1;

  private double prop1,prop2;    String Titulo="DIAGRAMA BHPBA vs QD",Variable1="BHPBA",Variable2="QD";

    public Graficacion()
    {
       numd = 12;
	   this.prop1=0;this.prop2=0;
       pos_ejex = 50;
       pos_ejey = 450;
       this.setBackground(Color.white);
       numser = 12;
       etiqx = new double[numd];
       etiqy = new double[numd];
       posx  = new int[numd];
       posy  = new int[numd];
       c = new Color[numser];
       inser = 0;
		 
       c[0] = Color.blue;
       c[1] = Color.red;
       c[2] = Color.green;
       c[3] = Color.darkGray;
       c[4] = Color.cyan;
       c[5] = Color.black;
       c[6] = Color.orange;
       c[7] = Color.pink;
       c[8] = Color.darkGray;
       c[9] = Color.cyan;
       c[10] = Color.darkGray;
       c[11] = Color.cyan;
    }
    public void setDimensiones(double anchos, double altos)
    {
	Ancho=anchos;
	Alto=altos;
        MaxX = (int)(Ancho-50); MaxY = (int)(Alto-50);
        pos_ejey = (int)(Alto-50);
    }
   void Datos(double x[], double y[],double width,double height,String tit,String var1, String var2)
   {
	  Ancho=width;
	  Alto=height;
          Titulo=tit;
          Variable1=var1;
          Variable2=var2;
     SerieX(x);
     SerieY(y);
     System.out.println("alto="+Alto+" ancho="+Ancho);
   }

   void SerieX(double x0[])
   {
     int i;
     double inc, aux;

     n = x0.length;


     if (inser==0)
     {
       x = new int[numser][n];
       maxx1 = minx1 = x0[0];

       for (i = 1; i < n; i++) {
         if (x0[i] > maxx1)
           maxx1 = x0[i];
         if (x0[i] < minx1)
           minx1 = x0[i];

       }

       minx = minx1;
       maxx = maxx1;

       inc = (maxx1 - minx1) / numd;
       if (Math.abs(maxx1 - minx1) > 1.0) {
         if (Math.abs(inc) < 1.0)
           inc = (double) 1.0;
         else
           inc = Math.round(inc + 0.5);
       }

       for (i = 0; i < numd; i++) {
         aux = minx1 + inc * i;
         aux = (Math.round(aux * 10000.0)) / 10000.0;
         etiqx[i] = aux;
         posx[i] = (int) ( (etiqx[i] - minx1) / (maxx1 - minx1) * (MaxX - 50));
       }

       if (minx1 < 0 && maxx1 > 0)
         pos_ejex += (int) ( -minx1 / (maxx1 - minx1) * (MaxX - 50));

     }
     else
     {
       minx1 = minx;
       maxx1 = maxx;
     }
     for (i = 0; i < n; i++)
       x[inser][i] = (int) ( (x0[i] - minx1) / (maxx - minx) * (MaxX - 50));

   }

   void SerieY(double y0[])
   {
     int i;
     double inc, aux;

     if(inser >= numser) return;

     if(inser == 0)
     {
       y = new int[numser][n];

       maxy1 = miny1 = y0[0];

       for(i=1; i<n; i++)
       {
         if(y0[i] > maxy1) maxy1 = y0[i];
         if(y0[i] < miny1) miny1 = 0;//y0[i];
       }

       //max = 255;
       //min = -256;
       maxy = maxy1;
       miny = miny1;


       inc = (maxy1 - miny1)/numd;

       if( Math.abs(maxy1 - miny1) > 1.0)
       {
          if(Math.abs(inc) < 1.0) inc = (double)1.0;
          else inc = Math.round(inc+0.5);
       }


       for(i=0; i<numd; i++)
       {
         aux = miny1 + inc*i;

         aux = (Math.round(aux*10000.0))/10000.0;
         etiqy[i] = aux;
         posy[i]  = (int) ((etiqy[i] - miny1)/(maxy1 - miny1)*(MaxY-50));
       }
       if(miny1 < 0 && maxy1 > 0)
         pos_ejey = MaxY -(int)(-miny1 /(maxy1 - miny1)*(MaxY-50));
     }
     else
     {
       maxy1 = maxy;
       miny1 = miny;
     }

     for(i=0; i<n; i++)
       y[inser][i] = (int) ((y0[i] - miny1)/(maxy1 - miny1)*(MaxY-50));

      inser++;
   }

   public void GraficarTodo(Graphics g)
   {
       int i, j, mm;
       // dibujar unas cuantas figuras
       g.setColor(Color.black);

       g.drawLine(pos_ejex, 0, pos_ejex, (int)(Alto));
       g.drawLine(0, pos_ejey, (int)(Ancho), pos_ejey);
       g.drawString(Variable1,10,20);
       g.drawString(Variable2,(this.getSize().width/2)-40,this.getSize().height-10);
       g.drawString(Titulo,(this.getSize().width/2)-80,10);
       for(i=0; i<numd; i++)
       {
 	 etiqx[i]=(((int)(etiqx[i]+0.5)));
 	 etiqy[i]=(((int)(etiqy[i]+0.5)));
         g.drawString(Integer.toString((int)(etiqx[i])), 50 + posx[i]-2, pos_ejey + 20 );
         g.drawLine(50+posx[i], pos_ejey-5,  50+posx[i], pos_ejey+5);

         g.drawString(Integer.toString((int)etiqy[i]), pos_ejex-50, MaxY-posy[i]);
         g.drawLine(pos_ejex-5, MaxY-posy[i], pos_ejex+5, MaxY-posy[i]);
        }  
       g.setColor(Color.blue);

       if(inser> numser) mm = numser;
       else mm = inser;
       for(j=0; j<=mm; j++)
       {
	       g.setColor(Color.blue);
	     //g.setColor(c[j]);

         for(i=0; i<n-1; i++)
           g.drawLine(50+x[j][i], MaxY-y[j][i],  50+x[j][i+1], MaxY-y[j][i+1]);
        }
		///////////////////////////////////////////
		//Marcando Punto Critico//
		///////////////////////////////////////////
		
	  g.setColor(Color.black);
	  g.fillOval((int)(50+Xpixel1-3),(int)(MaxY-Ypixel1-3),6,6);
   }
   public void MarcarPunto(double Temp, double entr)
   {
	   this.prop1=Temp;
	   this.prop2=entr;
		repaint();
	   MarcarUbicacion(getGraphics());
   }     
   public void MarcarUbicacion(Graphics g)
   {
		 g.setColor(Color.red);
		 Xpixel=(int) ( ((this.prop1 - minx1) / (maxx - minx)) * (MaxX - 50));
		 Ypixel=(int) (((this.prop2 - miny1)/(maxy1 - miny1))*(MaxY-50));
	      g.fillOval((int)(50+Xpixel-3),(int)(MaxY-Ypixel-3),6,6);	
   }
   public void paint(Graphics g)
   {
       GraficarTodo(g);
       MarcarUbicacion(g);
   }
public double[] ValoresY() {

    XYSeries datos = new XYSeries("Linea Funcion");
    
	return null;
}
public double[] valoreX() {
	// TODO Auto-generated method stub
	return null;
}
}
