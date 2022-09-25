public class Ant extends Creature {

    protected int breedNumber;

    Ant() {

        breedNumber = 1;

    }

    // - - - - - - - - - - - - - - - - MOVEMENT OF THE ANT - - - - - - - - - - - - - - - - //

    public String move(String moveAntInfo) {

        String orthogonalBeetleDistances = moveAntInfo;

        int[] closestOrthogonalBeetle = new int[1];
        int[] clearPathwayIndex = new int[1];

        if (checkAntForZeroOrthogonalBeetles(orthogonalBeetleDistances) == true) {

            return "Nothing"; // there are zero orthogonal beetles so ant does not have to move

        }
        else {

            if (checkAntForTiesOnDistances(orthogonalBeetleDistances, closestOrthogonalBeetle) == true) {
                
                if (clearPathway(orthogonalBeetleDistances, clearPathwayIndex) == true) {

                    if (clearPathwayIndex[0] == 0) { // since ant has tied closest beetles, it will move towards a clear path

                        return "North"; 

                    }
                    else if(clearPathwayIndex[0] == 1) {

                        return "East";

                    }
                    else if(clearPathwayIndex[0] == 2) {

                        return "South";

                    }
                    else if(clearPathwayIndex[0] == 3) {

                        return "West";

                    }

                }
                else {

                    int chosenDirection = furthestBeetle(orthogonalBeetleDistances);

                    if (chosenDirection == 0) { // since the ant is surrounded, it will move towards the farthest beetle

                        return "North";
    
                    }
                    else if (chosenDirection == 1) {
        
                        return "East";
        
                    }
                    else if (chosenDirection == 2) {
        
                        return "South";
        
                    }
                    else if (chosenDirection == 3) {
        
                        return "West";
        
                    }

                }

            }
            else {
                
                if (closestOrthogonalBeetle[0] == 0) { // since no ties on closest beetle, ant will move in opposite direction of closest beetle

                    return "South";

                }
                else if (closestOrthogonalBeetle[0] == 1) {

                    return "West";

                }
                else if (closestOrthogonalBeetle[0] == 2) {

                    return "North";

                }
                else if (closestOrthogonalBeetle[0] == 3) {

                    return "East";

                }

            }

        }

        return "Nothing";

    } // move

    public Boolean checkAntForZeroOrthogonalBeetles(String orthogonalBeetleDistances) {

        int numOfZeroOrthogonalBeetles = 0;

        for (int orthogonalBeetleDistancesIndex = 0; orthogonalBeetleDistancesIndex < 4; orthogonalBeetleDistancesIndex++) { // counts the number of zero orthogonal beetles

            if (stringToInteger(orthogonalBeetleDistances.substring(orthogonalBeetleDistancesIndex, orthogonalBeetleDistancesIndex + 1)) ==  0) {

                numOfZeroOrthogonalBeetles++;
                
            }

        }

        if (numOfZeroOrthogonalBeetles >= 0 && numOfZeroOrthogonalBeetles <= 3) {

            return false; // returns false since there were orthogonal beetles

        }

        
        return true; // there are no orthogonal beetles

    } // checkAntForZeroOrthogonalBeetles

    public Boolean checkAntForTiesOnDistances(String orthogonalBeetleDistances, int[] closestOrthogonalBeetle) {

        int distanceAtIndex = 0;
        closestOrthogonalBeetle[0] = 0;

        for (int orthogonalBeetleDistancesIndex = 0; orthogonalBeetleDistancesIndex < 4; orthogonalBeetleDistancesIndex++) { // finds a nonzero so we can properly find the closest orthogonal beetle
            
            if (stringToInteger(orthogonalBeetleDistances.substring(orthogonalBeetleDistancesIndex, orthogonalBeetleDistancesIndex + 1)) != 0) {
                
                distanceAtIndex = stringToInteger(orthogonalBeetleDistances.substring(orthogonalBeetleDistancesIndex, orthogonalBeetleDistancesIndex + 1));
                closestOrthogonalBeetle[0] = orthogonalBeetleDistancesIndex;
                break;

            }

        }

        for (int orthogonalBeetleDistancesIndex = 0; orthogonalBeetleDistancesIndex < 4; orthogonalBeetleDistancesIndex++) { // finds closest beetle from orthogonals

            if (stringToInteger(orthogonalBeetleDistances.substring(orthogonalBeetleDistancesIndex, orthogonalBeetleDistancesIndex + 1)) == 0) { // skips directions with no orthogonal beetle

                continue;

            }
            else if (stringToInteger(orthogonalBeetleDistances.substring(orthogonalBeetleDistancesIndex, orthogonalBeetleDistancesIndex + 1)) < distanceAtIndex) { // finds closest orthogonal beetle

                distanceAtIndex = stringToInteger(orthogonalBeetleDistances.substring(orthogonalBeetleDistancesIndex, orthogonalBeetleDistancesIndex + 1)); 
                closestOrthogonalBeetle[0] = orthogonalBeetleDistancesIndex;
 
            }

        } 

        int distanceOfClosestOrthogonalAnt = stringToInteger(orthogonalBeetleDistances.substring(closestOrthogonalBeetle[0], closestOrthogonalBeetle[0] + 1));
        for (int orthogonalAntDistancesIndex = 0; orthogonalAntDistancesIndex < 4; orthogonalAntDistancesIndex++) { // finds ties of closest orthogonal beetle

            if (orthogonalAntDistancesIndex != closestOrthogonalBeetle[0] && stringToInteger(orthogonalBeetleDistances.substring(orthogonalAntDistancesIndex, orthogonalAntDistancesIndex + 1)) == distanceOfClosestOrthogonalAnt) {
                
                return true; // there was a tie for closest orthogonal beetle

            }

        }

        return false; // no tie for closest orthogonal beetle

    } // checkBeetleForTiesOnDistances

    public Boolean clearPathway(String orthogonalBeetleDistances, int[] clearPathwayIndex) {

        for (int orthogonalBeetleDistancesIndex = 0; orthogonalBeetleDistancesIndex < 4; orthogonalBeetleDistancesIndex++) { // finds a clear pathway for ant to travel of no beetle

            if (stringToInteger(orthogonalBeetleDistances.substring(orthogonalBeetleDistancesIndex, orthogonalBeetleDistancesIndex + 1)) == 0) {

                clearPathwayIndex[0] = orthogonalBeetleDistancesIndex;
                
                return true; // there is a clear pathway for ant to travel

            }

        }

        return false; // there are beetles in all 4 directions

    }

    public int furthestBeetle(String orthogonalBeetleDistances) {

        int distanceOfFurthestBeetle = stringToInteger(orthogonalBeetleDistances.substring(0, 1));
        int distanceOfFurthestBeetleIndex = 0;

        for (int orthogonalBeetleDistancesIndex = 0; orthogonalBeetleDistancesIndex < 4; orthogonalBeetleDistancesIndex++) { // loops through and finds furthest beetle

            if (stringToInteger(orthogonalBeetleDistances.substring(orthogonalBeetleDistancesIndex, orthogonalBeetleDistancesIndex + 1)) > distanceOfFurthestBeetle) {
            
                distanceOfFurthestBeetle = stringToInteger(orthogonalBeetleDistances.substring(orthogonalBeetleDistancesIndex, orthogonalBeetleDistancesIndex + 1));
                distanceOfFurthestBeetleIndex = orthogonalBeetleDistancesIndex;

            }

        }

        return distanceOfFurthestBeetleIndex; // returns index of furthest beetle

    }

    // - - - - - - - - - - - - - - - - BREEDING OF ANT - - - - - - - - - - - - - - - - //

    public Boolean breed() {
        
        if (breedNumber == 3) { // when the breed number is 3, the ant will breed
            
            breedNumber = 0;
            return true;

        }

        return false;

    } // breed

    // - - - - - - - - - - - - - - - - MISC OF ANT - - - - - - - - - - - - - - - - //

    public void incrementTurns() {
        
        breedNumber++;
        
    } // incrementTurns

}
