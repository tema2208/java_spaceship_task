package com.company.builder;
import com.company.factory.*;

import java.util.HashMap;


public class ResearhSpaceshipBuilder implements SpaceshipBuilder{ //единственный может не иметь жилого модуля.
    // Приоритет – максимальное количество радаров. Без вооружения.
    private ModulsFactory factory;
    private int hull_size = 0;
    private int engine_size = 0;
    private EngineType engine_type;
    private int radar_count = 0;
    private int living_moduls_count = 0;
    private int landing_moduls_count = 0;
    private HashMap<ArmamentType, Integer> weapons_info = null;
    private SpaceshipType spaceship_type;
    private Spaceship.Type appointment;

    @Override
    public SpaceshipBuilder setAppointment(Spaceship.Type appointment) {
        this.appointment = appointment;
        return this;
    }

    @Override
    public SpaceshipBuilder setFactory(ModulsFactory f) {
        this.factory = f;
        return this;
    }

    @Override
    public SpaceshipBuilder setHullSize(int n) {
        this.hull_size = n;
        return this;
    }

    @Override
    public SpaceshipBuilder setEngineSize(int n) {
        this.engine_size = n;
        return this;
    }

    @Override
    public SpaceshipBuilder setEngineType(EngineType type) {
        this.engine_type = type;
        return this;
    }

    @Override
    public SpaceshipBuilder setRadarCount(int n) {
        this.radar_count = n;
        return this;
    }

    @Override
    public SpaceshipBuilder setLivingModulsCount(int n) {
        this.living_moduls_count = n;
        return this;
    }

    @Override
    public SpaceshipBuilder setLandingModulsCount(int n) {
        this.landing_moduls_count = n;
        return this;
    }

    @Override
    public SpaceshipBuilder setWeaponsInfo(ArmamentType type, int count) {
        this.weapons_info.put(type, count);
        return this;
    }

    @Override
    public SpaceshipBuilder setSpaceshipType(SpaceshipType type) {
        this.spaceship_type = type;
        return this;
    }

    @Override
    public Spaceship build() { // Нужно решить вопрос с двигателями,
        Spaceship spaceship = new Spaceship();
        if ((hull_size == 0) || (radar_count == 0) || (engine_size == 0)) {
            System.out.println("Нельзя создать корабль без корпуса, двигателя или без радара");
            return null;
        }

        Frame hull = factory.createHull(hull_size);
        StandartEngine standart_engine = factory.createStandartEngine(engine_size);
        SuperEngine super_engine = factory.createSuperEngine(engine_size);
        Radar radar = factory.createRadar();
        LivingModule living_module = factory.createLivingModule();
        LandingModule landing_module = factory.createLandingModule();

        Engine engine = null;

        if (appointment == Spaceship.Type.SYSTEM) engine = standart_engine;
        else engine = super_engine;

        if (engine.getSize() < hull.getSize()* ((double)1/12) && engine.getSize() > (double)1/2 * hull.getSize()){ // тут надо исправить
            return null;
        }
        int cargo_size = hull.getSize() - engine_size - radar.getSize() * radar_count;
        if (cargo_size > 0){
            spaceship.setHull(hull);

            spaceship.setStandartEngine(standart_engine);
            spaceship.setRadar(radar, radar_count);
        }
        else{ throw new ArithmeticException("В корпус не помещается необходимое для любого корабля оборудование");}

        switch(spaceship_type){
            case RESEARCH:
                if (weapons_info != null){
                    throw new ArithmeticException("Исследовательский корабль не содержит оружие");
                }
                if(cargo_size - living_moduls_count * living_module.getSize() > 0){
                    spaceship.setLiving_modul(living_module, living_moduls_count);
                    cargo_size -= living_moduls_count * living_module.getSize();
                }
                if(cargo_size - landing_moduls_count * landing_module.getSize() > 0){
                    spaceship.setLanding_modul(landing_module, landing_moduls_count);
                    cargo_size -= landing_moduls_count * landing_module.getSize();
                }
                spaceship.setType(SpaceshipType.RESEARCH);
                return spaceship;
            case PASSENGER:
                if (weapons_info != null) {
                    throw new ArithmeticException("Пасажирский корабль не содержит оружие");
                }
                if(cargo_size - living_moduls_count * living_module.getSize() < 0 || living_moduls_count == 0){
                    throw new ArithmeticException("Пасажирский корабль не может быть без жилых модулей");
                }
                else{
                    spaceship.setLiving_modul(living_module, living_moduls_count);
                    cargo_size -= living_moduls_count * living_module.getSize();
                }
                if(cargo_size - landing_moduls_count * landing_module.getSize() > 0){
                    spaceship.setLanding_modul(landing_module, landing_moduls_count);
                    cargo_size -= landing_moduls_count * landing_module.getSize();
                }
                spaceship.setType(SpaceshipType.PASSENGER);
                return spaceship;
            case CARGO: // под минимальным набором модулей я понимаю двигатель, радар и жилой модуль(их должно быть по одному?)
                //Грузовой: минимальный набор модулей, не нулевой трюм. Орудия типа «оборонительное»,
                // общим размером не более 1/k от объёма трюма, где k≥10
                if (cargo_size - living_moduls_count * living_module.getSize() < 0 || living_moduls_count == 0){
                    throw new ArithmeticException("Грузовой корабль не может быть без жилых модулей");
                }
                else{
                    spaceship.setLiving_modul(living_module, living_moduls_count);
                }



        }
        return null;
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
