package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import com.company.builder.*;
import com.company.factory.*;


public class Main {

    public static void main(String[] args) {

        //StandartEngine aue = new StandartEngine(50, 100, "abra");
        //System.out.println(aue.getSize());
        ModulsFactory factory = new ModulsFactory();

        HashMap<Object, Object> engine1_info = new HashMap<>();
        engine1_info.put("name", "engine1");
        engine1_info.put("speed", 1000.50);
        StandartEngine engine1 = (StandartEngine) factory.createModule(ModulsType.STANDART_ENGINE, 50, 100, engine1_info);

        HashMap<Object, Object> radar1_info = new HashMap<>();
        radar1_info.put("range", 100000.5);
        radar1_info.put("detection_force", 200.5);
        Radar radar1 = (Radar)factory.createModule(ModulsType.RADAR,10, 25, radar1_info);

        LivingModule living_module1 = (LivingModule) factory.createModule(ModulsType.LIVIND_MODULE, 13, 15);

        LandingModule landing_module1 = (LandingModule) factory.createModule(ModulsType.LANDING_MODULE, 10, 20);

        HashMap<Object, Object> weapon1_info = new HashMap<>();
        weapon1_info.put("range", 100.25);
        weapon1_info.put("recharge_time", 3.103);
        weapon1_info.put("damage", 50.0);
        weapon1_info.put("ArmamentType", ArmamentType.STRATEGIC);

        Armament weapon1 = (Armament) factory.createModule(ModulsType.ARMAMENT, 7, 1000, weapon1_info);


        System.out.println((double)1/12);

        // тестируем сборщика космических кораблей

        Frame frame = new Frame(80, 5, 100, 9);
        ResearhSpaceshipBuilder research_builder = new ResearhSpaceshipBuilder(frame);

        Spaceship spaceship = research_builder.addEngine(engine1).addRadar(radar1).addLivingModule(living_module1).addLangindModule(landing_module1).addArmament().build();
//        research_builder.addEngine(engine1, radar1, living_module1, landing_module1, weapon1);


    }



    //@SafeVarargs - ввел на случай, понадобится неограниченное количество аргументов
//    static Moduls createModul(ModulsType modul_type, int size, double cost, HashMap<Object, Object> modul_info) {
//        Moduls modul = null;
//
//        switch (modul_type) {
//            case STANDART_ENGINE:
//                Object name = modul_info.get("name");
//                return createStandartEngine(size, cost, name);
//        }
//        return modul;
//    }
}

//