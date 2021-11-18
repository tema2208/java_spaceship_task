package com.company.builder;
import com.company.factory.*;

import com.company.factory.*;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

import java.util.HashMap;

public interface SpaceshipBuilder {
    public SpaceshipBuilder setFactory(ModulsFactory f);

    public SpaceshipBuilder setHullSize(int n);

    public SpaceshipBuilder setEngineSize(int n);

    public SpaceshipBuilder setRadarCount(int n);

    public SpaceshipBuilder setLivingModulsCount(int n);

    public SpaceshipBuilder setLandingModulsCount(int n);

    public SpaceshipBuilder setWeaponsInfo(ArmamentType type, int count);

    public SpaceshipBuilder setSpaceshipType(SpaceshipType type);

    public SpaceshipBuilder setAppointment(Spaceship.Type appointment);

    public SpaceshipBuilder setEngineType(EngineType type);

    public Spaceship build();



//    public SpaceshipBuilder addEngine(Moduls engine); // тут можно уростить
//
//    public SpaceshipBuilder addRadar(Radar...radar);
//
//    public SpaceshipBuilder addLivingModule(LivingModule...living_module);
//
//    public SpaceshipBuilder addLangindModule(LandingModule...landing_module);
//
//    public SpaceshipBuilder addArmament(Armament...weapon);
//
//    public Spaceship build();
}
