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

        // linked list store players from file
        LinkList playerList = new LinkList();
        
        // get the input file for processing
        String fileName = /*askInputFileName()*/ "sample_stats2.txt"; // PLEASE CHANGE LATER PLEASE CHANGE LATER PLEASE CHANGE LATER PLEASE CHANGE LATER PLEASE CHANGE LATER PLEASE CHANGE LATER PLEASE CHANGE LATER

        // file scanning and processing
        File inputFile = new File(fileName); 
        FileInputStream inputFileStream = new FileInputStream(inputFile);
        Scanner scanFileLine = new Scanner(inputFile);

        System.out.println(); // create an empty line
        
        if (inputFile.exists() == true) { // makes sure the input file actually exists

            int playerNum = 1;

            while (scanFileLine.hasNextLine() == true) { // keeps scanning file until it reaches the end of the file

                readFileLine(scanFileLine, playerList, playerNum); // reads and stores the players into the playerList linked list
                playerNum++;

            }

        }
        else { // file could not be opened

            System.out.println("\"" + fileName + "\" could not be opened!");

        }
        
        // Closes the input file and the scanner
        inputFileStream.close();
        scanFileLine.close();

        // combine stats of players with same names and remove the extra 
        playerList.checkForMultipleEntries();

        // sort the players into alphabetical order
        playerList.sortPlayers_Alpha();

        // print the players and their stats recursively
        System.out.println("Player\tA-B\tH\tW\tK\tP\tS\tBA\tOBP");
        System.out.println("----------------------------------------------------------------------");
        
        playerList.printStatsRecursively(playerList.getHeadNode());

        // - - - Testing - - - //

        /* 
        LinkList testList = new LinkList();

        testList.appendPlayer(new Player("John","HHHOO",1));
        testList.appendPlayer(new Player("Bill","HHO",2));
        testList.appendPlayer(new Player("Zelda","SSSSSSS",3));

        System.out.println(testList.checkListInOrderByStat_GreatestToLeast(testList, "O", false));

        System.out.println("\nPlayer\tA-B\tH\tW\tK\tP\tS\tBA\tOBP");
        System.out.println("----------------------------------------------------------------------");
        
        playerList.printStatsRecursively(testList.getHeadNode());
        */


         
        LinkList playerBattingAverageList = playerList.sortPlayersByStat_GreatestToLeast(playerList, "BA", true);
        
        System.out.println("\nPlayer\tA-B\tH\tW\tK\tP\tS\tBA\tOBP");
        System.out.println("----------------------------------------------------------------------");
        
        playerList.printStatsRecursively(playerBattingAverageList.getHeadNode());
        
        
    } // Main

    public static String askInputFileName() {

        Scanner inputFileName = new Scanner(System.in);

        System.out.println("Please input the filename for Super Mario Sluggers");
        String fileName = inputFileName.nextLine(); // asks user for input file name
        System.out.println("The file you inputed was \"" + fileName + "\".");
        
        inputFileName.close();

        return fileName;

    } // askInputFileName

    public static void readFileLine(Scanner scanFileLine, LinkList playerList, int playerNum) {

        // strings to store information from file
        String fileLine = new String();
        String playerName = new String();
        String playerStats = new String();
        
        fileLine = scanFileLine.nextLine(); // scans line in the file

        if (fileLine.length() != 0) { // does not process blank lines

            for (int fileLineIndex = 0; fileLineIndex < fileLine.length(); fileLineIndex++) {

                if(fileLine.charAt(fileLineIndex) == ' ') { 

                    // using substring method to seperate names and stats
                    playerName = fileLine.substring(0, fileLineIndex);
                    playerStats = fileLine.substring(fileLineIndex + 1, fileLine.length());
                
                }

            }

            // stores extracted names and stats into player class
            playerList.appendPlayer(new Player(playerName, playerStats, playerNum));

        }

    } // readFileLine

}