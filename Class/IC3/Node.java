/* 
 * In Class Assignment #3
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/5/2022
 * Class & Section: CS - 2366.003
 */

public class Node {
    
    private String data;

    private Node nextNode;

    Node(String dataNew) {

        data = dataNew;

        nextNode = null; 

    } // Node - Constructor


    // getter functions
    public String getData() { return data; }

    public Node getNextNode() { return nextNode; }

    // setter functions
    public void setNextNode(Node nextNodeNew) { nextNode = nextNodeNew; }

}
