package com.company.builder;
import com.company.factory.*;

public interface Builder {
    public Builder setFactory(Factory f);

    public Builder setHullSize(int n);

    public Builder setEngineSize(int n);

    public Builder setSuperEngineSize(int super_engine_size);

    public Builder setRadarCount(int n);

    public Builder setLivingModulsCount(int n);

    public Builder setLandingModulsCount(int n);

    public Builder setWeaponsInfo(ArmamentType type, int count);

    public Builder setSpaceshipType(SpaceshipType type);

    public Builder setAppointment(Spaceship.Type appointment);

    public Builder setEngineType(EngineType type);

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
