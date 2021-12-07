package com.company;

public class Corpus extends AbstractPart{
    private double armor;
    private final int valueA=90;
    public Corpus(double armor, int n){
        this.armor=armor;
        size= (int)(valueA*Math.pow(2,n));
        price=size*10+armor*20;
    }
    public String toString(){
        return "Корпус:бронирование="+armor+";размер="+size+";цена="+price;
    }

    public double getArmor() {
        if(this==null)return 0;
        return armor;
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
