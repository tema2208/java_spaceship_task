package com.company;

import java.util.HashSet;

public class Num extends Expression{

    private double n;

    public Num(double x){
        super(x);
        n = x;
    }

    public double getNum(){
        return n;
    }

    public Expression clone(){
        return new Num(n);
    }

    public Expression derivate(char variable){
        return new Num(0);
    }

    public Expression substitution(char x, double val){
        return this.clone();
    }

    public double substitution(double val){
        return n;
    }

    public HashSet<Character> variables(){
        HashSet<Character> res = new HashSet<>();
        return res;
    }

    public String toString(){
        return Double.toString(n);
    }

}
