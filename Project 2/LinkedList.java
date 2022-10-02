/* 
 * Project 2: Super Mario Sluggers Part 2 with Linked Lists and Classes
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 9/27/2022
 * Class & Section: CS - 2366.003
 */
import java.lang.System;
public class LinkedList {
    
    private Node headNode; 

    // - - - Methods - - - //

    public void appendPlayer(Player playerReceived) {

        Node newNode = new Node(playerReceived);

        if (headNode == null) { // if the list is empty, set this newly created node to be the head

            headNode = newNode;

        }
        else { // if not empty, node will be put at the end of the list

            Node currentNode = headNode; // starting point for the traversal

            while (currentNode.getNextNode() != null) { // traveres list until it reaches the end

                currentNode = currentNode.getNextNode();

            }

            currentNode.setNextNode(newNode); // new node is put at the end of the list

        }

    } // appendPlayer

    public void deletePlayer(String playerName) {

        Node tempNode = headNode;
        Node prevNode = null;

        if (tempNode != null && tempNode.getPlayerData().getPlayerName() == playerName) { // checks if head is the player that needs to be deleted

            headNode = tempNode.getNextNode();

            return;

        }

        while (tempNode != null && tempNode.getPlayerData().getPlayerName() != playerName) { // keep traversing until player is found

            prevNode = tempNode;
            tempNode = tempNode.getNextNode();

        }

        if (tempNode == null) { // String is not detected within linked list
            
            return;

        }

        prevNode.setNextNode(tempNode.getNextNode()); // delete player and connect new link 

    } // deletePlayer

    public void checkForMultipleEntries() {

        Node searchNode;
        Node currentNode;

        if (headNode != null) {
            
            searchNode = headNode;
            currentNode = headNode;

        }
        else {

            return;

        }

        while (searchNode != null) {

            while(currentNode != null) {

                //System.out.println(currentNode.getPlayerData().getPlayerNum() + " " + searchNode.getPlayerData().getPlayerNum() + " | " + currentNode.getPlayerData().getPlayerName() + " " + searchNode.getPlayerData().getPlayerName());

                if (currentNode.getPlayerData().getPlayerNum() != searchNode.getPlayerData().getPlayerNum() /* as long as nodes are not the same */ && currentNode.getPlayerData().getPlayerName().compareTo(searchNode.getPlayerData().getPlayerName() /* as long as nodes have same name */) == 0) { // if the names of nodes match and they are not the same node
 
                    currentNode.getPlayerData().addPlayerHitStat(searchNode.getPlayerData().getPlayerHitStat()); // adds the hit stat of search node to the current node

                    currentNode.getPlayerData().addPlayerOutStat(searchNode.getPlayerData().getPlayerOutStat()); // adds the out stat of search node to the current node

                    currentNode.getPlayerData().addPlayerStrikeOutStat(searchNode.getPlayerData().getPlayerStrikeOutStat()); // adds the strike out stat of search node to the current node

                    currentNode.getPlayerData().addPlayerWalkStat(searchNode.getPlayerData().getPlayerWalkStat()); // adds the walk stat of search node to current node

                    currentNode.getPlayerData().addPlayerHitByPitchStat(searchNode.getPlayerData().getPlayerHitByPitchStat()); // adds the hit by pitch stat of search node to current node

                    currentNode.getPlayerData().addPlayerSacrificeStat(searchNode.getPlayerData().getPlayerSacrificeStat()); // adds the player sacrifice stat of search node to current node

                    deletePlayer(searchNode.getPlayerData().getPlayerName());

                }

                currentNode = currentNode.getNextNode();

            }

            currentNode = headNode;
            searchNode = searchNode.getNextNode();

        }

        return;

    } // checkForMultipleEntries

    public void sortPlayers() {

        Node currentNode = headNode;

        while(checkListInOrder() == false) {

            if (currentNode != null && currentNode.getNextNode() != null) { // as long as the current and next to current node is empty, compare nodes to see if in order

                String currentNodeStr = currentNode.getPlayerData().getPlayerName();
                String currentNodeNextStr = currentNode.getNextNode().getPlayerData().getPlayerName();

                int compareValue = currentNodeStr.compareTo(currentNodeNextStr);

                if (compareValue > 0) { // if the current node string is greater than the node next to it

                    appendPlayer(currentNode.getPlayerData()); // append a copy of the player 
                    deletePlayer(currentNodeStr); // delete the earlier instance of the player

                }

                currentNode = currentNode.getNextNode();

            }
            if (currentNode.getNextNode() == null) { // if at the end of the linked list, go back to the beginning of the list until list is sorted

                currentNode = headNode;

            }
       
        }

        return;

    } // sortPlayers

    public Boolean checkListInOrder() {

        Node currentNode = headNode;

        while (currentNode != null && currentNode.getNextNode() != null) {

            String currentNodeStr = currentNode.getPlayerData().getPlayerName();
            String nextNodeToCurrentStr = currentNode.getNextNode().getPlayerData().getPlayerName();

            int compareValue = currentNodeStr.compareTo(nextNodeToCurrentStr);

            if (compareValue > 0) {

                return false;

            }

            currentNode = currentNode.getNextNode();

        }

        return true;

    } // checkListInOrder

    public void printStatsRecursively(Node currentNode) {

        if (currentNode != null) {

            System.out.println(currentNode.getPlayerData().getPlayerName());

            printStatsRecursively(currentNode.getNextNode());

        }

        return; // breaks out of recursive function if the node is null

    } // printStatsRecursively

    // - - - Getter Methods - - - //

    public Node getHeadNode() {

        return headNode;

    } // getHeadNode

    // - - - Testing - - - //
    
}
