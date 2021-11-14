package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class Mult extends Expression{

    public ArrayList<Expression> parts;

    public Mult(ArrayList<Expression> parts){
        super(parts);
        this.parts = new ArrayList<>(parts);
    }

    public static Expression create(Expression e1, Expression e2) {
        if(e1 instanceof Sum){
            Expression res = new Num(0);
            for(int i = 0; i<((Sum) e1).parts.size(); i++){
                res = res.sum(((Sum) e1).parts.get(i).mult(e2));
            }
            return res;
        }
        if(e2 instanceof Sum){
            Expression res = new Num(0);
            for(int i = 0; i<((Sum) e2).parts.size(); i++){
                res = res.sum(((Sum) e2).parts.get(i).mult(e1));
            }
            return res;
        }
        if(e1 instanceof Quotient){
            return ((Quotient) e1).parts.get(0).mult(e2).quot(((Quotient) e1).parts.get(1));
        }
        if(e2 instanceof Quotient){
            return e1.mult(((Quotient) e2).parts.get(0)).quot(((Quotient) e2).parts.get(1));
        }
        ArrayList<Expression> a = new ArrayList<>();
        a.add(new Num(1));
        if(e1 instanceof Num){ a.add(1, new Num(((Num) a.get(0)).getNum() * ((Num) e1).getNum())); a.remove(0);}
        else if(e1 instanceof Mult){
            a.addAll(1, ((Mult) e1).parts);
            a.add(1, new Num(((Num) a.get(0)).getNum() * ((Num) a.get(1)).getNum())); a.remove(0);
            a.remove(1);
        }
        else{
            a.add(e1);
        }
        if(e2 instanceof Num){ a.add(1, new Num(((Num) a.get(0)).getNum() * ((Num) e2).getNum())); a.remove(0);}
        else if(e2 instanceof Mult){
            a.addAll(1, ((Mult) e2).parts);
            a.add(1, new Num(((Num) a.get(0)).getNum() * ((Num) a.get(1)).getNum())); a.remove(0);
            a.remove(1);
        }
        else{
            a.add(e2);
        }
        if(((Num) a.get(0)).getNum() == 0) return new Num(0);
        if(a.size()==1) return new Num(((Num) a.get(0)).getNum());
        if(a.size()==2 && ((Num) a.get(0)).getNum()==1) return a.get(1).clone();
        return new Mult(a);
    }

    public ArrayList<Expression> _getParts(){
        return parts;
    }

    public Expression clone(){
        Expression res = new Num(1);
        for(int i=0; i<parts.size(); i++){
            res = res.mult(parts.get(i).clone());
        }
        return res;
    }

    public Expression derivate(char variable){
        Expression res = new Num(0);
        for(int i=0; i<parts.size(); i++){
            Expression temp = new Num(1);
            for(int j=0; j<parts.size(); j++){
                if(i==j) temp = temp.mult(parts.get(j).derivate(variable));
                else temp = temp.mult(parts.get(j));
            }
            res = res.sum(temp);
        }
        return res;
    }

    public Expression substitution(char x, double val){
        Expression res = new Num(1);
        for(int i=0; i<parts.size(); i++){
            res = res.mult(parts.get(i).substitution(x, val));
        }
        return res;
    }

    public double substitution(double val){
        if(this.variables().size()>1){
            throw new RuntimeException("Что-то пошло не так");
        }
        double res = 1;
        for(int i=0; i<parts.size(); i++){
            res *= parts.get(i).substitution(val);
        }
        return res;
    }

    public HashSet<Character> variables(){
        HashSet<Character> res = new HashSet<>();
        for(int i=0; i<parts.size(); i++){
            res.addAll(parts.get(i).variables());
        }
        return res;
    }

    public String toString(){
        int i=0;
        if(((Num) parts.get(0)).getNum() == 1) i=1;
        String res = parts.get(i).toString() + "*";
        for(i++; i< parts.size()-1; i++){
            res += parts.get(i).toString() + "*";
        }
        res += parts.get(parts.size()-1).toString();
        return res;
    }

}
