package com.company;

import java.util.ArrayList;

public class Multi extends Expression{
    Expression e1;
    Expression e2;
    public Multi(Expression val1, Expression val2){
        this.e1=val1;
        this.e2=val2;
    }
    public String toString(){
        return "("+e1+"*"+e2+")";
    }
    public Expression derivative(char diff_var){
        System.out.println("---------f,mwenfiknewfkjlew.-------------");
        System.out.println("lvl1 ex1 = "+e1);
        System.out.println("ex2 = "+e2);
        System.out.println("----------.fknerlk;fkerlgn;ermngkl------------");
        return new Sum(new Multi(e1.derivative(diff_var).create(),e2).create(),new Multi(e1,e2.derivative(diff_var).create()).create()).create();
    }
    public ArrayList listarg(ArrayList<Character> args){
        e1.listarg(args);
        e2.listarg(args);
        return args;
    }
    public Expression sub(char namevar, double var){
        return new Multi(e1.sub(namevar,var).create(),e2.sub(namevar,var).create());
    }
    public double sub(double var){
        return e1.sub(var)*e2.sub(var);
    }
    public Expression clon(){
        return new Multi(e1.clon().create(),e2.clon().create()).create();
    }
    public Expression create(){
//        System.out.println("lvl1 ex1 = "+e1);
//        System.out.println("ex2 = "+e2);
        if(e1.getClass()==Num.class && e2.getClass()==Num.class){
            return new Num(((Num)e1).num*((Num)e2).num);
        }
        if ((e1.getClass()==Num.class && ((Num)e1).num==0)||(e2.getClass()==Num.class && ((Num)e2).num==0)){
            return new Num(0);
        }
        if(e2.getClass()==Num.class && ((Num)e2).num==1){
            return e1;
        }
        if(e1.getClass()==Num.class && ((Num)e1).num==1){
            return e2;
        }
        else{
            System.out.println("----------------------");
            System.out.println("lvl1 ex1 = "+e1);
            System.out.println("ex2 = "+e2);
            System.out.println("----------------------");
            return new Multi(e1, e2);
        }
    }
}
