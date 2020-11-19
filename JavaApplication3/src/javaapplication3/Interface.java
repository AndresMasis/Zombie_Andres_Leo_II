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
    
    Snipper[] spawns = new Snipper[4];  // weuse the class sniper for the spawns for the positions only
    
    
    WeakZombie[] weakZom = new WeakZombie[50];
    FastZombie[] fastZom = new FastZombie[50];
    StrongZombie[] strongZom = new StrongZombie[50];   


    Items[] sword = new Sword[20];
    Items[] axe = new Axe[20];
    Items[] rifle = new Rifle[20];
    Items[] smallGun = new SmallGun[20];  
    
    JLabel pjSel;
    JLabel zomB;
    BaseClass clas;
    
    BaseClass turnOff;
    int turn = 1;
    int checkTurn = -1;
    int mov;
    int att;
    int obj;
    int level = 1;
    int checkLev = 1;
    int clenaIt = 0;
    
    public Interface() throws IOException{
        setZomN();
        tablero.Tablero();            
        this.initComponents();       
        this.setLocationRelativeTo(null); 
        setTableroJ(); 
        refresh();  
    }
    void showEst(){
        int pj = turnOff.numT-1;
        
        estad.setText(" Turno de PJ "+pj+" \n Daño: "+turnOff.damage+"\n Vida: "+turnOff.resistance+"\n Velocidad:"+turnOff.speed+"\nTurnos: "+turnOff.turns);
        textLev.setText("Level "+level);        
    }
    void levelUp(){
        if (level == 5){
            snipper1.turns+=2;
            explorer1.turns+=3;
            destroyer1.turns+=1;
            snipper1.resistance+=3;
            explorer1.resistance+=4;
            destroyer1.resistance+=10;
        }
        if (level == 10){
            snipper1.turns+=2;
            explorer1.turns+=1;
            destroyer1.turns+=1;
            snipper1.resistance+=3;
            explorer1.resistance+=3;
            destroyer1.resistance+=3;
            snipper1.damage+=3;
            explorer1.damage+=3;
            destroyer1.damage+=3;
        } 
        if (level == 13){
            snipper1.resistance+=1;
            explorer1.resistance+=3;
            destroyer1.resistance+=5;
        } 
        if (level == 15){
            snipper1.turns+=1;
            explorer1.turns+=1;
            destroyer1.turns+=1;
            snipper1.resistance+=1;
            explorer1.resistance+=3;
            destroyer1.resistance+=10;
            snipper1.damage+=5;
            explorer1.damage+=2;
            destroyer1.damage+=1;
        }        
        
    }
    
    void setZomN(){
        int count = 0;        
        while (count != 50){
            weakZom[count]=null;
            fastZom[count]=null;
            strongZom[count]=null;  
            count++;
        }
        count = 0;
        while (count != 20){
            sword[count]=null;
            axe[count]=null;
            rifle[count]=null;  
            smallGun[count]=null;
            count++;
        }
    }         
            
     void searchTurn(){ 
        if(checkTurn==-1){
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
           checkTurn = 0;
        }
        if(snipper1.resistance < 0 && turn == 1){
            changeTurn();
        }  
        if(explorer1.resistance < 0 && turn==2){
            changeTurn();
        }
        if(destroyer1.resistance < 0 && turn==3){
            changeTurn();
        }
                
    }
    void movZombs2(){
        movZombs();         
        spawnZom();       
    }
    
    void changeTurn(){
        if (turn == 3){
            movZombs2(); 
            checkLev++;  
            clenaIt = 0;
            turn = 1;
        }else{
            turn++;
        }
        
        if(checkLev == 3){
            level++;
            levelUp();
            if (level == 20){
                JOptionPane.showMessageDialog(null, "YOU WIN");
                    Timer timer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                            System.exit(0);
                        }
                    });
                    timer.start(); 
            }
            checkLev = 1;
            clenaIt = 1;
        }
        checkTurn = -1;
        int i =turnOff.numT-1;
    }
    void spawnZom(){
        int random;
        int zomP;
        if (level >= 1){            
            random = (int) Math.floor(Math.random()*(2)+1); //random between 1 and 3            
            if(random >= 1){
                zomP = (int) Math.floor(Math.random()*(7-5+1)+5); //random between 5 and 7
                if(tablero.tablero[18][5] == 0){
                    tablero.tablero[18][5]=zomP;
                }
            }            
            if(random >= 2){
                zomP = (int) Math.floor(Math.random()*(7-5+1)+5); //random between 5 and 7
                if(tablero.tablero[19][6] == 0){
                    tablero.tablero[19][6]=zomP;  
                }
            }            
        }
        if (level >= 5){            
            random = (int) Math.floor(Math.random()*(2)+1); //random between 1 and 3            
            if(random >= 1){
                zomP = (int) Math.floor(Math.random()*(7-5+1)+5); //random between 5 and 7
                if(tablero.tablero[19][8] == 0){
                    tablero.tablero[19][8]=zomP;
                }
            }            
            if(random >= 2){
                zomP = (int) Math.floor(Math.random()*(7-5+1)+5); //random between 5 and 7
                if(tablero.tablero[18][9] == 0){                    
                    tablero.tablero[18][9]=zomP;
                }            
            }             
        }
        if (level >= 10){            
            random = (int) Math.floor(Math.random()*(3)+1); //random between 1 and 3
            
            if(random >= 1){
                zomP = (int) Math.floor(Math.random()*(7-5+1)+5); //random between 5 and 7
                if(tablero.tablero[17][6] == 0){
                    tablero.tablero[17][6]=zomP;
                }
            }            
            if(random >= 2){
                zomP = (int) Math.floor(Math.random()*(7-5+1)+5); //random between 5 and 7
                 if(tablero.tablero[16][7] == 0){
                    tablero.tablero[16][7]=zomP;
                 }            
            }               
            if(random >= 3){
                zomP = (int) Math.floor(Math.random()*(7-5+1)+5); //random between 5 and 7
                if(tablero.tablero[17][8] == 0){
                    tablero.tablero[17][8]=zomP;            
                }
            }
        }
        if (level >= 15){
            random = (int) Math.floor(Math.random()*(3)+1); //random between 1 and 3
            
            if(random >= 1){
                zomP = (int) Math.floor(Math.random()*(7-5+1)+5); //random between 5 and 7
                if(tablero.tablero[17][11] == 0){
                    tablero.tablero[17][11]=zomP;
                }
            }            
            if(random >= 2){
                zomP = (int) Math.floor(Math.random()*(7-5+1)+5); //random between 5 and 7
                if(tablero.tablero[16][12] == 0){
                    tablero.tablero[16][12]=zomP;            
                }
            }               
            if(random >= 3){
                zomP = (int) Math.floor(Math.random()*(7-5+1)+5); //random between 5 and 7
                if(tablero.tablero[17][13] == 0){
                    tablero.tablero[17][13]=zomP;            
                }
            }
        }
    
    }
        
    int movToHeroe(BaseZombie weakZom,WeakZombie[] W,FastZombie[] F,StrongZombie[] S,int count){
        int X = weakZom.horizontalPosition;
        int Y = weakZom.verticalPosition;
        int movZ = weakZom.speed;
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
        check = 0;
        
        
        if(X > weakZom.horizontalPosition){
            if (tablero.tablero[weakZom.horizontalPosition+movZ][weakZom.verticalPosition] == 0){
                tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                weakZom.horizontalPosition+=movZ;                
                tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;  
                check = 1;
            }
        }
        if (check == 0){
            if(X < weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition-movZ][weakZom.verticalPosition] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.horizontalPosition-=movZ;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }
        }
        if (check == 0){
            if(Y > weakZom.verticalPosition){
                if (tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition+movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition+=movZ;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }
        if (check == 0){
            if(Y < weakZom.verticalPosition){
                if (tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition-movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition-=movZ;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }
               if (check == 0){
            if(Y < weakZom.verticalPosition && X < weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition-movZ][weakZom.verticalPosition-movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition-=movZ;     
                    weakZom.horizontalPosition-=movZ;  
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }       
        if (check == 0){
            if(Y > weakZom.verticalPosition && X < weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition-movZ][weakZom.verticalPosition+movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition+=movZ;     
                    weakZom.horizontalPosition-=movZ;  
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }
        if (check == 0){
            if(Y > weakZom.verticalPosition && X > weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition+movZ][weakZom.verticalPosition+movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition+=movZ;     
                    weakZom.horizontalPosition+=movZ;  
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }
        if (check == 0){
            if(Y < weakZom.verticalPosition && X > weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition+movZ][weakZom.verticalPosition-movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition-=movZ;     
                    weakZom.horizontalPosition+=movZ;  
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }  

        if (check == 0){
            return 0;
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
                    textRes.setText(textRes.getText()+"\nMurio el PJ 1");
                }else{
                    textRes.setText(textRes.getText()+"\nEl zombie le pego a PJ 1 "+weakZom.damage+" de daño \n le queda "+snipper1.resistance+" de vida");
                }                
            }
            else if (Y == explorer1.verticalPosition && X == explorer1.horizontalPosition){
                this.explorer1.resistance -= weakZom.damage;
                if(explorer1.resistance <= 0){                
                    tablero.tablero[explorer1.horizontalPosition][explorer1.verticalPosition] = 0;
                    textRes.setText(textRes.getText()+"\nMurio el PJ 2");
                }else{
                    textRes.setText(textRes.getText()+"\nEl zombie le pego a PJ 2 "+weakZom.damage+" de daño \n le queda "+explorer1.resistance+" de vida");
                }
            }
            else if (Y == destroyer1.verticalPosition && X == destroyer1.horizontalPosition){
                this.destroyer1.resistance -= weakZom.damage;
                if(destroyer1.resistance <= 0){                
                    tablero.tablero[destroyer1.horizontalPosition][destroyer1.verticalPosition] = 0;
                    textRes.setText(textRes.getText()+"\nMurio el PJ 3");
                }else{
                    textRes.setText(textRes.getText()+"\nEl zombie le pego a PJ 3 "+weakZom.damage+" de daño \n le queda "+destroyer1.resistance+" de vida");
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
    
    int movToSound(BaseZombie weakZom,WeakZombie[] W,FastZombie[] F,StrongZombie[] S,int count){
        int X = weakZom.horizontalPosition;
        int Y = weakZom.verticalPosition;
        int movZ = weakZom.speed;
        int I = 1;
        int check = 0;      
        
        while(I != weakZom.vision+1){
            if(20 == tablero.tablero[X-I][Y-I]){
                X-= I;
                Y-= I;
                check = 1;
                break;
            }
            else if(20 == tablero.tablero[X][Y-I]){
                Y-= I;
                check = 1;
                break;
            }
            else if(20 == tablero.tablero[X+I][Y-I]){
                X+= I;
                Y-= I;
                check = 1;
                break;
            }
            else if(20 ==  tablero.tablero[X-I][Y]){
                X-= I;
                check = 1;
                break;
            }
            else if(20 ==  tablero.tablero[X+I][Y]){
                X+= I;
                check = 1;
                break;
            }
            else if(20 ==  tablero.tablero[X-I][Y+I]){
                Y+= I;
                X-= I;
                check = 1;
                break;
            }
            else if(20 ==  tablero.tablero[X][Y+I]){
                Y+= I;
                check = 1;
                break;
            }
            else if(20 ==  tablero.tablero[X+I][Y+I]){
                Y+= I;
                X+= I;
                check = 1;
                break;
            }   
            
            if (I >= 2){
                if(20 ==  tablero.tablero[X-1][Y-I]){
                    X-= 1;
                    Y-= I;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X+1][Y-I]){
                    Y-= I;
                    X+= 1;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X+I][Y+1]){
                    X+= I;
                    Y+= 1;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X+I][Y-1]){
                    X+= I;
                    Y-= 1;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X-I][Y+1]){
                    X-= I;
                    Y+= 1;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X-I][Y-1]){
                    Y-= 1;
                    X-= I;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X-1][Y+I]){
                    Y+= I;
                    X-= 1;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X+1][Y+I]){
                    Y+= I;
                    X+= 1;
                    check = 1;
                    break;
                }
            }
            
            if (I >= 3){
                if(20 ==  tablero.tablero[X-2][Y-I]){
                    X-= 2;
                    Y-= I;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X+2][Y-I]){
                    Y-= I;
                    X+= 2;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X+I][Y+2]){
                    X+= I;
                    Y+= 2;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X+I][Y-2]){
                    X+= I;
                    Y-= 2;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X-I][Y+2]){
                    X-= I;
                    Y+= 2;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X-I][Y-2]){
                    Y-= 2;
                    X-= I;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X-2][Y+I]){
                    Y+= I;
                    X-= 2;
                    check = 1;
                    break;
                }
                else if(20 ==  tablero.tablero[X+2][Y+I]){
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
        check = 0;
        
        
        if(X > weakZom.horizontalPosition){
            if (tablero.tablero[weakZom.horizontalPosition+movZ][weakZom.verticalPosition] == 0){
                tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                weakZom.horizontalPosition+=movZ;                
                tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;  
                check = 1;
            }
        }
        if (check == 0){
            if(X < weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition-movZ][weakZom.verticalPosition] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.horizontalPosition-=movZ;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }
        }
        if (check == 0){
            if(Y > weakZom.verticalPosition){
                if (tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition+movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition+=movZ;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }
        if (check == 0){
            if(Y < weakZom.verticalPosition){
                if (tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition-movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition-=movZ;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }
               if (check == 0){
            if(Y < weakZom.verticalPosition && X < weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition-movZ][weakZom.verticalPosition-movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition-=movZ;     
                    weakZom.horizontalPosition-=movZ;  
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }       
        if (check == 0){
            if(Y > weakZom.verticalPosition && X < weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition-movZ][weakZom.verticalPosition+movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition+=movZ;     
                    weakZom.horizontalPosition-=movZ;  
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }
        if (check == 0){
            if(Y > weakZom.verticalPosition && X > weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition+movZ][weakZom.verticalPosition+movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition+=movZ;     
                    weakZom.horizontalPosition+=movZ;  
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }
        if (check == 0){
            if(Y < weakZom.verticalPosition && X > weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition+movZ][weakZom.verticalPosition-movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition-=movZ;     
                    weakZom.horizontalPosition+=movZ;  
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }  

        if (check == 0){
            return 0;
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
    int movToBase(BaseZombie weakZom,WeakZombie[] W,FastZombie[] F,StrongZombie[] S,int count){
        int X = 5;
        int Y = 14;      
        int check = 0;   
        
        if (weakZom == null){
            System.out.println("Zombie null fuera de indice");
            return 0;
        }
        int movZ = weakZom.speed;
        
        if(X > weakZom.horizontalPosition){
            if (tablero.tablero[weakZom.horizontalPosition+movZ][weakZom.verticalPosition] == 0){
                tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                weakZom.horizontalPosition+=movZ;                
                tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;  
                check = 1;
            }
        }
        if (check == 0){
            if(X < weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition-movZ][weakZom.verticalPosition] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.horizontalPosition-=movZ;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }
        }
        if (check == 0){
            if(Y > weakZom.verticalPosition){
                if (tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition+movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition+=movZ;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }
        if (check == 0){
            if(Y < weakZom.verticalPosition){
                if (tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition-movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition-=movZ;                
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }
        if (check == 0){
            if(Y < weakZom.verticalPosition && X < weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition-movZ][weakZom.verticalPosition-movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition-=movZ;     
                    weakZom.horizontalPosition-=movZ;  
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }       
        if (check == 0){
            if(Y > weakZom.verticalPosition && X < weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition-movZ][weakZom.verticalPosition+movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition+=movZ;     
                    weakZom.horizontalPosition-=movZ;  
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }
        if (check == 0){
            if(Y > weakZom.verticalPosition && X > weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition+movZ][weakZom.verticalPosition+movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition+=movZ;     
                    weakZom.horizontalPosition+=movZ;  
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }
        if (check == 0){
            if(Y < weakZom.verticalPosition && X > weakZom.horizontalPosition){
                if (tablero.tablero[weakZom.horizontalPosition+movZ][weakZom.verticalPosition-movZ] == 0){
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = 0;
                    weakZom.verticalPosition-=movZ;     
                    weakZom.horizontalPosition+=movZ;  
                    tablero.tablero[weakZom.horizontalPosition][weakZom.verticalPosition] = weakZom.numT;
                    check = 1;
                }
            }    
        }  

        if (check == 0){
            return 0;
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
                check = movToSound(weakZom[count],weakZom,null,null,count);
            if (check == 0)
                check = movToBase(weakZom[count],weakZom,null,null,count);
            
            count++;
            
        }                        
        count = 0;
        while(fastZom[count] != null){            
            check = attHeroe(fastZom[count]);
            if (check == 0)
                check = movToHeroe(fastZom[count],null,fastZom,null,count);
            if (check == 0)
                check = movToSound(fastZom[count],null,fastZom,null,count);
            if (check == 0)
                check = movToBase(fastZom[count],null,fastZom,null,count);
            
            count++;
        }                        
        count = 0;
        while(strongZom[count] != null){                      
            check = attHeroe(strongZom[count]);
            if (check == 0)
                check = movToHeroe(strongZom[count],null,null,strongZom,count); 
            if (check == 0)
                check = movToSound(strongZom[count],null,null,strongZom,count);
            if (check == 0)
                check = movToBase(strongZom[count],null,null,strongZom,count);
            
            count++;
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
            int numSwor = 0;
            int numAxe = 0;
            int numSma = 0;
            int numRif = 0;
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
                        }                        
                        if (numWeak != 50){
                                numWeak++;
                            }
                    }
                    else if (tablero.tablero[fila][columna] == 6){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/zombieF.png"))); 
                        if(fastZom[numFast]==null){
                            fastZom[numFast] = new FastZombie(columna,fila);
                        }             
                        if (numFast != 50){
                                numFast++;
                            }
                    }
                    else if (tablero.tablero[fila][columna] == 7){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/zombieS.png")));    
                        if(strongZom[numStro]==null){
                            strongZom[numStro] = new StrongZombie(columna,fila);                             
                        }                        
                        if (numStro != 50){
                                numStro++;
                            }
                    }
                    else if (tablero.tablero[fila][columna] == 8){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/lapida.png")));                
                    }
                    else if (tablero.tablero[fila][columna] == 9){                        
                        if(level == 1){
                            tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/spawnIm.png")));
                            if (spawns[0] == null){
                                spawns[0]= new Snipper(columna,fila);  
                            }                                
                        }                        
                        if(level == 5){
                            tablero.tablero[19][9] = 9; 
                            tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/spawnIm.png")));
                            if (spawns[1] == null){                               
                               spawns[1]= new Snipper(columna,fila);   
                            }
                        }
                        if(level == 10){
                            tablero.tablero[17][7] = 9;
                            tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/spawnIm.png")));
                            if (spawns[2] == null){                                
                                spawns[2]= new Snipper(columna,fila);   
                            }
                        }
                        if(level == 15){  
                            tablero.tablero[17][12] = 9;
                            tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/spawnIm.png")));
                            if (spawns[3] == null){
                                spawns[3]= new Snipper(columna,fila);     
                            }                             
                        }
                    }               
                    else if (tablero.tablero[fila][columna] == 10){
                        if(clenaIt==0){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/swordIm.png")));
                            if(sword[numSwor]== null){
                                sword[numSwor] = new Sword();   
                                sword[numSwor].horizontalPosition = fila;
                                sword[numSwor].verticalPosition = columna;
                            }                        
                            if (numSwor != 20){
                                    numSwor++;
                            }
                        }else{
                            tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/empty.png")));
                            tablero.tablero[fila][columna]=0;
                        }
                                            
                    }                    
                    else if (tablero.tablero[fila][columna] == 11){
                        if(clenaIt==0){
                            tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/axeIm.png")));
                            if(axe[numAxe]== null){
                                axe[numAxe] = new Axe();   
                                axe[numAxe].horizontalPosition = fila;
                                axe[numAxe].verticalPosition = columna;
                            }                        
                            if (numAxe != 20){
                                    numAxe++;
                            }  
                        }else{
                            tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/empty.png")));
                            tablero.tablero[fila][columna]=0;
                        }

                    }
                    else if (tablero.tablero[fila][columna] == 12){
                        if(clenaIt==0){
                        tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/smallGIm.png")));
                            if(smallGun[numSma]== null){
                                smallGun[numSma] = new SmallGun();   
                                smallGun[numSma].horizontalPosition = fila;
                                smallGun[numSma].verticalPosition = columna;
                            }                        
                            if (numSma != 20){
                                    numSma++;
                            } 
                        }else{
                            tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/empty.png")));
                            tablero.tablero[fila][columna]=0;
                        }
 
                    }
                    else if (tablero.tablero[fila][columna] == 13){
                        if(clenaIt==0){
                            tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/rifleIm.png")));
                            if(rifle[numRif]== null){
                                rifle[numRif] = new Rifle();   
                                rifle[numRif].horizontalPosition = fila;
                                rifle[numRif].verticalPosition = columna;
                            }                        
                            if (numRif != 20){
                                    numRif++;
                            }
                        }else{
                            tableroJ[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/empty.png")));
                            tablero.tablero[fila][columna]=0;
                        }

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
            showEst();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        estad = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        textRes = new javax.swing.JTextArea();
        textLev = new javax.swing.JTextField();
        textLev1 = new javax.swing.JTextField();
        textLev2 = new javax.swing.JTextField();

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
            .addGap(0, 651, Short.MAX_VALUE)
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
        getObj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getObjActionPerformed(evt);
            }
        });

        attackZom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/attackIm.png"))); // NOI18N
        attackZom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        attackZom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attackZomActionPerformed(evt);
            }
        });

        estad.setColumns(20);
        estad.setRows(5);
        estad.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        estad.setEnabled(false);
        jScrollPane1.setViewportView(estad);

        textRes.setColumns(20);
        textRes.setRows(5);
        textRes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        textRes.setEnabled(false);
        jScrollPane2.setViewportView(textRes);

        textLev.setBackground(new java.awt.Color(51, 0, 204));
        textLev.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        textLev.setForeground(new java.awt.Color(255, 255, 255));
        textLev.setText("Level\n");
        textLev.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        textLev.setEnabled(false);
        textLev.setFocusable(false);
        textLev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textLevActionPerformed(evt);
            }
        });

        textLev1.setBackground(new java.awt.Color(140, 120, 18));
        textLev1.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        textLev1.setForeground(new java.awt.Color(255, 255, 255));
        textLev1.setText("Respuesta");
        textLev1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        textLev1.setEnabled(false);
        textLev1.setFocusable(false);
        textLev1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textLev1ActionPerformed(evt);
            }
        });

        textLev2.setBackground(new java.awt.Color(140, 120, 18));
        textLev2.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        textLev2.setForeground(new java.awt.Color(255, 255, 255));
        textLev2.setText("Stats");
        textLev2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        textLev2.setEnabled(false);
        textLev2.setFocusable(false);
        textLev2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textLev2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ControlLayout = new javax.swing.GroupLayout(Control);
        Control.setLayout(ControlLayout);
        ControlLayout.setHorizontalGroup(
            ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlLayout.createSequentialGroup()
                .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ControlLayout.createSequentialGroup()
                        .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ControlLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(up, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ControlLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textLev1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textLev, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ControlLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ControlLayout.createSequentialGroup()
                                .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ControlLayout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(down, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80)
                                        .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(getObj, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(attackZom, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(ControlLayout.createSequentialGroup()
                                        .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ControlLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ControlLayout.createSequentialGroup()
                                        .addGap(128, 128, 128)
                                        .addComponent(textLev2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        ControlLayout.setVerticalGroup(
            ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlLayout.createSequentialGroup()
                .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textLev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textLev1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ControlLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(textLev2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(ControlLayout.createSequentialGroup()
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ControlLayout.createSequentialGroup()
                                .addComponent(up, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(down, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ControlLayout.createSequentialGroup()
                                .addComponent(attackZom, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(getObj, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapa, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
            .addComponent(Control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            }else{
                textRes.setText("No tienes más movimientos");
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
            }else{
                textRes.setText("No tienes más movimientos");
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
            }else{
                textRes.setText("No tienes más movimientos");
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
            }else{
                textRes.setText("No tienes más movimientos");
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
                int random = (int) Math.floor(Math.random()*(4)+1); //random between 1 and 12
                if (check == 1){                        
                    while(weakZom[count] != null){
                        if(weakZom[count].verticalPosition == Y && weakZom[count].horizontalPosition == X){
                            weakZom[count].resistance-=turnOff.damage;
                            textRes.setText("Le pegaste "+turnOff.damage+" de daño a un zombi débil");
                            if (weakZom[count].resistance <= 0){
                                if (random <= 4){
                                    random +=9;
                                    tablero.tablero[weakZom[count].horizontalPosition][weakZom[count].verticalPosition] = random;
                                }else{
                                    tablero.tablero[weakZom[count].horizontalPosition][weakZom[count].verticalPosition] = 0;
                                }                                   
                                textRes.setText("Mataste al zombie débil");
                                weakZom[count]=null;
                            }
                                                
                        }  
                        count++;
                    }                        
                    count = 0;
                    while(fastZom[count] != null){
                        if(fastZom[count].verticalPosition == Y && fastZom[count].horizontalPosition == X){
                            fastZom[count].resistance-=turnOff.damage;
                            textRes.setText("Le pegaste "+turnOff.damage+" de daño al zombi rapido");
                            if (fastZom[count].resistance <= 0){
                                tablero.tablero[fastZom[count].horizontalPosition][fastZom[count].verticalPosition] = 0;
                                textRes.setText("Mataste a un zombie rapido");
                                fastZom[count]=null;
                            }
                        }  
                        count++;
                    }                        
                    count = 0;
                    while(strongZom[count] != null){
                        if(strongZom[count].verticalPosition == Y && strongZom[count].horizontalPosition == X){
                            strongZom[count].resistance-=turnOff.damage;
                            textRes.setText("Le pegaste "+turnOff.damage+" de daño al zombi fuerte");
                            if (strongZom[count].resistance <= 0){
                                tablero.tablero[strongZom[count].horizontalPosition][strongZom[count].verticalPosition] = 0;
                                textRes.setText("Mataste a un zombie fuerte");
                                strongZom[count]=null;
                            }
                        }
                        count++;
                    }                   
                }else{
                    textRes.setText("Mejora tu punteria, desperdiciate un ataque.");
                }
            att--;
            }else{
                textRes.setText("No tienes más ataques");
            }            
    }//GEN-LAST:event_attackZomActionPerformed

    
    void equip(Items item){
        if (turnOff.numT == 4) {
            // Destroyer increases the damage of him for the quantity of his weapons
            turnOff.damage += 3;
        } else if (turnOff.numT == 2){
            // Snipper increases the damage and have one more turn for the quantity of his weapons
            turnOff.damage += 3;
            turnOff.turns += 2;
        }
        // asigns stats
        turnOff.resistance += item.resistance;
        turnOff.damage += item.damage;
        turnOff.turns += item.range;
        turnOff.noise += item.noise;        
    }
    
    
    
    
    private void getObjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getObjActionPerformed
        int X = turnOff.horizontalPosition;
        int Y = turnOff.verticalPosition;
        
        if(13 >= tablero.tablero[X-1][Y-1] && tablero.tablero[X-1][Y-1] >= 10){
            X--;
            Y--;
        }
        else if(13 >= tablero.tablero[X][Y-1] && tablero.tablero[X][Y-1] >= 10){
            Y--;
        }
        else if(13 >= tablero.tablero[X+1][Y-1] && tablero.tablero[X+1][Y-1] >= 10){
            X++;
            Y--;
        }
        else if(13 >= tablero.tablero[X-1][Y] && tablero.tablero[X-1][Y] >= 10){
            X--;
        }
        else if(13 >= tablero.tablero[X+1][Y] && tablero.tablero[X+1][Y] >= 10){
            X++;
        }
        else if(13 >= tablero.tablero[X-1][Y+1] && tablero.tablero[X-1][Y+1] >= 10){
            Y++;
            X--;
        }
        else if(13 >= tablero.tablero[X][Y+1] && tablero.tablero[X][Y+1] >= 10){
            Y++;
        }
        else if(13 >= tablero.tablero[X+1][Y+1] && tablero.tablero[X+1][Y+1] >= 10){
            Y++;
            X++;
        }
        
        if (X != turnOff.horizontalPosition || Y != turnOff.verticalPosition){
            int count = 0;
            while(count != 20){
                if(sword[count]!= null){
                    if (sword[count].horizontalPosition == X && sword[count].verticalPosition == Y){
                        if(turnOff.mountItem < 2){
                            equip(sword[count]);
                            turnOff.mountItem+=1;
                            tablero.tablero[X][Y]=0;
                            textRes.setText("Agarraste una espada");
                        }else{
                            textRes.setText(" No puedes agarrar mas objetos tienes el inventario lleno");
                        }
                        break;                
                    }
                }
                if(axe[count]!= null){
                    if (axe[count].horizontalPosition == X && axe[count].verticalPosition == Y){
                        if(turnOff.mountItem < 2){
                            equip(axe[count]);
                            turnOff.mountItem+=1;
                            tablero.tablero[X][Y]=0;
                            textRes.setText("Agarraste un hacha");
                        }else{
                            textRes.setText(" No puedes agarrar mas objetos tienes el inventario lleno");
                        }
                        break;
                    }    
                }
                if(rifle[count]!= null){
                    if (rifle[count].horizontalPosition == X && rifle[count].verticalPosition == Y){
                        if(turnOff.mountItem < 2){
                            equip(rifle[count]);
                            turnOff.mountItem+=1;
                            tablero.tablero[X][Y]=0;
                            textRes.setText("Agarraste un rifle");
                        }else{
                            textRes.setText("No puedes agarrar mas objetos tienes el inventario lleno");
                        }
                        break;
                    }  
                }
                if(smallGun[count]!= null){
                    if (smallGun[count].horizontalPosition == X && smallGun[count].verticalPosition == Y){
                        if(turnOff.mountItem < 2){
                            equip(smallGun[count]);
                            turnOff.mountItem+=1;
                            tablero.tablero[X][Y]=0;
                            textRes.setText("Agarraste una pistola");
                        }
                        else{
                            textRes.setText("No puedes agarrar mas objetos tienes el inventario lleno");
                        }
                        break;
                    }
                }
                count++;
            }
        }
        else{
            textRes.setText("No encontraste nada en el suelo.");
        }       
        
    }//GEN-LAST:event_getObjActionPerformed

    private void textLevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textLevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textLevActionPerformed

    private void textLev1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textLev1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textLev1ActionPerformed

    private void textLev2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textLev2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textLev2ActionPerformed
  
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
    public javax.swing.JTextArea estad;
    public javax.swing.JButton getObj;
    public javax.swing.JButton jButton1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JButton left;
    public javax.swing.JPanel mapa;
    public javax.swing.JButton right;
    public javax.swing.JTextField textLev;
    public javax.swing.JTextField textLev1;
    public javax.swing.JTextField textLev2;
    public javax.swing.JTextArea textRes;
    public javax.swing.JButton up;
    // End of variables declaration//GEN-END:variables
}
