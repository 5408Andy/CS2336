/* 
 *
 */

public class Node<G extends Comparable<G>> {
    
    private G termData;

    private Node<G> leftNode;
    private Node<G> rightNode;

    Node(G newTermData) {

        termData = newTermData;

        leftNode = null;
        rightNode = null;

    } // Node - Constructor

    // - - - Comparable Interface - - - //

    public int compareTo(Node<G> nodeReceived) { return termData.compareTo(nodeReceived.getTermData()); } // compareTo

    // - - - Setter Methods - - - //

    public void setLeftNode(Node<G> leftNodeReceived) { leftNode = leftNodeReceived; } // setLeftNode

    public void setRightNode(Node<G> rightNodeReceived) { rightNode = rightNodeReceived; } // setRightNode

    public void setTermData(G termDataReceived) { termData = termDataReceived; }

    // - - - Getter Methods - - - //

    public G getTermData() { return termData; } // getPlayerData

    public Node<G> getLeftNode() { return leftNode; } // setLeftNode

    public Node<G> getRightNode() { return rightNode; } // setRightNode

}
