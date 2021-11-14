package com.p;

import com.sun.istack.internal.NotNull;

import java.util.HashSet;
import java.util.Objects;

public class Variable extends Expressions{ // класс переменной наследуемый от абстрактного класса
    protected String name;//$%#$#@

    public Variable(String n){
        if (n != null){
            this.name = n;
        }
        else throw new NullPointerException();
    }


    @Override
    public Expressions differentiation(Variable variable) {
        //System.out.println("шо ? "+variable.name+" : " + name);
        //System.out.println();
        if (Objects.equals(name, variable.name)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Expressions clone() {
        return new Variable(name);
    }

    @Override
    public double expressionValue(double arg) {
        return arg;
    }

    @Override
    public Expressions expressionValue(String n, double arg) {
        if (name.equals(n)){
            return new Num(arg);
        }
        else{
            return new Variable(name);
        }
    }

    @Override
    public HashSet<String> hsArgs(HashSet<String> hs) {
        hs.add(name);
        return hs;
    }

//    @Override
//    public void checkNumberOfVariables() {
//    }

}
