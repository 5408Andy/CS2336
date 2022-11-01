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

    private boolean comparisonTerm;

    Term() {

        coefValue = 0;
        expValue = 0;

        integralBounds[0] = 0;
        integralBounds[1] = 0;

        comparisonTerm = false;

    } // Term - Constructor

    Term(double coefValueReceived, int expValueReceived, boolean comparisonTermReceived) {

        coefValue = coefValueReceived;
        expValue = expValueReceived;

        comparisonTerm = comparisonTermReceived;

    } // Term - Constructor - When the term is a temporary term used for comparing terms in the binary tree

    Term(double coefValueReceived, int expValueReceived, boolean comparisonTermReceived, int[] integralBoundsReceived) {

        coefValue = coefValueReceived;
        expValue = expValueReceived;

        integralBounds = integralBoundsReceived;

        comparisonTerm = comparisonTermReceived;

    } // Term - Constructor - When the term is going on to the binary tree

    public int compareTo(Term receivedTerm) {

        int compareValue = expValue.compareTo(receivedTerm.getExpValue());

        if (compareValue == 0 && comparisonTerm == false) {
            
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
        
        if (newExpValue >= -10 && newCoefValue % 1 != 0) {

            return "(" + doubleToFraction(newCoefValue) + ")x^" + newExpValue; // returns fraction integrated

        }
        else if (newExpValue != 1 && newCoefValue % 1 == 0) {

            if (Math.round(newCoefValue) == 1) { // returns when x^ is one coefficient

                return "x^" + newExpValue;

            }
            else if (Math.round(newCoefValue) == -1) {

                return "-x^" + newExpValue;// returns when x^ is a negative one coefficient

            }
            else if (Math.round(newCoefValue) == 0) {
                
                return "0";

            }
            else {

                return (Math.round(newCoefValue)) + "x^" + newExpValue; // returns when coefficient is whole number not equal to one

            }

        }

        if (Math.round(newCoefValue) == 1) {

            return "x"; // returns when it coefficient is one so just x

        }
        
        return (Math.round(newCoefValue)) + "x"; // return its x alongside its whole number coefficient

    } // findIntegral

    public boolean isDefiniteIntegral() { // checks if the term is definite integral

        if (integralBounds[0] == 0 && integralBounds[1] == 0) {

            return false;

        }

        return true;

    }

    public double evalulateDefiniteIntegral() {

        double totalValue = 0;

        if (integralBounds[0] < integralBounds[1]) { // does normal integration because bounds are least to greatest

            double totalValueTopBound = newCoefValue * Math.pow(integralBounds[1], newExpValue);

            double totalValueBottomBound = newCoefValue * Math.pow(integralBounds[0], newExpValue);

            
            totalValue = totalValueTopBound - totalValueBottomBound;

        }
        else if (integralBounds[0] > integralBounds[1]) { // needs to flip the bounds and multiple by -1 to perform integration

            double totalValueTopBound = newCoefValue * Math.pow(integralBounds[1], newExpValue);

            double totalValueBottomBound = newCoefValue * Math.pow(integralBounds[0], newExpValue);
            
            totalValue = (-1) * (totalValueBottomBound - totalValueTopBound);

        }

        return totalValue;

    } // evalulateDefiniteIntegral

    public String toString() {

        return findIntegral();

    } // toString

    // - - - Setter Methods - - - //

    public void setCoefValue(double coefValueReceived) { coefValue = coefValueReceived; }

    public void setExpValue(Integer expValueReceived) { expValue = expValueReceived; }

    // - - - Getter Methods - - - //

    public double getCoefValue() { return coefValue; }

    public Integer getExpValue() { return expValue; }

    public int[] getIntegralBounds() { return integralBounds; }
    
}
