package Q1;

import java.util.ArrayList;

//-----------------------------------------------------
// Title: Edge class
// Author: Ceyda Kuşçuoğlu

// Description: This class implements the Edge structure
//-----------------------------------------------------
public class Edge implements Comparable<Edge> {
    private Node node1, node2;
    private final double weight;
    private ArrayList<Edge> Edges = new ArrayList<>(); // this list holds the edges in graph

    public Edge(Node v, Node w, double weight) {// creates an edge for between node1 and node2 verticies
        this.node1 = v;
        this.node2 = w;
        this.weight = weight;
    }

    public Node either() { // this method returns the node1
        return node1;
    }

    public Node other(Node vertex) { // this method returns the node2
        if (vertex == node1)
            return node2;
        else
            return node1;
    }

    public int compareTo(Edge that) {
        if (this.weight < that.weight)
            return -1;
        else if (this.weight > that.weight)
            return +1;
        else
            return 0;
    }

    public void addEdge(Edge edge) { // this method adds the edge to list
        Edges.add(edge);
    }

    public ArrayList<Edge> getEdges() { // returns the edge listF
        return Edges;
    }

    public double getWeight() {
        return weight;
    }

}
