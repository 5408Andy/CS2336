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

    private static final ArrayList<String> STATS_SHORTHAND = new ArrayList<String>() { // short hand version of the leader stats to help with processing
            
        {

            add("BA");
            add("OBP");
            add("H");
            add("W");
            add("K");
            add("P");
        
        }

    };

    private static final ArrayList<String> STATS_CATEGORY = new ArrayList<String>() { // titles of the leader stats
            
        {

            add("BATTING AVERAGE");
            add( "ON-BASE PERCENTAGE");
            add("HITS");
            add("WALKS");
            add("STRIKEOUTS");
            add("HIT BY PITCH");
            
        }

    };

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
        
        // print out the stats of each player from the file
        playerList.printStatsRecursively(playerList.getHeadNode());

        
        /* 
        
        - LEADER OUTPUT SECTION
        - ArrayLists in this program are only used to help store data from linked list, according to a PIAZZA post, Proffessor Smith has allowed us to use arraylists to help with storing leaders
        - For each stat, I first sorted the LinkList of players into the order from greatest to least or least to greatest depending on how the stat would declare them a leader
        - Then took up the first 3 elements a.k.a the leaders of the stat and store them into an ArrayList; 
        - With each ArrayList for each stat, I stored it into a ArrayList which stores ArrayLists for easier coding
        
        */ 

        ArrayList<ArrayList<ArrayList<Player>>> leaderList = new ArrayList<ArrayList<ArrayList<Player>>>(); // created an array list which stores array lists of leaders for each stat
        
        sortAndFindLeaders(playerList, leaderList); // sort through the link list (USING LINK LIST METHODS) and find the top 3 in each stat and store them into an array list, then but those leader array lists into one big arraylist

        displayLeaders(leaderList); // display the league leaders

        // TEST AREA! //
        /* 
        playerList.sortPlayers_Alpha();
        playerList.sortPlayersByStat_GreatestToLeast(playerList, "W", false);

        // print the players and their stats recursively
        System.out.println("\nPlayer\tA-B\tH\tW\tK\tP\tS\tBA\tOBP");
        System.out.println("----------------------------------------------------------------------");
        
        // print out the stats of each player from the file
        playerList.printStatsRecursively(playerList.getHeadNode());
        
        ArrayList<ArrayList<Player>> testArray = playerList.findLeaders(playerList, "W");

        System.out.println();

        for (int i = 0; i < testArray.size(); i++) {

            for (int j = 0; j < testArray.get(i).size(); j++) {

                System.out.print(testArray.get(i).get(j).getPlayerName() + " ");

            }

            System.out.println();

        }
        */

    } // Main

    public static String askInputFileName() {

        Scanner inputFileName = new Scanner(System.in);

        System.out.println("Please input the filename for Super Mario Sluggers");
        String fileName = inputFileName.nextLine(); // asks user for input file name
        System.out.println("The file you inputed was \"" + fileName + "\".");
        
        inputFileName.close();

        return fileName;

    } // askInputFileName

    public static void readFileLine(Scanner scanFileLine, LinkList<Player> playerList, int playerNum) {

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

    /* 
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
    
    public static String outputLeadersInteger(ArrayList<Player> desiredStatArrayList, String desiredStat) { 

        String desiredStatString = new String();

        try {

            if (desiredStatArrayList.isEmpty() == false) { // 1st leader determiner

                int valueOfFirstLeader = desiredStatArrayList.get(0).getCertainStatInteger(desiredStat);
                desiredStatString += valueOfFirstLeader + "\t";
        
                if (desiredStatArrayList.isEmpty() == false && valueOfFirstLeader == desiredStatArrayList.get(0).getCertainStatInteger(desiredStat)) {

                    desiredStatString += desiredStatArrayList.get(0).getPlayerName();
                    desiredStatArrayList.remove(0);

                }
                if (desiredStatArrayList.isEmpty() == false && valueOfFirstLeader == desiredStatArrayList.get(0).getCertainStatInteger(desiredStat)) {

                    desiredStatString += ", " + desiredStatArrayList.get(0).getPlayerName();
                    desiredStatArrayList.remove(0);

                }
                if (desiredStatArrayList.isEmpty() == false && valueOfFirstLeader == desiredStatArrayList.get(0).getCertainStatInteger(desiredStat)) {

                    desiredStatString += ", " + desiredStatArrayList.get(0).getPlayerName();
                    desiredStatArrayList.remove(0);

                    return desiredStatString; // 3 leaders have been determined

                }

            }

            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
            
            if (desiredStatArrayList.isEmpty() == false) { // 2nd leader determiner

                int valueOfSecondLeader = desiredStatArrayList.get(0).getCertainStatInteger(desiredStat);
                desiredStatString += "\n" + valueOfSecondLeader + "\t";

                if (desiredStatArrayList.isEmpty() == false && valueOfSecondLeader == desiredStatArrayList.get(0).getCertainStatInteger(desiredStat)) {

                    desiredStatString += desiredStatArrayList.get(0).getPlayerName();
                    desiredStatArrayList.remove(0);

                }
                if (desiredStatArrayList.isEmpty() == false && valueOfSecondLeader == desiredStatArrayList.get(0).getCertainStatInteger(desiredStat)) {

                    desiredStatString += ", " + desiredStatArrayList.get(0).getPlayerName();
                    desiredStatArrayList.remove(0);

                    return desiredStatString; // 3 leaders have been determined

                }

            }
            
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
 
            if (desiredStatArrayList.isEmpty() == false) { // 3rd leader determiner

                int valueOfThirdLeader = desiredStatArrayList.get(0).getCertainStatInteger(desiredStat);
                desiredStatString += "\n" + valueOfThirdLeader + "\t";

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
    */
    public static ArrayList<ArrayList<ArrayList<Player>>> sortAndFindLeaders(LinkList<Player> playerList, ArrayList<ArrayList<ArrayList<Player>>> leaderList) {

        for (int arrayIndex = 0; arrayIndex < STATS_SHORTHAND.size(); arrayIndex++) { // loops through the stats that need to find leaders for
            
            playerList.sortPlayers_Alpha();
           
            if (STATS_SHORTHAND.get(arrayIndex) == "BA" || STATS_SHORTHAND.get(arrayIndex) == "OBP") { // sorts and finds leaders of double value stats such as batting average and on base percentage
                
                playerList.sortPlayersByStat_GreatestToLeast(playerList, STATS_SHORTHAND.get(arrayIndex), true);
                leaderList.add(playerList.findLeadersDouble(playerList, STATS_SHORTHAND.get(arrayIndex))); // finds the 1st, 2nd, and 3rd of stat

            }
            else if (STATS_SHORTHAND.get(arrayIndex) != "K") { // sorts and finds leaders of integer value stats that relay on the greater the value the better such as hits and walks
               
                playerList.sortPlayersByStat_GreatestToLeast(playerList, STATS_SHORTHAND.get(arrayIndex), false);
                leaderList.add(playerList.findLeadersInteger(playerList, STATS_SHORTHAND.get(arrayIndex))); // finds the 1st, 2nd, and 3rd of stat

            }
            else { // sorts and finds leaders of integer value stats that relay on the smaller the value the better
        
                playerList.sortPlayersByStat_LeastToGreatest(playerList, STATS_SHORTHAND.get(arrayIndex));
                leaderList.add(playerList.findLeadersInteger(playerList, STATS_SHORTHAND.get(arrayIndex))); // finds the 1st, 2nd, and 3rd of stat

            }

        }

        return leaderList;

    }
    
    public static void displayLeaders(ArrayList<ArrayList<ArrayList<Player>>> leaderList) {

        System.out.println("\nLEAGUE LEADERS");

        String statOutput = new String();

        double previousValueDouble = 0;
        int previousValueInteger = 0;

        for (int statIndex = 0; statIndex < leaderList.size(); statIndex++) {

            System.out.println(STATS_CATEGORY.get(statIndex));

            for (int leaderIndex = 0; leaderIndex < leaderList.get(statIndex).size(); leaderIndex++) {

                for (int playerIndex = 0; playerIndex < leaderList.get(statIndex).get(leaderIndex).size(); playerIndex++) {

                    if (STATS_SHORTHAND.get(statIndex) == "BA" || STATS_SHORTHAND.get(statIndex) == "OBP") {
                        
                        if (leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getCertainStatDouble(STATS_SHORTHAND.get(statIndex)) != previousValueDouble) {

                            statOutput += formatDecimal(leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getCertainStatDouble(STATS_SHORTHAND.get(statIndex))) + " ";

                        }

                        statOutput += leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getPlayerName() + ", ";

                        previousValueDouble = leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getCertainStatDouble(STATS_SHORTHAND.get(statIndex));

                    }
                    else {

                        if (leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getCertainStatInteger(STATS_SHORTHAND.get(statIndex)) != previousValueInteger) {

                            statOutput += leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getCertainStatInteger(STATS_SHORTHAND.get(statIndex)) + " ";

                        }

                        statOutput += leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getPlayerName() + ", ";

                        previousValueInteger = leaderList.get(statIndex).get(leaderIndex).get(playerIndex).getCertainStatInteger(STATS_SHORTHAND.get(statIndex));

                    }
        
                }
            
                statOutput = statOutput.substring(0, statOutput.length() - 2) + "\n";                

            }

            System.out.println(statOutput);
            statOutput = "";

        }

    } // displayLeaders

    public static String formatDecimal(double desiredStatDouble) {

        DecimalFormat formatNum = new DecimalFormat("0.000"); // using decimal formatting to properly output the amount of decimals I want in a string

        return formatNum.format(desiredStatDouble);

    } //formatDecimal

}
