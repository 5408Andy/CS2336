/* 
 * Project 2: Super Mario Sluggers Part 2 with Linked Lists and Classes
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 9/27/2022
 * Class & Section: CS - 2366.003
 */

public class Node {
    
    private Player playerData;

    private Node nextNode;
    
    Node(Player playerDataNew) {

        playerData = playerDataNew;

        nextNode = null; 

    } // Node - Constructor

    // - - - Setter Methods - - - //

    public void setNextNode(Node nextNodeReceived) {

        nextNode = nextNodeReceived;

    } // setPlayerData

    // - - - Getter Methods - - - //

    public Player getPlayerData() {

        return playerData;

    } // getPlayerData

    public Node getNextNode() {

        return nextNode;

    } // setNextNode
    
}
