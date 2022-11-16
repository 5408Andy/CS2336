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

import java.util.ArrayList; // arraylists in this program are only used to help print out (THEY DO NOT MODIFY), according to a PIAZZA post, Proffessor Smith has allowed us to use arraylists to help with storing leaders
import java.text.DecimalFormat; // used to help format to 3 decimal points for floating point values

public class Main {
    
    private static final int STATS_SIZE = 6;
     
    private static final String[] STATS_CATEGORY =  {"BATTING AVERAGE", "ON-BASE PERCENTAGE", "HITS", "WALKS", "STRIKEOUTS", "HIT BY PITCH"};  // titles of the leader stats
            
    private static final String[] STATS_SHORTHAND = {"BA", "OBP", "H", "W", "K", "P"}; // short hand version of leader stats
    
    private static Player tempPlayer = new Player(); // temporary player to determine the stat the compareTo needs to run
    
    public static void main (String[] args) throws IOException {
        
        // linked list store players from file
        LinkList<Player> playerList = new LinkList<Player>();
        
        // get the input file for processing
        String fileName = askInputFileName();

        // file scanning and processing
        File inputFile = new File(fileName); 
        FileInputStream inputFileStream = new FileInputStream(inputFile);
        Scanner scanFileLine = new Scanner(inputFile);

        System.out.println(); // create an empty line
        
        /* 
        - FILE EXTRACTION AND CREATION OF LINK LIST SECTION
        - Read through each line and processed and appended to link list
        - GENERICS HAVE BEEN IMPLEMENTED
        */ 

        if (inputFile.exists() == true) { // makes sure the input file actually exists

            while (scanFileLine.hasNextLine() == true) { // keeps scanning file until it reaches the end of the file

                readFileLine(scanFileLine, playerList); // reads and stores the players into the playerList linked list

            }

        }
        else { // file could not be opened

            System.out.println("\"" + fileName + "\" could not be opened!");

        }
        
        // Closes the input file and the scanner
        inputFileStream.close();
        scanFileLine.close();

        /* 
        - PLAYERS OUTPUT SECTION
        - Removed nodes with the same player and combined data in the link list 
        - Sorted the nodes alphabetically using a psuedo "unnamed" sort
        - GENERICS HAVE BEEN IMPLEMENTED
        */ 

        // combine stats of players with same names and remove the extra 
        tempPlayer.setDesiredStat("Name");
        playerList.checkForMultipleEntries();

        // sort the players into alphabetical order
        tempPlayer.setDesiredStat("Name");
        playerList.sortPlayers(true);

        // display stats extracted from file
        displayPlayerStats(playerList);

        // sort through the link list (USING LINK LIST METHODS) and find the top 3 in each stat and store them into an array list, then but those leader array lists into one big arraylist
        ArrayList<ArrayList<ArrayList<Player>>> leaderList = new ArrayList<ArrayList<ArrayList<Player>>>(); // created an array list which stores array lists of leaders for each stat

        /* 
        - LEADER OUTPUT SECTION
        - ArrayLists in this program are only used to help store data from linked list, according to a PIAZZA post, Proffessor Smith has allowed us to use arraylists to help with storing leaders
        - For each stat, I first sorted the LinkList of players into the order from greatest to least or least to greatest depending on how the stat would declare them a leader
        - Took the leaders from each list including ties; 
        - I created an array list for 1st, 2nd, and 3rd, I store the leader array lists into a stat array list which then I once again store those stats into one big array list
        - I put everything into array list so I can just iterate though the data instead of writing it out.
        */ 

        sortAndFindLeaders(playerList, leaderList); // sort the link list to find leaders then extract them into array list for processing

        displayLeaders(leaderList); // display the league leaders of each stat

    } // Main

    public static String askInputFileName() {

        Scanner inputFileName = new Scanner(System.in);

        System.out.println("Please input the filename for Super Mario Sluggers");
        String fileName = inputFileName.nextLine(); // asks user for input file name
        System.out.println("The file you inputed was \"" + fileName + "\".");
        
        inputFileName.close();

        return fileName;

    } // askInputFileName

    public static void readFileLine(Scanner scanFileLine, LinkList<Player> playerList) {

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
            playerList.appendPlayer(new Player(playerName, playerStats));

        }

    } // readFileLine
     
    public static void displayPlayerStats(LinkList<Player> playerList) { // display player stats extracted from file

        // print the players and their stats recursively
        System.out.println("Player\tA-B\tH\tW\tK\tP\tS\tBA\tOBP");
        System.out.println("----------------------------------------------------------------------");
        
        // print out the stats of each player from the file
        playerList.printStatsRecursively(playerList.getHeadNode()); 

    } // displayPlayerStats()

    public static ArrayList<ArrayList<ArrayList<Player>>> sortAndFindLeaders(LinkList<Player> playerList, ArrayList<ArrayList<ArrayList<Player>>> leaderList) {
    
        for (int arrayIndex = 0; arrayIndex < STATS_SIZE; arrayIndex++) { // loops through the stats that need to find leaders for
           
            if (STATS_SHORTHAND[arrayIndex] == "BA" || STATS_SHORTHAND[arrayIndex] == "OBP") { // sorts and finds leaders of double value stats such as batting average and on base percentage
                
                // sort alphabetically
                tempPlayer.setDesiredStat("Name");
                playerList.sortPlayers(true);

                // sort double value stats greatest to least
                tempPlayer.setDesiredStat(STATS_SHORTHAND[arrayIndex]);
                playerList.sortPlayers(false);

                //System.out.println("\n" + STATS_SHORTHAND[arrayIndex]);
                //displayPlayerStats(playerList);

                leaderList.add(findLeadersDouble(playerList.outputDataIntoArrayList(),  STATS_SHORTHAND[arrayIndex])); // finds the 1st, 2nd, and 3rd of stat

            }
            else if (STATS_SHORTHAND[arrayIndex] != "K") { // sorts and finds leaders of integer value stats that relay on the greater the value the better such as hits and walks
               
                // sort alphabetically
                tempPlayer.setDesiredStat("Name");
                playerList.sortPlayers(true);

                // sort double value stats greatest to least
                tempPlayer.setDesiredStat(STATS_SHORTHAND[arrayIndex]);
                playerList.sortPlayers(false);

                //System.out.println("\n" + STATS_SHORTHAND[arrayIndex]);
                //displayPlayerStats(playerList);

                leaderList.add(findLeadersInteger(playerList.outputDataIntoArrayList(),  STATS_SHORTHAND[arrayIndex])); // finds the 1st, 2nd, and 3rd of stat

            }
            else { // sorts and finds leaders of integer value stats that relay on the smaller the value the better
                
                // sort alphabetically
                tempPlayer.setDesiredStat("Name");
                playerList.sortPlayers(true);

                // sort double value stats least to greatest
                tempPlayer.setDesiredStat(STATS_SHORTHAND[arrayIndex]);
                playerList.sortPlayers(true);

                //System.out.println("\n" + STATS_SHORTHAND[arrayIndex]);
                //displayPlayerStats(playerList);

                leaderList.add(findLeadersInteger(playerList.outputDataIntoArrayList(),  STATS_SHORTHAND[arrayIndex])); // finds the 1st, 2nd, and 3rd of stat

            }

        }

        return leaderList;

    } // sortAndFindLeaders

    public static ArrayList<ArrayList<Player>> findLeadersDouble(ArrayList<Player> sortedDoubleList, String desiredStat) {  // arraylists in this program are only used to help store data from linked list, according to a PIAZZA post, Proffessor Smith has allowed us to use arraylists to help with storing leaders

        ArrayList<ArrayList<Player>> desiredStatArrayList = new ArrayList<ArrayList<Player>>(); // stores all the 1st, 2nd, and 3rd leaders

        ArrayList<Player> firstLeaders = new ArrayList<Player>();

        double tiedStatCheckFirst = (sortedDoubleList.get(0).getCertainStatDouble(desiredStat));
        
        int numOfPlayersInFirst = 0;
        
        int arrayIndex = 0;

        while(arrayIndex < sortedDoubleList.size() && sortedDoubleList.get(arrayIndex).getCertainStatDouble(desiredStat) == tiedStatCheckFirst) { // extract 1st leaders including ties

            firstLeaders.add(sortedDoubleList.get(arrayIndex));
            
            numOfPlayersInFirst++;

            arrayIndex++;
            
        }

        desiredStatArrayList.add(firstLeaders);
         
        if (numOfPlayersInFirst >= 3 || arrayIndex == sortedDoubleList.size()) {
            
           return desiredStatArrayList; // more than 3 leaders in first so return 

        }

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

        ArrayList<Player> secondLeaders = new ArrayList<Player>();

        double tiedStatCheckSecond = (sortedDoubleList.get(arrayIndex).getCertainStatDouble(desiredStat));
        
        int numOfPlayersInSecond = 0;

        while(arrayIndex < sortedDoubleList.size() && sortedDoubleList.get(arrayIndex).getCertainStatDouble(desiredStat) == tiedStatCheckSecond) { // extract 2nd leaders including ties

            secondLeaders.add(sortedDoubleList.get(arrayIndex));
            
            numOfPlayersInSecond++;

            arrayIndex++;
            
        }

        desiredStatArrayList.add(secondLeaders);

        if (numOfPlayersInSecond + numOfPlayersInFirst >= 3 || arrayIndex == sortedDoubleList.size()) {
            
            return desiredStatArrayList; // less than 3 leaders in first
 
        }

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
        ArrayList<Player> thirdLeaders = new ArrayList<Player>();

        double tiedStatCheckThird = (sortedDoubleList.get(arrayIndex).getCertainStatDouble(desiredStat));

        while(arrayIndex < sortedDoubleList.size() && sortedDoubleList.get(arrayIndex).getCertainStatDouble(desiredStat) == tiedStatCheckThird) { // extract up to 3 players which should be the leader of each stat 

            thirdLeaders.add(sortedDoubleList.get(arrayIndex));

            arrayIndex++;
            
        }

        desiredStatArrayList.add(thirdLeaders);
        
        return desiredStatArrayList; // no ties for 1st or 2nd, so one leader per place

    } // findLeadersDouble

    public static ArrayList<ArrayList<Player>> findLeadersInteger(ArrayList<Player> sortedIntegerList, String desiredStat) {  // arraylists in this program are only used to help store data from linked list, according to a PIAZZA post, Proffessor Smith has allowed us to use arraylists to help with storing leaders

        ArrayList<ArrayList<Player>> desiredStatArrayList = new ArrayList<ArrayList<Player>>(); // stores all the 1st, 2nd, and 3rd leaders

        ArrayList<Player> firstLeaders = new ArrayList<Player>();

        int tiedStatCheckFirst = (sortedIntegerList.get(0).getCertainStatInteger(desiredStat));
        
        int numOfPlayersInFirst = 0;
        
        int arrayIndex = 0;

        while(arrayIndex < sortedIntegerList.size() && sortedIntegerList.get(arrayIndex).getCertainStatInteger(desiredStat) == tiedStatCheckFirst) { // extract 1st leaders including ties

            firstLeaders.add(sortedIntegerList.get(arrayIndex));
            
            numOfPlayersInFirst++;

            arrayIndex++;
            
        }

        desiredStatArrayList.add(firstLeaders);
         
        if (numOfPlayersInFirst >= 3 || arrayIndex == sortedIntegerList.size()) {
            
           return desiredStatArrayList; // more than 3 leaders in first so return 

        }

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

        ArrayList<Player> secondLeaders = new ArrayList<Player>();

        int tiedStatCheckSecond = (sortedIntegerList.get(arrayIndex).getCertainStatInteger(desiredStat));
        int numOfPlayersInSecond = 0;

        while(arrayIndex < sortedIntegerList.size() && sortedIntegerList.get(arrayIndex).getCertainStatInteger(desiredStat) == tiedStatCheckSecond) { // extract 2nd leaders including ties

            secondLeaders.add(sortedIntegerList.get(arrayIndex));
            
            numOfPlayersInSecond++;

            arrayIndex++;
            
        }

        desiredStatArrayList.add(secondLeaders);

        if (numOfPlayersInSecond + numOfPlayersInFirst >= 3 || arrayIndex == sortedIntegerList.size()) {
            
            return desiredStatArrayList; // less than 3 leaders in first
 
        }
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

        ArrayList<Player> thirdLeaders = new ArrayList<Player>();

        int tiedStatCheckThird = (sortedIntegerList.get(arrayIndex).getCertainStatInteger(desiredStat));

        while(arrayIndex < sortedIntegerList.size() && sortedIntegerList.get(arrayIndex).getCertainStatInteger(desiredStat) == tiedStatCheckThird) { // extract up to 3 players which should be the leader of each stat 

            thirdLeaders.add(sortedIntegerList.get(arrayIndex));

            arrayIndex++;
            
        }

        desiredStatArrayList.add(thirdLeaders);
        
        return desiredStatArrayList; // no ties for 1st or 2nd, so one leader per place

    } // findLeadersInteger

    public static void displayLeaders(ArrayList<ArrayList<ArrayList<Player>>> leaderList) {

        System.out.println("\nLEAGUE LEADERS");

        String statOutput = new String();

        double previousValueDouble = 0;
        int previousValueInteger = -1;

        for (int statIndex = 0; statIndex < leaderList.size(); statIndex++) {

            System.out.println(STATS_CATEGORY[statIndex]);

            for (int leaderIndex = 0; leaderIndex < leaderList.get(statIndex).size(); leaderIndex++) {

                for (int playerIndex = 0; playerIndex < leaderList.get(statIndex).get(leaderIndex).size(); playerIndex++) {

                    if (STATS_SHORTHAND[statIndex] == "BA" || STATS_SHORTHAND[statIndex] == "OBP") {
                        
                        if (leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getCertainStatDouble(STATS_SHORTHAND[statIndex]) != previousValueDouble) { // outputs the value of the leader stat once

                            statOutput += formatDecimal(leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getCertainStatDouble(STATS_SHORTHAND[statIndex])) + "\t";

                        }

                        statOutput += leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getPlayerName(); // adds the player name to output

                        previousValueDouble = leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getCertainStatDouble(STATS_SHORTHAND[statIndex]); // remembers the previous player to keep track of how many players need to be pushed into a single line to represent ties

                    }
                    else {

                        if (leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getCertainStatInteger(STATS_SHORTHAND[statIndex]) != previousValueInteger) { // outputs the value of the leader stat once

                            statOutput += leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getCertainStatInteger(STATS_SHORTHAND[statIndex]) + "\t";

                        }

                        statOutput += leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getPlayerName(); // adds the player name to output

                        previousValueInteger = leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getCertainStatInteger(STATS_SHORTHAND[statIndex]); // // remembers the previous player to keep track of how many players need to be pushed into a single line to represent ties

                    }

                    if (playerIndex !=  leaderList.get(statIndex).get(leaderIndex).size() - 1) { // adds a comma if it is not at the last index before size

                        statOutput += ", ";

                    }
                    else { // if it is the end, add a new line character

                        statOutput += "\n";

                    }
    
                }

            }

            System.out.println(statOutput);
            
            // reset tracker variables - these help with structuring the stats on console
            previousValueDouble = -1;
            previousValueInteger = -1;
            statOutput = "";

        }

    } // displayLeaders

    public static String formatDecimal(double desiredStatDouble) {

        DecimalFormat formatNum = new DecimalFormat("0.000"); // using decimal formatting to properly output the amount of decimals I want in a string

        return formatNum.format(desiredStatDouble);

    } //formatDecimal

    
}
