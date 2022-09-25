/*
 * File Name: projectZeroC.cpp
 * Program Name: Project Zero C++ Implementation
 * Name:  Andy Nguyen
 * NetID: adn200004
 * Date: 8/24/2022
 * Class and Section: CS - 2336
 * - - - - - - - - - - - - - - - - - - - - - *
 * 
 */

#include <iostream> // used to take in inputs and outputs with cin and cout
#include <iomanip> // used to manipulate text such as setprecision
#include <string>   // used to store long array of characters
#include <fstream>  // used to read in inputs from file

using namespace std;

// Globals
string playerNameArray[30];
string playerStatsArray[30];

int playerHitStat[30];
int playerOutStat[30];
int playerStrikeOutStat[30];
int playerWalkStat[30];
int playerHitByPitchStat[30];
int playerSacrificeStat[30];

double battingAverageStat[30];
double onBasePercentageStat[30];

bool tiedTempLeadersArray[30];

int playerArraySize = 0;

string askInputFileName() { // method used to prompt user for input file, so program can analyze players

    string inputFileName;
    string yesOrNo;

    // asks user to input filename
    cout << "Please input the filename for Super Mario Sluggers" << endl;
    cin >> inputFileName;
    
    cout << "The file you inputed was \"" << inputFileName << "\"." << endl;
    cout << "Is this correct? Type 'y' or 'n'." << endl; // asks user to check name of input file
    cin >> yesOrNo;

    // if the user types no, allows user to retype file name
    while (yesOrNo == "n" || yesOrNo == "no") { // 

        cout << "Please retype your file name." << endl;
        cin >> inputFileName;
        
        cout << "The file you inputted was \"" << inputFileName << "\"." << endl;
        cout << "Is this correct? Type 'y' or 'n'." << endl;
        cin >> yesOrNo;
        
    }
    
    return inputFileName;

} // askInputFileName

int readFileLine(ifstream &inputFile, int &playerIndex) { // reads in string from given file input

    string fileLine;
    string playerName;
    string playerStats;

    if (inputFile) { // checks if the file can be read

        if (getline(inputFile, fileLine)) { // reads the first line of the file

            if (fileLine.length() != 0) { // does not process blank lines

                for (int fileLineIndex = 0; fileLineIndex < static_cast<int>(fileLine.length()); fileLineIndex++) {

                    if(fileLine.at(fileLineIndex) == ' ') {
                        
                        playerName = fileLine.substr(0, fileLineIndex);
                        playerStats = fileLine.substr(fileLineIndex + 1, fileLine.length());

                    }

                }

                // stores extracted names and stats into parallel arrays
                playerNameArray[playerIndex] = playerName;
                playerStatsArray[playerIndex] = playerStats;

            }

        }

        return 1; // file could be opened

    }

    return 0; // file could not be opened

} // readFileLine

void parsePlayerStats(string playerStatsLine, int playerIndex) {

    for (int playerStatsLineIndex = 0; playerStatsLineIndex < static_cast<int>(playerStatsLine.length()); playerStatsLineIndex++) { // if character from players stat line matches with certain statistical char, its occurrence will be counted

        if (playerStatsLine.at(playerStatsLineIndex) == 'H') {                
            
            playerHitStat[playerIndex]++;
            
        }
        if (playerStatsLine.at(playerStatsLineIndex) == 'O') {
        
            playerOutStat[playerIndex]++;

        }
        if (playerStatsLine.at(playerStatsLineIndex ) == 'K') {

            playerStrikeOutStat[playerIndex]++;
        
        }
        if (playerStatsLine.at(playerStatsLineIndex) == 'W') {
    
            playerWalkStat[playerIndex]++;

        }
        if (playerStatsLine.at(playerStatsLineIndex) == 'P') {
            
            playerHitByPitchStat[playerIndex]++;
            
        }
        if (playerStatsLine.at(playerStatsLineIndex) == 'S') {
                
            playerSacrificeStat[playerIndex]++;
        
        }

    }

    return;

} // parsePlayerStats

int findLeaderInteger(int statArray[], bool findSmallestValue) { // used to find the highest value or smallest value in an array if it is a integer data type 

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
        for (int statArrayIndex = 0; statArrayIndex < playerArraySize; statArrayIndex++) { // finds all ties
            if (statArray[statArrayIndex] == smallestValue) {
                tiedTempLeadersArray[statArrayIndex] = true;
            }
        }
        return smallestValue;
    }
    return highestValue;

} // findLeadersInteger

double findLeaderDouble(double statArray[30]) { // used to find the highest value in an array if it is a double data type

    double highestValue = statArray[0];   
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

    return highestValue;

} // findLeaderDouble

double calculateBattingAverage(int playerIndex) {

    int atBatValue = playerStrikeOutStat[playerIndex] + playerOutStat[playerIndex] + playerHitStat[playerIndex]; // at-bat value is combined value of strike out and out stats and hit stats

    if (atBatValue == 0) {

        return 0;

    }

    double battingAverage = playerHitStat[playerIndex] / (double)atBatValue; // divide the player hits by the at-bats

    return battingAverage;

} // calculateBattingAverage

double calculateOnBasePercentage(int playerIndex) {

    int onBasePercentageNumerator = playerHitStat[playerIndex] + playerWalkStat[playerIndex] + playerHitByPitchStat[playerIndex]; // on-base percentage is combined value of hits, walk, and hit by pitch

    if (onBasePercentageNumerator == 0) {

        return 0;

    }

    int plateAppearance = playerHitStat[playerIndex] + playerOutStat[playerIndex] + playerStrikeOutStat[playerIndex] + playerWalkStat[playerIndex] + playerHitByPitchStat[playerIndex] + playerSacrificeStat[playerIndex]; // plate value is any valid result
    
    double onBasePercentage = onBasePercentageNumerator / (double)plateAppearance;

    return onBasePercentage;

} // calculateOnBasePercentage

double roundThousandths(double calculatedValue) { // round to 3 decimals

    if (calculatedValue < 0) {

        return 0;

    }

    double adjustValue = (int)(calculatedValue * 1000 + .5);

    return (double)adjustValue / 1000;

} // roundThoursandths

void outputPlayerDisplay(int playerIndex) { // method which outputs the stats of a player

    cout << playerNameArray[playerIndex] << endl;
    
    cout << "BA: " << fixed << setprecision(3) << roundThousandths(battingAverageStat[playerIndex]) << endl;

    cout << "OB%: " << fixed << setprecision(3) << roundThousandths(onBasePercentageStat[playerIndex]) << endl;
    
    cout << "H: " << playerHitStat[playerIndex] << endl;

    cout << "BB: " << playerWalkStat[playerIndex] << endl;

    cout << "K: " << playerStrikeOutStat[playerIndex] << endl;

    cout << "HBP: " << playerHitByPitchStat[playerIndex] << endl;

    cout << endl;

    return; 

} // outputPlayerDisplay

void resetTiedTempLeadersArray() { // resets the temporary leader location array to all false

    for (int tiedTempLeaderIndex = 0; tiedTempLeaderIndex < playerArraySize; tiedTempLeaderIndex++) {
        
        tiedTempLeadersArray[tiedTempLeaderIndex] = false;

    }

    return;

} // resetTiedTempLeadersArray

void outputTieLeaders() { // adds commas to list based on the amount of tied leaders

    int numOccurrences = 0;
    for (int tiedTempLeaderIndex = 0; tiedTempLeaderIndex < playerArraySize; tiedTempLeaderIndex++) {
        if (tiedTempLeadersArray[tiedTempLeaderIndex] == true) { // counts the number of occurences of the highest value
            numOccurrences++;
        }
    }
    for (int tiedTempLeaderIndex = 0; tiedTempLeaderIndex < playerArraySize; tiedTempLeaderIndex++) {
        if (tiedTempLeadersArray[tiedTempLeaderIndex] == true) {
            cout << playerNameArray[tiedTempLeaderIndex];
        }
        if (numOccurrences > 1 && tiedTempLeadersArray[tiedTempLeaderIndex] == true) { // adds comma if there are ties for the leaders
            cout << ", ";
            numOccurrences--;
        } 
    }

    resetTiedTempLeadersArray(); // resets the leader array for the next stat array
    numOccurrences = 0;

    return;

} // outputTieLeaders

void outputPlayerLeaders() {

    cout << "LEAGUE LEADERS" << endl;

    cout << "BA: ";
    double battingAverageLeader = findLeaderDouble(battingAverageStat);
    outputTieLeaders();
    cout << " " << battingAverageLeader << endl;

    cout << "OB%: ";
    double onBasePercentageLeader = findLeaderDouble(onBasePercentageStat);
    outputTieLeaders();
    cout << " " << onBasePercentageLeader << endl;

    cout << "H: ";
    int hitLeader = findLeaderInteger(playerHitStat, false);
    outputTieLeaders();
    cout << " " << hitLeader << endl;

    cout << "BB: ";
    int walkLeader = findLeaderInteger(playerWalkStat, false);
    outputTieLeaders();
    cout << " " << walkLeader << endl;

    cout << "K: ";
    int strikeoutLeader = findLeaderInteger(playerStrikeOutStat, true);
    outputTieLeaders();
    cout << " " << strikeoutLeader << endl;

    cout << "HBP: ";
    int hitByPitchLeader = findLeaderInteger(playerHitByPitchStat, false);
    outputTieLeaders();
    cout << " " << hitByPitchLeader << endl;

    return;

} // outputPlayerLeadersFile

int main() {

    // INPUT FILE Section

    ifstream inputFileMain;
    inputFileMain.open(askInputFileName());
    
    string playerStats;
    string playerName;
    string fileLine;

    int playerIndex = 0; // created index to traverse through the array
    while (!(inputFileMain.eof())) {
        
        int readValue = readFileLine(inputFileMain, playerIndex);

        if (playerNameArray[playerIndex].empty()) { // skips blank lines

            continue;

        }

        if (readValue == 0) {
            
            cout << "Opening file has failed..." << endl;
            break;

        }

        parsePlayerStats(playerStatsArray[playerIndex], playerIndex);
        battingAverageStat[playerIndex] = calculateBattingAverage(playerIndex);  
        onBasePercentageStat[playerIndex] = calculateOnBasePercentage(playerIndex);
        playerIndex++;



    }

    inputFileMain.close(); // closes the file

    playerArraySize = playerIndex;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
   
    // OUTPUT Section

    cout << endl;

    for (int playerNameIndex = 0; playerNameIndex < playerArraySize; playerNameIndex++) { // loops through players read from file and output their stats to file
        
        outputPlayerDisplay(playerNameIndex);

    }

    outputPlayerLeaders();

    // - - - - - - - - - - - - - - - - - // Test Area
    
    /*
    cout << endl;
    for (int i = 0; i < 30; i++) {

        cout << i << " " << playerNameArray[i] << endl;

    }
    */

} // main