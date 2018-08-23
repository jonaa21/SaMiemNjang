package sr.unasat.smn.services;

import sr.unasat.smn.entities.Category;
import sr.unasat.smn.entities.Food;
import sr.unasat.smn.entities.Restaurant;

import java.util.ArrayList;

public class Graph {

    private final int MAX_VERTS = 50;
    private final int INFINITY = 1000000;
    private Vertex vertexList[];
    private double adjMat[][]; // adjacency matrix
    private int nVerts; // current number of vertices
    private int nTree; // number of verts in tree
    private DistPar sPath[]; // array for shortest-shortestPath data
    private int currentVert; // current vertex
    private double startToCurrent; // weight to currentVert
    private Stack stack;
    private Queue queue;
    private Category category;
    private Restaurant restaurant;
    private ArrayList visits = new ArrayList();

    public Graph() // constructor
    {
        vertexList = new Vertex[MAX_VERTS];// adjacency matrix
        adjMat = new double[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        nTree = 0;
        for (int j = 0; j < MAX_VERTS; j++) // set adjacency
            for (int k = 0; k < MAX_VERTS; k++) // matrix
                adjMat[j][k] = INFINITY; // to infinity
        sPath = new DistPar[MAX_VERTS]; // shortest paths
        stack = new Stack();
        queue = new Queue();
        vertexList[nVerts++] = new Vertex("Customer", 0);
    }

    public void addEdge(int start, int end) {
        if (nVerts <= 0) {
            System.out.println("No vertices connected");
        } else {
            adjMat[start][end] = vertexList[nVerts - 1].weight; //directed
        }
    }


    public void addCatVertex(Category cat) {
        Vertex vertex = new Vertex(cat.catName, cat.weight);
        vertex.category = cat;
        vertexList[nVerts++] = vertex;
        addEdge(0, nVerts);
        //adjMat[0][nVerts] = cat.weight;
    }

    public int addRestVertex(String catName, Restaurant rest) {

        vertexList[0].wasVisited = true;
        displayVertex(0);
        stack.push(0);

        int foundItem = -1;

        int v = getAdjUnvisitedVertex(stack.peek());
        if (!stack.isEmpty()) {

            if (v == -1) {
                stack.pop();
                visits.clear();
            } else {

                vertexList[v].wasVisited = true; // mark it
                displayVertex(v); // display it
                stack.push(v); // push it
                visits.add(vertexList[v].category.catName);

                for (int i = 0; i < visits.size(); i++) {
                    if (CompareTo.execute(visits.get(i).toString(), catName) == 0) {
                        foundItem = v;
                    } else {
                        break;
                    }
                }
                // if the category not found, exit out of the loop

                Vertex vertex = new Vertex(rest.name, rest.weight);
                vertex.restaurant = rest;

                vertexList[foundItem].category.catName = catName;
                rest.cat = vertexList[foundItem].category;              //get found category and apply it to restaurant
                vertex.category = rest.cat;                             //add restaurant cat to found category vertex

                vertexList[nVerts++] = vertex;
                addEdge(foundItem, nVerts);
                //System.out.println(rest.name + " is connected to " + rest.cat.catName);
            }
        }
        for (int i = 0; i < nVerts; i++){
            vertexList[i].wasVisited=false;
        }
        return foundItem;
    }

    public void addFood(String restName, Food food) {
        vertexList[bfs(2)].wasVisited = true;
        displayVertex(bfs(2));
        stack.push(bfs(2));

        int foundItem = -1;

        if (!stack.isEmpty()) {

            int v = getAdjUnvisitedVertex(stack.peek());
            if (v == -1) {
                stack.pop();
            } else {

                vertexList[v].wasVisited = true; // mark it
                displayVertex(v); // display it
                stack.push(v); // push it

                // if the category not found, exit out of the loop
                if (CompareTo.execute(vertexList[v].restaurant.name, restName) != 0) {
                    System.out.println(restName + " not found in Restaurant List");
                } else {
                    foundItem = v;

                    Vertex vertex = new Vertex(food.name, food.weight);
                    vertex.food = food;

                    vertexList[foundItem].restaurant.name = restName;
                    food.restaurant = vertexList[foundItem].restaurant;              //get found category and apply it to restaurant
                    vertex.restaurant = food.restaurant;                             //add restaurant cat to found category vertex

                    vertexList[nVerts++] = vertex;
                    addEdge(foundItem, nVerts);
                    System.out.println(food.name + " is connected to " + food.restaurant.name);
                }
            }
        }
    }

    public void displayVertex(int v) {
        System.out.println(vertexList[v].getName());
    }

    public void shortestPath(String foodName, double price) {

        int startTree = 0;
        vertexList[startTree].isInTree = true;
        nTree = 1;

        for (int j = 0; j < nVerts; j++) {
            double tempDist = adjMat[startTree][j];
            sPath[j] = new DistPar(startTree, tempDist);
        }

        while (nTree < nVerts) {
            int indexMin = getMin();
            double minDist = sPath[indexMin].weight;

            if (minDist == INFINITY) {
                System.out.println("There are unreachable vertices");
                break;
            } else {
                currentVert = indexMin;
                startToCurrent = sPath[indexMin].weight;
            }
            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath();
        }
        displayPaths(foodName, price);

        nTree = 0;
        for (int j = 0; j < nVerts; j++) {
            vertexList[j].isInTree = false;
        }
    }

    public int getMin() // get entry from sPath
    { // with minimum weight
        double minDist = INFINITY; // assume minimum
        int indexMin = 0;
        for (int j = 1; j < nVerts; j++) // for each vertex,
        { // if it's in tree and
            if (!vertexList[j].isInTree && // smaller than old one
                    sPath[j].weight < minDist) {
                minDist = sPath[j].weight;
                indexMin = j; // update minimum
            }
        } // end for
        return indexMin; // return index of minimum
    } // end getMin()

    public void adjust_sPath() {
        int column = 1;

        while (column < nVerts) {
            if (vertexList[column].isInTree) {
                column++;
                continue;
            }
            double currentToFringe = adjMat[currentVert][column];
            double startToFringe = startToCurrent + currentToFringe;
            double sPathDist = sPath[column].weight;

            if (startToFringe < sPathDist) {
                sPath[column].parentVert = currentVert;
                sPath[column].weight = startToFringe;
            }
            column++;
        }
    }

    public void displayPaths(String foodName, double price) {
        for (int j = 0; j < nVerts; j++) {
            String parent = vertexList[sPath[j].parentVert].name;

            if (CompareTo.execute(foodName, parent) == 0) {
                System.out.println("food found");
                break;
            } else {
                System.out.println(vertexList[j].name + " = ");
                if (sPath[j].weight == INFINITY) {
                    System.out.println("Food not found");
                } else {
                    System.out.println(sPath[j].weight);
                }
                System.out.println("(" + parent + ") ");

                // if the amount is under the next weight
                if (price >= sPath[j].weight && price < sPath[j + 1].weight) {
                    System.out.println("---> Arrived till here <---");
                }
            }
        }
        System.out.println(" ");
    }

    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < nVerts; j++) {
            if (adjMat[v][j] > 0 && vertexList[j].wasVisited == false && adjMat[v][j] < INFINITY) {
                return j;
            }
            if (!vertexList[j].wasVisited) {
                vertexList[j].wasVisited = true;
            }
        }
        return -1;
    }


    public void dfs() {                                             // begin at vertex 0
        vertexList[0].wasVisited = true;                            // mark it
        displayVertex(0);                                        // display it
        stack.push(0);                                        // push it

        while (!stack.isEmpty()){                                 // until stack empty,
            // get an unvisited vertex adjacent to stack top
            int v = getAdjUnvisitedVertex(stack.peek());
            if(v == -1){
                stack.pop();
            }
            else{                                                    // if it exists,
                vertexList[v].wasVisited = true;                //mark it
                displayVertex(v);                                   // display it
                stack.push(v);                                  // push it
            }
        }
        for (int j = 0; j < nVerts; j++) {
            vertexList[j].wasVisited = false;
        }
    }

    public double priceCheck(int price){

        bfs(1);
        Stack stack = new Stack();
        int i;
        for(i = 0; i < nVerts; i++){
                stack.push(i);
                }
        if (!stack.isEmpty()){
            i = stack.pop();
            if(price == vertexList[i].food.weight){
                System.out.println("De prijs van dit gerecht bedraagt SRD: " + vertexList[i].food.weight);
            }
        }
        return vertexList[i].food.weight;
    }

    public int bfs(int start) // breadth-first search
    { // begin at vertex 0
        vertexList[start].wasVisited = true; // mark it
        displayVertex(start); // display it
        queue.insert(start); // insert at tail
        int v2;
        while (!queue.isEmpty()) // until queue empty,
        {
            int v1 = queue.remove(); // remove vertex at head
            // until it has no unvisited neighbors
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) { // get one,
                vertexList[v2].wasVisited = true; // mark it
                displayVertex(v2); // display it
                queue.insert(v2); // insert it
            } // end while
        } // end while(queue not empty)
        // queue is empty, so we’re done
        for (int j = 0; j < nVerts; j++) // reset flags
            vertexList[j].wasVisited = false;
        return start;
    }
}
