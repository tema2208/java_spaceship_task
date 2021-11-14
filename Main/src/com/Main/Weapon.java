package com.Main;

public class Weapon extends Item {

    private int damage;

    public Weapon(String name, int weight, int value, int damage){
        super(name, weight, value);
        this.damage = damage;
    }


    public String toString(){
        return "Имя " + super.name + "; весом " + super.weight + " ценностью " + value + " и уроном " + damage;
    }
}
