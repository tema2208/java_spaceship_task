public class Item {
    protected int value;
    protected int weight;
    protected String name;

    public Item(int value, int weight, String name) {
        this.value = value;
        this.weight = weight;
        this.name = name;
    }

    public String toString(){
        return "Имя " + name + "; стоимость " + value + "; вес " + weight;
    }
}
