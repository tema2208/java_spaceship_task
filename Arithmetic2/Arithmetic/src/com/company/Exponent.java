package com.company;


import java.util.HashSet;


public class Exponent extends Expression{
    Expression base;
    Expression power;
    private Exponent(Expression val1, Expression val2){
        this.base=val1;
        this.power=val2;
    }
    public String toString(){
        return base+"^"+power;
    }

    public Expression derivative(char diff_var) {
        if(power.getClass()==Num.class){
            return multiply(power,multiply(power(base,new Num(((Num)power).num-1)),base.derivative(diff_var)));
        }
        else return multiply(power(new Num(Math.E),multiply(log(base),power)),plus(divide(multiply(base.derivative(diff_var),power),base),multiply(log(base),power.derivative(diff_var))));
    }

    public HashSet<Character> listarg(HashSet<Character> args){
        base.listarg(args);
        return args;
    }

    public Expression substitute(char namevar, double var){
        return createPower(base.substitute(namevar,var), power.substitute(namevar,var));
    }

    public double substitute(double var) {
        if(this.argsExpression().size()==1) {
            return Math.pow(base.substitute(var), power.substitute(var));
        }
        else throw new ArithmeticException();
    }

    public Expression clon(){
        return createPower(base.clon(), power.clon());
    }

    public static Expression createPower(Expression var1, Expression var2){
        if((var1.getClass()==Num.class) && (var2.getClass()==Num.class)){
            return new Num(Math.pow(((Num)var1).num,((Num)var2).num));
        }
        if(var2.getClass()==Num.class && ((Num)var2).num==0){
            return new Num(1);
        }
        if(var2.getClass()==Num.class && ((Num)var2).num==1){
            return var1;
        }
        if(var1.getClass()==Num.class && ((Num)var1).num==1){
            return new Num(1);
        }
        else return new Exponent(var1,var2);
    }
}
