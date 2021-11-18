package com.company.builder;

import com.company.factory.*;

import java.util.HashMap;

public class Spaceship {
    private Frame hull;
    private StandartEngine standart_engine = null;// решить вопрос с супердвигателем
    private SuperEngine super_engine = null;
    private Radar radar;
    private int radars_count;
    private LivingModule living_modul;
    private int living_moduls_count;
    private LandingModule landing_modul;
    private int landing_moduls_count;
    private HashMap<ArmamentType, Integer> weapons_info = null;
    private int cargo_hold;
    private SpaceshipType type;
    private Type appointment;


    public void setHull(Frame hull) {
        this.hull = hull;
    }

    public void setStandartEngine(StandartEngine engine) {
        this.standart_engine = engine;
        setSpaceshipAppointment();
    }

    public void setSuperEngine(StandartEngine engine) {
        this.standart_engine = engine;
        setSpaceshipAppointment();
    }

    public void setRadar(Radar radar, int radars_count) {
        this.radar = radar;
        this.radars_count = radars_count;
    }


    public void setLiving_modul(LivingModule living_modul, int living_moduls_count) {
        this.living_modul = living_modul;
        this.living_moduls_count = living_moduls_count;
    }


    public void setLanding_modul(LandingModule landing_modul, int landing_moduls_count) {
        this.landing_modul = landing_modul;
        this.landing_moduls_count = landing_moduls_count;
    }


    public void setWeapons_info(HashMap<ArmamentType, Integer> weapons_info) {
        this.weapons_info = weapons_info;
    }

    private void setSpaceshipAppointment(){
        if (super_engine == null) this.appointment = Type.SYSTEM;
        else this.appointment = Type.INTERSTELLAR;
    }

    public void setType(SpaceshipType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "hull=" + hull +
                ", standart_engine=" + standart_engine +
                ", super_engine=" + super_engine +
                ", radar=" + radar +
                ", radars_count=" + radars_count +
                ", living_modul=" + living_modul +
                ", living_moduls_count=" + living_moduls_count +
                ", landing_modul=" + landing_modul +
                ", landing_moduls_count=" + landing_moduls_count +
                ", weapons_info=" + weapons_info +
                ", cargo_hold=" + cargo_hold +
                ", type=" + type +
                ", appointment=" + appointment +
                '}';
    }

    protected static enum Type {
        SYSTEM,
        INTERSTELLAR,
    }
//    public void setCargo_hold(int cargo_hold) {
//        this.cargo_hold = cargo_hold;
//    }
}
