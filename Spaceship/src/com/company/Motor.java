package com.company;

public class Motor extends AbstractPart{
    protected final double MINCONST=0.1;
    protected double MaxConst;
    protected int Maxspeed;
    protected double speed;
    public void getSpeed(Corpus hull){
        speed=Maxspeed*size/((MaxConst-MINCONST)*hull.getSize());
    }
    public int getSize() {
        if(this==null)return 0;
        return size;
    }

    public double getPrice() {
        if(this==null)return 0;
        return price;
    }

    public double getMaxConst() {
        if(this==null)return 0;
        return MaxConst;
    }

    public double getMINCONST() {
        if(this==null) return 0;
        return MINCONST;
    }

    public double getSpeed() {
        if(this==null) return 0;
        return speed;
    }

    public int getMaxspeed() {
        if(this==null) return 0;
        return Maxspeed;
    }
}
