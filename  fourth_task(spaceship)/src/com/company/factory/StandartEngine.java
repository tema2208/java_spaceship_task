package com.company.factory;


public class StandartEngine extends Engine{
    private final int size;  // Скажем, не менее 1/12 от объёма корпуса. И до 1/2 у плохого двигателя или до 1/8 у хорошего.
    private final double cost;
    private final double lower_const = (double) 1/12;
    private double apper_const;
    private double max_speed = 100;
    private double speed;

    protected StandartEngine(int size, double cost){
        this.size = size;
        this.cost = cost;
        if (cost > 500) this.apper_const = (double) 1/4; // значит создали хороший двигатель. 500 просто так сделал
        else this.apper_const = (double) 1/2;
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
        return "Двигатель системы:" +
                "размером = " + size +
                ", стоимость = " + cost +
                ", скорость = " + speed +
                ';';
    }

    @Override
    public int checkEngine(Frame hull){
        if (size < hull.getSize()* lower_const && size > apper_const * hull.getSize()) return 0;
        else return 1;
    }

    public void setSpeed(Frame hull){
        this.speed = (double) max_speed * (double) size / ( (double) apper_const * hull.getSize() - (double) lower_const * hull.getSize());
    }

}
