package com.company;

public class CommonMotor extends Motor{
    public CommonMotor(int size, double maxconst){
        price=size*100;
        this.size=size;
        MaxConst=maxconst;
        Maxspeed=300;
    }

    @Override
    public String toString() {
        return "Двигатель"+":размер="+size+";цена="+price+";поддерживаемая скорость="+speed;
    }
}
