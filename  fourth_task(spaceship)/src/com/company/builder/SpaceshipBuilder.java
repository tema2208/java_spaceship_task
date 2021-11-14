package com.company.builder;

import com.company.factory.*;

public interface SpaceshipBuilder {

    public SpaceshipBuilder addEngine(Moduls engine); // тут можно уростить

    public SpaceshipBuilder addRadar(Radar...radar);

    public SpaceshipBuilder addLivingModule(LivingModule...living_module);

    public SpaceshipBuilder addLangindModule(LandingModule...landing_module);

    public SpaceshipBuilder addArmament(Armament...weapon);

    public Spaceship build();
}
