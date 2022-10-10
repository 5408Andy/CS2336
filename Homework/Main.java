/* 
 * HW Review #1
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/9/2022
 * Class & Section: CS - 2366.003
 */

public class Main {
    
    public static void main(String[] args) {

        String[] testStrings = {"101010101", "12321020", "This is not binary"};

        for (int stringIndex = 0; stringIndex < 3; stringIndex++) {

            try {

                if (bin2Dec(testStrings[stringIndex]) == -1) { // the string is not a binary number so throw exception

                    throw new BinaryFormatException(testStrings[stringIndex]);

                } 
                else {

                    System.out.println("This string could be converted into binary: " + bin2Dec(testStrings[stringIndex]));

                }

            }
            catch (BinaryFormatException E) {

                System.out.println(E.getMessage());

            }

        }

    }

    public static int bin2Dec(String binaryStringReceived) throws BinaryFormatException {

        int decimalBinaryValue = -1;

        for (int stringIndex = 0; stringIndex < binaryStringReceived.length(); stringIndex++) { // loops through characters in string

            if (binaryStringReceived.charAt(stringIndex) != '0' && binaryStringReceived.charAt(stringIndex) != '1') { // if the value at index is not either 1 or 0, throw an exception

                return -1;

            }

        }
        
        decimalBinaryValue = Integer.parseInt(binaryStringReceived); // turn the string into an integer

        return decimalBinaryValue;

    }

} // bin2Dec
