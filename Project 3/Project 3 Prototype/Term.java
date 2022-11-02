/* 
 * Project 3: Antideriviative Calculator
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/27/2022
 * Class & Section: CS - 2366.003
 */

enum TrigSituations {

    COS,
    SIN,
    NEG_COS,
    NEG_SIN,
    NON_TRIG;

}

public class Term implements Comparable<Term> {
    
    private double coefValue;
    private Integer expValue;
    private Integer innerCoefValue;

    private double newCoefValue;
    private Integer newExpValue;

    private int[] integralBounds = new int[2];

    private boolean comparisonTerm;

    private TrigSituations situationPresent;

    Term() {

        coefValue = 0;
        expValue = 0;

        integralBounds[0] = 0;
        integralBounds[1] = 0;

        comparisonTerm = false;

    } // Term - Constructor
    
    Term(double coefValueReceived, int expValueReceived, boolean trigTermReceived, boolean comparisonTermReceived) {

        coefValue = coefValueReceived;
        expValue = expValueReceived;

        comparisonTerm = comparisonTermReceived;

    } // Term - Constructor - When the term is a temporary term used for comparing terms in the binary tree

    Term(double coefValueReceived, int innerCoefValueOrExpValueReceived, int[] integralBoundsReceived, TrigSituations situationReceived, boolean comparisonTermReceived) {

        situationPresent = situationReceived;

        if (situationPresent == TrigSituations.NON_TRIG) {

            coefValue = coefValueReceived;
            innerCoefValue = innerCoefValueOrExpValueReceived;

            integralBounds = integralBoundsReceived;

            situationPresent = situationReceived;

        }
        else {

            coefValue = coefValueReceived;
            expValue = innerCoefValueOrExpValueReceived;

            integralBounds = integralBoundsReceived;

            comparisonTerm = comparisonTermReceived;

            situationPresent = situationReceived;

        }

    } // Term - Constructor - When the term is being added to the binary tree and also determines whether term is trig or normal

    public int compareTo(Term receivedTerm) {

        int compareValue = 404;

        if (situationPresent == TrigSituations.NON_TRIG && receivedTerm.getTrigSituation() != TrigSituations.NON_TRIG) { // if the current term is not trig and the received term is trig, puts it at the back

            compareValue = -1;

        }
        else if (situationPresent == TrigSituations.NON_TRIG && receivedTerm.getTrigSituation() != TrigSituations.NON_TRIG) { // if the current term and the received term is trig, 

            compareValue = innerCoefValue.compareTo(receivedTerm.getInnerCoefValue());

            if (compareValue == 0 && comparisonTerm == false) {

                receivedTerm.setCoefValue(coefValue + receivedTerm.getCoefValue());

            }

        }
        else if (situationPresent == TrigSituations.NON_TRIG && receivedTerm.getTrigSituation() == TrigSituations.NON_TRIG) { // if the current term and the received term are normal terms

            compareValue = expValue.compareTo(receivedTerm.getExpValue());

            if (compareValue == 0 && comparisonTerm == false) {
                
                receivedTerm.setCoefValue(coefValue + receivedTerm.getCoefValue());

            }

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

        if (situationPresent == TrigSituations.NON_TRIG) {

            newExpValue = expValue + 1;

            newCoefValue = coefValue / (double)newExpValue;
            
            if (newExpValue != 1 && newCoefValue % 1 != 0) { // exponent value not equal to one and coefficient is not whole number

                return "(" + doubleToFraction(newCoefValue) + ")x^" + newExpValue; // returns fraction integrated

            }
            else if (newExpValue != 1 && newCoefValue % 1 == 0) { // exponent value not equal to 1 but coefficient is equal to 1

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
            else { // exponent equal to 1
                
                if (Math.round(newCoefValue) == 1) {

                    return "x"; // returns when it coefficient is one so just x

                }
                
                return (Math.round(newCoefValue)) + "x"; // return its x alongside its whole number coefficient

            }

        }
        else {

            newCoefValue /= innerCoefValue;

            

        }

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

    public Integer getInnerCoefValue() { return innerCoefValue; }

    public int[] getIntegralBounds() { return integralBounds; }

    public TrigSituations getTrigSituation() { return situationPresent; }
    
}
