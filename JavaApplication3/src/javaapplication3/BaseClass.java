/*
 This is the template for the rest of the clases.
This class contains the most general attributes and methods
 */
package javaapplication3;

import java.util.LinkedList;

/**
 *
 * @author Andrés
 */
public abstract class BaseClass {

    // atributtes
    int damage;
    int speed;
    int resistance;
    int horizontalPosition;
    int verticalPosition;
    String type;
    boolean jump;
    int turns;
    int vision;
    int numT;
    int mountItem;
    LinkedList<Items> ownedItems = new LinkedList<Items>();
    String category;
    int noise;

    // methods
    public void attack(BaseClass enemy) {
        // Checks that the enemy is really an enemy
        if (enemy.type != this.type) {
            // Substracts life of the character who is being attacked
            enemy.resistance -= this.damage;
        }
    }
        // Attributes
}
