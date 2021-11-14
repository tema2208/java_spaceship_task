package com.company;

import java.util.HashSet;


public class Multi extends Expression{
    private Expression e1;
    private Expression e2;
    public Multi(Expression val1, Expression val2){
        this.e1=val1;
        this.e2=val2;
    }
    public String toString(){
        return "("+e1+"*"+e2+")";
    }
    public Expression derivative(char diff_var){
        return plus(multiply(e1.derivative(diff_var),e2),multiply(e1,e2.derivative(diff_var)));
    }
    public HashSet<Character> listarg(HashSet<Character> args){
        e1.listarg(args);
        e2.listarg(args);
        return args;
    }
    public Expression substitute(char namevar, double var){
        return multiply(e1.substitute(namevar,var),e2.substitute(namevar,var));
    }
    public double substitute(double var){
        if(this.argsExpression().size()==1) {
            return e1.substitute(var) * e2.substitute(var);
        }
        else throw new ArithmeticException();
    }
    public Expression clon(){
        return multiply(e1.clon(),e2.clon());
    }
    public static Expression createMultiply(Expression var1, Expression var2){
        if(var1.getClass()==Num.class && var2.getClass()==Num.class){
            return new Num(((Num)var1).num*((Num)var2).num);
        }
        if ((var1.getClass()==Num.class && ((Num)var1).num==0)||(var2.getClass()==Num.class && ((Num)var2).num==0)){
            return new Num(0);
        }
        if(var2.getClass()==Num.class && ((Num)var2).num==1){
            return var1;
        }
        if(var1.getClass()==Num.class && ((Num)var1).num==1){
            return var2;
        }
        else return new Multi(var1, var2);
    }
}
