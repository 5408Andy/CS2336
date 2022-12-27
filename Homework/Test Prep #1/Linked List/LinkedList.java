public class LinkedList {
    
    private Node headNode;

    LinkedList(Node headNode) {

        this.headNode = headNode; 

    }

    public void insertNode(Node receivedNode) {

        if (headNode == null) {

            headNode = receivedNode;

        } 
        else {

            Node currentNode = headNode;

            while (currentNode.getNextNode() != null) {

                currentNode = currentNode.getNextNode();
             
            }

            currentNode.setNextNode(receivedNode);

        }

    }

    public void insertNodeAtStart(Node receivedNode) {

        if (headNode == null) {

            headNode = receivedNode;

        }
        else {

            Node tempNode = headNode;

            headNode = receivedNode;

            headNode.setNextNode(tempNode);

        }

    }

    public void removeNode(int dataValue) {

        Node tempNode = headNode;
        Node prevNode = null;
 
        if (tempNode != null && tempNode.getDataValue() == dataValue) { // checks if head is the player that needs to be deleted

            headNode = tempNode.getNextNode();

            return;

        }

        while (tempNode != null && tempNode.getDataValue() != dataValue) { // keep traversing until node is found
            
            prevNode = tempNode;
            tempNode = tempNode.getNextNode();
    
        }

        if (tempNode == null) { // integer is not detected within linked list
            
            return;

        }

        prevNode.setNextNode(tempNode.getNextNode()); // delete player and connect new link 
        
    }

    public boolean checkListSorted() {

        boolean sortedList = true;

        if (headNode == null) {

            sortedList = false; // list does not exist;

        }
        else {
        
            Node currentNode = headNode;

            while (currentNode != null && currentNode.getNextNode() != null) {

                if (currentNode.getDataValue() > currentNode.getNextNode().getDataValue()) {

                    sortedList = false;
                    break; // list is not in order

                }

                currentNode = currentNode.getNextNode();
                
            }

        }

        return sortedList;

    }

    public void sortNode() {

        if (headNode == null) {

            System.out.println("List does not exist!");
            return;

        }
        else {
            
            Node currentNode = headNode;

            while (checkListSorted() == false) {

                if (currentNode != null && currentNode.getNextNode() != null) { // as long as the current and next to current node is empty, compare nodes to see if in orde

                    //System.out.println(currentNode.getDataValue() + "  " + currentNode.getNextNode().getDataValue());

                    if (currentNode.getDataValue() > currentNode.getNextNode().getDataValue()) { // if the current node string is greater than the node next to it

                        insertNode(new Node(currentNode.getDataValue())); // append a copy of the player 
                        removeNode(currentNode.getDataValue()); // delete the earlier instance of the player
                        
                        //printList(headNode);

                

                    }
                    
                    currentNode = currentNode.getNextNode();
                    
                }
                if (currentNode.getNextNode() == null) {
                    
                    currentNode = headNode;
    
                }

            }

        }

    }

    public void printList(Node currentNode) {

        if (currentNode != null) {

            System.out.println(currentNode.getDataValue());

            printList(currentNode.getNextNode());

        }

        return;

    }

    public Node getHeadNode() throws NullPointerException {

        return headNode;

    }

}
