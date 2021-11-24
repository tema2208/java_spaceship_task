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
    private final HashMap<ArmamentType, Integer> weapons_info = createHs();
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
    public Builder setEngineType(EngineType type) {
        this.engine_type = type;
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
        this.weapons_info.put(type, count);
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
    public Spaceship build() { // Нужно решить вопрос с двигателями,
        Spaceship spaceship = new Spaceship();
        if (factory == null) throw new ArithmeticException("Нельзя создать корабли, фабрика не передана");
        if (appointment == null || spaceship_type == null) throw new ArithmeticException("Нельзя создать корабль без соответствующего типа");
        if ((hull_size == 0) || (radar_count == 0) || (engine_size == 0)) {
            throw new ArithmeticException("Нельзя создать корабль без корпуса, двигателя или без радара");
        }
        // нужно написать try - catch, на случай если фабрика не может производить нужные элементы
        Frame hull = factory.createHull(hull_size);
        StandartEngine standart_engine = factory.createStandartEngine(engine_size);
        SuperEngine super_engine = factory.createSuperEngine(super_engine_size);
        Radar radar = factory.createRadar();
        LivingModule living_module = factory.createLivingModule();
        LandingModule landing_module = factory.createLandingModule();

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

        switch(spaceship_type){
            case RESEARCH:
                if (!weaponInfoisEmpty()){
                    throw new ArithmeticException("Исследовательский корабль не содержит оружие");
                }
                if(living_moduls_count != 0 && cargo_size - living_moduls_count * living_module.getSize() > 0){
                    spaceship.setLiving_modul(living_module, living_moduls_count);
                    cargo_size -= living_moduls_count * living_module.getSize();
                }
                if(landing_moduls_count !=0 && cargo_size - landing_moduls_count * landing_module.getSize() > 0){
                    spaceship.setLanding_modul(landing_module, landing_moduls_count);
                    cargo_size -= landing_moduls_count * landing_module.getSize();
                }
                spaceship.setType(SpaceshipType.RESEARCH);
                spaceship.setCargo_hold(cargo_size);
                return spaceship;

            case PASSENGER:
                if (!weaponInfoisEmpty()) {
                    throw new ArithmeticException("Пасажирский корабль не содержит оружие");
                }
                if(cargo_size - living_moduls_count * living_module.getSize() < 0 || living_moduls_count == 0){
                    throw new ArithmeticException("Пасажирский корабль не может быть без жилых модулей");
                }
                else{
                    spaceship.setLiving_modul(living_module, living_moduls_count);
                    cargo_size -= living_moduls_count * living_module.getSize();
                }
                if(landing_moduls_count != 0 && cargo_size - landing_moduls_count * landing_module.getSize() > 0){
                    spaceship.setLanding_modul(landing_module, landing_moduls_count);
                    cargo_size -= landing_moduls_count * landing_module.getSize();
                }
                spaceship.setType(SpaceshipType.PASSENGER);
                spaceship.setCargo_hold(cargo_size);
                return spaceship;

            case CARGO:
                if (cargo_size - living_moduls_count * living_module.getSize() < 0 || living_moduls_count == 0){
                    throw new ArithmeticException("Грузовой корабль не может быть без жилых модулей");
                }
                else{
                    spaceship.setLiving_modul(living_module, living_moduls_count);
                    cargo_size -= living_moduls_count * living_module.getSize();
                }
                if (!weaponInfoisEmpty() && weapons_info.get(ArmamentType.STRATEGIC) == 0 && weapons_info.get(ArmamentType.STANDART) == 0 && weapons_info.get(ArmamentType.DEFENSIVE) != 0){
                    Armament weapon = factory.createDefensiveWeapon();
                    if ((cargo_size * (double) 1/10 - weapon.getSize() * weapons_info.get(ArmamentType.DEFENSIVE)) > 0){
                        spaceship.setWeapons_info(weapons_info);
                        spaceship.setWeapon_defensive(weapon);
                        cargo_size -= weapon.getSize() * weapons_info.get(ArmamentType.DEFENSIVE);
                    }
                    else throw new ArithmeticException("Не хватает места поставить оборонительное вооружение на грузовой корабль");
                }
                else throw new ArithmeticException("Нельзя создать грузовой корабль без защитного оружия или с другим видом");
                if(landing_moduls_count != 0 && cargo_size - landing_moduls_count * landing_module.getSize() > 0){
                    spaceship.setLanding_modul(landing_module, landing_moduls_count);
                    cargo_size -= landing_moduls_count * landing_module.getSize();
                }
//                if(landing_moduls_count != 0 && cargo_size - landing_moduls_count * landing_module.getSize() < 0){
//                    throw new ArithmeticException()
//                }
                spaceship.setType(SpaceshipType.CARGO);
                spaceship.setCargo_hold(cargo_size);
                return spaceship;

            case COLONIZER:
                if(cargo_size - living_moduls_count * living_module.getSize() < 0 || living_moduls_count == 0){
                    throw new ArithmeticException("Колонизаторский корабль не может быть без жилых модулей");
                }
                else{
                    spaceship.setLiving_modul(living_module, living_moduls_count);
                    cargo_size -= living_moduls_count * living_module.getSize();
                }
                if (!weaponInfoisEmpty() && weapons_info.get(ArmamentType.STRATEGIC) == 0 && weapons_info.get(ArmamentType.STANDART) == 0 && weapons_info.get(ArmamentType.DEFENSIVE) != 0){
                    Armament weapon = factory.createDefensiveWeapon();
                    int weapon_size = weapon.getSize() * weapons_info.get(ArmamentType.DEFENSIVE);
                    int living_module_size = living_moduls_count * living_module.getSize();
                    if ((living_module_size * (double) 1/10 - weapon.getSize() * weapons_info.get(ArmamentType.DEFENSIVE)) > 0 && (cargo_size - weapon_size) > 0){
                        spaceship.setWeapons_info(weapons_info);
                        spaceship.setWeapon_defensive(weapon);
                        cargo_size -= weapon_size;
                    }
                    else throw new ArithmeticException("Не хватает места поставить оборонительное вооружение на колонизаторский корабль");
                }
                else throw new ArithmeticException("Нельзя создать колонизаторский корабль без оборонительного вооружения или с другим видом");
                if(landing_moduls_count != 0 && cargo_size - landing_moduls_count * landing_module.getSize() > 0){
                    spaceship.setLanding_modul(landing_module, landing_moduls_count);
                    cargo_size -= landing_moduls_count * landing_module.getSize();
                }
                else throw new ArithmeticException("Нельзя создать военный транспортный корабль без посадочного модуля");
                spaceship.setType(SpaceshipType.COLONIZER);
                spaceship.setCargo_hold(cargo_size);
                return spaceship;

            case MILITARY_TRANSPORT:
                if (!weaponInfoisEmpty() || hull.getArmor() == 0) throw new ArithmeticException("Нельзя создать военный транспортный корабль без оружия или брони");
                //от 1 до 2*v(жилых модулей)/k (ну и как минимум одно, даже если 2*v(жилых модулей)/k<1)
                //как предыдущий, допускается вдвое большее количество орудий и требуется хотя бы одно. Ненулевое бронирование
                if(cargo_size - living_moduls_count * living_module.getSize() < 0 || living_moduls_count == 0){
                    throw new ArithmeticException("военный транспортный корабльь не может быть без жилых модулей");
                }
                else{
                    spaceship.setLiving_modul(living_module, living_moduls_count);
                    cargo_size -= living_moduls_count * living_module.getSize();
                }
                if (weapons_info.get(ArmamentType.STRATEGIC) == 0 && weapons_info.get(ArmamentType.STANDART) == 0 && weapons_info.get(ArmamentType.DEFENSIVE) != 0){
                    Armament weapon = factory.createDefensiveWeapon();
                    int weapon_size = weapon.getSize() * weapons_info.get(ArmamentType.DEFENSIVE);
                    int living_module_size = living_moduls_count * living_module.getSize();
                    if ((living_module_size * (double) 2/10 - weapon.getSize() * weapons_info.get(ArmamentType.DEFENSIVE)) > 0 && (cargo_size - weapon_size) > 0){
                        spaceship.setWeapons_info(weapons_info);
                        spaceship.setWeapon_defensive(weapon);
                        cargo_size -= weapon_size;
                    }
                    else if((cargo_size - weapon.getSize()) > 0){
                        weapons_info.put(ArmamentType.DEFENSIVE, 1);
                        spaceship.setWeapons_info(weapons_info);
                        spaceship.setWeapon_defensive(weapon);
                        cargo_size -= weapon.getSize();
                    }
                    else throw new ArithmeticException("Не хватает места поставить оборонительное вооружение на военный транспортный корабль");
                }
                else throw new ArithmeticException("Нельзя создать военный транспортный корабль без оборонительного вооружения или с другим видом");
                if(landing_moduls_count != 0 && cargo_size - landing_moduls_count * landing_module.getSize() > 0){
                    spaceship.setLanding_modul(landing_module, landing_moduls_count);
                    cargo_size -= landing_moduls_count * landing_module.getSize();
                }
                else throw new ArithmeticException("Нельзя создать военный транспортный корабль без посадочного модуля");
                spaceship.setType(SpaceshipType.COLONIZER);
                spaceship.setCargo_hold(cargo_size);
                return spaceship;

            case MILITARY_GENERAL:
                if (hull.getArmor() == 0) throw new ArithmeticException("Военный корабль общего назначения не может быть с корпусом нулевого бронирования");
                if (cargo_size - living_moduls_count * living_module.getSize() < 0 || living_moduls_count == 0){
                    throw new ArithmeticException("Военный корабль общего назначения не может быть без жилых модулей");
                }
                else{
                    spaceship.setLiving_modul(living_module, living_moduls_count);
                }
                if (!weaponInfoisEmpty() && weapons_info.get(ArmamentType.STRATEGIC) == 0 && weapons_info.get(ArmamentType.STANDART) != 0 && weapons_info.get(ArmamentType.DEFENSIVE) != 0){
                    Armament weapon_defensive = factory.createDefensiveWeapon();
                    Armament weapon_standart = factory.createStandartWeapon();
                    if ((cargo_size - weapon_defensive.getSize() * weapons_info.get(ArmamentType.DEFENSIVE) - weapon_standart.getSize() * weapons_info.get(ArmamentType.STANDART)) > 0){
                        spaceship.setWeapons_info(weapons_info);
                        spaceship.setWeapon_standart(weapon_standart);
                        spaceship.setWeapon_defensive(weapon_defensive);
                        cargo_size -= weapon_defensive.getSize() * weapons_info.get(ArmamentType.DEFENSIVE) - weapon_standart.getSize() * weapons_info.get(ArmamentType.STANDART);
                    }
                    else throw new ArithmeticException("Не хватает места поставить оборонительное вооружение на Военный корабль общего назначения");
                }
                else throw new ArithmeticException("Нельзя создать военный корабль общего назначения без соответствующего вооружения");
                if(landing_moduls_count != 0 && cargo_size - landing_moduls_count * landing_module.getSize() > 0){
                    spaceship.setLanding_modul(landing_module, landing_moduls_count);
                    cargo_size -= landing_moduls_count * landing_module.getSize();
                }
                spaceship.setType(SpaceshipType.MILITARY_GENERAL);
                spaceship.setCargo_hold(cargo_size);
                return spaceship;

            case MILITARY_STRATEGIC:
                if (weaponInfoisEmpty()) throw new ArithmeticException("Военный корабль стратегического назначения не может быть без оружия");
                if (cargo_size - living_moduls_count * living_module.getSize() < 0 || living_moduls_count == 0){
                    throw new ArithmeticException(" Военный корабль стратегического назначения не может быть без жилых модулей");
                }
                else{
                    spaceship.setLiving_modul(living_module, living_moduls_count);
                    cargo_size -= living_moduls_count * living_module.getSize();
                }
                switch (CheckArmament()){
                    case 0: throw new ArithmeticException("Военный корабль стратегического назначения не может быть без 3 типов оружия");
                    case 1: throw new ArithmeticException("Военный корабль стратегического назначения не может быть без 2 типов оружия");
                    case 2:
                        if (weapons_info.get(ArmamentType.STANDART) == 0){
                            Armament weapon_defensive = factory.createDefensiveWeapon();
                            Armament weapon_strategic= factory.createStratagicWeapon();
                            if ((cargo_size - weapon_defensive.getSize() * weapons_info.get(ArmamentType.DEFENSIVE) - weapon_strategic.getSize() * weapons_info.get(ArmamentType.STANDART)) > 0){
                                spaceship.setWeapons_info(weapons_info);
                                spaceship.setWeapon_strategic(weapon_strategic);
                                spaceship.setWeapon_defensive(weapon_defensive);
                                cargo_size -= weapon_defensive.getSize() * weapons_info.get(ArmamentType.DEFENSIVE) - weapon_strategic.getSize() * weapons_info.get(ArmamentType.STANDART);
                            }
                            else throw new ArithmeticException("Не хватает места поставить оборонительное вооружение на Военный корабль стратегического назначения3");
                        }
                        else throw new ArithmeticException("Корабль стратегического назначения не может быть без оборонительного и стратегического вооружения");
//                        if (weapons_info.get(ArmamentType.DEFENSIVE) == 0){
//                            Armament weapon_strategic= factory.createStratagicWeapon();
//                            Armament weapon_standart = factory.createStandartWeapon();
//                            if ((cargo_size - weapon_strategic.getSize() * weapons_info.get(ArmamentType.DEFENSIVE) - weapon_standart.getSize() * weapons_info.get(ArmamentType.STANDART)) > 0){
//                                spaceship.setWeapons_info(weapons_info);
//                                spaceship.setWeapon_strategic(weapon_strategic);
//                                spaceship.setWeapon_standart(weapon_standart);
//                            }
//                            else throw new ArithmeticException("Не хватает места поставить оборонительное вооружение на Военный корабль стратегического назначения2");
//                        }
//                        if (weapons_info.get(ArmamentType.STANDART) == 0){
//                            Armament weapon_defensive = factory.createDefensiveWeapon();
//                            Armament weapon_strategic= factory.createStratagicWeapon();
//                            if ((cargo_size - weapon_defensive.getSize() * weapons_info.get(ArmamentType.DEFENSIVE) - weapon_strategic.getSize() * weapons_info.get(ArmamentType.STANDART)) > 0){
//                                spaceship.setWeapons_info(weapons_info);
//                                spaceship.setWeapon_strategic(weapon_strategic);
//                                spaceship.setWeapon_defensive(weapon_defensive);
//                            }
//                            else throw new ArithmeticException("Не хватает места поставить оборонительное вооружение на Военный корабль стратегического назначения3");
//                        }
                    case 3:
                        Armament weapon_defensive = factory.createDefensiveWeapon();
                        Armament weapon_standart = factory.createStandartWeapon();
                        Armament weapon_strategic = factory.createStratagicWeapon();
                        if ((cargo_size - weapon_defensive.getSize() * weapons_info.get(ArmamentType.DEFENSIVE) - weapon_standart.getSize() * weapons_info.get(ArmamentType.STANDART) - weapon_strategic.getSize() * weapons_info.get(ArmamentType.STRATEGIC)) > 0){
                            spaceship.setWeapons_info(weapons_info);
                            spaceship.setWeapon_strategic(weapon_strategic);
                            spaceship.setWeapon_defensive(weapon_defensive);
                            spaceship.setWeapon_standart(weapon_standart);
                            cargo_size -= weapon_defensive.getSize() * weapons_info.get(ArmamentType.DEFENSIVE) - weapon_standart.getSize() * weapons_info.get(ArmamentType.STANDART) - weapon_strategic.getSize() * weapons_info.get(ArmamentType.STRATEGIC);
                        }
                        else throw new ArithmeticException("Не хватает места поставить вооружение на Военный корабль стратегического назначения4");spaceship.setType(SpaceshipType.MILITARY_STRATEGIC);
                }
                if(landing_moduls_count != 0 && cargo_size - landing_moduls_count * landing_module.getSize() > 0){
                    spaceship.setLanding_modul(landing_module, landing_moduls_count);
                    cargo_size -= landing_moduls_count * landing_module.getSize();
                }
                spaceship.setType(SpaceshipType.MILITARY_STRATEGIC);
                spaceship.setCargo_hold(cargo_size);
                return spaceship;
        }
        return null;
    }

    private int CheckArmament(){
        int result = 0;
        for (ArmamentType key: weapons_info.keySet()){
            if (weapons_info.get(key) !=0) result += 1;
        }
        return result;
//        int defesive_count = weapons_info.get(ArmamentType.DEFENSIVE);
//        int standart_count = weapons_info.get(ArmamentType.STANDART);
//        int strategic_count = weapons_info.get(ArmamentType.STRATEGIC);
//        return defesive_count * standart_count + defesive_count * strategic_count + standart_count * strategic_count; // 0 - если 0 оружия или 1 тип. 1 - если 2 типа оружия. 3 - если все 3 типа
    }

    private HashMap<ArmamentType, Integer> createHs(){
        HashMap<ArmamentType, Integer> hs = new HashMap<>();
        hs.put(ArmamentType.STRATEGIC, 0);
        hs.put(ArmamentType.STANDART, 0);
        hs.put(ArmamentType.DEFENSIVE, 0);
        return hs;
    }

    private boolean weaponInfoisEmpty(){
        if (weapons_info.get(ArmamentType.STRATEGIC) == 0 && weapons_info.get(ArmamentType.STANDART) == 0 && weapons_info.get(ArmamentType.DEFENSIVE) == 0){
            return true;
        }
        return false;
    }

    public Spaceship.Type getAppointment() {
        return appointment;
    }

//    public ResearhSpaceshipBuilder(ModulsFactory factory, int A, int n, int engine_size, HashMap<Moduls, Integer> radars, HashMap<Moduls, Integer> living_moduls, HashMap<Moduls, Integer> landing_moduls, HashMap<Moduls, Integer> weapons, int frame_size) {
//        this.factory = factory;
//
//    }


//
//    @Override
//    public SpaceshipBuilder addEngine(Moduls engine) {// Скажем, не менее 1/12 от объёма корпуса. И до 1/2 у плохого двигателя или до 1/8 у хорошего.
//        if (engine.getSize() < frame_size* ((double)1/12) && engine.getSize() > (double)1/2 * frame_size){
//            System.out.println("Размеры этого двигателя не подходят для данного корпуса");
//            return null; // возможно тут будет ошибка
//        }else if (research_engine == null){ // Добавил условие, что бы при повторном вызове метода двигатель заменялся
//            frame_size -= engine.getSize();
//            research_engine = engine;
//        }else{
//            frame_size += research_engine.getSize() - engine.getSize();// здесь возможно не нужен this
//            research_engine = engine;
//        }
//        if (frame_size >= 0) {
//            return this;
//        }
//        else{
//            System.out.println("Данный двигатель не подходит корпусу");
//            return null;
//        }
//    }
//
//    @Override
//    public SpaceshipBuilder addRadar(Radar ...mass) {
//        if (frame_size > 0 && mass.length != 0) { // тк исследовательскому кораблю необходим радар
//            for (Radar radar : mass) {
//                radar_array.add(radar);
//                frame_size -= radar.getSize();
//            }
//            if(frame_size >= 0) {
//                return this;
//            }
//            else{ // тк жилому модулю необходим радар
//                System.out.println("Не хватило место что бы добавить радар");
//                return null;
//            }
//        }
//        else{
//            System.out.println("Нельзя создать исследовательский корабль ");
//            return null; // нужно посмотреть что будет в данном случае
//        }
//    }
//
//    @Override
//    public SpaceshipBuilder addLivingModule(LivingModule ...mass) {
//        int minus_size = 0;
//        if (mass.length != 0) { // если передали жилой модуль{
//            for (LivingModule living_module : mass) {
//                minus_size += living_module.getSize();
//            }
//            if (frame_size - minus_size >= 0) { // если хватает места
//                living_module_array.addAll(Arrays.asList(mass));
//                frame_size -= minus_size;
//                return this;
//            }
//            else{ // если не хватает места на жилой модуль
//                return this;
//            }
//        }
//        else{
//            return this;
//        }
//    }
////        if (frame_size > 0) { // при наличии свободного месте можно добавить жилой модуль
////            living_module_array.add(living_module);
////            frame_size -= living_module.getSize();
////            return this;
////        }
////        else{
////            System.out.println("Нельзя создать исследовательский корабль, кончилось место");
////            return null; // нужно посмотреть что будет в данном случае
////        }
////    }
//
//    @Override
//    public SpaceshipBuilder addLangindModule(LandingModule ...mass) {
//        int minus_size = 0;
//        if (mass.length != 0) { // если передали посадочный модуль{
//            for (LandingModule living_module : mass) {
//                minus_size += living_module.getSize();
//            }
//            if (frame_size - minus_size >= 0) { // если хватает места
//                landing_module_array.addAll(Arrays.asList(mass));
//                frame_size -= minus_size;
//                return this;
//            }
//            else{ // если не хватает места на модуль высадки
//                return this;
//            }
//        }
//        else{ // может быть и без этого модуля
//            return this;
//        }
//    }
//
//    @Override
//    public SpaceshipBuilder addArmament(Armament ...mass) {
//        if (mass.length != 0) {
//            System.out.println("исследовательский корабль не имеет вооружения");
//            return null;
//        }
//        else{
//            return this;
//        }
//    }
//
//    @Override
//    public Spaceship build() { // можно тут дописать метод Check, который будет проверять наличие необходимых элементов что бы построить корабль
//        Spaceship spaceship = new Spaceship(frame, frame_size, research_engine, radar_array, living_module_array, landing_module_array, research_armament_array);
//        return spaceship;
//    }
//

//    public int test(Armament ...mass){
//        if (mass != null) return 1;
//        else return 0;
//    }
}
