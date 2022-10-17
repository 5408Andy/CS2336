/* 
 * Test Preparation
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/16/2022
 * Class & Section: CS - 2366.003
 */

public class HexFormatException extends Exception {
    
    private String hexString;

    HexFormatException(String hexStringReceived) {

        super("Invalid Argument" + hexStringReceived);
        hexString = hexStringReceived;

    } // BinaryFormatException - Overloaded Constructor

    public String getHexString() { return hexString; }

    @Override
    public String getMessage() {

        return "This string is an INVALID binary number: " + hexString;

    }

}
