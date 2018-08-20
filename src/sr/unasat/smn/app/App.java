package sr.unasat.smn.app;

import sr.unasat.smn.entities.Category;
import sr.unasat.smn.entities.Food;
import sr.unasat.smn.entities.Restaurant;
import sr.unasat.smn.services.Graph;

public class App {

    public static void main(String[] args) {

        Graph theGraph = new Graph();

        //Categories
        theGraph.addCatVertex(new Category("Start")); //0
        theGraph.addCatVertex(new Category("Street Food"));  //1
        theGraph.addCatVertex(new Category("Fast Food")); //2
        theGraph.addCatVertex(new Category("Dining In")); //3
        theGraph.addCatVertex(new Category("Take out")); //4

        //Restaurants
        theGraph.addRestVertex(new Restaurant("KFC")); //5
        theGraph.addRestVertex(new Restaurant("Popeyes")); //6
        theGraph.addRestVertex(new Restaurant("Maccie")); //7
        theGraph.addRestVertex(new Restaurant("Chi Min")); //8

        //Food
        theGraph.addFood(new Food("tjauwmin")); //9
        theGraph.addFood(new Food("Burgers")); //10
        theGraph.addFood(new Food("Fries")); //11
        theGraph.addFood(new Food("Nuggets")); //12

        //Start to Category
        theGraph.addEdge(0, 1, 30);
        theGraph.addEdge(0, 2, 20);
        theGraph.addEdge(0, 3, 50);
        theGraph.addEdge(0, 4, 15);

        //Cat to Restaurant
        theGraph.addEdge(2, 6, 40);
        theGraph.addEdge(2, 5, 35);
        theGraph.addEdge(2, 7, 30);
        theGraph.addEdge(4, 8, 20);

        //Rest to Food
        theGraph.addEdge(5, 10, 30);
        theGraph.addEdge(6, 10, 25);
        theGraph.addEdge(7, 10, 30);
        theGraph.addEdge(8, 9, 20);
        theGraph.addEdge(5, 11, 20);
        theGraph.addEdge(6, 11, 18);
        theGraph.addEdge(7, 11, 15);
        theGraph.addEdge(5, 12, 15);

        System.out.println("Shortest Path");
        theGraph.path();
        System.out.println();

        theGraph.displayVertex(8);
    }
}
