package com.company;

public class Main {

    public static void main(String[] args) {
    BigFactory factory=new BigFactory();
    Builder builder=new Builder();
    try {
        Spaceship product = builder.setFactory(factory).setVariableCorpusSize(15, 2).setMotorSize(40).setTypeSpaceship(TypeMotors.ОБЫЧНЫЙ, TypeSpaceships.ПАССАЖИРСКИЙ).setRadarCounts(2).setLivingCounts(1).build();
        System.out.println(product.toString());
    }
    catch(IllegalArgumentException ex){
        System.out.println(ex.getMessage());
    }
    }
}
