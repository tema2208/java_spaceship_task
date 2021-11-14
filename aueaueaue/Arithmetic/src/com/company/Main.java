package com.company;

public class Main {

    public static void main(String[] args) {

        Expression a = new Variable('a');
        Expression b = new Variable('b');
        Expression c = new Variable('c');
        Expression d = new Variable('d');

        Expression e1 = a.mult(new Num(3).sum(b)).pow(c.mult(b));
        Expression e2 = a.pow(new Num(3)).sum(a.mult(new Num(3)).sum(new Num(8).quot(a)));

        System.out.println(e1); //вывод выражения
        System.out.println(e1.derivate('a')); //дифференцирование по переменной a
        System.out.println(e1.derivate('d')); //дифференцирование по несуществующей переменной
        System.out.println(e1.variables()); //вывод переменных
        System.out.println(e1.substitution('a', 2)); //подстановка a = 2
        System.out.println(e2);
        System.out.println(e2.substitution(2));
        //System.out.println(e1.substitution1(2)); //выброс исключения

    }
}
