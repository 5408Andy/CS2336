/* 
 * Project Zero Java Implementation: Super Mario Sluggers
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 8/31/2022
 * Class & Section: CS - 2366.003
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    
    public static String askInputFileName() { // prompts the user for input file name

        Scanner inputFileName = new Scanner(System.in);

        System.out.println("Please input the filename for Super Mario Sluggers");
        String fileName = inputFileName.nextLine(); // asks user for input file name
        System.out.println("The file you inputed was \"" + fileName + "\".");
        
        inputFileName.close();

        return fileName;

    } // askInputFileName

    public static void readFileLine(Scanner scanFileLine, String[] playerNameArray, String[] playerStatsArray, int playerIndex) {

        String fileLine = new String();
        String playerName = new String();
        String playerStats = new String();

        fileLine = scanFileLine.nextLine(); // scans line in the file
        if (fileLine.length() != 0) { // does not process blank lines

            //System.out.println(fileLine);

            for (int fileLineIndex = 0; fileLineIndex < fileLine.length(); fileLineIndex++) { // loops through characters in string

                //System.out.println(fileLine.charAt(fileLineIndex));

                if(fileLine.charAt(fileLineIndex) == ' ') { // finds the empty space inbetween the player name and stat so we can store the types of data seperately
                    
                    // using substring method to seperate names and stats
                    playerName = fileLine.substring(0, fileLineIndex);
                    playerStats = fileLine.substring(fileLineIndex + 1, fileLine.length());

                }

            }

            // stores extracted names and stats into parallel arrays
            playerNameArray[playerIndex] = playerName;
            playerStatsArray[playerIndex] = playerStats;

        }

    } // readFileLin

    public static void parsePlayerStats(int[] playerHitStat, int[] playerOutStat, int[] playerStrikeOutStat,  int[] playerWalkStat, int[] playerHitByPitchStat, int[] playerSacrificeStat, String playerStatsLine, int playerIndex) {

        for (int playerStatsLineIndex = 0; playerStatsLineIndex < playerStatsLine.length(); playerStatsLineIndex++) { // if character from players stat line matches with certain statistical char, its occurrence will be counted
        
            if (playerStatsLine.charAt(playerStatsLineIndex) == 'H') {                
            
                playerHitStat[playerIndex]++;
                
            }
            if (playerStatsLine.charAt(playerStatsLineIndex) == 'O') {
            
                playerOutStat[playerIndex]++;
    
            }
            if (playerStatsLine.charAt(playerStatsLineIndex ) == 'K') {
    
                playerStrikeOutStat[playerIndex]++;
            
            }
            if (playerStatsLine.charAt(playerStatsLineIndex) == 'W') {
        
                playerWalkStat[playerIndex]++;
    
            }
            if (playerStatsLine.charAt(playerStatsLineIndex) == 'P') {
                
                playerHitByPitchStat[playerIndex]++;
                
            }
            if (playerStatsLine.charAt(playerStatsLineIndex) == 'S') {
                    
                playerSacrificeStat[playerIndex]++;
            
            }
            
            // characters that do not match any of the above statistic characters are ignored

        }
        
    } // parsePlayerStats

    public static double calculateBattingAverage(int[] playerStrikeOutStat, int[] playerOutStat, int[] playerHitStat, int playerIndex) {

        int atBatValue = playerStrikeOutStat[playerIndex] + playerOutStat[playerIndex] + playerHitStat[playerIndex]; // at-bat value is combined value of strike out and out stats and hit stats

        if (atBatValue == 0) { // if no stats, were collected during the reading/parsing process, the calculated batting average will be set to 0

            return 0;
    
        }

        double battingAverage = playerHitStat[playerIndex] / (double)atBatValue; // divide the player hits by the at-bats

        return battingAverage;

    } // calculateBattingAverage

    public static double calculateOnBasePercentage(int[] playerHitStat, int[] playerWalkStat, int[] playerHitByPitchStat, int[] playerOutStat, int[] playerStrikeOutStat, int[] playerSacrificeStat, int playerIndex) {

        int onBasePercentageNumerator = playerHitStat[playerIndex] + playerWalkStat[playerIndex] + playerHitByPitchStat[playerIndex]; // on-base percentage is combined value of hits, walk, and hit by pitch

        if (onBasePercentageNumerator == 0) { // if no stats, were collected during the reading/parsing process, the calculated on base percentage will be set to 0

            return 0;

        }

        int plateAppearance = playerHitStat[playerIndex] + playerOutStat[playerIndex] + playerStrikeOutStat[playerIndex] + playerWalkStat[playerIndex] + playerHitByPitchStat[playerIndex] + playerSacrificeStat[playerIndex]; // plate value is any valid result
        
        double onBasePercentage = onBasePercentageNumerator / (double)plateAppearance; // formula for calculate on base percentage

        return onBasePercentage;

    } // calculateOnBasePercentage

    public static void resetTiedTempLeadersArray(boolean[] tiedTempLeadersArray, int playerArraySize) { // resets the temporary leader location array to all false

        for (int tiedTempLeaderIndex = 0; tiedTempLeaderIndex < playerArraySize; tiedTempLeaderIndex++) {
        
            tiedTempLeadersArray[tiedTempLeaderIndex] = false;
    
        }

    } // resetTiedTempLeadersArray

    public static int findLeaderInteger(int[] statArray, boolean[] tiedTempLeadersArray, int playerArraySize, boolean findSmallestValue) {

        int highestValue;
        if (findSmallestValue == false) {

            highestValue = statArray[0];
            for (int statArrayIndex = 1; statArrayIndex < playerArraySize; statArrayIndex++) { // finds highest value in array
                
                if (highestValue < statArray[statArrayIndex]) {
                    
                    highestValue = statArray[statArrayIndex];
                
                }

            }
            for (int statArrayIndex = 0; statArrayIndex < playerArraySize; statArrayIndex++) { // finds all ties
                
                if (statArray[statArrayIndex] == highestValue) {

                    tiedTempLeadersArray[statArrayIndex] = true;

                }

            }
        }
        else { // finds the smallest value in the stat array
            
            int smallestValue = statArray[0];
            for (int statArrayIndex = 1; statArrayIndex < playerArraySize; statArrayIndex++) { // finds smallest value in array
                
                if (smallestValue > statArray[statArrayIndex]) {  

                    smallestValue = statArray[statArrayIndex];

                }

            }
            for (int statArrayIndex = 0; statArrayIndex < playerArraySize; statArrayIndex++) { // finds all ties, if player(s) have smallest value, set the tied temp leader array to true
                
                if (statArray[statArrayIndex] == smallestValue) {
                    
                    tiedTempLeadersArray[statArrayIndex] = true;
                
                }
            
            }

            return smallestValue;
        }

        return highestValue;

    }

    public static double findLeaderDouble(double[] statArray, boolean[] tiedTempLeadersArray, int playerArraySize) {

        double highestValue = statArray[0];
        for (int statArrayIndex = 1; statArrayIndex < playerArraySize; statArrayIndex++) { // finds highest value in array
            
            if (highestValue < statArray[statArrayIndex]) { 
                
                highestValue = statArray[statArrayIndex];
            
            }

        }
        for (int statArrayIndex = 0; statArrayIndex < playerArraySize; statArrayIndex++) { // finds all ties, if player(s) have smallest value, set the tied temp leader array to true            
            
            if (statArray[statArrayIndex] == highestValue) {
                
                tiedTempLeadersArray[statArrayIndex] = true;
            
            }

        }
        
        return highestValue;

    }

    public static void outputTieLeader(String[] playerNameArray, boolean[] tiedTempLeadersArray, int playerArraySize) {

        int numOccurrences = 0;
        for (int tiedTempLeaderIndex = 0; tiedTempLeaderIndex < playerArraySize; tiedTempLeaderIndex++) {

            if (tiedTempLeadersArray[tiedTempLeaderIndex] == true) { // counts the number of occurences of the highest value
                
                numOccurrences++;
            
            }

        }
        for (int tiedTempLeaderIndex = 0; tiedTempLeaderIndex < playerArraySize; tiedTempLeaderIndex++) {
            
            if (tiedTempLeadersArray[tiedTempLeaderIndex] == true) {
                
                System.out.print(playerNameArray[tiedTempLeaderIndex]);
            
            }
            if (numOccurrences > 1 && tiedTempLeadersArray[tiedTempLeaderIndex] == true) { // adds comma if there are ties for the leaders
                
                System.out.print(", ");
                
                numOccurrences--;
            }

        }

        resetTiedTempLeadersArray(tiedTempLeadersArray, playerArraySize); // resets the leader array for the next stat array
        numOccurrences = 0;

    }

    public static void outputPlayerDisplay(String[] playerNameArray, double[] battingAverageStat, double[] onBasePercentageStat, int[] playerHitStat, int[] playerWalkStat, int[] playerStrikeOutStat, int[] playerHitByPitchStat, int playerIndex) { // displays player name and their respective stats

        System.out.println(playerNameArray[playerIndex]); // outputs the player name

        System.out.printf("BA: %.3f\n", battingAverageStat[playerIndex]); // the %.f3f rounds the double type to 3 decimals

        System.out.printf("OB%%: %.3f\n", onBasePercentageStat[playerIndex]); // the %.f3f rounds the double type to 3 decimals
        //System.out.println(onBasePercentageStat[playerIndex]);

        System.out.println("H: " + playerHitStat[playerIndex]); // outputs the player hit stat

        System.out.println("BB: " + playerWalkStat[playerIndex]); // outputs the walk stat

        System.out.println("K: " + playerStrikeOutStat[playerIndex]); // outputs the strike out stat

        System.out.println("HBP: " + playerHitByPitchStat[playerIndex]); // outputs the hit by pitch stat

        System.out.println(); // creates empty line

    } // outputPlayerDisplay

    public static void outputPlayerLeaders(String[] playerNameArray, double[] battingAverageStat, double[] onBasePercentageStat, int[] playerHitStat, int[] playerWalkStat, int[] playerStrikeOutStat, int[] playerHitByPitchStat, boolean[] tiedTempLeadersArray, int playerArraySize) {

        System.out.println("LEAGUE LEADERS");

        System.out.print("BA: "); // finds leader for batting average
        double battingAverageLeader = findLeaderDouble(battingAverageStat, tiedTempLeadersArray, playerArraySize); // finds
        outputTieLeader(playerNameArray, tiedTempLeadersArray, playerArraySize); // adjusts the output based on how many tied leaders there are, adds tied leaders to output
        System.out.printf(" %.3f\n", battingAverageLeader); // the %.f3f rounds the double type to 3 decimals

        System.out.print("OB%: "); // finds leader for on base percentage
        double onBasePercentageLeader = findLeaderDouble(onBasePercentageStat, tiedTempLeadersArray, playerArraySize);
        outputTieLeader(playerNameArray, tiedTempLeadersArray, playerArraySize); // adjusts the output based on how many tied leaders there are, adds tied leaders to output
        System.out.printf(" %.3f\n", onBasePercentageLeader); // the %.f3f rounds the double type to 3 decimals

        System.out.print("H: "); // finds leader for hit 
        int hitLeader = findLeaderInteger(playerHitStat, tiedTempLeadersArray, playerArraySize, false);
        outputTieLeader(playerNameArray, tiedTempLeadersArray, playerArraySize); // adjusts the output based on how many tied leaders there are, adds tied leaders to output
        System.out.println(" " + hitLeader); // the %.f3f rounds the double type to 3 decimals

        System.out.print("BB: "); // finds leader for hit 
        int walkLeader = findLeaderInteger(playerWalkStat, tiedTempLeadersArray, playerArraySize, false);
        outputTieLeader(playerNameArray, tiedTempLeadersArray, playerArraySize); // adjusts the output based on how many tied leaders there are, adds tied leaders to output
        System.out.println(" " + walkLeader); // the %.f3f rounds the double type to 3 decimals

        System.out.print("K: "); // finds leader for strikeout
        int strikeoutLeader = findLeaderInteger(playerStrikeOutStat, tiedTempLeadersArray, playerArraySize, true);
        outputTieLeader(playerNameArray, tiedTempLeadersArray, playerArraySize); // adjusts the output based on how many tied leaders there are, adds tied leaders to output
        System.out.println(" " + strikeoutLeader); // the %.f3f rounds the double type to 3 decimals

        System.out.print("HBP: "); // finds leader for strikeout
        int hitByPitchLeader = findLeaderInteger(playerHitByPitchStat, tiedTempLeadersArray, playerArraySize, false);
        outputTieLeader(playerNameArray, tiedTempLeadersArray, playerArraySize); // adjusts the output based on how many tied leaders there are, adds tied leaders to output
        System.out.println(" " + hitByPitchLeader); // the %.f3f rounds the double type to 3 decimals

    }   
    public static void main (String[] args) throws IOException {

        // Player Arrays
        // stores values of players such as their name and statistics
        String[] playerNameArray = new String[30];
        String[] playerStatsArray = new String[30];

        int[] playerHitStat = new int[30];
        int[] playerOutStat = new int[30];
        int[] playerStrikeOutStat = new int[30];
        int[] playerWalkStat = new int[30];
        int[] playerHitByPitchStat = new int[30];
        int[] playerSacrificeStat = new int[30];

        double[] battingAverageStat = new double[30];
        double[] onBasePercentageStat = new double[30];

        boolean[] tiedTempLeadersArray = new boolean[30];

        // - - - - - - - - - - - - - - - - - - - - - - - - - //

        String fileName = askInputFileName();

        File inputFile = new File(fileName); 
        FileInputStream inputFileStream = new FileInputStream(inputFile);
        Scanner scanFileLine = new Scanner(inputFile);

        System.out.println(); // create an empty line

        int playerIndex = 0; // keeps track of the index at which a player is stored in the parallel arrays
        if (inputFile.exists() == true) { // makes sure the input file actually exists

            while (scanFileLine.hasNextLine() == true) { // keeps scanning file until it reaches the end of the file

                readFileLine(scanFileLine, playerNameArray, playerStatsArray, playerIndex);

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

        // Closes the input file and the scanner
        inputFileStream.close();
        scanFileLine.close();

        // - - - - - - - - - - - - - - - - - - - - - - - - - // Output Area!
        
        int playerArraySize = playerIndex;
        for (int playerNameIndex = 0; playerNameIndex < playerArraySize; playerNameIndex++) { // loops through players read from file and output their stats to file

            outputPlayerDisplay(playerNameArray, battingAverageStat, onBasePercentageStat, playerHitStat, playerWalkStat, playerStrikeOutStat, playerHitByPitchStat, playerNameIndex);

        }

        outputPlayerLeaders(playerNameArray, battingAverageStat, onBasePercentageStat, playerHitStat, playerWalkStat, playerStrikeOutStat, playerHitByPitchStat, tiedTempLeadersArray, playerArraySize);

        // - - - - - - - - - - - - - - - - - - - - - - - - - // Test Area!
        /* 
        System.out.println("Name: " + playerNameArray[0]);
        System.out.println("Stats: " + playerStatsArray[0]);

        System.out.println("Hit: " + playerHitStat[0]);
        System.out.println("Strike: " + playerStrikeOutStat[0]);

        System.out.println("BA: " + battingAverageStat[0]);
        System.out.println("OB%: " + onBasePercentageStat[0]);
        */
    } // main

}
