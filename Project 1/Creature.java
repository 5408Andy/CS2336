public abstract class Creature {

    protected Boolean creatureMoved;
    protected Boolean creatureBorn;

    Creature() {

        creatureMoved = false;
        creatureBorn = true;

    }

    abstract String move(String moveCreatureInfo);

    abstract Boolean breed();

    abstract void incrementTurns();

    public void creatureHasMoved() { // sets to true when beetle has moved
        
        creatureMoved = true;

    } // beetleHasMoved

    public boolean getCreatureMoved() { // gets the value of member variable creatureMoved

        return creatureMoved;

    } // getBeetleMoved

    public void creatureMovedReset() { // sets the creaetureMoved to false

        creatureMoved = false;

    }

    public boolean getCreatureBorn() { // gets the value of member variable creatureMoved

        return creatureBorn;

    } // getBeetleMoved

    public void creatureNowOld() { // sets the creaetureMoved to false

        creatureBorn = false;

    }

    public int stringToInteger(String sampleStr) { // turns the string into integer

        return Integer.parseInt(sampleStr);

    }

}
