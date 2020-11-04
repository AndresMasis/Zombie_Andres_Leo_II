/*
 This is the template for the zombie classes
 It describes how a zombie should be
 It inherits from BaseClass
 */
package projectandresleo;

/**
 *
 * @author Andrés
 */
public class BaseZombie extends BaseClass {
    protected Map map = new Map();
    protected String[][] obstaclesMatrix = map.obstaclePositions;
    protected BaseClass[][] charactersMatrix = map.characterPositions;
    
    

    // Atributtes
    protected boolean jump;

    // Methods
    @Override
    public void move(String[][] matrix, BaseClass[][] position) {
        // Leaves free the old position
        position[verticalPosition][horizontalPosition] = null;
        
        
        // Tries to move forward
        if (matrix[verticalPosition][horizontalPosition + 1].equals("free") || jump == true) {
            // Was able to move to the right
            this.horizontalPosition += 1;

            if (jump == true) {
                // Specific for a zombie who can jump, so must has passed over the obstacle
                this.horizontalPosition += 1;
            }

        } else if (matrix[verticalPosition + 1][horizontalPosition].equals("free")) { // Couldn´t go to the front
            // Goes down
            this.verticalPosition += 1;

        } else if (matrix[verticalPosition - 1][horizontalPosition].equals("free")) { // Couldn´t go down
            // Goes up
            this.verticalPosition -= 1;
        } else if (matrix[verticalPosition][horizontalPosition - 1].equals("free")) { // Couldn´t go up
            // Has to return by the left
            this.horizontalPosition -= 1;
        }
        
        // Applies the change of position, with the updated coordinates
        position[verticalPosition][horizontalPosition] = this;

    }

    @Override
    public void applyTurn() {
        // Checks if can attack
        BaseClass character;
        if (charactersMatrix[verticalPosition+1][horizontalPosition] != null ){
            // Has an character near
            
            // Checks that it is a hero an not another zombie
            character = charactersMatrix[verticalPosition+1][horizontalPosition];
            if (character.equals("hero")){
                // Found a hero, so proceeds to attack it
                this.attack(charactersMatrix[verticalPosition+1][horizontalPosition]);
            }
            
        }else if (charactersMatrix[verticalPosition-1][horizontalPosition] != null){
            // Has an character near
            
            // Checks that it is a hero an not another zombie
            character = charactersMatrix[verticalPosition+1][horizontalPosition];
            if (character.equals("hero")){
                // Found a hero, so proceeds to attack it
                this.attack(charactersMatrix[verticalPosition-1][horizontalPosition]);
            }
            
        }else if (charactersMatrix[verticalPosition][horizontalPosition+1] != null){
            // Has an character near
            
            // Checks that it is a hero an not another zombie
            character = charactersMatrix[verticalPosition+1][horizontalPosition];
            if (character.equals("hero")){
                // Found a hero, so proceeds to attack it
                this.attack(charactersMatrix[verticalPosition][horizontalPosition+1]);
            }
            
        }else if (charactersMatrix[verticalPosition][horizontalPosition-1] != null){
            // Has an character near
            
            // Checks that it is a hero an not another zombie
            character = charactersMatrix[verticalPosition+1][horizontalPosition];
            if (character.equals("hero")){
                // Found a hero, so proceeds to attack it
                this.attack(charactersMatrix[verticalPosition][horizontalPosition-1]);
            }
            
        } else {
            // Didnt have anything to attack, so it moves
            for (int i = 0; i < speed; i++){
                this.move(this.obstaclesMatrix, this.charactersMatrix);
            }
        }
        
    }
}



class WeakZombie extends BaseZombie{  
    public WeakZombie(int verticalPosition, int horizontalPosition){
        this.damage = 3;
        this.resistance = 5;
        this.speed = 1;
        this.jump = false;
        this.type = "zombie";
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
    }
}

class FastZombie extends BaseZombie{
    public FastZombie(int verticalPosition, int horizontalPosition){
        this.damage = 3;
        this.resistance = 5;
        this.speed = 2;
        this.jump = true;
        this.type = "zombie";
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
    }
}


class StrongZombie extends BaseZombie{
    public StrongZombie(int verticalPosition, int horizontalPosition){
        this.damage = 10;
        this.resistance = 10;
        this.speed = 1;
        this.jump = false;
        this.type = "zombie";
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
    }
}
