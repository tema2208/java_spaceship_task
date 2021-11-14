package com.company.builder;

import com.company.factory.*;

import java.util.ArrayList;

public class Spaceship {
    private Frame frame;
    private int frame_size;
    private Moduls engine;
    private ArrayList<Radar> radar_array;
    private ArrayList<LivingModule> living_module_array;
    private ArrayList<LandingModule> landing_module_array;
    private ArrayList<Armament> armament_array;





    public String name;
    public SpaceshipType type;

    public Spaceship(Frame frame, int frame_size, Moduls engine, ArrayList<Radar> radar_array, ArrayList<LivingModule> living_module_array, ArrayList<LandingModule> landing_module_array, ArrayList<Armament> armament_array) {
        this.frame = frame;
        this.frame_size = frame_size;
        this.engine = engine;
        this.radar_array = radar_array;
        this.living_module_array = living_module_array;
        this.landing_module_array = landing_module_array;
        this.armament_array = armament_array;
    }
}
