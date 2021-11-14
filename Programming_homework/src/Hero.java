import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Hero {
    private final String name; // финал означает, что не будет переопределенности в наследниках
    protected ArrayList<Item> array_of_items = new ArrayList<>();

    public Hero(String name) {
        this.name = name;
    }

//    public String get_name(){  // посоветовал ide
//        return name;
//    }

    void giveItem(Item new_item){
        array_of_items.add(new_item);
    }

    int total_weight_of_items(){
        int total_weight =  0;
        for (Item array_of_item : array_of_items) {
            total_weight += array_of_item.weight;
        }
        return total_weight;
    }

    int total_count_of_items(){
        return array_of_items.size();
    }

    public String toString(){
        return "герой с именем " + name + ", с собой " + total_count_of_items() + " предмета/предметов весом " + total_weight_of_items();
    }

    public void print_inventoty(){
        if (array_of_items.size() == 0) System.out.println("ничего нет");
        else {
            for (Item i : array_of_items) {
                System.out.println(i.toString());
            }
        }
    }

//    void print_special_item(int index){
//        array_of_items.get(index).info_about_special_item();
//    }
//    void give_info_about_special_item(int index){
//        String item_name = array_of_items.get(index).name;
//        int item_value = array_of_items.get(index).value;
//        int item_weight = array_of_items.get(index).weight;
//        int damage = array_of_items.get(index).damage;
//        System.out.println("Name = " + item_name + "; value = " + item_value + "; weigth = " + item_weight);
//    }

//    String give_info_about_item(int index){
//        String item_name = array_of_items.get(index).name;
//        int item_value = array_of_items.get(index).value;
//        int item_weight = array_of_items.get(index).weight;
//        return " "  "x=" + x + "; y=" + y
//    }
//

//    void create_food(int food_value, int food_weight, String item_name, int hp_regen){
//        Food f = new Food(food_value, food_weight, item_name, hp_regen);
//        array_of_items.add(f);
//    }
//
//    void create_weapon(int weapon_value, int weapon_weight, String weapon_name, int damage){
//        Weapon w = new Weapon(weapon_value, weapon_weight, weapon_name, damage);
//        array_of_items.add(w);
//    }
//
//    Item give_item_by_index(int index) {
//        return array_of_items.get(index);
//    }
}

