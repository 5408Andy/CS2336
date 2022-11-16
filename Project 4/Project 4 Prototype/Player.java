/* 
 * Project 4: Super Mario Sluggers with Hashmaps
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 11/09/2022
 * Class & Section: CS - 2366.003
 */

import java.text.DecimalFormat; // used to help format to 3 decimal points for floating point values

public class Player implements Comparable<Player> {

    private static String desiredStat;

    // member varaibles that should be received when object is created
    private String playerName;

    // member variables for storing the stats of a player
    private int playerHitStat;
    private int playerOutStat;
    private int playerStrikeOutStat;
    private int playerWalkStat;
    private int playerHitByPitchStat;
    private int playerSacrificeStat;
    private int playerErrorStat;

    Player() { // default constructor

        playerName = "Nameless";

        playerHitStat = 0;
        playerOutStat = 0;
        playerStrikeOutStat = 0;
        playerWalkStat = 0;
        playerHitByPitchStat = 0;
        playerSacrificeStat = 0;
        playerErrorStat = 0;
        
    } // Player - Constructor

    Player(String playerNameReceived, String playerCodeReceived) {  // stores the received strings into the player's member variables and parses the stats

        playerName = playerNameReceived;

        playerHitStat = 0;
        playerOutStat = 0;
        playerStrikeOutStat = 0;
        playerWalkStat = 0;
        playerHitByPitchStat = 0;
        playerSacrificeStat = 0;
        playerErrorStat = 0;

        incrementStat(playerCodeReceived);
        
    } // Player - Constructor

    // - - - Methods - - - //

    public int calculateAtBat() {

        int atBatValue = playerStrikeOutStat + playerOutStat + playerHitStat + playerErrorStat;

        return atBatValue;

    } // calculateAtBat

    public double calculateBattingAverage() {

        int atBatValue = calculateAtBat();

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

    public void incrementStat(String playerDataReceived) {

        if (playerDataReceived.compareTo("OUTS") == 0) {

            incPlayerOutStat();

        }
        else if (playerDataReceived.compareTo("STRIKEOUT") == 0) {

            incPlayerStrikeOutStat();

        }
        else if (playerDataReceived.compareTo("HITS") == 0) {
            
            incPlayerHitStat();

        }
        else if (playerDataReceived.compareTo("WALK") == 0) {

            incPlayerWalkStat();

        }
        else if (playerDataReceived.compareTo("SACRIFICE") == 0) {

            incPlayerSacrificeStat();

        }
        else if (playerDataReceived.compareTo("HIT BY PITCH") == 0) {

            incPlayerSacrificeStat();

        }
        else { // if the string received is "ERRORS"

            incPlayerErrorStat();

        }

    } // incrementStat

    public int compareTo(Player playerDataReceived) {

        if (desiredStat == "Name") { // Name Comparison

            int compareValue = playerName.compareTo(playerDataReceived.getPlayerName());
            
            return compareValue;

        }
        else if (desiredStat == "BA") { // Batting Average Comparison
            
            Double currentPlayerValue = calculateBattingAverage();
            Double receivedPlayerValue = playerDataReceived.calculateBattingAverage();
            
            int compareValueDouble = currentPlayerValue.compareTo(receivedPlayerValue); 
            int compareValueStr = playerName.compareTo(playerDataReceived.getPlayerName());
            
            if (compareValueDouble == 0 && compareValueStr > 0) { // ensures that if there is a tie in value, then they will be placed alphabetically
                
                return -1;

            }

            return compareValueDouble;
        }
        else if (desiredStat == "OBP") { // On Base Percentage Comparison

            Double currentPlayerValue = calculateOnBasePercentage();
            Double receivedPlayerValue = playerDataReceived.calculateOnBasePercentage();

            int compareValueDouble = currentPlayerValue.compareTo(receivedPlayerValue); 
            int compareValueStr = playerName.compareTo(playerDataReceived.getPlayerName());

            if (compareValueDouble == 0 && compareValueStr > 0) { // ensures that if there is a tie in value, then they will be placed alphabetically

                return -1;

            }

            return compareValueDouble; 

        }
        else if (desiredStat == "H") { // Hit Comparison
            
            Integer currentPlayerValue = playerHitStat;
            Integer receivedPlayerValue = playerDataReceived.getPlayerHitStat();
            
            int compareValueInteger = currentPlayerValue.compareTo(receivedPlayerValue); 
            int compareValueStr = playerName.compareTo(playerDataReceived.getPlayerName());

            if (compareValueInteger == 0 && compareValueStr > 0) { // ensures that if there is a tie in value, then they will be placed alphabetically

                return -1;

            }

            return compareValueInteger; 

        }
        else if (desiredStat == "W") { // Walk Comparison

            Integer currentPlayerValue = playerWalkStat;
            Integer receivedPlayerValue = playerDataReceived.getPlayerWalkStat();

            int compareValueInteger = currentPlayerValue.compareTo(receivedPlayerValue); 
            int compareValueStr = playerName.compareTo(playerDataReceived.getPlayerName());

            if (compareValueInteger == 0 && compareValueStr > 0) { // ensures that if there is a tie in value, then they will be placed alphabetically

                return -1;

            }

            return compareValueInteger; 

        }
        else if (desiredStat == "K") { // Strike Out Comparison

            Integer currentPlayerValue = playerStrikeOutStat;
            Integer receivedPlayerValue = playerDataReceived.getPlayerStrikeOutStat();

            int compareValueInteger = currentPlayerValue.compareTo(receivedPlayerValue); 
            int compareValueStr = playerName.compareTo(playerDataReceived.getPlayerName());

            if (compareValueInteger == 0 && compareValueStr > 0) { // ensures that if there is a tie in value, then they will be placed alphabetically

                return 1;

            }

            return compareValueInteger; 

        }
        else if (desiredStat == "P") { // Hit By Pitch Comparison

            Integer currentPlayerValue = playerHitByPitchStat;
            Integer receivedPlayerValue = playerDataReceived.getPlayerHitByPitchStat();

            int compareValueInteger = currentPlayerValue.compareTo(receivedPlayerValue); 
            int compareValueStr = playerName.compareTo(playerDataReceived.getPlayerName());

            if (compareValueInteger == 0 && compareValueStr > 0) { // ensures that if there is a tie in value, then they will be placed alphabetically

                return -1;

            }

            return compareValueInteger; 

        }
        
        return 404; // something went horribly wrong for it to return this value

    }

    public String formatDecimal(double desiredStatDouble) {

        DecimalFormat formatNum = new DecimalFormat("0.000"); // using decimal formatting to properly output the amount of decimals I want in a string

        return formatNum.format(desiredStatDouble);

    } //formatDecimal

    @Override
    public String toString() {

        return playerName + "\t" + calculateAtBat() + "\t" + playerHitStat + "\t" + playerWalkStat + "\t" + playerStrikeOutStat + "\t" + playerHitByPitchStat + "\t" + playerSacrificeStat + "\t" + formatDecimal(calculateBattingAverage()) + "\t" + formatDecimal(calculateOnBasePercentage());

    } // toString

    // - - - Getter Methods - - - //

    // getter method for desired stat

    public String getDesiredStat() { return desiredStat; }

    // getter method for player name

    public String getPlayerName() { return playerName; }
    
    // getter methods for player stats

    public int getPlayerHitStat() { return playerHitStat; } 

    public int getPlayerOutStat() { return playerOutStat; }

    public int getPlayerStrikeOutStat() { return playerStrikeOutStat; }

    public int getPlayerWalkStat() { return playerWalkStat; }

    public int getPlayerHitByPitchStat() { return playerHitByPitchStat; }

    public int getPlayerSacrificeStat() { return playerSacrificeStat; }

    // - - - Setter Methods - - - //

    public void setDesiredStat(String desiredStatReceived) {

        desiredStat = desiredStatReceived;

    } // setDesiredStat

    public int getCertainStatInteger(String desiredStat) { // returns specific stat based on the string parameter

        int valueOfStat = 0;

        if (desiredStat == "H") { valueOfStat = getPlayerHitStat(); }

        else if (desiredStat == "O") { valueOfStat = getPlayerOutStat(); }

        else if (desiredStat == "K") { valueOfStat = getPlayerStrikeOutStat(); }

        else if (desiredStat == "W") { valueOfStat = getPlayerWalkStat(); }

        else if (desiredStat == "P") { valueOfStat = getPlayerHitByPitchStat(); }

        else if (desiredStat == "S") { valueOfStat = getPlayerSacrificeStat(); }

        return valueOfStat;

    } // getCertainStatInteger

    public double getCertainStatDouble(String desiredStat) { // returns specific stat based on the string parameter

        double valueOfStat = 0;

        if (desiredStat == "BA") {

            valueOfStat = calculateBattingAverage();

        }   
        else if (desiredStat == "OBP") {

            valueOfStat = calculateOnBasePercentage();

        }

        return valueOfStat;

    } // getCertainStatDouble

    // Setter or "Adder" Methods

    public void incPlayerHitStat() { playerHitStat++; }

    public void incPlayerOutStat() { playerOutStat++; }

    public void incPlayerStrikeOutStat() { playerStrikeOutStat++; }

    public void incPlayerWalkStat() { playerWalkStat++; }

    public void incPlayerHitByPitchStat() { playerHitByPitchStat++; }

    public void incPlayerSacrificeStat() { playerSacrificeStat++; }

    public void incPlayerErrorStat() { playerErrorStat++; }
    
}