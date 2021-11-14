package com.Main;

public class Item {
    public String name;
    public int weight;
    public int value;
    public Item(String name, int weight, int value){
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
    public String toString(){
        return "Имя " + name + "; весом " + weight + " и ценностью " + value;
    }
}
