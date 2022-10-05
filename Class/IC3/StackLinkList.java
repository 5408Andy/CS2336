/* 
 * In Class Assignment #3
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/5/2022
 * Class & Section: CS - 2366.003
 */

public class StackLinkList {
    
    Node headNode;

    StackLinkList() { 

        headNode = null;

    }

    public void push(String dataNew) // insert at the beginning
    {
        // create new node temp and allocate memory
        Node tempNode = new Node(dataNew);
 
        // put top reference into temp link
        tempNode.setNextNode(headNode);
        // update top reference
        headNode = tempNode;
    }

    public void pop() // remove at the beginning
    {
        
 
        // update the top pointer to point to the next node
        headNode = headNode.getNextNode();

    }

    public boolean isEmpty() { return headNode == null; }

    public String getHeadNode() {

        if (!isEmpty()) {
            return headNode.getData();
        }
        else {
            
            return "Empty";
        }

    }

    public void printAllNodes() {

        Node currentNode = headNode;
        while (currentNode != null) {

            // print node data
            System.out.println(currentNode.getData());

            // assign temp link to temp
            currentNode = currentNode.getNextNode();
        }

    }

}
