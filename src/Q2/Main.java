package Q2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//-----------------------------------------------------
// Title: Main class
// Author: Ceyda Kuşçuoğlu

// Description: This class runs the main method
//-----------------------------------------------------
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        String fileName = scan.next(); // taking file name from user
        File file = new File(fileName);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(file);

        String nameOfTheSourceCity = scan.next(); // takes name of the source city
        int indexOfTheSourceCity = graph.getIndex(nameOfTheSourceCity); // finds the index of node with given data
        Node source = graph.getNodeByIndex(indexOfTheSourceCity); // finds the vertex with given index

        String nameOfTheDestinationCity = scan.next(); // takes name of the destination city
        int indexOfTheDestinationCity = graph.getIndex(nameOfTheDestinationCity); // finds the index of node with given
                                                                                  // data
        Node end = graph.getNodeByIndex(indexOfTheDestinationCity); // finds the vertex with given index

        int numberOfTheCityToVisit = scan.nextInt(); // takes the number of city for visit
        ArrayList<Node> visit = new ArrayList<>();
        if (numberOfTheCityToVisit > 0) { // if there are cities to visit take them and add to the list
            for (int i = 0; i < numberOfTheCityToVisit; i++) {
                String visiting = scan.next();
                int index = graph.getIndex(visiting);
                Node vis = graph.getNodeByIndex(index);
                visit.add(vis);

            }
        }
        DijkstraSP d = new DijkstraSP(graph, source, end, visit); // runs the algorithm

    }
}
