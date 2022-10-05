public class Node<PlayerType> {
    
    private PlayerType playerData;

    private Node<PlayerType> nextNode;

    Node(PlayerType playerDataNew) {

        playerData = playerDataNew;

        nextNode = null;

    } // Node - Constructor

    // - - - Setter Methods - - - //

    public void setNextNode(Node<PlayerType> nextNodeReceived) {

        nextNode = nextNodeReceived;

    } // setPlayerData

    // - - - Getter Methods - - - //

    public PlayerType getPlayerData() {

        return playerData;

    } // getPlayerData

    public Node<PlayerType> getNextNode() {

        return nextNode;

    } // setNextNode



}
