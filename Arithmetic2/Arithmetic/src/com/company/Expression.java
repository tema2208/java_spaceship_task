package com.company;

import java.util.HashSet;

import static com.company.Exponent.createPower;
import static com.company.Log.createLog;
import static com.company.Multi.createMultiply;
import static com.company.Quotient.createDivide;
import static com.company.Sum.createSum;

public abstract class Expression {
    public abstract String toString();
    public abstract Expression derivative(char diff_var);
    abstract HashSet listarg(HashSet<Character> args);
    public HashSet argsExpression(){
        HashSet<Character> arg=new HashSet<>();
        return listarg(arg);
    }
    public abstract Expression substitute(char namevar, double var);
    public abstract double substitute(double var);
    public abstract Expression clon();
    public static Expression plus(Expression var1, Expression var2){
        return createSum(var1, var2);
    }
    public static Expression multiply(Expression var1, Expression var2){
        return createMultiply(var1, var2);
    }
    public static Expression divide(Expression var1, Expression var2){
        return createDivide(var1, var2);
    }
    public static Expression power(Expression var1, Expression var2){
        return createPower(var1, var2);
    }
    public static Expression log(Expression var){
        return createLog(var);
    }
}
