package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class Ln extends Expression{

    ArrayList<Expression> parts;

    public Ln(Expression e){
        super(e);
        parts = new ArrayList<Expression>();
        parts.add(e);
    }

    public static Expression create(Expression e){
        if(e instanceof Num) return new Num(Math.log(((Num) e).getNum()));
        return new Ln(e);
    }

    public Expression clone(){
        return parts.get(0).clone().ln();
    }

    public Expression derivate(char variable){
        return parts.get(0).derivate(variable).quot(parts.get(0));
    }

    public Expression substitution(char x, double val){
        return parts.get(0).substitution(x, val).ln();
    }

    public double substitution(double val){
        if(this.variables().size()>1){
            throw new RuntimeException("Что-то пошло не так");
        }
        return Math.log(parts.get(0).substitution(val));
    }

    public HashSet<Character> variables(){
        HashSet<Character> res = new HashSet<>();
        for(int i=0; i<parts.size(); i++){
            res.addAll(parts.get(i).variables());
        }
        return res;
    }

    public String toString(){
        return "ln(" + parts.get(0) + ")";
    }

}
