/* 
 * Test Preparation
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/16/2022
 * Class & Section: CS - 2366.003
 */

public class Main {
    
    public static final int HEX_CHARARACTERS_SIZE = 16;

    public static final char[] HEX_CHARARACTERS = {

        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',

        'A', 'B', 'C', 'D', 'E', 'F'

    };

    public static final int[] HEX_VALUES = {

        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 ,15

    };

    public static void main(String[] args) {

        String testHex = "3B";

        try {

            System.out.println(convertHexToDecimal(testHex));

        }
        catch(HexFormatException E) {

            System.out.println(E.getMessage());

        }


    }

    public static int convertHexToDecimal(String hexStringReceived) throws HexFormatException {

        int hexDecimal = 0;

        for (int stringIndex = 0; stringIndex < hexStringReceived.length(); stringIndex++) {

            if (checkChar(hexStringReceived.charAt(stringIndex)) == false) {

                throw new HexFormatException(hexStringReceived);

            }

        }

        int exponentValue = 0;

        for (int stringIndex = hexStringReceived.length() - 1; stringIndex >= 0; stringIndex--) {

            if (hexStringReceived.charAt(stringIndex) == '0') {
                
                hexDecimal += 0 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == '1') {
                
                hexDecimal += 1 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == '2') {
                
                hexDecimal += 2 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == '3') {
                
                hexDecimal += 3 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == '4') {
                
                hexDecimal += 4 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == '5') {
                
                hexDecimal += 5 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == '6') {
                
                hexDecimal += 6 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == '7') {
                
                hexDecimal += 7 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == '8') {
                
                hexDecimal += 8 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == '9') {
                
                hexDecimal += 9 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == 'A') {
                
                hexDecimal += 10 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == 'B') {
                
                hexDecimal += 11 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == 'C') {
                
                hexDecimal += 12 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == 'D') {
                
                hexDecimal += 13 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == 'E') {
                
                hexDecimal += 14 * Math.pow(16, exponentValue);
            
            }
            else if (hexStringReceived.charAt(stringIndex) == 'F') {
                
                hexDecimal += 15 * Math.pow(16, exponentValue);
            
            }

            exponentValue++;
            
        }


        return hexDecimal;

    } // convertHexToDecimal

    public static boolean checkChar(char characterReceived) {

        for (int arrayIndex = 0; arrayIndex < HEX_CHARARACTERS_SIZE; arrayIndex++) {

            if (HEX_CHARARACTERS[arrayIndex] == characterReceived) {

                return true;

            }

        }
    
        return false;

    }

}
