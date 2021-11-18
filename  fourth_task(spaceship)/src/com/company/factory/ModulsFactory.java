package com.company.factory;

public class ModulsFactory extends Factory {

//    public final Moduls createModule(ModulsType modul_type, int size, double cost, HashMap<Object, Object>... info){
//    }
//        Moduls modul = null;
//        HashMap<Object, Object> modul_info = null;
//        if (info.length != 0) {
//            modul_info = info[0];
//        }
//        try {
//            switch (modul_type) {
//                case STANDART_ENGINE:
//                    Object name = modul_info.get("name");
//                    Object speed = modul_info.get("speed");
//                    return new StandartEngine(size, cost, (String) name, (double) speed);
//                case RADAR:
//                    Object radar_range = modul_info.get("range");
//                    Object detection_force = modul_info.get("detection_force");
//                    return new Radar(size, cost, (double) radar_range, (double) detection_force);
//                case LIVIND_MODULE:
//                    return new LivingModule(size, cost);
//                case LANDING_MODULE:
//                    return new LandingModule(size, cost);
//                case ARMAMENT:
//                    double weapon_range = (double) modul_info.get("range");
//                    double recharge_time = (double) modul_info.get("recharge_time");
//                    double damage = (double) modul_info.get("damage");
//                    ArmamentType armament_type = (ArmamentType) modul_info.get("ArmamentType");
//                    return new Armament(size, cost, weapon_range, recharge_time, damage, armament_type);
//            }
//        } catch(Exception e){
//            System.out.println("было передано недостаточно данных для создания модуля");
//        }
//        return modul;
//    }
    @Override
    public Frame createHull(int n){
        return new Frame(n, 100, 10);
    }

    @Override
    public StandartEngine createStandartEngine(int size){
        return new StandartEngine(size, 30);
    }

    @Override
    public SuperEngine createSuperEngine(int size){
        return new SuperEngine(size, 200);
    }

    @Override
    public Radar createRadar(){
        return new Radar(10, 10, 100, 150);
    }

    @Override
    public LivingModule createLivingModule(){
        return new LivingModule(40, 18);
    }

    @Override
    public LandingModule createLandingModule(){
        return new LandingModule(30, 15);
    }

    @Override
    public Armament createStratagicWeapon(){
        return new Armament(15, 15, 1000, 15, 100, ArmamentType.STRATEGIC);
    }

    @Override
    public Armament createStandartWeapon(){
        return new Armament(20, 19, 700, 12, 120, ArmamentType.STANDART);
    }

    @Override
    public Armament createDefensiveWeapon(){
        return new Armament(25, 21, 400, 10, 115, ArmamentType.DEFENSIVE);
    }
}

