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

        LinkedList playerList = new LinkedList();
        
        //String fileName = askInputFileName();

        //File inputFile = new File(fileName); 
        //FileInputStream inputFileStream = new FileInputStream(inputFile);
        //Scanner scanFileLine = new Scanner(inputFile);

        System.out.println(); // create an empty line
        
        /* 
        if (inputFile.exists() == true) { // makes sure the input file actually exists

            while (scanFileLine.hasNextLine() == true) { // keeps scanning file until it reaches the end of the file

                readFileLine(scanFileLine, );

                if (playerNameArray[playerIndex] == null) { // skips blank lines

                    continue;
        
                }

                parsePlayerStats(playerHitStat, playerOutStat, playerStrikeOutStat, playerWalkStat, playerHitByPitchStat, playerSacrificeStat, playerStatsArray[playerIndex], playerIndex);

                calculateBattingAverage(playerStrikeOutStat, playerOutStat, playerHitStat, playerIndex);

                battingAverageStat[playerIndex] = calculateBattingAverage(playerStrikeOutStat, playerOutStat, playerHitStat, playerIndex);
                onBasePercentageStat[playerIndex] = calculateOnBasePercentage(playerHitStat, playerWalkStat, playerHitByPitchStat, playerOutStat, playerStrikeOutStat, playerSacrificeStat, playerIndex);

                playerIndex++;

            }

        }
        else {

            System.out.println("\"" + fileName + "\" could not be opened!");

        }
        */

        // Closes the input file and the scanner
        //inputFileStream.close();
        //scanFileLine.close();

        // - - - Testing - - - //
        
        playerList.appendPlayer(new Player("Bob", "WTFHOWDOESTHISWORK"));
        playerList.appendPlayer(new Player("Tom", "ASDASD"));
        playerList.appendPlayer(new Player("Zed", "WasdSTHISWORK"));
        playerList.appendPlayer(new Player("Ada", "WTFHOWDOESTHISWORK"));
        playerList.appendPlayer(new Player("Aba", "WTFHOWDOESTHISWORK"));
        playerList.appendPlayer(new Player("Jane", "WTFHOWDOESTHISWORK"));

        System.out.println("H1");
        System.out.println(playerList.checkListInOrder());
        playerList.printStatsRecursively(playerList.getHeadNode());

        playerList.sortPlayers();

        System.out.println("\nH2");
        System.out.println(playerList.checkListInOrder());
        playerList.printStatsRecursively(playerList.getHeadNode());
        
        
        /* 
        playerList.sortPlayers();

        System.out.println("\nH3");
        //System.out.println(playerList.checkListInOrder());
        playerList.printStatsRecursively(playerList.getHeadNode());

        playerList.sortPlayers();

        System.out.println("\nH4");
        //System.out.println(playerList.checkListInOrder());
        playerList.printStatsRecursively(playerList.getHeadNode());
        //System.out.println();

        //playerList.printStatsRecursively(playerList.getHeadNode());
        */
        
        //playerList.TEMPORARY_printStats();

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