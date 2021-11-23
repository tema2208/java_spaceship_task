package com.company.factory;

public class ModulsFactory2 extends Factory{
    @Override
    public Frame createHull(int n) {
        return new Frame(n, 150, 15);
    }

    @Override
    public StandartEngine createStandartEngine(int size) {
        return new StandartEngine(size, 400);
    }

    @Override
    public SuperEngine createSuperEngine(int size) {
        throw new ArithmeticException("Эта фабрика не может создать межгалактические двигатели");
    }

    @Override
    public Radar createRadar() {
        return new Radar(12, 3, 200, 400);
    }

    @Override
    public LivingModule createLivingModule() {
        return new LivingModule(45, 25);
    }

    @Override
    public LandingModule createLandingModule() {
        return new LandingModule(28, 13);
    }

    @Override
    public Armament createStratagicWeapon() {
        return  new Armament(3, 20, 1200, 17, 120, ArmamentType.STRATEGIC);
    }

    @Override
    public Armament createStandartWeapon() {
        return new Armament(5, 19, 700, 12, 120, ArmamentType.STANDART);
    }

    @Override
    public Armament createDefensiveWeapon() {
        return new Armament(15, 21, 400, 10, 115, ArmamentType.DEFENSIVE);
    }
}
