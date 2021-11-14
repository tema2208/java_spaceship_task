package com.company;

import java.util.HashSet;

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
    public HashSet<Character> listarg(HashSet<Character> args){
        return args;
    }
    public Expression substitute(char namevar, double var){
        return new Num(num);
    }
    public double substitute(double var){
        return num;
    }
    public Expression clon(){
        return new Num(num);
    }
}
