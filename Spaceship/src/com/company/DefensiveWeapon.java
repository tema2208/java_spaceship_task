package com.company;

public class DefensiveWeapon extends Weapon{
    public DefensiveWeapon(int size){
        this.size=size;
        price=size*100;
        range=size*0.4;
        damage=size*0.9;
        reloadtime=1.5;
    }
    public String toString(){
        return "Оборонительное оружие"+":размер="+size+";дальность действия="+range+";урон="+damage+";время перезарядки="+reloadtime+";цена="+price;
    }
}
