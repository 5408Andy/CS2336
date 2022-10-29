/* 
 * Project 3: Antideriviative Calculator
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/27/2022
 * Class & Section: CS - 2366.003
 */

 // Files
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// Formatting
import java.text.DecimalFormat; // used to help format to 3 decimal points for floating point values

// Storage 
import java.util.ArrayList; // helps store the multiple binary trees

public class Main {
    
    public static void main(String[] args) throws IOException {

        // array list of binary trees
        ArrayList<BinTree<Term>> listOfIntegrals = new  ArrayList<BinTree<Term>>();

        // get the input file for processing
        String fileName = "sample_integrals.txt"/*askInputFileName()*/;

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

                listOfIntegrals.add(readFileLine(scanFileLine));

            }

        }
        else { // file could not be opened

            System.out.println("\"" + fileName + "\" could not be opened!");

        }

        // Closes the input file and the scanner
        inputFileStream.close();
        scanFileLine.close();

        // - - - Testing - - - //

        ArrayList<ArrayList<Term>> orderedEquations = new ArrayList<ArrayList<Term>>();

        for (int arrayIndex = 0; arrayIndex < listOfIntegrals.size(); arrayIndex++) {

            ArrayList<Term> orderedTerms = listOfIntegrals.get(arrayIndex).traverseInOrder();

            orderedEquations.add(orderedTerms);

        }
        
        String outputEqu = "";
        for (int arrayIndex = 0; arrayIndex < orderedEquations.size(); arrayIndex++) {

            outputEqu = "";

            for (int i = 0; i <  orderedEquations.get(arrayIndex).size(); i++) {

                outputEqu = outputEqu + orderedEquations.get(arrayIndex).get(i) + " + ";
                 
                while (outputEqu.indexOf(" + -") != -1) {
                    
                    StringBuffer stringBuffer = new StringBuffer(outputEqu);
                    stringBuffer.replace(outputEqu.indexOf(" + -"), outputEqu.indexOf(" + -") + 4, " - ");
                    outputEqu = stringBuffer.toString();
                    
                }

            }

            System.out.println(outputEqu + "C \n");

        }

        //orderedEquation.get(0).
        
        /* 
        listOfIntegrals.get(0).traverseInOrder();

        System.out.println();

        listOfIntegrals.get(1).traverseInOrder();

        System.out.println();

        listOfIntegrals.get(2).traverseInOrder();

        System.out.println();

        listOfIntegrals.get(3).traverseInOrder();
        */

    }

    public static String askInputFileName() {

        Scanner inputFileName = new Scanner(System.in);

        System.out.println("Please input the filename for Anti-Derivative Calculator");
        String fileName = inputFileName.nextLine(); // asks user for input file name
        System.out.println("The file you inputed was \"" + fileName + "\".");
        
        inputFileName.close();

        return fileName;

    } // askInputFileName

    public static String reverseString(String receivedString) {

        String reversedString = "";

        // loops through the received string and reverses the string
        for (int stringIndex = 0; stringIndex < receivedString.length(); stringIndex++) { // loops through each character in string

            reversedString = receivedString.charAt(stringIndex) + reversedString; // add characters in front of string

        }

        return reversedString;

    } // reverseString

    public static boolean containsDigit(String receivedString) {

        // loops through a received string and checks if there is a digit in the string
        for(int stringIndex = 0; stringIndex < receivedString.length(); stringIndex++) {

            if (Character.isDigit(receivedString.charAt(stringIndex)) == true) {

               return true;

            }

        }

        return false;

    } // containsDigit

    public static BinTree<Term> readFileLine(Scanner scanFileLine) {

        BinTree<Term> integralTree = new BinTree<Term>();

        String fileLine = scanFileLine.nextLine(); // scans line in the file
        String coefficientStr = "";
        String exponentStr = "";

        // reference integers to remove a node
        int indexRemoval1 = 0;
        int indexRemoval2 = 0;


        if (fileLine.length() != 0) { // does not process blank lines

            // remove white spaces and dx from file line
            fileLine = fileLine.replace(" ","");
            fileLine = fileLine.replace("dx","");

            if (fileLine.indexOf("|") == 0) { // if there is the existance of the "pipe" and it is at at the beginning

                // collect boundary information

                fileLine = fileLine.substring(1);

            }

            // polynomials with exponents greater than 1
            while (fileLine.indexOf("x^") != -1) {

                coefficientStr = "";
                exponentStr = "";

                int stringIndex = fileLine.indexOf("x^"); // start the index at the occurrence of the "x^"
                while (stringIndex >= 0) {

                    indexRemoval1 = stringIndex;

                    if (Character.isDigit(fileLine.charAt(stringIndex)) == true) { // if index of char is digit, then add to string

                        coefficientStr += fileLine.charAt(stringIndex);

                    }
                    else if (fileLine.charAt(stringIndex) == '-') { // if index of char is minus operator, then add to string and break

                        coefficientStr += fileLine.charAt(stringIndex);
                        break;

                    }
                    else if (fileLine.charAt(stringIndex) == '+') { // if index of char is plus operator, then break

                        break;

                    }

                    stringIndex--;

                }
                coefficientStr = reverseString(coefficientStr);

                boolean digitOccurred = false;
                stringIndex = fileLine.indexOf("x^"); // start the index at the occurrence of the "x^"
                while (stringIndex < fileLine.length()) {

                    if (Character.isDigit(fileLine.charAt(stringIndex)) == true) {

                        digitOccurred = true;
                        exponentStr += fileLine.charAt(stringIndex);

                    }
                    else if (fileLine.charAt(stringIndex) == '-') {

                        if (digitOccurred == true) { break; }

                        exponentStr += fileLine.charAt(stringIndex);

                    }
                    else if (fileLine.charAt(stringIndex) == '+' ) {

                        break;

                    }

                    stringIndex++;
                    indexRemoval2 = stringIndex;

                }

                Term newTerm = new Term(Double.parseDouble(coefficientStr), Integer.parseInt(exponentStr)); // create new term and turn strings into number values
                integralTree.insertData(newTerm); // insert new term into binary tree
                fileLine = fileLine.substring(0, indexRemoval1) + fileLine.substring(indexRemoval2, fileLine.length());

            }
            
            // polynomials with exponents equal to 1
            while (fileLine.indexOf("x") != -1) {
                
                coefficientStr = "";

                int stringIndex = fileLine.indexOf("x"); // start the index at the occurrence of the "x^"
                while (stringIndex >= 0) {

                    if (Character.isDigit(fileLine.charAt(stringIndex)) == true) { // if index of char is digit, then add to string

                        coefficientStr += fileLine.charAt(stringIndex);

                    }
                    else if (fileLine.charAt(stringIndex) == '-') { // if index of char is minus operator, then add to string and break

                        coefficientStr += fileLine.charAt(stringIndex);
                        break;

                    }
                    else if (fileLine.charAt(stringIndex) == '+') { // if index of char is plus operator, then break

                        break;

                    }

                    stringIndex--;
                    indexRemoval1 = stringIndex;
                    

                }
                coefficientStr = reverseString(coefficientStr);

                Term newTerm2 = new Term(Double.parseDouble(coefficientStr), 1); // create new term and turn strings into number values
                integralTree.insertData(newTerm2); // insert new term into binary tree
                fileLine = fileLine.substring(0, indexRemoval1) + fileLine.substring(fileLine.indexOf("x") + 1, fileLine.length());

            }
            
            // numbers with no variables
            while (containsDigit(fileLine) == true) {

                coefficientStr = "";

                int stringIndex = 0;
                boolean digitOccurred = false;
                while (stringIndex < fileLine.length()) { // loops through the rest of the file line
                    
                    if (fileLine.charAt(stringIndex) == '-') { // stores a negative into term if there is one

                        coefficientStr += fileLine.charAt(stringIndex);

                    }
                    else if (Character.isDigit(fileLine.charAt(stringIndex)) == true) { // adds the character if it is a digit

                        coefficientStr += fileLine.charAt(stringIndex);
                        digitOccurred = true;

                    }
                    else if ((fileLine.charAt(stringIndex) == '+' || fileLine.charAt(stringIndex) == '-') && digitOccurred == true) { // breaks out of the loop once it encounters another operator

                        break;

                    }

                    indexRemoval1 = stringIndex;
                    stringIndex++;

                }

                Term newTerm3 = new Term(Double.parseDouble(coefficientStr), 0); // create new term and turn strings into number values
                integralTree.insertData(newTerm3); // insert new term into binary tree
                fileLine = fileLine.substring(indexRemoval1 + 1);

            }

        }

        return integralTree;

    } // readFileLine

}   
