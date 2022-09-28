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

    // - - - Testing - - - //

    public void TEMPORARY_printStats() { // PLEASE REMOVE ME

        Node currentNode = headNode;

        while (currentNode.getNextNode() != null) {

            System.out.println(currentNode.getPlayerData().getPlayerName());
            currentNode = currentNode.getNextNode();

        }

        System.out.println(currentNode.getPlayerData().getPlayerName());

    }

}
