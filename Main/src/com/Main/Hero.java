package com.Main;

import java.util.ArrayList;

public class Hero {
    private String name;
    public Hero(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    protected ArrayList<Item> items = new ArrayList<>();

    public void giveItem(String it_name, int weight, int value){
        Item new_one;
        new_one = new Item(it_name, weight, value);
        items.add(new_one);
    }

    public int number_of_items(){
        return items.size();
    }

    public int weight_of_items(){
        int total_weight = 0;
        for (Item item : items) {
            total_weight += item.weight;
        }
        return total_weight;
    }
    public void get_item(int index) {
        String item_name = items.get(index).name;
        int it_weight = items.get(index).weight;
        int it_value = items.get(index).value;
    }

    public String toString(){
        return "Вещь{" + item_name + " весом " + it_weight + " и ценностью" + it_value + "}";
    }

    public void addFood(String item_name, int it_weigh, int it_value, int satiety){
        Food yammy = new Food(item_name, it_weight, value, satiety);
        items.add(yammy);
    }
    public void addWeapon(String it_name, int weight, int value, int damage){
        Weapon achtung = new Weapon(it_name, weight, value, damage);
        items.add(achtung);
    }
}
