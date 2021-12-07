package com.company;

public abstract class AbstractFactory {
    public abstract CommonMotor getCommonMotor(int n, Corpus hull);
    public abstract Radar getRadar();
    public abstract Corpus getCorpus(double armor, int n);
    public abstract Hypermotor getHypermotor(int size, Corpus hull);
    public abstract LandingUnit getLandingUnit();
    public abstract LivingUnit getLivingUnit();
    public abstract CommonWeapon getCommonWeapon();
    public abstract StrategicWeapon getStrategicWeapon();
    public abstract DefensiveWeapon getDefensiveWeapon();
}
