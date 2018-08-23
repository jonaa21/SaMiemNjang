package sr.unasat.smn.services;

import sr.unasat.smn.entities.Category;
import sr.unasat.smn.entities.Food;
import sr.unasat.smn.entities.Restaurant;

public class Vertex {

    Category category;
    Restaurant restaurant;
    Food food;

    public String name;
    public double weight;
    public boolean isInTree;
    public boolean wasVisited;

    public Vertex(String name, double weight){
        this.name = name;
        this.weight = weight;
        isInTree = false;
        wasVisited = false;
    }

    public Category getCategory() {
        return category;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Food getFood() {
        return food;
    }

    public String getName() {
        return name;
    }

}
