////////////////////////////////////////////////////////////////
class Edge
{
    int index1, index2;

    /// To complete
    Edge(int i1, int i2){
        index1 = i1;
        index2 = i2;
    }
 
}  // end class Edge


////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////


class Vertex
{
   /// To complete
    boolean visited;
    int i, j, data;

    Vertex(int x, int y, int data){
        this.data = data;
        i = x;
        j = y;
        visited = false;
    }
    
}  // end class Vertex


////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////


class Graph
{
    private Vertex nodes[]; // list of vertices
    private int adjMat[][];      // adjacency matrix
    private int nVertex;// Number of vertices/nodes
    protected int weightTotal;
  

   
    ////////////// To Complete
    Graph(int nx, int ny){
        nVertex = nx*ny;
        nodes = new Vertex[nVertex];
        adjMat = new int[nVertex][nVertex];
        for(int i = 0; i< ny; i++) {
            for (int j = 0; j < nx; j++) {
                nodes[(i*nx)+j] = new Vertex(j,i,(i*nx)+j);
                adjMat[i][j] = 0;
            }

        }
        /*
        int xCount = 0;
        int yCount = 0;
        int label = 0;
        while(label < nVertex){
            nodes[label] = new Vertex(xCount, yCount, label);
            if(yCount < ny){
                yCount++;
            }
            else if(yCount >= ny){
                yCount = 0;
                xCount++;
            }
            label++;
        }
        */
    }

    public void addEdge(int v1, int v2) {
        adjMat[v1][v2] = 1;
        adjMat[v2][v1] = 1;
    }

    public double distance(int x1, int x2, int y1, int y2){
        Double result = Math.sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)));
        return result;
    }

    public void form2DGrid(){
        for (int i = 0; i < nodes.length; i++) {
            for (int j = i+1; j < nodes.length; j++) {
                if(distance(nodes[i].i, nodes[j].i, nodes[i].j, nodes[j].j) == 1){
                    addEdge(i,j);
                    weightTotal++;
                }

            }

        }
    }

    public void displayInfoGraph(){
        System.out.println("List of edges + weight:");
        for (int i = 0; i < nodes.length; i++) {
            for (int j = i+1; j < nodes.length; j++) {
                if(adjMat[i][j] == 1) {
                    System.out.println("(" + nodes[i].i + "," + nodes[i].j + ") <--> (" + nodes[i].i + "," + nodes[i].j + ") " + adjMat[i][j]);
                }
            }
        }
        System.out.println("Total weight: " + weightTotal);
    }

    public int getnVertex(){
        return nVertex;
    }

    public void displayAdjMat(){
        for (int i = 0; i < adjMat.length; i++) {
            for (int j = 0; j < adjMat.length; j++) {
                System.out.print(adjMat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Graph dfs(int label) {
        Stack theStack = new Stack(nVertex);
        Graph grph = this;
        nodes[label].visited = true;
        //setAdjMat(label, label, 1);
        theStack.push(label);
        while (!theStack.isEmpty()) {
            int currentNode = (int) theStack.peek();
            int v = getAdjUnvisitedNode(currentNode);
            if (v == -1) // no such node
                theStack.pop();
            else {
                nodes[v].visited = true;
                //setAdjMat(v,v, 1);
                theStack.push(v);
                grph.addEdge(currentNode, v);
            }
        }
            // stack is empty so we can reset the flags
            for (int i = 0; i < nVertex; i++) nodes[i].visited = false;
            return grph;
        }

    public int getAdjUnvisitedNode(int v){
        for(int i=0; i<getnVertex();i++)
            if (adjMat[v][i]==1 && nodes[i].visited==false)
                return i; // found neighbor
        return -1; // no such node
    }

    protected void setAdjMat(int x, int y, int data){
        adjMat[x][y] = data;
    }








    

    //// Plot the Graph using the StdDraw.java library
    public void plot(String color){

        if (color.equals("BLUE"))
            StdDraw.setPenColor(StdDraw.BLUE);  // change pen color
        else if (color.equals("GRAY"))
            StdDraw.setPenColor(StdDraw.GRAY);  // change pen color
        else if (color.equals("RED"))
            StdDraw.setPenColor(StdDraw.RED);  // change pen color

        for (int i=0;i<nVertex;i++)
            for (int j=0;j<=i;j++)
                if(adjMat[i][j]!=0){
                    StdDraw.setPenRadius(adjMat[i][j]*adjMat[i][j]*0.0025);
                    StdDraw.filledCircle(nodes[i].i,nodes[i].j,0.25);  // plot node
                    StdDraw.filledCircle(nodes[j].i,nodes[j].j,0.25);  // plot node
                    StdDraw.line(nodes[i].i,nodes[i].j,nodes[j].i,nodes[j].j); //plot edges
                }

    }


    

}  // end class Graph

