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
    private Integer innerCoefValue;
    private TrigSituations situationPresent;

    private double newCoefValue;
    private Integer newExpValue;
    private TrigSituations newSituationPresent;

    private int[] integralBounds = new int[2];

    private Integer trigEncounterPrecedence;

    private boolean comparisonTerm;

    int trigAlternator = 0;

    enum TrigSituations {

        COS,
        SIN,
        NEG_COS,
        NEG_SIN,
        NON_TRIG;
    
    }

    Term() {

        coefValue = 0;
        expValue = 0;

        integralBounds[0] = 0;
        integralBounds[1] = 0;

        comparisonTerm = false;

    } // Term - Constructor
    
    Term(double coefValueReceived, int expValueReceived, String situationReceived, boolean comparisonTermReceived) {

        coefValue = coefValueReceived;
        expValue = expValueReceived;

        situationPresent = parseTrigSituations(situationReceived);

        comparisonTerm = comparisonTermReceived;

    } // Term - Constructor - When the term is a temporary term used for comparing terms in the binary tree

    Term(double coefValueReceived, int innerCoefValueOrExpValueReceived, int[] integralBoundsReceived, String situationReceived, boolean comparisonTermReceived) {

        situationPresent = parseTrigSituations(situationReceived);

        if (situationPresent != TrigSituations.NON_TRIG) {

            coefValue = coefValueReceived;
            innerCoefValue = innerCoefValueOrExpValueReceived;

            integralBounds = integralBoundsReceived;

            situationPresent = parseTrigSituations(situationReceived);

        }
        else {

            coefValue = coefValueReceived;
            expValue = innerCoefValueOrExpValueReceived;

            integralBounds = integralBoundsReceived;

            comparisonTerm = comparisonTermReceived;

            situationPresent = parseTrigSituations(situationReceived);

        }

    } // Term - Constructor - When the term is being added to the binary tree and also determines whether term is trig or normal

    @Override
    public int compareTo(Term receivedTerm) {

        int compareValue = 404;

        if (situationPresent == TrigSituations.NON_TRIG && receivedTerm.getTrigSituation() != TrigSituations.NON_TRIG) { // if the current term is not trig and the received term is trig, puts it at the back

            compareValue = 1;

        }
        else if (situationPresent != TrigSituations.NON_TRIG && receivedTerm.getTrigSituation() == TrigSituations.NON_TRIG) { // if the current term is not trig and the received term is trig, puts it at the back

            compareValue = -1;

        }
        else if ((situationPresent != TrigSituations.NON_TRIG && receivedTerm.getTrigSituation() != TrigSituations.NON_TRIG) && situationPresent != receivedTerm.getTrigSituation()) { // if they are trig terms, but not the same type
        
           compareValue = -1 * trigEncounterPrecedence.compareTo(receivedTerm.getTrigEncounterPrecedence());

        }
        else if ((situationPresent != TrigSituations.NON_TRIG && receivedTerm.getTrigSituation() != TrigSituations.NON_TRIG) && situationPresent == receivedTerm.getTrigSituation()) { // if the current term and the received term is trig and same type of trig

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

    private String doubleToFraction(double receivedDouble) { // turn the double into a fraction

        double negligibleRatio = 0.01;

        int denomValue = 1;

        double numerValue = receivedDouble / (1.0/denomValue);
        
        while (Math.abs(numerValue - Math.round(numerValue)) > negligibleRatio) {

            denomValue++;

            numerValue = receivedDouble / (1.0/denomValue);

        }

        return Math.round(numerValue)+ "/" + denomValue;

    } // doubleToFraction

    private TrigSituations parseTrigSituations(String stringReceived) { // turn the received string from constructor into a enum so we can use in the term object

        if (stringReceived == "COS") {

            return TrigSituations.COS;

        }
        else if (stringReceived == "NEG_COS") {

            return TrigSituations.NEG_COS;

        }
        else if (stringReceived == "SIN") {

            return TrigSituations.SIN;

        }
        else if (stringReceived == "NEG_SIN") {

            return TrigSituations.NEG_SIN;

        }
        else {

            return TrigSituations.NON_TRIG;

        }
        

    } // parseTrigSituations

    private TrigSituations integrateTrig() { // integrate the trig term so we can form a proper output

        TrigSituations situationIntegrated = TrigSituations.NON_TRIG;

        switch (situationPresent) {

            case COS:
                situationIntegrated = TrigSituations.SIN;
                break;
            
            case SIN:
                situationIntegrated = TrigSituations.NEG_COS;
                break;
            
            case NEG_COS:
                situationIntegrated = TrigSituations.NEG_SIN;
                break;

            case NEG_SIN:
                situationIntegrated = TrigSituations.COS;
                break;

            case NON_TRIG:
                System.out.println("Error In Trig Integration");
                break;

        }

        return situationIntegrated;

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

            newSituationPresent = integrateTrig();

            if (newSituationPresent == TrigSituations.COS) { // integral result being cos

                newCoefValue = -1 * coefValue / (double)innerCoefValue;

                if (newCoefValue % 1 != 0) {

                    if (innerCoefValue != -1 && innerCoefValue != 1) { 
                        
                        return "(" + doubleToFraction(newCoefValue) + ")cos " + innerCoefValue + "x"; // returns fraction integrated

                    }
                    else if (innerCoefValue == 1) {

                        return "(" + doubleToFraction(newCoefValue) + ")cos x"; // returns fraction integrated

                    }
                    else if (innerCoefValue == -1) {

                        return "(" + doubleToFraction(newCoefValue) + ")cos -x"; // returns fraction integrated


                    }

                }
                else if (newCoefValue % 1 == 0 && newCoefValue != -1 && newCoefValue != 1) {

                    if (innerCoefValue != -1 && innerCoefValue != 1) {

                        return Math.round(newCoefValue) + "cos " + innerCoefValue + "x";

                    }
                    else if (innerCoefValue == 1) {

                        return Math.round(newCoefValue) + "cos x";

                    }
                    else if (innerCoefValue == -1) {

                        return Math.round(newCoefValue) + "cos -x";

                    }

                }   
                else if (newCoefValue == -1) { // when the coefficent is negative

                    if (innerCoefValue != -1 && innerCoefValue != 1) {

                        return "-cos " + innerCoefValue + "x";

                    }
                    else if (innerCoefValue == 1) {

                        return "-cos x";

                    }
                    else if (innerCoefValue == -1) {

                        return "-cos -x";

                    }

                }
                else if (newCoefValue == 1) {  // when the coefficient is positive

                    if (innerCoefValue != -1 && innerCoefValue != 1) {

                        return "cos" + innerCoefValue + "x";

                    }
                    else if (innerCoefValue == 1) {

                        return "cos x";

                    }
                    else if (innerCoefValue == -1) {

                        return "cos -x";

                    }

                }

            }
            else if (newSituationPresent == TrigSituations.NEG_COS) { // integral result being negative cos

                newCoefValue = -1 * coefValue / (double)innerCoefValue;

                if (newCoefValue % 1 != 0) {

                    if (innerCoefValue != -1 && innerCoefValue != 1) { 
                        
                        return "(" + doubleToFraction(newCoefValue) + ")cos " + innerCoefValue + "x"; // returns fraction integrated

                    }
                    else if (innerCoefValue == 1) {

                        return "(" + doubleToFraction(newCoefValue) + ")cos x"; // returns fraction integrated

                    }
                    else if (innerCoefValue == -1) {

                        return "(" + doubleToFraction(newCoefValue) + ")cos -x"; // returns fraction integrated


                    }

                }
                else if (newCoefValue % 1 == 0 && newCoefValue != -1 && newCoefValue != 1) {

                    if (innerCoefValue != -1 && innerCoefValue != 1) {

                        return Math.round(newCoefValue) + "cos " + innerCoefValue + "x";

                    }
                    else if (innerCoefValue == 1) {

                        return Math.round(newCoefValue) + "cos x";

                    }
                    else if (innerCoefValue == -1) {

                        return Math.round(newCoefValue) + "cos -x";

                    }

                }   
                else if (newCoefValue == -1) { // when the coefficent is negative

                    if (innerCoefValue != -1 && innerCoefValue != 1) { 

                        return "-cos " + innerCoefValue + "x";

                    }
                    else if (innerCoefValue == 1) {

                        return "-cos x";

                    }
                    else if (innerCoefValue == -1) {

                        return "-cos -x";

                    }

                }
                else if (newCoefValue == 1) { // when the coefficient is positive

                    if (innerCoefValue != -1 && innerCoefValue != 1) {

                        return "cos" + innerCoefValue + "x";

                    }
                    else if (innerCoefValue == 1) {

                        return "cos x";

                    }
                    else if (innerCoefValue == -1) {

                        return "cos -x";

                    }

                }

            }
            else if (newSituationPresent == TrigSituations.SIN) { // integral result being sin

                newCoefValue =  coefValue / (double)innerCoefValue;

                if (newCoefValue % 1 != 0) {

                    if (innerCoefValue != -1 && innerCoefValue != 1) { 
                        
                        return "(" + doubleToFraction(newCoefValue) + ")sin " + innerCoefValue + "x"; // returns fraction integrated

                    }
                    else if (innerCoefValue == 1) {

                        return "(" + doubleToFraction(newCoefValue) + ")sin x"; // returns fraction integrated

                    }
                    else if (innerCoefValue == -1) {

                        return "(" + doubleToFraction(newCoefValue) + ")sin -x"; // returns fraction integrated


                    }

                }
                else if (newCoefValue % 1 == 0 && newCoefValue != -1 && newCoefValue != 1) {

                    if (innerCoefValue != -1 && innerCoefValue != 1) {

                        return Math.round(newCoefValue) + "sin " + innerCoefValue + "x";

                    }
                    else if (innerCoefValue == 1) {

                        return Math.round(newCoefValue) + "sin x";

                    }
                    else if (innerCoefValue == -1) {

                        return Math.round(newCoefValue) + "sin -x";

                    }

                }   
                else if (newCoefValue == -1) { // when the coefficent is negative

                    if (innerCoefValue != -1 && innerCoefValue != 1) {

                        return "-sin " + innerCoefValue + "x";

                    }
                    else if (innerCoefValue == 1) {

                        return "-sin x";

                    }
                    else if (innerCoefValue == -1) {

                        return "-sin -x";

                    }

                }
                else if (newCoefValue == 1) { // when the coefficent is positive

                    if (innerCoefValue != -1 && innerCoefValue != 1) {

                        return "sin" + innerCoefValue + "x";

                    }
                    else if (innerCoefValue == 1) {

                        return "sin x";

                    }
                    else if (innerCoefValue == -1) {

                        return "sin -x";

                    }

                }

            }
            else if (newSituationPresent == TrigSituations.NEG_SIN) {

                newCoefValue =  coefValue / (double)innerCoefValue;

                if (newCoefValue % 1 != 0) {

                    if (innerCoefValue != -1 && innerCoefValue != 1) { 
                        
                        return "(" + doubleToFraction(newCoefValue) + ")sin " + innerCoefValue + "x"; // returns fraction integrated

                    }
                    else if (innerCoefValue == 1) {

                        return "(" + doubleToFraction(newCoefValue) + ")sin x"; // returns fraction integrated

                    }
                    else if (innerCoefValue == -1) {

                        return "(" + doubleToFraction(newCoefValue) + ")sin -x"; // returns fraction integrated


                    }

                }
                else if (newCoefValue % 1 == 0 && newCoefValue != -1 && newCoefValue != 1) {

                    if (innerCoefValue != -1 && innerCoefValue != 1) {

                        return Math.round(newCoefValue) + "sin " + innerCoefValue + "x";

                    }
                    else if (innerCoefValue == 1) {

                        return Math.round(newCoefValue) + "sin x";

                    }
                    else if (innerCoefValue == -1) {

                        return Math.round(newCoefValue) + "sin -x";

                    }

                }   
                else if (newCoefValue == -1) { // when the coefficent is negative

                    if (innerCoefValue != -1 && innerCoefValue != 1) {

                        return "-sin " + innerCoefValue + "x";

                    }
                    else if (innerCoefValue == 1) {

                        return "-sin x";

                    }
                    else if (innerCoefValue == -1) {

                        return "-sin -x";

                    }

                }
                else if (newCoefValue == 1) { // when the coefficent is positive

                    if (innerCoefValue != -1 && innerCoefValue != 1) {

                        return "sin" + innerCoefValue + "x";

                    }
                    else if (innerCoefValue == 1) {

                        return "sin x";

                    }
                    else if (innerCoefValue == -1) {

                        return "sin -x";

                    }

                }

            }

            return "Error In Output Formatting";
        
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

    public void setTrigEncounterPrecedence(Integer orderReceived) { trigEncounterPrecedence = orderReceived; } 

    // - - - Getter Methods - - - //

    public double getCoefValue() { return coefValue; }

    public Integer getExpValue() { return expValue; }

    public Integer getInnerCoefValue() { return innerCoefValue; }

    public int[] getIntegralBounds() { return integralBounds; }

    public TrigSituations getTrigSituation() { return situationPresent; }

    public Integer getTrigEncounterPrecedence() { return trigEncounterPrecedence; }
    
}
