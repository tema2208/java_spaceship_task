package com.company;

import java.util.ArrayList;

public abstract class Expression {
    public abstract String toString();
    public abstract Expression derivative(char diff_var);
    public abstract ArrayList listarg(ArrayList<Character> args);
    public abstract Expression sub(char namevar, double var);
    public abstract double sub(double var);
    public abstract Expression clon();
    public abstract Expression create();
}
