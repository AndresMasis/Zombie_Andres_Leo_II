package projectandresleo;

/*
 This is the template for the zombie classes
 It describes how a zombie should be
 It inherits from BaseClass
 Uses abstract because wont be instantiated
 */
public class BaseZombie extends BaseClass {

    protected Map map = new Map();
    protected String[][] obstaclesMatrix = map.obstaclePositions;
    protected BaseClass[][] charactersMatrix = map.characterPositions;

    // Methods
    @Override
    public boolean attack(BaseClass enemy) {
        // Checks that the enemy is really an enemy
        if (enemy instanceof BaseHero) {
            // Substracts life of the character who is being attacked
            enemy.resistance -= this.damage;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void move(String[][] matrix, BaseClass[][] position) {
        for (int i = 0; i < this.speed; i++) {
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
    }

    @Override
    public void applyTurn() {
        // Checks if can attack
        boolean flag;
        if (charactersMatrix[verticalPosition + 1][horizontalPosition] != null) {
            // Has an character near
            flag = this.attack(charactersMatrix[verticalPosition + 1][horizontalPosition]);
            if (flag == false) {
                // Tried to attacked but just found another zombie, so it moves
                this.move(obstaclesMatrix, charactersMatrix);
            }

        } else if (charactersMatrix[verticalPosition - 1][horizontalPosition] != null) {
            // Has an character near
            flag = this.attack(charactersMatrix[verticalPosition - 1][horizontalPosition]);
            if (flag == false) {
                // Tried to attacked but just found another zombie, so it moves
                this.move(obstaclesMatrix, charactersMatrix);
            }

        } else if (charactersMatrix[verticalPosition][horizontalPosition + 1] != null) {
            // Has an character near
            flag = this.attack(charactersMatrix[verticalPosition][horizontalPosition + 1]);
            if (flag == false) {
                // Tried to attacked but just found another zombie, so it moves
                this.move(obstaclesMatrix, charactersMatrix);
            }

        } else if (charactersMatrix[verticalPosition][horizontalPosition - 1] != null) {
            // Has a character near
            flag = this.attack(charactersMatrix[verticalPosition][horizontalPosition - 1]);
            if (flag == false) {
                // Tried to attacked but just found another zombie, so it moves
                this.move(obstaclesMatrix, charactersMatrix);
            }

        } else {
            // Didnt have anything to attack, so it moves{
            this.move(this.obstaclesMatrix, this.charactersMatrix);
        }
    }

}



/*
Useful layers 
Use final because wont be inherited
*/
final class WeakZombie extends BaseZombie {
    // Nothing special
    public WeakZombie(int verticalPosition, int horizontalPosition) {
        this.damage = 3;
        this.resistance = 5;
        this.speed = 1;
        this.jump = false;
        this.turns = 1;
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
    }
}

final class FastZombie extends BaseZombie {
    // Has more speed and can jump
    public FastZombie(int verticalPosition, int horizontalPosition) {
        this.damage = 3;
        this.resistance = 5;
        this.speed = 2;
        this.jump = true;
        this.turns = 1;
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
    }
}

final class StrongZombie extends BaseZombie {
    // Has more damage and resistance
    public StrongZombie(int verticalPosition, int horizontalPosition) {
        this.damage = 10;
        this.resistance = 10;
        this.speed = 1;
        this.turns = 1;
        this.jump = false;
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
    }
}
