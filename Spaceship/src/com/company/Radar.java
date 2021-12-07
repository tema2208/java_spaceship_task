package com.company;

public class Radar extends AbstractPart {
    private double radius;
    private double accuracy;
    public Radar(int size){
        radius=size*1.5;
        accuracy=size*0.1;
        this.size=size;
        price=size*15;
    }
    public String toString(){
        return "Радар:размер="+size+";радиус действия="+radius+";сила обнаружения="+accuracy+";цена="+price;
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
