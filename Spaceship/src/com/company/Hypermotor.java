package com.company;

public class Hypermotor extends Motor{
    public Hypermotor(int size, double maxconst){
        price=size*500;
        this.size=size;
        MaxConst=maxconst;
        Maxspeed=1000;
    }

    @Override
    public String toString() {
        return "Гипердвигатель"+":размер="+size+";цена="+price+";поддерживаемая скорость в ="+speed;
    }
}
