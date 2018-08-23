package sr.unasat.smn.entities;

public class Food {

    public String name;
    public double weight;
    public Restaurant restaurant;
    public Category category;

    public Food(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
}