package spiderman;

import java.util.*;

public class Graph {
    private ArrayList<LinkedList<DimensionNode>> adjacencyList;

    public Graph() {
        this.adjacencyList = new ArrayList<>();
    }

    private void makeA(ArrayList<LinkedList<Integer>> a){
        adjacencyList = a;
    }

    // Add a vertex to the graph
    public void addVertex(int vertex) {
        while (adjacencyList.size() <= vertex) {
            adjacencyList.add(new LinkedList<>());
        }
    }

    // Add an edge between two vertices
    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);
        
        adjacencyList.get(source).add(destination);
        // For undirected graph, uncomment the line below
        // adjacencyList.get(destination).add(source);
    }

    // Get neighbors of a vertex
    public List<Integer> getNeighbors(int vertex) {
        return adjacencyList.get(vertex);
    }

    // Print the graph
    public void printGraph() {
        for (int i = 0; i < adjacencyList.size(); i++) {
            LinkedList<Integer> neighbors = adjacencyList.get(i);
            System.out.print(i + " -> ");
            for (Integer neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}