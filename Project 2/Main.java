/* 
 * Project 2: Super Mario Sluggers Part 2 with Linked Lists and Classes
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 9/27/2022
 * Class & Section: CS - 2366.003
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main (String[] args) throws IOException {
        
        //System.out.println("Test");

        LinkedList playerList = new LinkedList();
        
        
        // - - - Testing - - - //

        Player blank = new Player("Tom", "ASDASD");
        //Player blank = new Player("Ada2", "ASDASD");

        playerList.appendPlayer(blank);
        playerList.appendPlayer(new Player("Tom2", "WTFHOWDOESTHISWORK"));
        playerList.appendPlayer(new Player("Tom10", "WTFHOWDOESTHISWORK"));
        playerList.appendPlayer(new Player("Tom3", "WTFHOWDOESTHISWORK"));

        playerList.TEMPORARY_printStats();

    } // Main

    public static String askInputFileName() {

        Scanner inputFileName = new Scanner(System.in);

        System.out.println("Please input the filename for Super Mario Sluggers");
        String fileName = inputFileName.nextLine(); // asks user for input file name
        System.out.println("The file you inputed was \"" + fileName + "\".");
        
        inputFileName.close();

        return fileName;

    } // askInputFileName

    //public static void readFileLine(Scanner scanFileLine, )

}