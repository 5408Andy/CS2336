import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException {

        Graph graph1 = new Graph();

        graph1.createGraph("graph1.txt");

        graph1.printAdjMatrix();

    }

}
