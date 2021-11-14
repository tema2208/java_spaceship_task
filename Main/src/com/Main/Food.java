package com.Main;

public class Food extends Item {
    private int satiety;
    public Food(String it_name, int weight, int value, int satiety){
        super(it_name, weight, value);
        this.satiety = satiety;
    }
    public Food(String it_name, int weight, int value, int satiety){
        this(it_name, weight, value, satiety);
    }
    public void print_Food(){
        System.out.println("Насыщение " + satiety);
    }
}
