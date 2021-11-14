package com.company;

import java.util.HashSet;


public class Log extends Expression{
    private Expression logexpression;
    public String toString(){
        return "LN"+"("+logexpression+")";
    }
    private Log(Expression var){
        this.logexpression=var;
    }
    public Expression derivative(char diff_var){
        return divide(logexpression.derivative(diff_var),logexpression);
    }
    public HashSet listarg(HashSet<Character> args){
        logexpression.listarg(args);
        return args;
    }
    public Expression substitute(char namevar, double var){
        return log(logexpression.substitute(namevar, var));
    }
    public double substitute(double var){
        if(this.argsExpression().size()==1) {
            return Math.log(logexpression.substitute(var));
        }
        else throw new ArithmeticException();
    }
    public Expression clon(){
        return log(logexpression.clon());
    }
    public static Expression createLog(Expression var){
        if(var.getClass()==Num.class) return new Num(Math.log(((Num)var).num));
        if(var.getClass()==Exponent.class)return multiply(((Exponent)var).power,new Log(((Exponent)var).base));
        return new Log(var);
    }
}
