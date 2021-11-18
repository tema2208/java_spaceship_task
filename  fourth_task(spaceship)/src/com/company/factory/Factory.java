package com.company.factory;

public abstract class Factory {

    public abstract Frame createHull(int n);

    public abstract StandartEngine createStandartEngine(int size);

    public abstract SuperEngine createSuperEngine(int size);

    public abstract Radar createRadar();

    public abstract LivingModule createLivingModule();

    public abstract LandingModule createLandingModule();

    public abstract Armament createStratagicWeapon();

    public abstract Armament createStandartWeapon();

    public abstract Armament createDefensiveWeapon();
}
