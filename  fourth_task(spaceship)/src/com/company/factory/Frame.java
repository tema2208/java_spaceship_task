package com.company.factory;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public class Frame {
    private final int A_const = 80;
    private final int size;
    private final double cost;
    private final double armor;

    public Frame(int n, double cost, double armor){
        if (n >= 0 && armor >= 0){
            this.size = (int) round(A_const * pow(2,n/2));
            this.cost = cost;
            this.armor = armor;
        }
        else throw new ArithmeticException();
    }

    public int getSize(){
        return size;
    }

    public double getCost() {
        return cost;
    }

    public double getArmor() {
        return armor;
    }

    @Override
    public String toString() {
        return "Корпус:" +
                "размер =" + size +
                ", цена =" + cost +
                ", бронирование =" + armor +
                ';';
    }
}
