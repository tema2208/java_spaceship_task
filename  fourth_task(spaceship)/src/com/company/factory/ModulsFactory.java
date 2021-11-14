package com.company.factory;

import java.util.HashMap;

public class ModulsFactory {
    @SafeVarargs
    public final Moduls createModule(ModulsType modul_type, int size, double cost, HashMap<Object, Object>... info){
        Moduls modul = null;
        HashMap<Object, Object> modul_info = null;
        if (info.length != 0) {
            modul_info = info[0];
        }
        try {
            switch (modul_type) {
                case STANDART_ENGINE:
                    Object name = modul_info.get("name");
                    Object speed = modul_info.get("speed");
                    return new StandartEngine(size, cost, (String) name, (double) speed);
                case RADAR:
                    Object radar_range = modul_info.get("range");
                    Object detection_force = modul_info.get("detection_force");
                    return new Radar(size, cost, (double) radar_range, (double) detection_force);
                case LIVIND_MODULE:
                    return new LivingModule(size, cost);
                case LANDING_MODULE:
                    return new LandingModule(size, cost);
                case ARMAMENT:
                    double weapon_range = (double) modul_info.get("range");
                    double recharge_time = (double) modul_info.get("recharge_time");
                    double damage = (double) modul_info.get("damage");
                    ArmamentType armament_type = (ArmamentType) modul_info.get("ArmamentType");
                    return new Armament(size, cost, weapon_range, recharge_time, damage, armament_type);
            }
        } catch(Exception e){
            System.out.println("было передано недостаточно данных для создания модуля");
        }
        return modul;
    }
}

