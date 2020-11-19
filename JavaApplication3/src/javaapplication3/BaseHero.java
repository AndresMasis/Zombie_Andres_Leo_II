/*
 This is the template for the heroes classes
 It describes how a hero should be
 It inherits from BaseClass
 */
package javaapplication3;

import java.util.LinkedList;

public class BaseHero extends BaseClass {

    String category;    
}

class Destroyer extends BaseHero {

    // Has more damage and resistance
    public Destroyer(int verticalPosition, int horizontalPosition) {
        this.damage = 8;
        this.resistance = 25;
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
        this.resistance = 35;
        this.speed = 1;
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
        this.resistance = 25;
        this.speed = 1;
        this.turns = 1;
        this.type = "hero";
        this.category = "snipper";
        this.jump = false;
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
        this.numT = 2;
    }
}
