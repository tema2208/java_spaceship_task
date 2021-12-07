package com.company;

public class StrategicWeapon extends Weapon{
    public StrategicWeapon(int size){
        this.size=size;
        price=size*110;
        range=size*0.15;
        damage=size*1.3;
        reloadtime=0.8;
    }
    public String toString(){
        return "Стратегическое оружие"+":размер="+size+";дальность действия="+range+";урон="+damage+";время перезарядки="+reloadtime+";цена="+price;
    }
}
