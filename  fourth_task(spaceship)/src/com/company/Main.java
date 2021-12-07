package com.company;

import com.company.builder.*;
import com.company.factory.*;


public class Main {

    public static void main(String[] args) {
        ModulsFactory factory = new ModulsFactory();
        SpaceshipBuilder builder = new SpaceshipBuilder();
        Spaceship spaceship_research = builder.setFactory(factory).setHullSize(3).setEngineSize(15).setSpaceshipType(SpaceshipType.RESEARCH).setRadarCount(3).setAppointment(Spaceship.Type.SYSTEM).build();
        System.out.println(spaceship_research);

        SpaceshipBuilder builder1 = new SpaceshipBuilder();
        Spaceship spaceship_military_strategic = builder1.setFactory(factory).setSpaceshipType(SpaceshipType.MILITARY_STRATEGIC).setHullSize(5).setAppointment(Spaceship.Type.SYSTEM).setEngineSize(50).setRadarCount(1).setLivingModulsCount(2).setWeaponsInfo(ArmamentType.DEFENSIVE, 2).setWeaponsInfo(ArmamentType.STRATEGIC, 1).build();

        System.out.println(spaceship_military_strategic);

        SpaceshipBuilder builder3 = new SpaceshipBuilder();
        Spaceship spaceship_colonizer = builder3.setFactory(factory).setSpaceshipType(SpaceshipType.COLONIZER).setHullSize(6).setEngineSize(50).setRadarCount(1).setLivingModulsCount(5).setLandingModulsCount(1).setSuperEngineSize(50).setWeaponsInfo(ArmamentType.DEFENSIVE, 1).setAppointment(Spaceship.Type.INTERSTELLAR).build();
        System.out.println(spaceship_colonizer);
        System.out.println(builder3.setFactory(factory).setSpaceshipType(SpaceshipType.COLONIZER).setHullSize(6).setEngineSize(50).setRadarCount(1).setLivingModulsCount(5).setLandingModulsCount(1).setSuperEngineSize(50).setAppointment(Spaceship.Type.INTERSTELLAR));

        ModulsFactory2 factory2 = new ModulsFactory2();
        SpaceshipBuilder builder4 = new SpaceshipBuilder();
        try {
            builder4.setFactory(factory2).setSpaceshipType(SpaceshipType.COLONIZER).setHullSize(6).setEngineSize(50).setRadarCount(1).setLivingModulsCount(5).setLandingModulsCount(1).setSuperEngineSize(50).setWeaponsInfo(ArmamentType.DEFENSIVE, 1).setAppointment(Spaceship.Type.INTERSTELLAR).build();
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
    }
}