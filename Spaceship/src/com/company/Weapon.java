package com.company;

public class Weapon extends AbstractPart {
    protected double range;
    protected double damage;
    protected double reloadtime;
    public int getSize() {
        if(this==null)return 0;
        return size;
    }

    public double getPrice() {
        if(this==null)return 0;
        return price;
    }

    public double getDamage() {
        if (this==null)return 0;
        return damage;
    }

    public double getRange() {
        if(this==null) return 0;
        return range;
    }

    public double getReloadtime() {
        if(this==null) return 0;
        return reloadtime;
    }
}
