package com.company;

public class LittleFactory extends AbstractFactory{
    public CommonMotor getCommonMotor(int n, Corpus hull){
        CommonMotor motor=new CommonMotor(n, 0.3);
        motor.getSpeed(hull);
        return motor;
    }
    public Radar getRadar(){
        return new Radar(5);
    }
    public Corpus getCorpus(double armor, int n){
        return new Corpus(armor, n);
    }
    public Hypermotor getHypermotor(int size, Corpus hull){
        Hypermotor motor=new Hypermotor(size, 0.3);
        motor.getSpeed(hull);
        return motor;
    }
    public LandingUnit getLandingUnit(){
        return new LandingUnit(30);
    }
    public LivingUnit getLivingUnit(){
        return new LivingUnit(40);
    }
    public CommonWeapon getCommonWeapon(){
        throw new IllegalArgumentException("Данная фабрика не умеет производить оружие");
    }
    public StrategicWeapon getStrategicWeapon(){
        throw new IllegalArgumentException("Данная фабрика не умеет производить оружие");
    }
    public DefensiveWeapon getDefensiveWeapon(){
        throw new IllegalArgumentException("Данная фабрика не умеет производить оружие");
    }
}
