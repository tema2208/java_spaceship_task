package com.company;

import java.util.HashSet;

public class Variable extends Expression{
    public char name;
    public Variable(char n){
        this.name=n;
    }
    public String toString(){
        return String.valueOf(name);
    }
    public Expression derivative(char diff_var){
        if(name==diff_var){
            return new Num(1);
        }
        else{
            return new Num(0);
        }
    }
    public HashSet<Character> listarg(HashSet<Character> args){
        if(args.add(name)) args.add(name);
        return args;
    }
    public Expression substitute(char namevar, double var){
        if (namevar==name) return new Num(var);
        else return new Variable(name);
    }
    public double substitute(double var){
        return var;
    }
    public Expression clon(){
        return new Variable(name);
    }
}
