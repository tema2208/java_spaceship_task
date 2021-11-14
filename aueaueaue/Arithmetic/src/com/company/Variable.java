package com.company;

import java.util.HashSet;

public class Variable extends Expression{

    private char v;

    public Variable(char v){
        super(v);
        this.v = v;
    }

    public char getVariable(){
        return v;
    }

    public Expression clone(){
        return new Variable(v);
    }

    public Expression derivate(char variable){
        if(v == variable) return new Num(1);
        return new Num(0);
    }

    public Expression substitution(char x, double val){
        if(v == x) return new Num(val);
        return this.clone();
    }

    public double substitution(double val){
        return val;
    }

    public HashSet<Character> variables(){
        HashSet<Character> res = new HashSet<>();
        res.add(v);
        return res;
    }

    public String toString(){
        return Character.toString(v);
    }

}
