/* 
 *
 */

 // Storage
import java.util.ArrayList;

public class BinTree<G extends Comparable<G>> {
    
    private Node<G> rootNode;

    BinTree() {

        rootNode = null;

    } // BinTree - Constructor

    // - - - Insertion - - - // 

    public void insertData(G termDataReceived) {

        // creates a node and stores the received data into it 
        // if the root node is null, then insert the new node at the root node
        rootNode = insertData(rootNode, new Node<G>(termDataReceived));

    } // insertData

    private Node<G> insertData(Node<G> currentNode, Node<G> newNode) {

        if (currentNode == null) { // if the root node is null, then insert the new node at the root node

            return newNode;

        }
        else {

            int compareValue = newNode.compareTo(currentNode); // stores the integer result of the comparable term

            if (compareValue < 0) { // if the current node is less than the new node to be inserted, traverse left

                currentNode.setLeftNode(insertData(currentNode.getLeftNode(), newNode));

            } 
            else if (compareValue > 0) { // if the current node is more than the new node to be inserted, traverse right

                currentNode.setRightNode(insertData(currentNode.getRightNode(), newNode));

            }

        }
        
        return currentNode;

    } // insertData - Private and Overloaded

    // - - - Searching - - - // 

    public boolean searchData(G termDataReceived) {

        if (searchData(rootNode, termDataReceived) == null) { // if the search method returns null, it means it could not be found

            return false;

        }

        return true; // the data could be found within the binary tree

    } // searchData

    private Node<G> searchData(Node<G> currentNode, G termDataReceived) {
        
        Node<G> comparisonNode = new Node<G>(termDataReceived);

        if (currentNode.compareTo(comparisonNode) == 0) { // if the data of the current node matches what we are looking for stop recursion

            return currentNode;

        }
        else if (currentNode.compareTo(comparisonNode) > 0  && currentNode.getLeftNode() != null) { // traverse left cause the current node is bigger than we are looking for

            return searchData(currentNode.getLeftNode(), termDataReceived);

        }
        else if (currentNode.compareTo(comparisonNode) < 0 && currentNode.getRightNode() != null) { // traverse right cause the current node is smaller than we are looking for

            return searchData(currentNode.getRightNode(), termDataReceived);

        }
       
        // data could not be found in the binary tree
        return null;

    } // searchData - Private and Overloaded
    
    // - - - Delete - - - // 

    private G minimumData(Node<G> currentNode) {

        G minimumData = currentNode.getTermData();
        while (currentNode.getLeftNode() != null) {

            minimumData = currentNode.getLeftNode().getTermData();

            currentNode = currentNode.getLeftNode();

        }

        return minimumData;

    } // minimumData

    public void deleteData(G termDataReceived) { rootNode = deleteData(rootNode, termDataReceived); }

    private Node<G> deleteData(Node<G> currentNode, G termDataReceived) {

        Node<G> comparisonNode = new Node<G>(termDataReceived);

        if (currentNode == null) {

            return currentNode;

        }
        
        int compareValue = comparisonNode.compareTo(currentNode); // stores the integer result of the comparable term

        if (compareValue < 0) { // if the current node is less than the new node to be inserted, traverse left

            currentNode.setLeftNode(deleteData(currentNode.getLeftNode(), termDataReceived));

        } 
        else if (compareValue > 0) { // if the current node is more than the new node to be inserted, traverse right

            currentNode.setRightNode(deleteData(currentNode.getRightNode(), termDataReceived));

        }
        else {

            if (currentNode.getLeftNode() == null) {

                return currentNode.getRightNode();

            }
            else if (currentNode.getRightNode() == null) {

                return currentNode.getLeftNode();

            }


            System.out.println("Work");
            currentNode.setTermData(minimumData(currentNode.getRightNode()));

            currentNode.setRightNode(deleteData(currentNode.getRightNode(), currentNode.getTermData()));

        }    
        
        return currentNode;

    } // deleteData
    

    // - - - In Order Traversal of Binary Tree - - - // 

    public ArrayList<G> traverseInOrder() { 
        
        ArrayList<G> listOfData = new ArrayList<G>();

        traverseInOrder(rootNode, listOfData); 
        
        return listOfData;
    
    } // traverseInOrder

    private void traverseInOrder(Node<G> currentNode, ArrayList<G> listOfData) { 

        if (currentNode == null) { // break out of the recursive loop if the node is null
            
            return;

        }
        
        traverseInOrder(currentNode.getLeftNode(), listOfData);

        //System.out.println(currentNode.getTermData());
        listOfData.add(currentNode.getTermData());

        traverseInOrder(currentNode.getRightNode(), listOfData);
    
    } // traverseInOrder - Private and Overloaded

    public void printInOrder() { printInOrder(rootNode); }

    private void printInOrder(Node<G> currentNode) { 

        if (currentNode == null) { // break out of the recursive loop if the node is null
            
            return;

        }
        
        printInOrder(currentNode.getLeftNode());

        System.out.print(currentNode.getTermData() + " ");

        printInOrder(currentNode.getRightNode());
    
    } // traverseInOrder - Private and Overloaded

    // - - - Getter Methods - - - //

    public Node<G> getRootNode() {
        
        return rootNode;

    } // getHeadNode

}