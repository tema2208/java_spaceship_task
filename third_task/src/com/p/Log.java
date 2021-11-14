package com.p;

import java.util.HashSet;

public class Log extends Expressions{
    protected Expressions expr;

    private Log(Expressions expr) {
        this.expr = expr;
    }

    @Override
    public HashSet<String> hsArgs(HashSet<String> hs) {
        expr.hsArgs(hs);
        return hs;
    }

    @Override
    public double expressionValue(double arg) {
//        try {
//            this.checkNumberOfVariables();
//        } catch (IllegalArgumentException e){
//            System.out.println("  выражении более одной переменной");
//        }
        if(this.createHs().size() == 1) {
            return Math.log(expr.expressionValue(arg));
        }
        else throw new IllegalArgumentException();
    }

    @Override
    public Expressions expressionValue(String name, double arg) {
        return log(expr.expressionValue(name, arg));
    }

    @Override
    public Expressions differentiation(Variable variable) {
        return mult(divide(new Num(1), expr), expr.differentiation(variable));
    }

    @Override
    public String toString() {
        return "ln("+expr+")";
    }

    @Override
    public Expressions clone() {
        return new Log(expr);
    }


    public static Expressions correctLog(Expressions expr){
        if (expr.getClass() == Num.class){
            return new Num(Math.log(((Num)expr).value));
        }
        if (expr.getClass() == Exponentiation.class){
            return mult(((Exponentiation)expr).deg, log(((Exponentiation)expr).base));
        }
        else return new Log(expr);
    }

//    @Override
//    public void checkNumberOfVariables() {
//        expr.checkNumberOfVariables();
//    }

}
