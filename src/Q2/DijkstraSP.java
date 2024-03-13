package Q2;

import java.util.ArrayList;

//-----------------------------------------------------
// Title: DijkstraSP implementation class
// Author: Ceyda Kuşçuoğlu

// Description: This class implements the dijikstra algorithm 
//-----------------------------------------------------
public class DijkstraSP {
    private Edge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;
    ArrayList<Edge> existingPath; // current path
    int totalLength; // total length of the path

    public DijkstraSP(EdgeWeightedGraph G, Node source, Node sink, ArrayList<Node> nodesToBeVisited) {
        existingPath = new ArrayList<>();
        totalLength = 0;

        if (nodesToBeVisited.size() == 0) { // if there are no cities to be visit just apply dijikstra and print the
                                            // shortest path between source and sink
            Dijkstra(G, source.getIndex());
            addPath(existingPath, source.getIndex(), sink.getIndex());
            printExistingPath();
        } else {
            if (nodesToBeVisited.size() == 1) { // if there are 1 city to visit find shortest path to it from source add
                                                // to path and then find the shortest path to sink from it , then print
                                                // the path
                Dijkstra(G, source.getIndex());
                addPath(existingPath, source.getIndex(), nodesToBeVisited.get(0).getIndex());
                Dijkstra(G, nodesToBeVisited.get(0).getIndex());
                addPath(existingPath, nodesToBeVisited.get(0).getIndex(), sink.getIndex());
                printExistingPath();
            } else { // if there are more than 1 repeat the process till the sink vertex
                for (int i = 1; i < nodesToBeVisited.size(); i++) {
                    Dijkstra(G, nodesToBeVisited.get(i - 1).getIndex());
                    addPath(existingPath, nodesToBeVisited.get(i - 1).getIndex(), nodesToBeVisited.get(i).getIndex());
                }
                Dijkstra(G, nodesToBeVisited.get(nodesToBeVisited.size() - 1).getIndex());
                addPath(existingPath, nodesToBeVisited.get(nodesToBeVisited.size() - 1).getIndex(), sink.getIndex());
                printExistingPath();
            }

        }
    }

    public void Dijkstra(EdgeWeightedGraph G, int s) { // implementation of dijikstra algorithm
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : G.adj(v))
                relax(e);
        }
    }

    private void relax(Edge e) { // relax operation implementation
        int v = e.either().getIndex(), w = e.other(e.either()).getIndex();
        if (distTo[w] > distTo[v] + e.getWeight()) {
            distTo[w] = distTo[v] + e.getWeight();
            edgeTo[w] = e;
            if (pq.contains(w))
                pq.decreaseKey(w, distTo[w]);
            else
                pq.insert(w, distTo[w]);
        }

    }

    public void addPath(ArrayList<Edge> existingPath, int start, int end) {
        // adds the path to current path
        Stack<Edge> path = new Stack<>();
        for (Edge e = edgeTo[end]; e != null; e = edgeTo[e.either().getIndex()]) {
            path.push(e);
        }

        while (!path.isEmpty()) {
            existingPath.add(path.pop());
        }
        totalLength += distTo[end];
    }

    public void printExistingPath() {
        // prints the existing current path
        if (existingPath.isEmpty()) {
            System.out.println("The existing path is empty.");
            return;
        }

        System.out.println("Routes are: ");
        System.out.print(existingPath.get(0).either().getData()); // Print the first vertex
        for (Edge e : existingPath) {
            System.out.print("-" + e.other(e.either()).getData());
        }

        // Print the length of the route
        System.out.println();
        System.out.println("Length of route is: " + totalLength);
    }

}
