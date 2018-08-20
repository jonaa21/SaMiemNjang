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
        theGraph.addCatVertex(new Category("Fast Food")); //1
        theGraph.addCatVertex(new Category("Dining In")); //2
        theGraph.addCatVertex(new Category("Take out")); //3

        //Restaurants
        theGraph.addRestVertex(new Restaurant("KFC")); //4
        theGraph.addRestVertex(new Restaurant("Popeyes")); //5
        theGraph.addRestVertex(new Restaurant("Maccie")); //6
        theGraph.addRestVertex(new Restaurant("Chi Min")); //7
        theGraph.addRestVertex(new Restaurant("Mariott")); //8

        //Food
        theGraph.addFood(new Food("tjauwmin", 25)); //9
        theGraph.addFood(new Food("Burgers", 35)); //10
        theGraph.addFood(new Food("Fries", 15)); //11
        theGraph.addFood(new Food("2pc Chicken", 20)); //12
        theGraph.addFood(new Food("Picanha", 20)); //13

        //Start to Category
        theGraph.addEdge(0, 1, 35); //Start - Fast Food
        theGraph.addEdge(0, 2, 50); //Start -  Dining In
        theGraph.addEdge(0, 3, 30); //Start - Take out

        //Cat to Restaurant
        theGraph.addEdge(1, 4, 30); //Fast Food - KFC
        theGraph.addEdge(1, 5, 35); //Fast Food - Popeyes
        theGraph.addEdge(1, 6, 30); //Fast Food - Maccie
        theGraph.addEdge(2, 8, 70); //Dining In - Mariott
        theGraph.addEdge(3, 7, 50); //Take out - Chi Min

        //Rest to Food
        theGraph.addEdge(5, 12, 30); //KFC - 2pc
        theGraph.addEdge(6, 10, 25); //Maccie - Burgers
        theGraph.addEdge(7, 9, 30); //Chi Min - tjauwmin
        theGraph.addEdge(8, 13, 150); //Mariott - Picanha
        theGraph.addEdge(5, 11, 20); //KFC - Fries
        theGraph.addEdge(6, 11, 18); //Maccie - Fries
        theGraph.addEdge(5, 12, 15); //Popeyes - 2pc

        //theGraph.path(); //Shortest Path

        //theGraph.bfs(0); //BFS

        theGraph.priceCheck(30);
    }
}
