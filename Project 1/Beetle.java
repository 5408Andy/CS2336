public class Beetle extends Creature {

    protected int breedNumber;
    protected int starveNumber;

    Beetle() { 

        breedNumber = 1;
        starveNumber = 1;

    }

    // - - - - - - - - - - - - - - - - MOVEMENT OF THE BEETLE - - - - - - - - - - - - - - - - //

    public String move(String moveBeetleInfo) {

        String orthogonalAntDistances = parseBeetleString(moveBeetleInfo)[0];

        String orthogonalAntNeighbors = parseBeetleString(moveBeetleInfo)[1];
        
        String edgeDistances = parseBeetleString(moveBeetleInfo)[2];

        int[] highestEdgeDistanceIndex = new int[1];
        int[] closestOrthogonalAnt = new int[1];

        if (checkBeetleForZeroOrthogonalAnts(orthogonalAntDistances, edgeDistances, highestEdgeDistanceIndex) == false) {

            if (checkBeetleForTiesOnDistances(orthogonalAntDistances, closestOrthogonalAnt) == true) {

                int chosenDirection = checkBeetleForTiesOnNeighbors(orthogonalAntNeighbors, orthogonalAntDistances, closestOrthogonalAnt);

                if (chosenDirection == 0) { // since there is a tie on closest distance, the tie breaker will be whoever has greatest amount of neighbors

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
            else {

                if (closestOrthogonalAnt[0] == 0) { // since there is no ties in closest distance, beetle will go to closest orthogonal nat

                    return "North";
    
                }
                else if (closestOrthogonalAnt[0] == 1) {
    
                    return "East";
    
                }
                else if (closestOrthogonalAnt[0] == 2) {
    
                    return "South";
    
                }
                else if (closestOrthogonalAnt[0] == 3) {
    
                    return "West";
    
                }

            }

        }
        else { // since there are no orthognal ants, the beetle will travel to the farthest edge

            if (highestEdgeDistanceIndex[0] == 0) {

                return "North";

            }
            else if (highestEdgeDistanceIndex[0] == 1) {

                return "East";

            }
            else if (highestEdgeDistanceIndex[0] == 2) {

                return "South";

            }
            else if (highestEdgeDistanceIndex[0] == 3) {

                return "West";

            }

        }

        return "Nothing";

    }

    public Boolean checkBeetleForZeroOrthogonalAnts(String orthogonalAntDistances, String edgeDistances, int[] highestEdgeDistanceIndex) {

        int numOfZeroOrthogonalAnts = 0;

        for (int orthogonalAntDistancesIndex = 0; orthogonalAntDistancesIndex < 4; orthogonalAntDistancesIndex++) { // count the number of zero orthogonal ants 

            if (stringToInteger(orthogonalAntDistances.substring(orthogonalAntDistancesIndex, orthogonalAntDistancesIndex + 1)) ==  0) {

                numOfZeroOrthogonalAnts++;

            }

        }

        if (numOfZeroOrthogonalAnts >= 0 && numOfZeroOrthogonalAnts <= 3) { // returns false if there is an orthogonal ant

            return false; // there is an orthogonal ant

        }

        if (numOfZeroOrthogonalAnts == 4) { // executes if there is zero orthogonal ants

            int highestEdgeDistance = stringToInteger(edgeDistances.substring(0, 1));
            highestEdgeDistanceIndex[0] = 0;

            for (int edgeDistancesIndex = 1; edgeDistancesIndex < 4; edgeDistancesIndex++) { // finds furthest edge, if ties for furthest edge, return using NESW priority

                if (stringToInteger(edgeDistances.substring(edgeDistancesIndex, edgeDistancesIndex + 1)) > highestEdgeDistance) {

                    highestEdgeDistance = stringToInteger(edgeDistances.substring(edgeDistancesIndex, edgeDistancesIndex + 1));
                    highestEdgeDistanceIndex[0] = edgeDistancesIndex;

                }

            }

        }

        return true; // returning true meaning program had to find the furthest edge

    } // checkBeetleForZeroOrthogonalAnts

    public Boolean checkBeetleForTiesOnDistances(String orthogonalAntDistances, int[] closestOrthogonalAnt) { 

        closestOrthogonalAnt[0] = 0;
        int distanceAtIndex = 0;

        for (int orthogonalAntDistancesIndex = 0; orthogonalAntDistancesIndex < 4; orthogonalAntDistancesIndex++) { // loop finds a value in the string which is not equal to zero, we will use this 1st nonzero as a number to compare
            
            if (stringToInteger(orthogonalAntDistances.substring(orthogonalAntDistancesIndex, orthogonalAntDistancesIndex + 1)) != 0) {
                
                distanceAtIndex = stringToInteger(orthogonalAntDistances.substring(orthogonalAntDistancesIndex, orthogonalAntDistancesIndex + 1));
                closestOrthogonalAnt[0] = orthogonalAntDistancesIndex;
                break;

            }

        }

        for (int orthogonalAntDistancesIndex = 0; orthogonalAntDistancesIndex < 4; orthogonalAntDistancesIndex++) { // finds closest beetle from orthogonals

            if (stringToInteger(orthogonalAntDistances.substring(orthogonalAntDistancesIndex, orthogonalAntDistancesIndex + 1)) == 0) { // skips directions that have no orthogonal ants

                continue;

            }
            if (stringToInteger(orthogonalAntDistances.substring(orthogonalAntDistancesIndex, orthogonalAntDistancesIndex + 1)) < distanceAtIndex) { // finds the closest orthogonal ant

                distanceAtIndex = stringToInteger(orthogonalAntDistances.substring(orthogonalAntDistancesIndex, orthogonalAntDistancesIndex + 1));
                closestOrthogonalAnt[0] = orthogonalAntDistancesIndex;
                
                
            }

        } 
        
        int distanceOfClosestOrthogonalAnt = stringToInteger(orthogonalAntDistances.substring(closestOrthogonalAnt[0], closestOrthogonalAnt[0] + 1));
        
        for (int orthogonalAntDistancesIndex = 0; orthogonalAntDistancesIndex < 4; orthogonalAntDistancesIndex++) { // finds ties of closest orthogonal ant

            if (orthogonalAntDistancesIndex != closestOrthogonalAnt[0] && stringToInteger(orthogonalAntDistances.substring(orthogonalAntDistancesIndex, orthogonalAntDistancesIndex + 1)) == distanceOfClosestOrthogonalAnt) {

                return true; // there were ties for closest orthogonal ant

            }

        }

        return false; // there were no ties for the closest orthogonal ant

    } // checkBeetleForTiesOnDistances

    public int checkBeetleForTiesOnNeighbors(String orthogonalAntNeighbors, String orthogonalAntDistances, int[] closestOrthogonalAnt) {

        Boolean[] tiedClosestAnts = new Boolean[4]; // boolean array which determines the closest tied ants, we don't want the amount of neighbors of the farther ants
        
        int distanceOfClosestOrthogonalAnt = stringToInteger(orthogonalAntDistances.substring(closestOrthogonalAnt[0], closestOrthogonalAnt[0] + 1));

        for (int orthogonalAntDistancesIndex = 0; orthogonalAntDistancesIndex < 4; orthogonalAntDistancesIndex++) { // finds ties of closest orthogonal ant

            if (stringToInteger(orthogonalAntDistances.substring(orthogonalAntDistancesIndex, orthogonalAntDistancesIndex + 1)) == distanceOfClosestOrthogonalAnt) {

                tiedClosestAnts[orthogonalAntDistancesIndex] = true;

            }
            else {

                tiedClosestAnts[orthogonalAntDistancesIndex] = false;

            }

        }
        
        int highestNeighbors = 0;
        int highestNeighborsIndex = 0;

        for (int tiedClosestAntsIndex = 0; tiedClosestAntsIndex < 4; tiedClosestAntsIndex++) { // loop finds a tied closest ant 

            if (tiedClosestAnts[tiedClosestAntsIndex] == true) {

                highestNeighbors = stringToInteger(orthogonalAntNeighbors.substring(tiedClosestAntsIndex, tiedClosestAntsIndex + 1));
                highestNeighborsIndex = tiedClosestAntsIndex;
                break;

            }

        }
    
        for (int orthogonalAntNeighborsIndex = 0; orthogonalAntNeighborsIndex < 4; orthogonalAntNeighborsIndex++) { // loop finds the closesest orthogonal with most amount of neighbors

            if (tiedClosestAnts[orthogonalAntNeighborsIndex] == true && stringToInteger(orthogonalAntNeighbors.substring(orthogonalAntNeighborsIndex, orthogonalAntNeighborsIndex + 1)) > highestNeighbors) {

                highestNeighbors = stringToInteger(orthogonalAntNeighbors.substring(orthogonalAntNeighborsIndex, orthogonalAntNeighborsIndex + 1));
                highestNeighborsIndex = orthogonalAntNeighborsIndex;

            }

        }
        
        return highestNeighborsIndex; // returns the closest orthogonal ant with most amount of neighbors (if tied for closest amount of orthogonal neighbors, return using NESW priority)

    } // checkBeetleForTiesOnNeighbors

    // - - - - - - - - - - - - - - - - STARVATION OF BEETLE - - - - - - - - - - - - - - - - //

    public Boolean starve() {

        if (starveNumber == 5) { // if have been 5 turns and not eaten, beetle will be directed to die

            starveNumber = 0;
            return true;

        }

        return false;

    } // starve

    public void eaten() { 
        
        // the starve number will be set to 0, allow the beetle to continue to live longer without starving
        starveNumber = 0;

    } // eaten

    // - - - - - - - - - - - - - - - - BREEDING OF BEETLE   - - - - - - - - - - - - - - - - //

    public Boolean breed() {

        if (breedNumber == 8) { // when the breed number is 3, the beetle will breed

            breedNumber = 0;
            return true;

        }

        return false;

    } // breed

    // - - - - - - - - - - - - - - - - MISC OF THE BEETLE   - - - - - - - - - - - - - - - - //

    public String[] parseBeetleString(String moveBeetleInfo) {

        String[] beetleInfo = new String[3];

        beetleInfo[0] = moveBeetleInfo.substring(0, moveBeetleInfo.indexOf(' ')); // extracts the distances of orthogonal ants
        moveBeetleInfo = moveBeetleInfo.substring(moveBeetleInfo.indexOf(' ') + 1);

        beetleInfo[1] = moveBeetleInfo.substring(0, moveBeetleInfo.indexOf(' ')); // extracts the neighbors of the beetle
        moveBeetleInfo = moveBeetleInfo.substring(moveBeetleInfo.indexOf(' ') + 1);

        beetleInfo[2] = moveBeetleInfo; // extracts furthest edge from beetle

        return beetleInfo;

    } // parseBeetleString

    public void incrementTurns() { 

        starveNumber++;
        breedNumber++;

    } // incrementTurns

}
