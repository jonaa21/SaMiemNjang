package sr.unasat.smn.services;

import sr.unasat.smn.entities.Category;
import sr.unasat.smn.entities.Food;
import sr.unasat.smn.entities.Restaurant;

public class Vertex {

    Category category;
    Restaurant restaurant;
    Food food;

    public String name;
    public boolean isInTree;
    public boolean wasVisited;
    public int vertexIndex;

    public Vertex(String name){
        this.name = name;
        isInTree = false;
        wasVisited = false;
        vertexIndex = 0;
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
