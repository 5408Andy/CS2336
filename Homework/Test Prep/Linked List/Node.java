public class Node {

    private int dataValue;

    private Node nextNode;

    Node (int dataValue) {

        this.dataValue = dataValue;

        nextNode = null;

    }

    public void setNextNode(Node nextNodeReceived) {

        nextNode = nextNodeReceived;

    } // setPlayerData

    // - - - Getter Methods - - - //

    public int getDataValue() {

        return dataValue;

    } // getDataValue

    public Node getNextNode() {

        return nextNode;

    } // setNextNode

}