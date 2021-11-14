package com.company.factory;

public class LivingModule extends Moduls {
    private int size;
    private double cost;

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
        return "LivingModule{" +
                "size=" + size +
                ", cost=" + cost +
                '}';
    }
}