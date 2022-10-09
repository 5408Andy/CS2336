/* 
 * HW Review #1
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/9/2022
 * Class & Section: CS - 2366.003
 */

public class BinaryFormatException extends Exception{
    
    private int invalidBinaryNum;

    BinaryFormatException(int invalidBinaryNumReceived) {

        invalidBinaryNumReceived = invalidBinaryNum;

    } // BinaryFormatException - Overloaded Constructor

    public int getInvalidBinaryNum() { return invalidBinaryNum; } // getter function for invalid binary number

    public String getMessage() {

        return "This is a string of the invalid binary number: " + invalidBinaryNum;

    } // getMessage

}
