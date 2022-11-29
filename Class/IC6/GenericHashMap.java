import java.util.ArrayList;

public class GenericHashMap {

    // size of hash table
    private int hashTableSize;

    // hash table
    private ArrayList<LinkList<Integer>> hashTable; 
    
    // number of elements added to hash table
    private int itemCount;

    // number of linked list utilized
    private int listCount;

    // lamda value
    final private Double lamdaValue = 2.0;

    GenericHashMap() {

        hashTableSize = 10; // set initial size of hash map

        hashTable = new ArrayList<LinkList<Integer>>(hashTableSize); // create hash table array list

        // initialize elements of array list
        for (int hashIndex = 0; hashIndex < hashTableSize; hashIndex++) {

            LinkList<Integer> newLinkList = new LinkList<Integer>();

            hashTable.add(newLinkList); // create a new integer link list for each element in the array list
                
        }

    } // Constructor - Default

    private void rehashTable() {

        hashTableSize = 2 * hashTableSize;

        ArrayList<LinkList<Integer>> oldHashTable = hashTable;

        hashTable = new ArrayList<LinkList<Integer>>(hashTableSize);

        // initialize elements of array list
        for (int hashIndex = 0; hashIndex < hashTableSize; hashIndex++) {

            LinkList<Integer> newLinkList = new LinkList<Integer>();

            hashTable.add(newLinkList); // create a new integer link list for each element in the array list
                
        }

        for (int hashIndex = 0; hashIndex < hashTableSize / 2; hashIndex++) { // loop through the old hash table

            ArrayList<Integer> currentList = oldHashTable.get(hashIndex).outputDataIntoArrayList(); // extract the link list from the hash table

            for (int arrayIndex = 0; arrayIndex < currentList.size(); arrayIndex++) { // put the elements into the new hash table

                put(currentList.get(arrayIndex));

            }

        }

    } // rehashTable

    public void put (int keyItem) {

        Double loadFactor = itemCount / (double)listCount;

        if ((listCount != 0 && itemCount != 0) && lamdaValue.compareTo(loadFactor) <= 0) {

            System.out.println("Rehashing!");
            System.out.println((itemCount / (double)listCount) + " is greater than 2...");

            // reset the counters
            listCount = 0;
            itemCount = 0;

            rehashTable();

        }

        int hashCode = keyItem % hashTableSize;
        
        if (hashTable.get(hashCode).getHeadNode() == null) { // if the element at the hash code link list is empty

            hashTable.get(hashCode).appendPlayer(keyItem);

            itemCount++;

            listCount++;

        }
        else { // if the element at the hash code link list already has  an element

            hashTable.get(hashCode).appendPlayer(keyItem);

            itemCount++;

        }

    } // put

    public void printHashTable() {

        System.out.println("Number of Items: " + itemCount);

        System.out.println("Number of Lists: " + listCount);

        for (int hashIndex = 0; hashIndex < hashTableSize; hashIndex++) {

            if (hashTable.get(hashIndex).getHeadNode() != null) {

                System.out.println(hashTable.get(hashIndex).outputDataIntoArrayList());

            }
            else {

                System.out.println("_");

            }

        }

    } // printHashTable

}