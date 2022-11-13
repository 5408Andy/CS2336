import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException {

        Graph graph1 = new Graph();
        Graph graph2 = new Graph();

        graph1.createGraph("graph1.txt");
        graph2.createGraph("graph2.txt");

        if (graph1.checkBipartite(2) == true) {

            System.out.println("\"graph1.txt\" is a Bipartite Graph.");

        }  
        else {

            System.out.println("\"graph1.txt\" is NOT a Bipartite Graph.");

        }

        if (graph2.checkBipartite(2) == true) {

            System.out.println("\"graph2.txt\" is a Bipartite Graph.");

        }  
        else {

            System.out.println("\"graph2.txt\" is NOT a Bipartite Graph.");

        }

       // graph1.printAdjMatrix();

    }

}
