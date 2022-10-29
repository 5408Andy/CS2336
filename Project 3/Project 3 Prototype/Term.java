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

    private double newCoefValue;
    private Integer newExpValue;

    private int[] integralBounds = new int[2];

    Term() {

        coefValue = 0;
        expValue = 0;

        integralBounds[0] = 0;
        integralBounds[1] = 0;

    } // Term - Constructor

    Term(double coefValueReceived, int expValueReceived/* , int[] integralBoundsReceived*/) {

        coefValue = coefValueReceived;
        expValue = expValueReceived;

        /*integralBounds = integralBoundsReceived;*/

    } // Term - Constructor

    public int compareTo(Term receivedTerm) {

        int compareValue = expValue.compareTo(receivedTerm.getExpValue());

        if (compareValue == 0) {
            
            receivedTerm.setCoefValue(coefValue + receivedTerm.getCoefValue());

        }

        return compareValue;
     
    } // compareTo

    private String doubleToFraction(double receivedDouble) {

        double negligibleRatio = 0.01;

        for(int i=1; ; i++){
            
            double tempValue = receivedDouble / (1D/i);
            
            if (Math.abs(tempValue - Math.round(tempValue)) < negligibleRatio){
                
                return (Math.round(tempValue)+ "/" + i);
                
            }
        }

    }

    private String findIntegral() {

        newExpValue = expValue + 1;

        newCoefValue = coefValue / (double)newExpValue;
        
        if (newExpValue > 1 && newCoefValue % 1 != 0) {

            return "(" + doubleToFraction(newCoefValue) + ")x^" + newExpValue ;

        }
        else if (newExpValue != 1 && newCoefValue % 1 == 0) {

            if (Math.round(newCoefValue) == 1) {

                return "x^" + newExpValue;

            }
            else if (Math.round(newCoefValue) == -1) {

                return "-x^" + newExpValue;

            }

            return (Math.round(newCoefValue)) + "x^" + newExpValue;

        }

        if (Math.round(newCoefValue) == 1) {

            return "x";

        }
        //System.out.println(newCoefValue + " " + newExpValue + " " + coefValue);
        return (Math.round(newCoefValue)) + "x";

    } // findIntegral

    public String toString() {

        return findIntegral();

    } // toString

    // - - - Setter Methods - - - //

    public void setCoefValue(double coefValueReceived) { coefValue = coefValueReceived; }

    public void setExpValue(Integer expValueReceived) { expValue = expValueReceived; }

    // - - - Getter Methods - - - //

    public double getCoefValue() { return coefValue; }

    public Integer getExpValue() { return expValue; }

}
