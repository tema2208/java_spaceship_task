package com.company;

import java.util.ArrayList;

public class Exponent extends Expression{
    Expression base;
    Num power;
    public Exponent(Expression val1, Num val2){
        this.base=val1;
        this.power=val2;
    }
    public String toString(){
        return base+"^"+power;
    }
    public Expression derivative(char diff_var) {
        return new Multi(new Multi(power,new Exponent(base.create(),new Num(power.num-1)).create()),base.derivative(diff_var).create()).create();
    }
    public ArrayList listarg(ArrayList<Character> args){
        base.listarg(args);
        return args;
    }
    public Expression sub(char namevar, double var){
        return new Exponent(base.sub(namevar,var).create(), (Num) power.sub(namevar,var).create()).create();
    }
    public double sub(double var) {
        return Math.pow(base.sub(var), power.sub(var));
    }
    public Expression clon(){
        return new Exponent(base.clon().create(), (Num) power.clon().create()).create();
    }
    public Expression create(){
        if(base.getClass()==Num.class){
            return new Num(Math.pow(((Num)base).num,power.num));
        }
        if (power.num==0){
            return new Num(1);
        }
        if(power.num==1){
            return base;
        }
        if(base.getClass()==Num.class && ((Num)base).num==1){
            return new Num(1);
        }
        else return new Exponent(base,power);
    }
}
