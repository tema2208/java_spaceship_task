package com.company.factory;

public class StandartEngine extends Moduls {
    private int size;  // Скажем, не менее 1/12 от объёма корпуса. И до 1/2 у плохого двигателя или до 1/8 у хорошего.
    private double cost;
    private String name;
    private double speed;

    protected StandartEngine(int size, double cost, String name, double speed){
        this.size = size;
        this.cost = cost;
        this.name = name;
        this.speed = speed;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StandartEngine{" +
                "size=" + size +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }
}
