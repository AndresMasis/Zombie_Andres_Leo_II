package javaapplication3;

public class Items {

    // Atributtes
    int type;
    String name;
    boolean equipped;
    int amount;
    int range;
    int noise;
    
    int horizontalPosition;
    int verticalPosition;
    
    int damage;  //Stat
    int resistance;  //Stat

    public void setEquipado(boolean equipado) {
        this.equipped = equipado;
    }
}

class Axe extends Items {
    public Axe() {
        this.type = 11;
        this.name = "Viking Axe";
        this.equipped = false;
        this.amount = 0;
        this.range = 1;
        this.noise = 1;
        this.damage = 3; 
        this.resistance = 0;  
    }
}

class Sword extends Items{
    public Sword() {
        this.type = 10;
        this.name = "Samurai sword";
        this.equipped = false;
        this.amount = 0;
        this.range = 1;
        this.noise = 1;
        this.damage = 5;  
        this.resistance = 0;  
    }

}

class Rifle extends Items{
    public Rifle(){
        this.type = 13;
        this.name = "Rifle";
        this.equipped = false;
        this.amount = 0;
        this.range = 5;
        this.noise = 5;
        this.damage = 5;  
        this.resistance = 0;  
    }
}


class SmallGun extends Items{
    public SmallGun(){
        this.type = 12;
        this.name = "Small Gun";
        this.equipped = false;
        this.amount = 0;
        this.range = 3;
        this.noise = 2;
        this.damage = 2;  
        this.resistance = 0;  
    }
}


class Helmet extends Items{
    public Helmet(){
        this.type = 18;
        this.name = "helmet";
        this.equipped = false;
        this.amount = 0;
        this.range = 0;
        this.noise = 0;
        this.damage = 0;  
        this.resistance = 3;  
    }
}


class Vest extends Items{
    public Vest(){
        this.type = 14;
        this.name = "vest";
        this.equipped = false;
        this.amount = 0;
        this.range = 0;
        this.noise = 0;
        this.damage = 0;  
        this.resistance = 5;  
    }
}


class Pants extends Items{
    public Pants(){
        this.type = 15;
        this.name = "pants";
        this.equipped = false;
        this.amount = 0;
        this.range = 0;
        this.noise = 0;
        this.damage = 0;  
        this.resistance = 1;  
    }
}

class Boots extends Items{
    public Boots(){
        this.type = 16;
        this.name = "boots";
        this.equipped = false;
        this.amount = 0;
        this.range = 0;
        this.noise = 0;
        this.damage = 0;  
        this.resistance = 2;  
    }
}

class SpecialItem extends Items{
    public SpecialItem(){
        this.type = 17;
        this.name = "Super Duper Item";
        this.equipped = false;
        this.amount = 0;
        this.range = 10;
        this.noise = 2;
        this.damage = 15;  
        this.resistance = 15;  
    }
    
}
