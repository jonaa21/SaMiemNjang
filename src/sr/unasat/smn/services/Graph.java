package sr.unasat.smn.services;

import sr.unasat.smn.entities.Category;
import sr.unasat.smn.entities.Food;
import sr.unasat.smn.entities.Restaurant;

public class Graph {

    private final int MAX_VERTS = 50;
    private final int INFINITY = 1000000;
    private Vertex vertexList[];
    private Vertex vertex;
    private int adjMat[][]; // adjacency matrix
    private int nVerts; // current number of vertices
    private int nTree; // number of verts in tree
    private DistPar sPath[]; // array for shortest-path data
    private int currentVert; // current vertex
    private int startToCurrent; // distance to currentVert
    private Stack stack;
    private Queue queue;
    private Category category;

    public Graph() // constructor
    {
        vertexList = new Vertex[MAX_VERTS];// adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        nTree = 0;
        for(int j=0; j<MAX_VERTS; j++) // set adjacency
            for(int k=0; k<MAX_VERTS; k++) // matrix
                adjMat[j][k] = INFINITY; // to infinity
        sPath = new DistPar[MAX_VERTS]; // shortest paths
    }

    public void addEdge(int start, int end, int weight){
        adjMat[start][end] = weight; //directed
    }


    public void addCatVertex(Category cat){
        Vertex vertex = new Vertex(cat.getCatName());
        vertex.category = cat;
        vertexList[nVerts++] = vertex;
    }

    public void addRestVertex(Restaurant rest){
        Vertex vertex = new Vertex(rest.getName());
        vertex.restaurant = rest;
        vertexList[nVerts++] = vertex;
    }

    public void addFood(Food food){
        Vertex vertex = new Vertex(food.getName());
        vertex.food = food;
        vertexList[nVerts++] = vertex;
    }

    public void displayVertex(int v){
        System.out.println(vertexList[v].getName());
    }

    public void path(){

        int startTree = 0;
        vertexList[startTree].isInTree = true;
        nTree = 1;

        for (int j = 0; j < nVerts; j++){
            int tempDist = adjMat[startTree][j];
            sPath[j] = new DistPar(startTree, tempDist);
        }

        while (nTree<nVerts){
            int indexMin = getMin();
            int minDist = sPath[indexMin].distance;

            if (minDist == INFINITY){
                System.out.println("There are unreachable vertices");
                break;
            }
            else{
                currentVert = indexMin;
                startToCurrent = sPath[indexMin].distance;
            }
            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath();
        }
        displayPaths();

        nTree = 0;
        for (int j = 0; j < nVerts; j++) {
            vertexList[j].isInTree = false;
        }
    }

    public int getMin(){
        int minDist = INFINITY;
        int indexMin = 0;

        for (int j = 1; j < nVerts; j++) {
            if (!vertexList[j].isInTree && sPath[j].distance < minDist){

                minDist = sPath[j].distance;
                indexMin = j;
            }
        }
        return indexMin;
    }

    public void adjust_sPath(){
        int column = 1;

        while(column < nVerts){
            if (vertexList[column].isInTree){
                column++;
                continue;
            }
            int currentToFringe = adjMat[currentVert][column];
            int startToFringe = startToCurrent + currentToFringe;
            int sPathDist = sPath[column].distance;

            if (startToFringe < sPathDist){
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }
    }

    public void displayPaths(){
        for (int j = 0; j < nVerts; j++) {
            System.out.println(vertexList[j].getName() + " = ");
            if (sPath[j].distance == INFINITY){
                System.out.println("0");
            }else{
                System.out.println(sPath[j].distance);
                String parent = vertexList[sPath[j].parentVert].getName();
                System.out.println(" ( " + parent + " )");
            }
        }
        System.out.println(" ");
    }

    public void bfs() {                                                 // begin at vertex 0
        vertexList[0].wasVisited = true;                                // mark it
        vertexList[0].vertexIndex = 1;
        System.out.println(" ");
        Queue queue = new Queue();
        queue.insert(0);
        int v2;
        while (!queue.isEmpty()) {                                   // until queue empty,
            int v1 = queue.remove();                                 // remove vertex at head
            // until it has no unvisited neighbors
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {            // get one,
                vertexList[v2].wasVisited = true;                       // mark it
                vertexList[v2].vertexIndex = vertexList[v1].vertexIndex + 1;
                queue.insert(v2);                                    // insert it
            }                                                           // end while(unvisited neighbors)
        }                                                               // end while(queue not empty)
        for (int j = 0; j < nVerts; j++)
            vertexList[j].wasVisited = false;
    }

    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < nVerts; j++)
            if (adjMat[v][j] > 0 && vertexList[j].wasVisited == false && adjMat[v][j] < INFINITY)
                return j; // return first such vertex
        return -1; // no such vertices
    }

    public void dfs() {                                             // begin at vertex 0
        vertexList[0].wasVisited = true;                            // mark it
        displayVertex(0);                                        // display it
        System.out.println(" ");
        stack.push(0);                                        // push it
        while (!stack.isEmpty())                                 // until stack empty,
        {
            // get an unvisited vertex adjacent to stack top
            int v = getAdjUnvisitedVertex(stack.peek());
            if (v == -1)                                            // if no such vertex,
                stack.pop();                                     // pop a new one
            else                                                    // if it exists,
            {
                vertexList[v].wasVisited = true;
                displayVertex(v);                                   // display it
                stack.push(v);                                   // push it
            }
        }
        for (int j = 0; j < nVerts; j++)
            vertexList[j].wasVisited = false;
    }
}