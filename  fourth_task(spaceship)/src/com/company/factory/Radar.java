package com.company.factory;

public class Radar extends Moduls{
    private int size;
    private double cost;
    private double range;
    private double detection_force;

    protected Radar(int size, double cost, double range, double detection_force) {
        this.size = size;
        this.cost = cost;
        this.range = range;
        this.detection_force = detection_force;
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
        return "Радар:" +
                "размер =" + size +
                ", цена=" + cost +
                ", область дествия =" + range +
                ", сила распознавания =" + detection_force +
                ';';
    }
}
