package com.Main;
import java.util.Scanner;
import java.util.AddList;

public class Main<toString> {

    Hero [] heroes = new Hero[2];
    heroes[0] = "Stella";
    heroes[1] = "Bloom";

    heroes[0].giveItem("Крылышки Чармикса", 1 , 30);
    heroes[0].addWeapon("Кольцо Соляриса", 1, 35, 15);
    heroes[0].addFood("Яблоко", 2, 6, 3);

    heroes[1].giveItem("Крылышки энчантикса", 1, 45);
    heroes[1].addWeapon("Флакончик с волшебной пыльцой", 0.3, 35, 24);
    heroes[1].addFood("Пицца", 0.7, 10, 5);

    public void printHero(Hero[] heroes){
        for(int i = 0; i < heroes.size; i++){
            System.out.println(i.getName() + ", с собой " + i.number_of_items() + " предметов с весом " + i.weight_of_items());
        }
    }

        System.out.println("Введите номер героя, собственность которого хотите увидеть: 1 - Стелла, 2 - Блум: ");
    Scanner in = new Scanner(System.in);
    int ord = in.nextInt();
    heroes[ord - 1].get_item(0);
    heroes[ord - 1].toString;
    heroes[ord - 1].get_item(1);
    heroes[ord - 1].toString;
    heroes[ord - 1].get_item(2);
    heroes[ord - 1].toString;


}
}
