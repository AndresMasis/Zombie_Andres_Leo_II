package javaapplication3;


public class Tablero {
    int tablero[][]= new int [25][25];//we work between 5-20 and 5-15
    int turno = 0;
    /*
    0 = vacio
    1 = base
    2 = personaje 1 snipper
    3 = personaje 2 
    4 = personaje 3
    5 = zombie 1
    6 = zombie 2
    7 = zombie 3
    8 = obstaculo
    9 = spawn
    10 = item
    */
     public void Tablero(){
       int fila = 0;
       int columna = 0;            
       fila = 5;
       columna = 5;
       while (columna != 15){
           while (fila != 20){   
               tablero[fila][columna] = 0;
               fila++;
           }
           fila = 5;
           columna++;                
       }
       
        tablero[5][14] = 1;
        tablero[7][14] = 2;
        tablero[7][12] = 3;
        tablero[5][12] = 4;
        
        tablero[13][14] = 8;
        tablero[11][12] = 8;
        tablero[10][11] = 8;
        tablero[14][10] = 8;
        tablero[9][10] = 8;
        tablero[8][8] = 8;
        tablero[13][7] = 8;
        tablero[15][5]= 8;
        tablero[9][5] = 8;    
        
        
        tablero[19][5] = 9;   
        tablero[19][9] = 9;   
        tablero[17][7] = 9; 
        tablero[17][12] = 9; 
     }
     
   
    
   
}