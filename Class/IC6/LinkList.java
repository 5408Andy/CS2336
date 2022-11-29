/* 
 * Project 2: Super Mario Sluggers Part 2 with Linked Lists and Classes
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 9/27/2022
 * Class & Section: CS - 2366.003
 */

import java.util.ArrayList; // array lists are ONLY USED FOR STORING THE LEADERS, according to a PIAZZA post, Professor Smith has allowed us to use arraylists to help with storing leaders

public class LinkList<PlayerType extends Comparable<PlayerType>> {
    
    private Node<PlayerType> headNode;
    
    // - - - Methods - - - //

    public void appendPlayer(PlayerType playerReceived) { // puts the piece of data at the end of the link list

        Node<PlayerType> newNode = new Node<PlayerType>(playerReceived);

        if (headNode == null) { // if the list is empty, set this newly created node to be the head

            headNode = newNode;

        }
        else { // if not empty, node will be put at the end of the list

            Node<PlayerType> currentNode = headNode; // starting point for the traversal

            while (currentNode.getNextNode() != null) { // traveres list until it reaches the end

                currentNode = currentNode.getNextNode();

            }

            currentNode.setNextNode(newNode); // new node is put at the end of the list

        }

    } // appendPlayer

    public void deletePlayer(int numNodeReceived) { // deletes the node at where the traversal is at

        Node <PlayerType> tempNode = headNode;
        Node <PlayerType> prevNode = null;
 
        if (tempNode != null && tempNode.getNumNode() == numNodeReceived) { // checks if head is the player that needs to be deleted

            headNode = tempNode.getNextNode();

            return;

        }

        while (tempNode != null && tempNode.getNumNode() != numNodeReceived) { // keep traversing until node is found

            prevNode = tempNode;
            tempNode = tempNode.getNextNode();

        }

        if (tempNode == null) { // integer is not detected within linked list
            
            return;

        }

        prevNode.setNextNode(tempNode.getNextNode()); // delete player and connect new link 
    
    } // deletePlayer

    public void checkForMultipleEntries() { // deletes duplicate data while combining it into one

        Node<PlayerType> searchNode;
        Node<PlayerType> currentNode;

        if (headNode != null) {
            
            searchNode = headNode;
            currentNode = headNode;

        }
        else {

            return;

        }

        while (searchNode != null) {

            while(currentNode != null) {
                
                if (currentNode.getNumNode() != searchNode.getNumNode() /* as long as nodes are not the same */ && currentNode.compareTo(searchNode) == 0 /* as long as nodes have same name */) { // if the names of nodes match and they are not the same node

                    // duplicate name's stats are combined into one node

                    deletePlayer(currentNode.getNumNode()); // deletes the duplicate data

                }

                currentNode = currentNode.getNextNode();

            }

            
            currentNode = headNode;
            searchNode = searchNode.getNextNode();

        }

        return;

    } // checkForMultipleEntries

    public boolean checkListInOrder(boolean leastToGreatest) { // makes sure the link list is from greatest to least

        Node<PlayerType> currentNode = headNode;

        while (currentNode != null && currentNode.getNextNode() != null) {

            if (leastToGreatest == true && currentNode.compareTo(currentNode.getNextNode()) > 0) {

                return false; // the list was not from least to greatest

            }
            else if (leastToGreatest == false && currentNode.compareTo(currentNode.getNextNode()) < 0) {

                return false; // the list was not from greatest to least

            }
            
            currentNode = currentNode.getNextNode();

        }

        return true;

    } // checkListInOrder

    public void sortPlayers(boolean leastToGreatest) { // sort players from least to greatest or greatest to least based on the parameter input

        Node<PlayerType> currentNode = headNode;

        while(checkListInOrder(leastToGreatest) == false) {

            if (currentNode != null && currentNode.getNextNode() != null) { // as long as the current and next to current node is empty, compare nodes to see if in orde

                if (leastToGreatest == true && currentNode.compareTo(currentNode.getNextNode()) > 0) { // if the current node string is greater than the node next to it

                    appendPlayer(currentNode.getPlayerData()); // append a copy of the player 

                    deletePlayer(currentNode.getNumNode()); // delete the earlier instance of the player

                }
                else if (leastToGreatest == false && currentNode.compareTo(currentNode.getNextNode()) < 0) {
                    
                    appendPlayer(currentNode.getPlayerData()); // append a copy of the player 

                    deletePlayer(currentNode.getNumNode()); // delete the earlier instance of the player

                }
                
                currentNode = currentNode.getNextNode();

            }
            if (currentNode.getNextNode() == null) { // if at the end of the linked list, go back to the beginning of the list until list is sorted

                currentNode = headNode;

            }
       
        }

        return;

    } // sortPlayers

    public void printStatsRecursively(Node<PlayerType> currentNode) {

        if (currentNode != null) {

            System.out.println(currentNode.getPlayerData());

            printStatsRecursively(currentNode.getNextNode());

        }

        return; // breaks out of recursive function if the node is null

    } // printStatsRecursively

    public ArrayList<PlayerType> outputDataIntoArrayList() { // put the data from linked list into an array list

        ArrayList<PlayerType> playerDataList = new  ArrayList<PlayerType>();

        Node<PlayerType> currentNode = headNode;

        while (currentNode != null) {
            
            playerDataList.add(currentNode.getPlayerData()); // add data from nodes to an array list

            currentNode = currentNode.getNextNode();

        } 

        return playerDataList; // breaks out of recursive function if the node is null

    } // printStatsRecursively

    // - - - Getter Methods - - - //

    public Node<PlayerType> getHeadNode() {

        return headNode;

    } // getHeadNode

    // - - - Testing - - - //

}