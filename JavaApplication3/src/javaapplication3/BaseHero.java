/*
 This is the template for the heroes classes
 It describes how a hero should be
 It inherits from BaseClass
 */
package javaapplication3;

import java.util.LinkedList;

public class BaseHero extends BaseClass {

    // Attributes
    LinkedList<Items> ownedItems = new LinkedList<Items>();
    String category;

    // Grabs an item
    public void grabItem(Items item) {
        // Restrictions
        if (item == null) {
            // Has not selected any item
            System.out.println("ERROR"
                    + "\nNo has seleccionado ningun objeto.");
        } else {
            // Gets the item
            this.ownedItems.add(item);
        }
    }

    // Equips an item
    public void equipItem(Items item) {
        // Restrictions
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
        // Restrictions
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
        // Checks if is a special hero
        if (item.type.equals("weapon") && this.equals("destroyer")) {
            // Destroyer increases the damage of all weapons
            item.damage -= 3;
        } else if (item.type.equals("weapon") && item.range > 1 && this.equals("snipper")){
            // Snipper increases the range damage of long range weapons
            item.damage -= 3;
            item.range -= 3;
        }

        // Decreases the stats
        this.damage += item.damage;
        this.resistance += item.resistance;
    }

    // Removes the stats that the item increases to the hero
    public void quitStats(Items item) {
        // Checks if is a special hero
        if (item.type.equals("weapon") && this.equals("destroyer")) {
            // Destroyer increases the damage of all weapons
            item.damage += 3;
        } else if (item.type.equals("weapon") && item.range > 1 && this.equals("snipper")){
            // Snipper increases the range damage of long range weapons
            item.damage += 3;
            item.range += 3;
        }
        
        // Decreases the stats
        this.damage -= item.damage;
        this.resistance -= item.resistance;
    }
}

class Destroyer extends BaseHero {

    // Has more damage and resistance
    public Destroyer(int verticalPosition, int horizontalPosition) {
        this.damage = 8;
        this.resistance = 35;
        this.speed = 1;
        this.turns = 1;
        this.type = "hero";
        this.category = "destroyer";
        this.jump = false;
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
        this.numT = 4;
    }
}

class Explorer extends BaseHero {

    // Has more speed, can jump and can grab an item of any part of the map
    public Explorer(int verticalPosition, int horizontalPosition) {
        this.damage = 3;
        this.resistance = 25;
        this.speed = 2;
        this.turns = 1;
        this.type = "hero";
        this.category = "explorer";
        this.jump = true;
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
        this.numT = 3;
    }

}

class Snipper extends BaseHero {

    // Has double turn, increases the damage and the range of the long range weapon
    public Snipper(int verticalPosition, int horizontalPosition) {
        this.damage = 3;
        this.resistance = 15;
        this.speed = 1;
        this.turns = 10;
        this.type = "hero";
        this.category = "snipper";
        this.jump = false;
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
        this.numT = 2;
    }
}
