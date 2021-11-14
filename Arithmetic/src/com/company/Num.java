package com.company;

import java.util.ArrayList;

public class Num extends Expression{
    double num;
    public Num(double value){
        this.num=value;
    }
    public String toString(){
        return String.valueOf(num);
    }
    public Expression derivative(char diff_var){
        return new Num(0);
    }
    public ArrayList listarg(ArrayList<Character> args){
        return args;
    }
    public Expression sub(char namevar, double var){
        return new Num(num);
    }
    public double sub(double var){
        return num;
    }
    public Expression clon(){
        return new Num(num);
    }
    public Expression create(){
        return new Num(num);
    }
}
