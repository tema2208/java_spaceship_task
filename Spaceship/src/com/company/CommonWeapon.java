package com.company;

public class CommonWeapon extends Weapon{
    public CommonWeapon(int size){
        this.size=size;
        price=size*100;
        range=size*0.25;
        damage=size*1.1;
        reloadtime=1.5;
    }
    public String toString(){
        return "Обычное оружие"+":размер="+size+";дальность действия="+range+";урон="+damage+";время перезарядки="+reloadtime+";цена="+price;
    }
}
