/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.util.LinkedList;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.Timer; 

import javaapplication3.BaseHero;
import javaapplication3.BaseZombie;
/**
 *
 * @author Andrés
 */
public final class Interface extends javax.swing.JFrame {
   
    Tablero tablero = new Tablero();   
    
    javax.swing.JLabel[][] tableroJ= new javax.swing.JLabel[25][25];// it is 15 and 10 but its for not have out of index error
    
    Snipper snipper1 = null;    
    Explorer explorer1 = null;
    Destroyer destroyer1 = null; 

    WeakZombie[] weakZom = new WeakZombie[20];
    FastZombie[] fastZom = new FastZombie[5];
    StrongZombie[] strongZom = new StrongZombie[5];   

    JLabel pjSel;
    JLabel zomB;
    BaseClass clas;
    
    BaseClass turnOff;
    int turn = 1;
    int mov;
    int att;
    int obj;
    int level = 1;
    int checkLev = 1;
    
    public Interface() throws IOException{
        tablero.Tablero();               
        this.initComponents();       
        this.setLocationRelativeTo(null); 
        setTableroJ(); 
        refresh();     
    }
    
     void searchTurn(){ 
        if (turn == 1){
           turnOff = snipper1;                
        }
        else if (turn == 2){            
           turnOff = explorer1;            
        }
        else if (turn == 3){            
           turnOff = destroyer1;
       }
       mov = turnOff.turns;
       att = turnOff.turns;
       obj = turnOff.turns;        
    }
    void movZombs2(){
        movZombs();
        spawnZom();
    }
     
    void changeTurn(){
        if (turn == 3){ 
            turn = 1;
            movZombs2(); 
            checkLev++;
        }else{
            turn++;
        }
        
        if(checkLev == 3){
            level++;
            checkLev = 1;
        }
    }
    void spawnZom(){
        
        if (level == 1){
        
        }
        if (level == 2){
        
        }
        if (level == 3){
        
        }
        if (level == 4){
        
        }
    
    }
        
    int movToHeroe(BaseZombie weakZom,WeakZombie[] W,FastZombie[] F,StrongZombie[] S,int count){
        int X = weakZom.horizontalPosition;
        int Y = weakZom.verticalPosition;
        int I = 1;
        int check = 0;      
        
        while(I != weakZom.vision+1){
            if(4 >= tablero.tablero[X-I][Y-I] && tablero.tablero[X-I][Y-I] >= 2){
                X-= I;
                Y-= I;
                check = 1;
                break;
            }
            else if(4 >= tablero.tablero[X][Y-I] && tablero.tablero[X][Y-I] >= 2){
                Y-= I;
                check = 1;
                break;
            }
            else if(4 >= tablero.tablero[X+I][Y-I] && tablero.tablero[X+I][Y-I] >= 2){
                X+= I;
                Y-= I;
                check = 1;
                break;
            }
            else if(4 >= tablero.tablero[X-I][Y] && tablero.tablero[X-I][Y] >= 2){
                X-= I;
                check = 1;
                break;
            }
            else if(4 >= tablero.tablero[X+I][Y] && tablero.tablero[X+I][Y] >= 2){
                X+= I;
                check = 1;
                break;
            }
            else if(4 >= tablero.tablero[X-I][Y+I] && tablero.tablero[X-I][Y+I] >= 2){
                Y+= I;
                X-= I;
                check = 1;
                break;
            }
            else if(4 >= tablero.tablero[X][Y+I] && tablero.tablero[X][Y+I] >= 2){
                Y+= I;
                check = 1;
                break;
            }
            else if(4 >= tablero.tablero[X+I][Y+I] && tablero.tablero[X+I][Y+I] >= 2){
                Y+= I;
                X+= I;
                check = 1;
                break;
            }   
            
            if (I >= 2){
                if(4 >= tablero.tablero[X-1][Y-I] && tablero.tablero[X-1][Y-I] >= 2){
                    X-= 1;
                    Y-= I;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X+1][Y-I] && tablero.tablero[X+1][Y-I] >= 2){
                    Y-= I;
                    X+= 1;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X+I][Y+1] && tablero.tablero[X+I][Y+1] >= 2){
                    X+= I;
                    Y+= 1;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X+I][Y-1] && tablero.tablero[X+I][Y-1] >= 2){
                    X+= I;
                    Y-= 1;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X-I][Y+1] && tablero.tablero[X-I][Y+1] >= 2){
                    X-= I;
                    Y+= 1;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X-I][Y-1] && tablero.tablero[X-I][Y-1] >= 2){
                    Y-= 1;
                    X-= I;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X-1][Y+I] && tablero.tablero[X-1][Y+I] >= 2){
                    Y+= I;
                    X-= 1;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X+1][Y+I] && tablero.tablero[X+1][Y+I] >= 2){
                    Y+= I;
                    X+= 1;
                    check = 1;
                    break;
                }
            }
            
            if (I >= 3){
                if(4 >= tablero.tablero[X-2][Y-I] && tablero.tablero[X-2][Y-I] >= 2){
                    X-= 2;
                    Y-= I;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X+2][Y-I] && tablero.tablero[X+2][Y-I] >= 2){
                    Y-= I;
                    X+= 2;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X+I][Y+2] && tablero.tablero[X+I][Y+2] >= 2){
                    X+= I;
                    Y+= 2;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X+I][Y-2] && tablero.tablero[X+I][Y-2] >= 2){
                    X+= I;
                    Y-= 2;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X-I][Y+2] && tablero.tablero[X-I][Y+2] >= 2){
                    X-= I;
                    Y+= 2;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X-I][Y-2] && tablero.tablero[X-I][Y-2] >= 2){
                    Y-= 2;
                    X-= I;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X-2][Y+I] && tablero.tablero[X-2][Y+I] >= 2){
                    Y+= I;
                    X-= 2;
                    check = 1;
                    break;
                }
                else if(4 >= tablero.tablero[X+2][Y+I] && tablero.tablero[X+2][Y+I] >= 2){
                    Y+= I;
                    X+= 2;
                    check = 1;
                    break;
                }
            }
        I++;
        }
        if(check == 0){
            return 0;
        }
        System.out.println("--------4 X = " +X+" Y = "+Y);
        check = 0;
        
        if(X > weakZom.horizontalPosition){
            if (tablero.tablero[weakZom.horizontalPosition+1][weakZom.verticalPosition] == 0){
                tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                weakZom.horizontalPosition++;                
                tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                check = 1;
            }
        }
        if (check == 0){
            if(X < weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition-1][weakZom.verticalPosition] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.horizontalPosition--;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }
        }
        if (check == 0){
            if(Y > weakZom.verticalPosition){
                if (tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition+1] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition++;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }            
        }
        if (check == 0){ 
            if(Y < weakZom.verticalPosition){
                if (tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition-1] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition--;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }
        }
        
        if(W != null){
            W[count].horizontalPosition = weakZom.horizontalPosition;
            W[count].verticalPosition = weakZom.verticalPosition;
        }
        else if(F != null){
            F[count].horizontalPosition = weakZom.horizontalPosition;
            F[count].verticalPosition = weakZom.verticalPosition;
        }
        else if(S != null){
            S[count].horizontalPosition = weakZom.horizontalPosition;
            S[count].verticalPosition = weakZom.verticalPosition;
        }
        return 1;
    }
    void hitHero(int X,int Y,BaseZombie weakZom){
            if (Y == snipper1.verticalPosition && X == snipper1.horizontalPosition){
                this.snipper1.resistance -= weakZom.damage;                
                if(snipper1.resistance <= 0){                
                    tablero.tablero[snipper1.horizontalPosition][snipper1.verticalPosition] = 0;
                    System.out.println("Murio el PJ 1");
                }else{
                    System.out.println("El zombie le pego a PJ 1 "+weakZom.damage+" de daño \n le queda "+snipper1.resistance+" de vida");
                }                
            }
            else if (Y == explorer1.verticalPosition && X == explorer1.horizontalPosition){
                this.explorer1.resistance -= weakZom.damage;
                if(explorer1.resistance <= 0){                
                    tablero.tablero[explorer1.horizontalPosition][explorer1.verticalPosition] = 0;
                    System.out.println("Murio el PJ 2");
                }else{
                    System.out.println("El zombie le pego a PJ 2 "+weakZom.damage+" de daño \n le queda "+explorer1.resistance+" de vida");
                }
            }
            else if (Y == destroyer1.verticalPosition && X == destroyer1.horizontalPosition){
                this.destroyer1.resistance -= weakZom.damage;
                if(destroyer1.resistance <= 0){                
                    tablero.tablero[destroyer1.horizontalPosition][destroyer1.verticalPosition] = 0;
                    System.out.println("Murio el PJ 3");
                }else{
                    System.out.println("El zombie le pego a PJ 3 "+weakZom.damage+" de daño \n le queda "+destroyer1.resistance+" de vida");
                }                
            }
            else if(Y == 14 && X == 5){
                JOptionPane.showMessageDialog(null, "GAME OVER");
                Timer timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                        System.exit(0);
                    }
                });
                timer.start();                
            }
    }
    
    int attHeroe(BaseZombie weakZom){
        int X = weakZom.horizontalPosition;
        int Y = weakZom.verticalPosition;
        
        if(4 >= tablero.tablero[X-1][Y-1] && tablero.tablero[X-1][Y-1] >= 1){
            X--;
            Y--;
        }
        else if(4 >= tablero.tablero[X][Y-1] && tablero.tablero[X][Y-1] >= 1){
            Y--;
        }
        else if(4 >= tablero.tablero[X+1][Y-1] && tablero.tablero[X+1][Y-1] >= 1){
            X++;
            Y--;
        }
        else if(4 >= tablero.tablero[X-1][Y] && tablero.tablero[X-1][Y] >= 1){
            X--;
        }
        else if(4 >= tablero.tablero[X+1][Y] && tablero.tablero[X+1][Y] >= 1){
            X++;
        }
        else if(4 >= tablero.tablero[X-1][Y+1] && tablero.tablero[X-1][Y+1] >= 1){
            Y++;
            X--;
        }
        else if(4 >= tablero.tablero[X][Y+1] && tablero.tablero[X][Y+1] >= 1){
            Y++;
        }
        else if(4 >= tablero.tablero[X+1][Y+1] && tablero.tablero[X+1][Y+1] >= 1){
            Y++;
            X++;
        }else{
            return 0;
        }
        
        hitHero(X,Y,weakZom);
        return 1;
    }
    
    int movToSound(BaseZombie weakZom){
    
        return 0;
    }
    int movToBase(BaseZombie weakZom,WeakZombie[] W,FastZombie[] F,StrongZombie[] S,int count){
        int X = 5;
        int Y = 14;      
        int check = 0;   
        
        if(X > weakZom.horizontalPosition){
            if (tablero.tablero[weakZom.horizontalPosition+1][weakZom.verticalPosition] == 0){
                tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                weakZom.horizontalPosition++;                
                tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                check = 1;
            }
        }
        if (check == 0){
            if(X < weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition-1][weakZom.verticalPosition] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.horizontalPosition--;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }
        }
        if (check == 0){
            if(Y > weakZom.verticalPosition){
                if (tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition+1] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition++;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }            
        }
        if (check == 0){ 
            if(Y < weakZom.verticalPosition){
                if (tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition-1] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition--;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }
        }
        
        if(W != null){
            W[count].horizontalPosition = weakZom.horizontalPosition;
            W[count].verticalPosition = weakZom.verticalPosition;
        }
        else if(F != null){
            F[count].horizontalPosition = weakZom.horizontalPosition;
            F[count].verticalPosition = weakZom.verticalPosition;
        }
        else if(S != null){
            S[count].horizontalPosition = weakZom.horizontalPosition;
            S[count].verticalPosition = weakZom.verticalPosition;
        }
        return 1;
    }
    
    void movZombs(){
        int count = 0;
        int check;
                
        while(weakZom[count] != null){            
            check = attHeroe(weakZom[count]);
            if (check == 0)
                check = movToHeroe(weakZom[count],weakZom,null,null,count);
            if (check == 0)
                check = movToSound(weakZom[count]);
            if (check == 0)
                check = movToBase(weakZom[count],weakZom,null,null,count);
            
            count++;
            System.out.println("--------3");
            
        }                        
        count = 0;
        while(fastZom[count] != null){            
            check = attHeroe(fastZom[count]);
            if (check == 0)
                check = movToHeroe(fastZom[count],null,fastZom,null,count);
            if (check == 0)
                check = movToSound(fastZom[count]);
            if (check == 0)
                check = movToBase(fastZom[count],null,fastZom,null,count);
            
            count++;
            System.out.println("--------2");
        }                        
        count = 0;
        while(strongZom[count] != null){                      
            check = attHeroe(strongZom[count]);
            if (check == 0)
                check = movToHeroe(strongZom[count],null,null,strongZom,count); 
            if (check == 0)
                check = movToSound(fastZom[count]);
            if (check == 0)
                check = movToBase(fastZom[count],null,null,strongZom,count);
            
            count++;
            System.out.println("--------1");
        } 
    }
    
    void setTableroJ(){
        int fila = 5;
        int columna = 5;         
        while (columna != 15){
            while (fila != 20){   
                tableroJ[fila][columna] = new javax.swing.JLabel();
                mapa.add(tableroJ[fila][columna]);
                fila++;
            }
            fila = 5;
            columna++;                
        }
    }
    void refresh() {
              
        Timer timer = new Timer(300, new ActionListener() {
        public void actionPerformed(ActionEvent ae) {

            int posF = 20;
            int posC = 20;
            int fila = 5;
            int columna = 5;
            int numWeak = 0;
            int numFast = 0;
            int numStro = 0;
  
            while (columna != 15){
                while (fila != 20){                   
                    tableroJ[fila][columna].setSize(40, 40);

                    if (tablero.tablero[fila][columna] == 0){
                        tableroJ[fila][columna].setBackground(new java.awt.Color(153, 255, 153));
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/empty.png")));    
                    } 
                    else if (tablero.tablero[fila][columna] == 1){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/base.png")));                                  
                    }
                    else if (tablero.tablero[fila][columna] == 2){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/PJ1.png")));  
                        if(snipper1 == null)
                            snipper1 = new Snipper(columna,fila);  
                    }
                    else if (tablero.tablero[fila][columna] == 3){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/PJ2.png")));
                        if(explorer1 == null)
                            explorer1 = new Explorer(columna,fila);
                    }
                    else if (tablero.tablero[fila][columna] == 4){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/PJ3.png")));
                        if(destroyer1 == null)
                            destroyer1 = new Destroyer(columna,fila);                            
                    }
                    else if (tablero.tablero[fila][columna] == 5){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/zombieW.png")));  
                        if(weakZom[numWeak]==null){
                            weakZom[numWeak] = new WeakZombie(columna,fila);
                            if (numWeak != 20){
                                numWeak++;
                            }                        
                        }                        
                    }
                    else if (tablero.tablero[fila][columna] == 6){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/zombieF.png"))); 
                        if(fastZom[numFast]==null){
                            fastZom[numFast] = new FastZombie(columna,fila); 
                            if (numFast != 5){
                                numFast++;
                            }
                        }                        
                    }
                    else if (tablero.tablero[fila][columna] == 7){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/zombieS.png")));    
                        if(strongZom[numStro]==null){
                            strongZom[numStro] = new StrongZombie(columna,fila); 
                            if (numStro != 5){
                                numStro++;
                            }
                        }                        
                    }
                    else if (tablero.tablero[fila][columna] == 8){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/lapida.png")));                
                    }
                    else if (tablero.tablero[fila][columna] == 9){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/spawnIm.png")));                
                    }

                    tableroJ[fila][columna].setLocation(posF, posC);
                    tableroJ[fila][columna].setOpaque(rootPaneCheckingEnabled);
                    tableroJ[fila][columna].setText("");
                    mapa.add(tableroJ[fila][columna]);
                    fila++;
                    posF += 41;
                }
                posF = 20;
                posC += 41;
                fila = 5;
                columna++;                
            } 
            searchTurn();
        }
    });
        
    timer.start();        
    }
     
    
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mapa = new javax.swing.JPanel();
        Control = new javax.swing.JPanel();
        up = new javax.swing.JButton();
        left = new javax.swing.JButton();
        down = new javax.swing.JButton();
        right = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        getObj = new javax.swing.JButton();
        attackZom = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(java.awt.Color.black);
        setIconImage(Toolkit.getDefaultToolkit().getImage("Icon.png"));
        setSize(new java.awt.Dimension(500, 500));

        mapa.setBackground(new java.awt.Color(153, 255, 153));
        mapa.setMaximumSize(new java.awt.Dimension(615, 410));
        mapa.setPreferredSize(new java.awt.Dimension(800, 800));
        mapa.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout mapaLayout = new javax.swing.GroupLayout(mapa);
        mapa.setLayout(mapaLayout);
        mapaLayout.setHorizontalGroup(
            mapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        mapaLayout.setVerticalGroup(
            mapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        Control.setBackground(new java.awt.Color(128, 120, 5));

        up.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/arroUp.png"))); // NOI18N
        up.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upActionPerformed(evt);
            }
        });

        left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/arroLeft.png"))); // NOI18N
        left.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftActionPerformed(evt);
            }
        });

        down.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/arroDown.png"))); // NOI18N
        down.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downActionPerformed(evt);
            }
        });

        right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/arroRight.png"))); // NOI18N
        right.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/next.png"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        getObj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/getIm.png"))); // NOI18N
        getObj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        attackZom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/attackIm.png"))); // NOI18N
        attackZom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        attackZom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attackZomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ControlLayout = new javax.swing.GroupLayout(Control);
        Control.setLayout(ControlLayout);
        ControlLayout.setHorizontalGroup(
            ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ControlLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(up, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(down, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78)
                        .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ControlLayout.createSequentialGroup()
                                .addComponent(getObj, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ControlLayout.createSequentialGroup()
                                .addComponent(attackZom, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(ControlLayout.createSequentialGroup()
                        .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ControlLayout.setVerticalGroup(
            ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ControlLayout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ControlLayout.createSequentialGroup()
                        .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(down, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(ControlLayout.createSequentialGroup()
                        .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(attackZom, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(up, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(getObj, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mapa, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addComponent(Control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 135, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mapa, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upActionPerformed
        int tmp;
        if(turnOff.verticalPosition > 5){
            if(mov > 0){
                if(tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition-1] == 0){
                    tmp = tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition];
                    tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition] = 0;   
                    turnOff.verticalPosition--;
                    tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition] = tmp;
                    mov--;
                }
            }
        }
    }//GEN-LAST:event_upActionPerformed

    private void rightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightActionPerformed
        int tmp;
        if(turnOff.horizontalPosition < 19){
            if(mov > 0){
                if(tablero.tablero[turnOff.horizontalPosition+1][turnOff.verticalPosition] == 0){
                    tmp = tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition];
                    tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition] = 0;   
                    turnOff.horizontalPosition++;
                    tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition] = tmp;
                    mov--;
                }
            }
        }
    }//GEN-LAST:event_rightActionPerformed

    private void downActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downActionPerformed
        int tmp;
        if(turnOff.verticalPosition < 14){
            if(mov > 0){
                if(tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition+1] == 0){
                    tmp = tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition];
                    tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition] = 0;   
                    turnOff.verticalPosition++;
                    tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition] = tmp;
                    mov--;
                }
            }
        }
    }//GEN-LAST:event_downActionPerformed

    private void leftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftActionPerformed
        int tmp;
        if(turnOff.horizontalPosition > 5){
            if(mov > 0){
                if(tablero.tablero[turnOff.horizontalPosition-1][turnOff.verticalPosition] == 0){
                    tmp = tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition];
                    tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition] = 0;   
                    turnOff.horizontalPosition--;
                    tablero.tablero[turnOff.horizontalPosition][turnOff.verticalPosition] = tmp;
                    mov--;
                }
            }
        }
    }//GEN-LAST:event_leftActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        changeTurn();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void attackZomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attackZomActionPerformed
        int X = turnOff.horizontalPosition;
        int Y = turnOff.verticalPosition;
        int check = 0;
        int count = 0;
        
        if(att > 0){
                if(7 >= tablero.tablero[X-1][Y-1] && tablero.tablero[X-1][Y-1] >= 5){
                    X--;
                    Y--;
                    check = 1;
                }
                else if(7 >= tablero.tablero[X][Y-1] && tablero.tablero[X][Y-1] >= 5){
                    Y--;
                    check = 1;
                }
                else if(7 >= tablero.tablero[X+1][Y-1] && tablero.tablero[X+1][Y-1] >= 5){
                    X++;
                    Y--;
                    check = 1;
                }
                else if(7 >= tablero.tablero[X-1][Y] && tablero.tablero[X-1][Y] >= 5){
                    X--;
                    check = 1;
                }
                else if(7 >= tablero.tablero[X+1][Y] && tablero.tablero[X+1][Y] >= 5){
                    X++;
                    check = 1;
                }
                else if(7 >= tablero.tablero[X-1][Y+1] && tablero.tablero[X-1][Y+1] >= 5){
                    Y++;
                    X--;
                    check = 1;
                }
                else if(7 >= tablero.tablero[X][Y+1] && tablero.tablero[X][Y+1] >= 5){
                    Y++;
                    check = 1;
                }
                else if(7 >= tablero.tablero[X+1][Y+1] && tablero.tablero[X+1][Y+1] >= 5){
                    Y++;
                    X++;
                    check = 1;
                }

                if (check == 1){                        
                    while(weakZom[count] != null){
                        if(weakZom[count].verticalPosition == Y && weakZom[count].horizontalPosition == X){
                            weakZom[count].resistance-=turnOff.damage;
                            System.out.println("Le pegaste "+turnOff.damage+" al zombi debil en la casilla X= "+X+" Y= "+Y);
                            if (weakZom[count].resistance <= 0){
                                tablero.tablero[weakZom[count].horizontalPosition][weakZom[count].verticalPosition] = 0;
                                System.out.println("Murio el zombie W");
                                weakZom[count]=null;
                            }
                                                
                        }  
                        count++;
                    }                        
                    count = 0;
                    while(fastZom[count] != null){
                        if(fastZom[count].verticalPosition == Y && fastZom[count].horizontalPosition == X){
                            fastZom[count].resistance-=turnOff.damage;
                            System.out.println("Le pegaste "+turnOff.damage+" al zombi rapdio en la casilla X= "+X+" Y= "+Y);
                            if (fastZom[count].resistance <= 0){
                                tablero.tablero[fastZom[count].horizontalPosition][fastZom[count].verticalPosition] = 0;
                                System.out.println("Murio el zombie F");
                                fastZom[count]=null;
                            }
                        }  
                        count++;
                    }                        
                    count = 0;
                    while(strongZom[count] != null){
                        if(strongZom[count].verticalPosition == Y && strongZom[count].horizontalPosition == X){
                            strongZom[count].resistance-=turnOff.damage;
                            System.out.println("Le pegaste "+turnOff.damage+" al zombi fuerte en la casilla X= "+X+" Y= "+Y);
                            if (strongZom[count].resistance <= 0){
                                tablero.tablero[strongZom[count].horizontalPosition][strongZom[count].verticalPosition] = 0;
                                System.out.println("Murio el zombie S");
                                strongZom[count]=null;
                            }
                        }
                        count++;
                    }                   
                }else{
                    System.out.println("Mejora tu punteria");
                }
            att--;
            }
            
    }//GEN-LAST:event_attackZomActionPerformed
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*    
    public void stopObs1(int bot, JLabel obs, JLabel elec){        
        if ( elec.getX() <= obs.getX()+ 25 && elec.getX() >= obs.getX()- 25 && elec.getY() <= obs.getY()+ 25 && elec.getY() >= obs.getY()- 25){            
            if (bot == 1){ // 1 igual izquierda
            elec.setLocation(elec.getX()+45,elec.getY());
            }
            if (bot == 2){// 2 igual derecha
            elec.setLocation(elec.getX()-45,elec.getY());         
            }     
            if (bot == 3){ // 3 igual abajo
            elec.setLocation(elec.getX(),elec.getY()-45);
            }
            if (bot == 4){// 4 igual arriba
            elec.setLocation(elec.getX(),elec.getY()+45);
            }
        }
    }
        
    public void hitZm(){        
        if ( pjSel.getX()-10 <= zomB.getX()-45 && pjSel.getX()+10 >= zomB.getX()-45       &&   pjSel.getY()-10 <= zomB.getY() && pjSel.getY()+10 >= zomB.getY()) {
            System.out.println("Le pegaste al zombie en la celda 5 un total de "+(clas.damage)+" de daño.");
        }
        else if( pjSel.getX()-10 <= zomB.getX()+45 && pjSel.getX()+10 >= zomB.getX()+45   &&   pjSel.getY()-10 <= zomB.getY() && pjSel.getY()+10 >= zomB.getY()) {
            System.out.println("Le pegaste al zombie celda 4 un total de  "+clas.damage+" de daño.");
        }
        else if( pjSel.getY()-10 <= zomB.getY()+45 && pjSel.getY()+10 >= zomB.getY()+45   &&  pjSel.getX()-10 <= zomB.getX() && pjSel.getX()+10 >= zomB.getX()){
            System.out.println("Le pegaste al zombie celda 2 un total de  "+clas.damage+" de daño.");
        }
        else if( pjSel.getY()-10 <= zomB.getY()-45 && pjSel.getY()+10 >= zomB.getY()-45   &&  pjSel.getX()-10 <= zomB.getX() && pjSel.getX()+10 >= zomB.getX()){
            System.out.println("Le pegaste al zombie celda 7 un total de  "+clas.damage+" de daño.");
        }
        else if( pjSel.getY()-10 <= zomB.getY()+45 && pjSel.getY()+10 >= zomB.getY()+45 && pjSel.getX()-10 <= zomB.getX()+45 && pjSel.getX()+10 >= zomB.getX()+45){
            System.out.println("Le pegaste al zombie celda 1 un total de  "+clas.damage+" de daño.");
        }
        else if( pjSel.getY()-10 <= zomB.getY()+45 && pjSel.getY()+10 >= zomB.getY()+45 && pjSel.getX()-10 <= zomB.getX()-45 && pjSel.getX()+10 >= zomB.getX()-45){
            System.out.println("Le pegaste al zombie celda 3 un total de  "+clas.damage+" de daño.");
        }
        else if( pjSel.getY()-10 <= zomB.getY()-45 && pjSel.getY()+10 >= zomB.getY()-45 && pjSel.getX()-10 <= zomB.getX()+45 && pjSel.getX()+10 >= zomB.getX()+45){
            System.out.println("Le pegaste al zombie celda 6 un total de  "+clas.damage+" de daño.");
        }
        else if( pjSel.getY()-10 <= zomB.getY()-45 && pjSel.getY()+10 >= zomB.getY()-45 && pjSel.getX()-10 <= zomB.getX()-45 && pjSel.getX()+10 >= zomB.getX()-45){
            System.out.println("Le pegaste al zombie celda 8 un total de  "+clas.damage+" de daño.");
        }
        else{
            System.out.println("No le pegaste a nada.");
        }       
    }
        
    public void ShowBot(JLabel PJ) {   
        jButton1.setLocation(PJ.getX()+ 80,PJ.getY()+ 30);        
        jButton2.setLocation(PJ.getX()+ 40,PJ.getY()+ 0);        
        jButton3.setLocation(PJ.getX()+ 80,PJ.getY()- 30);
        jButton4.setLocation(PJ.getX()+ 120,PJ.getY());
        hitZm.setLocation(PJ.getX()+ 80,PJ.getY()+ 60);
    }
    public void noShowBot() {   
        jButton1.setLocation(3000,3000);      
        jButton2.setLocation(3000,3000);   
        jButton3.setLocation(3000,3000);   
        jButton4.setLocation(3000,3000);   
        hitZm.setLocation(3000,3000);   
    }
    
    public void movZom() {
    Timer movZombie = new Timer(600, (ActionEvent ae) -> {
        
        if(zm1.getX()<500){
        zm1.setLocation(zm1.getX()+45,zm1.getY());
        }
        else{
        zm1.setLocation(38,zm1.getY());
        }
        
        stopObs1(2,jLabel1,zm1);
        stopObs1(2,pj1,zm1);
        stopObs1(2,pj2,zm1);
        stopObs1(2,pj3,zm1);
        
        if ( zm1.getX()-10 <= base.getX()-45 && zm1.getX()+10 >= base.getX()-45       &&   zm1.getY()-10 <= base.getY() && zm1.getY()+10 >= base.getY()) {
            JOptionPane.showMessageDialog(null, "GAME OVER");
        }
        else if( zm1.getX()-10 <= base.getX()+45 && zm1.getX()+10 >= base.getX()+45   &&   zm1.getY()-10 <= base.getY() && zm1.getY()+10 >= base.getY()) {
            JOptionPane.showMessageDialog(null, "GAME OVER");
        }
        else if( zm1.getY()-10 <= base.getY()+45 && zm1.getY()+10 >= base.getY()+45   &&  zm1.getX()-10 <= base.getX() && zm1.getX()+10 >= base.getX()){
            JOptionPane.showMessageDialog(null, "GAME OVER");
        }
        else if( zm1.getY()-10 <= base.getY()-45 && zm1.getY()+10 >= base.getY()-45   &&  zm1.getX()-10 <= base.getX() && zm1.getX()+10 >= base.getX()){
            JOptionPane.showMessageDialog(null, "GAME OVER");
        }
        else if( zm1.getY()-10 <= base.getY()+45 && zm1.getY()+10 >= base.getY()+45 && zm1.getX()-10 <= base.getX()+45 && zm1.getX()+10 >= base.getX()+45){
            JOptionPane.showMessageDialog(null, "GAME OVER");
        }
        else if( zm1.getY()-10 <= base.getY()+45 && zm1.getY()+10 >= base.getY()+45 && zm1.getX()-10 <= base.getX()-45 && zm1.getX()+10 >= base.getX()-45){
            JOptionPane.showMessageDialog(null, "GAME OVER");
        }
        else if( zm1.getY()-10 <= base.getY()-45 && zm1.getY()+10 >= base.getY()-45 && zm1.getX()-10 <= base.getX()+45 && zm1.getX()+10 >= base.getX()+45){
            JOptionPane.showMessageDialog(null, "GAME OVER");
        }
        else if( zm1.getY()-10 <= base.getY()-45 && zm1.getY()+10 >= base.getY()-45 && zm1.getX()-10 <= base.getX()-45 && zm1.getX()+10 >= base.getX()-45){
            JOptionPane.showMessageDialog(null, "GAME OVER");
        }            
                       
    });

    movZombie.start();        
}
    
    */

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Interface().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel Control;
    public javax.swing.JButton attackZom;
    public javax.swing.JButton down;
    public javax.swing.JButton getObj;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton left;
    public javax.swing.JPanel mapa;
    public javax.swing.JButton right;
    public javax.swing.JButton up;
    // End of variables declaration//GEN-END:variables
}
