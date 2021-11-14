public class Weapon extends  Item{
    private final int weapon_damage;

    public Weapon(int value, int weight, String name, int damage) {
        super(value, weight, name);
        weapon_damage = damage;
    }

    public String toString(){
        return super.toString() + " урон " + weapon_damage;
    }
}
