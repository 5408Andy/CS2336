/* 
 * HW Review #2
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 11/3/2022
 * Class & Section: CS - 2366.003
 */

// File Input
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// Depth First Traversal
import java.util.Stack;

public class Main {

    private static class Graph {

        // max size of adjacency matrix
        private int maxSize; 

        // current size 
        private int currentSize;

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

        public boolean isConnected(int startPoint) {

            boolean[] visitedNodes = new boolean[currentSize];
            for (int arrayIndex = 0; arrayIndex < currentSize; arrayIndex++) {

                visitedNodes[arrayIndex] = false;

            }

            Stack<Integer> stackList = new Stack<Integer>();
            boolean isItConnected = false;

            stackList.push(startPoint);
            visitedNodes[startPoint - 1] = true;

            int currentDataValue;
            while(stackList.isEmpty() == false) {
                
                currentDataValue = stackList.pop();

                for (int arrayIndex = 0; arrayIndex < currentSize; arrayIndex++) {
                    //System.out.println(currentDataValue);
                    if (adjMatrix[currentDataValue - 1][arrayIndex] == 1 && visitedNodes[arrayIndex] == false) {

                        stackList.push(arrayIndex + 1);
                        visitedNodes[arrayIndex] = true;

                    } 
                    
                }

            }

            // loops through visited node list and check if every node has been visited
            int countNodes = 0;
            for (int arrayIndex = 0; arrayIndex < currentSize; arrayIndex++) {

                if (visitedNodes[arrayIndex] == true) {

                    countNodes++;

                }

            }

            if (countNodes == currentSize) { // if the number of nodes that were visited is equal to the number of vertices, return true

                isItConnected = true;
                    
            }

            return isItConnected;

        } // isConnected

        public boolean checkAllStartingPoints() {

            boolean[] connectedStartingPoints = new boolean[currentSize];
            boolean isStronglyConnected = false;

            for (int arrayIndex = 0; arrayIndex < currentSize; arrayIndex++) {

                if (isConnected(arrayIndex + 1) == true) {

                    connectedStartingPoints[arrayIndex] = true;

                }

            }

            for (int arrayIndex = 0; arrayIndex < currentSize; arrayIndex++) {

                if (connectedStartingPoints[arrayIndex] == false) {

                    isStronglyConnected = false;
                    break;

                }
                else {

                    isStronglyConnected = true;

                }

            }

            return isStronglyConnected;

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

    } // private Graph class in Main.java

    public static void main(String[] args) throws IOException {

        Graph testGraph = new Graph();

        testGraph.createGraph(askInputFileName());
            
        boolean isItConnected = testGraph.checkAllStartingPoints();

        if (isItConnected == true) {

            System.out.println("connected");

        }
        else {

            System.out.println("not connected");

        }

        //stestGraph.printAdjMatrix();

    } // Main

    public static String askInputFileName() {

        Scanner inputFileName = new Scanner(System.in);

        System.out.println("Please input the filename for whether \"a graph is strongly connected or not\"");
        String fileName = inputFileName.nextLine(); // asks user for input file name
        System.out.println("The file you inputed was \"" + fileName + "\".");
        
        inputFileName.close();

        return fileName;

    } // askInputFileName

}