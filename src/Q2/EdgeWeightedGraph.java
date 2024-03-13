package Q2;

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
    private ArrayList<Edge> Edges; // this list holds the edges

    public EdgeWeightedGraph(File file) {
        int index = 0;
        Edges = new ArrayList<>();

        verticies = new ArrayList<>(); // The list that holds the verticies in graph

        try (FileReader reader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {

                String[] cities = line.split(",");// It splits the line with "," and takes the items in array

                if (!contains(cities[0], verticies)) { // If verticies list not contains the vertex it creates node and
                                                       // adds to the vertex list
                    node = new Node(cities[0], index);
                    index++;
                    verticies.add(node);
                }
                if (!contains(cities[1], verticies)) { // If verticies list not contains the vertex it creates node and
                                                       // adds to the vertex list
                    node = new Node(cities[1], index);
                    index++;
                    verticies.add(node);
                }
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.V = verticies.size();

        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }

        try (FileReader reader = new FileReader(file)) {
            BufferedReader bufferedReader2 = new BufferedReader(reader);
            String newLine;

            while ((newLine = bufferedReader2.readLine()) != null) {
                // This loop adds the edge between veriticies
                String[] cities = newLine.split(",");
                String a = cities[0];
                String b = cities[1];
                int distance = Integer.parseInt(cities[2]);
                edge = new Edge(getNodeByIndex(getIndex(a)), getNodeByIndex(getIndex(b)), distance); // creates the edge
                addEdge(edge);// adds to the graph
                Edges.add(edge); // adds to the list
            }
            bufferedReader2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Node> getVerticies() {
        return verticies;
    }

    public int getIndex(String data) {
        // This method takes the data of node and find returns the index of that node
        for (int i = 0; i < verticies.size(); i++) {
            Node n = verticies.get(i);
            if (n.getData().equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public Node getNodeByIndex(int searchIndex) { // this method returns the node that has given index
        for (Node node : verticies) {
            if (node.getIndex() == searchIndex) {
                return node;
            }
        }
        return null;
    }

    public boolean contains(String data, ArrayList<Node> list) {
        // This method returns if given list contains the wanted node
        Node node = new Node(data);
        for (Node n : list) {
            if (n.getData().equals(node.getData())) {
                return true;
            }
        }
        return false;
    }

    public void addEdge(Edge e) {
        // adds edge between verticies
        // it changes the positions of the nodes while adding to them
        int v = e.either().getIndex(), w = e.other(e.either()).getIndex();
        Edge e2 = new Edge(e.other(e.either()), e.either(), e.getWeight());
        adj[v].add(e);
        adj[w].add(e2);
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

    public ArrayList<Edge> edges() {
        return Edges;
    }

}

class Node {

    // This class implements the node structure

    private String data;
    private int index;

    public Node() {

    }

    public Node(String data, int index) {
        this.data = data;
        this.index = index;
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

    @Override
    public String toString() {
        return data;
    }

}