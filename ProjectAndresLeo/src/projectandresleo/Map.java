/*
 This is the map of the game
 Controls the interactions between zombies and characters
 */
package projectandresleo;

/**
 *
 * @author Andrés
 */
public class Map {

    String[][] obstaclePositions = new String[2][3];
    BaseClass[][] characterPositions = new BaseClass[2][3]; 

    public Map() {
        this.obstaclePositions[0][0] = "free";
        this.obstaclePositions[0][1] = "obstacle";
        this.obstaclePositions[0][2] = "free";
        this.obstaclePositions[1][0] = "obstacle";
        this.obstaclePositions[1][1] = "free";
        this.obstaclePositions[1][2] = "free";
        
        /*
        It will look like this
        [free][obstacle][free]
        [obstacle][free][free]
        */
        
        /*1 free
        2 obstacle
        3 hero
        4 zombie
        5 item
        6 base*/
    }
}
