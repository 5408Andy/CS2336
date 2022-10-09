/* 
 * Project 2: Super Mario Sluggers Part 2 with Linked Lists and Classes
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 9/27/2022
 * Class & Section: CS - 2366.003
 */

import java.util.ArrayList; // arraylists in this program are only used to help store data from linked list, according to a PIAZZA post, Proffessor Smith has allowed us to use arraylists to help with storing leaders

public class LinkList<PlayerType extends Player> {
    
    private Node<PlayerType> headNode; 

    // - - - Methods - - - //

    public void appendPlayer(PlayerType playerReceived) {

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

    public void deletePlayer(String playerName) {

        Node <PlayerType> tempNode = headNode;
        Node <PlayerType> prevNode = null;
 
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

                //System.out.println(currentNode.getPlayerData().getPlayerNum() + " " + searchNode.getPlayerData().getPlayerNum() + " | " + currentNode.getPlayerData().getPlayerName() + " " + searchNode.getPlayerData().getPlayerName());

                if (currentNode.getPlayerData().getPlayerNum() != searchNode.getPlayerData().getPlayerNum() /* as long as nodes are not the same */ && currentNode.getPlayerData().getPlayerName().compareTo(searchNode.getPlayerData().getPlayerName() /* as long as nodes have same name */) == 0) { // if the names of nodes match and they are not the same node

                    //System.out.println("CHECKED");

                    searchNode.getPlayerData().addPlayerHitStat(currentNode.getPlayerData().getPlayerHitStat()); // adds the hit stat of search node to the current node

                    searchNode.getPlayerData().addPlayerOutStat(currentNode.getPlayerData().getPlayerOutStat()); // adds the out stat of search node to the current node

                    searchNode.getPlayerData().addPlayerStrikeOutStat(currentNode.getPlayerData().getPlayerStrikeOutStat()); // adds the strike out stat of search node to the current node

                    searchNode.getPlayerData().addPlayerWalkStat(currentNode.getPlayerData().getPlayerWalkStat()); // adds the walk stat of search node to current node

                    searchNode.getPlayerData().addPlayerHitByPitchStat(currentNode.getPlayerData().getPlayerHitByPitchStat()); // adds the hit by pitch stat of search node to current node

                    searchNode.getPlayerData().addPlayerSacrificeStat(currentNode.getPlayerData().getPlayerSacrificeStat()); // adds the player sacrifice stat of search node to current node

                    deletePlayer(currentNode.getPlayerData().getPlayerName());

                }

                currentNode = currentNode.getNextNode();

            }

            currentNode = headNode;
            searchNode = searchNode.getNextNode();

        }

        return;

    } // checkForMultipleEntries

    public void sortPlayers_Alpha() {

        Node<PlayerType> currentNode = headNode;

        while(checkListInOrder_Alpha() == false) {

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

    public Boolean checkListInOrder_Alpha() {

        Node<PlayerType> currentNode = headNode;

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

    public void printStatsRecursively(Node<PlayerType> currentNode) {

        if (currentNode != null) {

            System.out.println(currentNode.getPlayerData());

            printStatsRecursively(currentNode.getNextNode());

        }

        return; // breaks out of recursive function if the node is null

    } // printStatsRecursively

    public Boolean checkListInOrderByStat_GreatestToLeast(LinkList<PlayerType> desiredStatList, String desiredStat, Boolean isDoubleValue) {

        Node<PlayerType> currentNode = desiredStatList.getHeadNode(); // gets a copy of the alphabetized list

        while (currentNode != null && currentNode.getNextNode() != null) {

            if (isDoubleValue == false) { // use the following code if the stat is an integer
                
                // gets the stats from the nodes
                int currentNodeInteger = currentNode.getPlayerData().getCertainStatInteger(desiredStat);
                int currentNodeNextInteger = currentNode.getNextNode().getPlayerData().getCertainStatInteger(desiredStat);

                if (currentNodeInteger < currentNodeNextInteger) { // if the value is of the current node is less than the next node

                    return false; // the list is not from greatest to least

                }

            }
            else { // use the following code if the stat is a double

                // gets the stats from the nodes
                double currentNodeDouble = currentNode.getPlayerData().getCertainStatDouble(desiredStat);
                double currentNodeNextDouble = currentNode.getNextNode().getPlayerData().getCertainStatDouble(desiredStat);

                if (currentNodeDouble < currentNodeNextDouble) { // if the value is of the current node is less than the next node

                    return false; // the list is not from greatest to least

                }

            }

            currentNode = currentNode.getNextNode();

        }

        return true; // the list is from greatest to least

    } // checkListInOrderByStat_GreatestToLeast

    public void sortPlayersByStat_GreatestToLeast(LinkList<PlayerType> desiredStatList, String desiredStat, Boolean isDoubleValue) {

        Node<PlayerType> currentNode = desiredStatList.getHeadNode();

        while (desiredStatList.checkListInOrderByStat_GreatestToLeast(desiredStatList, desiredStat, isDoubleValue) == false) {
           
            if (isDoubleValue == false) { // use the following code if the stat is an integer

                if (currentNode != null && currentNode.getNextNode() != null) { // as long as the current and next to current node is empty, compare nodes to see if in order

                    int currentNodeInteger = currentNode.getPlayerData().getCertainStatInteger(desiredStat);
                    int currentNodeNextInteger = currentNode.getNextNode().getPlayerData().getCertainStatInteger(desiredStat);

                    if (currentNodeInteger < currentNodeNextInteger) { // if the value is of the current node is less than the next node
                        
                        desiredStatList.appendPlayer(currentNode.getPlayerData()); // append a copy of the player 
                        desiredStatList.deletePlayer(currentNode.getPlayerData().getPlayerName()); // delete the earlier instance of the player

                    }
                    
                    currentNode = currentNode.getNextNode();

                }

                if (currentNode.getNextNode() == null) { // if at the end of the linked list, go back to the beginning of the list until list is sorted

                    currentNode = desiredStatList.getHeadNode();
    
                }

            }
            else { // use the following code if the stat is a double

                if (currentNode != null && currentNode.getNextNode() != null) { // as long as the current and next to current node is empty, compare nodes to see if in order
                    
                    double currentNodeDouble = currentNode.getPlayerData().getCertainStatDouble(desiredStat);
                    double currentNodeNextDouble = currentNode.getNextNode().getPlayerData().getCertainStatDouble(desiredStat);

                    if (currentNodeDouble < currentNodeNextDouble) { // if the value is of the current node is less than the next node
                        
                        desiredStatList.appendPlayer(currentNode.getPlayerData()); // append a copy of the player 
                        desiredStatList.deletePlayer(currentNode.getPlayerData().getPlayerName()); // delete the earlier instance of the player

                    }

                    currentNode = currentNode.getNextNode();

                }

                if (currentNode.getNextNode() == null) { // if at the end of the linked list, go back to the beginning of the list until list is sorted

                    currentNode = desiredStatList.getHeadNode();
    
                }

            }

        }

        return;

    } // sortPlayersByStat_GreatestToLeasts

    public Boolean checkListInOrderByStat_LeastToGreatest(LinkList<PlayerType> desiredStatList, String desiredStat) { // function is essentially dedicated for the strikeout leader cause the smaller the value the better

        Node<PlayerType> currentNode = desiredStatList.getHeadNode(); // gets a copy of the alphabetized list

        while (currentNode != null && currentNode.getNextNode() != null) {
 
            // gets the stats from the nodes
            int currentNodeInteger = currentNode.getPlayerData().getCertainStatInteger(desiredStat);
            int currentNodeNextInteger = currentNode.getNextNode().getPlayerData().getCertainStatInteger(desiredStat);
                
            if (currentNodeInteger > currentNodeNextInteger) { // if the value is of the current node is more than the next node

                return false; // the list is not from least to greatest

            }

            currentNode = currentNode.getNextNode();

        }

        return true; // the list is from from least to greatest

    } // checkListInOrderByStat_LeastToGreatest

    public void sortPlayersByStat_LeastToGreatest(LinkList<PlayerType> desiredStatList, String desiredStat) {

        Node<PlayerType> currentNode = desiredStatList.getHeadNode();

        while (desiredStatList.checkListInOrderByStat_LeastToGreatest(desiredStatList, desiredStat) == false) {

            if (currentNode != null && currentNode.getNextNode() != null) { // as long as the current and next to current node is empty, compare nodes to see if in order

                int currentNodeInteger = currentNode.getPlayerData().getCertainStatInteger(desiredStat);
                int currentNodeNextInteger = currentNode.getNextNode().getPlayerData().getCertainStatInteger(desiredStat);

                if (currentNodeInteger > currentNodeNextInteger) { // if the value is of the current node is more than the next node
                        
                    desiredStatList.appendPlayer(currentNode.getPlayerData()); // append a copy of the player 
                    desiredStatList.deletePlayer(currentNode.getPlayerData().getPlayerName()); // delete the earlier instance of the player

                }

                currentNode = currentNode.getNextNode();

            }

            if (currentNode.getNextNode() == null) { // if at the end of the linked list, go back to the beginning of the list until list is sorted
                    
                currentNode = desiredStatList.getHeadNode();
    
            }

        }

        return;

    } // sortPlayersByStat_GreatestToLeasts

    public ArrayList<ArrayList<PlayerType>> findLeadersDouble(LinkList<PlayerType> desiredLinkList, String desiredStat) {  // arraylists in this program are only used to help store data from linked list, according to a PIAZZA post, Proffessor Smith has allowed us to use arraylists to help with storing leaders

        ArrayList<ArrayList<PlayerType>> desiredStatArrayList = new ArrayList<ArrayList<PlayerType>>();

        Node<PlayerType> currentNode = desiredLinkList.getHeadNode(); // set up current node to help traverse linked list

        ArrayList<PlayerType> firstLeaders = new ArrayList<PlayerType>();

        double tiedStatCheckFirst = currentNode.getPlayerData().getCertainStatDouble(desiredStat);
        int numOfPlayersInFirst = 0;

        while(currentNode != null && currentNode.getPlayerData().getCertainStatDouble(desiredStat) == tiedStatCheckFirst) { // extract up to 3 players which should be the leader of each stat 

            firstLeaders.add(currentNode.getPlayerData());
            
            numOfPlayersInFirst++;

            currentNode = currentNode.getNextNode();
            
        }

        desiredStatArrayList.add(firstLeaders);

        if (numOfPlayersInFirst >= 3) {
            
           return desiredStatArrayList; // more than 3 leaders in first

        }

        ArrayList<PlayerType> secondLeaders = new ArrayList<PlayerType>();

        double tiedStatCheckSecond = currentNode.getPlayerData().getCertainStatDouble(desiredStat);
        int numOfPlayersInSecond = 0;

        while(currentNode != null && currentNode.getPlayerData().getCertainStatDouble(desiredStat) == tiedStatCheckSecond) { // extract up to 3 players which should be the leader of each stat 

            secondLeaders.add(currentNode.getPlayerData());
            
            numOfPlayersInSecond++;

            currentNode = currentNode.getNextNode();
            
        }

        desiredStatArrayList.add(secondLeaders);

        if (numOfPlayersInSecond + numOfPlayersInFirst >= 3) {
            
            return desiredStatArrayList; // less than 3 leaders in first
 
        }

        ArrayList<PlayerType> thirdLeaders = new ArrayList<PlayerType>();

        double tiedStatCheckThird = currentNode.getPlayerData().getCertainStatDouble(desiredStat);

        while(currentNode != null && currentNode.getPlayerData().getCertainStatDouble(desiredStat) == tiedStatCheckThird) { // extract up to 3 players which should be the leader of each stat 

            thirdLeaders.add(currentNode.getPlayerData());

            currentNode = currentNode.getNextNode();
            
        }

        desiredStatArrayList.add(thirdLeaders);
        
        return desiredStatArrayList; // no ties for 1st or 2nd, so one leader per place

    } // findLeadersDouble

    public ArrayList<ArrayList<PlayerType>> findLeadersInteger(LinkList<PlayerType> desiredLinkList, String desiredStat) {  // arraylists in this program are only used to help store data from linked list, according to a PIAZZA post, Proffessor Smith has allowed us to use arraylists to help with storing leaders

        ArrayList<ArrayList<PlayerType>> desiredStatArrayList = new ArrayList<ArrayList<PlayerType>>();

        Node<PlayerType> currentNode = desiredLinkList.getHeadNode(); // set up current node to help traverse linked list

        ArrayList<PlayerType> firstLeaders = new ArrayList<PlayerType>();

        int tiedStatCheckFirst = currentNode.getPlayerData().getCertainStatInteger(desiredStat);
        int numOfPlayersInFirst = 0;

        while(currentNode != null && currentNode.getPlayerData().getCertainStatInteger(desiredStat) == tiedStatCheckFirst) { // extract up to 3 players which should be the leader of each stat 

            firstLeaders.add(currentNode.getPlayerData());
            
            numOfPlayersInFirst++;

            currentNode = currentNode.getNextNode();
            
        }

        desiredStatArrayList.add(firstLeaders);

        if (numOfPlayersInFirst >= 3) {
            
           return desiredStatArrayList; // more than 3 leaders in first

        }

        ArrayList<PlayerType> secondLeaders = new ArrayList<PlayerType>();

        int tiedStatCheckSecond = currentNode.getPlayerData().getCertainStatInteger(desiredStat);
        int numOfPlayersInSecond = 0;

        while(currentNode != null && currentNode.getPlayerData().getCertainStatInteger(desiredStat) == tiedStatCheckSecond) { // extract up to 3 players which should be the leader of each stat 

            secondLeaders.add(currentNode.getPlayerData());
            
            numOfPlayersInSecond++;

            currentNode = currentNode.getNextNode();
            
        }

        desiredStatArrayList.add(secondLeaders);

        if (numOfPlayersInSecond >= 2) {
            
            return desiredStatArrayList; // more than 3 leaders in first
 
        }

        ArrayList<PlayerType> thirdLeaders = new ArrayList<PlayerType>();

        int tiedStatCheckThird = currentNode.getPlayerData().getCertainStatInteger(desiredStat);

        while(currentNode != null && currentNode.getPlayerData().getCertainStatInteger(desiredStat) == tiedStatCheckThird) { // extract up to 3 players which should be the leader of each stat 

            thirdLeaders.add(currentNode.getPlayerData());

            currentNode = currentNode.getNextNode();
            
        }

        desiredStatArrayList.add(thirdLeaders);
        
        return desiredStatArrayList; // no ties for 1st or 2nd, so one leader per place

    } // findLeadersInteger

    // - - - Getter Methods - - - //

    public Node<PlayerType> getHeadNode() {

        return headNode;

    } // getHeadNode

    // - - - Testing - - - //
    
}
