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
    protected boolean jump;
    protected int turns;

    // methods
    public abstract boolean attack(BaseClass enemy);

    public abstract void move(String[][] matrix, BaseClass[][] position);

    public abstract void applyTurn();

}
