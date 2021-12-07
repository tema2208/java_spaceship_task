package com.company;

public class Builder {
    private int MotorSize=0;
    private int VariableCorpusSize=0;
    private double ArmorCorpus=0;
    private int radarsCount=0;
    private int livingCount=0;
    private int landingCount=0;
    private int commonWeaponCount=0;
    private int strategicWeaponCount=0;
    private int defensiveWeaponCount=0;
    private int cargoHold;
    private double price;
    private TypeMotors motor;
    private TypeSpaceships spaceshipType;
    private AbstractFactory Factory;

    public Builder setFactory(AbstractFactory factory){
        Factory=factory;
        return this;
    }
    public Builder setMotorSize(int size){
        MotorSize=size;
        return this;
    }
    public Builder setVariableCorpusSize(double armor,int n) {
        VariableCorpusSize = n;
        ArmorCorpus=armor;
        return this;
    }
    public Builder setTypeSpaceship(TypeMotors n,TypeSpaceships m){
        motor=n;
        spaceshipType=m;
        return this;
    }
    public Builder setRadarCounts(int n){
        radarsCount=n;
        return this;
    }
    public Builder setLivingCounts(int n){
        livingCount=n;
        return this;
    }
    public Builder setLandingCounts(int n){
        landingCount=n;
        return this;
    }
    public Builder setCommonWeaponCounts(int n){
        commonWeaponCount=n;
        return this;
    }
    public Builder setStrategicWeaponCounts(int n){
        strategicWeaponCount=n;
        return this;
    }
    Builder setDefensiveWeaponCounts(int n){
        defensiveWeaponCount=n;
        return this;
    }
    private Builder setPrice(){
        price=0;
        price+=Factory.getCorpus(ArmorCorpus,VariableCorpusSize).getPrice();
        price+=Factory.getCommonMotor(MotorSize,Factory.getCorpus(ArmorCorpus,VariableCorpusSize)).getPrice();
        price+=Factory.getHypermotor(MotorSize,Factory.getCorpus(ArmorCorpus,VariableCorpusSize) ).getPrice();
        price+=Factory.getRadar().getPrice()*radarsCount;
        price+=Factory.getLivingUnit().getPrice()*livingCount;
        price+=Factory.getLandingUnit().getPrice()*landingCount;
        price+=Factory.getCommonWeapon().getPrice()*commonWeaponCount;
        price+=Factory.getStrategicWeapon().getPrice()*strategicWeaponCount;
        price+=Factory.getDefensiveWeapon().getPrice()*defensiveWeaponCount;
        return this;
    }
    public String toString() {
        return " Будущий космический корабль будет иметь: цена="+price+"\n"+
                ", корпус=" + Factory.getCorpus(ArmorCorpus,VariableCorpusSize)+"\n" +
                ", обычный двигатель=" + Factory.getCommonMotor(MotorSize,Factory.getCorpus(ArmorCorpus,VariableCorpusSize))+"\n" +
                ", гипердвигатель=" +Factory.getHypermotor(MotorSize,Factory.getCorpus(ArmorCorpus,VariableCorpusSize) )+"\n" +
                ", радар=" + Factory.getRadar()+"\n" +
                ", количество радаров=" + radarsCount+"\n" +
                ", жилой модуль=" + Factory.getLivingUnit()+"\n" +
                ", количество жилых модулей=" + livingCount+"\n" +
                ", десантный модуль=" + Factory.getLandingUnit()+"\n" +
                ", количество десантных модулей=" + landingCount+"\n" +
                ", обычное оружие=" + Factory.getCommonWeapon()+"\n" +
                ", количество обычного оружия=" + commonWeaponCount+"\n" +
                ", стратегическое оружие=" + Factory.getStrategicWeapon()+"\n" +
                ", количество стратегического оружие=" + strategicWeaponCount+"\n" +
                ", оборонительное оружие=" + Factory.getDefensiveWeapon()+"\n" +
                ", количество оборонительного оружие=" + defensiveWeaponCount+"\n" +
                ", грузовой трюм=" + cargoHold+"\n" +
                ", тип=" + spaceshipType + " и " + motor;
    }
    public Spaceship build(){
        Spaceship spaceship = new Spaceship();
        if ((VariableCorpusSize == 0) || (radarsCount == 0) || (MotorSize == 0)) {
            throw new IllegalArgumentException("Нельзя собрать корабль без корпуса, радара или двигателя");
        }
        Corpus hull = Factory.getCorpus(ArmorCorpus,VariableCorpusSize);
        LivingUnit livingUnit = Factory.getLivingUnit();
        Radar radar = Factory.getRadar();
        LandingUnit landingUnit = Factory.getLandingUnit();
        int cargoHold = hull.getSize() - MotorSize - radar.getSize() * radarsCount;
        if (cargoHold > 0){
            CommonMotor commonMotor = Factory.getCommonMotor(MotorSize, hull);
            if(commonMotor.getSize()<commonMotor.getMINCONST()* hull.getSize()||commonMotor.getSize()>commonMotor.getMaxConst()* hull.getSize())throw new IllegalArgumentException("Невозможный размер двигателя");
            spaceship.setCorpus(hull);
            spaceship.setCommonMotor(commonMotor);
            if(motor == TypeMotors.МЕЖЗВЁЗДНЫЙ){
                if(cargoHold>MotorSize){
                    Hypermotor hyperMotor = Factory.getHypermotor(MotorSize, hull);
                    if(hyperMotor.getSize()<hyperMotor.getMINCONST()* hull.getSize()||hyperMotor.getSize()>hyperMotor.getMaxConst()* hull.getSize())throw new IllegalArgumentException("Невозможный размер гипердвигателя");
                    spaceship.setTypeMotor(TypeMotors.МЕЖЗВЁЗДНЫЙ);
                    cargoHold-=hyperMotor.getSize();
                    spaceship.setHyperMotor(hyperMotor);
                }
                else throw new IllegalArgumentException("Не хватает места для гипердвигателя");
            }
            spaceship.setTypeMotor(TypeMotors.ОБЫЧНЫЙ);
            spaceship.setRadar(radar, radarsCount);
        }
        else{ throw new IllegalArgumentException("В корпус не помещается необходимое для любого корабля оборудование");}

        switch(spaceshipType) {
            case ИССЛЕДОВАТЕЛЬСКИЙ:
                if (commonWeaponCount != 0 || defensiveWeaponCount != 0 || strategicWeaponCount != 0) {
                    throw new IllegalArgumentException("Исследовательский корабль не содержит оружие");
                }
                if (cargoHold >livingCount * livingUnit.getSize()) {
                    spaceship.setLivingUnit(livingUnit, livingCount);
                    cargoHold -= livingCount * livingUnit.getSize();
                }
                if (cargoHold >landingCount * landingUnit.getSize()) {
                    spaceship.setLandingUnit(landingUnit, landingCount);
                    cargoHold -= landingCount * landingUnit.getSize();
                }
                spaceship.setTypeSpaceship(TypeSpaceships.ИССЛЕДОВАТЕЛЬСКИЙ);
                spaceship.setCargoHold(cargoHold);
                spaceship.setPrice();
                return spaceship;

            case ПАССАЖИРСКИЙ:
                if (commonWeaponCount != 0 || defensiveWeaponCount != 0 || strategicWeaponCount != 0) {
                    throw new IllegalArgumentException("Пасажирский корабль не содержит оружие");
                }
                if (cargoHold <livingCount * livingUnit.getSize() || livingCount == 0) {
                    throw new IllegalArgumentException("Пасажирский корабль не может быть без жилых модулей");
                } else {
                    spaceship.setLivingUnit(livingUnit, livingCount);
                    cargoHold -= livingCount * livingUnit.getSize();
                }
                if (cargoHold - landingCount * landingUnit.getSize() > 0) {
                    spaceship.setLandingUnit(landingUnit, landingCount);
                    cargoHold -= landingCount * landingUnit.getSize();
                }
                spaceship.setTypeSpaceship(TypeSpaceships.ПАССАЖИРСКИЙ);
                spaceship.setCargoHold(cargoHold);
                spaceship.setPrice();
                return spaceship;

            case ГРУЗОВОЙ:
                if (cargoHold < livingCount * livingUnit.getSize() || livingCount == 0) {
                    throw new IllegalArgumentException("Грузовой корабль не может быть без жилых модулей");
                } else{
                    spaceship.setLivingUnit(livingUnit, livingCount);
                    cargoHold-=livingCount*livingUnit.getSize();
                }
                if (commonWeaponCount == 0 && defensiveWeaponCount != 0 && strategicWeaponCount == 0) {
                    DefensiveWeapon weapon = Factory.getDefensiveWeapon();
                    if ((cargoHold- weapon.getSize() * defensiveWeaponCount) * (double) 1 / 10 > weapon.getSize() * defensiveWeaponCount) {
                        spaceship.setDefensiveWeapon(weapon, defensiveWeaponCount);
                        cargoHold-=defensiveWeaponCount*weapon.getSize();
                    }
                    else
                        throw new IllegalArgumentException("Не хватает места поставить оборонительное вооружение на грузовой корабль");
                } else
                    throw new IllegalArgumentException("Нельзя создать грузовой корабль без оборонительного оружия или с другим видом");
                spaceship.setTypeSpaceship(TypeSpaceships.ГРУЗОВОЙ);
                spaceship.setCargoHold(cargoHold);
                spaceship.setPrice();
                return spaceship;

            case КОЛОНИЗАТОР:
                if (cargoHold<livingCount * livingUnit.getSize() || livingCount == 0) {
                    throw new IllegalArgumentException("Колонизаторский корабль не может быть без жилых модулей");
                } else {
                    spaceship.setLivingUnit(livingUnit, livingCount);
                    cargoHold -= livingCount * livingUnit.getSize();
                }
                if (cargoHold < landingCount * livingUnit.getSize() || landingCount == 0) {
                    throw new IllegalArgumentException("Колонизаторский корабль не может быть без жилых модулей");
                } else {
                    spaceship.setLandingUnit(landingUnit, landingCount);
                    cargoHold -= landingCount * landingUnit.getSize();
                }
                if (commonWeaponCount == 0 && defensiveWeaponCount != 0 && strategicWeaponCount == 0) {
                    DefensiveWeapon weapon = Factory.getDefensiveWeapon();
                    if ((livingCount * livingUnit.getSize() * (double) 1 / 10 > weapon.getSize() * defensiveWeaponCount) && (cargoHold>weapon.getSize() * defensiveWeaponCount)) {
                        spaceship.setDefensiveWeapon(weapon, defensiveWeaponCount);
                        cargoHold-=defensiveWeaponCount*weapon.getSize();
                    } else
                        throw new IllegalArgumentException("Не хватает места поставить оборонительное вооружение на колонизаторский корабль");
                } else
                    throw new IllegalArgumentException("Нельзя создать колонизаторский корабль без оборонительного вооружения или с другим видом");
                spaceship.setTypeSpaceship(TypeSpaceships.КОЛОНИЗАТОР);
                spaceship.setCargoHold(cargoHold);
                spaceship.setPrice();
                return spaceship;

            case ВОЕННОТРАНСПОРТНЫЙ:
                if (hull.getArmor() == 0)
                    throw new IllegalArgumentException("Военно-транпортный корабль не может быть с корпусом нулевого бронирования");
                if (cargoHold<livingCount * livingUnit.getSize() || livingCount == 0) {
                    throw new IllegalArgumentException("Военно-транспортный корабль не может быть без жилых модулей");
                } else {
                    spaceship.setLivingUnit(livingUnit, livingCount);
                    cargoHold -= livingCount * livingUnit.getSize();
                }
                if (cargoHold<landingCount * livingUnit.getSize() || landingCount == 0) {
                    throw new IllegalArgumentException("Военно-транспортный корабль не может быть без десантных модулей");
                } else {
                    spaceship.setLandingUnit(landingUnit, landingCount);
                    cargoHold -= landingCount * landingUnit.getSize();
                }
                if (commonWeaponCount == 0 && defensiveWeaponCount != 0 && strategicWeaponCount == 0) {
                    DefensiveWeapon weapon = Factory.getDefensiveWeapon();
                    if ((livingCount * livingUnit.getSize() * (double) 1 / 5 > weapon.getSize() * defensiveWeaponCount) && (cargoHold >weapon.getSize() * defensiveWeaponCount)) {
                        spaceship.setDefensiveWeapon(weapon, defensiveWeaponCount);
                        cargoHold-=weapon.getSize()*defensiveWeaponCount;
                    } else
                        throw new IllegalArgumentException("Не хватает места поставить оборонительное вооружение на военно-транспортный корабль");
                } else
                    throw new IllegalArgumentException("Нельзя создать военно-транспортный корабль без оборонительного вооружения или с другим видом");
                spaceship.setTypeSpaceship(TypeSpaceships.ВОЕННОТРАНСПОРТНЫЙ);
                spaceship.setCargoHold(cargoHold);
                spaceship.setPrice();
                return spaceship;

            case ОБЩЕВОЕННЫЙ:
                if (hull.getArmor() == 0)
                    throw new IllegalArgumentException("Военный корабль общего назначения не может быть с корпусом нулевого бронирования");
                if (cargoHold<livingCount * livingUnit.getSize() || livingCount == 0) {
                    throw new IllegalArgumentException("Военный корабль общего назначения не может быть без жилых модулей");
                } else {
                    spaceship.setLivingUnit(livingUnit, livingCount);
                    cargoHold -= livingCount * livingUnit.getSize();
                }
                if (commonWeaponCount != 0 && defensiveWeaponCount != 0 && strategicWeaponCount == 0) {
                    DefensiveWeapon weaponDefensive = Factory.getDefensiveWeapon();
                    CommonWeapon weaponCommon = Factory.getCommonWeapon();
                    if ((cargoHold - weaponDefensive.getSize() * defensiveWeaponCount - weaponCommon.getSize() * commonWeaponCount) > 0) {
                        spaceship.setCommonWeapon(weaponCommon, commonWeaponCount);
                        spaceship.setDefensiveWeapon(weaponDefensive, defensiveWeaponCount);
                        cargoHold=cargoHold-weaponDefensive.getSize() * defensiveWeaponCount - weaponCommon.getSize() * commonWeaponCount;
                    } else
                        throw new IllegalArgumentException("Не хватает места поставить вооружение на Военный корабль общего назначения");
                } else
                    throw new IllegalArgumentException("Нельзя создать военный корабль общего назначения без соответствующего вооружения");
                spaceship.setTypeSpaceship(TypeSpaceships.ОБЩЕВОЕННЫЙ);
                spaceship.setCargoHold(cargoHold);
                spaceship.setPrice();
                return spaceship;

            case ВОЕННОСТРАТЕГИЧЕСКИЙ:
                if (cargoHold<livingCount * livingUnit.getSize() || livingCount == 0) {
                    throw new IllegalArgumentException("Военный корабль стратегического назначения не может быть без жилых модулей");
                } else {
                    spaceship.setLivingUnit(livingUnit, livingCount);
                    cargoHold -= livingCount * livingUnit.getSize();
                }
                if(commonWeaponCount == 0 && defensiveWeaponCount == 0 && strategicWeaponCount == 0) throw new IllegalArgumentException("Военный корабль стратегического назначения не может быть без оружия");
                if(commonWeaponCount+defensiveWeaponCount+strategicWeaponCount<=1) throw new IllegalArgumentException("Военный корабль стратегического назначения не может быть без 2 или 3 типов оружия");
                if (defensiveWeaponCount!=0&&strategicWeaponCount!=0) {
                    DefensiveWeapon weaponDefensive = Factory.getDefensiveWeapon();
                    StrategicWeapon weaponStrategic = Factory.getStrategicWeapon();
                    if ((cargoHold - weaponDefensive.getSize() * defensiveWeaponCount - weaponStrategic.getSize() * strategicWeaponCount) > 0) {
                        spaceship.setStrategicWeapon(weaponStrategic, strategicWeaponCount);
                        spaceship.setDefensiveWeapon(weaponDefensive, defensiveWeaponCount);
                        cargoHold=cargoHold- weaponDefensive.getSize() * defensiveWeaponCount - weaponStrategic.getSize() * strategicWeaponCount;
                    }
                    else if(commonWeaponCount!=0){
                        CommonWeapon weaponCommon = Factory.getCommonWeapon();
                        if (cargoHold > weaponCommon.getSize() * commonWeaponCount){
                            spaceship.setCommonWeapon(weaponCommon, commonWeaponCount);
                            cargoHold-=weaponCommon.getSize() * commonWeaponCount;
                        }
                        else throw new IllegalArgumentException("Не хватает места поставить вооружение на Военный корабль стратегического назначения");
                    }
                    else
                        throw new IllegalArgumentException("Не хватает места поставить вооружение на Военный корабль стратегического назначения");
                }
                spaceship.setTypeSpaceship(TypeSpaceships.ВОЕННОТРАНСПОРТНЫЙ);
                spaceship.setCargoHold(cargoHold);
                spaceship.setPrice();
                return spaceship;
            default:
                throw new IllegalArgumentException("Не указан тип создаваемого корабля");
        }
    }
}
