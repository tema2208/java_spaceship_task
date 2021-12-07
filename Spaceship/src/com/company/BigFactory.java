package com.company;

public class BigFactory extends AbstractFactory{
    public CommonMotor getCommonMotor(int n, Corpus hull){
        CommonMotor motor=new CommonMotor(n, 0.5);
        motor.getSpeed(hull);
        return motor;
    }
    public Radar getRadar(){
        return new Radar(7);
    }
    public Corpus getCorpus(double armor, int n){
        return new Corpus(armor, n);
    }
    public Hypermotor getHypermotor(int size, Corpus hull){
        Hypermotor motor=new Hypermotor(size, 0.5);
        motor.getSpeed(hull);
        return motor;
    }
    public LandingUnit getLandingUnit(){
        return new LandingUnit(40);
    }
    public LivingUnit getLivingUnit(){
        return new LivingUnit(50);
    }
    public CommonWeapon getCommonWeapon(){
        return new CommonWeapon(13);
    }
    public StrategicWeapon getStrategicWeapon(){
        return new StrategicWeapon(15);
    }
    public DefensiveWeapon getDefensiveWeapon(){
        return new DefensiveWeapon(10);
    }
}
