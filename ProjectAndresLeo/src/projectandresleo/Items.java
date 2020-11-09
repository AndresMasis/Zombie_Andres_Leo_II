/*
Here you can find all the classes of Items kind
Fisrt there is Items class, which is the most basic and is the start of everything
Then are Weapons and Armors, which are "children" of Items*
From Weapons is created LongRangeWeapons
At the very last bottom there are the useful classes (the ones that will be instanced)*/
package projectandresleo;

// First level, uses abstract because wont be instantiated 
public abstract class Items {
    // Atributtes
    String name;
    boolean equipped;

    // Methods
    public void setEquipado(boolean equipado) {
        this.equipped = equipado;
    }

    // Constructor
    public Items() {
        this.equipped = false;
    }

}

// Second level, uses abstract because wont be instantiated 
abstract class Weapon extends Items {
    //Attributes
    int damage;
    
    // Constructor
    public Weapon() {
        super();
    }
}

abstract class Armor extends Items {
    // Constructor
    int resistance;
    
    public Armor() {
        super();
    }
}


// Third level, uses abstract because wont be instantiated 
abstract class LongRangeWeapon extends Weapon {
    // Attributes
    int range;
    int noise;

    // Constructor
    public LongRangeWeapon() {
        super();
    }
}


// Last level, uses final becuase wont be inherited
final class Axe extends Weapon {
    // Constructor
    public Axe() {
        super();
        this.name = "Viking Axe";
        this.damage = 3;
    }
}

final class Sword extends Weapon {
    // Constructor
    public Sword() {
        super();
        this.name = "Samurai sword";
        this.damage = 5;
    }
}

final class Rifle extends LongRangeWeapon {
    // Constructor
    public Rifle() {
        super();
        this.name = "Rifle";
        this.damage = 5;
        this.range = 5;
        this.noise = 4;
    }
}

final class SmallGun extends LongRangeWeapon{
    // Constructor
    public SmallGun() {
        super();
        this.name = "Small Gun";
        this.damage = 2;
        this.range = 3;
        this.noise = 2;
    }
}

final class Helmet extends Armor{
    // Constructor
    public Helmet() {
        super();
        this.name = "helmet";
        this.resistance = 3;
    }
}

final class Vest extends Armor{
    // Constructor
    public Vest() {
        super();
        this.name = "vest";
        this.resistance = 5;
    }
}

final class Pants extends Armor{
    // Constructor
    public Pants() {
        super();
        this.name = "pants";
        this.resistance = 1;
    }
}

final class Boots extends Armor{
    // Constructor
    public Boots() {
        super();
        this.name = "boots";
        this.resistance = 2;
    }
}


