package com.company;

import static com.company.Expression.*;

public class Main {


    public static void main(String[] args) {
	    Expression ex1= plus(power(new Variable('x'),new Num(2)),new Num(1));
        Expression ex2=ex1.clon();
        System.out.println(ex1.toString());
        System.out.println(ex2.toString());
        ex2=multiply(plus(power(new Variable('x'),new Num(2)),new Num(1)), new Variable('y'));
        try {
            System.out.println(ex2.substitute(2));
        }
        catch (ArithmeticException ex){
            System.out.println("Больше чем одна переменная");
        }
        System.out.println(ex2.argsExpression().toString());
        ex2=ex2.substitute('x',3);
        System.out.println(ex2.toString());
        System.out.println(ex1.derivative('x'));
        Expression ex3=power(new Variable('x'),new Variable('x'));
        System.out.println(ex3.derivative('x'));

        Variable x = new Variable('x');
        Variable y = new Variable('y');
        Num value1 = new Num(5);
        Num value2 = new Num(10);


        Multi x_val1 = new Multi(x, value1);
        Multi x_val2 = new Multi(x, value2);
        Multi all = new Multi(x_val1,x_val2);
        Multi all1 = new Multi(all,y);
        System.out.println(all1.substitute('y', 1));


    }
}
