package com.company.factory;

public class Armament extends Moduls {
    private int size;
    private double cost;
    private double range;
    private double recharge_time;
    private double damage;
    private ArmamentType type;


    protected Armament(int size, double cost, double range, double recharge_time, double damage,ArmamentType type) {
        this.size = size;
        this.cost = cost;
        this.range = range;
        this.recharge_time = recharge_time;
        this.damage = damage;
        this.type = type;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public double getCost() {
        return cost;
    }

    public double getRange() {
        return range;
    }

//    public void setRange(double range) {
//        this.range = range;
//    }

    public double getRecharge_time() {
        return recharge_time;
    }

    public double getDamage() {
        return damage;
    }

    public ArmamentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Вооружение" +
                ", размеры =" + size +
                ", цена =" + cost +
                ", дальность действия=" + range +
                ", время перезарядки =" + recharge_time +
                ", урон =" + damage +
                ", тип оружия =" + type +
                ';';
    }

//    public void setRecharge_time(double recharge_time) {
//        this.recharge_time = recharge_time;
//    }
}
