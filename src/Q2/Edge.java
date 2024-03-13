package Q2;
//-----------------------------------------------------

// Title: Edge class
// Author: Ceyda Kuşçuoğlu

// Description: This class implements the Edge structure
//-----------------------------------------------------

public class Edge implements Comparable<Edge> {
    private Node node1, node2;
    private final int weight;

    public Edge(Node v, Node w, int weight) {// creates an edge for between v and w verticies
        this.node1 = v;
        this.node2 = w;
        this.weight = weight;
    }

    public Node either() { // this method returns the v
        return node1;
    }

    public Node other(Node vertex) { // this method returns the w
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

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return (node1.getData() + "-" + node2.getData() + ":" + weight + ", ");
    }

}
