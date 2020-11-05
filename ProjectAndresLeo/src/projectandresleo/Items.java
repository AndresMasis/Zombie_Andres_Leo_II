package projectandresleo;

public class Items {

    // Atributtes
    String type;
    String name;
    boolean equipped;
    int amount;
    int range;
    int noise;

    int damage;  //Stat
    int resistance;  //Stat

    public void setEquipado(boolean equipado) {
        this.equipped = equipado;
    }
}

class Axe extends Items {
    public Axe() {
        this.type = "weapon";
        this.name = "Viking Axe";
        this.equipped = false;
        this.amount = 0;
        this.range = 1;
        this.noise = 0;
        this.damage = 3;  
        this.resistance = 0;  
    }
}

class Sword extends Items{
    public Sword() {
        this.type = "weapon";
        this.name = "Samurai sword";
        this.equipped = false;
        this.amount = 0;
        this.range = 1;
        this.noise = 0;
        this.damage = 5;  
        this.resistance = 0;  
    }

}

class Rifle extends Items{
    public Rifle(){
        this.type = "weapon";
        this.name = "Rifle";
        this.equipped = false;
        this.amount = 0;
        this.range = 5;
        this.noise = 4;
        this.damage = 5;  
        this.resistance = 0;  
    }
}


class SmallGun extends Items{
    public SmallGun(){
        this.type = "weapon";
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
        this.type = "armor";
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
        this.type = "armor";
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
        this.type = "armor";
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
        this.type = "armor";
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
        this.type = "armor";
        this.name = "Super Duper Item";
        this.equipped = false;
        this.amount = 0;
        this.range = 10;
        this.noise = 2;
        this.damage = 15;  
        this.resistance = 15;  
    }
    
}
