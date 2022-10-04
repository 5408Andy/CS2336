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

import java.util.ArrayList; // arraylists in this program are only used to help store data from linked list, according to a PIAZZA post, Proffessor Smith has allowed us to use arraylists to help with storing leaders
import java.text.DecimalFormat; // used to help format to 3 decimal points for floating point values

public class Main {

    public static void main (String[] args) throws IOException {

        // linked list store players from file
        LinkList playerList = new LinkList(1);
        
        // get the input file for processing
        String fileName = askInputFileName() /*"sample_stats2.txt"*/; // PLEASE CHANGE LATER PLEASE CHANGE LATER PLEASE CHANGE LATER PLEASE CHANGE LATER PLEASE CHANGE LATER PLEASE CHANGE LATER PLEASE CHANGE LATER

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

        // - - - Testing - - - // // - - - Testing - - - // // - - - Testing - - - // // - - - Testing - - - // // - - - Testing - - - // // - - - Testing - - - // // - - - Testing - - - // // - - - Testing - - - //
        
        /* 
        
        - ArrayLists in this program are only used to help store data from linked list, according to a PIAZZA post, Proffessor Smith has allowed us to use arraylists to help with storing leaders
        - For each stat, I first sorted the LinkList of players into the order from greatest to least or least to greatest depending on how the stat would declare them a leader
        - Then took up the first 3 elements a.k.a the leaders of the stat and store them into an ArrayList; 
        - With each ArrayList for each stat, I stored it into a ArrayList which stores ArrayLists for easier coding
        
        */ 

        ArrayList<ArrayList<Player>> leaderList = new ArrayList<ArrayList<Player>>();

        // Batting Average
        LinkList playerBattingAverageList = playerList.sortPlayersByStat_GreatestToLeast(playerList, "BA", true);
        ArrayList<Player> battingAverageLeaders = playerBattingAverageList.findLeaders(playerBattingAverageList);
        leaderList.add(battingAverageLeaders);

        System.out.println("Player\tA-B\tH\tW\tK\tP\tS\tBA\tOBP");
        System.out.println("----------------------------------------------------------------------");
        
        playerList.printStatsRecursively(playerList.getHeadNode());

        // On Base Percentage
        LinkList playerOnBasePercentageList = playerList.sortPlayersByStat_GreatestToLeast(playerList, "OBP", true);
        ArrayList<Player> onBasePercentageLeaders = playerOnBasePercentageList.findLeaders(playerOnBasePercentageList);
        leaderList.add(onBasePercentageLeaders);

        // Hit
        LinkList playerHitList = playerList.sortPlayersByStat_GreatestToLeast(playerList, "H", false);
        ArrayList<Player> hitLeaders = playerOnBasePercentageList.findLeaders(playerHitList);
        leaderList.add(hitLeaders);

        // Walk
        LinkList playerWalkList = playerList.sortPlayersByStat_GreatestToLeast(playerList, "W", false);
        ArrayList<Player> walkLeaders = playerOnBasePercentageList.findLeaders(playerWalkList);
        leaderList.add(walkLeaders);

        // Strike Out
        LinkList playerStrikeOutList = playerList.sortPlayersByStat_LeastToGreatest(playerList, "K");
        ArrayList<Player> strikeOutLeaders = playerOnBasePercentageList.findLeaders(playerStrikeOutList);
        leaderList.add(strikeOutLeaders);

        // Hit By Pitch
        LinkList playerHitByPitchList = playerList.sortPlayersByStat_GreatestToLeast(playerList, "P", false);
        ArrayList<Player> hitByPitchLeaders = playerOnBasePercentageList.findLeaders(playerHitByPitchList);
        leaderList.add(hitByPitchLeaders );

        // Probably going to create a minor mini array for just holding the leaders and extracting values from linked list

        // - - - Testing - - - // // - - - Testing - - - // // - - - Testing - - - // // - - - Testing - - - // // - - - Testing - - - // // - - - Testing - - - // // - - - Testing - - - // // - - - Testing - - - //
        
        for (int arrayIndex = 0; arrayIndex < leaderList.size(); arrayIndex++) {

            System.out.println(outputLeadersDouble(leaderList.get(arrayIndex), "BA"));

        }

        System.out.println(outputLeadersDouble(battingAverageLeaders, "BA"));

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

    public static String outputLeadersDouble(ArrayList<Player> desiredStatArrayList, String desiredStat) { 

        String desiredStatString = new String();

        try {

            if (desiredStatArrayList.isEmpty() == false) {

                double valueOfFirstLeader = desiredStatArrayList.get(0).getCertainStatDouble(desiredStat);
                desiredStatString += formatDecimal(valueOfFirstLeader) + "\t";
        
                if (desiredStatArrayList.isEmpty() == false && valueOfFirstLeader == desiredStatArrayList.get(0).getCertainStatDouble(desiredStat)) {

                    desiredStatString += desiredStatArrayList.get(0).getPlayerName();
                    desiredStatArrayList.remove(0);

                }
                if (desiredStatArrayList.isEmpty() == false && valueOfFirstLeader == desiredStatArrayList.get(0).getCertainStatDouble(desiredStat)) {

                    desiredStatString += ", " + desiredStatArrayList.get(0).getPlayerName();
                    desiredStatArrayList.remove(0);

                }
                if (desiredStatArrayList.isEmpty() == false && valueOfFirstLeader == desiredStatArrayList.get(0).getCertainStatDouble(desiredStat)) {

                    desiredStatString += ", " + desiredStatArrayList.get(0).getPlayerName();
                    desiredStatArrayList.remove(0);

                    return desiredStatString; // 3 leaders have been determined

                }

            }

            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
            
            if (desiredStatArrayList.isEmpty() == false) {

                double valueOfSecondLeader = desiredStatArrayList.get(0).getCertainStatDouble(desiredStat);
                desiredStatString += "\n" + formatDecimal(valueOfSecondLeader) + "\t";

                if (desiredStatArrayList.isEmpty() == false && valueOfSecondLeader == desiredStatArrayList.get(0).getCertainStatDouble(desiredStat)) {

                    desiredStatString += desiredStatArrayList.get(0).getPlayerName();
                    desiredStatArrayList.remove(0);

                }
                if (desiredStatArrayList.isEmpty() == false && valueOfSecondLeader == desiredStatArrayList.get(0).getCertainStatDouble(desiredStat)) {

                    desiredStatString += ", " + desiredStatArrayList.get(0).getPlayerName();
                    desiredStatArrayList.remove(0);

                    return desiredStatString; // 3 leaders have been determined

                }

            }
            
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
 
            if (desiredStatArrayList.isEmpty() == false) {

                double valueOfThirdLeader = desiredStatArrayList.get(0).getCertainStatDouble(desiredStat);
                desiredStatString += "\n" + formatDecimal(valueOfThirdLeader) + "\t";

                if (desiredStatArrayList.isEmpty() == false) {

                    desiredStatString += desiredStatArrayList.get(0).getPlayerName();

                    return desiredStatString; // 3 leaders have been determined

                }

            }

        }
        catch (NullPointerException e) {

            System.out.println("Exception thrown : " + e); // possible that the array might at least 3 players
        
        }
        
        return desiredStatString;
    
    } // outputLeadersDouble

    public static String formatDecimal(double desiredStatDouble) {

        DecimalFormat formatNum = new DecimalFormat("0.000"); // using decimal formatting to properly output the amount of decimals I want in a string

        return formatNum.format(desiredStatDouble);

    } //formatDecimal

}