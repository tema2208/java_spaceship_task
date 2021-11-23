package com.company.builder;

import com.company.factory.*;

import java.util.HashMap;

public class Spaceship {
    private Frame hull;
    private StandartEngine standart_engine;// решить вопрос с супердвигателем
    private SuperEngine super_engine;
    private Radar radar;
    private Integer radars_count;
    private LivingModule living_modul;
    private Integer living_moduls_count;
    private LandingModule landing_modul;
    private Integer landing_moduls_count;
    private HashMap<ArmamentType, Integer> weapons_info;
    private Armament weapon_strategic;
    private Armament weapon_defensive;
    private Armament weapon_standart;
    private SpaceshipType type;
    public Type appointment;
    private int cargo_hold;
    private final int total_cost = totalCost();
    private double final_speed;

    public void setHull(Frame hull) {
        this.hull = hull;
    }

    public void setStandartEngine(StandartEngine engine) {
        this.standart_engine = engine;
        standart_engine.setSpeed(hull);
    }

    public void setSuperEngine(SuperEngine engine) {
        this.super_engine = engine;
        super_engine.setSpeed(hull);
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


    public void setAppointment(Type appointment) {
        this.appointment = appointment;
    }

    public void setType(SpaceshipType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Космический корабль назначения:" + correcToString(type) + ". типа: "+ correcToString(appointment) + ". Общей стоимостью = " + totalCost() + "\n" +
                hull + "\n" +
                ", " + standart_engine + "\n" +
                ", " + correcToString(super_engine) + "\n" +
                ", " + correctToString2(correcToString(radar), radars_count) + "\n" +
//                ", " + correcToString(living_modul) + "\n" +
                ", " + correctToString2(correcToString(living_modul), living_moduls_count) + "\n" +
                ", " + correctToString2(correcToString(landing_modul), landing_moduls_count) + "\n" +
//                ", " + weapon_defensive + "количеством " + weapons_info.get(ArmamentType.DEFENSIVE) + "\n" +
//                ", " + weapon_standart + "количеством " + weapons_info.get(ArmamentType.STANDART) + "\n" +
//                ", " + weapon_strategic + "количеством " + weapons_info.get(ArmamentType.STRATEGIC) + "\n" +
                weaponToString() +
                ", Размер трюма = " + cargo_hold + "\n" +
                '}';
    }

    public void setWeapon_strategic(Armament weapon_strategic) {
        this.weapon_strategic = weapon_strategic;
    }

    public void setWeapon_defensive(Armament weapon_defensive) {
        this.weapon_defensive = weapon_defensive;
    }

    public void setWeapon_standart(Armament weapon_standart) {
        this.weapon_standart = weapon_standart;
    }

    public static enum Type {
        SYSTEM,
        INTERSTELLAR,
    }

    public void setCargo_hold(int cargo_hold) {
        this.cargo_hold = cargo_hold;
    }

    private int totalCost(){ // столько проверок потому что я уже не знаю где тут может быть NullPointerException
        int result = 0;
        if (hull != null) result += hull.getCost();
        if (standart_engine != null) result += standart_engine.getCost();
        if (super_engine != null) result += super_engine.getCost();
        if (radar != null && radars_count != null) result += radar.getCost() * radars_count;
        if (living_modul != null && living_moduls_count != null) result += living_modul.getCost() * living_moduls_count;
        if (landing_modul != null && landing_moduls_count != null) result += landing_modul.getCost() * landing_moduls_count;
        if (weapon_strategic != null && weapons_info.get(ArmamentType.STRATEGIC) != 0) result += weapon_strategic.getCost() * weapons_info.get(ArmamentType.STRATEGIC);
        if (weapon_defensive != null && weapons_info.get(ArmamentType.DEFENSIVE) !=0 ) result +=  weapon_defensive.getCost() * weapons_info.get(ArmamentType.DEFENSIVE);
        if (weapon_standart != null && weapons_info.get(ArmamentType.STANDART ) != 0) result += weapon_standart.getCost() * weapons_info.get(ArmamentType.STANDART);
        return result;
    }

    private String weaponToString(){
        String result = "";
        if (weapon_strategic != null && weapons_info.get(ArmamentType.STRATEGIC) != 0)result += ", " + weapon_strategic + "количеством " + weapons_info.get(ArmamentType.STRATEGIC) + "\n";
        if (weapon_defensive != null && weapons_info.get(ArmamentType.DEFENSIVE) != 0) result += ", " + weapon_defensive + "количеством " + weapons_info.get(ArmamentType.DEFENSIVE) + "\n";
        if (weapon_standart != null && weapons_info.get(ArmamentType.STANDART ) != 0) result += ", " + weapon_standart + "количеством " + weapons_info.get(ArmamentType.STANDART) + "\n";
        return result;
    }

    private String correctToString2(String str, Integer count){
        if (count == null) return "-------------данного модуля нет-------------";
        else return str + "количеством = " + count;
    }

    private String correcToString(Object object){
        if (object == null) return "-------------";
        else return object.toString();
    }

}
