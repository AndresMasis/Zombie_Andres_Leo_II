package javaapplication3;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Interface2 {
    
    public Interface2() {
        JPanel FONDO = new JPanel();
      /*  FONDO.setSize(30,30);
        FONDO.setBackground(new java.awt.Color(0, 0, 0));
        FONDO.setForeground(new java.awt.Color(0, 0, 0));
        */
     //  FONDO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton_comprar_1.png")));
        
        FONDO.setOpaque(true);
        FONDO.setAlignmentX(0);
        FONDO.setAlignmentY(0);
        
        
        
        
        
        Rectangle Cuadro2 = new Rectangle();
        
        Rectangle Cuadro3 = new Rectangle();
        
        Cuadro3.setLocation(100, 100);
        Cuadro2.setLocation(10, 10);
        
        JFrame ventana = new JFrame("PROTO");
        
        ventana.setDefaultCloseOperation(3);
        ventana.setSize(800,800);
        
       // ventana.getContentPane().setBackground(Color.black);

        ventana.setLocationRelativeTo(null);
        
        ventana.add(new Cuadro(Cuadro2));
        ventana.add(new Cuadro(Cuadro3));
    //    ventana.add(FONDO);
    
        JLabel[][] tableroJ = new JLabel[10][15];
        
        int posF = 20;
        int posC = 20;
        int fila = 0;
        int columna = 0;
        
      
        while (columna != 15){
            while (fila != 10){     
                tableroJ[fila][columna] = new JLabel();
                (tableroJ[fila][columna]).setSize(40, 40);
                tableroJ[fila][columna].setBackground(Color.green);
                tableroJ[fila][columna].setLocation(posF, posC);
                tableroJ[fila][columna].setText("");
                
                ventana.add(tableroJ[fila][columna]); 
                
                fila++;
                posF += 40;                
            }
            posF = 20;
            posF += 40;
            fila = 0;
            columna++;                
        }
        
        ventana.setVisible(true);
    }
        
}
