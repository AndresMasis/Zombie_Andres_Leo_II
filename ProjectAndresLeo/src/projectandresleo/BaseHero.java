/*
 This is the template for the heroes classes
 It describes how a hero should be
 It inherits from BaseClass
 */
package projectandresleo;
import java.util.LinkedList;

public class BaseHero extends BaseClass {
    // Attributes
    LinkedList<Items> ownedItems = new LinkedList<Items>();

    // Methods
    @Override
    public void move(String[][] matrix, BaseClass[][] position) {
        // Nothing yet
    }

    @Override
    public void applyTurn() {
        // Nothing yet
    }
    
    // Grabs an item
    public void grabItem(Items item) {
        //Restricciones
        if (item == null) {
            // Has not selected any item
            System.out.println("ERROR"
                    + "\nNo has seleccionado ningun objeto.");
        } else {
            item.amount += 1;
        }
    }

    // Equips an item
    public void equipItem(Items item) {
        if (item == null) {
            // Has not selected any item
            System.out.println("ERROR"
                    + "\nNo has seleccionado ningun objeto.");

        } else if (item.equipped) {
            // Already has that item equipped
            System.out.println("ERROR"
                    + "\nYa tienes equipado ese objeto");
            
        } else {
            // Was able equip the item with no problem
            item.setEquipado(true);
            this.assignStats(item);
        }
    }

    // Takes off an item
    public void unequipItem(Items item) {
        // Restricciones
        if (item == null || item.amount == 0) {
            // No se ha seleccionado ningun objeto o ya no se posee
            System.out.println("ERROR"
                    + "\nNo has seleccionado ningun objeto.");


        } else if (item.equipped == false) {
            // The item is not equipped
            System.out.println("ERROR"
                    + "\nNo tienes equipado este objeto"
                    + "\nPor lo tanto no lo puedes desequipar");

        } else {
            // Was able unequip the item with no problem
            item.setEquipado(false);
            this.quitStats(item);
        }
    }

    // Put the stats that the item increases to the hero
    public void assignStats(Items item) {
        // Increases the stats
        this.damage += item.damage;
        this.resistance += item.resistance;
    }

    // Removes the stats that the item increases to the hero
    public void quitStats(Items item) {
        // Decreases the stats
        this.damage -= item.damage;
        this.resistance -= item.resistance;
    }
    // recoger item
    // equipar y desequipar
    // inventario

    // clase Items
}
