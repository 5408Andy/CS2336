/* 
 * Project 4: Super Mario Sluggers with Hashmaps
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 11/09/2022
 * Class & Section: CS - 2366.003
 */

 // Hashmaps
import java.util.HashMap;

// Files
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// ArrayList
import java.util.ArrayList;

public class Main {  

    private static final String KEY_FILE = "keyfile.txt";
  
    public static void main(String[] args) throws IOException {

        // hash map for determining the type of stat based on the code
        HashMap<String, String> keyLookup = new HashMap<String, String>();

        // hash map for the away team 
        HashMap<String, Player> awayTeamMap = new HashMap<String, Player>();

        // hash map for the home team
        HashMap<String, Player> homeTeamMap = new HashMap<String, Player>();

        readKeyFile(keyLookup);

        readPlayByPlay(keyLookup, awayTeamMap, homeTeamMap);

        displayStats(extractAndAlphabetize(awayTeamMap), false);

        System.out.println();


        displayStats(extractAndAlphabetize(homeTeamMap), true);

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

    public static void readKeyFile(HashMap<String, String> keyLookup) throws IOException {

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

    public static void parseKeyFile(HashMap<String, String> keyLookup, Scanner scanFileLine) {

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

    public static void readPlayByPlay(HashMap<String, String> keyLookup, HashMap<String, Player> awayTeamMap, HashMap<String, Player> homeTeamMap) throws IOException {

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

    public static void parsePlayByPlay(HashMap<String, String> keyLookup, HashMap<String, Player> awayTeamMap, HashMap<String, Player> homeTeamMap, Scanner scanFileLine) {

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

    }

    public static ArrayList<Player> extractAndAlphabetize(HashMap<String, Player> receivedTeamMap) {

        ArrayList<Player> playerAlphabetized = new ArrayList<Player>();
        
        // extract values from hash map and put into array list
        receivedTeamMap.forEach((playerName, playerData) -> { 
        
            playerAlphabetized.add(playerData);

        });
        
        // when using the compareTo inside player, make sure it is comparing string names of player object
        Player tempPlayer = new Player();
        tempPlayer.setDesiredStat("Name");
         
        int arrayIndex = 0;
        while (checkListInOrder(playerAlphabetized, true) == false) {

            if (playerAlphabetized.size() > arrayIndex + 1) {

                if (playerAlphabetized.get(arrayIndex).compareTo(playerAlphabetized.get(arrayIndex + 1)) > 0) { // if earlier element is bigger than later element
                    
                    playerAlphabetized.add(playerAlphabetized.get(arrayIndex)); // append the element to end of list
                    playerAlphabetized.remove(arrayIndex); // delete earlier instance of element

                }

            }
            
            if (playerAlphabetized.size() == arrayIndex + 1) { // resets the index once it is one spot away from max size

                arrayIndex = 0;

            }
            else { // continue the index count
                
                arrayIndex++;
            
            }

        }
        
        return playerAlphabetized;

    }

    public static void displayStats(ArrayList<Player> statArrayList, boolean homeOrAway) {

        if (homeOrAway) {

            System.out.println("HOME");

        } else {

            System.out.println("AWAY");

        }

        for (int arrayIndex = 0; arrayIndex < statArrayList.size(); arrayIndex++) { // loop through array list and output all players of that team

            System.out.println(statArrayList.get(arrayIndex));

        }

    }

}