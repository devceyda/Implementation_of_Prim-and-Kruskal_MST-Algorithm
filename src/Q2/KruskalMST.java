package Q2;

public class KruskalMST {
    private MinPQ<Edge> mst = new MinPQ<Edge>();

    public KruskalMST(EdgeWeightedGraph G) {
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge edge : G.edges()) {
            pq.insert(edge);
        }
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either().getIndex(), w = e.other(e.either()).getIndex();
            if (uf.find(v) != uf.find(w)) {
                uf.union(v, w);
                mst.insert(e);
            }
        }
    }


    public void printMST() {
        System.out.println("Paths are:");
        System.out.println();
        for (Edge e : mst) {

            System.out.println(
                    e.either().getData() + "-" + e.other(e.either()).getData() + ": " + e.getWeight());
            System.out.println();
        }
    }
}
