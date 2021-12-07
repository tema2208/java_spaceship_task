package com.company;

public class LandingUnit extends AbstractPart{
    public LandingUnit(int size){
        this.size=size;
        price=size*30;
    }
    public String toString(){
        return "Модуль десантирования:размер="+size+";цена="+price;
    }
    public int getSize() {
        if(this==null)return 0;
        return size;
    }

    public double getPrice() {
        if(this==null)return 0;
        return price;
    }
}
