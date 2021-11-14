package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class Power extends Expression{

    ArrayList<Expression> parts;

    public Power(Expression e1, Expression e2){
        super(e1, e2);
        parts = new ArrayList<Expression>();
        parts.add(e1); parts.add(e2);
    }

    public static Expression create(Expression e1, Expression e2){
        if(e2 instanceof Num && ((Num) e2).getNum() == 0 || e1 instanceof Num && ((Num) e1).getNum() == 1) return new Num(1);
        if(e1 instanceof Num && ((Num) e1).getNum() == 0) return new Num(0);
        if(e2 instanceof Num && ((Num) e2).getNum() == 1) return e1.clone();
        return new Power(e1, e2);
    }

    public Expression clone(){
        return parts.get(0).clone().pow(parts.get(1).clone());
    }

    public Expression derivate(char variable){
        return parts.get(0).pow(parts.get(1)).mult(parts.get(0).ln().mult(parts.get(1)).derivate(variable));
    }

    public Expression substitution(char x, double val){
        return parts.get(0).substitution(x, val).pow(parts.get(1).substitution(x, val));
    }

    public double substitution(double val){
        if(this.variables().size()>1){
            throw new RuntimeException("Что-то пошло не так");
        }
        return Math.pow(parts.get(0).substitution(val), parts.get(1).substitution(val));
    }

    public HashSet<Character> variables(){
        HashSet<Character> res = new HashSet<>();
        for(int i=0; i<parts.size(); i++){
            res.addAll(parts.get(i).variables());
        }
        return res;
    }

    public String toString(){
        return "(" + parts.get(0).toString() + ")^(" + parts.get(1).toString() + ")";
    }

}
