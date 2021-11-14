package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Expression {

    public Expression(double n){}

    public Expression(char v){}

    public Expression(Expression e){}

    public Expression(Expression e1, Expression e2){}

    public Expression(ArrayList<Expression> parts){}

    public Expression sum(Expression e){
        return Sum.create(this, e);
    }

    public Expression mult(Expression e){
        return Mult.create(this, e);
    }

    public Expression quot(Expression e){
        return Quotient.create(this, e);
    }

    public Expression pow(Expression e){
        return Power.create(this, e);
    }

    public Expression ln(){
        return Ln.create(this);
    }

    public abstract Expression clone();

    public abstract Expression derivate(char variable);

    public abstract HashSet<Character> variables();

    public abstract Expression substitution(char x, double val);

    public abstract double substitution(double val);

}
