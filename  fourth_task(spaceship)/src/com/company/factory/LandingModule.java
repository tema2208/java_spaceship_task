package com.company.factory;

public class LandingModule extends Moduls {
    private int size;
    private double cost;

    protected LandingModule(int size, double cost) {
        this.size = size;
        this.cost = cost;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Посадочный модуль:" +
                "размер =" + size +
                ", цена =" + cost +
                ';';
    }
}
