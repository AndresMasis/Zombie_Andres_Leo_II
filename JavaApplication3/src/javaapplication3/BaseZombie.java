/*
 This is the template for the zombie classes
 It describes how a zombie should be
 It inherits from BaseClass
 */
package javaapplication3;

/**
 *
 * @author Andrés
 */
public class BaseZombie extends BaseClass {

    Items sword = new Sword();
    Items axe = new Axe();
    Items rifle = new Rifle();
    Items smallGun = new SmallGun();
    Items helmet = new Helmet();
    Items vest = new Vest();
    Items pants = new Pants();
    Items boots = new Boots();

    Items[] allItems = {sword, pants, rifle, helmet, smallGun, axe, boots, vest};
}

class WeakZombie extends BaseZombie {

    // Nothing special
    public WeakZombie(int verticalPosition, int horizontalPosition) {
        this.damage = 3;
        this.resistance = 5;
        this.speed = 1;
        this.jump = false;
        this.turns = 1;
        this.type = "zombie";
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
        this.vision = 2;
        this.numT = 5;
    }
}

class FastZombie extends BaseZombie {

    // Has more speed and can jump
    public FastZombie(int verticalPosition, int horizontalPosition) {
        this.damage = 3;
        this.resistance = 5;
        this.speed = 2;
        this.jump = true;
        this.turns = 1;
        this.type = "zombie";
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
        this.vision = 3;
        this.numT = 6;
    }
}

class StrongZombie extends BaseZombie {

    // Has more damage and resistance
    public StrongZombie(int verticalPosition, int horizontalPosition) {
        this.damage = 10;
        this.resistance = 10;
        this.speed = 1;
        this.turns = 1;
        this.jump = false;
        this.type = "zombie";
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
        this.vision = 3;
        this.numT = 7;
    }
}

