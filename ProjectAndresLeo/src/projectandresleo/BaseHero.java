/*
 This is the template for the heroes classes
 It describes how a hero should be
 It inherits from BaseClass
 */
package projectandresleo;

import java.util.LinkedList;

public abstract class BaseHero extends BaseClass {

    // Attributes
    protected LinkedList<Items> ownedItems = new LinkedList<>();
    int maxStorage;

    // Methods
    @Override
    public boolean attack(BaseClass enemy) {
        // Nothing yet
        return true;
    }

    @Override
    public void move(String[][] matrix, BaseClass[][] position) {
        // Nothing yet
        // This method must be done by Leo
    }

    @Override
    public void applyTurn() {
        // Nothing yet
        // This method must be done by Leo
    }

    // Grabs an item
    public void grabItem(Items item) {
        // Restrictions
        if (item == null) {
            // Has not selected any item
            System.out.println("ERROR"
                    + "\nNo has seleccionado ningun objeto.");

        } else if (ownedItems.size() > maxStorage) {
            // Coludnt pack the item
            System.out.println("El inventario esta lleno"
                    + "\nPor lo tanto no puedes agarrar ese item");
        } else {
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
        if (item == null) {
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

    public abstract void changeSpecialStats(Items item, int operation);

    // Put the stats that the item increases to the hero
    public void assignStats(Items item) {
        // If needed modifies the stats of the item depending on the character
        this.changeSpecialStats(item, 1);
        // Checks what kind of item enters
        if (item instanceof Weapon) {
            // It is a weapon
            this.damage += ((Weapon) item).damage;
        } else if (item instanceof Weapon){
            // It is an armor
            this.resistance += ((Armor) item).resistance;
        }
        this.changeSpecialStats(item, 2);
    }

    // Removes the stats that the item increases to the hero
    public void quitStats(Items item) {
        // If needed modifies the stats of the item depending on the character
        this.changeSpecialStats(item, 2);

        // Checks what kind of item enters
        if (item instanceof Weapon) {
            // It is a weapon
            this.damage -= ((Weapon) item).damage;
        } else if (item instanceof Armor){
            // It is an armor
            this.resistance -= ((Armor) item).resistance;
        }
    }
}

/*
Useful layers 
Use final because wont be inherited
 */
final class Destroyer extends BaseHero {

    // Has more damage and resistance
    public Destroyer(int verticalPosition, int horizontalPosition) {
        this.damage = 8;
        this.resistance = 35;
        this.speed = 3;
        this.turns = 1;
        this.jump = false;
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
    }

    // Increases the damage of any weapon
    @Override
    public void changeSpecialStats(Items item, int operation) {
        if (item instanceof Weapon) {
            if (operation == 1) {
                //Sums the stat
                ((Weapon) item).damage += 3;
            } else {
                //Substracts the stat
                ((Weapon) item).damage -= 3;
            }
        }
    }
}

final class Explorer extends BaseHero {

    // Has more speed, can jump and can grab an item of any part of the map
    public Explorer(int verticalPosition, int horizontalPosition) {
        this.damage = 3;
        this.resistance = 25;
        this.speed = 5;
        this.turns = 1;
        this.jump = true;
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
    }

    @Override
    public void changeSpecialStats(Items item, int operation) {
        // This hero does not modify any special stat
    }

}

final class Snipper extends BaseHero {

    // Has double turn
    public Snipper(int verticalPosition, int horizontalPosition) {
        this.damage = 3;
        this.resistance = 15;
        this.speed = 2;
        this.turns = 2;
        this.jump = false;
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
    }

    // Increases the damage and the range of the long range weapon
    @Override
    public void changeSpecialStats(Items item, int operation) {
        if (item instanceof LongRangeWeapon) {
            if (operation == 1) {
                //Sums the stat
                ((LongRangeWeapon) item).damage += 3;
                ((LongRangeWeapon) item).range += 4;
            } else {
                //Substracts the stat
                ((LongRangeWeapon) item).damage -= 3;
                ((LongRangeWeapon) item).range -= 4;
            }
        }
    }
}
