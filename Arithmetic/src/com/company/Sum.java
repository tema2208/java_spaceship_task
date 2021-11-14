package com.company;

import java.util.ArrayList;

public class Sum extends Expression{
    Expression e1;
    Expression e2;
    public Sum(Expression val1, Expression val2){
        this.e1=val1;
        this.e2=val2;
    }
    public String toString(){
        return "("+e1+"+"+e2+")";
    }
    public Expression derivative(char diff_var){
        return new Sum(e1.derivative(diff_var).create(),e2.derivative(diff_var).create()).create();
    }
    public ArrayList listarg(ArrayList<Character> args){
        e1.listarg(args);
        e2.listarg(args);
        return args;
    }
    public Expression sub(char namevar, double var){
        return new Sum(e1.sub(namevar,var).create(),e2.sub(namevar,var).create()).create();
    }
    public double sub(double var){
        return e1.sub(var)+e2.sub(var);
    }
    public Expression clon() {
        return new Sum(e1.clon().create(), e2.clon().create()).create();
    }
    public Expression create(){
        if(e1.getClass()==Num.class && e2.getClass()==Num.class){
            return new Num(((Num)e1).num+((Num) e2).num);
        }
        if (e1.getClass()==Num.class && ((Num)e1).num==0){
            return e2;
        }
        if(e2.getClass()==Num.class && ((Num)e2).num==0){
            return e1;
        }
        else return new Sum(e1, e2);
    }
}
