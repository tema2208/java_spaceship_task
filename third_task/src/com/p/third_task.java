package com.p;

import java.util.HashSet;

import static com.p.Expressions.add;
import static com.p.Expressions.mult;
import static com.p.Expressions.expon;
import static com.p.Expressions.divide;
import static com.p.Expressions.log;

public class third_task {
    public static void main(String[] args) {
        // тестируем клона
        Num test = new Num(5001);
        Num clone = (Num) test.clone();
        System.out.println(clone.value);

        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Variable x1 = new Variable("x1");
        Variable y1 = new Variable("y1");

        Num value1 = new Num(5);
        Num value2 = new Num(10);


        Expressions ex1 = add(x,x);
        Expressions ex2 = expon(ex1, x);
        Expressions ex3 = mult(ex2, x);
        Expressions e4 = log(ex3);
        Expressions e5 = divide(e4, x);


        System.out.println(e5.expressionValue(2));

        HashSet<String> hs = new HashSet();
        System.out.println(e5.hsArgs(hs));
        System.out.println(hs);
    }
}
