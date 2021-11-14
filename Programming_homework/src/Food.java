public class Food extends Item{
    private final int food_hp_regen;

    public Food(int value, int weight, String name, int hp_regen) {
        super(value, weight, name);
        food_hp_regen = hp_regen;
    }

    public String toString(){
        return super.toString() + " восстанавливает здоровья" + food_hp_regen;
    }

//
}
