package com.company.factory;

public class LivingModule extends Moduls {

    protected LivingModule(int size, double cost) {
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
        return "Жилой модуль:" +
                "размер =" + size +
                ", цена =" + cost +
                ';';
    }
}
