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
        ArrayList<BinTree<Term>> listOfIntegrals = new ArrayList<BinTree<Term>>();

        // get the input file for processing
        String fileName = askInputFileName();

        // file scanning and processing
        File inputFile = new File(fileName); 
        FileInputStream inputFileStream = new FileInputStream(inputFile);
        Scanner scanFileLine = new Scanner(inputFile);

        /* 
        - FILE EXTRACTION AND CREATION OF BINARY SEARCH TREE SECTION
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

        // - - - Printing - - - //

        ArrayList<ArrayList<Term>> orderedEquations = getOrderedEquation(listOfIntegrals);

        displayAntiderivatives(orderedEquations);

        // - - - Testing - - - //
        
        /* 

        // searching
        System.out.println("\nIt is " + listOfIntegrals.get(0).searchData(new Term(1,2, false, true)) + " that the term is in the equation...\n");

        // deleting
        listOfIntegrals.get(0).deleteData(new Term(1,2, false, true));

        orderedEquations = getOrderedEquation(listOfIntegrals);
        displayAntiderivatives(orderedEquations);

        */

    } // Main

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

    public static String determineTrigSituations(String receivedString, boolean sinOrCos) {

        String situationPresent = "";

        if (sinOrCos == true) { // the analyzed string is a cosine

            if (receivedString.length() == 0) {

                situationPresent = "COS";

            } 

            for (int stringIndex = 0; stringIndex < receivedString.length(); stringIndex++) { // loop through the string

                if (receivedString.charAt(stringIndex) == '-') { // if the string contains a negative, then the trig is negative

                    situationPresent = "NEG_COS";
                    break;

                }
                else {

                    situationPresent = "COS";

                }

            }

        }   
        else { // the trig situation is sin

            if (receivedString.length() == 0) {

                situationPresent = "SIN";

            } 

            for (int stringIndex = 0; stringIndex < receivedString.length(); stringIndex++) { // loop through the string

                if (receivedString.charAt(stringIndex) == '-') { // if the string contains a negative, then the trig is negative

                    situationPresent = "NEG_SIN";
                    break;

                }
                else {

                    situationPresent = "SIN";

                }

            }

        }
       
        return situationPresent;

    }

    public static String findBounds(String fileLine, int[] integralBounds) {

        if (fileLine.indexOf("|") != -1) { // if there is the existance of the "pipe" and it is at at the beginning
            
            String bottomBound = "";
            String topBound = "";
                 
                boolean bottomBoundFound = false;

            int stringIndex = 0;
            while (stringIndex < fileLine.length()) {
                    
                if ((Character.isDigit(fileLine.charAt(stringIndex)) == true || fileLine.charAt(stringIndex) == '-') && bottomBoundFound == false) {

                        bottomBound += fileLine.charAt(stringIndex); // extract bottom bound

                }
                else if (fileLine.charAt(stringIndex) == '|') {
                        
                    bottomBoundFound = true; // once bottom bound is found start looking for top bound

                }
                else if ((Character.isDigit(fileLine.charAt(stringIndex)) == true || fileLine.charAt(stringIndex) == '-') && bottomBoundFound == true) {

                    topBound += fileLine.charAt(stringIndex); // extract top bound

                }
                else if (fileLine.charAt(stringIndex) == ' ') { // stop the search once space is encountered
                        
                    break;

                }
                   
                stringIndex++;
                    
            }
                
            if (bottomBound.length() == 0 && topBound.length() == 0) { // if not bounds are detected

                fileLine = fileLine.replace("|", "");

                integralBounds[0] = 0;
                integralBounds[1] = 0;

            }
            else { // if there are bounds from the equation

                fileLine = fileLine.substring(stringIndex);
                integralBounds[0] = Integer.parseInt(bottomBound);
                integralBounds[1] = Integer.parseInt(topBound);

            }
            }

        return fileLine;

    } // findBounds

    public static String findTermsWithExponents(String fileLine, int[] integralBounds, BinTree<Term> integralTree) {

        // storage variables for coefficient and exponent
        String coefficientStr = "";
        String exponentStr = "";

        // reference integers to remove a node
        int indexRemoval1 = 0;
        int indexRemoval2 = 0;

        // polynomials with exponents greater than 1
        while (fileLine.indexOf("x^") != -1) {

            coefficientStr = "";
            exponentStr = "";

            int stringIndex = fileLine.indexOf("x^"); // start the index at the occurrence of the "x^"
            while (stringIndex >= 0) { // search for the coefficient

                indexRemoval1 = stringIndex;

                if (Character.isDigit(fileLine.charAt(stringIndex)) == true) { // if index of char is digit, then add to string

                    coefficientStr += fileLine.charAt(stringIndex);

                }
                else if (fileLine.charAt(stringIndex) == '-') { // if index of char is minus operator, then add to string and break

                    coefficientStr += fileLine.charAt(stringIndex);
                    break;

                }
                else if (stringIndex == 0 || fileLine.charAt(stringIndex) == '+') { // if index of char is plus operator, then break

                    break;

                }

                stringIndex--;

            }
            coefficientStr = reverseString(coefficientStr);
            
            boolean digitOccurred = false;
            stringIndex = fileLine.indexOf("x^"); // start the index at the occurrence of the "x^"
            while (stringIndex < fileLine.length()) { // search for the exponent

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

            Term newTerm = new Term(parseValuesDouble(coefficientStr), parseValuesInteger(exponentStr), integralBounds, "NON_TRIG", false); // create new term and turn strings into number values
            integralTree.insertData(newTerm); // insert new term into binary tree
            fileLine = fileLine.substring(0, indexRemoval1) + fileLine.substring(indexRemoval2, fileLine.length());

        }

        return fileLine;

    } // findTermsWithExponents

    public static String findTermsWithOnlyX(String fileLine, int[] integralBounds, BinTree<Term> integralTree) {

        // storage variables for coefficient and exponent
        String coefficientStr = "";

        // reference integers to remove a node
        int indexRemoval1 = 0;

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
                else if (stringIndex == 0 || fileLine.charAt(stringIndex) == '+') { // if index of char is plus operator, then break

                    break;

                }

                stringIndex--;
                indexRemoval1 = stringIndex;
                

            }
            coefficientStr = reverseString(coefficientStr);

            Term newTerm2 = new Term(parseValuesDouble(coefficientStr), 1, integralBounds, "NON_TRIG", false); // create new term and turn strings into number values
            integralTree.insertData(newTerm2); // insert new term into binary tree
            fileLine = fileLine.substring(0, indexRemoval1) + fileLine.substring(fileLine.indexOf("x") + 1, fileLine.length());

        }

        return fileLine;

    } // findTermsWithOnlyX

    public static String findTermsWithOnlyNums(String fileLine, int[] integralBounds, BinTree<Term> integralTree) {

        // storage variables for coefficient and exponent
        String coefficientStr = "";

        // reference integers to remove a node
        int indexRemoval1 = 0;

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

            Term newTerm3 = new Term(parseValuesDouble(coefficientStr), 0, integralBounds, "NON_TRIG", false); // create new term and turn strings into number values
            integralTree.insertData(newTerm3); // insert new term into binary tree
            fileLine = fileLine.substring(indexRemoval1 + 1);

        }

        return fileLine;

    } // findTermsWithOnlyNums

    public static String findTermsWithTrig(String fileLine, int[] integralBounds, BinTree<Term> integralTree) {

        // copy of the file line
        String copyOfFileLine = fileLine;

        // storage variables for coefficient and exponent
        String coefficientStr = "";
        String internalCoefficientStr = "";

        // reference integers to remove a node
        int indexRemoval1 = 0;
        int indexRemoval2 = 0;

        // polynomials with sin
        while (fileLine.indexOf("sin") != -1) {

            coefficientStr = "";
            internalCoefficientStr = "";

            int stringIndex = fileLine.indexOf("sin"); // start the index at the occurrence of the "x^"
            while (stringIndex >= 0) { // search for the coefficient

                indexRemoval1 = stringIndex;

                if (Character.isDigit(fileLine.charAt(stringIndex)) == true) { // if index of char is digit, then add to string

                    coefficientStr += fileLine.charAt(stringIndex);

                }
                else if (fileLine.charAt(stringIndex) == '-') { // if index of char is minus operator, then add to string and break

                    coefficientStr += fileLine.charAt(stringIndex);
                    break;

                }
                else if (stringIndex == 0 || fileLine.charAt(stringIndex) == '+') { // if index of char is plus operator, then break

                    break;

                }

                stringIndex--;

            }
            coefficientStr = reverseString(coefficientStr);
             
            boolean digitOccurred = false;
            stringIndex = fileLine.indexOf("sin"); // start the index at the occurrence of the "x^"
            while (stringIndex < fileLine.length()) { // search for the exponent

                if (Character.isDigit(fileLine.charAt(stringIndex)) == true) {

                    digitOccurred = true;
                    internalCoefficientStr += fileLine.charAt(stringIndex);

                }
                else if (fileLine.charAt(stringIndex) == 'x') {

                    digitOccurred = true;

                }
                else if (fileLine.charAt(stringIndex) == '-') {

                    if (digitOccurred == true) { break; }

                    internalCoefficientStr += fileLine.charAt(stringIndex);

                }
                else if (fileLine.charAt(stringIndex) == '+') {

                    break;

                }

                stringIndex++;
                indexRemoval2 = stringIndex;

            }
            
            Term newTerm = new Term(parseValuesDouble(coefficientStr), parseValuesInteger(internalCoefficientStr), integralBounds, determineTrigSituations(coefficientStr, false), false); // create new term and turn strings into number values
            
            newTerm.setTrigEncounterPrecedence(copyOfFileLine.indexOf("sin")); // stores the order of the trigs
            
            // remove sin form copied file line to help determine the order in which the trigs came
            StringBuffer stringBuffer = new StringBuffer(copyOfFileLine);
            stringBuffer.replace(copyOfFileLine.indexOf("sin"), copyOfFileLine.indexOf("sin") + 3, "   ");
            copyOfFileLine = stringBuffer.toString();

            integralTree.insertData(newTerm); // insert new term into binary tree
            fileLine = fileLine.substring(0, indexRemoval1) + fileLine.substring(indexRemoval2, fileLine.length());
        
        }

        // polynomials with cos
        while (fileLine.indexOf("cos") != -1) {

            coefficientStr = "";
            internalCoefficientStr = "";

            int stringIndex = fileLine.indexOf("cos"); // start the index at the occurrence of the "x^"
            while (stringIndex >= 0) { // search for the coefficient
                
                indexRemoval1 = stringIndex;

                if (Character.isDigit(fileLine.charAt(stringIndex)) == true) { // if index of char is digit, then add to string

                    coefficientStr += fileLine.charAt(stringIndex);

                }
                else if (fileLine.charAt(stringIndex) == '-') { // if index of char is minus operator, then add to string and break
                    
                    coefficientStr += fileLine.charAt(stringIndex);
                    break;

                }
                else if (stringIndex == 0 || fileLine.charAt(stringIndex) == '+') { // if index of char is plus operator, then break

                    break;

                }

                stringIndex--;

            }
            coefficientStr = reverseString(coefficientStr);

            boolean digitOccurred = false;
            stringIndex = fileLine.indexOf("cos"); // start the index at the occurrence of the "x^"
            while (stringIndex < fileLine.length()) { // search for the exponent

                if (Character.isDigit(fileLine.charAt(stringIndex)) == true) {

                    digitOccurred = true;
                    internalCoefficientStr += fileLine.charAt(stringIndex);

                }
                else if (fileLine.charAt(stringIndex) == 'x') {

                    digitOccurred = true;

                }
                else if (fileLine.charAt(stringIndex) == '-') {

                    if (digitOccurred == true) { break; }

                    internalCoefficientStr += fileLine.charAt(stringIndex);

                }
                else if (fileLine.charAt(stringIndex) == '+') {

                    break;

                }

                stringIndex++;
                indexRemoval2 = stringIndex;

            }

            Term newTerm = new Term(parseValuesDouble(coefficientStr), parseValuesInteger(internalCoefficientStr), integralBounds, determineTrigSituations(coefficientStr, true), false); // create new term and turn strings into number values
           
            newTerm.setTrigEncounterPrecedence(copyOfFileLine.indexOf("cos")); // stores the order of the trigs
            
            // remove sin form copied file line to help determine the order in which the trigs came
            StringBuffer stringBuffer2 = new StringBuffer(copyOfFileLine);
            stringBuffer2.replace(copyOfFileLine.indexOf("cos"), copyOfFileLine.indexOf("cos") + 3, "   ");
            copyOfFileLine = stringBuffer2.toString();
            
            integralTree.insertData(newTerm); // insert new term into binary tree
            fileLine = fileLine.substring(0, indexRemoval1) + fileLine.substring(indexRemoval2, fileLine.length());
        
        }

        return fileLine;

    } // findTermsWithTrig

    public static BinTree<Term> readFileLine(Scanner scanFileLine) {

        BinTree<Term> integralTree = new BinTree<Term>();

        int[] integralBounds = new int[2]; // stores the integral bounds

        String fileLine = scanFileLine.nextLine(); // scans line in the file

        if (fileLine.length() != 0) { // does not process blank lines

            // find bounds of anti-derivative if it has one
            fileLine = findBounds(fileLine, integralBounds);

            // remove white spaces and dx from file line
            fileLine = fileLine.replace(" ","");
            fileLine = fileLine.replace("dx","");

            // polynomials with trig
            fileLine = findTermsWithTrig(fileLine, integralBounds, integralTree);

            // polynomials with exponents greater than 1
            fileLine = findTermsWithExponents(fileLine, integralBounds, integralTree);
            
            // polynomials with exponents equal to 1
            fileLine = findTermsWithOnlyX(fileLine, integralBounds, integralTree);
            
            // numbers with no variables
            fileLine = findTermsWithOnlyNums(fileLine, integralBounds, integralTree);
           
        }

        return integralTree;

    } // readFileLine

    public static ArrayList<ArrayList<Term>> getOrderedEquation(ArrayList<BinTree<Term>> listOfIntegrals) {

        ArrayList<ArrayList<Term>> orderedEquations = new ArrayList<ArrayList<Term>>();

        for (int arrayIndex = 0; arrayIndex < listOfIntegrals.size(); arrayIndex++) { // get the binary tree, and store into an array list

            ArrayList<Term> orderedTerms = listOfIntegrals.get(arrayIndex).traverseInOrder(); // traverses the binary tree in order then, push the term to begginning of array list

            orderedEquations.add(orderedTerms); // adds term to array list

        }

        return orderedEquations;

    } // getOrderedEquation

    public static void displayAntiderivatives( ArrayList<ArrayList<Term>> orderedEquations) {

        boolean isDefiniteEquation = false;
        int[] integralBounds = new int[2];
        double definiteIntegralTotalValue = 0;

        String outputEqu = "";
        for (int arrayIndex = 0; arrayIndex < orderedEquations.size(); arrayIndex++) {

            // reset outputs for new equation
            outputEqu = "";
            definiteIntegralTotalValue = 0;

            for (int i = 0; i <  orderedEquations.get(arrayIndex).size(); i++) {

                outputEqu = outputEqu + orderedEquations.get(arrayIndex).get(i) + " + "; // add a plus between each term

                isDefiniteEquation = orderedEquations.get(arrayIndex).get(i).isDefiniteIntegral(); // checks if the integral is definite
                
                
                definiteIntegralTotalValue += orderedEquations.get(arrayIndex).get(i).evalulateDefiniteIntegral(); // evalulate each individuall term if it contains bounds

                integralBounds = orderedEquations.get(arrayIndex).get(i).getIntegralBounds();

                while (outputEqu.indexOf(" + -") != -1) { // get rid of extra operators when term is negative
                    
                    StringBuffer stringBuffer = new StringBuffer(outputEqu);
                    stringBuffer.replace(outputEqu.indexOf(" + -"), outputEqu.indexOf(" + -") + 4, " - ");
                    outputEqu = stringBuffer.toString();
                    
                }
                 
                while (outputEqu.indexOf(" + (-") != -1) { // get rid of extra operators when term is negative
                    
                    StringBuffer stringBuffer = new StringBuffer(outputEqu);
                    stringBuffer.replace(outputEqu.indexOf(" + (-"), outputEqu.indexOf(" + (-") + 5, " - (");
                    outputEqu = stringBuffer.toString();
                    
                }
                
            }

            if (isDefiniteEquation == false) { // indefinite integrals

                System.out.println(outputEqu + "C");

            }
            else { // definite integrals

                outputEqu = outputEqu.substring(0, outputEqu.length() - 3) + ", " + integralBounds[0] + "|" + integralBounds[1] + " = " + formatDecimal(definiteIntegralTotalValue);
                
                System.out.println(outputEqu);

            }

        }

    } // getOrderedEquation

    public static String formatDecimal(double desiredStatDouble) { // format decimals to three points

        if (desiredStatDouble == 0) {

            return "0";

        }

        DecimalFormat formatNum = new DecimalFormat("0.000"); // using decimal formatting to properly output the amount of decimals I want in a string

        return formatNum.format(desiredStatDouble);

    } //formatDecimal    

    public static double parseValuesDouble(String coefficientStr) {

        if (coefficientStr.length() != 0) {
            
            if (coefficientStr.compareTo("-") == 0) {

                return -1;

            }
            else {  
                
                if (coefficientStr == "") { // if the term coefficient is 0 send a 0 to the term object

                    return 0;

                }
                else {
                
                    return Double.parseDouble(coefficientStr); // parse the double out of the string
                
                }

            }

        }

        return 1; // if the string is empty, return 1 because the coefficient is one

    } // parseValuesDouble

    public static int parseValuesInteger(String expOrInnerCoefStr) {

        if (expOrInnerCoefStr.length() != 0) {
            
            if (expOrInnerCoefStr.compareTo("-") == 0) {

                return -1;

            }
            else {  
                
                return Integer.parseInt(expOrInnerCoefStr); // parse the double out of the string

            }

        }

        return 1; // if the string is empty, return 1 because the exponent or inner coefficient is one

    } // parseValuesInteger

} 
