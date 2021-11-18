package com.company.factory;

public class SuperEngine extends Engine{
    int size;
    double cost;

    protected SuperEngine(int size, double cost){
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
