/* 
 * 
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

} 
