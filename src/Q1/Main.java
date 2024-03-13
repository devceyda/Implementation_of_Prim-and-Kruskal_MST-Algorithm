package Q1;

import java.io.File;
import java.util.Scanner;

//-----------------------------------------------------
// Title: Main class
// Author: Ceyda Kuşçuoğlu

// Description: This class runs the main method
//-----------------------------------------------------
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();// Take file name from user
        File file = new File(fileName);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(file);
        LazyPrimMST prim = new LazyPrimMST(graph); // finds the mst using prim algorithm
        prim.printMST();
    }
}
