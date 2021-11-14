package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class Quotient extends Expression{

    public ArrayList<Expression> parts;

    public Quotient(Expression e1, Expression e2){
        super(e1, e2);
        parts = new ArrayList<Expression>();
        parts.add(e1); parts.add(e2);
    }

    public static Expression create(Expression e1, Expression e2){
        if(e1 instanceof Num && ((Num) e1).getNum() == 0) return new Num(0);
        if(e2 instanceof Num) return e1.mult(new Num(1/((Num) e2).getNum()));
        if(e1 instanceof Quotient) return ((Quotient) e1).parts.get(0).quot(((Quotient) e1).parts.get(1).mult(e2));
        if(e2 instanceof Quotient) return e1.mult(((Quotient) e2).parts.get(1).quot(((Quotient) e2).parts.get(0)));
        return new Quotient(e1, e2);
    }

    public ArrayList<Expression> _getParts(){
        return parts;
    }

    public Expression clone(){
        return parts.get(0).clone().quot(parts.get(1).clone());
    }

    public Expression derivate(char variable){
        return parts.get(0).derivate(variable).mult(parts.get(1)).sum(parts.get(0).mult(parts.get(1).derivate(variable).mult(new Num(-1)))).quot(parts.get(1).mult(parts.get(1)));
    }

    public Expression substitution(char x, double val){
        return parts.get(0).substitution(x, val).quot(parts.get(1).substitution(x, val));
    }

    public double substitution(double val){
        if(this.variables().size()>1){
            throw new RuntimeException("Что-то пошло не так");
        }
        return parts.get(0).substitution(val)/parts.get(1).substitution(val);
    }

    public HashSet<Character> variables(){
        HashSet<Character> res = new HashSet<>();
        for(int i=0; i<parts.size(); i++){
            res.addAll(parts.get(i).variables());
        }
        return res;
    }

    public String toString(){
        return "(" + parts.get(0).toString() + ")/(" + parts.get(1).toString() + ")";
    }

}
