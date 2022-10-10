/* 
 * HW Review #1
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/9/2022
 * Class & Section: CS - 2366.003
 */

public class BinaryFormatException extends Exception {
    
    private String invalidBinaryNum;

    BinaryFormatException(String invalidBinaryNum) {

        super("Invalid Argument: " + invalidBinaryNum);
        this.invalidBinaryNum = invalidBinaryNum;

    } // BinaryFormatException - Overloaded Constructor

    public String getInvalidBinaryNum() { return invalidBinaryNum; } // getter function for invalid binary number

    public String getMessage() {

        return "This is a string of the invalid binary number: " + invalidBinaryNum;

    } // getMessage

}
