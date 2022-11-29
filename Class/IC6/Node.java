/* 
 * Project 2: Super Mario Sluggers Part 2 with Linked Lists and Classes
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 9/27/2022
 * Class & Section: CS - 2366.003
 */

public class Node<PlayerType extends Comparable<PlayerType>> {
    
    private PlayerType playerData;

    private Node<PlayerType> nextNode;

    private static int countNode;
    private int numNode;

    Node(PlayerType playerDataNew) {

        countNode++;
        numNode = countNode;

        playerData = playerDataNew;

        nextNode = null;

    } // Node - Constructor

    public int compareTo(Node<PlayerType> nodeReceived) {

        return playerData.compareTo(nodeReceived.getPlayerData());

    } // compareTo

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

    public int getNumNode() {

        return numNode;

    } // getNumNode

}
