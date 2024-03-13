package Q1;

import java.util.Locale;

//-----------------------------------------------------
// Title: LazyPrimMST class
// Author: Ceyda Kuşçuoğlu

// Description: This class implements the prim algorithm
//-----------------------------------------------------

public class LazyPrimMST {
    private boolean[] marked; // MST vertices
    private MinPQ<Edge> mst; // MST edges
    private MinPQ<Edge> pq; // PQ of edges

    public LazyPrimMST(EdgeWeightedGraph G) { // implementation of the prim algorithm
        pq = new MinPQ<Edge>();
        mst = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        visit(G, 0);

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either().getIndex(), w = e.other(e.either()).getIndex();
            if (marked[v] && marked[w])
                continue;
            mst.insert(e);
            if (!marked[v])
                visit(G, v);
            if (!marked[w])
                visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(e.either()).getIndex()])
                pq.insert(e);
    }

    public Iterable<Edge> mst() {
        return (Iterable<Edge>) mst;
    }

    public void printMST() { // prints the minimum spanning tree
        String s;
        System.out.println("Paths are:");
        System.out.println();
        for (Edge e : mst) {
            s = String.format(Locale.US, "%.1f", e.getWeight()); // formatting the weight of the edge
            System.out.println(
                    e.either().getData() + "-" + e.other(e.either()).getData() + ": " + s);
            System.out.println();
        }
    }
}