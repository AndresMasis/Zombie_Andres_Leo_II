/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;


public class Cuadro extends JPanel implements KeyListener{

    private  Rectangle Cuadro;
    
    
    public Cuadro(Rectangle newCua) {
        addKeyListener(this);
        setFocusable(true);        
        this.Cuadro = newCua;
        //add(Cuadro);
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(Cuadro.getX());
        if (Cuadro.getX() > 300 || Cuadro.getX()< 0){
            Cuadro.setLocation(150,150);            
        }
        if (Cuadro.getY() > 300 || Cuadro.getY()< 0){
            Cuadro.setLocation(150,150);
        }
        if (e.getKeyChar() == 'W'){
            Cuadro.setLocation((int) Cuadro.getX(),(int) Cuadro.getY()- 50);
        }
        if (e.getKeyChar() == 'S'){
            Cuadro.setLocation((int)Cuadro.getX(),(int)Cuadro.getY()+ 50);
        }
        if (e.getKeyChar() == 'A'){
            Cuadro.setLocation((int)Cuadro.getX()- 50,(int)Cuadro.getY());
        }
        if (e.getKeyChar() == 'D'){
            Cuadro.setLocation((int)Cuadro.getX()+ 50,(int)Cuadro.getY());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
