package com.company.factory;

public class StandartEngine extends Engine{
    private int size;  // Скажем, не менее 1/12 от объёма корпуса. И до 1/2 у плохого двигателя или до 1/8 у хорошего.
    private double cost;

    protected StandartEngine(int size, double cost){
        this.size = size;
        this.cost = cost;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "StandartEngine{" +
                "size=" + size +
                ", cost=" + cost +
                '}';
    }
}
