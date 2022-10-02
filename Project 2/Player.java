/* 
 * Project 2: Super Mario Sluggers Part 2 with Linked Lists and Classes
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 9/27/2022
 * Class & Section: CS - 2366.003
 */

public class Player {

    private int playerNum;

    // member varaibles that should be received when object is created
    private String playerName;
    private String playerStats;

    // member variables for storing the stats of a player
    private int playerHitStat;
    private int playerOutStat;
    private int playerStrikeOutStat;
    private int playerWalkStat;
    private int playerHitByPitchStat;
    private int playerSacrificeStat;

    Player() { // default constructor

        playerNum = 0;

        playerName = "Nameless";
        playerStats = "No Stats";

        playerHitStat = 0;
        playerOutStat = 0;
        playerStrikeOutStat = 0;
        playerWalkStat = 0;
        playerHitByPitchStat = 0;
        
    } // Player - Constructor

    Player(String playerNameReceived, String playerStatsReceived, int playerNumReceived) {  // stores the received strings into the player's member variables and parses the stats

        playerNum = playerNumReceived;

        playerName = playerNameReceived;
        playerStats = playerStatsReceived;

        playerHitStat = 0;
        playerOutStat = 0;
        playerStrikeOutStat = 0;
        playerWalkStat = 0;
        playerHitByPitchStat = 0;
        playerSacrificeStat = 0;

        parsePlayerStats(); // parses that playerStats string and stores the increments into their respective member variables
        
    } // Player - Constructor

    // - - - Methods - - - //

    private void parsePlayerStats() { 

        for (int statsIndex = 0; statsIndex < playerStats.length(); statsIndex++) { // loops through string/line of stats that the object has received

            // if character at index matches certain character paired with certain stat, the integer holding that stat will be incremented by 1

            if (playerStats.charAt(statsIndex) == 'H') { 

                playerHitStat++;

            }
            if (playerStats.charAt(statsIndex) == 'O') {

                playerOutStat++;

            }
            if (playerStats.charAt(statsIndex) == 'K') {

                playerStrikeOutStat++;

            }
            if (playerStats.charAt(statsIndex) == 'W') {

                playerWalkStat++;

            }
            if (playerStats.charAt(statsIndex) == 'P') {

                playerHitByPitchStat++;

            }
            if (playerStats.charAt(statsIndex) == 'S') {

                playerSacrificeStat++;

            }

        } // end of loop

    } // parsePlayerStats

    public double calculateBattingAverage() {

        int atBatValue = playerStrikeOutStat + playerOutStat + playerHitStat;

        if (atBatValue != 0) {

            double battingAverageStat = playerHitStat / (double)atBatValue; // divide the palyer hits by the at bats to get the batting average

            return battingAverageStat;

        }

        return 0; // since the at bat value was zero, we return 0 to prevent a divde by zero scenario

    } // calculateBattingAverage

    public double calculateOnBasePercentage() {

        int onBasePercentageNumerator = playerHitStat + playerWalkStat + playerHitByPitchStat; // calculate the numerator of on base percentage

        int plateAppearances = playerHitStat + playerOutStat + playerStrikeOutStat + playerWalkStat + playerHitByPitchStat + playerSacrificeStat; // calculate the denominator of on base percentage

        if (onBasePercentageNumerator != 0 && plateAppearances != 0) { 

            double onBasePercentageStat = onBasePercentageNumerator / (double)plateAppearances;

            return onBasePercentageStat;

        }

        return 0; // since either the onBasePercentageNumerator or plate appearances was equal to zero, we return 0 to prevent a divde by zero scenario

    } // calculateOnBasePercentage

    // - - - Getter Methods - - - //

    // getter method for player name

    public String getPlayerName() { return playerName; }
    
    // getter methods for player stats

    public int getPlayerHitStat() { return playerHitStat; } 

    public int getPlayerOutStat() { return playerOutStat; }

    public int getPlayerStrikeOutStat() { return playerStrikeOutStat; }

    public int getPlayerWalkStat() { return playerWalkStat; }

    public int getPlayerHitByPitchStat() { return playerHitByPitchStat; }

    public int getPlayerSacrificeStat() { return playerSacrificeStat; }

    public int getPlayerNum() { return playerNum; }

    // - - - Setter (Adder) Methods - - - //

    public void addPlayerHitStat(int playerHitStatReceived) { playerHitStat += playerHitStatReceived; }

    public void addPlayerOutStat(int playerOutStatReceived) { playerOutStat += playerOutStatReceived; }

    public void addPlayerStrikeOutStat(int playerStrikeOutStatReceived) { playerStrikeOutStat += playerStrikeOutStatReceived; }

    public void addPlayerWalkStat(int playerWalkStatReceived) { playerWalkStat += playerWalkStatReceived; }

    public void addPlayerHitByPitchStat(int playerHitByPitchStatReceived) { playerHitByPitchStat += playerHitByPitchStatReceived; }

    public void addPlayerSacrificeStat(int playerSacrificeStatReceived) { playerSacrificeStat += playerSacrificeStatReceived; }
    
}