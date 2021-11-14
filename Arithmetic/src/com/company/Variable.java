package com.company;

import java.util.ArrayList;

public class Variable extends Expression{
    char name;
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
    public ArrayList listarg(ArrayList<Character> args){
        int i;
        for(i=0;i<args.size();i++) {
            if(args.get(i)==name)break;
        }
        if(i==args.size())args.add(name);
        return args;
    }
    public Expression sub(char namevar, double var){
        if (namevar==name)return new Num(var);
        else return new Variable(name);
    }
    public double sub(double var){
        return var;
    }
    public Expression clon(){
        return new Variable(name);
    }
    public Expression create(){
        return new Variable(name);
    }
}
