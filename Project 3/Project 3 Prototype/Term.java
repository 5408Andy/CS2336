/* 
 * Project 3: Antideriviative Calculator
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/27/2022
 * Class & Section: CS - 2366.003
 */

public class Term implements Comparable<Term> {
    
    private double coefValue;
    private Integer expValue;

    private int[] integralBounds = new int[2];

    Term() {

        coefValue = 0;
        expValue = 0;

        integralBounds[0] = 0;
        integralBounds[1] = 0;

    } // Term - Constructor

    Term(double coefValueReceived, int expValueReceived, int[] integralBoundsReceived) {

        coefValue = coefValueReceived;
        expValue = expValueReceived;

        integralBounds = integralBoundsReceived;

    } // Term - Constructor

    public int compareTo(Term receivedTerm) {

        return expValue.compareTo(receivedTerm.getExpValue());
    
    }

    public String toString() {

        return "Coefficient: " + coefValue + "\tExponent: " + expValue;

    }

    // - - - Getter Methods - - - //

    public Integer getExpValue() { return expValue; }

}
