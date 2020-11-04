/*
 This is the template for the rest of the clases.
This class contains the most general attributes and methods
 */
package projectandresleo;

/**
 *
 * @author Andrés
 */
public abstract class BaseClass {
    
    // atributtes
    protected int damage;
    protected int speed;
    protected int resistance;
    protected int horizontalPosition;
    protected int verticalPosition;
    protected String type;
    protected int turn;
    
    
    // methods
    public void attack(BaseClass enemy){
        // Substracts life of the character who is being attacked
        enemy.resistance -= this.damage;
    }
    public abstract void move(String[][] matrix, BaseClass[][] position);
    public abstract void applyTurn();
    
}
