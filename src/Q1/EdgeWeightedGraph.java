package Q1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//-----------------------------------------------------
// Title: EdgeWeightedGraph class
// Author: Ceyda Kuşçuoğlu

// Description: This class implements the edge weighted graph structure
//-----------------------------------------------------

public class EdgeWeightedGraph {
    private final int V;
    private final Bag<Edge>[] adj;
    private ArrayList<Node> verticies; // this list holds the verticies
    private Node node;
    private Edge edge;

    public EdgeWeightedGraph(File file) { // this api reads the graph from given file
        int index = 0;

        verticies = new ArrayList<>(); // The list that holds the verticies in graph

        try (FileReader reader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] elements = line.split(",");// taking the items in line

                String vertexData = elements[0];
                int coordinate1 = Integer.parseInt(elements[1]);
                int coordinate2 = Integer.parseInt(elements[2]);
                node = new Node(vertexData, index, coordinate1, coordinate2);// creates the vertex with given values
                verticies.add(node);
                index++;
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.V = verticies.size();

        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Edge>();

        for (int i = 0; i < adj.length; i++) { // adds edges to graph
            for (int j = i + 1; j < adj.length; j++) {
                edge = new Edge(verticies.get(i), verticies.get(j),
                        calculateDistance(verticies.get(i), verticies.get(j)));// creates the edge
                if (edge.getEdges().contains(edge)) {

                } else {// if edge does not exist,then add to graph and list
                    addEdge(edge);
                    edge.addEdge(edge);
                }

            }
        }

    }

    public double calculateDistance(Node node1, Node node2) {// calculates the distance for edge with using given
                                                             // coordinates and formula
        double deltaX = node1.getCoordinate1() - node2.getCoordinate1();
        double deltaY = node1.getCoordinate2() - node2.getCoordinate2();

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public void addEdge(Edge e) { // adds edge between verticies
        int v = e.either().getIndex(), w = e.other(e.either()).getIndex();
        adj[v].add(e);
        adj[w].add(e);
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

}

class Node {

    // This class implements the node structure

    private String data;
    private int index;
    private int coordinate1;
    private int coordinate2;

    public Node() {

    }

    public Node(String data, int index, int coordinate1, int coordinate2) {
        this.data = data;
        this.index = index;
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
    }

    public Node(String data) {
        this.data = data;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getData() {
        return data;
    }

    public int getCoordinate1() {
        return coordinate1;
    }

    public int getCoordinate2() {
        return coordinate2;
    }

    @Override
    public String toString() {
        return data;
    }

}