package com.company.builder;
import com.company.factory.*;

import java.util.HashMap;


public class SpaceshipBuilder implements Builder { //единственный может не иметь жилого модуля.
    // Приоритет – максимальное количество радаров. Без вооружения.
    private Factory factory;
    private int hull_size = 0;
    private int engine_size = 0;
    private int super_engine_size = 0;
    private EngineType engine_type;
    private int radar_count = 0;
    private int living_moduls_count = 0;
    private int landing_moduls_count = 0;
    private final HashMap<ArmamentType, Integer> weapons_info0 = createHs();
    private SpaceshipType spaceship_type;
    private Spaceship.Type appointment;

    @Override
    public Builder setAppointment(Spaceship.Type appointment) {
        this.appointment = appointment;
        return this;
    }

    @Override
    public Builder setFactory(Factory f) {
        this.factory = f;
        return this;
    }

    @Override
    public Builder setHullSize(int n) {
        this.hull_size = n;
        return this;
    }

    @Override
    public Builder setEngineSize(int n) {
        this.engine_size = n;
        return this;
    }

    @Override
    public Builder setRadarCount(int n) {
        this.radar_count = n;
        return this;
    }

    @Override
    public Builder setLivingModulsCount(int n) {
        this.living_moduls_count = n;
        return this;
    }

    @Override
    public Builder setLandingModulsCount(int n) {
        this.landing_moduls_count = n;
        return this;
    }

    @Override
    public Builder setWeaponsInfo(ArmamentType type, int count) {
        this.weapons_info0.put(type, count);
        return this;
    }

    @Override
    public Builder setSpaceshipType(SpaceshipType type) {
        this.spaceship_type = type;
        return this;
    }

    @Override
    public Builder setSuperEngineSize(int super_engine_size) {
        this.super_engine_size = super_engine_size;
        return this;
    }

    @Override
    public Spaceship build() {
        Spaceship spaceship = new Spaceship();
        if (factory == null) throw new ArithmeticException("Нельзя создать корабли, фабрика не передана");
        if (appointment == null || spaceship_type == null) throw new ArithmeticException("Нельзя создать корабль без соответствующего типа");
        if ((hull_size == 0) || (radar_count == 0) || (engine_size == 0)) {
            throw new ArithmeticException("Нельзя создать корабль без корпуса, двигателя или без радара");
        }

        Frame hull = factory.createHull(hull_size);
        StandartEngine standart_engine = factory.createStandartEngine(engine_size);
        SuperEngine super_engine = factory.createSuperEngine(super_engine_size);
        Radar radar = factory.createRadar();
        LivingModule living_module = factory.createLivingModule();
        LandingModule landing_module = factory.createLandingModule();
        Armament defensive_weapon = factory.createDefensiveWeapon();
        Armament standart_weapon = factory.createStandartWeapon();
        Armament strategic_weapon = factory.createStratagicWeapon();
        HashMap<ArmamentType, Integer> weapons_info = (HashMap<ArmamentType, Integer>) weapons_info0.clone();

        if (standart_engine.checkEngine(hull) == 0){
            throw new ArithmeticException("Двигатель и корпус не подходят друг другу");
        }
        int cargo_size = hull.getSize() - engine_size - radar.getSize() * radar_count;
        if (cargo_size > 0){
            spaceship.setHull(hull);
            spaceship.setStandartEngine(standart_engine);
            spaceship.setRadar(radar, radar_count);
        }
        else{ throw new ArithmeticException("В корпус не помещается необходимое для любого корабля оборудование");}

        if (appointment == Spaceship.Type.INTERSTELLAR){
            if (super_engine_size == 0) throw new ArithmeticException("Передана неверная информация для построения межзвездного корабля");
            if (super_engine.checkEngine(hull) == 0){
                throw new ArithmeticException("Двигатель и корпус не подходят друг другу");
            }
            if (cargo_size - super_engine_size < 0) throw new ArithmeticException("Не хватает места что бы поставить межзвездный двигатель");
            spaceship.setSuperEngine(super_engine);
            cargo_size -= super_engine_size;
        }
        spaceship.setAppointment(appointment);

        cargo_size = checkCargoSize(cargo_size, living_module, landing_module, defensive_weapon, standart_weapon, strategic_weapon);
        if (cargo_size < 0) throw new ArithmeticException("Корпус перегружен");
        spaceship.setCargoHold(cargo_size);

        switch(spaceship_type){
            case RESEARCH:
                if (!weaponInfoisEmpty()){
                    throw new ArithmeticException("Исследовательский корабль не содержит оружие");
                }
                if(living_moduls_count != 0){
                    spaceship.setLivingModul(living_module, living_moduls_count);
                }
                if(landing_moduls_count !=0){
                    spaceship.setLandingModul(landing_module, landing_moduls_count);
                }
                spaceship.setType(SpaceshipType.RESEARCH);
                return spaceship;

            case PASSENGER:
                if (!weaponInfoisEmpty()) {
                    throw new ArithmeticException("Пасажирский корабль не содержит оружие");
                }
                if(living_moduls_count == 0){
                    throw new ArithmeticException("Пасажирский корабль не может быть без жилых модулей");
                }
                else{
                    spaceship.setLivingModul(living_module, living_moduls_count);
                }
                if(landing_moduls_count != 0){
                    spaceship.setLandingModul(landing_module, landing_moduls_count);
                }
                spaceship.setType(SpaceshipType.PASSENGER);
                return spaceship;

            case CARGO:
                if (cargo_size == 0)  throw new ArithmeticException("В грузовом корабле должен быть ненулевой трюм");
                if (living_moduls_count == 0){
                    throw new ArithmeticException("Грузовой корабль не может быть без жилых модулей");
                }
                else{
                    spaceship.setLivingModul(living_module, living_moduls_count);
                }

                if (!weaponInfoisEmpty() && weapons_info.get(ArmamentType.DEFENSIVE) != 0 && weapons_info.get(ArmamentType.STANDART) == 0 && weapons_info.get(ArmamentType.STRATEGIC) == 0){
                    if ((cargo_size * (double) 1/10 - defensive_weapon.getSize() * weapons_info.get(ArmamentType.DEFENSIVE)) > 0){
                        spaceship.setWeaponsInfo(weapons_info);
                        spaceship.setWeaponDefensive(defensive_weapon);
                    }
                    else throw new ArithmeticException("Оружие не влезает в грузовой корабль");
                }
                else if (!weaponInfoisEmpty()) throw new ArithmeticException("Нельзя создать грузовой корабль с оружием не оборонительного типа ");

                if(landing_moduls_count != 0){
                    spaceship.setLandingModul(landing_module, landing_moduls_count);
                }
                spaceship.setType(SpaceshipType.CARGO);
                return spaceship;

            case COLONIZER:
                if(living_moduls_count == 0){
                    throw new ArithmeticException("Колонизаторский корабль не может быть без жилых модулей");
                }
                else{
                    spaceship.setLivingModul(living_module, living_moduls_count);
                }
                if (!weaponInfoisEmpty() && weapons_info.get(ArmamentType.STRATEGIC) == 0 && weapons_info.get(ArmamentType.STANDART) == 0 && weapons_info.get(ArmamentType.DEFENSIVE) != 0){
                    int living_module_size = living_moduls_count * living_module.getSize();
                    if ((living_module_size * (double) 1/10 - defensive_weapon.getSize() * weapons_info.get(ArmamentType.DEFENSIVE)) > 0){
                        spaceship.setWeaponsInfo(weapons_info);
                        spaceship.setWeaponDefensive(defensive_weapon);
                    }
                    else throw new ArithmeticException("Не хватает места поставить оборонительное вооружение на колонизаторский корабль");
                }
                else if(!weaponInfoisEmpty()) throw new ArithmeticException("Нельзя создать колонизаторский корабль с оружием не оборонительного типа ");

                if(landing_moduls_count != 0){
                    spaceship.setLandingModul(landing_module, landing_moduls_count);
                }
                else throw new ArithmeticException("Нельзя создать военный транспортный корабль без посадочного модуля");
                spaceship.setType(SpaceshipType.COLONIZER);
                return spaceship;

            case MILITARY_TRANSPORT:
                if (!weaponInfoisEmpty() || hull.getArmor() == 0) throw new ArithmeticException("Нельзя создать военный транспортный корабль без оружия или брони");
                if(living_moduls_count == 0){
                    throw new ArithmeticException("военный транспортный корабльь не может быть без жилых модулей");
                }
                else{
                    spaceship.setLivingModul(living_module, living_moduls_count);
                }
                if (weapons_info.get(ArmamentType.STRATEGIC) == 0 && weapons_info.get(ArmamentType.STANDART) == 0 && weapons_info.get(ArmamentType.DEFENSIVE) != 0){
                    int living_module_size = living_moduls_count * living_module.getSize();
                    if ((living_module_size * (double) 2/10 - defensive_weapon.getSize() * weapons_info.get(ArmamentType.DEFENSIVE)) > 0){
                        spaceship.setWeaponsInfo(weapons_info);
                        spaceship.setWeaponDefensive(defensive_weapon);
                    }
                    else{
                        weapons_info.put(ArmamentType.DEFENSIVE, 1);
                        spaceship.setWeaponsInfo(weapons_info);
                        spaceship.setWeaponDefensive(defensive_weapon);
                    }
                }
                else throw new ArithmeticException("Нельзя создать военный транспортный корабль без оборонительного вооружения или с другим видом");
                if(landing_moduls_count != 0){
                    spaceship.setLandingModul(landing_module, landing_moduls_count);
                }
                else throw new ArithmeticException("Нельзя создать военный транспортный корабль без посадочного модуля");
                spaceship.setType(SpaceshipType.COLONIZER);
                return spaceship;

            case MILITARY_GENERAL:
                if (hull.getArmor() == 0) throw new ArithmeticException("Военный корабль общего назначения не может быть с корпусом нулевого бронирования");
                if (living_moduls_count == 0){
                    throw new ArithmeticException("Военный корабль общего назначения не может быть без жилых модулей");
                }
                else{
                    spaceship.setLivingModul(living_module, living_moduls_count);
                }
                if (!weaponInfoisEmpty() && weapons_info.get(ArmamentType.STRATEGIC) == 0 && weapons_info.get(ArmamentType.STANDART) != 0 && weapons_info.get(ArmamentType.DEFENSIVE) != 0){
                    spaceship.setWeaponsInfo(weapons_info);
                    spaceship.setWeaponStandart(standart_weapon);
                    spaceship.setWeaponDefensive(defensive_weapon);
                }
                else throw new ArithmeticException("Нельзя создать военный корабль общего назначения без соответствующего вооружения");
                if(landing_moduls_count != 0){
                    spaceship.setLandingModul(landing_module, landing_moduls_count);
                }
                spaceship.setType(SpaceshipType.MILITARY_GENERAL);
                return spaceship;

            case MILITARY_STRATEGIC:
                if (weaponInfoisEmpty()) throw new ArithmeticException("Военный корабль стратегического назначения не может быть без оружия");
                if (living_moduls_count == 0){
                    throw new ArithmeticException(" Военный корабль стратегического назначения не может быть без жилых модулей");
                }
                else{
                    spaceship.setLivingModul(living_module, living_moduls_count);
                }
                switch (CheckArmament()){
                    case 0: throw new ArithmeticException("Военный корабль стратегического назначения не может быть без 3 типов оружия");
                    case 1: throw new ArithmeticException("Военный корабль стратегического назначения не может быть без 2 типов оружия");
                    case 2:
                        if (weapons_info.get(ArmamentType.STANDART) == 0){
                            spaceship.setWeaponsInfo(weapons_info);
                            spaceship.setWeaponStrategic(strategic_weapon);
                            spaceship.setWeaponDefensive(defensive_weapon);
                        }
                        else throw new ArithmeticException("Корабль стратегического назначения не может быть без оборонительного и стратегического вооружения");
                    case 3:
                            spaceship.setWeaponsInfo(weapons_info);
                            spaceship.setWeaponStrategic(strategic_weapon);
                            spaceship.setWeaponDefensive(defensive_weapon);
                            spaceship.setWeaponStandart(standart_weapon);
                }
                if(landing_moduls_count != 0){
                    spaceship.setLandingModul(landing_module, landing_moduls_count);
                }
                spaceship.setType(SpaceshipType.MILITARY_STRATEGIC);
                return spaceship;
        }
        return null;
    }

    private int checkCargoSize(int cargo_size, LivingModule living_modul, LandingModule landing_modul, Armament defensive_weapon, Armament standart_weaopon, Armament strategic_weapon){
        cargo_size = cargo_size - (living_modul.getSize() * living_moduls_count + landing_moduls_count * landing_modul.getSize());
        cargo_size = cargo_size - (defensive_weapon.getSize() * weapons_info0.get(ArmamentType.DEFENSIVE) + standart_weaopon.getSize() * weapons_info0.get(ArmamentType.STANDART) + strategic_weapon.getSize() * weapons_info0.get(ArmamentType.STRATEGIC));
        return cargo_size;
    }
    private int CheckArmament(){
        int result = 0;
        for (ArmamentType key: weapons_info0.keySet()){
            if (weapons_info0.get(key) !=0) result += 1;
        }
        return result;
    }

    private HashMap<ArmamentType, Integer> createHs(){
        HashMap<ArmamentType, Integer> hs = new HashMap<>();
        hs.put(ArmamentType.STRATEGIC, 0);
        hs.put(ArmamentType.STANDART, 0);
        hs.put(ArmamentType.DEFENSIVE, 0);
        return hs;
    }

    private boolean weaponInfoisEmpty(){
        if (weapons_info0.get(ArmamentType.STRATEGIC) == 0 && weapons_info0.get(ArmamentType.STANDART) == 0 && weapons_info0.get(ArmamentType.DEFENSIVE) == 0){
            return true;
        }
        return false;
    }

    public String toString() {
        return " Будущий космический корабль будет иметь: цена="+ getCost() +"\n"+
                ", " + factory.createHull(hull_size) +"\n" +
                ", " + factory.createStandartEngine(engine_size) +"\n" +
                ", " + factory.createSuperEngine(super_engine_size) +"\n" +
                ", " +  factory.createRadar() + "; количество =" + radar_count +"\n" +
                ", " +factory.createLivingModule()+ "; количество =" + living_moduls_count +"\n" +
                ", " + factory.createLandingModule() + "; количество д=" + landing_moduls_count +"\n" +
                ", " + factory.createStandartWeapon()+ "; количество о=" + weapons_info0.get(ArmamentType.STANDART) +"\n" +
                ", " + factory.createStratagicWeapon()+ "; количество =" + weapons_info0.get(ArmamentType.STRATEGIC)+"\n" +
                ", " + factory.createDefensiveWeapon() + "; количество =" + weapons_info0.get(ArmamentType.DEFENSIVE)+"\n" +
                ", " + getCargoHold() +"\n" +
                ", " + appointment;
    }

    private int getCargoHold(){
        int cargo_size = factory.createHull(hull_size).getSize();
        cargo_size = cargo_size - (engine_size + super_engine_size);
        cargo_size -= factory.createRadar().getSize() * radar_count;
        cargo_size -= factory.createLivingModule().getSize() * living_moduls_count;
        cargo_size -= factory.createLandingModule().getSize() * landing_moduls_count;
        cargo_size -= factory.createStandartWeapon().getSize() * weapons_info0.get(ArmamentType.STANDART);
        cargo_size -= factory.createStratagicWeapon().getSize() * weapons_info0.get(ArmamentType.STRATEGIC);
        cargo_size -= factory.createDefensiveWeapon().getSize() * weapons_info0.get(ArmamentType.DEFENSIVE);
        return cargo_size;
    }

    private int getCost(){
        int cost = 0;
        cost += factory.createHull(hull_size).getCost();
        cost += factory.createStandartEngine(engine_size).getCost();
        cost += factory.createSuperEngine(super_engine_size).getCost();
        cost += factory.createRadar().getCost() * radar_count;
        cost += factory.createLivingModule().getCost() * living_moduls_count;
        cost += factory.createLandingModule().getCost() * landing_moduls_count;
        cost += factory.createDefensiveWeapon().getCost() * weapons_info0.get(ArmamentType.DEFENSIVE);
        cost += factory.createStandartWeapon().getCost() * weapons_info0.get(ArmamentType.STANDART);
        cost += factory.createStratagicWeapon().getCost() * weapons_info0.get(ArmamentType.STRATEGIC);
        return cost;
    }
}
