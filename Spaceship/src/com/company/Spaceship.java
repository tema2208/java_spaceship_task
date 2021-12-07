package com.company;


public class Spaceship {
    private Corpus hull=null;
    private CommonMotor commonMotor=null;
    private Hypermotor hyperMotor=null;
    private Radar radar=null;
    private LivingUnit livingUnit=null;
    private LandingUnit landingUnit=null;
    private CommonWeapon commonWeapon=null;
    private StrategicWeapon strategicWeapon=null;
    private DefensiveWeapon defensiveWeapon=null;
    private int radarsCount;
    private int livingCount;
    private int landingCount;
    private int commonWeaponCount;
    private int strategicWeaponCount;
    private int defensiveWeaponCount;
    private int cargoHold;
    private TypeMotors motor;
    private TypeSpaceships spaceship;
    private double price;
    public void setCorpus(Corpus hull) {
        this.hull = hull;
    }
    public void setCommonMotor(CommonMotor engine) {
        commonMotor = engine;
    }
    public void setHyperMotor(Hypermotor engine) {
        hyperMotor=engine;
    }

    public void setRadar(Radar radar, int radars_count) {
        this.radar = radar;
        radarsCount = radars_count;
    }


    public void setLivingUnit(LivingUnit living, int livingCount) {
        livingUnit = living;
        this.livingCount = livingCount;
    }


    public void setLandingUnit(LandingUnit landing, int landingCount) {
        landingUnit = landing;
        this.landingCount = landingCount;
    }


    public void setCommonWeapon(CommonWeapon weapon, int commonWeaponCount) {
        commonWeapon=weapon;
        this.commonWeaponCount=commonWeaponCount;
    }
    public void setStrategicWeapon(StrategicWeapon weapon, int strategicWeaponCount) {
        strategicWeapon=weapon;
        this.strategicWeaponCount=strategicWeaponCount;
    }
    public void setDefensiveWeapon(DefensiveWeapon weapon, int defensiveWeaponCount) {
        defensiveWeapon=weapon;
        this.defensiveWeaponCount=defensiveWeaponCount;
    }
    public void setTypeSpaceship(TypeSpaceships type) {
        spaceship = type;
    }
    public void setTypeMotor(TypeMotors type){
        motor=type;
    }
    public void setCargoHold(int cargo){
        cargoHold=cargo;
    }

    @Override
    public String toString() {
        return "Космический корабль: цена="+price+"\n" +
                ", корпус=" + hull+"\n" +
                ", обычный двигатель=" + commonMotor+"\n" +
                ", гипердвигатель=" +hyperMotor+"\n" +
                ", радар=" + radar+"\n" +
                ", количество радаров=" + radarsCount+"\n" +
                ", жилой модуль=" + livingUnit+"\n" +
                ", количество жилых модулей=" + livingCount+"\n" +
                ", десантный модуль=" + landingUnit+"\n" +
                ", количество десантных модулей=" + landingCount+"\n" +
                ", обычное оружие=" + commonWeapon+"\n" +
                ", количество обычного оружия=" + commonWeaponCount+"\n" +
                ", стратегическое оружие=" + strategicWeapon+"\n" +
                ", количество стратегического оружие=" + strategicWeaponCount+"\n" +
                ", оборонительное оружие=" + defensiveWeapon+"\n" +
                ", количество оборонительного оружие=" + defensiveWeaponCount+"\n" +
                ", грузовой трюм=" + cargoHold+"\n" +
                ", тип=" + spaceship + " и " + motor;
    }
    public void setPrice(){
        price=0;
        price+= hull.getPrice();
        price+=commonMotor.getPrice();
        if(hyperMotor!=null) price+=hyperMotor.getPrice();
        price+=radar.getPrice()*radarsCount;
        if(livingUnit!=null)price+=livingUnit.getPrice()*livingCount;
        if(landingUnit!=null)price+=landingUnit.getPrice()*landingCount;
        if(commonWeapon!=null)price+=commonWeapon.getPrice()*commonWeaponCount;
        if(strategicWeapon!=null)price+=strategicWeapon.getPrice()*strategicWeaponCount;
        if(defensiveWeapon!=null)price+=defensiveWeapon.getPrice()*defensiveWeaponCount;
    }
}
