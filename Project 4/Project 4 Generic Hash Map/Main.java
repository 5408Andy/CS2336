/* 
 * Project 4: Super Mario Sluggers with Hashmaps
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 11/09/2022
 * Class & Section: CS - 2366.003
 */

// Files
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// ArrayList
import java.util.ArrayList;

// Decimal Formatting
import java.text.DecimalFormat; // used to help format to 3 decimal points for floating point values


public class Main {  

    private static final String KEY_FILE = "keyfile.txt";
  
    private static final String[] STATS_SHORTHAND = {"BA", "OBP", "H", "W", "K", "P"};

    private static final String[] STATS_CATEGORY =  {"BATTING AVERAGE", "ON-BASE PERCENTAGE", "HITS", "WALKS", "STRIKEOUTS", "HIT BY PITCH"};  // titles of the leader stats

    public static void main(String[] args) throws IOException {

        // hash map for determining the type of stat based on the code
        GenericHashMap<String, String> keyLookup = new GenericHashMap<String, String>();

        //GenericHashMap<String, String> keyLookupCustum = new GenericHashMap<String, String>();

        // hash map for the away team 
        GenericHashMap<String, Player> awayTeamMap = new GenericHashMap<String, Player>();

        // hash map for the home team
        GenericHashMap<String, Player> homeTeamMap = new GenericHashMap<String, Player>();

        readKeyFile(keyLookup);
        
        readPlayByPlay(keyLookup, awayTeamMap, homeTeamMap);

        // array lists which hold the contents from the hash maps
        ArrayList<Player> awayTeam = extractAndAlphabetize(awayTeamMap);
        ArrayList<Player> homeTeam = extractAndAlphabetize(homeTeamMap);
        
        displayStats(awayTeam, false);

        System.out.println();

        displayStats(homeTeam, true);
        
        // combine the the teams from the hash maps putting them into one array list
        ArrayList<Player> combinedTeams = extractAndCombine(awayTeamMap, homeTeamMap);
        combinedTeams = sortStatsOfTeam(combinedTeams, false, "BA");

        // array lists which hold the leaders of each stat
        ArrayList<ArrayList<ArrayList<Player>>> leaderList = new ArrayList<ArrayList<ArrayList<Player>>>(); // created an array list which stores array lists of leaders for each stat
        sortAndFindLeaders(combinedTeams, leaderList);

        displayLeaders(leaderList);
        
        //System.out.println(awayTeamMap.get("Larry"));

    }
    
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    public static String askInputFileName() {

        Scanner inputFileName = new Scanner(System.in);

        System.out.println("Please input the filename for Super Mario Sluggers");
        String fileName = inputFileName.nextLine(); // asks user for input file name
        System.out.println("The file you inputed was \"" + fileName + "\".");
        
        inputFileName.close();

        return fileName;

    } // askInputFileName

    public static void readKeyFile(GenericHashMap<String, String> keyLookup) throws IOException {

        // file scanning and processing
        File inputFile = new File(KEY_FILE); 
        FileInputStream inputFileStream = new FileInputStream(inputFile);
        Scanner scanFileLine = new Scanner(inputFile);

        if (inputFile.exists() == true) { // makes sure the input file actually exists

            while (scanFileLine.hasNextLine() == true) { // keeps scanning file until it reaches the end of the file
                    
                parseKeyFile(keyLookup, scanFileLine);

            }

        }
        else { // file could not be opened

            System.out.println("\"" + KEY_FILE + "\" could not be opened!");

        }

        // Closes the input file and the scanner
        inputFileStream.close();
        scanFileLine.close();

    } // readKeyFile

    public static void parseKeyFile(GenericHashMap<String, String> keyLookup, Scanner scanFileLine) {

        String fileLine = scanFileLine.nextLine();

        String typeOfStat = fileLine.substring(fileLine.indexOf("## ") + 3, fileLine.indexOf(" ##")); // extract the type of stat from key file

        fileLine = scanFileLine.nextLine();
        
        while (fileLine.indexOf("## ") == -1 && fileLine.length() != 0) {
                    
            keyLookup.put(fileLine, typeOfStat); // stores the cases into the hash map
                    
            if (scanFileLine.hasNextLine() == true) { // make sure there is a next line

                fileLine = scanFileLine.nextLine();

            }
            else { // stops the loop if there is no next line

                break;

            }
                    
        }

    } // parseKeyFile

    public static void readPlayByPlay(GenericHashMap<String, String> keyLookup, GenericHashMap<String, Player> awayTeamMap, GenericHashMap<String, Player> homeTeamMap) throws IOException {

        String fileName = /*askInputFileName()*/"sample_playbyplay.txt";

        // file scanning and processing
        File inputFile = new File(fileName); 
        FileInputStream inputFileStream = new FileInputStream(inputFile);
        Scanner scanFileLine = new Scanner(inputFile);

        if (inputFile.exists() == true) { // makes sure the input file actually exists

            while (scanFileLine.hasNextLine() == true) { // keeps scanning file until it reaches the end of the file
                    
                parsePlayByPlay(keyLookup, awayTeamMap, homeTeamMap, scanFileLine);

            }

        }
        else { // file could not be opened

            System.out.println("\"" + fileName + "\" could not be opened!");

        }

        // Closes the input file and the scanner
        inputFileStream.close();
        scanFileLine.close();

    } // readPLayerStats

    public static void parsePlayByPlay(GenericHashMap<String, String> keyLookup, GenericHashMap<String, Player> awayTeamMap, GenericHashMap<String, Player> homeTeamMap, Scanner scanFileLine) {

        String fileLine = scanFileLine.nextLine();

        String typeTeam = new String();
        String playerName = new String();
        String playerCode = new String();

        // determine if away team or home tema
        if (fileLine.charAt(0) == 'A') { typeTeam = "A "; }
        else { typeTeam = "H "; }

        for (int stringIndex = fileLine.indexOf(typeTeam) + 2; stringIndex < fileLine.length(); stringIndex++) { // loop through each character of file line

            if (fileLine.charAt(stringIndex) != ' ') { // as long as the character is not a space, add character to player name
                
                playerName += fileLine.charAt(stringIndex);

            }
            else { // if the character is a space

                playerCode = fileLine.substring(stringIndex + 1, fileLine.length()); // get the substring of the player code after the space

                break; // break out of the

            }

        }
         
        if (typeTeam == "A ") { // if player is on the away team
            
            if (awayTeamMap.get(playerName) == null) { // if the player has not been added to the hash map

                //System.out.println(playerName + " " + keyLookup.get(playerCode)+"!");
                
                awayTeamMap.put(playerName, new Player(playerName, keyLookup.get(playerCode)));

            }
            else { // if the player has already been added to the hash map

                awayTeamMap.get(playerName).incrementStat(keyLookup.get(playerCode));

            }

        }
        else { // if player is on the home team

            if (homeTeamMap.get(playerName) == null) { // if the player has not been added to the hash map

                homeTeamMap.put(playerName, new Player(playerName, keyLookup.get(playerCode)));

            }
            else { // if the player has already been added to the hash map

                homeTeamMap.get(playerName).incrementStat(keyLookup.get(playerCode));

            }

        }     

    } // parsePlayByPlay

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    public static boolean checkListInOrder(ArrayList<Player> arrayList, boolean leastToGreatest) {

        for (int arrayIndex = 0; arrayIndex < arrayList.size() - 1; arrayIndex++) {

            if (leastToGreatest == true && arrayList.get(arrayIndex).compareTo(arrayList.get(arrayIndex + 1)) > 0) {

                return false;

            }
            else if (leastToGreatest == false && arrayList.get(arrayIndex).compareTo(arrayList.get(arrayIndex + 1)) < 0) {
                
                return false;

            }

        }
        
        return true;

    } // checkListInOrder

    public static ArrayList<Player> sortStatsOfTeam(ArrayList<Player> arrayList, boolean leastToGreatest, String desiredStat) {

        // when using the compareTo inside player, make sure it is comparing whatever the desired stat is 
        Player tempPlayer = new Player();
        tempPlayer.setDesiredStat(desiredStat);
        
        int arrayIndex = 0;
        while (checkListInOrder(arrayList, leastToGreatest) == false) {
           
            if (leastToGreatest == true) { // least to greatest
                
                if (arrayList.size() > arrayIndex + 1 && arrayList.get(arrayIndex).compareTo(arrayList.get(arrayIndex + 1)) > 0) { // if earlier element is bigger than later element
                        
                    arrayList.add(arrayList.get(arrayIndex)); // append the element to end of list
                    arrayList.remove(arrayIndex); // delete earlier instance of element
    
                }

            }
            else { // greatest to least
                
                if (arrayList.size() > arrayIndex + 1 && arrayList.get(arrayIndex).compareTo(arrayList.get(arrayIndex + 1)) < 0) { // if earlier element is bigger than later element
                        
                    arrayList.add(arrayList.get(arrayIndex)); // append the element to end of list
                    arrayList.remove(arrayIndex); // delete earlier instance of element
    
                }

            }

            if (arrayList.size() == arrayIndex + 1) { // resets the index once it is one spot away from max size

                arrayIndex = 0;

            }
            else { // continue the index count
                
                arrayIndex++;
            
            }

        }

        return arrayList;

    } // sortStatsOfTeam

    public static ArrayList<Player> extractAndAlphabetize(GenericHashMap<String, Player> receivedTeamMap) {
        
        ArrayList<Player> statArrayList = new ArrayList<Player>();

        // extract values from hash map and put into array list
        for (Player playerData : receivedTeamMap.values()) {

            statArrayList.add(playerData);

        }
        
        // when using the compareTo inside player, make sure it is comparing string names of player object
        Player tempPlayer = new Player();
        tempPlayer.setDesiredStat("Name");
        
        statArrayList = sortStatsOfTeam(statArrayList, true, "Name");

        return statArrayList;

    } // extractAndAlphabetize

    public static ArrayList<Player> extractAndCombine(GenericHashMap<String, Player> receivedTeamMap, GenericHashMap<String, Player> receivedTeamMap2) {

        ArrayList<Player> statArrayList = new ArrayList<Player>();

        // extract values from 1st hash map and put into array list
        for (Player playerData : receivedTeamMap.values()) {

            statArrayList.add(playerData);

        }

        // extract values from  2nd hash map and put into array list
        for (Player playerData : receivedTeamMap2.values()) {

            statArrayList.add(playerData);

        }

        return statArrayList;

    } // extractAndCombine

    public static void displayStats(ArrayList<Player> statArrayList, boolean homeOrAway) {

        if (homeOrAway) { System.out.println("HOME"); } 
        else { System.out.println("AWAY"); }

        for (int arrayIndex = 0; arrayIndex < statArrayList.size(); arrayIndex++) { // loop through array list and output all players of that team

            System.out.println(statArrayList.get(arrayIndex));

        }

    } // displayStats

    public static ArrayList<ArrayList<ArrayList<Player>>> sortAndFindLeaders(ArrayList<Player> playerList, ArrayList<ArrayList<ArrayList<Player>>> leaderList) {
        
        Player tempPlayer = new Player();

        for (int arrayIndex = 0; arrayIndex < STATS_SHORTHAND.length; arrayIndex++) { // loops through the stats that need to find leaders for
           
            if (STATS_SHORTHAND[arrayIndex] == "BA" || STATS_SHORTHAND[arrayIndex] == "OBP") { // sorts and finds leaders of double value stats such as batting average and on base percentage

                // sort double value stats greatest to least
                tempPlayer.setDesiredStat(STATS_SHORTHAND[arrayIndex]);
                sortStatsOfTeam(playerList, false, STATS_SHORTHAND[arrayIndex]);

                leaderList.add(findLeadersDouble(playerList,  STATS_SHORTHAND[arrayIndex])); // finds the 1st, 2nd, and 3rd of stat

            }
            else if (STATS_SHORTHAND[arrayIndex] != "K") { // sorts and finds leaders of integer value stats that relay on the greater the value the better such as hits and walks
               
                 // sort double value stats greatest to least
                 tempPlayer.setDesiredStat(STATS_SHORTHAND[arrayIndex]);
                 sortStatsOfTeam(playerList, false, STATS_SHORTHAND[arrayIndex]);
 
                 leaderList.add(findLeadersInteger(playerList,  STATS_SHORTHAND[arrayIndex])); // finds the 1st, 2nd, and 3rd of stat

            }
            else { // sorts and finds leaders of integer value stats that relay on the smaller the value the better

                 // sort double value stats greatest to least
                 tempPlayer.setDesiredStat(STATS_SHORTHAND[arrayIndex]);
                 sortStatsOfTeam(playerList, true, STATS_SHORTHAND[arrayIndex]);
 
                 leaderList.add(findLeadersInteger(playerList,  STATS_SHORTHAND[arrayIndex])); // finds the 1st, 2nd, and 3rd of stat

            }

        }

        return leaderList;

    } // sortAndFindLeaders

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