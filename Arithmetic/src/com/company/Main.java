package com.company;

import java.util.ArrayList;

public class Main {
    public static void sub(Expression ex, double var, ArrayList<Character> args){
        args= ex.listarg(args);
        if(args.size()==1){
            System.out.println(ex.sub(var));
        }
        else throw new IndexOutOfBoundsException();
    }

    public static void main(String[] args) {
        ArrayList<Character> arg= new ArrayList<Character>();
	    Expression ex1= new Sum(new Exponent(new Variable('x').create(),new Num(2)),new Num(1)).create();
        Expression ex2=ex1.clon();
        System.out.println(ex1.toString());
        System.out.println(ex2.toString());
        try {
            sub(ex1,2,arg);
        }
        catch (IndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
        }
        ex2=new Multi(new Sum(new Exponent(new Variable('x'),new Num(2)),new Num(1)), new Variable('y'));
        ex2.listarg(arg);
        System.out.println(arg.toString());
        ex2=ex2.sub('x',3);
        System.out.println(ex2.toString());
        System.out.println(ex1.derivative('x'));

        Num test1 = new Num(5);
        Num test2 = new Num(10);
        Variable t1 = new Variable('x');
        Variable t2 = new Variable('x');
        Sum t1_t2 = new Sum(test1, test2);
        Variable x = new Variable('x');
        System.out.println(t1_t2.derivative('x'));
        Sum aue = new Sum(t1, t2);
        System.out.println(aue.derivative('z'));

        Multi jopa1 = new Multi(x, test1);
        Multi jopa2 = new Multi(x, test2);
        Multi proizv = new Multi(jopa1,jopa2);
        System.out.println(proizv);
        System.out.println(proizv.derivative('x'));

    }
}
