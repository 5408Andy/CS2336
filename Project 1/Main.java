/* 
 * Project One: Army of Ants
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 9/12/2022
 * Class & Section: CS - 2366.003
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    private static final int X_MAX = 10;

    private static final int Y_MAX = 10;
    public static void main (String[] args) throws IOException {

        // Inputs for Army of Ants
        Scanner inputScannerMain = new Scanner(System.in);
        
        String fileNameMain = askInputFileName(inputScannerMain);
        String antCharacter = askInputAntCharacter(inputScannerMain);
        String beetleCharacter = askInputBeetleCharacter(inputScannerMain);
        int numOfTurns = askInputNumberOfTurns(inputScannerMain);
        
        System.out.print("\n");

        //System.out.print("\033[H\033[2J"); // escape sequence which clears the console
        //System.out.flush();

        inputScannerMain.close();

        // declaring the 2D array of objects which will store both ant and beetle objects
        Creature[][] creatureMapMain = new Creature[X_MAX][Y_MAX];

        
        // info for movement of beetles
        int[] closestOrthogonalAnts = new int[4];
        int[] neighborsOfTiedOrthogonalAnts = new int[4];
        int[] nearestEdge = new int[4];

        // info for movement of ants
        int[] closestOrthogonalBeetles = new int[4];

        // extracts information from "world.txt" file and creates objects for game to play
        popilateGridArray(fileNameMain, creatureMapMain); 
    
        for (int turnPhase = 1; turnPhase <= numOfTurns; turnPhase++) { // the number of turns the user wants the game to run

            for (int turnOrder = 1; turnOrder <= 5; turnOrder++) { // turn order, 1. beetles move, 2. ants move, 3. beetles starve, 4. ants breed, 5. beetles breed 

                // checks and loops through the grid and moves creatures according to their type
                for (int creatureColumnIndex = 0; creatureColumnIndex < Y_MAX; creatureColumnIndex++) { // reads by column by column

                    for (int creatureRowIndex = 0; creatureRowIndex < X_MAX; creatureRowIndex++) {

                        if (turnOrder == 1 && creatureMapMain[creatureRowIndex][creatureColumnIndex] instanceof Beetle && creatureMapMain[creatureRowIndex][creatureColumnIndex].getCreatureMoved() == false) {
                            
                            // find necessary information for beetle to decide direction of movement
                            closestOrthogonalAnts = findClosestOrthogonalAnts(creatureMapMain, creatureColumnIndex, creatureRowIndex);
                            neighborsOfTiedOrthogonalAnts = findNeighborsOfTiedOrthogonalAnts(creatureMapMain, creatureColumnIndex, creatureRowIndex, closestOrthogonalAnts);
                            nearestEdge = findNearestEdge(creatureMapMain, creatureColumnIndex, creatureRowIndex);
                            
                            creatureMapMain[creatureRowIndex][creatureColumnIndex].creatureHasMoved();
                            creatureMapMain[creatureRowIndex][creatureColumnIndex].creatureNowOld();

                            moveBeetleInGrid(creatureMapMain, creatureMapMain[creatureRowIndex][creatureColumnIndex], creatureColumnIndex, creatureRowIndex, closestOrthogonalAnts, neighborsOfTiedOrthogonalAnts, nearestEdge);

                        }
                        
                        if (turnOrder == 2 && creatureMapMain[creatureRowIndex][creatureColumnIndex] instanceof Ant && creatureMapMain[creatureRowIndex][creatureColumnIndex].getCreatureMoved() == false) {

                            // find necessary information for ant to decide direction of movement
                            closestOrthogonalBeetles = findClosestOrthogonalBeetle(creatureMapMain, creatureColumnIndex, creatureRowIndex);
                            
                            creatureMapMain[creatureRowIndex][creatureColumnIndex].creatureHasMoved();
                            creatureMapMain[creatureRowIndex][creatureColumnIndex].creatureNowOld();

                            moveAntInGrid(creatureMapMain, creatureMapMain[creatureRowIndex][creatureColumnIndex], creatureColumnIndex, creatureRowIndex, closestOrthogonalBeetles);

                        }
                        
                        if (turnOrder == 3 && creatureMapMain[creatureRowIndex][creatureColumnIndex] instanceof Beetle) {

                            // if the beetle has reached a starve turn (beetle has not eaten an ant within 5 turns), beetle is set to null, deleting it...
                            if (((Beetle)creatureMapMain[creatureRowIndex][creatureColumnIndex]).starve() == true) {
                                
                                creatureMapMain[creatureRowIndex][creatureColumnIndex] = null;

                                //System.out.println("Beetle has died.")

                            }

                        }
                       
                        if (turnOrder == 4 && creatureMapMain[creatureRowIndex][creatureColumnIndex] instanceof Ant) {
                            
                            // if the beetle has survived 8 turns without starving, the beelte can reproduce
                            if (creatureMapMain[creatureRowIndex][creatureColumnIndex].breed() == true) {

                                antsBreedCreate(creatureMapMain, creatureColumnIndex, creatureRowIndex);

                            }

                        }

                        if (turnOrder == 5 && creatureMapMain[creatureRowIndex][creatureColumnIndex] instanceof Beetle) {

                            // if the ant has survived for 3 turns without being eaten by beetle, the ant can reproduce
                            if (creatureMapMain[creatureRowIndex][creatureColumnIndex].breed() == true) {

                                beetlesBreedCreate(creatureMapMain, creatureColumnIndex, creatureRowIndex);

                            }

                        }

                    }

                }   

            }
            
            resetCreatureMovedandIncrement(creatureMapMain); // function which helps prevent a creature which has already moved from moving again within the same turn

            printGrid(creatureMapMain, antCharacter, beetleCharacter, turnPhase); // prints out the grid after done with turn order

        }
        

    } // main

    public static String askInputFileName(Scanner inputScanner) { // prompts the user for input file name

        System.out.println("Please input the filename for Army of Ants");
        String fileName = inputScanner.nextLine(); // asks user for input file name
        System.out.println("The file you inputed was \"" + fileName + "\".");

        return fileName;

    } // askInputFileName

    public static String askInputAntCharacter(Scanner inputScanner) { // prompts the user for input file name

        System.out.println("Please input the character to represent ants.");
        String charAnt = inputScanner.nextLine(); // asks user for ant character
        System.out.println("The character you inputed was \"" + charAnt + "\".");

        return charAnt;

    } // askInputAntCharacter

    public static String askInputBeetleCharacter(Scanner inputScanner) { // prompts the user for input file name

        System.out.println("Please input the character to represent beetles.");
        String charBeetle = inputScanner.nextLine(); // asks user for beetles character
        System.out.println("The character you inputed was \"" + charBeetle + "\".");

        return charBeetle;

    } // askInputBeetleCharacter

    public static int askInputNumberOfTurns(Scanner inputScanner) { // prompts the user for input file name

        System.out.println("Please input the number of turns.");
        int numOfTurns = inputScanner.nextInt(); // asks user for beetles character
        System.out.println("The number of turns you inputted was \"" + numOfTurns + "\".");

        return numOfTurns;

    } // askInputBeetleCharacter

    public static void popilateGridArray(String fileName, Creature[][] creatureMap) throws IOException {

        File inputFile = new File(fileName); 
        FileInputStream inputFileStream = new FileInputStream(inputFile);
        Scanner scanFileLine = new Scanner(inputFile);

        if (inputFile.exists() == true) { // checks if the file exists

            int rowOfMap = 0;
            while (scanFileLine.hasNextLine() == true) {

                String fileLine = scanFileLine.nextLine();

                for (int fileLineIndex = 0; fileLineIndex < fileLine.length(); fileLineIndex++) {

                    if (fileLine.charAt(fileLineIndex) == 'B') { // if the character is 'B', set the creatureMap at index to beelte object

                        creatureMap[rowOfMap][fileLineIndex] = new Beetle();

                        //System.out.println("Beetle Added to Row: [" + rowOfMap + "] Column: [" + fileLineIndex + "]");

                    }
                    else if (fileLine.charAt(fileLineIndex) == 'a') { // if the character is 'a', set the creatureMap at index to ant object

                        creatureMap[rowOfMap][fileLineIndex] = new Ant();

                        //System.out.println("Ant Added to Row: [" + rowOfMap + "] Column: [" + fileLineIndex + "]");

                    }
                    else if (fileLine.charAt(fileLineIndex) == 'a' || fileLine.charAt(fileLineIndex) == '\n') { // if the character is a space, set the creatureMap at index to null

                        creatureMap[rowOfMap][fileLineIndex] = null;

                        //System.out.println("Null Added to Row: [" + rowOfMap + "] Column: [" + fileLineIndex + "]");

                    }

                }

                rowOfMap++;

            }

        }
        else {

            System.out.println("\"" + fileName + "\" could not be opened!");

        }

        scanFileLine.close();
        inputFileStream.close();

    } // popilateGridArray

    public static void printGrid(Creature[][] creatureMap, String charForAnt, String charForBeetle, int turnNumber) {

        System.out.print("TURN " + turnNumber + "\n");

        // loops through grid and prints out the creature with respective character assigned by user
        for (int creatureRowIndex = 0; creatureRowIndex < X_MAX; creatureRowIndex++) {

            for (int creatureColumnIndex = 0; creatureColumnIndex < Y_MAX; creatureColumnIndex++) {

                if (creatureMap[creatureRowIndex][creatureColumnIndex] instanceof Beetle) { // if instance of beetle, print user's desired character

                    System.out.print(charForBeetle);

                }
                else if (creatureMap[creatureRowIndex][creatureColumnIndex] instanceof Ant) { // if instance of ant, print user's desired character

                    System.out.print(charForAnt);

                }
                else { // if not any of the previous choices, print a space

                    System.out.print(" ");

                }

            }

            System.out.print("\n");

        }

        System.out.print("\n");

    } // printGrid

    // - - - - - - - - - - - - - - - - MOVEMENT OF THE BEETLE - - - - - - - - - - - - - - - - //

    public static int[] findClosestOrthogonalAnts(Creature[][] creatureMap, int xPositionIndex, int yPositionIndex) {

        int[] orthogonalAntDistances = new int[4];
        
        for (int indexNorth = yPositionIndex; indexNorth >= 0; indexNorth--) { // finds orthogonal north ant
            //System.out.println("Checking [" + xPositionIndex + "] [" + indexNorth + "]");
            if (creatureMap[indexNorth][xPositionIndex] instanceof Ant) {
            
                orthogonalAntDistances[0] = yPositionIndex - indexNorth; // stores the distance between beetle and orthogonal ant
                break; // stop after 1st find

            }
            else {

                orthogonalAntDistances[0] = 0;

            }

        }
 
        for (int indexEast = xPositionIndex; indexEast < X_MAX; indexEast++) { // finds orthogonal east ant
            //System.out.println("Checking [" + indexEast + "] [" + yPositionIndex + "]");
            if (creatureMap[yPositionIndex][indexEast] instanceof Ant) {
                
                orthogonalAntDistances[1] = indexEast - xPositionIndex; // stores the distance between beetle and orthogonal ant
                break; // stop after 1st find

            }
            else {

                orthogonalAntDistances[1] = 0;

            }

        }

        for (int indexSouth = yPositionIndex; indexSouth < Y_MAX; indexSouth++) { // finds orthogonal south ant
            //System.out.println("Checking [" + indexSouth + "] [" + xPositionIndex + "]");
            if (creatureMap[indexSouth][xPositionIndex] instanceof Ant) {
            
                orthogonalAntDistances[2] = indexSouth - yPositionIndex; // stores the distance between beetle and orthogonal ant
                break; // stop after 1st find

            }
            else {

                orthogonalAntDistances[2] = 0;

            }

        }

        for (int indexWest = xPositionIndex; indexWest >= 0; indexWest--) { // finds orthogonal east ant
            //System.out.println("Checking [" + indexWest + "] [" + yPositionIndex + "]");
            if (creatureMap[yPositionIndex][indexWest] instanceof Ant) {
                
                orthogonalAntDistances[3] = xPositionIndex - indexWest; // stores the distance between beetle and orthogonal ant
                break; // stop after 1st find

            }
            else {

                orthogonalAntDistances[3] = 0;

            }

        }

        return orthogonalAntDistances;

    } // findClosestOrthogonalAnt

    public static int[][] findPositionOfOrthogonalAnts(Creature[][] creatureMap, int xPositionIndex, int yPositionIndex, int[] orthogonalAntDistances) { // method used to help find adjacent neighbors

        int[][] orthogonalAntCoords = new int[][] {{0,0}, {0,0}, {0,0}, {0,0}}; // (x,y) format

        for (int orthogonalAntDistancesIndex = 0; orthogonalAntDistancesIndex < 4; orthogonalAntDistancesIndex++) {

            if (orthogonalAntDistances[orthogonalAntDistancesIndex] != 0) {

                if (orthogonalAntDistancesIndex == 0) { // find position of north orthogonal ant

                    orthogonalAntCoords[0][0] += xPositionIndex;
                    orthogonalAntCoords[0][1] += yPositionIndex - orthogonalAntDistances[orthogonalAntDistancesIndex];

                }
                if (orthogonalAntDistancesIndex == 1) { // find position of east orthogonal ant

                    orthogonalAntCoords[1][0] += xPositionIndex + orthogonalAntDistances[orthogonalAntDistancesIndex];
                    orthogonalAntCoords[1][1] += yPositionIndex;

                }
                if (orthogonalAntDistancesIndex == 2) { // find position of south orthogonal ant

                    orthogonalAntCoords[2][0] += xPositionIndex;
                    orthogonalAntCoords[2][1] += yPositionIndex + orthogonalAntDistances[orthogonalAntDistancesIndex];

                }
                if (orthogonalAntDistancesIndex == 3) { // find position of west orthogonal ant
                   
                    orthogonalAntCoords[3][0] += xPositionIndex - orthogonalAntDistances[orthogonalAntDistancesIndex];
                    orthogonalAntCoords[3][1] += yPositionIndex;

                }
                
            }

        }

        return orthogonalAntCoords;

    } // findPositionOfOrthogonalAnts

    public static int[] findNeighborsOfTiedOrthogonalAnts(Creature[][] creatureMap, int xPositionIndex, int yPositionIndex, int[] orthogonalAntDistances) {
        
        int[] orthogonalAntNeighbors = new int[4];

        int[][] orthogonalAntCoords = findPositionOfOrthogonalAnts(creatureMap, xPositionIndex, yPositionIndex, orthogonalAntDistances);

        int[][] checkDirections = new int[][] {{-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0, -1}};

        for (int coordinateIndex = 0; coordinateIndex < 4; coordinateIndex++) { // counts the neighbors of each orthogonal ant

            for (int checkDirectionsIndex = 0; checkDirectionsIndex < 8; checkDirectionsIndex++) { // loops through all adjacent spots of ant

                if ((orthogonalAntCoords[coordinateIndex][1] + checkDirections[checkDirectionsIndex][0]) < 10 && (orthogonalAntCoords[coordinateIndex][0] + checkDirections[checkDirectionsIndex][1]) < 10) { // prevents from accessing max size of creature array
                
                    if ((orthogonalAntCoords[coordinateIndex][1] + checkDirections[checkDirectionsIndex][0]) >= 0 && (orthogonalAntCoords[coordinateIndex][0] + checkDirections[checkDirectionsIndex][1]) >= 0) { // prevents from accessing negative indexes of creature array

                        if (creatureMap[orthogonalAntCoords[coordinateIndex][1] + checkDirections[checkDirectionsIndex][0]][orthogonalAntCoords[coordinateIndex][0] + checkDirections[checkDirectionsIndex][1]] instanceof Ant) { // checks adjacent ants of orthogonal ant to beetle

                            orthogonalAntNeighbors[coordinateIndex]++; // if there is a fellow ant adjacent to orthogonal ant, add to count

                        }

                    }

                }

            }

        }
        
        return orthogonalAntNeighbors; // return the amount of neighbors at each adjacent ant

    } // findNeighborsOfTiedOrthogonalAnts

    public static int[] findNearestEdge(Creature[][] creatureMap, int xPositionIndex, int yPositionIndex) {

        int[] edgeDistances = new int[4]; 

        edgeDistances[0] = yPositionIndex - 0; // distance from north edge
        edgeDistances[1] = (X_MAX - 1) - xPositionIndex; // distance from east edge
        edgeDistances[2] = (Y_MAX - 1) - yPositionIndex; // distance from south edge
        edgeDistances[3] = xPositionIndex - 0; // distance from west edge

        return edgeDistances; // return the distance of each edge from the beetle

    } // findNearestEdge

    public static void moveBeetleInGrid(Creature[][] creatureMap, Creature beetleObj, int xPositionIndex, int yPositionIndex, int[] closestOrthogonalAnts, int[] neighborsOfTiedOrthogonalAnts, int[] nearestEdge) {

        // send information to beetle so it can decide which direction to go
        String beetleDirection = beetleObj.move(formatToStringBeetle(closestOrthogonalAnts, neighborsOfTiedOrthogonalAnts, nearestEdge));

        if (beetleDirection == "North") {

            if (yPositionIndex - 1 >= 0 && (creatureMap[yPositionIndex - 1][xPositionIndex] == null || creatureMap[yPositionIndex - 1][xPositionIndex] instanceof Ant)) { // the conditional in each if statement says the beetle can not be out of bounds

                if (creatureMap[yPositionIndex - 1][xPositionIndex] instanceof Ant) { // if beetle moves in direction which takes spot of ant, the hunger bar of beetle will be reset

                    ((Beetle)creatureMap[yPositionIndex][xPositionIndex]).eaten();

                }

                creatureMap[yPositionIndex - 1][xPositionIndex] = beetleObj;
                creatureMap[yPositionIndex][xPositionIndex] = null;

            }

        }
        else if (beetleDirection == "East") {

            if (xPositionIndex + 1 < X_MAX && (creatureMap[yPositionIndex][xPositionIndex + 1] == null || creatureMap[yPositionIndex][xPositionIndex + 1] instanceof Ant)) { // the conditional in each if statement says the beetle can not be out of bounds

                if (creatureMap[yPositionIndex][xPositionIndex + 1] instanceof Ant) { // if beetle moves in direction which takes spot of ant, the hunger bar of beetle will be reset

                    ((Beetle)creatureMap[yPositionIndex][xPositionIndex]).eaten();

                }

                // sends address of beetle to new spot in array and sets previous spot to null
                creatureMap[yPositionIndex][xPositionIndex + 1] = beetleObj;
                creatureMap[yPositionIndex][xPositionIndex] = null;

            }

        }
        else if (beetleDirection == "South") {

            if (yPositionIndex + 1 < Y_MAX && (creatureMap[yPositionIndex + 1][xPositionIndex] == null || creatureMap[yPositionIndex + 1][xPositionIndex] instanceof Ant)) { // the conditional in each if statement says the beetle can not be out of bounds

                if (creatureMap[yPositionIndex + 1][xPositionIndex] instanceof Ant) { // if beetle moves in direction which takes spot of ant, the hunger bar of beetle will be reset

                    ((Beetle)creatureMap[yPositionIndex][xPositionIndex]).eaten();

                }

                // sends address of beetle to new spot in array and sets previous spot to null
                creatureMap[yPositionIndex + 1][xPositionIndex] = beetleObj;
                creatureMap[yPositionIndex][xPositionIndex] = null;

            }

        }
        else if (beetleDirection == "West") {

            if (xPositionIndex - 1 >= 0 && (creatureMap[yPositionIndex][xPositionIndex - 1] == null || creatureMap[yPositionIndex][xPositionIndex - 1] instanceof Ant)) { // the conditional in each if statement says the beetle can not be out of bounds

                if (creatureMap[yPositionIndex][xPositionIndex - 1] instanceof Ant) { // if beetle moves in direction which takes spot of ant, the hunger bar of beetle will be reset

                    ((Beetle)creatureMap[yPositionIndex][xPositionIndex]).eaten();

                }

                // sends address of beetle to new spot in array and sets previous spot to null
                creatureMap[yPositionIndex][xPositionIndex - 1] = beetleObj;
                creatureMap[yPositionIndex][xPositionIndex] = null;

            }

        }

    } // moveBeetleInGrid

    // - - - - - - - - - - - - - - - - MOVEMENT OF THE ANT - - - - - - - - - - - - - - - - //

    public static int[] findClosestOrthogonalBeetle(Creature[][] creatureMap, int xPositionIndex, int yPositionIndex) {

        int[] orthogonalBeetleDistances = new int[4];

        for (int indexNorth = yPositionIndex; indexNorth >= 0; indexNorth--) { // finds orthogonal north beetle
            //System.out.println("Checking [" + xPositionIndex + "] [" + indexNorth + "]");
            if (creatureMap[indexNorth][xPositionIndex] instanceof Beetle) {
            
                orthogonalBeetleDistances[0] = yPositionIndex - indexNorth; // stores the distance between beetle and orthogonal beetle
                break; // stop after 1st find

            }
            else {

                orthogonalBeetleDistances[0] = 0;

            }

        }
 
        for (int indexEast = xPositionIndex; indexEast < X_MAX; indexEast++) { // finds orthogonal east beetle
            //System.out.println("Checking [" + indexEast + "] [" + yPositionIndex + "]");
            if (creatureMap[yPositionIndex][indexEast] instanceof Beetle) {
                
                orthogonalBeetleDistances[1] = indexEast - xPositionIndex; // stores the distance between ant and orthogonal beetle
                break; // stop after 1st find

            }
            else {

                orthogonalBeetleDistances[1] = 0;

            }

        }

        for (int indexSouth = yPositionIndex; indexSouth < Y_MAX; indexSouth++) { // finds orthogonal south beetle
            //System.out.println("Checking [" + indexSouth + "] [" + xPositionIndex + "]");
            if (creatureMap[indexSouth][xPositionIndex] instanceof Beetle) {
                
                orthogonalBeetleDistances[2] = indexSouth - yPositionIndex; // stores the distance between ant and orthogonal beetle
                break; // stop after 1st find

            }
            else {

                orthogonalBeetleDistances[2] = 0;

            }

        }

        for (int indexWest = xPositionIndex; indexWest >= 0; indexWest--) { // finds orthogonal east beetle
            //System.out.println("Checking [" + indexWest + "] [" + yPositionIndex + "]");
            if (creatureMap[yPositionIndex][indexWest] instanceof Beetle) {
                
                orthogonalBeetleDistances[3] = xPositionIndex - indexWest; // stores the distance between ant and orthogonal beetle
                break; // stop after 1st find

            }
            else {

                orthogonalBeetleDistances[3] = 0;

            }

        }

        return orthogonalBeetleDistances;

    } // findClosestOrthogonalBeetle

    public static void moveAntInGrid(Creature[][] creatureMap, Creature antObj, int xPositionIndex, int yPositionIndex, int[] closestOrthogonalBeetles) {
        
        String antDirection = antObj.move(formatToStringAnt(closestOrthogonalBeetles));
        
        if (antDirection == "North") { 

            if (yPositionIndex - 1 >= 0 && creatureMap[yPositionIndex - 1][xPositionIndex] == null) { // the conditional in each statement states that the the ant can not move out of bounds or move into another creature's spot
            
                // sends address of beetle to new spot in array and sets previous spot to null
                creatureMap[yPositionIndex - 1][xPositionIndex] = antObj;
                creatureMap[yPositionIndex][xPositionIndex] = null;

            }

        }
        else if (antDirection == "East") {

            if (xPositionIndex + 1 < X_MAX && creatureMap[yPositionIndex][xPositionIndex + 1] == null) { // the conditional in each statement states that the the ant can not move out of bounds or move into another creature's spot

                // sends address of beetle to new spot in array and sets previous spot to null
                creatureMap[yPositionIndex][xPositionIndex + 1] = antObj;
                creatureMap[yPositionIndex][xPositionIndex] = null;

            }

        }
        else if (antDirection == "South") {

            if (yPositionIndex + 1 < Y_MAX && creatureMap[yPositionIndex + 1][xPositionIndex] == null) { // the conditional in each statement states that the the ant can not move out of bounds or move into another creature's spot

                // sends address of beetle to new spot in array and sets previous spot to null
                creatureMap[yPositionIndex + 1][xPositionIndex] = antObj;
                creatureMap[yPositionIndex][xPositionIndex] = null;

            }

        }
        else if (antDirection == "West") {

            if (xPositionIndex - 1 >= 0 && creatureMap[yPositionIndex][xPositionIndex - 1] == null) { // the conditional in each statement states that the the ant can not move out of bounds or move into another creature's spot

                // sends address of beetle to new spot in array and sets previous spot to null
                creatureMap[yPositionIndex][xPositionIndex - 1] = antObj;
                creatureMap[yPositionIndex][xPositionIndex] = null;

            }

        }

    } //  moveAntInGrid

    // - - - - - - - - - - - - - - - - BREEDING OF BEETLE - - - - - - - - - - - - - - - - //

    public static void beetlesBreedCreate(Creature[][] creatureMap, int xPositionIndex, int yPositionIndex) {

        if (yPositionIndex - 1 >= 0 && creatureMap[yPositionIndex - 1][xPositionIndex] == null) { // check north orthogonal if empty and not out of bounds

            creatureMap[yPositionIndex - 1][xPositionIndex] = new Beetle();

        }
        else if (xPositionIndex + 1 < X_MAX && creatureMap[yPositionIndex][xPositionIndex + 1] == null) { // check east orthogonal if empty and not out of bounds

            creatureMap[yPositionIndex][xPositionIndex + 1] = new Beetle();

        }
        else if (yPositionIndex + 1 < Y_MAX && creatureMap[yPositionIndex + 1][xPositionIndex] == null) { // check south orthogonal if empty and not out of bounds

            creatureMap[yPositionIndex + 1][xPositionIndex] = new Beetle();

        }
        else if (xPositionIndex - 1 >= 0 && creatureMap[yPositionIndex][xPositionIndex - 1] == null) { // check west orthogonal if empty and not out of bounds

            creatureMap[yPositionIndex][xPositionIndex - 1] = new Beetle();

        }

    } // antsBreedCreate

    // - - - - - - - - - - - - - - - - BREEDING OF ANT - - - - - - - - - - - - - - - - //

    public static void antsBreedCreate(Creature[][] creatureMap, int xPositionIndex, int yPositionIndex) {

        if (yPositionIndex - 1 >= 0 && creatureMap[yPositionIndex - 1][xPositionIndex] == null) { // check north orthogonal if empty and not out of bounds

            creatureMap[yPositionIndex - 1][xPositionIndex] = new Ant();

        }
        else if (xPositionIndex + 1 < X_MAX && creatureMap[yPositionIndex][xPositionIndex + 1] == null) { // check east orthogonal if empty and not out of bounds

            creatureMap[yPositionIndex][xPositionIndex + 1] = new Ant();

        }
        else if (yPositionIndex + 1 < Y_MAX && creatureMap[yPositionIndex + 1][xPositionIndex] == null) { // check south orthogonal if empty and not out of bounds

            creatureMap[yPositionIndex + 1][xPositionIndex] = new Ant();

        }
        else if (xPositionIndex - 1 >= 0 && creatureMap[yPositionIndex][xPositionIndex - 1] == null) { // check west orthogonal if empty and not out of bounds

            creatureMap[yPositionIndex][xPositionIndex - 1] = new Ant();

        }

    } // antsBreedCreate

    // - - - - - - - - - - - - - - - - MISC - - - - - - - - - - - - - - - - //

    public static void resetCreatureMovedandIncrement(Creature[][] creatureMap) { // resets the boolean value of whether the creature has moved back to false to get ready for next turn
        
        // I need to keep track of the creatures that have moved so they don't get moved again when I am looping through the creature map
        for (int creatureRowIndex = 0; creatureRowIndex < X_MAX; creatureRowIndex++) { // loops through 2D creature array and sets all creatures to have "not moved" for the turn

            for (int creatureColumnIndex = 0; creatureColumnIndex < Y_MAX; creatureColumnIndex++) {

                if (creatureMap[creatureRowIndex][creatureColumnIndex] instanceof Creature) {
                    
                    creatureMap[creatureRowIndex][creatureColumnIndex].creatureMovedReset();

                    if (creatureMap[creatureRowIndex][creatureColumnIndex].getCreatureBorn() == false) {
                        
                        creatureMap[creatureRowIndex][creatureColumnIndex].incrementTurns();

                    }
                
                }

            }

        }

    } // resetCreatureMovedandIncrement

    public static String formatToStringBeetle(int[] orthogonalAntDistances, int[] orthogonalAntNeighbors, int[] edgeDistances) { // takes the necessary data for beetle to decide direction and transform data into string

        String moveBeetleInfo = "";

        for (int arrayIndex = 0; arrayIndex < 4; arrayIndex++) { // loops through array and output each element into string

            moveBeetleInfo += orthogonalAntDistances[arrayIndex];

        }
        
        moveBeetleInfo += " ";

        for (int arrayIndex = 0; arrayIndex < 4; arrayIndex++) { // loops through array and output each element into string

            moveBeetleInfo += orthogonalAntNeighbors[arrayIndex];

        }

        moveBeetleInfo += " ";

        for (int arrayIndex = 0; arrayIndex < 4; arrayIndex++) { // loops through array and output each element into string

            moveBeetleInfo += edgeDistances[arrayIndex];

        }

        return moveBeetleInfo; // return string example would be 4512 0020 5432, the spaces differentiate the the type of information it holds, and the order of each 4 digit number represents the data in NESW priority

    } // formatToStringBeetle

    public static String formatToStringAnt(int[] orthogonalBeetleDistances) { /// takes necessary data for ant to decide direction and turns it into a string

        String moveAntInfo = "";
        
        for (int arrayIndex = 0; arrayIndex < 4; arrayIndex++) { // loops through array and output each element into string

            moveAntInfo += orthogonalBeetleDistances[arrayIndex];

        }

        return moveAntInfo; // return string example would be 4321, and the order of the 4 digit number represents the data in NESW priority

    } // formatToStringAnt

}