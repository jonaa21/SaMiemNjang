package sr.unasat.smn.app;

import sr.unasat.smn.entities.Category;
import sr.unasat.smn.entities.Food;
import sr.unasat.smn.entities.Restaurant;
import sr.unasat.smn.services.Graph;

public class App {

    public static void main(String[] args) {

        Graph theGraph = new Graph();

        //Categories
        theGraph.addCatVertex(new Category("Street Food",5)); //0
        theGraph.addCatVertex(new Category("Fast Food", 4)); //1
        theGraph.addCatVertex(new Category("Dining In", 3)); //2
        theGraph.addCatVertex(new Category("Take out", 1)); //3

        //Restaurants
        theGraph.addRestVertex("Fast Food", new Restaurant("KFC", 20)); //4
        theGraph.addRestVertex("Fast Food", new Restaurant("Popeyes", 18)); //5
        theGraph.addRestVertex("Street Food",new Restaurant("Vieze Pata", 15)); //6
        theGraph.addRestVertex("Take out",new Restaurant("Chi Min", 17)); //7
        theGraph.addRestVertex("Dining In",new Restaurant("Mariott", 25)); //8

        //Food
        /*theGraph.addFood("Chi Min",new Food("tjauwmin", 25)); //9
        theGraph.addFood("KFC",new Food("Burgers", 35)); //10
        theGraph.addFood(new Food("Fries", 15)); //11
        theGraph.addFood(new Food("2pc Chicken", 20)); //12
        theGraph.addFood(new Food("Picanha", 120)); //13

        //Start to Category
        theGraph.addEdge(0, 1); //Start - Fast Food
        theGraph.addEdge(0, 2); //Start -  Dining In
        theGraph.addEdge(0, 3); //Start - Take out

        //Cat to Restaurant
        theGraph.addEdge(1, 4); //Fast Food - KFC
        theGraph.addEdge(1, 5); //Fast Food - Popeyes
        theGraph.addEdge(1, 6); //Fast Food - Maccie
        theGraph.addEdge(2, 8); //Dining In - Mariott
        theGraph.addEdge(3, 7); //Take out - Chi Min

        //Rest to Food
        theGraph.addEdge(5, 12); //KFC - 2pc
        theGraph.addEdge(6, 10); //Maccie - Burgers
        theGraph.addEdge(7, 9); //Chi Min - tjauwmin
        theGraph.addEdge(8, 13); //Mariott - Picanha
        theGraph.addEdge(4, 11); //KFC - Fries
        theGraph.addEdge(6, 11); //Maccie - Fries
        theGraph.addEdge(5, 12); //Popeyes - 2pc
*/
        //theGraph.shortestPath(); //Shortest Path

        //theGraph.bfs(3); //BFS

        //theGraph.shortestPath("Burger", 35);
        //theGraph.priceCheck(130);
    }

    // TODO: getMax
    // TODO: longestPath
    // TODO: getPrice per foodItem
    // TODO: get list rest per cat
}
