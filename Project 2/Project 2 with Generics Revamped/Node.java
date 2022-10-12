/* 
 * Project 2: Super Mario Sluggers Part 2 with Linked Lists and Classes
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 9/27/2022
 * Class & Section: CS - 2366.003
 */

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
