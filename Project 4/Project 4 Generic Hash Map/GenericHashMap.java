/* 
 * Project 4: Super Mario Sluggers with Hashmaps
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 11/09/2022
 * Class & Section: CS - 2366.003
 */

// ArrayList
import java.util.ArrayList;

public class GenericHashMap<K extends Comparable<K>, V> {

    private class Entry<Key extends Comparable<Key>, Value> implements Comparable<Key> {

        private Key keyItem;
        private Value valueItem;

        public Entry(Key keyItemReceived, Value valueItemReceived) {

            keyItem = keyItemReceived;
            valueItem = valueItemReceived;

        } // Constructor - Entry

        // compareTo Method
        public int compareTo(Key keyItemReceived) {

            return keyItem.compareTo(keyItemReceived);

        }

        // Getter Methods

        public Key getKey() { return keyItem; }

        public Value getValue() { return valueItem; }

        // Setter Methods

        //public void setValue(Value valueItemReceived) { valueItem = valueItemReceived; }

    } // Private Class - Entry

    private final int SELECT_PRIME = 7;

    private final double LOAD_FACTOR = 0.5;

    private int elementsInHashTable = 0;

    private int hashTableSize = 11;
    
    private ArrayList<Entry<K, V>> hashTable;

    public GenericHashMap() {

        hashTable = new ArrayList<Entry<K, V>>(hashTableSize);

        hashTable = initializeToZero(hashTable);

    } // Constructor - GenericHashMap
    
    private int findNextHashTableSize() {

        int newHashTableSize = 2 * hashTableSize;

        boolean isPrime = isNumberPrime(newHashTableSize);

        while (isPrime == false) {

            newHashTableSize++; // increment number till finds a prime
            isPrime = isNumberPrime(newHashTableSize);

        }

        return newHashTableSize;

    }

    private boolean isNumberPrime(int numberReceived) {

        boolean isPrime = true;

        for (int dividingNumber = 2; dividingNumber < numberReceived; dividingNumber++) { // loop through numbers to check if there are possible divsors

            if (numberReceived % dividingNumber == 0) { // loop found a divisible number so it is not prime

                isPrime = false;
                break;

            }

        }

        return isPrime;

    }

    private void rehashHashTable() {

        ArrayList<Entry<K, V>> oldHashTable = hashTable; // stores the old hash table temporarily

        hashTableSize = findNextHashTableSize(); // finds the double of the initial size then find the next closest prime numberd
        hashTable = new ArrayList<Entry<K, V>>(hashTableSize); // creates a new hash table with the new hash table size
        hashTable = initializeToZero(hashTable);

        for (int arrayIndex = 0; arrayIndex < oldHashTable.size(); arrayIndex++) { // loop through elements in old hash table

            Entry<K, V> tempEntry = oldHashTable.get(arrayIndex);

            if (tempEntry != null) { // as long as the element at index is not empty

                put(tempEntry.getKey(), tempEntry.getValue()); // add the  old entry to the new hash table

            }

        }

    }

    public void put(K keyItemReceived, V valueItemRecieved) { // adds elements to hash table

        int hashCode = Math.abs(keyItemReceived.hashCode() % hashTableSize);
        
        double currentLoadFactor = elementsInHashTable / (double)hashTableSize;
        
        if (currentLoadFactor >= LOAD_FACTOR) { // if the current load factor is greater than the limit, rehash the table

            elementsInHashTable = 0; // a new hash table is going to be created so set elements counted to zero

            rehashHashTable(); // create new hash table with new size

        }

        if (hashTable.get(hashCode) == null) { // if the element at index is empty, add new entry

            hashTable.set(hashCode, new Entry<K, V>(keyItemReceived, valueItemRecieved)); // adds new entry at empty index
            elementsInHashTable++;

        }
        else {

            int jumpIndex = 0;

            int hashOne = hashCode;

            int hashTwo = SELECT_PRIME - (keyItemReceived.hashCode() % SELECT_PRIME);

            while (hashTable.get((hashOne + jumpIndex * hashTwo) % hashTableSize) != null) { // keeps looping through double hash formula until empty space is found

                jumpIndex++;

            }

            hashTable.set(((hashOne + jumpIndex * hashTwo) % hashTableSize), new Entry<K, V>(keyItemReceived, valueItemRecieved)); // adds new entry at empty index
            elementsInHashTable++;

        }
    
    } // put

    public V get(K keyItemReceived) {

        int hashCode = Math.abs(keyItemReceived.hashCode() % hashTableSize);
        
        if (hashTable.get(hashCode) == null) { // if the initial hash code is empty, it means the key could not be found, so return null 
            
            return null;

        }

        int jumpIndex = 0;

        int hashOne = hashCode;

        int hashTwo = SELECT_PRIME - (keyItemReceived.hashCode() % SELECT_PRIME);

        while (hashTable.get((hashOne + jumpIndex * hashTwo) % hashTableSize).getKey().compareTo(keyItemReceived) != 0) { // the hash code was not empty, but it was not the key we were looking for
            
            jumpIndex++;

            if (hashTable.get((hashOne + jumpIndex * hashTwo) % hashTableSize) == null || (hashOne + jumpIndex * hashTwo) % hashTableSize >= hashTableSize) { // if the double hashing formula calls for an element outside of the array list size, key could not be found
                
                return null;

            }

        }
        
        return hashTable.get((hashOne + jumpIndex * hashTwo) % hashTableSize).getValue(); // if it breaks out of the while loop, return value associated with inputted key

    } // get

    public ArrayList<V> values() {

        ArrayList<V> listOfValues = new ArrayList<V>();

        for (int arrayIndex = 0; arrayIndex < hashTableSize; arrayIndex++) { // loops through hash table

            if (hashTable.get(arrayIndex) != null) { // if the element is not empty, extract the entry object's value

                listOfValues.add(hashTable.get(arrayIndex).getValue()); // store the value into an array list

            }

        }

        return listOfValues;

    } // values

    public ArrayList<K> keys() {

        ArrayList<K> listOfKeys = new ArrayList<K>();

        for (int arrayIndex = 0; arrayIndex < hashTableSize; arrayIndex++) { // loops through hash table

            if (hashTable.get(arrayIndex) != null) { // if the element is not empty, extract the entry object's value

            listOfKeys.add(hashTable.get(arrayIndex).getKey()); // store the key into an array list

            }
            else {

                listOfKeys.add(null);

            }

        }

        return listOfKeys;

    } // values

    public ArrayList<Entry<K, V>> initializeToZero(ArrayList<Entry<K, V>> receivedHashTable) {

        for (int arrayIndex = 0; arrayIndex < hashTableSize; arrayIndex++) { // set the the array list elements to null

            hashTable.add(null);

        }

        return receivedHashTable;

    } // initializeToZero

}
