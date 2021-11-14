package com.company.builder;
import com.company.factory.*;

import java.util.ArrayList;
import java.util.Arrays;


public class ResearhSpaceshipBuilder implements SpaceshipBuilder{ //единственный может не иметь жилого модуля.
    // Приоритет – максимальное количество радаров. Без вооружения.
    private Frame frame;
    private int frame_size;
    private Moduls research_engine;
    private ArrayList<Radar> radar_array;
    private ArrayList<LivingModule> living_module_array;
    private ArrayList<LandingModule> landing_module_array;
    private ArrayList<Armament> research_armament_array;


    public ResearhSpaceshipBuilder(Frame frame){
        this.frame = frame;
        this.frame_size = frame.getSize();
    }

    @Override
    public SpaceshipBuilder addEngine(Moduls engine) {// Скажем, не менее 1/12 от объёма корпуса. И до 1/2 у плохого двигателя или до 1/8 у хорошего.
        if (engine.getSize() < frame_size* ((double)1/12) && engine.getSize() > (double)1/2 * frame_size){
            System.out.println("Размеры этого двигателя не подходят для данного корпуса");
            return null; // возможно тут будет ошибка
        }else if (research_engine == null){ // Добавил условие, что бы при повторном вызове метода двигатель заменялся
            frame_size -= engine.getSize();
            research_engine = engine;
        }else{
            frame_size += research_engine.getSize() - engine.getSize();// здесь возможно не нужен this
            research_engine = engine;
        }
        if (frame_size >= 0) {
            return this;
        }
        else{
            System.out.println("Данный двигатель не подходит корпусу");
            return null;
        }
    }

    @Override
    public SpaceshipBuilder addRadar(Radar ...mass) {
        if (frame_size > 0 && mass != null) { // тк исследовательскому кораблю необходим радар
            for (Radar radar : mass) {
                radar_array.add(radar);
                frame_size -= radar.getSize();
            }
            if(frame_size >= 0) {
                return this;
            }
            else{ // тк жилому модулю необходим радар
                System.out.println("Не хватило место что бы добавить радар");
                return null;
            }
        }
        else{
            System.out.println("Нельзя создать исследовательский корабль ");
            return null; // нужно посмотреть что будет в данном случае
        }
    }

    @Override
    public SpaceshipBuilder addLivingModule(LivingModule ...mass) {
        int minus_size = 0;
        if (mass != null) { // если передали жилой модуль{
            for (LivingModule living_module : mass) {
                minus_size += living_module.getSize();
            }
            if (frame_size - minus_size >= 0) { // если хватает места
                living_module_array.addAll(Arrays.asList(mass));
                frame_size -= minus_size;
                return this;
            }
            else{ // если не хватает места на жилой модуль
                return this;
            }
        }
        else{
            return this;
        }
    }
//        if (frame_size > 0) { // при наличии свободного месте можно добавить жилой модуль
//            living_module_array.add(living_module);
//            frame_size -= living_module.getSize();
//            return this;
//        }
//        else{
//            System.out.println("Нельзя создать исследовательский корабль, кончилось место");
//            return null; // нужно посмотреть что будет в данном случае
//        }
//    }

    @Override
    public SpaceshipBuilder addLangindModule(LandingModule ...mass) {
        int minus_size = 0;
        if (mass != null) { // если передали посадочный модуль{
            for (LandingModule living_module : mass) {
                minus_size += living_module.getSize();
            }
            if (frame_size - minus_size >= 0) { // если хватает места
                landing_module_array.addAll(Arrays.asList(mass));
                frame_size -= minus_size;
                return this;
            }
            else{ // если не хватает места на модуль высадки
                return this;
            }
        }
        else{ // может быть и без этого модуля
            return this;
        }
    }

    @Override
    public SpaceshipBuilder addArmament(Armament ...mass) {
        if (mass != null) {
            System.out.println("исследовательский корабль не имеет вооружения");
            return null;
        }
        else{
            return this;
        }
    }

    @Override
    public Spaceship build() { // можно тут дописать метод Check, который будет проверять наличие необходимых элементов что бы построить корабль
        Spaceship spaceship = new Spaceship(frame, frame_size, research_engine, radar_array, living_module_array, landing_module_array, research_armament_array);
        return spaceship;
    }

    @Override
    public String toString() {
        return "ResearhSpaceshipBuilder{" +
                "frame=" + frame +
                ", frame_size=" + frame_size +
                ", research_engine=" + research_engine +
                ", radar_array=" + radar_array +
                ", living_module_array=" + living_module_array +
                ", landing_module_array=" + landing_module_array +
                ", research_armament_array=" + research_armament_array +
                '}';
    }


}
