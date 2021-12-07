package com.company;

public class LivingUnit extends AbstractPart {
    public LivingUnit(int size){
        price=size*20;
        this.size=size;
    }
    public String toString(){
        return "Жилой модуль:размер="+size+";цена="+price;
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
