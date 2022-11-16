// File Input
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// Queues and LinkedList
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    // max size of adjacency matrix
    private int maxSize; 

    // current size 
    private int currentSize;

    // color of vertices
    private int[] colorOfVertices;

    // adjacency matrix
    int[][] adjMatrix;

    Graph() {

        maxSize = 10;

        adjMatrix = new int[maxSize][maxSize];

    } // Constructor - Default

    Graph(int receivedSize) {

        maxSize = receivedSize;

        adjMatrix = new int[maxSize][maxSize];

    } // Constructor - Overloaded

    public boolean isEmpty() {

        boolean isItEmpty = false;

        if (currentSize == 0) { // checks if the current size is equal to zero

            isItEmpty = true; // the adjacency matrix is indeed empty, so return true

        }

        return isItEmpty;

    }

    public void createGraph(String fileName) throws IOException {

        // file scanning and processing
        File inputFile = new File(fileName); 
        FileInputStream inputFileStream = new FileInputStream(inputFile);
        Scanner scanFileLine = new Scanner(inputFile);

        if (inputFile.exists() == true) { // makes sure the input file actually exists
            
            currentSize = Integer.parseInt(scanFileLine.nextLine()); // scan the first line of the file and parse the int to determine the size of adjancency matrix

            while (scanFileLine.hasNextLine() == true) { // keeps scanning file until it reaches the end of the file

                String pointValue = "";

                String fileLine = scanFileLine.nextLine(); // reads in a line from the file

                int adjMatrixIndex = Integer.parseInt(String.valueOf(fileLine.charAt(0))); // gets the first digit
       
                for (int fileLineIndex = fileLine.indexOf(" ") + 1; fileLineIndex < fileLine.length(); fileLineIndex++) { // loops through the file line after the first digit
                    
                    if (Character.isDigit(fileLine.charAt(fileLineIndex)) == true) { // parses out number from file
                        
                        pointValue += fileLine.charAt(fileLineIndex);

                    }
                    
                    if (fileLine.charAt(fileLineIndex) == ' ' || fileLineIndex == fileLine.length() - 1) {
                        
                        adjMatrix[adjMatrixIndex - 1][Integer.parseInt(pointValue) - 1] = 1; // sets the coordinate on adjacency matrix to 1

                        pointValue = ""; // reset the string

                    }

                }
            
            }
        
        }
        else {

            System.out.println("\"" + fileName + "\" could not be opened!");

        }

        // Closes the input file and the scanner
        inputFileStream.close();
        scanFileLine.close();

    }

    public int[] resetArray(int[] arrayReceived) {

        //set all array elements to -1 
        for(int arrayIndex = 0; arrayIndex < currentSize; arrayIndex++) {
            
            arrayReceived[arrayIndex] = -1;
            
        }

        return arrayReceived;

    }

    public boolean checkBipartite(int startingPoint) {

        Queue<Integer> queueBFS = new LinkedList<Integer>();

        colorOfVertices = new int[currentSize];
        colorOfVertices = resetArray(colorOfVertices);

        final int colorNone = -1;
        final int colorOne = 1;
        final int colorTwo = 2;

        colorOfVertices[startingPoint - 1] = colorOne;
        queueBFS.add(startingPoint);

        while (queueBFS.isEmpty() == false) {

            int currentValue = queueBFS.poll();
        
            for (int arrayIndex = 0; arrayIndex < currentSize; arrayIndex++) {

                if (adjMatrix[currentValue - 1][arrayIndex] == 1 && colorOfVertices[arrayIndex] == colorNone) {
                    System.out.println(colorOfVertices[currentValue - 1]);

                    if (colorOfVertices[currentValue - 1] == 1) {

                        colorOfVertices[arrayIndex] = colorTwo;

                    }
                    else {

                        colorOfVertices[arrayIndex] = colorOne;

                    }

                    //colorOfVertices[arrayIndex] = 1 - colorOfVertices[currentValue - 1];
                    //System.out.println(colorOfVertices[arrayIndex] + "!");
                    queueBFS.add(arrayIndex + 1);

                }
                else if (adjMatrix[currentValue - 1][arrayIndex] == 1 && colorOfVertices[arrayIndex] == colorOfVertices[currentValue - 1]) {

                    return false;

                }

            }

        }

        return true;

    }

    public void printAdjMatrix() {

        System.out.println("\nAdjacency Matrix");

        for (int rowIndex = 0; rowIndex < currentSize; rowIndex++) {

            for (int colIndex = 0; colIndex < currentSize; colIndex++) {

                System.out.print(adjMatrix[rowIndex][colIndex] + " ");

            }

            System.out.println();

        }

    } // printAdjMatrix

}