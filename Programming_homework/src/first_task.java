import java.lang.invoke.SwitchPoint;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class first_task {
    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//
//        System.out.print("Input hero's name: ");
//        String name_of_hero = in.nextLine();
//
//        System.out.print("Input item's name: ");
//        String name_of_item = in.nextLine();
//
//        System.out.print("Input a value: ");
//        int value = in.nextInt();
//
//        System.out.print("Input a weight: ");
//        int weight = in.nextInt();

        Hero hero0 = new Hero("Sergei");
        Hero hero1 = new Hero("Anton");
        Hero hero2 = new Hero("Vasiliy");
        Hero[] arrayOfHeroes = new Hero[3];

        arrayOfHeroes[0] = hero0;
        arrayOfHeroes[1] = hero1;
        arrayOfHeroes[2] = hero2;

        Item item1 = create_item(1, 5, "кожаная куртка");
        Item item2 = create_item(2, 7, "кальчуга");
        Item item3 = create_item(3, 10, "латы");
        Food chicken = new Food(10, 1, "окорочек", 15);
        Weapon sword = new Weapon(50, 3, "Dragon Slayer", 100);

//        for(int i = 0; i < 3; i++){
//            System.out.println(arrayOfHeroes[i].give_name(arrayOfHeroes[i]));
//        }
//        System.out.println(hero1.total_weight_of_items());
//        System.out.println(hero1.total_count_of_items());
//        hero0.give_info_about_item(0);

        //print_info(arrayOfHeroes);
        //System.out.println(hero1.give_item(0)); - возвращает объект Item

        //hero1.giveItem(value, weight, name_of_item);

        //System.out.println("работаем с hero0");
//        hero0.create_food(5, 1,"окорочок", 15);
//        hero0.create_weapon(20, 4,"меч", 40);
//
//        hero0.give_info_about_item(0);
//        hero0.print_special_item(1);
//        hero0.print_special_item(2);
        hero0.giveItem(item1);
        hero0.giveItem(item2);
        hero0.giveItem(item3);
        hero0.giveItem(chicken);
        hero0.giveItem(sword);
        print_info(arrayOfHeroes);
    }

    static Item create_item(int value, int weight, String name){
        return new Item(value, weight, name);
    }

    static void print_info(Hero[] arrayOfHeroes) {
        for (Hero h : arrayOfHeroes) {
            System.out.println(h.toString());
            System.out.println("в инвентаре ");
            h.print_inventoty();
        }
    }

}



