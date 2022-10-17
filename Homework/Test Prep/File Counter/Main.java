/* 
 * Test Preparation
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/16/2022
 * Class & Section: CS - 2366.003
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Main {
    
    public static void main(String[] args) throws IOException{

         // get the input file for processing
        String fileName = askInputFileName();

        // file scanning and processing
        File inputFile = new File(fileName); 
        FileInputStream inputFileStream = new FileInputStream(inputFile);
        Scanner scanFileLine = new Scanner(inputFile);

        // counters
        int[] characterCount = { 0 };
        int[] wordCount= { 0 };
        int[] lineCount = { 0 };


        if (inputFile.exists() == true) { // makes sure the input file actually exists

            while (scanFileLine.hasNextLine() == true) { // keeps scanning file until it reaches the end of the file

                readFileLine(scanFileLine, characterCount, lineCount, wordCount); // reads and stores the players into the playerList linked list

            }

        }
        else { // file could not be opened

            System.out.println("\"" + fileName + "\" could not be opened!");

        }
        
        // Closes the input file and the scanner
        inputFileStream.close();
        scanFileLine.close();

        System.out.println("Lines: "+ lineCount[0]);
        System.out.println("Words: "+ wordCount[0]);
        System.out.println("Characters: "+ characterCount[0]);

    }

    public static String askInputFileName() {

        Scanner inputFileName = new Scanner(System.in);

        System.out.println("Please input the filename for File Counter");
        String fileName = inputFileName.nextLine(); // asks user for input file name
        System.out.println("The file you inputed was \"" + fileName + "\".");
        
        inputFileName.close();

        return fileName;

    } // askInputFileName

    public static void readFileLine(Scanner scanFileLine, int[] characterCount, int[] lineCount, int[] wordCount) {

        // strings to store information from file
        String fileLine = scanFileLine.nextLine(); // scans line in the file

        lineCount[0]++;
        wordCount[0]++;

        for (int fileLineIndex = 0; fileLineIndex < fileLine.length(); fileLineIndex++) {

            characterCount[0]++;

            if (fileLine.charAt(fileLineIndex) ==  ' ' || fileLine.charAt(fileLineIndex) ==  '\n') {

                wordCount[0]++;

            }

        }

    } // readFileLine

}
